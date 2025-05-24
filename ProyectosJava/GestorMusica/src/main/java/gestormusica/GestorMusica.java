package gestormusica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que permet gestionar una biblioteca de música. Mostra un menú a
 * l'usuari amb opcions per cercar a un directori tots els arxius mp3 per
 * afegir-los a la biblioteca, editar les etiquetes ID3 dels arxius i
 * reproduir arxius de la biblioteca. 
 * Es pot executar la classe directament des de l'IDE, però per tal de que 
 * la visualització sigui la correcta (a la consola dels IDE el mètode 
 * d'esborrar pantalla no funciona correctament), es recomana executar-la des 
 * d'un terminal de Windows (cmd.exe) o de GNU/Linux. Un cop obert el terminal, 
 * s'ha de canviar al directori on es trobar el jar i executar: 
 * 
 *    java -jar gestormusica-0.0.1-SNAPSHOT.jar arxiu_csv
 *
 * Com a únic paràmetre, se li ha de proporcionar la ruta de
 * l'arxiu csv amb les dades de la biblioteca.
 * 
 */
public class GestorMusica {
	
	private Biblioteca biblio;

	/**
	 * Constructor de la classe. Inicialitza els atributs de la classe. Obre
	 * l'arxiu csv indicat com a paràmetre amb les dades de la biblioteca i amb
	 * les dades de cada línia crea una instància de Canço i l'afegeix a l'atribut
	 * llista de la classe.
	 *
	 * @param arxiuBiblioteca la ruta de l'arxiu amb les dades de la biblioteca,
	 * on cada línia conté les dades d'una de les cançons, amb el format:
	 * autor;titol;ruta_absoluta_de_l'arxiu
	 */
	public GestorMusica(String arxiuBiblioteca) {
		this.biblio = new Biblioteca(arxiuBiblioteca);
	}

	/**
	 * Mètode que a partir de la llista de rutes absolutes a arxius mp3 rebudes com a paràmetre,
	 * llegeix les etiquetes ID3 dels arxius mp3 i crea instàncies de Canso a partir d'aquestes dades, 
	 * afegint-les a la biblioteca.
	 * @param rutes les rutes absolutes als arxius mp3
	 */
	public void afegirCansonsLlista(ArrayList<String> rutes) {
		// Aquest mètode us pot ser útil a l'hora d'escanejar un directori cercant arxius mp3
		// per afegir-los a la biblioteca. Per entendre millor el funcionament consulteu l'annex
		// de l'enunciat referent a les etiquetes ID3
		String titol;
		String autor;
		for (String r : rutes) {
			titol = "Sense titol";
			autor = "Sense autor";
			try {
				RandomAccessFile arxiu = new RandomAccessFile(r, "r");
				arxiu.seek(arxiu.length() - 128);
				byte[] b = new byte[3];
				arxiu.read(b);
				String tag = new String(b);
				// L'arxiu conté etiqueta ID3v1
				if ("TAG".equals(tag)) {
					b = new byte[30];
					arxiu.read(b);
					titol = new String(b);
					titol = Utils.retallaZeros(titol);
					arxiu.read(b);
					autor = new String(b);
					autor = Utils.retallaZeros(autor);
				}
				Canso canso = new Canso(autor, titol, r);
				biblio.afegirCanso(canso);
				String ruta = r.replace(".mp3", ".txt");
			    File arxiuLletra = new File(new File(r).getParent(), ruta);
			    if (arxiuLletra.exists()) {
			        canso.setArxiuLletra(arxiuLletra.getAbsolutePath());
					canso.cargarLletra();
			    }
				arxiu.close();
			} catch (FileNotFoundException ex) {
				Utils.mostraError("no s'ha trobat l'arxiu " + r);
			} catch (IOException ex) {
				Utils.mostraError("no s'ha pogut llegir de l'arxiu " + r);
			}
		}
		Utils.mostraMissatge("Se han procesat " + rutes.size() + " cansons");
	}

	private ArrayList<String> escanejarDirectori(String ruta) {
		File directori = new File(ruta);
		ArrayList<String> rutes = new ArrayList<>();
		if (!directori.exists() || !directori.isDirectory()) {
	        Utils.mostraError("La ruta no existeix o no és un directori vàlid");
	        return null;
	    }
		File[] arxius = directori.listFiles();
		if (arxius != null) {
	        for (File arxiu : arxius) {
	            if (arxiu.isDirectory()) {
	                rutes.addAll(escanejarDirectori(arxiu.toString()));
	            } else if (arxiu.getName().toLowerCase().endsWith(".mp3")) {
	                rutes.add(arxiu.toString());
	            }
	        }
	    }
		return rutes;
	}
	
	/**
	 * Retorna una llista en format String de les cançons, útil per mostrar-les per pantalla
	 * @param cancons la llista de cançons
	 * @return la llista en format String
	 */
	private List<String> llistarTitols(List <Canso> cancons){
		List<String> titols = new ArrayList<>();
		for(Canso c: cancons) {
			titols.add(c.toString());
		}
		return titols;
	}	
	
	private void editarEtiquetes() {
	    if (biblio.getLlista().isEmpty()) {
	        Utils.mostraError("La biblioteca està buida");
	        Utils.pausaFinsTecla();
	        return;
	    }
	    int opcio = Utils.demanaOpcioLlista("Selecciona una canso per editar", llistarTitols(biblio.getLlista()));
	    Canso canso = biblio.getLlista().get(opcio);
	    Utils.mostraMissatge("EDITANT CANÇÓ:");
	    Utils.mostraMissatge("Autor actual: " + canso.getAutor());
	    Utils.mostraMissatge("Títol actual: " + canso.getTitol());
	    String nouAutor = Utils.demanaText("Nou autor (deixa en blanc per no canviar)");
	    String nouTitol = Utils.demanaText("Nou títol (deixa en blanc per no canviar)");
	    if (!nouAutor.isEmpty() || !nouTitol.isEmpty()) {
	        if (!nouAutor.isEmpty()) {
	            canso.setAutor(nouAutor);
	        }
	        if (!nouTitol.isEmpty()) {
	            canso.setTitol(nouTitol);
	        }
	        if (actualitzarEtiquetesID3(canso)) {
	            Utils.mostraMissatge("Cançó actualitzada correctament!");
	            biblio.salvar();
	        } else {
	            Utils.mostraError("No s'ha pogut actualitzar l'arxiu MP3");
	        }
	    } else {
	        Utils.mostraMissatge("No s'han fet canvis");
	    }
	}
	
	private boolean actualitzarEtiquetesID3(Canso canco) {
	    File arxiuMP3 = new File(canco.getArxiu());
	    try (RandomAccessFile arxiu = new RandomAccessFile(arxiuMP3, "rw")) {
	        boolean teEtiqueta = false;
	        if (arxiu.length() >= 128) {
	            arxiu.seek(arxiu.length() - 128);
	            byte[] tag = new byte[3];
	            arxiu.read(tag);
	            teEtiqueta = "TAG".equals(new String(tag));
	        }
	        if (teEtiqueta) {
	            arxiu.seek(arxiu.length() - 125);
	        } else {
	            arxiu.seek(arxiu.length());
	            arxiu.write("TAG".getBytes());
	        }
	        byte[] titolBytes = Arrays.copyOf(canco.getTitol().getBytes(), 30);
	        arxiu.write(titolBytes);
	        byte[] autorBytes = Arrays.copyOf(canco.getAutor().getBytes(), 30);
	        arxiu.write(autorBytes);
	        if (!teEtiqueta) {
	            byte[] empty = new byte[93];
	            arxiu.write(empty);
	        }
	        return true;
	    } catch (IOException e) {
	        Utils.mostraError("Error en actualitzar etiquetes ID3: " + e.getMessage());
	        return false;
	    }
	}

	/**
	 * Demana a l'usuari que trii una cançó de la llista i a continuació la reprodueix
	 */
	private void reproduir() {
		Utils.netejaPantalla();
		if (biblio.getLlista().size() == 0) {
			Utils.mostraError("La biblioteca està buida");
			Utils.mostraMissatge("Premi una tecla per tornar al menú principal...");
			Utils.pausaFinsTecla();
		} else {
			int opcio = Utils.demanaOpcioLlista("Llistat de cançons", llistarTitols(biblio.getLlista()));
			Reproductor reproductor = new Reproductor(biblio.getLlista().get(opcio));
			reproductor.reproduir();
		}
	}

	/**
	 * Emmagatzema la llista de cançons a l'arxiu csv
	 */
	private void salvarBiblioteca() {
		biblio.salvar();
	}
	
	private void gestionarPlayLists() {
		boolean tornar = false;
		List<String> opcions = Arrays.asList(
				"Crear nova playlist",
				"Carregar nova playlist",
				"Eliminar playlist",
				"Reproduir playlist",
				"Tornar al menú principal"
			);
		while (!tornar) {
			Utils.netejaPantalla();
			int opcio = Utils.demanaOpcioLlista("GESTIÓ DE PLAYLISTS", opcions);
			switch (opcio) {
			case 0: 
				crearPlayList(); 
				break;
			case 1: 
				carregarPlayList(); 
				break;
			case 2: 
				eliminarPlayList(); 
				break;
			case 3: 
				reproduirPlayList(); 
				break;
			case 4: 
				tornar = true; 
				break;
			}
		}
	}
	
	private void crearPlayList() {
		String nom = Utils.demanaText("Introdueix el nom de la nova playlist");
		PlayList nova = new PlayList(nom);
		boolean afegirMes = true;
		while (afegirMes && !biblio.getLlista().isEmpty()) {
			int opcio = Utils.demanaOpcioLlista("Selecciona una cançó per afegir:", llistarTitols(biblio.getLlista()));
            nova.afegirCanso(biblio.getLlista().get(opcio));
    		List<String> opcions = Arrays.asList(new String[] {
    				"Afegir altre", 
    				"Sortir"});
    		switch (Utils.demanaOpcioLlista("Vols afegir una altra cançó?", opcions)) {
    		case 0:
                afegirMes = true;
                break;
    		case 1:
                afegirMes = false;
                break;
    		}
        }
        
        biblio.afegirPlayList(nova);
        biblio.afegirPlayListArxiu(nova);
        Utils.mostraMissatge("Playlist creada correctament!");
        Utils.pausaFinsTecla();
    }
	
    private void eliminarPlayList() {
        List<PlayList> playlists = biblio.getLlistaPL();
        List<String> playlistsNom = new ArrayList<String>();
        for(PlayList pl: playlists) {
        	playlistsNom.add(pl.getNom());
        }
        if (playlistsNom.isEmpty()) {
            Utils.mostraError("No hi ha playlists guardades");
            Utils.pausaFinsTecla();
            return;
        }
        int opcio = Utils.demanaOpcioLlista("Selecciona una playlist per eliminar:", playlistsNom);
        List<String> opcions = Arrays.asList(new String[] {
				"Si, vull eliminar la playlist " + playlistsNom.get(opcio), 
				"No, no vull eliminar la playlist " + playlistsNom.get(opcio)});
		switch (Utils.demanaOpcioLlista("Segur que vols eliminar la playlist " + playlistsNom.get(opcio) + "?", opcions)) {
		case 0:
			biblio.eliminarPlayListArxiu(playlists.get(opcio));
			biblio.eliminarPlayList(playlists.get(opcio));
			Utils.mostraMissatge("Playlist eliminada correctament");
            break;
		case 1:
			Utils.mostraMissatge("Playlist no eliminada");
            break;
		}
        Utils.pausaFinsTecla();
    }
    
    private void carregarPlayList() {
    	String nomArxiu = Utils.demanaText("Posa el nom de l'arxiu que esta en la carpeta playlist");
		PlayList playlist= biblio.carregarPlayListArxiu(nomArxiu);
		if(playlist != null) {
			biblio.afegirPlayList(playlist);
			Utils.mostraMissatge("Playlist creada correctament!");
		}
        Utils.pausaFinsTecla();
    }
    
    private void reproduirPlayList() {
        List<PlayList> playlists = biblio.getLlistaPL();
        List<String> playlistsNom = new ArrayList<String>();
        for(PlayList pl: playlists) {
        	playlistsNom.add(pl.getNom());
        }
        if (playlistsNom.isEmpty()) {
            Utils.mostraError("No hi ha playlists guardades");
            Utils.pausaFinsTecla();
            return;
        }
        int opcio = Utils.demanaOpcioLlista("Selecciona una playlist per reproduir:", playlistsNom);
        PlayList playlist = playlists.get(opcio);
        if (playlist != null) {
            for (Canso canso : playlist.getLlista()) {
                if (canso.getArxiu() != null) {
                    new Reproductor(canso).reproduir();
                } else {
                    Utils.mostraMissatge("Canso no trobada: " + canso.getArxiu());
                }
            }
        }
    }

	/**
	 * Mètode principal que inicia l'execució de la instància de GestorMusica.
	 * Mostra un menú d'opcions i executar l'opció triada fins que aquest 
	 * seleccioni l'opció de finalitzar, moment en el qual s'hauran de salvar
	 * les dades de la biblioteca a l'arxiu CSV, amb els canvis que s'hagin
	 * produït durant l'execució (canvis a les etiquetes ID3 i/o addició de
	 * nous arxius trobats en escanejar directoris
	 */
	public void iniciar() {
		boolean finalitzar = false;
		List<String> opcions = Arrays.asList(new String[] {
				"Escanejar directori", 
				"Editar etiquetes ID3",
				"Reproduir canço",
				"Gestionar playlists",
				"Sortir"});
		while (!finalitzar) {
			Utils.netejaPantalla();
			switch (Utils.demanaOpcioLlista("MENU PRINCIPAL", opcions)) {
			case 0:
				String ruta= Utils.demanaText("Introdueix la ruta del directori a escanejar");
				afegirCansonsLlista(escanejarDirectori(ruta));
				break;
			case 1:
				editarEtiquetes();
				break;
			case 2:
				reproduir();
				break;
			case 3:
				gestionarPlayLists();
				break;
			case 4:
				finalitzar = true;
				Utils.mostraMissatge("Sortint de l'aplicació ...");
				salvarBiblioteca();
				Utils.mostraMissatge("S'han salvat les dades de la biblioteca a " + biblio.getArxiuBiblioteca());
				Utils.mostraMissatge("Prem qualsevol tecla per finalitzar");
				Utils.pausaFinsTecla();
				break;
			}
		}

	}

	public static void main(String[] args) {
		if (args.length != 1) {
			Utils.mostraError("Ús: GestorMusica nom_arxiu.csv");
		} else {
			GestorMusica gestor = new GestorMusica(args[0]);
			gestor.iniciar();
		}
	}
}

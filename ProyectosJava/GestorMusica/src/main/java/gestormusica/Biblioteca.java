package gestormusica;

import com.google.gson.annotations.Expose;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "biblioteca")
@XmlAccessorType(XmlAccessType.FIELD) 
public class Biblioteca {
	
	/**
	 * ArrayList amb les dades de les cançons que formen la biblioteca
	 */
	@Expose
	@XmlElementWrapper(name = "llista")
    @XmlElement(name = "canso")
	private List<Canso> llista;
	
	@Expose
	@XmlElementWrapper(name = "llistaPlayLists")
    @XmlElement(name = "playlist")
	private List<PlayList> llistaPlayLists;
	/**
	 * Ruta de l'arxiu csv amb la biblioteca musical
	 */
	@XmlElement(required = true)
	private String arxiuBiblioteca;

	public Biblioteca(String arxiuBiblioteca) {
		this.arxiuBiblioteca = arxiuBiblioteca;
		llista = new ArrayList<>();
		llistaPlayLists= new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(this.arxiuBiblioteca))) {
			String linea = reader.readLine();
			while (linea != null) {
				String[] dades = linea.split(";");
				Canso canso = new Canso(dades[0], dades[1], dades[2]);
				llista.add(canso);
				linea = reader.readLine();
			}
		} catch (IOException ex) {
			Utils.mostraError("No s'ha pogut obrir l'arxiu de la biblioteca musical");
		}
	}

	public List<Canso> getLlista() {
		return llista;
	}
	public List<PlayList> getLlistaPL() {
		return llistaPlayLists;
	}
	
	public String getArxiuBiblioteca() {
		return arxiuBiblioteca;
	}

	public void setArxiuBiblioteca(String arxiuBiblioteca) {
		this.arxiuBiblioteca = arxiuBiblioteca;
	}
	
	public void afegirCanso(Canso canso) {
		llista.add(canso);
	}
	
	public boolean eliminarCanso(Canso canso) {
        return llista.remove(canso);
    }
	
	public void afegirPlayList(PlayList pl) {
		llistaPlayLists.add(pl);
	}
	
	public boolean eliminarPlayList(PlayList pl) {
		Utils.mostraMissatge("Borrant playlist "+pl.getNom());
        return llistaPlayLists.remove(pl);
    }
	
	public void salvar() {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(arxiuBiblioteca),
					Charset.forName("UTF-8"), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
			for (Canso c : llista) {
				writer.write(c.getAutor() + ";" + c.getTitol() + ";" + c.getArxiu());
				writer.newLine();
			}
			writer.close();
		} catch (IOException ex) {
			Utils.mostraError("ERROR en obrir arxiu de la biblioteca per escritura");
		}
	}
	
	public List<Canso> buscarCansons(String criteri) {
		return llista.stream().filter(c -> c.coincideixAmb(criteri)).toList();
	}
	
	public void afegirPlayListArxiu(PlayList playlist) {
		File d = new File("playlists/");
		if(!d.exists()) {
			d.mkdirs();
		}
		File arxiu = new File("playlists/" + playlist.getNom() + ".xml");
		try {
    		JAXBContext context = JAXBContext.newInstance(PlayList.class);
    		Marshaller marshaller = context.createMarshaller();
    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		marshaller.marshal(playlist, arxiu);
		} catch (PropertyException e) {
			Utils.mostraError(e.getMessage());
		} catch (JAXBException e) {
			Utils.mostraError(e.getMessage());
		}
    }
	
	public PlayList carregarPlayListArxiu(String nom) {
		try {
			File arxiu = new File("playlists/"+nom);
			if (!arxiu.exists()) {
				Utils.mostraMissatge("El fitxer " + nom + " no existeix!");
				return null;
			}
			JAXBContext context = JAXBContext.newInstance(PlayList.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PlayList) unmarshaller.unmarshal(arxiu);	
		} catch (PropertyException e) {
			Utils.mostraError(e.getMessage());
		} catch (JAXBException e) {
			Utils.mostraError(e.getMessage());
		}
		return null;
    }
	
	
	public void eliminarPlayListArxiu(PlayList playlist) {
		Utils.mostraMissatge("Borrant arxiu "+playlist.getNom()+".xml");
		File arxiu = new File("playlists/" + playlist.getNom() + ".xml");
		if (arxiu.exists()) {
            arxiu.delete();
        }
    }
}

package heroes_Of_Drakthoria;

import java.util.Scanner;

public class Game {
	
	/**
	 * Funcio per iniciar el joc HoD
	 */
	public void start() {
		Scanner in = new Scanner(System.in);
		boolean jugar = true;
		objectesPredeterminats();
		while (jugar) {
			System.out.println(
					"\n\tMenu d'opcions\n\t1 - Personatges\n\t2 - Combatre\n\t3 - Estadistiques\n\t4 - Sortir\n");
			System.out.print("\tAgafa una opcio del menu: ");
			int opcio = demanaOpcio(1, 4);
			switch(opcio) {
			case 1:
				menuPersonatges();
				break;
			case 2:
				Personatge p1 = Registre.getPersonatge();
				boolean demanaSegon = true;
				Personatge p2 = null;
				while (demanaSegon) {
					 p2 = Registre.getPersonatge();
					 if (p1 != p2) {
						 demanaSegon = false;
					 } else {
						 new Error(7);
					 }
				}
				new Combat(p1, p2);
				break;
				
			case 3:
				menuEstadistiques();
				break;
			case 4:
				jugar = false;
				break;
			}

		}
		
		System.out.println("\n\tHas sortit del joc, adeu!");
	}
	/**
	 * Aquesta funcio declara els objectes per defecte
	 */
	private void objectesPredeterminats() {
		new Arma("DAGA", 5, 15);
		new Arma("ESPASA", 10, 10);
		new Arma("MARTELL", 15, 5);
		new Amulet("SelloDeWrynn", 2, 3, 5, 4, 1);
		new Amulet("SortijaHelada", 2, 1, 2, 5, 3);
		new Armadura("TogaDelArchimago", 4, 4, 4);
		new Armadura("HombrerasBarbaricas", 4, 9);
		new Armadura("CapaDeEscamasDeOnyxia", 7, 5);
		int [] stats1 = {14, 15, 6, 14, 11}, stats2 = {17, 18, 8, 7, 10}, stats3 = {6, 14, 12, 12, 16}, stats4 = {7, 9, 16, 16, 12};
		new Cavaller("Baboo", Tipologia.CAVALLER, Registre.getArma("ESPASA"), Registre.getAmulet("SelloDeWrynn"), Registre.getArmadura("HombrerasBarbaricas"), stats1, new AtacRapid(), new DefensaConcentrada());
		new Guerrer("Yonko", Tipologia.GUERRER, Registre.getArma("MARTELL"), Registre.getAmulet("SortijaHelada"), Registre.getArmadura("CapaDeEscamasDeOnyxia"), stats2, new CopPoderos(), new AtacFurtiu());
		new Valquiria("Saylor", Tipologia.VALQUIRIA, Registre.getArma("ESPASA"), Registre.getAmulet("SortijaHelada"), Registre.getArmadura("TogaDelArchimago"), stats3, new AtacRapid(), new Curacio());
		new Assassi("Kaito", Tipologia.ASSASSI, Registre.getArma("DAGA"), Registre.getAmulet("SelloDeWrynn"), Registre.getArmadura("HombrerasBarbaricas"), stats4, new AtacFurtiu(), new Vampir());
	}
	
	/**
	 * Aquesta funcio demana un numero dins del rang per escollit opcio
	 * @param min
	 * @param max
	 * @return
	 */
	public static int demanaOpcio(int min, int max) {
		Scanner in = new Scanner(System.in);
		if (in.hasNextInt()) {
			int opcio = in.nextInt();
			if (opcio >= min && opcio <= max) {
				return opcio;
			} else {
				new Error(2);
				return -1;
			}
		} else {
			in.next();
			new Error(1);
			return -1;
		}
	}
	
	/**
	 * Aquesta funcio mostra el menu d'estadistiques
	 */
	private void menuEstadistiques() {
		Scanner in = new Scanner(System.in);
		boolean continua = true;
		while (continua) {
			System.out.println("\n\t1 - Historial de combat\n\t2 - Resultats de personatge\n\t3 - Tornar\n");
			System.out.print("\tAgafa una opcio del menu: ");
			int opcio = demanaOpcio(1, 3);
			switch(opcio) {
			case 1:
				System.out.println("\n\tHISTORIAL DE COMBATS");
				for (Combat b : Registre.getCombats()) {
					System.out
							.println("\n\tWINNER: " + b.getWinner().getNom() + " LOSER: " + b.getLoser().getNom() + " TORNS: " + b.getTorns());
				}
				break;
			case 2:
				Personatge buscar = Registre.getPersonatge();
				System.out.println("\n\tHISTORIAL DE COMBATS DE " + buscar.getClasse() + " " + buscar.getNom()
				+ "\n\tVICTORIES: " + buscar.getVictories() + " DERROTES: " + buscar.getDerrotes());
				break;
			case 3:
				continua = false;
				break;
			}
		}
		
	}

	/**
	 * Aquesta funcio mostra el menu de personatges
	 */
	private void menuPersonatges() {
		Scanner in = new Scanner(System.in);
		boolean continua = true;
		while (continua) {
			Registre.mostrarPersonatges();
			System.out.println(
					"\n\t1 - Veure personatge\n\t2 - Nou personatge\n\t3 - Importar personatge\n\t4 - Exportar personatge\n\t5 - Tornar\n");
			System.out.print("\tAgafa una opcio del menu: ");
			int opcio = demanaOpcio(1, 5);
			switch(opcio) {
			case 1:
				System.out.println(Registre.getPersonatge().toString());
				break;
			case 2:
				Registre.creaPersonatge();
				break;
			case 3:
				System.out.println(Registre.getPersonatge().toImport());
				break;
			case 4:
				System.out.print("\n\tPega les dades que vas copiar al importar: ");
				String linea = in.nextLine();
				System.out.println(Personatge.toExport(linea));
				break;
			case 5:
				continua = false;
				break;
			}
		}
		
	}
}

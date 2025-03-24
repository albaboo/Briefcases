package heroes_Of_Drakthoria;

import java.util.Scanner;

/**
 * Main del joc 
 * @author albadb
 */
public class Main {
	/**
	 * Funcio principal del joc
	 * @param args Los argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new Arma("DAGA", 5, 15);
		new Arma("ESPASA", 10, 10);
		new Arma("MARTELL", 15, 5);
		new Personatge("Baboo", "CAVALLER", Arma.getArma("ESPASA"), 14, 15, 6, 14, 11);
		new Personatge("Yonko", "GUERRER", Arma.getArma("MARTELL"), 17, 18, 8, 7, 10);
		new Personatge("Saylor", "VALQUIRIA", Arma.getArma("ESPASA"), 6, 14, 12, 12, 16);
		new Personatge("Kaito", "ASSESSI", Arma.getArma("DAGA"), 7, 9, 16, 16, 12);
		boolean jugar = true;
		while (jugar) {
			System.out.println(
					"\n\tMenu d'opcions\n\t1 - Personatges\n\t2 - Combatre\n\t3 - Estadistiques\n\t4 - Sortir\n");
			System.out.print("\tAgafa una opcio del menu: ");
			if (in.hasNextInt()) {
				int opcio = in.nextInt();
				if (opcio == 1) {
					menuPersonatges(in);
				} else if (opcio == 2) {
					in.nextLine();
					Personatge p1 = Personatge.getPersonatge(in);
					Personatge p2 = Personatge.getPersonatge(in);
					new Combat(p1, p2);
				} else if (opcio == 3) {
					menuEstadistiques(in);
				} else if (opcio == 4) {
					jugar = false;
				} else {
					new Error(2);
				}
			} else {
				in.next();
				new Error(1);
			}
		}
		System.out.println("\n\tHas sortit del joc, adeu!");
	}

	/**
	 * Aquesta funcio mostra el menu d'estadistiques
	 * @param in Scanner per demanar valors
	 */
	private static void menuEstadistiques(Scanner in) {
		boolean continua = true;
		while (continua) {
			System.out.println("\n\t1 - Historial de combat\n\t2 - Resultats de personatge\n\t3 - Tornar\n");
			System.out.print("\tAgafa una opcio del menu: ");
			if (in.hasNextInt()) {
				int opcio = in.nextInt();
				if (opcio == 1) {
					System.out.println("\n\tHISTORIAL DE COMBATS");
					for (Combat b : Combat.getBatalla()) {
						System.out
								.println("\n\tWINNER: " + b.getWinner().getNom() + " LOSER: " + b.getLoser().getNom());
					}
				} else if (opcio == 2) {
					in.nextLine();
					Personatge buscar = Personatge.getPersonatge(in);
					System.out.println("\n\tHISTORIAL DE COMBATS DE " + buscar.getClasse() + " " + buscar.getNom()
							+ "\n\tVICTORIES: " + buscar.getVictories() + " DERROTES: " + buscar.getDerrotes());
				} else if (opcio == 3) {
					continua = false;
				} else {
					new Error(2);
				}
			} else {
				in.next();
				new Error(1);
			}
		}

	}

	/**
	 * Aquesta funcio mostra el menu de personatges
	 * @param in Scanner per demanar valors
	 */
	private static void menuPersonatges(Scanner in) {
		boolean continua = true;
		while (continua) {
			Personatge.mostrarPersonatges();
			System.out.println(
					"\n\t1 - Veure personatge\n\t2 - Nou personatge\n\t3 - Importar personatge\n\t4 - Exportar personatge\n\t5 - Tornar\n");
			System.out.print("\tAgafa una opcio del menu: ");
			if (in.hasNextInt()) {
				int opcio = in.nextInt();
				if (opcio == 1) {
					in.nextLine();
					System.out.println(Personatge.getPersonatge(in).toString());
				} else if (opcio == 2) {
					in.nextLine();
					Personatge.nouPersonatge(in);
				} else if (opcio == 3) {
					in.nextLine();
					System.out.println(Personatge.getPersonatge(in).toImport());
				} else if (opcio == 4) {
					in.nextLine();
					System.out.print("\n\tPega les dades que vas copiar al importar: ");
					String linea = in.nextLine();
					System.out.println(Personatge.toExport(linea));
				} else if (opcio == 5) {
					continua = false;
				} else {
					new Error(2);
				}
			} else {
				in.next();
				new Error(1);
			}
		}
	}
}
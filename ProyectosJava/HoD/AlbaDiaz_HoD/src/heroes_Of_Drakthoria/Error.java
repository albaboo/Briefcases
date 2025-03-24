package heroes_Of_Drakthoria;

/**
 * Classe Error
 * @author albadb
 */
public class Error {
	/**
	 * La classe Error no te atributs
	 */

	/**
	 * Constructor d'un objecte Error amb parametre 
	 * <br>
	 * <br>
	 * Aquest constructor només imprimeix per consola el error detectat, no fa res més
	 * @param n Numeracio de l'error detectat
	 */
	public Error(int n) {
		if (n == 1) {
			System.out.println("\tERROR, posa un numero");
		} else if (n == 2) {
			System.out.println("\tERROR, posa un numero de les opcions");
		} else if (n == 3) {
			System.out.println("\tERROR, numero de punts no dins del rang [3-18]");
			;
		} else if (n == 4) {
			System.out.println("\tERROR, hi ha punts de massa sobrants, torna a comenzar");
		} else if (n == 5) {
			System.out.println("\tERROR, no has escollit un personatge del registre");
		} else if (n == 6) {
			System.out.println("\tERROR, nombre no introduit");
		} else if (n == 7) {
			System.out.println("\tERROR, personatge registrat, posa altre nom");
		} else if (n == 8) {
			System.out.println("\tERROR, nombre de classe no reconegut");
		} else if (n == 9) {
			System.out.println("\tERROR, nombre d'arma no reconegut");
		} else if (n == 10) {
			System.out.println("\tERROR, no queden punts suficients, torna a comenzar");
		} else if (n == 11) {
			System.out.println("\tERROR, algun valor o varis no son del tipus adequat");
		} else if (n == 12) {
			System.out.println("\tERROR, pega les dades que vas copiar al importar");
		} else if (n == 13) {
			System.out.println("\tERROR DESCONEGUT, algo estrany a portat a error del codi");
		}
	}
}
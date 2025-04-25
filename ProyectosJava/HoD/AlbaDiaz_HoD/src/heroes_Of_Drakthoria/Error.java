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
		switch(n) {
		case 1:
			System.out.println("\tERROR, posa un numero");
			break;
		case 2:
			System.out.println("\tERROR, posa un numero de les opcions");
			break;
		case 3:
			System.out.println("\tERROR, numero de punts no dins del rang [3-18]");
			break;
		case 4:
			System.out.println("\tERROR, hi ha punts de massa sobrants, torna a comenzar");
			break;
		case 5:
			System.out.println("\tERROR, no has escollit un personatge del registre");
			break;
		case 6:
			System.out.println("\tERROR, nombre no introduit");
			break;
		case 7:
			System.out.println("\tERROR, personatge registrat, posa altre nom");
			break;
		case 8:
			System.out.println("\tERROR, nombre de classe no reconegut");
			break;
		case 9:
			System.out.println("\tERROR, nombre d'objecte no reconegut");
			break;
		case 10:
			System.out.println("\tERROR, no queden punts suficients, torna a comenzar");
			break;
		case 11:
			System.out.println("\tERROR, algun valor o varis no son del tipus adequat");
			break;
		case 12:
			System.out.println("\tERROR, pega les dades que vas copiar al importar");
			break;
		case 13: 
			System.out.println("\tERROR, nombre de habilitat no reconegut");
			break;
		case 14:
			System.out.println("\tERROR DESCONEGUT, algo estrany a portat a error del codi");
			break;
		}
	}
}
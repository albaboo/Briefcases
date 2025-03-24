package heroes_Of_Drakthoria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Arma
 * @author albadb
 */
public class Arma {
	/**
	 * Atributs de la classe Arma
	 */
	private String nom;
	private int wpow;
	private int wvel;
	/**
	 * Llista de armes registrades
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Arma que es
	 * crean amb el constructor
	 */
	private static ArrayList<Arma> armes = new ArrayList<Arma>();

	/**
	 * Constructor d'un objecte Arma amb parametres 
	 * <br>
	 * <br>
	 * L'objecte Arma creat s'emmagatzema a l'arrayList armes
	 * @param nom  Nom de l'arma
	 * @param wpow Poder de l'arma
	 * @param wvel Velocitat de l'arma
	 */
	public Arma(String nom, int wpow, int wvel) {
		this.setNom(nom);
		this.setWpow(wpow);
		this.setWvel(wvel);
		armes.add(this);
	}

	/**
	 * Getter de Nom
	 * @return Retorna el nom de l'arma
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de Wpow
	 * @return Retorna el poder de l'arma
	 */
	public int getWpow() {
		return this.wpow;
	}

	/**
	 * Getter de Wvel
	 * @return Retorna la velocitat de l'arma
	 */
	public int getWvel() {
		return this.wvel;
	}

	/**
	 * Setter de Nom
	 * @param nom Valor pel qual es modifica l'atribut nom de l'arma
	 */
	private void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	/**
	 * Setter de Wpow
	 * @param wpow Valor pel qual es modifica l'atribut poder de l'arma
	 */
	private void setWpow(int wpow) {
		this.wpow = wpow;
	}

	/**
	 * Setter de Wvel
	 * @param wvel Valor pel qual es modifica l'atribut velocitat de l'arma
	 */
	private void setWvel(int wvel) {
		this.wvel = wvel;
	}

	/**
	 * Mostra el nom dels objecte Arma creades
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, 
	 * només imprimeix per consola els noms dels armes 
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a getArmes() per obtenir la llista dels objectes Arma i depres,
	 * imprimeix un per un el nom de l'arma
	 */
	public static void mostrarArmes() {
		System.out.println("\n\tArmes:");
		for (Arma a : Arma.getArmes()) {
			System.out.println("\t" + a.getNom());
		}
	}

	/**
	 * Getter de Armes 
	 * <br>
	 * <br>
	 * Retorna un ArrayList d'objectes Arma que s'ha creat amb el constructor
	 * @return armes ArrayList que conte els objectes Arma
	 */
	private static ArrayList<Arma> getArmes() {
		return armes;
	}

	/**
	 * Getter de objecte Arma en armes (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a getArmes() per obtenir la llista dels objectes Arma i depres, comprova si el
	 * nom d'aquesta arma coincideix amb el parametre nom
	 * @param nom El nom de l'arma el qual es vol obtenir
	 * @return Objecte Arma que coincideix el seu atribut nom amb el parametre nom,
	 *         en cas de no trobar l'objecte Arma es declara un nou error amb la
	 *         seva numeracio i no retorna cap objecte Arma
	 */
	public static Arma getArma(String nom) {
		for (Arma a : Arma.getArmes()) {
			if (a.getNom().equals(nom.toUpperCase())) {
				return a;
			}
		}
		new Error(9);
		return null;
	}

	/**
	 * Getter de objecte Arma en armes (sense parametre nom) 
	 * <br>
	 * <br>
	 * Primer mostra les armes disponibles trucant a mostrarArmes(), 
	 * despres demana amb el scanner rebut un nom d'arma i el bucle 
	 * truca a getArmes() per obtenir la llista dels objectes Arma, 
	 * comprova si el nom d'aquesta arma coincideix amb la variable nom
	 * @param in Scanner per demanar nom
	 * @return Objecte Arma que coincideix el seu atribut nom amb la variable nom,
	 *         si no es troba al final es declara un nou error amb la seva numeracio
	 *         d'error com parametre
	 */
	public static Arma getArma(Scanner in) {
		mostrarArmes();
		String nom = "";
		while (true) {
			System.out.print("\n\tEscull un arma del registre: ");
			nom = in.nextLine();
			for (Arma a : Arma.getArmes()) {
				if (a.getNom().equals(nom.toUpperCase())) {
					return a;
				}
			}
			new Error(9);
		}
	}
}
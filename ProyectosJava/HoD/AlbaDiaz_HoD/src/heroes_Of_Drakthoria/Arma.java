package heroes_Of_Drakthoria;

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
	 * Constructor d'un objecte Arma amb parametres 
	 * <br>
	 * <br>
	 * L'objecte Arma creat s'emmagatzema a l'arrayList armes
	 * @param nom  Nom de l'arma
	 * @param wpow Poder de l'arma
	 * @param wvel Velocitat de l'arma
	 */
	public Arma(String nom, int wpow, int wvel) {
		this.nom = nom.toUpperCase();
		this.wpow = wpow;
		this.wvel = wvel;
		Registre.addArma(this);
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

}
package heroes_Of_Drakthoria;

import java.util.Random;

/**
 * Classe Dau
 * @author albadb
 */
public class Dau {
	/**
	 * Atribut de la classe Dau
	 */
	private int cares;

	/**
	 * Constructor d'un objecte Dau amb parametre 
	 * <br>
	 * <br>
	 * El parametre valor s'assigna a l'atribut valor
	 * @param valor Valor maxim que pot tenir el dau (Es a dir, el numero de cares
	 *              que te el dau)
	 */
	public Dau(int valor) {
		this.cares = valor;
	}

	/**
	 * Llençar el dau 
	 * <br>
	 * <br>
	 * Simula un llançament de dau de tant cares com sigui el numero
	 * del valor de l'objecte Dau
	 * @return Retorna un valor aleatori entre 1 i el valor del objecte Dau
	 */
	public int llencament() {
		Random random = new Random();
		return random.nextInt(1, cares+1);
	}
}
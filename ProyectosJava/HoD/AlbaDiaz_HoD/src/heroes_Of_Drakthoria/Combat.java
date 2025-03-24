package heroes_Of_Drakthoria;

import java.util.ArrayList;

/**
 * Classe Combat
 * @author albadb
 */
public class Combat {
	/**
	 * Atributs de la classe Combat
	 */
	private Personatge p1;
	private Personatge p2;
	private Personatge winner;
	private Personatge loser;
	/**
	 * Llista dels 3 daus
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut on s'emmagazenen els objectes Dau que es crean
	 * amb el constructor
	 */
	private Dau[] daus = new Dau[3];
	/**
	 * Llista de combats
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Combat que
	 * es crean amb el constructor
	 */
	private static ArrayList<Combat> batalla = new ArrayList<Combat>();

	/**
	 * Constructor d'un objecte Combat amb parametres 
	 * <br>
	 * <br>
	 * Es crean 3 objectes Dau amb 25 cares 
	 * <br>
	 * <br>
	 * Es truca a la funcio atac() amb els 2 objecte Personatge seleccionats
	 * com parametres L'objecte Combat creat s'emmagatzema a l'arrayList batalla
	 * @param p1 Objecte Personatge, es el personatge que ha seleccionat el primer
	 *           jugador
	 * @param p2 Objecte Personatge, es el personatge que ha seleccionat el segon
	 *           jugador
	 */
	public Combat(Personatge p1, Personatge p2) {
		this.setP1(p1);
		this.setP2(p2);
		this.daus[0] = new Dau(25);
		this.daus[1] = new Dau(25);
		this.daus[2] = new Dau(25);
		atac(this.getP1(), this.getP2());
		batalla.add(this);
	}
	/**
	 * Getter de batalla
	 * @return Retorna una llista amb objectes Combat creats
	 */
	public static ArrayList<Combat> getBatalla() {
		return batalla;
	}

	/**
	 * Getter de P1
	 * @return Retorna el objecte Personatge que ha seleccionat el primer jugador
	 */
	public Personatge getP1() {
		return this.p1;
	}

	/**
	 * Setter de P1
	 * @param p1 Valor pel qual es modifica l'atribut p1 del combat
	 */
	private void setP1(Personatge p1) {
		this.p1 = p1;
	}

	/**
	 * Getter de P2
	 * @return Retorna el objecte Personatge que ha seleccionat el segon jugador
	 */
	public Personatge getP2() {
		return this.p2;
	}

	/**
	 * Setter de P2
	 * @param p2 Valor pel qual es modifica l'atribut p2 del combat
	 */
	private void setP2(Personatge p2) {
		this.p2 = p2;
	}

	/**
	 * Getter de Winner
	 * @return Retorna el objecte Personatge que ha guanyat aquest combat
	 */
	public Personatge getWinner() {
		return this.winner;
	}

	/**
	 * Setter de Winner
	 * @param winner Valor pel qual es modifica l'atribut winner del combat
	 */
	private void setWinner(Personatge winner) {
		this.winner = winner;
	}

	/**
	 * Getter de Loser
	 * @return Retorna el objecte Personatge que ha perdut aquest combat
	 */
	public Personatge getLoser() {
		return this.loser;
	}

	/**
	 * Setter de Loser
	 * @param loser Valor pel qual es modifica l'atribut loser del combat
	 */
	public void setLoser(Personatge loser) {
		this.loser = loser;
	}

	/**
	 * Torn del atacant, l'atacant intenta atacer al defensant 
	 * <br>
	 * <br>
	 * Es fan 3 llaçaments de daus, els valors que retornan es suma per determinar el valor d'atac 
	 * <br>
	 * <br>
	 * Si l'atac supera la probabilitat d'atac (Pa), l'atac falla i el defensant passa
	 * a ser atacant i viceversa Si l'atac acerta, es truca a la funcio defensa()
	 * amb els mateix parametres que ha rebut aquesta funcio atac() 
	 * <br>
	 * <br>
	 * Si el defensant perd tanta vida que els seu Ps es inferior o igual 
	 * a 0 acaba el combat i es truca a la funcio acaba() amb els mateix 
	 * parametres que ha rebut aquesta funcio atac()
	 * @param atacant   Objecte Personatge del jugador que te el torn d'atac
	 * @param defensant Objecte Personatge del jugador que te el torn de defensa
	 */
	public void atac(Personatge atacant, Personatge defensant) {
		System.out.println("\n\tTorn d'atac de " + atacant.getNom());
		int dau1 = daus[0].llencament(), dau2 = daus[1].llencament(), dau3 = daus[2].llencament();
		int atac = dau1 + dau2 + dau3;
		System.out
				.println("\n\t" + atacant.getNom() + " llença els 3 daus de 25: " + dau1 + ", " + dau2 + " i " + dau3);
		System.out.println("\tResultat: " + atac);
		if (atac <= atacant.getPa()) {
			System.out.println("\t" + atacant.getNom() + " ha acertat l'atac contra " + defensant.getNom());
			System.out.println("\t" + defensant.getNom() + " te que aconseguir evitar l'atac de " + defensant.getNom()
					+ " per a no perdre PS");
			defensa(atacant, defensant);
		} else {
			System.out.println("\t" + atacant.getNom() + " ha fallat l'atac contra " + defensant.getNom());
		}
		if (defensant.getPs() <= 0) {
			acaba(atacant, defensant);
		} else {
			atac(defensant, atacant);
		}
	}

	/**
	 * Torn del defensant, el defensant intenta defensar-se de l'atacant 
	 * <br>
	 * <br>
	 * Es fan 3 llaçaments de daus, els valors que retornan es suma per determinar el valor de la defensa 
	 * <br>
	 * <br>
	 * Si la defensa supera la probabilitat d'esquivo (Pe), la defensa falla i el defensant perd tants 
	 * punts de vida (Ps) com punts de dany (Pd) te l'atacant Si la defensa acerta, el defensant no rep 
	 * l'atac i no perd vida (Ps)
	 * @param atacant   Objecte Personatge del jugador que te el torn d'atac
	 * @param defensant Objecte Personatge del jugador que te el torn de defensa
	 */
	public void defensa(Personatge atacant, Personatge defensant) {
		System.out.println("\n\tTorn de defensa de " + defensant.getNom());
		int dau1 = daus[0].llencament(), dau2 = daus[1].llencament(), dau3 = daus[2].llencament();
		int defensa = dau1 + dau2 + dau3;
		System.out.println(
				"\n\t" + defensant.getNom() + " llença els 3 daus de 25: " + dau1 + ", " + dau2 + " i " + dau3);
		System.out.println("\tResultat: " + defensa);
		if (defensa <= defensant.getPe()) {
			System.out.println("\t" + defensant.getNom() + " ha esquivat l'atac de " + atacant.getNom());
			System.out.println("\t" + defensant.getNom() + " ha aconseguit no perdre PS aquest torn");
		} else {
			System.out.println("\t" + defensant.getNom() + " no ha esquivat l'atac de " + atacant.getNom());
			defensant.setPs(defensant.getPs() - atacant.getPd());
			System.out.println("\t" + defensant.getNom() + " perd " + atacant.getPd() + "PS\n\t" + defensant.getClasse()
					+ " " + defensant.getNom() + " te " + defensant.getPs() + "PS");

		}
	}

	/**
	 * Fi del combat 
	 * <br>
	 * <br>
	 * La vida dels personatges (Ps) es reseteja amb resetPs() 
	 * <br>
	 * <br>
	 * Els atributs de winner i loser del combat es modifiquen, 
	 * el defensant ha perdut (loser) i per tant, 
	 * l'atacant guanya el combat (winner) 
	 * <br>
	 * <br>
	 * L'atribut de Victories del personatge que ha guanyat es modifica amb setVictories()
	 * <br>
	 * <br>
	 * L'atribut de Derrotes del personatge que ha perdut es modifica amb setDerrotes() 
	 * <br>
	 * <br>
	 * L'atribut de Pex del personatge que ha guanyat es modifica amb pujarPex() passant 
	 * com parametre l'objecte Personatge del jugador que te el torn de defensa
	 * @param atacant   Objecte Personatge del jugador que te el torn d'atac
	 * @param defensant Objecte Personatge del jugador que te el torn de defensa
	 */
	public void acaba(Personatge atacant, Personatge defensant) {
		atacant.resetPs();
		defensant.resetPs();
		this.setWinner(atacant);
		this.setLoser(defensant);
		atacant.setVictories();
		defensant.setDerrotes();
		atacant.pujarPex(defensant);
	}
}
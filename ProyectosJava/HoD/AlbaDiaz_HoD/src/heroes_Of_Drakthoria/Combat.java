package heroes_Of_Drakthoria;

import java.util.Scanner;

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
	private int torns;

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
		this.p1 = p1;
		this.p2 = p2;
		Registre.addCombat(this);
		tornDeJugador(this.p1, this.p2);
		Registre.setCombat(this);
	}

	/**
	 * Getter de Winner
	 * @return Retorna el objecte Personatge que ha guanyat aquest combat
	 */
	public Personatge getWinner() {
		return this.winner;
	}

	/**
	 * Getter de Loser
	 * @return Retorna el objecte Personatge que ha perdut aquest combat
	 */
	public Personatge getLoser() {
		return this.loser;
	}
	
	/**
	 * Getter de Torns
	 * @return Retorna el torns que lleva el combat
	 */
	public int getTorns() {
		return torns;
	}

	private void tornDeJugador(Personatge atacant, Personatge defensant) {
		accio(atacant, defensant);
		if (comprovarVida(defensant)) {
			this.tornDeJugador(defensant, atacant);
		} else {
			this.acaba(atacant, defensant);
		}
	}
	public boolean comprovarVida(Personatge jugador) {
		if (jugador.getPs() > 0) {
			return true;
		}
		return false;
	}
	
	public void accio(Personatge atacant, Personatge defensant) {
		this.torns ++;
		System.out.println("\n\tTorn "+this.getTorns());
		boolean demanar = true;
		System.out.println("\tAtaca: "+atacant.getNom()+" Defensa: "+defensant.getNom());
		while (demanar) {
			System.out.println("\n\tMenu d'opcions");
			System.out.println("\t1 - Atacar:\t\t\tAtaca amb 3 daus a altre oponent\n\n\t2 - "+atacant.getH1().getNom()+": "+atacant.getH1().getDescripcio()+"\n\t3 - "+atacant.getH2().getNom()+": "+atacant.getH2().getDescripcio()+"\n\t4 - Veure les meves dades\n\n\t5 - Veure les dades de l'oponent");
			System.out.print("\n\t"+atacant.getNom()+" escull una accio: ");
			int opcio = Game.demanaOpcio(1, 5);
			switch (opcio) {
			case 1: atacant.atacar(defensant); demanar = false; break;
			case 2: 
				if (atacant.getPm() - atacant.getH1().getCostMana() > 0) {
					atacant.utilitzarHabilitat(1, defensant);					
					demanar = false; 
				} else {
					System.out.println("\tNo tens PM suficients per fer aquesta accio, escull altre");
				}
				break;
			case 3: 
				if (atacant.getPm() - atacant.getH2().getCostMana() > 0) {
					atacant.utilitzarHabilitat(2, defensant);
					demanar = false;
				} else {
					System.out.println("\tNo tens PM suficients per fer aquesta accio, escull altre"); 
				}
				break;
			case 4: System.out.println(atacant.toString()); break;
			case 5: System.out.println(defensant.toString()); break;
			}		
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
	private void acaba(Personatge winner, Personatge loser) {
		winner.resetSubstats();
		loser.resetSubstats();
		this.winner = winner;
		this.loser = loser;
		winner.setVictories(loser);
		loser.setDerrotes();
	}
}
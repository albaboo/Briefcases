package heroes_Of_Drakthoria;

public final class Valquiria extends Personatge {
	
	/**
	 * Constructor d'un objecte Valquiria amb parametres (stats en array)
	 * <br>
	 * <br>
	 * S'assignen els valors
	 * @param nom    Nom del personatge
	 * @param classe Classe del personatge
	 * @param arma   Arma del personatge
	 * @param stats  Valors de Forza, Constitucio, Velocitat, Inteligencia i Sort
	 */
	public Valquiria(String nom, Tipologia classe, Arma arma, Amulet amulet, Armadura armadura, int[] stats, Habilitat h1, Habilitat h2) {
		super(nom, classe, arma, amulet, armadura, stats, h1, h2);
		setVelocitat(getVelocitat()+2);
		setSort(getSort()+2);
		this.resetSubstats();
		Registre.addPersonatge(this);
	}
	/**
	 * Constructor per exportacio d'un objecte 
	 * <br>
	 * <br>
	 * Personatge amb parametres l'objecte Valquiria creat s'emmagatzema a l'arrayList personatges
	 * @param nom          Nom del personatge
	 * @param classe       Classe del personatge
	 * @param forza        Forza del personatge
	 * @param constitucio  Constitucio del personatge
	 * @param velocitat    Velocitat del personatge
	 * @param inteligencia Inteligencia del personatge
	 * @param sort         Sort del personatge
	 * @param arma         Arma del personatge
	 * @param ps           Ps són los punts de salut
	 * @param pd           Pd són los punts de dany
	 * @param pa           Pa és la probabilitat d'atac
	 * @param pe           Pe és la probabilitat d'esquivar
	 * @param pex          Pex són los punts d'experiencia
	 * @param nivell       Nivell és el nivell del personatge
	 * @param victories    Victories del personatge
	 * @param derrotes     Derrotes del personatge
	 */
	public Valquiria(String nom, String classe, Arma arma, Amulet amulet, Armadura armadura, int forza, int constitucio, int velocitat, int inteligencia,
			int sort, int ps, int pd, int pa, int pe, int pex, int nivell, int victories, int derrotes,  Habilitat h1, Habilitat h2) {
		super(nom, Tipologia.valueOf(classe), arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories,
				derrotes, h1, h2);
	}
	
}

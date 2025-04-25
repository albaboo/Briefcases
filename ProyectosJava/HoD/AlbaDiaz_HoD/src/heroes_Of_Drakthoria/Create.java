package heroes_Of_Drakthoria;

public class Create {
	/**
	 * Crea un personatge segons la seva classe amb parametres (stats en array)
	 * <br>
	 * <br>
	 * S'assignen els valors
	 * @param nom    Nom del personatge
	 * @param classe Classe del personatge
	 * @param arma   Arma del personatge
	 * @param stats  Valors de Forza, Constitucio, Velocitat, Inteligencia i Sort
	 */
	public static void newPlayer(String nom, Tipologia classe, Arma arma, Amulet amulet, Armadura armadura, int[] stats, Habilitat h1, Habilitat h2) {
		switch(classe.toString()) {
		case "GUERRER": new Guerrer(nom, classe, arma, amulet, armadura, stats, h1, h2); break;
		case "CAVALLER": new Cavaller(nom, classe, arma, amulet, armadura, stats, h1, h2); break;
		case "VALQUIRIA": new Valquiria(nom, classe, arma, amulet, armadura, stats, h1, h2); break;
		case "ASSASSI": new Assassi(nom, classe, arma, amulet, armadura, stats, h1, h2); break;
		}
	}
	
	/**
	 * Exporta un personatge segons la seva classe 
	 * <br>
	 * <br>
	 * Personatge amb parametres creat s'emmagatzema a l'arrayList personatges
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
	public static void newPlayer(String nom, String classe, Arma arma, Amulet amulet, Armadura armadura, int forza, int constitucio, int velocitat, int inteligencia,
			int sort, int ps, int pd, int pa, int pe, int pex, int nivell, int victories, int derrotes,  Habilitat h1, Habilitat h2) {
		switch(classe) {
		case "GUERRER": new Guerrer(nom, classe, arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories,
				derrotes, h1, h2); break;
		case "CAVALLER": new Cavaller(nom, classe, arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories,
				derrotes, h1, h2); break;
		case "VALQUIRIA": new Valquiria(nom, classe, arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories,
				derrotes, h1, h2); break;
		case "ASSASSI": new Assassi(nom, classe, arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories,
				derrotes, h1, h2); break;
		}
	}
	
}

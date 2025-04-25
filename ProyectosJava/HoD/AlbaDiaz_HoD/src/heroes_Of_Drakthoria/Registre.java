package heroes_Of_Drakthoria;

import java.util.ArrayList;
import java.util.Scanner;

public class Registre {
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE ARMES
	 * 
	 * ----------------------------------------
	 */
	
	/**
	 * Llista de armes registrades
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Arma que es
	 * crean amb el constructor
	 */
	private static ArrayList<Arma> armes = new ArrayList<Arma>();
	
	/**
	 * Afegir Arma a armes
	 * @param nova Arma nova que s'emmagatzena en armes
	 */
	public static void addArma(Arma nova) {
		armes.add(nova);
	}
	
	/**
	 * Mostra el nom dels objecte Arma creades
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, 
	 * només imprimeix per consola els noms dels armes 
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a getArmes() per obtenir la llista dels objectes Arma i despres,
	 * imprimeix un per un el nom de l'arma
	 */
	public static void mostrarArmes() {
		System.out.println("\n\tArmes:");
		int aux = 1;
		for (Arma a : armes) {
			System.out.println("\t" + aux + " - " + a.getNom());
			aux++;
		}
	}

	/**
	 * Getter de objecte Arma en armes (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a getArmes() per obtenir la llista dels objectes Arma i despres, comprova si el
	 * nom d'aquesta arma coincideix amb el parametre nom
	 * @param nom El nom de l'arma el qual es vol obtenir
	 * @return Objecte Arma que coincideix el seu atribut nom amb el parametre nom,
	 *         en cas de no trobar l'objecte Arma es declara un nou error amb la
	 *         seva numeracio i no retorna cap objecte Arma
	 */
	public static Arma getArma(String nom) {
		for (Arma a : armes) {
			if (a.getNom().toUpperCase().equals(nom.toUpperCase())) {
				return a;
			}
		}
		new Error(9);
		return null;
	}
	
	public static Arma demanaArma() {
		Scanner in = new Scanner(System.in);
		while(true) {
			mostrarArmes();
			System.out.print("\tPosa un numero per escollir un arma: ");
			return armes.get(Game.demanaOpcio(1, armes.size())-1);
			
		}
	}

	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE CLASSES
	 * 
	 * ----------------------------------------
	 */
	
	public static Tipologia demanaTipologia() {
		Scanner in = new Scanner(System.in);
		while(true) {
			mostrarClasses();
			System.out.print("\tPosa un numero per escollir una classe: ");
			int opcio = Game.demanaOpcio(1, 8);
			switch (opcio) {
			case 1: return Tipologia.GUERRER;
			case 2: return Tipologia.CAVALLER;
			case 3: return Tipologia.VALQUIRIA;
			case 4: return Tipologia.ASSASSI;
			}
		}
	}
	/**
	 * Mostra les classes registrades al enum Tipologia
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, només imprimeix per consola les classes
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a Tipologia.values() per obtenir 
	 * la llista de classes i despres, imprimeix un per un la classe
	 */
	public static void mostrarClasses() {
		System.out.println("\n\tClasses:\n");
		int aux = 1;
		for (Tipologia classe : Tipologia.values()) {
			System.out.println("\t" + aux + " - " + classe);
			aux ++;
		}
	}

	/**
	 * Comprabar nom de la classe 
	 * <br>
	 * <br>
	 * En un bucle truca a Personatges.Classes.values()
	 * per obtgenir la llista de classes, comproba amb si el nom d'alguna de les
	 * classes es igual al rebut i retorna un boolea amb la resposta
	 * @param classe Nom de la classe que es vol comprobar si existeix
	 * @return Retorna true o false depenen de si troba o no aquest nom de classe
	 */
	public static boolean comprobarClasse(String nom) {
		for (Tipologia classe : Tipologia.values()) {
			if (nom.toUpperCase().equals(classe.toString().toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE COMBAT
	 * 
	 * ----------------------------------------
	 */
	
	/**
	 * Llista de combats
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Combat que
	 * es crean amb el constructor
	 */
	private static ArrayList<Combat> combats = new ArrayList<Combat>();

	/**
	 * Getter de batalla
	 * @return Retorna una llista amb objectes Combat creats
	 */
	public static ArrayList<Combat> getCombats() {
		return combats;
	}
	
	/**
	 * Afegir Combat a combats
	 * @param nova Combat nova que s'emmagatzena en combats
	 */
	public static void addCombat(Combat nova) {
		combats.add(nova);
	}
	
	/**
	 * Editar ultim Combat en combats
	 * @param nova Combat nova que s'edita en combats
	 */
	public static void setCombat(Combat nova) {
		combats.set(combats.size()-1, nova);
	}
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE PERSONATGE
	 * 
	 * ----------------------------------------
	 */
	
	/**
	 * Llista de personatges registrats
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Personatge
	 * que es crean amb el constructor
	 */
	private static ArrayList<Personatge> personatges = new ArrayList<Personatge>();
	
	/**
	 * Llista de requisits dels nivells
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els Pex minims que es
	 * necessita per pujar de nivell al personatge
	 */
	private static int[] nivells = { 100, 200, 500, 1000, 2000 };
	
	public static int[] getNivells() {
		return nivells;
	}

	/**
	 * Afegir Personatge a personatges
	 * @param nova Personatge nou que s'emmagatzena en personatges
	 */
	public static void addPersonatge(Personatge nova) {
		personatges.add(nova);
	}
	
	/**
	 * Esborrar Personatge a personatges
	 * @param nova Personatge nou que s'emmagatzena en personatges
	 */
	public static void dropPersonatge(Personatge nova) {
		personatges.remove(nova);
	}
	
	/**
	 * Getter de objecte Personatge en personatges (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a personatges per obtenir la llista dels objectes Personatge i
	 * despres, comprova si el nom d'aquest personatge coincideix amb el parametre nom
	 * @param nom El nom del personatge el qual es vol obtenir
	 * @return Objecte Personatge que coincideix el seu atribut nom amb el parametre
	 *         nom, en cas de no trobar l'objecte Personatge es declara un nou error
	 *         amb la seva numeracio i no retorna cap objecte Personatge
	 */
	public static Personatge getPersonatge(String nom) {
		for (Personatge p : personatges) {
			if (p.getNom().toUpperCase().equals(nom.toUpperCase())) {
				return p;
			}
		}
		new Error(5);
		return null;
	}
	
	/**
	 * Getter de objecte Personatge en personatges (sense parametre nom) 
	 * <br>
	 * <br>
	 * Primer mostra els personatges disponibles trucant a mostrarPersonatges(), 
	 * despres demana amb el scanner rebut un nom de personatge i el bucle truca a
	 * personatges per obtenir la llista dels objectes Personatge, comprova si
	 * el nom d'aquesta personatge coincideix amb el de la varieble nom, si no es
	 * troba al final es declara un nou error amb la seva numeracio d'error com
	 * parametre
	 * @param in Scanner per demanar nom
	 * @return Objecte Personatge que coincideix el seu atribut nom amb la variable nom
	 */
	public static Personatge getPersonatge() {
		Scanner in = new Scanner(System.in);
		mostrarPersonatges();
		String nom = "";
		while (true) {
			System.out.print("\n\tEscull un personatge del registre: ");
			nom = in.nextLine();
			for (Personatge p : personatges) {
				if (p.getNom().toUpperCase().equals(nom.toUpperCase())) {
					return p;
				}
			}
			new Error(5);
		}
	}

	/**
	 * Mostra la classe i el nom dels objecte 
	 * <br>
	 * <br>
	 * Personatges creades amb el constructor que s'emmgatzenen a l'arrayList personatges 
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, només imprimeix per consola les classes i els
	 * noms dels personatges 
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a personatges per obtenir la llista dels objectes Personatge i despres, 
	 * imprimeix un per un la classe i el nom del personatge
	 */
	public static void mostrarPersonatges() {
		System.out.println("\n\tRegistre de personatges\n");
		for (Personatge p : personatges) {
			System.out.println("\t" + p.getClasse() + " \t" + p.getNom());
		}
	}
	
	/**
	 * Crea nou objecte Personatge 
	 * <br>
	 * <br>
	 * Truca a la funcio demanaNom() amb parametre Scanner per obtenir el nom, 
	 * truca a la funcio demanaClasse() amb parametre Scanner per obtenir 
	 * la classe i truca a la funcio getArma() amb parametre Scanner per obtenir el arma
	 * @param in Scanner per passar-lo com parametre a altres funcions
	 */
	public static void creaPersonatge() {
		String nom = demanaNom();
		Tipologia classe = demanaTipologia();
		Arma armaPersonatge = demanaArma();
		Amulet amuletPersonatge = demanaAmulet();
		Armadura armaduraPersonatge = demanaArmadura();
		int[] stats = montarStats();
		System.out.println("\n\n\t- Escull la primera habilitat");
		Habilitat h1 = demanaHabilitat();
		Habilitat h2 = null;
		do {
			System.out.println("\n\n\t- Escull la segona habilitat");
			h2 = demanaHabilitat();
		} while (h1.getNom().toUpperCase().equals(h2.getNom().toUpperCase()));
		Create.newPlayer(nom, classe, armaPersonatge, amuletPersonatge, armaduraPersonatge, stats, h1, h2);
	}

	/**
	 * Demanar nom de personatge 
	 * <br>
	 * <br>
	 * En un bucle demana el nom i truca a la funcio comprobarNom() amb el nom 
	 * com parametre per saber si ja esta assignat aquest nom, si el nom 
	 * ja esta registrat declarara un nou error amb la seva numeraio d'error 
	 * com parametre i demanara altre vegada un nom, si el nom no esta
	 * registrat retornara aquest nom
	 * @param in Scanner per demanar nom
	 * @return Retorna el nom escollit
	 */
	public static String demanaNom() {
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("\n\tEscriu el nom del personatge: ");
			String nom = in.nextLine();
			if (nom.trim().equals("")) {
				new Error(6);
			} else {
				if (comprobarNom(nom)) {
					new Error(7);
				} else {
					
					return nom;
				}
			}
		}
	}

	/**
	 * Comprobar nom del personatge 
	 * <br>
	 * <br>
	 * En un bucle truca a getPesonatges per obtgenir una llista de personatges, 
	 * comproba amb getter si el nom d'algun es igual al rebut i retorna un boolea 
	 * amb la resposta
	 * @param nom Nom de personatge que es vol comprobar si existeix
	 * @return Retorna true o false depenen de si troba o no aquest nom en algun
	 *         dels personatges
	 */
	public static boolean comprobarNom(String nom) {
		for (Personatge p : personatges) {
			if (p.getNom().toUpperCase().equals(nom.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Montar els Stats 
	 * <br>
	 * <br>
	 * Amb la llista stats i el bucle for demana cadascun dels
	 * stats seguint les intruccions que explica per consola Si al terminar no
	 * compleix els requisits es torna a trucar a la funcio de manera recursica fins
	 * que els compleixi Si no compleix els requisits declara un nou error amb
	 * la numeracio corresponent al error per a que s'expliqui per pantalla
	 * @param in Scanner que es fa servir per demanar els valors de puntuacio
	 * @return Retorna una llista dels stats un cop tots els valors compleixi el
	 *         requisits
	 */
	private static int[] montarStats() {
		Scanner in = new Scanner(System.in);
		int[] stats = new int[5];
		String[] stat = { "FORZA", "CONSTITUCIO", "VELOCITAT", "INTELIGENCIA", "SORT" };
		int punts = 60;
		System.out.println("\n\tTens " + punts
				+ " punts per repartir entre els 5 stats (forza, constitucio, velocitat, inteligencia, sort)\n\tCadascun dels stats ha de tenir com minim 3 punts i com maxim 18\n\tPosa 0 si vols tornar a reasignar punts des del principi");
		boolean continua = true;
		for (int i = 0; i < stats.length && continua; i++) {
			boolean demana = true;
			while (demana) {
				if (stats.length - 1 != i) {
					System.out.print("\n\tPunts per " + stat[i] + ": ");
					if (in.hasNextInt()) {
						int num = in.nextInt();
						if (num == 0) {
							continua = false;
							demana = false;
						} else if (num >= 3 && num <= 18) {
							stats[i] = num;
							punts -= num;
							System.out.println("\n\tTens " + punts + " punts per repertre");
							demana = false;
						} else {
							new Error(3);
						}
					} else {
						in.next();
						new Error(1);
					}
				} else {
					if (punts >= 3 && punts <= 18) {
						System.out.println(
								"\n\tEcull una opcio:\n\t0 - Tornar a reasignar punts des del principi\n\t1 - Donar "
										+ punts + " a " + stat[i]);
						System.out.print("\tOpcio: ");
						if (in.hasNextInt()) {
							int num = in.nextInt();
							if (num == 0) {
								continua = false;
								demana = false;
							} else if (num == 1 || num == punts) {
								stats[i] = punts;
								punts -= punts;
								System.out.println("\n\tTens " + punts + " punts per repertre");
								demana = false;
							} else {
								new Error(2);
							}
						} else {
							in.next();
							new Error(1);
						}
					} else {
						if (punts > 18) {
							new Error(4);
						} else {
							new Error(10);
						}
						continua = false;
						demana = false;
					}
				}
			}
		}
		if (!continua) {
			stats = montarStats();
		}
		
		return stats;
	}
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE HABILITATS
	 * 
	 * ----------------------------------------
	 */
	
	public static void mostrarHabilitats() {
		System.out.println("\n\n\tHabilitats:\n");
		System.out.println("\t1 - Cop Poderos:\t\tL’atac té un 150% de dany (Cost: 4 PM)\n");
		System.out.println("\t2 - Atac Rapid:\t\t\tPermet atacar dues vegades durant el mateix torn (Cost: 3 PM)\n");
		System.out.println("\t3 - Atac Furtiu:\t\tRedueix en un 20% la probabilitat de que el defensor pugui esquivar l’atac (Cost: 3 PM)\n");
		System.out.println("\t4 - Curacio:\t\t\tEl personatge no realitza cap atac en el torn, però restaura en un 25% els seus PS (Cost: 2 PM)\n");
		System.out.println("\t5 - Defensa Concentrada:\tDurant el torn el personatge no atacarà i es dedicarà a preparar la defensa del següent torn, durant el qual augmentarà un 30%\n\t\t\t\t\tla seva probabilitat d’esquivar. A més, si té èxit en l’esquiva, podrà llençar un contraatac dintre del mateix torn sense que\n\t\t\t\t\tl’oponent tingui l’oportunitat d’esquivar (Cost: 4 PM)\n");
		System.out.println("\t6 - Vampir:\t\t\tSi l’atac té èxit, els PS que perd l’oponent en aquest torn els recupera el personatge que ha fet servir l’habilitat (Cost: 4 PM)\n");
		System.out.println("\t7 - Atac Concentrat:\t\tMillora en un 30% la probabilitat d'acertar l’atac contra l'objectiu (Cost: 4 PM)\n");
		System.out.println("\t8 - Defensa Maxima:\t\tIgual que la Defensa Concentrada, pero a mes redueix en un 30% la probabilitat de que el atacant pugui acertar l’atac (Cost: 7 PM)\n");
	}
	public static Habilitat demanaHabilitat() {
		Scanner in = new Scanner(System.in);
		while(true) {
			mostrarHabilitats();
			System.out.print("\tPosa un numero per escollir una habilitat: ");
			int opcio = Game.demanaOpcio(1, 8);
			switch (opcio) {
			case 1: return new CopPoderos();
			case 2: return new AtacRapid();
			case 3: return new AtacFurtiu();
			case 4: return new Curacio();
			case 5: return new DefensaConcentrada();
			case 6: return new Vampir();
			case 7: return new AtacConcentrat();
			case 8: return new DefensaMaxima();
			}
		}
	}
	public static Habilitat getHabilitat(String nom) {
		switch (nom) {
		case "CopPoderos": return new CopPoderos();
		case "AtacRapid": return new AtacRapid();
		case "AtacFurtiu": return new AtacFurtiu();
		case "Curacio": return new Curacio();
		case "DefensaConcentrada": return new DefensaConcentrada();
		case "Vampir": return new Vampir();
		case "AtacConcentrat": return new AtacConcentrat();
		case "DefensaMaxima": return new DefensaMaxima();
		default: return null;
		}
	}
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE AMULETS
	 * 
	 * ----------------------------------------
	 */
	
	/**
	 * Llista de amulets registrades
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Amulet que es
	 * crean amb el constructor
	 */
	private static ArrayList<Amulet> amulets = new ArrayList<Amulet>();
	
	/**
	 * Afegir Amulet a amulets
	 * @param nova Amulet nova que s'emmagatzena en amulets
	 */
	public static void addAmulet(Amulet nova) {
		amulets.add(nova);
	}
	
	/**
	 * Mostra el nom dels objecte Amulet creades
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, 
	 * només imprimeix per consola els noms dels amulets
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a getAmulets() per obtenir la llista dels objectes Amulet i despres,
	 * imprimeix un per un el nom de l'amulet
	 */
	public static void mostrarAmulets() {
		System.out.println("\n\tAmulets:");
		int aux = 1;
		for (Amulet a : amulets) {
			System.out.println("\t" + aux + " - " + a.getNom()+": +"+a.getBonificacioForza()+" FORZA, +"+a.getBonificacioConstitucio()+" CONSTITUCIO, +"+a.getBonificacioVelocitat()+" VELOCITAT, +"+a.getBonificacioInteligencia()+" INTELIGENCIA, +"+a.getBonificacioSort()+" SORT");
			aux ++;
		}
	}

	/**
	 * Getter de objecte Amulet en amulets (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a getAmulets() per obtenir la llista dels objectes Amulet i despres, comprova si el
	 * nom d'aquesta amulet coincideix amb el parametre nom
	 * @param nom El nom de l'amulet el qual es vol obtenir
	 * @return Objecte Amulet que coincideix el seu atribut nom amb el parametre nom,
	 *         en cas de no trobar l'objecte Amulet es declara un nou error amb la
	 *         seva numeracio i no retorna cap objecte Amulet
	 */
	public static Amulet getAmulet(String nom) {
		for (Amulet a : amulets) {
			if (a.getNom().toUpperCase().equals(nom.toUpperCase())) {
				return a;
			}
		}
		new Error(9);
		return null;
	}
	
	public static Amulet demanaAmulet() {
		Scanner in = new Scanner(System.in);
		while(true) {
			mostrarAmulets();
			System.out.print("\tPosa un numero per escollir un amulet: ");
			return amulets.get(Game.demanaOpcio(1, amulets.size())-1);
			
		}
	}
	
	/**
	 * ----------------------------------------
	 * 
	 * 			REGISTRE DE ARMADURES
	 * 
	 * ----------------------------------------
	 */
	
	/**
	 * Llista de armadures registrades
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Armadura que es
	 * crean amb el constructor
	 */
	private static ArrayList<Armadura> armadures = new ArrayList<Armadura>();
	
	/**
	 * Afegir Armadura a armadures
	 * @param nova Armadura nova que s'emmagatzena en armadures
	 */
	public static void addArmadura(Armadura nova) {
		armadures.add(nova);
	}
	
	/**
	 * Mostra el nom dels objecte Armadura creades
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, 
	 * només imprimeix per consola els noms de les armadures
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a getArmadures() per obtenir la llista dels objectes Armadura i despres,
	 * imprimeix un per un el nom de l'armadura
	 */
	public static void mostrarArmadures() {
		System.out.println("\n\tArmadures:");
		int aux = 1;
		for (Armadura a : armadures) {
			if(a.getBonificacioForza() == 0 && a.getBonificacioConstitucio() == 0) {
				System.out.println("\t" + aux + " - " + a.getNom()+": +"+a.getBonificacioVelocitat()+" VELOCITAT, +"+a.getBonificacioInteligencia()+" INTELIGENCIA, +"+a.getBonificacioSort()+" SORT");
			} else {
				System.out.println("\t" + aux + " - " + a.getNom()+": +"+a.getBonificacioForza()+" FORZA, +"+a.getBonificacioConstitucio()+" CONSTITUCIO");
			}
			aux ++;
		}
	}

	/**
	 * Getter de objecte Armadura en armadures (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a getArmadures() per obtenir la llista dels objectes Armadura i despres, comprova si el
	 * nom d'aquesta armadura coincideix amb el parametre nom
	 * @param nom El nom de l'armadura el qual es vol obtenir
	 * @return Objecte Armadura que coincideix el seu atribut nom amb el parametre nom,
	 *         en cas de no trobar l'objecte Armadura es declara un nou error amb la
	 *         seva numeracio i no retorna cap objecte Armadura
	 */
	public static Armadura getArmadura(String nom) {
		for (Armadura a : armadures) {
			if (a.getNom().toUpperCase().equals(nom.toUpperCase())) {
				return a;
			}
		}
		new Error(9);
		return null;
	}
	
	public static Armadura demanaArmadura() {
		Scanner in = new Scanner(System.in);
		while(true) {
			mostrarArmadures();
			System.out.print("\tPosa un numero per escollir un arma: ");
			return armadures.get(Game.demanaOpcio(1, armadures.size())-1);
			
		}
	}
}
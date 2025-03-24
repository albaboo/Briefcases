package heroes_Of_Drakthoria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Personatge
 * @author albadb
 */
public class Personatge {
	/**
	 * Atributs de la classe Personatge
	 */
	private String nom;
	
	public enum Classes {
		GUERRER, CAVALLER, VALQUIRIA, ASSESSI
	}
	
	private Classes classe;
	private int forza;
	private int constitucio;
	private int velocitat;
	private int inteligencia;
	private int sort;
	private Arma arma;
	private int ps;
	private int pd;
	private int pa;
	private int pe;
	private int pex;
	private int nivell;
	private int victories = 0;
	private int derrotes = 0;
	/**
	 * Llista de requisits dels nivells
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els Pex minims que es
	 * necessita per pujar de nivell al personatge
	 */
	private static int[] nivells = { 100, 200, 500, 1000, 2000 };
	/**
	 * Llista de personatges registrats
	 * <br>
	 * <br>
	 * Aquesta llista es un atribut static on s'emmagazenen els objectes Personatge
	 * que es crean amb el constructor
	 */
	private static ArrayList<Personatge> personatges = new ArrayList<Personatge>();

	/**
	 * Constructor d'un objecte Personatge amb parametres (parametre Scanner no inclós) 
	 * <br>
	 * <br>
	 * Ps són los punts de salut Pd són los punts de dany Pa és la
	 * probabilitat d'atac Pe és la probabilitat d'esquivar Pex són los punts
	 * d'experiencia Nivell és el nivell del personatge L'objecte Personatge creat
	 * s'emmagatzema a l'arrayList personatges
	 * <br>
	 * @param nom          Nom del personatge
	 * @param classe       Classe del personatge
	 * @param arma         Arma del personatge
	 * @param forza        Forza del personatge
	 * @param constitucio  Constitucio del personatge
	 * @param velocitat    Velocitat del personatge
	 * @param inteligencia Inteligencia del personatge
	 * @param sort         Sort del personatge
	 */
	public Personatge(String nom, String classe, Arma arma, int forza, int constitucio, int velocitat, int inteligencia,
			int sort) {
		this.setNom(nom);
		this.setClasse(classe.toUpperCase());
		this.setArma(arma);
		this.setForza(forza);
		this.setConstitucio(constitucio);
		this.setVelocitat(velocitat);
		this.setInteligencia(inteligencia);
		this.setSort(sort);
		this.setPs(this.getConstitucio() + this.getForza());
		this.setPd((this.getForza() + this.getArma().getWpow()) / 4);
		this.setPa(this.getInteligencia() + this.getSort() + this.getArma().getWvel());
		this.setPe(this.getVelocitat() + this.getSort() + this.getInteligencia());
		this.setPex(0);
		this.setNivell(0);
		personatges.add(this);
	}

	/**
	 * Constructor d'un objecte Personatge amb parametres (parametre Scanner inclós)
	 * <br>
	 * <br>
	 * Els atributs Forza, Constitucio, Velocitat, Inteligencia i Sort es demanen i
	 * assignen amb setStats() amb el Scanner com parametre
	 * @param nom    Nom del personatge
	 * @param classe Classe del personatge
	 * @param arma   Arma del personatge
	 * @param in     Scanner que es passa com parametre per a setStats()
	 */
	public Personatge(String nom, String classe, Arma arma, Scanner in) {
		this.setNom(nom);
		this.setClasse(classe);
		this.setArma(arma);
		this.setStats(montarStats(in));
		this.setPs(this.getConstitucio() + this.getForza());
		this.setPd((this.getForza() + this.getArma().getWpow()) / 4);
		this.setPa(this.getInteligencia() + this.getSort() + this.getArma().getWvel());
		this.setPe(this.getVelocitat() + this.getSort() + this.getInteligencia());
		this.setPex(0);
		this.setNivell(0);
		personatges.add(this);
	}

	/**
	 * Getter de Nom
	 * @return Retorna el nom del personatge
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Setter de Nom
	 * @param nom Valor pel qual es modifica l'atribut nom del personatge
	 */
	private void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter de Classe
	 * @return Retorna la classe del personatge
	 */
	public Classes getClasse() {
		return this.classe;
	}

	/**
	 * Setter de Classe amb Classes
	 * @param classe Valor pel qual es modifica l'atribut classe del personatge
	 */
	private void setClasse(Classes classe) {
		this.classe = classe;
	}

	/**
	 * Setter de Classe amb String
	 * @param classe Valor pel qual es modifica l'atribut classe del personatge
	 */
	private void setClasse(String classe) {
		this.setClasse(Classes.valueOf(classe));
	}

	/**
	 * Getter de Forza
	 * @return Retorna la forza del personatge
	 */
	private int getForza() {
		return this.forza;
	}

	/**
	 * Setter de Forza
	 * @param forza Valor pel qual es modifica l'atribut forza del personatge
	 */
	private void setForza(int forza) {
		this.forza = forza;
	}

	/**
	 * Getter de Constitucio
	 * @return Retorna la constitucio del personatge
	 */
	private int getConstitucio() {
		return this.constitucio;
	}

	/**
	 * Setter de Constitucio
	 * @param constitucio Valor pel qual es modifica l'atribut constitucio del personatge
	 */
	private void setConstitucio(int constitucio) {
		this.constitucio = constitucio;
	}

	/**
	 * Getter de Velocitat
	 * @return Retorna la velocitat del personatge
	 */
	private int getVelocitat() {
		return this.velocitat;
	}

	/**
	 * Setter de Velocitat
	 * @param velocitat Valor pel qual es modifica l'atribut velocitat del personatge
	 */
	private void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}

	/**
	 * Getter de Inteligencia
	 * @return Retorna la inteligencia del personatge
	 */
	private int getInteligencia() {
		return this.inteligencia;
	}

	/**
	 * Setter de Inteligencia
	 * @param inteligencia Valor pel qual es modifica l'atribut inteligencia del personatge
	 */
	private void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/**
	 * Getter de Sort
	 * @return Retorna la sort del personatge
	 */
	private int getSort() {
		return this.sort;
	}

	/**
	 * Setter de Sort
	 * @param sort Valor pel qual es modifica l'atribut sort del personatge
	 */
	private void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * Getter de Arma
	 * @return Retorna l'arma del personatge
	 */
	public Arma getArma() {
		return this.arma;
	}

	/**
	 * Setter de Arma
	 * @param arma Valor pel qual es modifica l'atribut arma del personatge
	 */
	private void setArma(Arma arma) {
		this.arma = arma;
	}

	/**
	 * Getter de Ps
	 * @return Retorna el ps del personatge
	 */
	public int getPs() {
		return this.ps;
	}

	/**
	 * Setter de Ps
	 * @param ps Valor pel qual es modifica l'atribut ps del personatge
	 */
	public void setPs(int ps) {
		if (ps < 0) {
			this.ps = 0;
		} else {
			this.ps = ps;
		}
	}

	/**
	 * Resetter de Ps 
	 * <br>
	 * <br>
	 * ACLARIMENT: No rep parametres ni retorna res
	 * <br>
	 * <br>
	 * FUNCIONAMENT: Calcula el seu Ps sumant la seva
	 * constitucio i la seva forza
	 */
	public void resetPs() {
		this.setPs(this.constitucio + this.forza);
	}

	/**
	 * Getter de Pd
	 * @return Retorna el pd del personatge
	 */
	public int getPd() {
		return this.pd;
	}

	/**
	 * Setter de Pd
	 * @param pd Valor pel qual es modifica l'atribut pd del personatge
	 */
	public void setPd(int pd) {
		this.pd = pd;
	}

	/**
	 * Getter de Pa
	 * @return Retorna el pa del personatge
	 */
	public int getPa() {
		return this.pa;
	}

	/**
	 * Setter de Pa
	 * @param pa Valor pel qual es modifica l'atribut pa del personatge
	 */
	public void setPa(int pa) {
		this.pa = pa;
	}

	/**
	 * Getter de Pe
	 * @return Retorna el pe del personatge
	 */
	public int getPe() {
		return this.pe;
	}

	/**
	 * Setter de Pe
	 * @param pe Valor pel qual es modifica l'atribut pe del personatge
	 */
	public void setPe(int pe) {
		this.pe = pe;
	}

	/**
	 * Getter de Pex
	 * @return Retorna el pex del personatge
	 */
	public int getPex() {
		return this.pex;
	}

	/**
	 * Setter de Pex
	 * @param pex Valor pel qual es modifica l'atribut pex del personatge
	 */
	public void setPex(int pex) {
		this.pex = this.pex;
	}

	/**
	 * Getter de Nivell
	 * @return Retorna el nivell del personatge
	 */
	public int getNivell() {
		return this.nivell;
	}

	/**
	 * Getter de Victories
	 * @return Retorna les victories del personatge
	 */
	public int getVictories() {
		return this.victories;
	}

	/**
	 * Setter de Victories 
	 * <br>
	 * <br>
	 * ACLARIMENT: No rep parametres ni retorna res
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El valor de victories incrementa a 1
	 */
	public void setVictories() {
		this.victories++;
	}

	/**
	 * Setter de Victories
	 * @param n Valor pel qual es modifica l'atribut victories del personatge
	 */
	public void setVictories(int n) {
		this.victories = n;
	}

	/**
	 * Getter de Derrotes
	 * @return Retorna les derrotes del personatge
	 */
	public int getDerrotes() {
		return this.derrotes;
	}

	/**
	 * Setter de Derrotes 
	 * <br>
	 * <br>
	 * ACLARIMENT: No rep parametres ni retorna res
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El valor de derrotes incrementa a 1
	 */
	public void setDerrotes() {
		this.derrotes++;
	}

	/**
	 * Setter de Derrotes
	 * @param n Valor pel qual es modifica l'atribut derrotes del personatge
	 */
	public void setDerrotes(int n) {
		this.derrotes = n;
	}

	/**
	 * Setter de Nivell
	 * 
	 * 
	 * @param nivell Valor pel qual es modifica l'atribut nivell del personatge
	 */
	public void setNivell(int nivell) {
		this.nivell = nivell;
	}

	/**
	 * Setter de Stats
	 * 
	 * 
	 * @param stats Llista dels stats del personatge pel qual es modifiquen els
	 *              atributs de stats del personatge
	 */
	private void setStats(int[] stats) {
		this.setForza(stats[0]);
		this.setConstitucio(stats[1]);
		this.setVelocitat(stats[2]);
		this.setInteligencia(stats[3]);
		this.setSort(stats[4]);
	}

	/**
	 * Resetter de Substats 
	 * <br>
	 * <br>
	 * ACLARIMENT: No rep parametres ni retorna res
	 * <br>
	 * <br>
	 * FUNCIONAMENT: Els valors dels substats (Ps, Pd, Pa i Pe) es caculen en funcio del seu stats i les propietats de l'arma
	 */
	private void resetSubstats() {
		this.setPs(this.getConstitucio() + this.getForza());
		this.setPd((this.getForza() + this.getArma().getWpow()) / 4);
		this.setPa(this.getInteligencia() + this.getSort() + this.getArma().getWvel());
		this.setPe(this.getVelocitat() + this.getSort() + this.getInteligencia());
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
	private static int[] montarStats(Scanner in) {
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
			stats = montarStats(in);
		}
		return stats;
	}

	/**
	 * Getter de Personatges
	 * @return Retorna un ArrayList d'objectes Personatge que s'han creat amb el
	 *         constructor
	 */
	private static ArrayList<Personatge> getPersonatges() {
		return personatges;
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
	 * FUNCIONAMENT: El bucle truca a getPersonatges() per obtenir la llista dels objectes Personatge i depres, 
	 * imprimeix un per un la classe i el nom del personatge
	 */
	public static void mostrarPersonatges() {
		System.out.println("\n\tRegistre de personatges\n");
		for (Personatge p : Personatge.getPersonatges()) {
			System.out.println("\t" + p.getClasse() + " \t" + p.getNom());
		}
	}

	/**
	 * Canvia l'objecte Personatge a String
	 * @return Retorna (no tots) els atributs de l'objecte Personatge que s'obtenen
	 *         trucant als seus getters per a que retornin els valors
	 */
	public String toString() {
		return "\n\tDades de " + this.getNom() + "\n\n\t" + this.getClasse() + " \t" + this.getNom() + "\n\tARMA: \t"
				+ this.getArma().getNom() + "\n\tWPOW: \t" + this.getArma().getWpow() + "\tWVEL: \t"
				+ this.getArma().getWvel() + "\n\tFORZA: \t\t" + this.getForza() + "\n\tCONSTITUCIO: \t"
				+ this.getConstitucio() + "\n\tVELOCITAT: \t" + this.getVelocitat() + "\n\tINTELIGENCIA: \t"
				+ this.getInteligencia() + "\n\tSORT: \t\t" + this.getSort() + "\n\tPS: \t" + this.getPs() + "\tPD: \t"
				+ this.getPd() + "\n\tPA: \t" + this.getPa() + "\tPE: \t" + this.getPe() + "\n\tPEX: \t" + this.getPex()
				+ "\tNIV: \t" + this.getNivell();
	}

	/**
	 * Importar un objecte Personatge 
	 * <br>
	 * <br>
	 * Imprimeix per pantalla les intruccions necessaries i 
	 * retorna tots els atributs de l'objecte Personatge en format String
	 * @return Retorna tots els atributs de l'objecte Personatge que s'obtenen
	 *         trucant als seus getters per a que retornin els valors
	 */
	public String toImport() {
		System.out.println("\n\tCopia les dades del personatge per poder exportar despres:");
		return "\n\t" + this.getNom() + " " + this.getClasse() + " " + this.getForza() + " " + this.getConstitucio()
				+ " " + this.getVelocitat() + " " + this.getInteligencia() + " " + this.getSort() + " "
				+ this.getArma().getNom() + " " + this.getPs() + " " + this.getPd() + " " + this.getPa() + " "
				+ this.getPe() + " " + this.getPex() + " " + this.getNivell() + " " + this.getVictories() + " "
				+ this.getDerrotes();
	}

	/**
	 * Exportar un objecte Personatge 
	 * <br>
	 * <br>
	 * El parametre linea es separa per espais amb split(" ") i s'emmgatzema 
	 * en una llista i comprova que tots els valors d'aquesta siguin els adequats 
	 * <br>
	 * <br>
	 * Si els valors obtinguts son adequats es truca al constructor per crear un 
	 * nou objecte Personatge i retorna un missatge indicant que l'exportacio 
	 * s'ha fet amb exit Si els valors obtinguts no son adequats declara un nou error 
	 * amb un numero enter que indica la numeracio del error i retorna un missatge 
	 * indicant que l'exportacio no s'ha pogut fer
	 * @param linea Valor String que conte tots els atributs del nou objecte
	 *              Personatge
	 * @return Retorna un missatge indicant si l'exportacio s'ha pogut fer
	 */
	public static String toExport(String linea) {
		String[] valors = linea.split(" ");
		String nom, classe;
		int forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories, derrotes;
		Arma arma;
		if (linea.trim().equals("") || valors.length != 16) {
			new Error(12);
			return "\n\tEXPORTACIO HA FALLAT";
		} else if (!Personatge.comprobarClasse(valors[1])) {
			new Error(8);
			return "\n\tEXPORTACIO HA FALLAT";
		} else if (Arma.getArma(valors[7]) == null) {
			return "\n\tEXPORTACIO HA FALLAT";
		} else {
			nom = valors[0];
			classe = valors[1];
			arma = Arma.getArma(valors[7]);
			try {
				forza = Integer.parseInt(valors[2]);
				constitucio = Integer.parseInt(valors[3]);
				velocitat = Integer.parseInt(valors[4]);
				inteligencia = Integer.parseInt(valors[5]);
				sort = Integer.parseInt(valors[6]);
				ps = Integer.parseInt(valors[8]);
				pd = Integer.parseInt(valors[9]);
				pa = Integer.parseInt(valors[10]);
				pe = Integer.parseInt(valors[11]);
				pex = Integer.parseInt(valors[12]);
				nivell = Integer.parseInt(valors[13]);
				victories = Integer.parseInt(valors[14]);
				derrotes = Integer.parseInt(valors[15]);
			} catch (Exception e) {
				new Error(11);
				return "\n\tEXPORTACIO HA FALLAT";
			}
			try {
				if (Personatge.comprobarNom(nom)) {
					personatges.remove(Personatge.getPersonatge(nom));
				}
				new Personatge(nom, classe, forza, constitucio, velocitat, inteligencia, sort, arma, ps, pd, pa, pe,
						pex, nivell, victories, derrotes);
				return "\n\tEXPORTSCIO AMB EXIT";
			} catch (Exception a) {
				new Error(13);
				return "\n\tEXPORTACIO HA FALLAT INESPERADAMENT";
			}
		}
	}

	/**
	 * Constructor per exportacio d'un objecte 
	 * <br>
	 * <br>
	 * Personatge amb parametres l'objecte Personatge creat s'emmagatzema a l'arrayList personatges
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
	public Personatge(String nom, String classe, int forza, int constitucio, int velocitat, int inteligencia, int sort,
			Arma arma, int ps, int pd, int pa, int pe, int pex, int nivell, int victories, int derrotes) {
		this.setNom(nom);
		this.setClasse(classe);
		this.setForza(forza);
		this.setConstitucio(constitucio);
		this.setVelocitat(velocitat);
		this.setInteligencia(inteligencia);
		this.setSort(sort);
		this.setArma(arma);
		this.setPs(this.getConstitucio() + this.getForza());
		this.setPd((this.getForza() + this.getArma().getWpow()) / 4);
		this.setPa(this.getInteligencia() + this.getSort() + this.getArma().getWvel());
		this.setPe(this.getVelocitat() + this.getSort() + this.getInteligencia());
		this.setPex(pex);
		this.setNivell(nivell);
		this.setVictories(victories);
		this.setDerrotes(derrotes);
		personatges.add(this);
	}

	/**
	 * Getter de objecte Personatge en personatges (sense parametre nom) 
	 * <br>
	 * <br>
	 * Primer mostra els personatges disponibles trucant a mostrarPersonatges(), 
	 * despres demana amb el scanner rebut un nom de personatge i el bucle truca a
	 * getPersonatges() per obtenir la llista dels objectes Personatge, comprova si
	 * el nom d'aquesta personatge coincideix amb el de la varieble nom, si no es
	 * troba al final es declara un nou error amb la seva numeracio d'error com
	 * parametre
	 * @param in Scanner per demanar nom
	 * @return Objecte Personatge que coincideix el seu atribut nom amb la variable nom
	 */
	public static Personatge getPersonatge(Scanner in) {
		Personatge.mostrarPersonatges();
		String nom = "";
		while (true) {
			System.out.print("\n\tEscull un personatge del registre: ");
			nom = in.nextLine();
			for (Personatge p : Personatge.getPersonatges()) {
				if (p.getNom().toLowerCase().equals(nom.toLowerCase())) {
					return p;
				}
			}
			new Error(5);
		}
	}

	/**
	 * Getter de objecte Personatge en personatges (amb parametre nom) 
	 * <br>
	 * <br>
	 * El bucle truca a getPersonatges() per obtenir la llista dels objectes Personatge i
	 * depres, comprova si el nom d'aquest personatge coincideix amb el parametre nom
	 * @param nom El nom del personatge el qual es vol obtenir
	 * @return Objecte Personatge que coincideix el seu atribut nom amb el parametre
	 *         nom, en cas de no trobar l'objecte Personatge es declara un nou error
	 *         amb la seva numeracio i no retorna cap objecte Personatge
	 */
	public static Personatge getPersonatge(String nom) {
		for (Personatge p : Personatge.getPersonatges()) {
			if (p.getNom().toLowerCase().equals(nom.toLowerCase())) {
				return p;
			}
		}
		new Error(5);
		return null;
	}

	/**
	 * Nou objecte Personatge 
	 * <br>
	 * <br>
	 * Truca a la funcio demanaNom() amb parametre Scanner per obtenir el nom, 
	 * truca a la funcio demanaClasse() amb parametre Scanner per obtenir 
	 * la classe i truca a la funcio getArma() amb parametre Scanner per obtenir el arma
	 * @param in Scanner per passar-lo com parametre a altres funcions
	 */
	public static void nouPersonatge(Scanner in) {
		String nom = demanaNom(in);
		String classe = demanaClasse(in);
		Arma armaPersonatge = Arma.getArma(in);
		new Personatge(nom, classe, armaPersonatge, in);
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
	private static String demanaNom(Scanner in) {
		while (true) {
			System.out.print("\n\tEscriu el nom del personatge: ");
			String nom = in.nextLine();
			if (nom.trim().equals("")) {
				new Error(6);
			} else {
				if (Personatge.comprobarNom(nom)) {
					new Error(7);
				} else {
					return nom;
				}
			}
		}
	}

	/**
	 * Comprabar nom del personatge 
	 * <br>
	 * <br>
	 * En un bucle truca a getPesonatges per obtgenir una llista de personatges, 
	 * comproba amb getter si el nom d'algun es igual al rebut i retorna un boolea 
	 * amb la resposta
	 * @param nom Nom de personatge que es vol comprobar si existeix
	 * @return Retorna true o false depenen de si troba o no aquest nom en algun
	 *         dels personatges
	 */
	private static boolean comprobarNom(String nom) {
		for (Personatge p : Personatge.getPersonatges()) {
			if (p.getNom().toLowerCase().equals(nom.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Demanar classe del personatge 
	 * <br>
	 * <br>
	 * En un bucle mostra les classes amb mostrarClasses() i demana un nom d'arma 
	 * i truca a la funcio comprobarClasse() amb el nom de la classe com parametre 
	 * per saber si existeix, si la classe no existeix declarara un nou error 
	 * amb la seva numeraio d'error com parametre i demanara altre vegada un nom 
	 * de classe, si la classe existeix retorna el nom de la classe
	 * @param in Scanner per demanar classe
	 * @return Retorna el nom de la classe escollida
	 */
	private static String demanaClasse(Scanner in) {
		while (true) {
			mostrarClasses();
			System.out.print("\n\tEscriu el tipus de classe: ");
			String classe = in.nextLine().toUpperCase().trim();
			if (comprobarClasse(classe)) {
				return classe;
			} else {
				new Error(8);
			}
		}
	}

	/**
	 * Mostra les classes registrades al enum Classes 
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res, només imprimeix per consola les classes
	 * <br>
	 * <br>
	 * FUNCIONAMENT: El bucle truca a Personatges.Classes.values() per obtenir 
	 * la llista de classes i depres, imprimeix un per un la classe
	 */
	private static void mostrarClasses() {
		System.out.println("\n\tClasses:");
		for (Classes c : Personatge.Classes.values()) {
			System.out.println("\t" + c);
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
	private static boolean comprobarClasse(String classe) {
		for (Classes c : Personatge.Classes.values()) {
			if (classe.equals(c.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Pujar punts d'experiencia 
	 * <br>
	 * <br>
	 * Truca a setPex() per modificar els Pex i posa com parametre la suma 
	 * dels Pex actuals i els Ps de l'altre objecte Personatge obtinguts 
	 * amb el seus getters Comprova si ha aconseguit els Pex necessaris
	 * per pujar de nivell obtenin els Pex minims que deu arribar de la llista de
	 * nivells amb la posicio del nivell actual, si ha arribat puja de nivell i
	 * truca a la funcio pujarNivell()
	 * @param other Objecte Personatge de l'atre jugador que ha perdut el combat
	 *              contra aquest
	 */
	public void pujarPex(Personatge other) {
		this.setPex(this.getPex() + other.getPs());
		System.out.println("\n\t" + this.getNom() + " aconsegueix " + other.getPs() + " PEx");
		System.out.println("\t" + this.getNom() + " te " + this.getPex() + " PEx");
		if (Personatge.nivells[this.getNivell()] <= this.getPex()) {
			System.out.println("\n\t" + this.getNom() + " ha aconseguit PEx necessaris per pujar de nivell("
					+ Personatge.nivells[this.getNivell()] + " PEx minims) amb " + this.getPex() + " PEx");
			this.pujarNivell();
		}
	}

	/**
	 * Pujar nivell del personatge 
	 * <br>
	 * <br>
	 * ACLARACIO: Aquest metod static no te parametres ni retorna res 
	 * <br>
	 * <br>
	 * FUNCIONAMENT: Comprova que no esta al nivell maxim i puja el nivell 
	 * trucant a setNivell() i posant com parametre la suma del nivel actual
	 * obtingut amb getter mes 1, posa els Pex a 0 trucant a setPex() posant 0 com
	 * parametre, en una llista obte els valors dels stats amb getter i suma 1 a
	 * tots, posa aquesta llista com parametre per a trucar a setStats() i truca a
	 * resetSubstats() per actualitzar els substats
	 */
	public void pujarNivell() {
		if (this.getNivell() < Personatge.nivells.length) {
			this.setNivell(this.getNivell() + 1);
			this.setPex(0);
			int[] stats = { this.getForza() + 1, this.getConstitucio() + 1, this.getVelocitat() + 1,
					this.getInteligencia() + 1, this.getSort() + 1 };
			this.setStats(stats);
			this.resetSubstats();
		} else {
			System.out.println("\tLa pujada de nivell s'ha cancelat, nivell al maxim");
		}
	}
}
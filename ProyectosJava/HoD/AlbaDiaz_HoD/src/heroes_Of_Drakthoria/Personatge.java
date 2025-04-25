package heroes_Of_Drakthoria;

/**
 * Classe Personatge
 * @author albadb
 */
public abstract class Personatge{
	/**
	 * Atributs de la classe Personatge
	 */
	private String nom;	
	private Tipologia classe;
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
	private int pm;
	private Armadura armadura;
	private Amulet amulet;
	private Habilitat habilitat1;
	private Habilitat habilitat2;
	private int pex = 0;
	private int nivell = 0;
	private int victories = 0;
	private int derrotes = 0;
	
	/**
	 * Constructor d'un objecte Personatge amb parametres (stats en array)
	 * <br>
	 * <br>
	 * S'assignen els valors
	 * Ps són los punts de salut Pd són los punts de dany Pa és la
	 * probabilitat d'atac Pe és la probabilitat d'esquivar Pex són los punts
	 * d'experiencia Nivell és el nivell del personatge L'objecte Personatge creat
	 * s'emmagatzema a l'arrayList personatges
	 * @param nom    Nom del personatge
	 * @param classe Classe del personatge
	 * @param arma   Arma del personatge
	 * @param stats  Valors de Forza, Constitucio, Velocitat, Inteligencia i Sort
	 */
	public Personatge(String nom, Tipologia classe, Arma arma, Amulet amulet, Armadura armadura, int[] stats, Habilitat h1, Habilitat h2) {
		this.nom = nom;
		this.classe = classe;
		this.arma = arma;
		this.amulet = amulet;
		this.armadura = armadura;
		this.forza = stats[0]+amulet.getBonificacioForza()+armadura.getBonificacioForza();
		this.constitucio = stats[1]+amulet.getBonificacioConstitucio()+armadura.getBonificacioConstitucio();
		this.velocitat = stats[2]+amulet.getBonificacioVelocitat()+armadura.getBonificacioVelocitat();
		this.inteligencia = stats[3]+amulet.getBonificacioInteligencia()+armadura.getBonificacioInteligencia();
		this.sort = stats[4]+amulet.getBonificacioSort()+armadura.getBonificacioSort();
		this.pex = 0;
		this.nivell = 0;
		this.habilitat1 = h1;
		this.habilitat2 = h2;
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
	public Personatge(String nom, Tipologia classe, Arma arma, Amulet amulet, Armadura armadura, int forza, int constitucio, int velocitat, int inteligencia, int sort, int ps, int pd, int pa, int pe, int pex, int nivell, int victories, int derrotes, Habilitat h1, Habilitat h2) {
		this.nom = nom;
		this.classe = classe;
		this.forza = forza;
		this.constitucio = constitucio;
		this.velocitat = velocitat;
		this.inteligencia = inteligencia;
		this.sort = sort;
		this.arma = arma;
		this.amulet = amulet;
		this.armadura = armadura;
		this.resetSubstats();
		this.pex = pex;
		this.nivell = nivell;
		this.victories =  victories;
		this.derrotes = derrotes;
		this.habilitat1 = h1;
		this.habilitat2 = h2;
		Registre.addPersonatge(this);
	}
	
	/**
	 * Getter de Nom
	 * @return Retorna el nom del personatge
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de Classe
	 * @return Retorna la classe del personatge
	 */
	public Tipologia getClasse() {
		return this.classe;
	}
	
	/**
	 * Getter de Ps
	 * @return Retorna el ps del personatge
	 */
	public int getPs() {
		return this.ps;
	}
	
	public int getForza() {
		return forza;
	}

	public int getConstitucio() {
		return constitucio;
	}

	public int getVelocitat() {
		return velocitat;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public int getSort() {
		return sort;
	}
	
	/**
	 * Getter de Pd
	 * @return Retorna el pd del personatge
	 */
	public int getPd() {
		return this.pd;
	}

	/**
	 * Getter de Pa
	 * @return Retorna el pa del personatge
	 */
	public int getPa() {
		return this.pa;
	}

	/**
	 * Getter de Pe
	 * @return Retorna el pe del personatge
	 */
	public int getPe() {
		return this.pe;
	}
	
	/**
	 * Getter de Pm
	 * @return Retorna el pm del personatge
	 */
	public int getPm() {
		return pm;
	}
	
	/**
	 * Getter de Habilitat 1
	 * @return Retorna la Habilitat 1 del personatge
	 */
	public Habilitat getH1() {
		return habilitat1;
	}
	
	/**
	 * Getter de Habilitat 2
	 * @return Retorna el Habilitat 2 del personatge
	 */
	public Habilitat getH2() {
		return habilitat2;
	}

	/**
	 * Getter de Victories
	 * @return Retorna les victories del personatge
	 */
	public int getVictories() {
		return this.victories;
	}
	

	/**
	 * Getter de Derrotes
	 * @return Retorna les derrotes del personatge
	 */
	public int getDerrotes() {
		return this.derrotes;
	}

	public void setForza(int forza) {
		this.forza = forza;
	}

	public void setConstitucio(int constitucio) {
		this.constitucio = constitucio;
	}

	public void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * Setter de Ps
	 * <br>
	 * <br>
	 * Si els Ps son menors a 0, s'assigen 0 Ps en cop d'altre nombre mes petit
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
	 * Setter de Pd
	 * <br>
	 * <br>
	 * Si els Pd son menors a 0, s'assigen 0 Pd en cop d'altre nombre mes petit
	 * @param pe Valor pel qual es modifica l'atribut pd del personatge
	 */
	public void setPd(int pd) {
		if (pd < 0) {
			this.pd = 0;
		} else {
			this.pd = pd;
		}
	}
	
	/**
	 * Setter de Pa
	 * <br>
	 * <br>
	 * Si els Pa son menors a 0, s'assigen 0 Pa en cop d'altre nombre mes petit
	 * @param pe Valor pel qual es modifica l'atribut pa del personatge
	 */
	public void setPa(int pa) {
		if (pa < 0) {
			this.pa = 0;
		} else {
			this.pa = pa;
		}
	}
	
	/**
	 * Setter de Pe
	 * <br>
	 * <br>
	 * Si els Pe son menors a 0, s'assigen 0 Pe en cop d'altre nombre mes petit
	 * @param pe Valor pel qual es modifica l'atribut pe del personatge
	 */
	public void setPe(int pe) {
		if (pe < 0) {
			this.pe = 0;
		} else {
			this.pe = pe;
		}
	}
	
	/**
	 * Setter de victories
	 * <br>
	 * <br>
	 * Les victories del personatge incrmenta en un i depres puja els seus PEx
	 * i comprova si pot pujar de nivell
	 * @param other Personatge al que ha guanyat, es fan servir els seus Ps
	 * 		   		per incrementar els PEx d'aquest jugador
	 */
	public void setVictories(Personatge other) {
		this.victories++;
		this.pex = (this.pex+ other.ps);
		System.out.println("\n\t" + this.nom + " aconsegueix " + other.ps + " PEx");
		System.out.println("\t" + this.nom + " te " + this.pex + " PEx");
		if (Registre.getNivells()[this.nivell] <= this.pex) {
			System.out.println("\n\t" + this.nom+ " ha aconseguit PEx necessaris per pujar de nivell("
					+ Registre.getNivells()[this.nivell] + " PEx minims) amb " + this.pex + " PEx");
			if (this.nivell < Registre.getNivells().length) {
				this.nivell = (this.nivell + 1);
				this.pex = 0;
				this.forza = this.forza + 1;
				this.constitucio =  this.constitucio + 1;
				this.velocitat = this.velocitat + 1;
				this.inteligencia = this.inteligencia + 1;
				this.sort = this.sort + 1;
				this.resetSubstats();
			} else {
				System.out.println("\tLa pujada de nivell s'ha cancelat, nivell al maxim");
			}
		}
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
	 * Resetter de Substats 
	 * <br>
	 * <br>
	 * ACLARIMENT: No rep parametres ni retorna res
	 * <br>
	 * <br>
	 * FUNCIONAMENT: Els valors dels substats (Ps, Pd, Pa i Pe) es caculen en funcio del seu stats i les propietats de l'arma
	 */
	public void resetSubstats() {
		this.ps = (this.constitucio + this.forza);
		this.pd = ((this.forza + this.arma.getWpow()) / 4);
		this.pa = (this.inteligencia + this.sort + this.arma.getWvel());
		this.pe = (this.velocitat + this.sort + this.inteligencia);
		this.pm = (this.inteligencia * 2);
	}

	/**
	 * Canvia l'objecte Personatge a String
	 * @return Retorna (no tots) els atributs de l'objecte Personatge que s'obtenen
	 *         trucant als seus getters per a que retornin els valors
	 */
	@Override
	public String toString() {
		return "\n\tDades de " + this.nom+ "\n\n\t" + this.classe + " \t" + this.nom + "\n\tARMA: \t" 
				+ this.arma.getNom() + "\n\tAMULET: \t" + this.amulet.getNom() + "\n\tARMADURA: \t" 
				+ this.armadura.getNom() +  "\n\tWPOW: \t" + this.arma.getWpow() + "\tWVEL: \t"
				+ this.arma.getWvel() + "\n\tFORZA: \t\t" + this.forza + "\n\tCONSTITUCIO: \t"
				+ this.constitucio + "\n\tVELOCITAT: \t" + this.velocitat + "\n\tINTELIGENCIA: \t"
				+ this.inteligencia + "\n\tSORT: \t\t" + this.sort + "\n\tPS: \t" + this.ps + "\tPD: \t"
				+ this.pd + "\tPM: \t" + this.pm + "\n\tPA: \t" + this.pa + "\tPE: \t" + this.pe + "\n\tPEX: \t" + this.pex
				+ "\tNIV: \t" + this.nivell+"\n\tHabilitats:\n\t"+this.habilitat1.getNom()+":"+this.habilitat1.getDescripcio()+"\n\t"+this.habilitat2.getNom()+":"+this.habilitat2.getDescripcio();
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
		return "\n\t" + this.nom+ " " + this.classe + " " + this.forza + " " + this.constitucio
				+ " " + this.velocitat + " " + this.inteligencia + " " + this.sort + " "
				+ this.arma.getNom() + " " + this.amulet.getNom() + " " + this.armadura.getNom() + " " + this.ps + " " + this.pd + " " + this.pa + " "
				+ this.pe + " " + this.pex + " " + this.nivell + " " + this.victories + " "
				+ this.derrotes+" " + this.habilitat1.getNom() + " " + this.habilitat2.getNom();
	}

	/**
	 * Exportar un objecte Personatge 
	 * <br>
	 * <br>
	 * El parametre linia es separa per espais amb split(" ") i s'emmgatzema 
	 * en una llista i comprova que tots els valors d'aquesta siguin els adequats 
	 * <br>
	 * <br>
	 * Si els valors obtinguts son adequats es truca al constructor per crear un 
	 * nou objecte Personatge i retorna un missatge indicant que l'exportacio 
	 * s'ha fet amb exit Si els valors obtinguts no son adequats declara un nou error 
	 * amb un numero enter que indica la numeracio del error i retorna un missatge 
	 * indicant que l'exportacio no s'ha pogut fer
	 * @param linia Valor String que conte tots els atributs del nou objecte
	 *              Personatge
	 * @return Retorna un missatge indicant si l'exportacio s'ha pogut fer
	 */
	public static String toExport(String linia) {
		String[] valors = linia.split(" ");
		String nom, classe;
		int forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe, pex, nivell, victories, derrotes;
		Arma arma;
		Amulet amulet;
		Armadura armadura;
		Habilitat h1 = Registre.getHabilitat(valors[18]);
		Habilitat h2 = Registre.getHabilitat(valors[19]);
		if (linia.trim().equals("") || valors.length != 20) {
			new Error(12);
			return "\n\tEXPORTACIO HA FALLAT";
		} else if (!Registre.comprobarClasse(valors[1])) {
			new Error(8);
			return "\n\tEXPORTACIO HA FALLAT";
		} else if ((Registre.getArma(valors[7]) == null) || (Registre.getAmulet(valors[8]) == null) ||  (Registre.getArmadura(valors[9]) == null)) {
			new Error(9);
			return "\n\tEXPORTACIO HA FALLAT";
		} else if (h1 == null || h2 == null){
			new Error(13);
			return "\n\tEXPORTACIO HA FALLAT";
		} else {
			nom = valors[0];
			classe = valors[1];
			arma = Registre.getArma(valors[7]);
			amulet = Registre.getAmulet(valors[8]);
			armadura = Registre.getArmadura(valors[9]);
			try {
				forza = Integer.parseInt(valors[2]);
				constitucio = Integer.parseInt(valors[3]);
				velocitat = Integer.parseInt(valors[4]);
				inteligencia = Integer.parseInt(valors[5]);
				sort = Integer.parseInt(valors[6]);
				ps = Integer.parseInt(valors[10]);
				pd = Integer.parseInt(valors[11]);
				pa = Integer.parseInt(valors[12]);
				pe = Integer.parseInt(valors[13]);
				pex = Integer.parseInt(valors[14]);
				nivell = Integer.parseInt(valors[15]);
				victories = Integer.parseInt(valors[16]);
				derrotes = Integer.parseInt(valors[17]);
			} catch (Exception e) {
				new Error(11);
				return "\n\tEXPORTACIO HA FALLAT";
			}
			try {
				if (Registre.comprobarNom(nom)) {
					Registre.dropPersonatge(Registre.getPersonatge(nom));
				}
				Create.newPlayer(nom, classe, arma, amulet, armadura, forza, constitucio, velocitat, inteligencia, sort, ps, pd, pa, pe,
						pex, nivell, victories, derrotes, h1, h2);
				return "\n\tEXPORTSCIO AMB EXIT";
			} catch (Exception a) {
				new Error(14);
				return "\n\tEXPORTACIO HA FALLAT INESPERADAMENT";
			}
		}
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
	public void atacar (Personatge defensant) {
		Dau dau = new Dau(25);
		System.out.println("\n\t" + this.nom+" ataca a "+defensant.nom);
		int dau1 = dau.llencament(), dau2 = dau.llencament(), dau3 = dau.llencament();
		int atac = dau1 + dau2 + dau3;
		System.out.println("\n\t" + this.nom + " llença els 3 daus de 25: " + dau1 + ", " + dau2 + " i " + dau3);
		System.out.println("\tResultat: " + atac);
		if (atac <= this.pa) {
			System.out.println("\t" + this.nom + " ha acertat l'atac contra " + defensant.nom);
			System.out.println("\t" + defensant.nom + " te que aconseguir evitar l'atac de " + this.nom
					+ " per a no perdre PS");
			defensant.defensar(this);
		} else {
			System.out.println("\t" + this.nom + " ha fallat l'atac contra " + defensant.nom);
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
	public void defensar(Personatge atacant) {
		Dau dau = new Dau(25);
		System.out.println("\n\t" + this.nom+" es defensa de "+atacant.nom);
		int dau1 = dau.llencament(), dau2 = dau.llencament(), dau3 = dau.llencament();
		int defensa = dau1 + dau2 + dau3;
		System.out.println("\n\t" + this.nom + " llença els 3 daus de 25: " + dau1 + ", " + dau2 + " i " + dau3);
		System.out.println("\tResultat: " + defensa);
		if (defensa <= this.pe) {
			System.out.println("\t" + this.nom + " ha esquivat l'atac de " + atacant.nom);
			System.out.println("\t" + this.nom + " ha aconseguit no perdre PS aquest torn");
		} else {
			System.out.println("\t" + this.nom + " no ha esquivat l'atac de " + atacant.nom);
			this.setPs(this.ps - atacant.pd);
			System.out.println("\t" + this.nom + " perd " + atacant.pd + "PS\n\t" + this.classe
					+ " " + this.nom + " te " + this.ps + "PS");
		}
	}
	public void utilitzarHabilitat(int opcio, Personatge defensant) {
		boolean acertat;
		Habilitat accio = null;
		if (opcio == 1) {
			accio = this.habilitat1;
		} else {
			accio = this.habilitat2;
		}
		acertat = accio.utilitzar(this, defensant);
		if (acertat) {
			this.pm -= accio.getCostMana();
			System.out.println("\n\t"+this.nom+" gasta "+accio.getCostMana()+" per fer us de una habilitat amb exit");
			System.out.println("\tAra te "+this.pm+" PM");
			if ((accio.getNom().equals("DefensaConcentrada") || accio.getNom().equals("DefensaMaxima")) && Registre.getCombats().getLast().comprovarVida(this)) {
				Registre.getCombats().getLast().accio(this, defensant);				
			}
		}
	}
	
}
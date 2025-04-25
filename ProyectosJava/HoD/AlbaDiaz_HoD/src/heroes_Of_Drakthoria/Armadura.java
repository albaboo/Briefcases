package heroes_Of_Drakthoria;

public class Armadura {
	public String nom;
	private int forza = 0;
	private int constitucio = 0;
	private int velocitat = 0;
	private int inteligencia = 0;
	private int sort = 0;
	
	public Armadura(String nom, int forza, int constitucio) {
		this.nom = nom;
		this.forza = forza;
		this.constitucio = constitucio;
		Registre.addArmadura(this);
	}
	
	public Armadura(String nom, int velocitat, int inteligencia, int sort) {
		this.nom = nom;
		this.velocitat = velocitat;
		this.inteligencia = inteligencia;
		this.sort = sort;
		Registre.addArmadura(this);
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getBonificacioForza() {
		return forza;
	}

	public int getBonificacioConstitucio() {
		return constitucio;
	}

	public int getBonificacioVelocitat() {
		return velocitat;
	}

	public int getBonificacioInteligencia() {
		return inteligencia;
	}

	public int getBonificacioSort() {
		return sort;
	}	
	
}

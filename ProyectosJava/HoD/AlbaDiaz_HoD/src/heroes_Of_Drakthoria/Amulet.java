package heroes_Of_Drakthoria;

public class Amulet {
	private String nom;
	private int forza;
	private int constitucio;
	private int velocitat;
	private int inteligencia;
	private int sort;
	
	public Amulet(String nom, int forza, int constitucio, int velocitat, int inteligencia, int sort) {
		this.nom = nom;
		this.forza = forza;
		this.constitucio = constitucio;
		this.velocitat = velocitat;
		this.inteligencia = inteligencia;
		this.sort = sort;
		Registre.addAmulet(this);
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

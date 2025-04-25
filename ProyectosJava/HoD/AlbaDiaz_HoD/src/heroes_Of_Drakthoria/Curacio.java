package heroes_Of_Drakthoria;

public class Curacio implements Habilitat{

	@Override
	public String getNom() {
		return "Curacio";
	}

	@Override
	public String getDescripcio() {		
		return "\t\tEl personatge no realitza cap atac en el torn, però restaura en un 25% els seus PS (Cost: 2 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 2;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		System.out.println("\n\t" + usuari.getNom()+" s'esta curant...");
		usuari.setPs((int) (usuari.getPs()*1.25));
		System.out.println("\t" + usuari.getClasse()+ " " + usuari.getNom() + " te " + usuari.getPs() + "PS");
		return true;
	}

}
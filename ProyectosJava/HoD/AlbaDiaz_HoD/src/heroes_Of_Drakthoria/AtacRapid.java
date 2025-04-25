package heroes_Of_Drakthoria;

public class AtacRapid implements Habilitat{

	@Override
	public String getNom() {
		return "AtacRapid";
	}

	@Override
	public String getDescripcio() {
		return "\t\tPermet atacar dues vegades durant el mateix torn (Cost: 3 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 3;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		Dau dau = new Dau(25);
		int vida = objectiu.getPs();
		System.out.println("\n\t" + usuari.getNom()+" esta preparant un atac doble...");
		System.out.println("\t" + usuari.getNom()+" intenta el primer atac...");
		usuari.atacar(objectiu);
		System.out.println("\n\t" + usuari.getNom()+" intenta el segon atac...");
		usuari.atacar(objectiu);
		if (objectiu.getPs() != vida) {
			return true;
		} else {
			return false;
		}
	}

	

}
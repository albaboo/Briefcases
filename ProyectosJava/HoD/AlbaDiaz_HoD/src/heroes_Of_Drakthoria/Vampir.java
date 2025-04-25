package heroes_Of_Drakthoria;

public class Vampir implements Habilitat{

	@Override
	public String getNom() {
		return "Vampir";
	}

	@Override
	public String getDescripcio() {
		return "\t\tSi l’atac té èxit, els PS que perd l’oponent en aquest torn els recupera el personatge que ha fet servir l’habilitat (Cost: 4 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 4;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int vida = objectiu.getPs();
		usuari.atacar(objectiu);
		if (objectiu.getPs() != vida) {
			usuari.setPs(usuari.getPs()+(vida - objectiu.getPs()));
			System.out.println("\n\t"+usuari.getNom()+" esta robant vida a "+objectiu.getNom());
			System.out.println("\t" + usuari.getClasse()+ " " + usuari.getNom() + " te " + usuari.getPs() + "PS");
			return true;
		} else {
			System.out.println("\n\t"+usuari.getNom()+" no pot robar vida a "+objectiu.getNom());
			return false;
		}
	}

}
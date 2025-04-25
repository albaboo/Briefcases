package heroes_Of_Drakthoria;

public class AtacConcentrat implements Habilitat {

	@Override
	public String getNom() {
		return "AtacConcentrat";
	}

	@Override
	public String getDescripcio() {
		return "\tMillora en un 30% la probabilitat d'acertar l’atac contra l'objectiu (Cost: 4 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 4;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int vida = objectiu.getPs();
		int aux = usuari.getPa();
		System.out.println("\n\t" + usuari.getNom()+" esta aumentant les probabilitats d'acertar l'atac...");
		usuari.setPa((int) (usuari.getPa()*1.3));
		usuari.atacar(objectiu);
		objectiu.setPa(aux);
		if (objectiu.getPs() != vida) {
			return true;
		} else {
			return false;
		}
	}

}

package heroes_Of_Drakthoria;

public class AtacFurtiu implements Habilitat{

	@Override
	public String getNom() {
		return "AtacFurtiu";
	}

	@Override
	public String getDescripcio() {
		return "\t\tRedueix en un 20% la probabilitat de que el defensor pugui esquivar l’atac (Cost: 3 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 3;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int vida = objectiu.getPs();
		int aux = objectiu.getPe();
		System.out.println("\n\t" + usuari.getNom()+" esta baixant les probabilitats d'esquivar l'atac de "+objectiu.getNom()+"...");
		objectiu.setPe((int) (objectiu.getPe()*0.8));
		usuari.atacar(objectiu);
		objectiu.setPe(aux);
		if (objectiu.getPs() != vida) {
			return true;
		} else {
			return false;
		}	
		
	}

}
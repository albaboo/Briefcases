package heroes_Of_Drakthoria;

public class CopPoderos implements Habilitat {
	
	@Override
	public String getNom() {
		return "CopPoderos";
	}
	
	@Override
	public String getDescripcio() {
		return "\t\tL’atac té un 150% de dany (Cost: 4 PM)\n";
	}
	
	@Override
	public int getCostMana() {
		return 4;
	}
	
	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int vida = objectiu.getPs();
		int aux = usuari.getPd();
		System.out.println("\n\t" + usuari.getNom()+" esta preparant un cop poderos que pot fer molt dany a "+ objectiu.getNom()+"...");
		usuari.setPd((int) (usuari.getPd()*1.5));
		usuari.atacar(objectiu);
		usuari.setPd(aux);
		if (objectiu.getPs() != vida) {
			return true;
		} else {
			return false;
		}
	}
	
}
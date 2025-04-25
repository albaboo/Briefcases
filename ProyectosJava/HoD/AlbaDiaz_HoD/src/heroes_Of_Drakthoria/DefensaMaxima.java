package heroes_Of_Drakthoria;

public class DefensaMaxima implements Habilitat {
	
	Habilitat base = new DefensaConcentrada();
	
	@Override
	public String getNom() {
		return "DefensaMaxima";
	}

	@Override
	public String getDescripcio() {
		return "\tIgual que la Defensa Concentrada, pero a mes redueix en un 30% la probabilitat de que el atacant pugui acertar l’atac (Cost: 7 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 7;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int aux = objectiu.getPa();
		System.out.println("\n\t" + usuari.getNom()+" esta reduint les probabilitats d'acertar el proxim l'atac de "+objectiu.getNom()+"...");
		objectiu.setPa((int) (objectiu.getPa()*0.7));
		boolean util = base.utilitzar(usuari, objectiu);
		objectiu.setPa(aux);
		return util;
	}

}

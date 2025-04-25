package heroes_Of_Drakthoria;

public class DefensaConcentrada implements Habilitat{

	@Override
	public String getNom() {
		return "DefensaConcentrada";
	}

	@Override
	public String getDescripcio() {
		return "\tDurant el torn el personatge no atacarà i es dedicarà a preparar la defensa del següent torn, durant el qual augmentarà un 30%\n\t\t\t\tla seva probabilitat d’esquivar. A més, si té èxit en l’esquiva, podrà llençar un contraatac dintre del mateix torn sense que\n\t\t\t\tl’oponent tingui l’oportunitat d’esquivar (Cost: 4 PM)\n";
	}

	@Override
	public int getCostMana() {
		return 4;
	}

	@Override
	public boolean utilitzar(Personatge usuari, Personatge objectiu) {
		int aux = usuari.getPe();
		int vida = usuari.getPs();
		System.out.println("\n\t" + usuari.getNom()+" esta concentrant la defensa pel seguent torn...");
		usuari.setPe((int) (usuari.getPe()*1.3));
		Registre.getCombats().getLast().accio(objectiu, usuari);
		usuari.setPe(aux);
		if (usuari.getPs() == vida) {
			return true;
		} else {
			return false;
		}
				
	}
	
}
package heroes_Of_Drakthoria;

public interface Habilitat {
	String getNom();
	
	String getDescripcio();
    
	int getCostMana();
    
	boolean utilitzar(Personatge usuari, Personatge objectiu);

}
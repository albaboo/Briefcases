package gestormusica;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Classe que permet reproduir una cançó des del terminal de Windows fent servir la llibreria Javazoom
 * 
 */
public class Reproductor {

	private boolean stop = false;
	private Player player;
	private Canso canso;

	/**
	 * Constructor de la classe. Crea una instància del reproductor i la inicialitza
	 * amb el paràmetre canço. No inicia la reproducció immediatament, s'haurà de
	 * crida al mètode {@link #reproduir() reproduir}.
	 * @param canso la instància de Canço a reproduir
	 */
	public Reproductor(Canso canso) {
		this.canso = canso;
		try {
			player = new Player(new FileInputStream(canso.getArxiu()));
		} catch (FileNotFoundException ex) {
			Utils.mostraError("no s'ha trobat l'arxiu: " + canso.getArxiu());
		} catch (JavaLayerException ex) {
			Utils.mostraError("no s'ha pogut crear el reproductor");
		}	
	}

	/**
	 * Inicia la reproducció de la cançó proporcionada al constructor.
	 * La reproducció es pot aturar en qualsevol moment prement alguna tecla.
	 * Durant la reproducció es mostren les dades de la canço i un comptador del 
	 * temps de reproducció.
	 */
	public void reproduir() {
		new Thread() {
			public void run() {
				int segons, segonAnt = -1, lineaActual = 0;
				try {
					while (!stop) {
						segons = player.getPosition() / 1000;
						if (segons > segonAnt) {
							Utils.netejaPantalla();
							Utils.mostraMissatge("Reproduint: " + canso);
							if (canso.getArxiuLletra() != null) {
								if (new File(canso.getArxiuLletra()).exists() && lineaActual < canso.getLletra().size() && lineaActual < canso.getTempsLletra().size() && canso.getTempsLletra().get(lineaActual) <= segons * 1000) {
									Utils.mostraMissatge(canso.getLletra().get(lineaActual));
				                    lineaActual++;
								}
							}							
							String reproduccio = String.format("%02d:%02d%n", segons / 60, segons % 60);
							Utils.mostraMissatge(reproduccio);

							Utils.mostraMissatge("Prem qualsevol tecla per finalitzar la reproducció");
							segonAnt = segons;
						}
						player.play(1);
					}
				} catch (JavaLayerException e) {
					Utils.mostraError(e.getMessage());
				}
				player.close();
			}
		}.start();
		Utils.pausaFinsTecla();
		stop = true;
	}
}

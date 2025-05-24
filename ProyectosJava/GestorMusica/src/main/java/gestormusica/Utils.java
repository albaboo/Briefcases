package gestormusica;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Classe estática amb diversos métodes utils per la gestió de l'entrada/sortida des del terminal
 * 
 */


 @XmlRootElement(name = "utils")
 @XmlAccessorType(XmlAccessType.FIELD) 
public class Utils {
	
	/**
	 * Object Scanner estàtic
	 */
	private final static Scanner in = new Scanner(System.in);

	/**
	 * Mètode per netejar el terminal, compatible amb sistemes Windows i GNU/Linux
	 */
	public static void netejaPantalla() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				//Runtime.getRuntime().exec("clear");
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (IOException | InterruptedException ex) {
			System.out.println("Error en netejar pantalla: " + ex.getMessage());
		}
	}

	/**
	 * Mètode que espera a que l'usuari premi qualsevol tecla. Al contrari que els 
	 * mètodes tradicionals de lectura de teclat de Java, la tecla es rep de seguida, 
	 * sense haver d'esperar a que l'usuari premi ENTER
	 */
	public static void pausaFinsTecla() {
		try {
			Terminal terminal = TerminalBuilder.builder()
					.jna(true)
					.system(true)
					.build();

			// raw mode means we get keypresses rather than line buffered input
			terminal.enterRawMode();
			NonBlockingReader reader = terminal.reader();
			reader.read();
			reader.close();
			terminal.close();
		} catch (IOException ex) {
			System.out.println("Error d'entrada/sortida: " + ex.getMessage());
		}
	}

	private static void mostraTitol(String titol) {
		System.out.println("----------------------------------------------------");
		System.out.println(titol);
		System.out.println("----------------------------------------------------");
	}
	
	/**
	 * Mètode que mostra un missatge d'error i es queda esperant fins que l'usuari prem una tecla.
	 * @param msg el missatge d'error a mostrar
	 */
	public static void mostraError(String msg) {
		System.err.println("ERROR: " + msg);
		System.out.println("Prem qualsevol tecla per continuar ...");
		pausaFinsTecla();
		netejaPantalla();
	}
	
	public static void mostraMissatge(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Mostra la llista d'opcions rebuda i demana que l'usuari en trii una. Realitza el tractament d'errors
	 * i no retorna fins que l'usuari tria una opció correcta.
	 * @param llista llistat d'opcions a mostrar
	 * @return l'índex de l'opció triada dintre de la llista, començant per 0 i fins a llista.size()-1
	 */
	public static int demanaOpcioLlista(String titol, List<String> llista) {
		int opcio = 0;
		int min = 1, max = llista.size();
		
		if(llista != null) {
			mostraTitol(titol);
			while(opcio < min || opcio > max) {
				for(int i = 0; i < llista.size(); i++) {
					System.out.printf("%d - %s%n", i+1, llista.get(i));
				}
				System.out.printf("Insereix un nombre enter entre %d i %d: ", min, max);
				try {
					opcio = Integer.parseInt(in.nextLine());
					if (opcio < min || opcio > max) {
						mostraError("el nombre no es troba dintre del rang permés [" + min + " - " + max + "]");
					}
				} catch (NumberFormatException ex) {
					mostraError("no és un nombre enter");
				}
			}
		}
		return opcio-1;
	}
	
	/**
	 * Demana un text a l'usuari
	 * @param msg el missatge a mostrar
	 * @return el text inserit per l'usuari
	 */
	public static String demanaText(String msg) {
		System.out.print(msg + ": ");
		return in.nextLine();
	}
	
	/**
	 * Demana un nombre enter dintre del rang indicat. Si l'usuari insereix un valor
	 * incorrecte, mostra un missatge d'error i torna a demanar el nombre fins que 
	 * sigui correcte
	 * @param in l'objecte Scanner d'on llegir les dades
	 * @param min el valor mínim permés
	 * @param max el valor màxim permés
	 * @return el valor inserit per l'usuari
	 */
	public static int llegeixEnterRang(int min, int max) {
		boolean correcte = false;
		int nombre = 0;
		while (!correcte) {
			System.out.printf("Insereix un nombre enter entre %d i %d: ", min, max);
			try {
				nombre = Integer.parseInt(in.nextLine());
				if (nombre < min || nombre > max) {
					mostraError("el nombre no es troba dintre del rang permés [" + min + " - " + max + "]");
				} else {
					correcte = true;
				}
			} catch (NumberFormatException ex) {
				mostraError("no és un nombre enter");
			}
		}
		return nombre;
	}

	/**
	 * Elimina de la part final de la cadena str tots els caràcters amb valor
	 * zero (0x00)
	 * @param str la cadena a netejar
	 * @return la cadena netejada
	 */
	static String retallaZeros(String str) {
		int pos = str.indexOf(0);
		return pos == -1 ? str : str.substring(0, pos);
	}

	/**
	 * Afegeix a la part dreta de la cadena caràcters amb valor zero (0x00) fins
	 * completar la logitud indicada per length
	 * @param str la cadena a completar
	 * @param length la longitud total que ha de tenir la cadena de retorn
	 * @return la cadena amb caràcters zero afegits fins a completar la longitud indicada per length
	 */
	static String afegeixZeros(String str, int length){
		StringBuffer resultat = new StringBuffer(str);
		for (int i = str.length(); i < length; i++) {
			resultat.append((char)0);
		}
		return resultat.toString();
	}
}

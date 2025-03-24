package stardewValley;
/**
 * Comprobador de ruta
 * @author albadb
 */
import java.util.HashMap;
import java.util.Scanner;
/**
 * Classe Main
 */
public class Main {
	/** 
	 * Metodo main
	 * <br>
	 * <br>
	 * Declarar Scanner para pedir datos por consola
	 * <br>
	 * Scanner in = new Scanner(System.in);
	 * <br>
	 * <br>
	 * Declara variable restaE que restara su valor a la energia del jugador
	 * <br>
	 * int restaE = 0;
	 * <br>
	 * <br>
	 * Declara variable restaT que restara su valor a al tiempo que le queda al jugador
	 * <br>
	 * int restaT = 0;
	 * <br>
	 * <br>
	 * Declara variable minutoActual para saber en que minuto del dia esta el jugador dentro del juego
	 * <br>
	 * float minutoActual = 0;
	 * <br>
	 * <br>
	 * Declara variable mX que gestiona los posibles movimientos en x del jugador
	 * <br>
	 * int[] mX = {-1, 1, 0, 0};
	 * <br>
	 * <br>
	 * Declara variable mY que gestiona los posibles movimientos en y del jugador
	 * <br>
	 * int[] mY = {0, 0, -1, 1};
	 * <br>
	 * <br>
	 * Declara variable jugadorX y jugadorY para saber la posicion del jugador del jugador
	 * int jugadorX = -1, jugadorY = -1;
	 * <br>
	 * Declara variable mensaje para guardar el mensaje final que mostrara por consola, por defecto es LINUS ...
	 * <br>
	 * String mensaje = "LINUS: Te he dejado en casa, pero creo que alguien te ha robado";
	 * <br>
	 * <br>
	 * Pide por consola el numero de filas
	 * <br>
	 * int f = in.nextInt();
	 * <br>
	 * <br>
	 * Pide por consola el numero de columnas
	 * <br>
	 * int c = in.nextInt();
	 * <br>
	 * <br>
	 * Declara variable mapa con una matrix de F x C
	 * <br>
	 * char[][] mapa = new char[f][c];
	 * <br>
	 * <br>
	 * Bucle para pedir F lineas con C caracteres
	 * <br>
	 * for (int i = 0; i < f; i++) {
	 * <br>
	 *		String linea = in.nextLine();
	 * <br>
	 *		for (int j = 0; j < c; j++) {
	 * <br>
	 * <br>
	 * Guarda la casilla en el mapa en la posicion I x J, donde I: numero de fila y J: numero de columna
	 * 			mapa[i][j] = linea.charAt(j);
	 * <br>
	 * <br>
	 * Comprueba si en la casilla esta el jugador
	 * 			if (mapa[i][j] == 'J') {
	 * <br>
	 * <br>
	 * Si esta guarda su posicion X: numero de fila y su posicion Y: numero de columna
	 * 				jugadorX = i;
	 * <br>
	 * 				jugadorY = j;
	 * <br>
	 * <br>
	 * 			}
	 * <br>
	 * 		}
	 * <br>
	 * 	}
	 * <br>
	 * <br>
	 * Pide por consola los segundos reales restantes que quedan hasta las 2am dentro del juego y lo convierte en tiempo de juego dividiendo entre 0.7
	 * float tiempoJ = (float) (in.nextFloat()/0.7);
	 * <br>
	 * int tiempo = (int) tiempoJ;
	 * <br>
	 * <br>
	 * Pide por consola la energia actual del jugador
	 * int energia = in.nextInt();
	 * <br>
	 * <br>
	 * in.nextLine();
	 * <br>
	 * <br>
	 * Pide por consola los movimientos que hace el jugador en su ruta para llegar a la granja (T, D, L o R)
	 * String movimientos = in.nextLine();
	 * <br>
	 * <br>
	 * Posibles movimientos del jugador
	 * HashMap<Character, Integer> direcciones = new HashMap<>();
	 * <br>
	 * direcciones.put('T', 0); Top: Jugador se mueve hacia arriba
	 * <br>
	 * direcciones.put('D', 1); Down: Jugador se mueve hacia abajo
	 * <br>
	 * direcciones.put('L', 2); Left: JUgador se mueve hacia su izquierda
	 * <br>
	 * direcciones.put('R', 3); Right: Jugador se mueve hacia su derecha
	 * <br>
	 * <br>
	 * Comprobar cada casilla por la que pasa el jugador extrayendo cada movimiento de la variable String movimientos que se convierte en array con .toCharArray()
	 * for (char movimiento : movimientos.toCharArray()) {
	 * <br>
	 * <br>
	 * Extrae el numero de la posicion del movimiento en las listas mX y mY con el hashMap direcciones que guarda estos valores
	 * 		int direccion = direcciones.get(movimiento);
	 * <br>
	 * <br>
	 * Guarda la nueva posicion del jugador en dos variables auxiliares nuevoX y nuevoY
	 * 		int nuevoX = jugadorX + mX[direccion];
	 * <br>
	 * 		int nuevoY = jugadorY + mY[direccion];
	 * <br>
	 * <br>
	 * El tiempo se reduce en 1 minuto de juego por cada movimiento hacia cualquier direccion
	 * 		tiempo --;
	 * <br>
	 * <br>
	 * Comprueba que tenga tiempo, sino es asi saldra del bucle
	 * 		if (tiempo <= 0) {
	 * <br>
	 * 			break;
	 * <br>
	 * 		}
	 * <br>
	 * <br>
	 * Comprueba que la nueva posicion este dentro del mapa
	 * 		if (nuevoX >= 0 && nuevoX < f && nuevoY >= 0 && nuevoY < c) {
	 * <br>
	 * <br>
	 * Si esta dentro del mapa comprueba que en esta casilla
	 * 			char celda = mapa[nuevoX][nuevoY];
	 * <br>
	 * <br>
	 * Comprueba si hay un obstaculo
	 * 			if (celda == '#') {
	 * <br>
	 * <br>
	 * Si es asi la energia que le restara y el tiempo de juego que le restara se incrementara en uno
	 * 				restaE ++;
	 * <br>
	 * 				restaT ++;
	 * <br>
	 * <br>
	 * Resta al jugador la energia y el tiempo de juego y la celda se queda vacia para que pueda pasar
	 * 				energia -= restaE;
	 * <br>
	 * 				tiempo -= restaT - 1;
	 * <br>
	 * 				celda = '*';
	 * <br>
	 * <br>
	 * Si no habia obstaculo, comprueba si ha llegado a la granja la granja
	 * 			} else if (celda == 'G') {
	 * <br>
	 * <br>
	 * Calcula que hora es, si el tiempo es menor a 120 es que llego pasadas las 12 de la noche
	 * 				if (tiempo < 120) {
	 * <br>
	 * 					minutoActual = 120 - tiempo;
	 * <br>
	 * <br>
	 * Sino es que llego a alguna hora del dia anterior
	 * 				} else {
	 * <br>
	 * 					minutoActual = 1440 - tiempo + 120;
	 * <br>
	 * 				}
	 * <br>
	 * <br>
	 * Combierte los minutos del dia en la hora actual
	 * 				int hora = (int) (minutoActual / 60);
	 * <br>
	 * <br>
	 * Los minutos deben ir de 10 en 10, asi que se redondean
	 * 				int minutos = (int) ((minutoActual % 60)/10) * 10;
	 * <br>
	 * <br>
	 * Si es en punto se ajusta el mensaje para que muestre 00 en minutos
	 * 				if (minutos == 0) {
	 * <br>
	 * 					mensaje = "Has llegado a casa a las " + hora + ":00";
	 * <br>
	 * 				} else {
	 * <br>
	 * 					mensaje = "Has llegado a casa a las " + hora + ":" + minutos;
	 * <br>
	 * 				}
	 * <br>
	 * <br>
	 * Sale del bucle
	 * 				break;
	 * <br>
	 * 				}
	 * <br>
	 * <br>
	 * Si no es la granja continuara el bucle y actualizara la posicion del jugador
	 * 				jugadorX = nuevoX;
	 * <br>
	 * 				jugadorY = nuevoY;
	 * <br>
	 * 			}
	 * <br>
	 * <br>
	 * Comprueba si tiene energia, sino tiene cambiara el mensaje prederterminado a HARVEY ... y saldra del bucle
	 * 		if (energia <= 0) {
	 * <br>
	 * 			mensaje = "HARVEY: Intenta descansar mas, te he cobrado 1000o por los servicios de la clinica";
	 * <br>
	 * 			break;
	 * <br>
	 * 		}
	 * <br>
	 * }
	 * <br>
	 * <br>
	 * Imprime por consola el mensaje final
	 * System.out.println(mensaje);
	 * <br>
	 * <br>
	 * Cierra el scanner
	 * in.close();
	 * @param args Los argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int restaE = 0;
		int restaT = 0;
		float minutoActual = 0;
		int[] mX = {-1, 1, 0, 0};
		int[] mY = {0, 0, -1, 1};
		int jugadorX = -1, jugadorY = -1;
		String mensaje = "LINUS: Te he dejado en casa, pero creo que alguien te ha robado";
		int f = in.nextInt();
		int c = in.nextInt();
		char[][] mapa = new char[f][c];
		in.nextLine();
		for (int i = 0; i < f; i++) {
			String linea = in.nextLine();
			for (int j = 0; j < c; j++) {
				mapa[i][j] = linea.charAt(j);
				if (mapa[i][j] == 'J') {
					jugadorX = i;
					jugadorY = j;
				}
			}
		}
		float tiempoJ = (float) (in.nextFloat()/0.7);
		int tiempo = (int) tiempoJ;
		int energia = in.nextInt();
		in.nextLine();
		String movimientos = in.nextLine();
		HashMap<Character, Integer> direcciones = new HashMap<>();
		direcciones.put('T', 0);
		direcciones.put('D', 1);
		direcciones.put('L', 2);
		direcciones.put('R', 3);
		for (char movimiento : movimientos.toCharArray()) {
			int direccion = direcciones.get(movimiento);
			int nuevoX = jugadorX + mX[direccion];
			int nuevoY = jugadorY + mY[direccion];
			tiempo --;
			if (tiempo <= 0) {
				break;
			}
			if (nuevoX >= 0 && nuevoX < f && nuevoY >= 0 && nuevoY < c) {
				char celda = mapa[nuevoX][nuevoY];
				if (celda == '#') {
					restaE ++;
					restaT ++;
					energia -= restaE;
					tiempo -= restaT - 1;
					celda = '*';
				} else if (celda == 'G') {
					if (tiempo < 120) {
						minutoActual = 120 - tiempo;
					} else {
						minutoActual = 1440 - tiempo + 120;
					}
					int hora = (int) (minutoActual / 60);
					int minutos = (int) ((minutoActual % 60)/10) * 10;
					if (minutos == 0) {
						mensaje = "Has llegado a casa a las " + hora + ":00";
					} else {
						mensaje = "Has llegado a casa a las " + hora + ":" + minutos;
					}
					break;
				}
				jugadorX = nuevoX;
				jugadorY = nuevoY;
			}
			if (energia <= 0) {
				mensaje = "HARVEY: Intenta descansar mas, te he cobrado 1000o por los servicios de la clinica";
				break;
			}
		}
		System.out.println(mensaje);
		in.close();
    }
}
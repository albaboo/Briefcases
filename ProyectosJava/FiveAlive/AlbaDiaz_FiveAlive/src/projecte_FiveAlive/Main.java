package projecte_FiveAlive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void mostrarMenu(boolean configurat) {
		System.out.println("1 - Configuracion");
		if (configurat) {
			System.out.println("2 - Jugar\n3 - Salir");
		} else {
			System.out.println("2 - Salir");
		}
	}
	public static int validar(boolean configurat, Scanner in) {
		int opcio = -1;
		boolean opciono = true;
		while (opciono) {
			System.out.print("Escriu opcio: ");
			if (in.hasNextInt()) {
				opcio = in.nextInt();
				if (configurat) {
					if (opcio >= 1 && opcio <=3) {
						opciono = false;
					} else {
						System.out.println("ERROR, POSA UN NUMERO DEL MENU");
						mostrarMenu(configurat);
					}
				} else {
					if (opcio >= 1 && opcio <=2) {
						opciono = false;
					} else {
						System.out.println("ERROR, POSA UN NUMERO DEL MENU");
						mostrarMenu(configurat);
					}
				}
			} else {
				System.out.println("ERROR, POSA UN NUMERO DEL MENU");
				mostrarMenu(configurat);
				in.next();
			}
		}
		System.out.println();
		return opcio;
	}
	public static boolean configurar(String[] jugadors, Scanner in) {
		in.nextLine();
		System.out.print("Jugador 1, escriu el teu nom: ");
		jugadors[0] = in.nextLine();
		for (String jugador : jugadors) {
			System.out.println(jugador);
		}
		System.out.println();
		return true;
	}
	public static void jugar(ArrayList<Integer> baralla, ArrayList<Integer> barallaOriginal, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts, Scanner in) {
		for(int i = 0; i < 4; i ++) {
			vides[i] = 5;
		}
		iniciar(baralla, barallaOriginal, jugadors, manos, vides, punts);
		System.out.println();
		boolean winner = false;
		String guanyador = "";
		while (!winner) {
			for (int i = 0; i<4 && !winner; i ++) {
				if (vides[i] > 0) {
					System.out.println("Torn de "+jugadors[i]);
					System.out.println("Vides: "+vides[i]+ " Punts: "+punts[i]);
					mostrarMano(jugadors[i], manos.get(i));
					System.out.println();
					if (manos.get(i).size() > 0) {
						if (i == 0) {
							tirar(baralla, jugadors, manos, vides, punts, i, in);						
						} else {
							canvis(baralla, jugadors, manos, vides, punts, manos.get(i).get(0), i);
						}
						accions(baralla, barallaOriginal, jugadors, manos, vides, punts, i);
						int morts = 0;
						for (int j = 0; j<4; j ++) {
							if (vides[j] <= 0) {
								morts ++;
							}
						}
						if (morts == 3) {
							winner = true;
						}
					} else {
						System.out.println(jugadors[i]+" no te cartes a la ma, per tant, es passa el seu torn");
					}
					
				} else {
					System.out.println(jugadors[i]+" ha mort");
				}
			}
		}
		for (int j = 0; j<4; j ++) {
			if (vides[j] > 0) {
				guanyador = jugadors[j];
			}
		}
		System.out.println("Guanyador és "+guanyador);
	}
	public static void accions(ArrayList<Integer> baralla, ArrayList<Integer> barallaOriginal, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts, int p) {
		if (punts[p] > 21) {
			vides[p] --;
			System.out.println(jugadors[p]+" ha perdut una vida");
			for(int i = 0; i < 4; i++) {
				System.out.println(jugadors[i]+" te "+vides[i]+" vides");
			}
			punts[p] = 0;
		} else if (manos.get(p).size() == 0){
			ferit(jugadors, vides, p);
			iniciar(baralla, barallaOriginal, jugadors, manos, vides, punts);
		}
		System.out.println();
	}
	public static void ferit(String[] jugadors, int[] vides, int p) {
		for(int i = 0; i < 4; i ++) {
			if (i != p && vides[i] > 0) {
				vides[i] --;
				System.out.println(jugadors[i]+" ha perdut una vida");
			}
		}
		for(int i = 0; i < 4; i++) {
			System.out.println(jugadors[i]+" te "+vides[i]+" vides");
		}
	}
	public static void tirar(ArrayList<Integer> baralla, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts, int p, Scanner in) {
		boolean opciono = true;
		int opcio = -1;
		while (opciono) {
			System.out.print("Escriu el numero de la carta per tirar: ");
			if (in.hasNextInt()) {
				opcio = in.nextInt();
				if(manos.get(p).contains(opcio)) {
					canvis(baralla, jugadors, manos, vides, punts, opcio, p);
					opciono = false;
				} else {
					System.out.println("ERROR, POSA UN NUMERO DE LA TEVA MANO DE CARTES");
				}
			} else {
				System.out.println("ERROR, POSA UN NUMERO DE LA TEVA MANO DE CARTES");
				in.next();
			}
		}
	}
	public static void canvis(ArrayList<Integer> baralla, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts, int opcio, int p) {
		if (opcio == 10) {
			punts[p] = 10;
			System.out.println("Estableix puntuacio a 10");
		} else if (opcio == 21) {
			punts[p] = 21;
			System.out.println("Estableix puntuacio a 21");
		} else if (opcio == 11) {
			System.out.println("La resta de jugadors roba 1 carta");
			robar(baralla, jugadors, manos, vides, punts, p, 1);
		} else if (opcio == 12) {
			System.out.println("La resta de jugadors roba 2 carta");
			robar(baralla, jugadors, manos, vides, punts, p, 2);
		} else {
			punts[p] += opcio;
			System.out.println(jugadors[p]+" sumes "+opcio+" punts\nPuntuacio total de "+jugadors[p]+": "+punts[p]);
		}					
		manos.get(p).remove(manos.get(p).indexOf(opcio));
		baralla.add(opcio);
		System.out.println();
	}
	public static void robar(ArrayList<Integer> baralla, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts, int p, int robo) {
		for(int i = 0; i < 4; i ++) {
			if (i != p && vides[i] > 0) {
				System.out.println(jugadors[i]+" roba "+robo+ " de la baralla");
				for(int j = 0; j < robo; j ++) {
					ArrayList<Integer> mano = manos.get(i);
					mano.add(baralla.get(0));
					baralla.remove(0);
				}
			}
		}
	}
	public static void iniciar(ArrayList<Integer> baralla, ArrayList<Integer> barallaOriginal, String[] jugadors, ArrayList<ArrayList<Integer>> manos, int[] vides, int[] punts) {
		baralla.clear();
		for (int i = 0; i< barallaOriginal.size(); i++) {
			baralla.add(barallaOriginal.get(i));
		}
		Collections.shuffle(baralla);
		manos.clear();
		for(int i = 0; i < 4; i ++) {
			if (vides[i] > 0) {
				punts[i] = 0;
				ArrayList<Integer> mano = new ArrayList<Integer>();
				for(int j = 0; j < 10; j ++) {
					mano.add(baralla.get(0));
					baralla.remove(0);
				}
				mostrarMano(jugadors[i], mano);
				manos.add(mano);
			}			
		}		
	}
	public static void mostrarMano(String jugador, ArrayList<Integer> mano) {
		System.out.println("Cartes de "+jugador+":");
		for (int j = 0; j < mano.size()-1; j ++) {
			System.out.print(mano.get(j)+", ");
		}
		System.out.print(mano.get(mano.size()-1));
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> barallaOriginal = new ArrayList<Integer> (Arrays.asList(
				0,1,2,3,4,5,6,7,10,21,11,12,
				0,1,2,3,4,5,6,7,10,21,11,12,
				0,1,2,3,4,5,6,7,10,21,11,12,
				0,1,2,3,4,5,6,7,10,21,11,12,
				0,1,2,3,5,5,6,7,10,21,11,12,
				0,1,2,3,4,5,6,7,10,21,11,12));
		ArrayList<Integer> baralla = new ArrayList<Integer> ();
		ArrayList<ArrayList<Integer>> manos = new ArrayList<ArrayList<Integer>>();
		boolean jugar = true, configurat = false;
		int opcio = -1;
		String[] jugadors = {"J1", "J2", "J3", "J4"};
		int[] vides = {5, 5, 5, 5};
		int[] punts = {0, 0, 0, 0};
		while(jugar) {
			mostrarMenu(configurat);
			opcio = validar(configurat, in);
			if(opcio == 1) {
				configurat = configurar(jugadors, in);
			} else if (configurat && opcio == 2) {
				jugar(baralla, barallaOriginal, jugadors, manos, vides, punts, in);
			} else {
				jugar = false;
			}
		}
		System.out.println("Has sortit! :(");
		in.close();
	}
}
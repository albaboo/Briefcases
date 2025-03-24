package heroes_Of_Drakthoria;

import junit.framework.TestCase;

public class Test_Personatge_HOD extends TestCase {
	
	Arma a = new Arma("DAGA", 5, 15);
	
	Arma b = new Arma("ESPASA", 10, 10);
	
	Arma c = new Arma("MARTELL", 15, 5);
	
	Personatge p1 = new Personatge("Baboo", "CAVALLER", b, 14, 15, 6, 14, 11);
	
	Personatge p2 = new Personatge("Yonko", "GUERRER", c, 17, 18, 8, 7, 10);
	
	Personatge p3 = new Personatge("Saylor", "VALQUIRIA", b, 6, 14, 12, 12, 16);
	
	Personatge p4 = new Personatge("Kaito", "ASSESSI", a, 7, 9, 16, 16, 12);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPersonatge1() {
		assertSame(Personatge.class, p1.getClass());
		assertSame(Personatge.class, p2.getClass());
		assertSame(Personatge.class, p3.getClass());
		assertSame(Personatge.class, p4.getClass());
	}

	public void testGetNom() {
		assertEquals("Baboo", p1.getNom());
		assertEquals("Yonko", p2.getNom());
		assertEquals("Saylor", p3.getNom());
		assertEquals("Kaito", p4.getNom());
	}

	public void testGetArma() {
		assertEquals(b, p1.getArma());
		assertEquals(c, p2.getArma());
		assertEquals(b, p3.getArma());
		assertEquals(a, p4.getArma());
	}

	public void testGetPs() {
		assertEquals(14+15, p1.getPs());
		assertEquals(17+18, p2.getPs());
		assertEquals(6+14, p3.getPs());
		assertEquals(7+9, p4.getPs());
	}

	public void testGetPd() {
		assertEquals((14+10)/4, p1.getPd());
	}

	public void testGetPa() {
		assertEquals(14+11+10, p1.getPa());
	}

	public void testGetPe() {
		assertEquals(14+11+6, p1.getPe());
	}

	public void testGetPex() {
		assertEquals(0, p1.getPex());
		assertEquals(0, p2.getPex());
		assertEquals(0, p3.getPex());
		assertEquals(0, p4.getPex());
	}

	public void testGetNivell() {
		assertEquals(0, p1.getNivell());
		assertEquals(0, p2.getNivell());
		assertEquals(0, p3.getNivell());
		assertEquals(0, p4.getNivell());
	}

	public void testGetVictories() {
		assertEquals(0, p1.getVictories());
		assertEquals(0, p2.getVictories());
		assertEquals(0, p3.getVictories());
		assertEquals(0, p4.getVictories());
	}

	public void testGetDerrotes() {
		assertEquals(0, p1.getDerrotes());
		assertEquals(0, p2.getDerrotes());
		assertEquals(0, p3.getDerrotes());
		assertEquals(0, p4.getDerrotes());
	}

	public void testToExport() {
		assertEquals("\n\tEXPORTSCIO AMB EXIT", Personatge.toExport("Baboo CAVALLER 14 15 6 14 11 ESPASA 29 6 35 31 0 0 0 0"));
	}

	public void testPersonatge2() {
		Personatge prova = new Personatge("Baboo", "CAVALLER", 14, 15, 6, 14, 11, b, 29, 6, 35, 31, 0, 0, 0, 0);
		assertSame(Personatge.class, prova.getClass());
	}

}
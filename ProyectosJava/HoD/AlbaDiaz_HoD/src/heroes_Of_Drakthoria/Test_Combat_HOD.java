package heroes_Of_Drakthoria;

import junit.framework.TestCase;

public class Test_Combat_HOD extends TestCase {
	
	Arma a = new Arma("prova", 10, 10);
	
	Personatge p1 = new Personatge("nom", "GUERRER", a, 5, 5, 5, 5, 5);
	
	Personatge p2 = new Personatge("nom", "guerrer", a, 5, 5, 5, 5, 5);
	
	Combat prova = new Combat(p1, p2);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCombat() {
		assertSame(Combat.class, prova.getClass());
	}

	public void testGetP1() {
		assertEquals(p1, prova.getP1());
	}

	public void testGetP2() {
		assertEquals(p2, prova.getP2());
	}

}

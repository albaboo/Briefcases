package heroes_Of_Drakthoria;

import junit.framework.TestCase;

public class Test_Dau_HOD extends TestCase {
	
	Dau prova = new Dau(25);
	
	Dau prova2 = new Dau();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDau() {
		assertSame(Dau.class, prova2.getClass());
	}

	public void testDauInt() {
		assertSame(Dau.class, prova.getClass());
		assertSame(prova.getClass(), prova2.getClass());
	}

	public void testLlencament() {
		int tirar = prova.llencament();
		assertEquals(true, tirar>=1 && tirar <= 25);
		prova2.setValor(20);
		tirar = prova2.llencament();
		assertEquals(true, tirar>=1 && tirar <= 20);
	}

}

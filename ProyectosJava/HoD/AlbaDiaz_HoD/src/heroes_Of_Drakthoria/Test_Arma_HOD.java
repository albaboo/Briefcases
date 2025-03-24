package heroes_Of_Drakthoria;

import org.junit.Assert;

import junit.framework.TestCase;

public class Test_Arma_HOD extends TestCase {
	
	Arma prova = new Arma("nombre de arma", 10, 20);
	
	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	public void testArma() {
		assertSame(Arma.class, prova.getClass());
	}
	
	public void testGetNom() {
		assertEquals("NOMBRE DE ARMA", prova.getNom());
	}

	public void testGetWpow() {
		assertEquals(10, prova.getWpow());
	}

	public void testGetWvel() {
		assertEquals(20, prova.getWvel());
	}

	public void testGetArmaString() {
		assertSame(Arma.class, Arma.getArma(prova.getNom()).getClass());
		assertEquals("NOMBRE DE ARMA", Arma.getArma(prova.getNom()).getNom());
		assertEquals(10, Arma.getArma(prova.getNom()).getWpow());
		assertEquals(20, Arma.getArma(prova.getNom()).getWvel());
	}

}

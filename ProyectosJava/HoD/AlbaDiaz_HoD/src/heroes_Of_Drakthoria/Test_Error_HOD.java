package heroes_Of_Drakthoria;

import junit.framework.TestCase;

public class Test_Error_HOD extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testError() {
		Error prova = new Error(1);
		assertSame(Error.class, prova.getClass());
	}

}

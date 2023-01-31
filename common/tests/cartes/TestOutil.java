package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;



public class TestOutil {

	@Test
	public void testConstructeurOutil() {
		Outil o = new Outil("Maillet", 200, 100, 15, 50, 2);
		assertEquals(o.getRessources().getNom(), "Maillet");
		assertEquals(o.getRessources().getPierre(), 200);
		assertEquals(o.getRessources().getBois(), 100);
		assertEquals(o.getRessources().getSavoir(), 15);
		assertEquals(o.getRessources().getTuile(), 50);
		assertEquals(o.getRessources().getEcus(), 2);
	}
	
	@Test
	public void TestAffichage() {
		Outil o = new Outil("Maillet", 200, 100, 15, 50, 2);
		assertEquals("Outil: \"Maillet\" bois=100 pierres=200 tuiles=50 savoir=15 cout=2", o.toString());
	}
}

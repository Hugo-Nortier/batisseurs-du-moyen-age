package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;



public class TestUniversite {

	@Test
	public void testConstructeurUniversite() {
		Universite u = new Universite("Université", 2500, 10200, 250,14700 , 15);
		assertEquals(u.getRessources().getNom() , "Université");
		assertEquals(u.getRessources().getPierre() , 2500);
		assertEquals(u.getRessources().getBois(), 10200);
		assertEquals(u.getRessources().getSavoir(), 250);
		assertEquals(u.getRessources().getTuile() , 14700);
		assertEquals(u.getRessources().getEcus(), 15);
	}
}

package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;



public class TestEmprunt {
	
	@Test
	public void TestConstructeurEmprunt() {
		Emprunt e = new Emprunt("emprunt",15,12,9);
	    assertEquals(e.getNom(),"emprunt");
	    assertEquals(e.getGainEcus(),15);
	    assertEquals(e.getPenalite(),12);
	    assertEquals(e.getEcusRembours(),9);
	    assertEquals(e.isRembourse(),false);

	}	
}


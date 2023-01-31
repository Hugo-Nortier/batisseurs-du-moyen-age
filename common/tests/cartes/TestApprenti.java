package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestApprenti {
	
	@Test
	public void testConstructeurApprenti() {
		Apprenti a = new Apprenti("Apprenti",2000, 200, 3, 1000, 4);
		assertEquals(a.getRessources().getNom(), "Apprenti");
		assertEquals(a.getRessources().getBois(), 200);
		assertEquals(a.getRessources().getPierre(), 2000);
		assertEquals(a.getRessources().getSavoir(), 3);
		assertEquals(a.getRessources().getTuile(), 1000);
		assertEquals(a.getRessources().getEcus(), 4);
	}
}

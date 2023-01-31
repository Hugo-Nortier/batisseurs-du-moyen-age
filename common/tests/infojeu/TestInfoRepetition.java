package infojeu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



public class TestInfoRepetition {
	@Test
	public void testInfoRepetition() { 
		InfoRepetition ir=new InfoRepetition();
		assertEquals(ir.getRepetition(), 0);
		ir.ajouterRepetition(4);
		assertEquals(ir.getRepetition(), 4);
		ir.resetGI();
		assertEquals(ir.getRepetition(), 0);
		assertFalse(ir.getInv());
		ir.prisEcus();
		assertTrue(ir.isPrisEcus());
		ir.investi();
		assertTrue(ir.getInv());
		ir.acheteAction();
		assertTrue(ir.isAcheteAction());




		
	}
}

package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;






public class TestOuvrier {
	@Test
	public void testAddEnleverOutil() {
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Outil r=new Outil("Outil 1", 1, 0, 0, 2, 0);
		o.addOutil(r);
		assertEquals(o.getRessources().getBois(),200);
		assertEquals(o.getRessources().getPierre(),2001);
		assertEquals(o.getRessources().getTuile(),1002);
		o.recupererOutil();
		assertEquals(o.getRessources().getBois(),200);
		assertEquals(o.getRessources().getPierre(),2000);
		assertEquals(o.getRessources().getTuile(),1000);


	}
	@Test
	public void Testinstruire() {
		Ouvrier o=new Ouvrier("Ouvrier o", 5, 0, 7, 0, 0);
		Universite i=new Universite("Univ", 1, 4, 3, 9, 1);
		o.instruire(i);
		assertTrue(o.getRessources().getBois()==4);
		assertTrue(o.getRessources().getPierre()==6);
		assertTrue(o.isInstruit());

	}
	
	@Test
	public void TestAffichage() {
		Ouvrier o=new Ouvrier("Arthur", 5, 3, 7, 5, 2);
		assertEquals("Arthur: bois=3, pierres=5, tuiles=5, savoir=7, cout=2", o.toString());
	}
	
}

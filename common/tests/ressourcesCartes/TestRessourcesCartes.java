package ressourcesCartes;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cartes.Batiment;
import cartes.Ouvrier;



public class TestRessourcesCartes {
	
	@Test
	public void testConstructeurStat() {
		RessourceBatiment sb = new RessourceBatiment("s1", 3, 4, 5, 2,5,10);
		assertEquals(sb.getNom(),"s1");
		assertEquals(sb.getBois(),4);
		assertEquals(sb.getPierre(),3);
		assertEquals(sb.getSavoir(),5);
		assertEquals(sb.getTuile(),2);
		assertEquals(sb.getPtsVictoire(), 5);
		assertEquals(sb.getEcusRecompense(), 10);
	}
	
	@Test
	public void testConstructeurStatOuvrier() {
		Ressources so = new Ressources("ouvrier1", 3, 4, 5, 2, 5);
		assertEquals(so.getNom(),"ouvrier1");
		assertEquals(so.getBois(),4);
		assertEquals(so.getPierre(),3);
		assertEquals(so.getSavoir(),5);
		assertEquals(so.getTuile(),2);
		assertEquals(so.getEcus(),5);
	}

	
	@Test
	public void testRentabiliteOuvrier() {
		Ouvrier ro = new Ouvrier("resO1", 3, 4, 3, 2, 9);
		assertEquals(3, ro.rentabilite());
	}
	
	@Test
	public void testRentabiliteBatiment() {
		Batiment b = new Batiment("res1", 3, 4, 5, 2, 5, 10);
		assertEquals(1, b.rentabilite());
	}
	@Test
	public void testEquals() {
		Batiment b = new Batiment(null, 3, 4, 5, 2, 5, 10);
		Batiment b2= new Batiment(null, 3, 4, 5, 2, 5, 10);
		assertTrue(b.getRessources().equals(b2.getRessources()));
		Batiment b3= new Batiment("b", 3, 4, 9, 2, 5, 10);
		assertFalse(b.getRessources().equals(b3.getRessources()));

	}
}

package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;



public class TestEsclave {

	@Test
	public void testConstructeurEsclave() {
		Esclave e = new Esclave("Django",450,285,300,150,12);
		assertEquals(e.getRessources().getNom(),"Django");
		assertEquals(e.getRessources().getBois(),285);
		assertEquals(e.getRessources().getPierre(),450);
		assertEquals(e.getRessources().getSavoir(),300);
		assertEquals(e.getRessources().getTuile(),150);
		assertEquals(e.getRessources().getEcus(),0);
		assertEquals(e.getCoutAffranchi(),12);
		assertEquals(e.isAffranchi(),false);
	}
	
	@Test
	public void TestgetCoutAchat() {
		Esclave e = new Esclave("Django",450,285,300,150,17);
		assertEquals(e.getRessources().getEcus(),0);
	}
	
	@Test
	public void TestisAffranchi() {
		Esclave e = new Esclave("Django",450,285,300,150,17);
		assertEquals(e.isAffranchi(),false);
	}
	
	@Test
	public void Testaffranchir() {
		Esclave e = new Esclave("Django",450,285,300,150,17);
		e.affranchir();
		assertEquals(e.isAffranchi(),true);
	}
	
	@Test
	public void TestAffichage() {
		Esclave e = new Esclave("Kevin",450,285,300,150,17);
		assertEquals("Esclave: \"Kevin\" bois=285 pierres=450 tuiles=150 savoir=300 cout=0", e.toString());
	}
}


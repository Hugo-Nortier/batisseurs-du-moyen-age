package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import infojeu.InfoJoueur;
import infojeu.InfoPlateau;

public class TestMachine {
	@Test
	public void testConstructeurMachine() {
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Machine m = new Machine("eglise", 25000, 10200, 250, 14700, 10, 15, o);
		assertEquals(m.getRessources().getPierre(), 25000);
		assertEquals(m.getRessources().getBois(), 10200);
		assertEquals(m.getRessources().getSavoir(), 250);
		assertEquals(m.getRessources().getTuile(), 14700);
		assertEquals(m.getRessources().getPtsVictoire(), 10);
		assertEquals(m.getRessources().getEcusRecompense(), 15);
		assertEquals(m.getOuvrier(), o);

	}
	@Test
	public void testFinirMachine() {
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Machine m = new Machine("eglise", 0,0,0,0, 0, 0, o);

		InfoJoueur j = new InfoJoueur();
		InfoPlateau p = new InfoPlateau();

		m.finirBat(j, p);
		assertEquals(j.getOuvriers().size(),1);
	}
	
}

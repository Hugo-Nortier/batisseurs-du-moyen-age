package cartes;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import infojeu.InfoJoueur;
import infojeu.InfoPlateau;

import ressourcesCartes.RessourceBatiment;

public class TestBatiment {
	@Test
	public void testConstructeurBatiment() {
		Batiment b = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		RessourceBatiment sb = new RessourceBatiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		assertEquals(b.getRessources(), sb);
	}

	@Test
	public void testBatimentEstFini() {
		Batiment b = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Ouvrier o1 = new Ouvrier("Ouvrier 2", 2000, 200, 3, 1000, 4);
		Ouvrier o2 = new Ouvrier("Ouvrier 3", 2000, 200, 3, 1000, 4);
		Ouvrier o3 = new Ouvrier("Ouvrier 4", 25000, 10200, 250, 14700, 10);
		Ouvrier o4 = new Ouvrier("Ouvrier 4", 25000, 10200, 250, 14700, 0);
		b.getOuvriers().add(o);
		b.getOuvriers().add(o1);
		b.getOuvriers().add(o2); 
		assertFalse(b.estFini());
		b.getOuvriers().removeAll(b.getOuvriers());
		b.getOuvriers().add(o3);
		assertTrue(b.estFini());
		b.getOuvriers().remove(0);
		b.getOuvriers().add(o4);
		b.getOuvriers().remove(0);
		Ouvrier o5 = new Ouvrier("Ouvrier 3", 1000, 200, 100, 1000, 4);
		Ouvrier o6 = new Ouvrier("Ouvrier 4", 4000, 8000, 400, 14700, 5);
		Ouvrier o7 = new Ouvrier("Ouvrier 4", 20000, 2000, 30, 14700, 0);
		b.getOuvriers().add(o5);
		b.getOuvriers().add(o6);
		b.getOuvriers().add(o7);
		assertTrue(b.estFini());
	}
	@Test
	public void testFinirBat() {
		Ouvrier o = new Ouvrier ("Ouvrier", 25000, 10200, 250, 14700, 2);
		

		InfoJoueur j = new InfoJoueur();
		j.setEcus(10);
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40); 
		Batiment b = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		j.addOuvrier(o);
		j.getBatiments().add(b);
		assertEquals(j.getOuvriers().get(0).getRessources().getBois(), 10200);
		assertEquals(j.getOuvriers().get(0).getRessources().getPierre(), 25000);
		assertEquals(j.getOuvriers().get(0).getRessources().getSavoir(), 250);
		
		j.ajoutNbEcus(-j.getOuvriers().get(0).getRessources().getEcus());
		b.ouvriers.add(j.getOuvriers().remove(0));
		b.finirBat(j, p);
		assertEquals(j.getNbEcus(),23);
		assertFalse(j.getBatFinis().isEmpty());
		assertEquals(j.getOuvriers().get(0),o);
		assertEquals(j.getPtsVictoire(),10);
		j.getBatiments().add(b);
		j.getOuvriers().remove(0);
		Ouvrier o1 = new Ouvrier ("Ouvrier", 24000, 10200, 250, 14700, 4);
		j.getOuvriers().add(o1);
		j.ajoutNbEcus(-j.getOuvriers().get(0).getRessources().getEcus());
		b.ouvriers.add(j.getOuvriers().remove(0));
		b.finirBat(j, p);
		assertTrue(j.getOuvriers().isEmpty());
		assertEquals(j.getPtsVictoire(),10);
		assertEquals(j.getBatFinis().size(),1);

		
		
	}
	@Test
	public void TestAffichage() {
		Batiment b = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		assertEquals("eglise: bois=10200, pierres=25000, tuiles=14700, savoir=250, monnaie récompense=15, points victoire=10", b.toString());
	}
}

package jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;




import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.Test;

import action.AcheterAction;
import action.Action;
import action.PrendreEcus;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;


public class TestJoueur {
	@Test
	public void testJoueur() {
		Affichage a = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",a);
		Ouvrier o = new Ouvrier("ouvrier1", 3, 4, 5, 2, 5);
		j.addOuvrier(o);
		assertEquals(j.getOuvriers().get(0), o);
		assertEquals(1, j.getOuvriers().size());

	}
	
	
	@Test
	public void testjoueurDistribution() {
		Affichage a = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",a);
		assertEquals(j.getNbEcus(),10);
	}
	
	
	
	@Test
	public void testGetNbActionsEchanges() {
		PrendreEcus prendre = new PrendreEcus();
			Random mocks = mock(Random.class);
			
			when((Integer)mocks.nextInt()).thenReturn(1);
			assertEquals(prendre.getNbActionsEchangees(mocks, 1),1);
		}

	@Test
	public void testChoixAchatAction() {
		Affichage a = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",a);
		Action ach = new AcheterAction();
		Plateau p = new Plateau();
		InfoRepetition ir = new InfoRepetition();
		ach.effectuerAction(p,j, new Random(), 1, a,ir,new ChoixCartes());
		assertEquals(p.getReserveEcus(), 45);
		assertEquals(j.getNbEcus(), 5);
		ach.effectuerAction(p,j, new Random(), 1, a,ir,new ChoixCartes());
		assertEquals(p.getReserveEcus(), 50);
		assertEquals(j.getNbEcus(), 0);
		ach.effectuerAction(p,j, new Random(), 1, a,ir,new ChoixCartes());
		assertEquals(p.getReserveEcus(), 50);
		assertEquals(j.getNbEcus(), 0);
		
	}
	
	
	@Test
	public void testGetnbAction() {
		Affichage a = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",a);
		j.setNbAction(0);
		assertEquals(j.getNbAction(),0);
		j.setNbAction(1);
		assertEquals(j.getNbAction(),1);
	}
	
}


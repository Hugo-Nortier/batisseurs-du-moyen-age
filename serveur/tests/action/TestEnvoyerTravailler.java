package action;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;


import cartes.Batiment;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;

public class TestEnvoyerTravailler {
	@Test
	public void testEnvoyerOuvrierTravailler() { //Dans joueur
		Affichage a = new Affichage("MOYENAGE",5);
		Action env = new EnvoyerTravailler();
		Joueur j = new Joueur(1,"MOYENAGE",a);
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Ouvrier o1 = new Ouvrier("Ouvrier 2", 2000, 200, 3, 1000, 4);
		Ouvrier o2 = new Ouvrier("Ouvrier 3", 2000, 200, 3, 1000, 4);
		Ouvrier o3 = new Ouvrier("Ouvrier 4", 25000, 10200, 250, 14700, 10);
		Ouvrier o4 = new Ouvrier("Ouvrier 5", 25000, 10200, 250, 14700, 0);
		Ouvrier o5 = new Ouvrier("Ouvrier 6", 200973, 10200, 10100, 14700, 0);
		j.addOuvrier(o);
		j.addOuvrier(o1);
		j.addOuvrier(o2);
		j.addOuvrier(o3);
		j.addOuvrier(o4);
		j.ajoutNbEcus(500);

		Batiment b=new Batiment("Batiment 1",200973,10200,4830,8,657,78);
		j.getBatiments().add(b);
		Plateau p=new Plateau();
		assertEquals(env.effectuerAction(p, j, new Random(), 1, a,new InfoRepetition(),new ChoixCartes()),0);
		j.resetCoutActionsBatiments();
		assertEquals(env.effectuerAction(p, j, new Random(), 2, a,new InfoRepetition(),new ChoixCartes()),1);
		assertEquals(env.effectuerAction(p, j, new Random(), 2, a,new InfoRepetition(),new ChoixCartes()),0);
		j.resetCoutActionsBatiments();

		env.effectuerAction(p, j, new Random(), 1, a,new InfoRepetition(),new ChoixCartes());
		j.resetCoutActionsBatiments();
		assertEquals(b.getOuvriers().get(0),o4);
		assertEquals(j.getBatiments().get(0).boisRestant(),0);
		assertFalse(b.estFini());
		j.getOuvriers().remove(o3);
		j.addOuvrier(o5);
		env.effectuerAction(p, j, new Random(), 1, a,new InfoRepetition(),new ChoixCartes());
		assertFalse(b.estFini());
		assertTrue(b==j.getBatFinis().get(0));
		j.setEcus(0);
		j.addBatiment(new Batiment("Bat", 0, 0, 0, 4, 0, 8));
	j.getBatiments().get(0).addCoutActionTravail();
	assertEquals(env.effectuerAction(p, j, new Random(), 2, a,new InfoRepetition(),new ChoixCartes()),0);
	j.getOuvriers().remove(o4);



		
	}
}

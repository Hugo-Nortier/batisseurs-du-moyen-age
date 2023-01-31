package action;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;

public class TestOuvrirChantier {

	@Test
	public void testOuvrirChantier() {
		Plateau p=new Plateau();
		Affichage a = new Affichage("MOYENAGE",5);
		Action ouvrir = new OuvrirChantier();

		Joueur j=new Joueur(1, "MOYENAGE", a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		Random mocks = mock(Random.class);
		when((Integer)mocks.nextInt()).thenReturn(0);
		ouvrir.effectuerAction(p,j, mocks, 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getBatiments().size(),1);
		ouvrir.effectuerAction(p,j, mocks, 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getBatiments().size(),2);
	}
}

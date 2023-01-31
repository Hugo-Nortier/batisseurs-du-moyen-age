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

public class TestRecruterOuvrier {
	@Test
	public void testRecruterOuvrier() {
		Plateau p=new Plateau();
		Affichage a = new Affichage("MOYENAGE",5);
		Action recr = new RecruterOuvrier();

		Joueur j=new Joueur(1, "MOYENAGE", a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		Random mocks = mock(Random.class);
		when((Integer)mocks.nextInt()).thenReturn(0);
		recr.effectuerAction(p,j, mocks, 3, a,new InfoRepetition(),new ChoixCartes());

		assertEquals(j.getOuvriers().size(),2);
		recr.effectuerAction(p,j, mocks, 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getOuvriers().size(),3);
		
		
	}
}

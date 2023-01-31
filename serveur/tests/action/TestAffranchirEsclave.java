package action;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;


import cartes.Esclave;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;

public class TestAffranchirEsclave {
	@Test 
	public void testAffranchirEsclave() {
		Plateau p=new Plateau();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action affranchir = new AffranchirEsclave();

		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		Random mocks = mock(Random.class);
		when((Integer)mocks.nextInt()).thenReturn(0);
		Esclave o=new Esclave("Eslave 0", 0, 0, 0, 0, 0);
		j.getOuvriers().add(o);
		assertEquals(j.getEsclaves().size(),1);
		   affranchir.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getEsclaves().size(),0);
		
	}
}

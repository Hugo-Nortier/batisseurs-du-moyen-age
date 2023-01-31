package action;

import static org.junit.jupiter.api.Assertions.*;



import java.util.*;

import org.junit.jupiter.api.*;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestFinirTour {
	@Test
	public void testFinirTour() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action finirTour = new FinirTour();
		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		
		finirTour.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getStats().getNbAchatPasseTourConsecutif(),1);
	} 
}



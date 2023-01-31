package action;

import static org.junit.jupiter.api.Assertions.assertEquals;




import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestContracterEmprunt {
	@Test
	public void testContracterEmprunt() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action contracter = new ContracterEmprunt();

		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		
		assertEquals(j.getNbEcus(),10);
		   contracter.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getNbEcus(),20);
		
	} 
}

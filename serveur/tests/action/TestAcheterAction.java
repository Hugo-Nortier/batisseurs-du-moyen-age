package action;

import static org.junit.jupiter.api.Assertions.*;



import java.util.*;

import org.junit.jupiter.api.*;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestAcheterAction {
	@Test
	public void testAcheterEsclave() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action acheterAction = new AcheterAction();
		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		int actionsJoueurs =3;
		assertEquals(j.getNbEcus(),10);
		assertEquals(j.getStats().getAchatAction(),0);

		 acheterAction.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getNbEcus(),5);
		assertEquals(actionsJoueurs+j.getStats().getAchatAction(),4);
	} 
	
}



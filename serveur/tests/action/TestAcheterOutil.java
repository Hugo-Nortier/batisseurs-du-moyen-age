package action;

import static org.junit.jupiter.api.Assertions.*;




import java.util.*;

import org.junit.jupiter.api.*;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestAcheterOutil {
	@Test
	public void testAcheterOutil() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action acheterOutil = new AcheterOutil();
		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		
		assertEquals(j.getNbEcus(),10);
		int reservePlateau = p.getReserveEcus();
		assertEquals(j.getOutils().size(),0);
		assertEquals(p.getPaquetOutils().size(),4);
		  acheterOutil.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(reservePlateau+2, p.getReserveEcus());
		assertEquals(j.getNbEcus(),8);
		assertEquals(j.getOutils().size(),1);
		assertEquals(p.getPaquetOutils().size(),3);
	} 

}



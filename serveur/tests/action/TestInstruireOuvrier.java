package action;


import static org.junit.jupiter.api.Assertions.*;



import java.util.*;

import org.junit.jupiter.api.*;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestInstruireOuvrier {
	@Test
	public void testInstruireOuvrier() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action instruire = new InstruireOuvrier();

		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		assertEquals(p.getPaquetUniversite().size(),4);

		assertEquals(j.getNbEcus(),10);
		int reservePlateau = p.getReserveEcus();
		InfoRepetition ir = new InfoRepetition();
		instruire.effectuerAction(p, j, new Random(), 3, a,ir,new ChoixCartes());
		assertEquals(j.getNbEcus(),3);
		assertEquals(reservePlateau+7, p.getReserveEcus());

		assertEquals(p.getPaquetUniversite().size(),3);
		assertEquals(ir.getInv(),true);
	} 
}

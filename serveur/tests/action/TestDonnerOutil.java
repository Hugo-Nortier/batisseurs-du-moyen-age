package action;

import static org.junit.jupiter.api.Assertions.assertEquals;




import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;


import cartes.*;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.PlateauAntiquite;

public class TestDonnerOutil {
	@Test
	public void testDonnerOutil() {
		PlateauAntiquite p=new PlateauAntiquite();
		Affichage a = new Affichage("ANTIQUITE",5);
		Action donnerOutil = new DonnerOutil();

		Joueur j = new Joueur(1,"ANTIQUITE",a);
		ArrayList<Joueur>joueurs=new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		j.addOutil(new Outil("machin",1,2,3,4,5));
		
		j.addOuvrier(new Ouvrier("machin",2,3,4,5,6));
		j.addBatiment(new Batiment("batiment",3,4,5,6,7,8));
		assertEquals(j.getOutils().size(),1);
		donnerOutil.effectuerAction(p, j, new Random(), 3, a,new InfoRepetition(),new ChoixCartes());
		assertEquals(j.getOutils().size(),0);
	}
}

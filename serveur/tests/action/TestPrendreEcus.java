package action;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;

public class TestPrendreEcus {
	@Test
	public void testPrendreEcus() {
		Plateau p=new Plateau();
		Affichage a = new Affichage("MOYENAGE",5);
		Joueur j=new Joueur(1, "MOYENAGE",a);
		PrendreEcus prendre = new PrendreEcus();
		InfoRepetition ir = new InfoRepetition();
		prendre.prendreEcus(p, 1, 3, j, a,ir,new ChoixCartes());
		assertEquals(j.getNbEcus(),11);
		assertEquals(p.getReserveEcus(),39);
		ir.resetGI();
		prendre.prendreEcus(p, 2, 3, j, a,ir,new ChoixCartes());
		assertEquals(j.getNbEcus(),14);
		assertEquals(p.getReserveEcus(),36);
		p.sousReserveEcus(50);
		p.ajouterReserveEcus(14);
		prendre.prendreEcus(p, 2, 3, j, a,ir,new ChoixCartes());
		assertEquals(j.getNbEcus(),14);
		assertEquals(p.getReserveEcus(),0);

		
	}
}

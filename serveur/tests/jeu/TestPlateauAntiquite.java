package jeu;

import static org.junit.jupiter.api.Assertions.*;



import java.util.*;

import org.junit.jupiter.api.*;

import cartes.*;

public class TestPlateauAntiquite {
	private PlateauAntiquite p = new PlateauAntiquite();
	private ArrayList<Joueur> joueurs = new ArrayList<>();
	@Test
	public void testInitDesPioches() {
		p = new PlateauAntiquite();
		//le constructeur de Plateau contient la méthode initPioches 
		//qui met 45 cartes dans chacune des pioches (batiments et ouvriers)
		assertEquals(42, p.getPiocheBatiments().size()); 
		assertEquals(42, p.getPiocheOuvriers().size());
		//on vérifie que les cartes ont bien été instanciées 
		for(int i=0;i<8;i++) 
			assertEquals(p.getPiocheOuvriers().get(i).getRessources().getNom(),"maitre");
		for(int i=0;i<16;i++) {
			assertEquals(p.getPiocheOuvriers().get(i+8).getRessources().getNom(),"compagnon");
		}
		assertTrue(p.getReserveEcus()==40);
	}
	@Test
	public void testPiocherApprenti() {
		Affichage af = new Affichage("ANTIQUITE",5);
		Joueur j = new Joueur(1,"ANTIQUITE",af);
		joueurs.add(j);
		Apprenti a = new Apprenti("apprenti test", 0, 0, 0, 0, 1);
		joueurs.get(0).addOuvrier(a);
		p.initPartie(joueurs);
		assertTrue(joueurs.get(0).getOuvriers().get(0).equals(a));
		//36 car piocherApprenti() return piocheOuvriers.remove(0);
		assertEquals(p.getPiocheOuvriers().size(),36);
	}
	@Test
	public void testInitPartie() {
		p = new PlateauAntiquite();
		ArrayList<Ouvrier> piocheOuvriersTemp = new ArrayList<>();
		for(int i=0;i<p.getPiocheOuvriers().size();i++) 
			piocheOuvriersTemp.addAll(p.getPiocheOuvriers());
		// rappel: initPartie() mélange les pioches!
		p.initPartie(joueurs);
		// on vérifie que les cartes sont pas dans le même ordre
		assertFalse(piocheOuvriersTemp.equals(p.getPiocheOuvriers()));
		// on vérifie qu'il y a bien 5 cartes dans les paquets 
		assertEquals(p.getPaquetEmprunt().size(),4);
		assertEquals(p.getPaquetEsclaves().size(),6);
		assertEquals(p.getPaquetEsclaves().get(0).getRessources().getNom(),"esclave");
		assertEquals(p.getPaquetOutils().size(),4);
		assertEquals(p.getPaquetOutils().get(0).getRessources().getNom(),"un maillet");
		assertTrue(p.getPaquetBatiments().size()==5);
		assertTrue(p.getPaquetOuvriers().size()==5);

	}
	
	@Test
	void testGagnantsAntiquite() {
		Affichage a = new Affichage("ANTIQUITE",5);
		Joueur j1 = new Joueur(1,"ANTIQUITE",a);
		Joueur j2 = new Joueur(2,"ANTIQUITE",a);
		Joueur j3 = new Joueur(3,"ANTIQUITE",a);
		PlateauAntiquite p = new PlateauAntiquite();
		ArrayList<Joueur> joueurs = new ArrayList<>();
		ArrayList<Joueur> gagnants = new ArrayList<>();

		joueurs.add(j1);
		joueurs.add(j2);
		joueurs.add(j3);
		p.initPartie(joueurs);

		//17 pour gagner
		j1.ajouterPtsVictoire(17);
		gagnants.add(j1);
		assertEquals(p.getGagnants(joueurs), gagnants);
		j2.ajouterPtsVictoire(20);
		gagnants.add(j2);
		gagnants.remove(j1);
		//j2 gagne car il a plus de points vicctoires
		assertEquals(p.getGagnants(joueurs), gagnants);
		//on ajoute 51 écus donc j1 aura 21 points
		j1.setEcus(51);
		gagnants.add(j1);
		gagnants.remove(j2);
		//j1 gagnera avec ses 21 points
		assertEquals(p.getGagnants(joueurs), gagnants);

		//on va donner 1 emprunt à j1, lui faisant perdre 2points!
		Emprunt e = new Emprunt("e", 5, 5, 5);
		j1.getEmprunts().add(e);
		gagnants.add(j2);
		gagnants.remove(j1);
		//j2 gagne car il a plus de points vicctoires (20) j1 en a 19 depuis qu il a un emprunt
		assertEquals(p.getGagnants(joueurs), gagnants);
		
		//on va donner 1 esclave à j2, lui faisant perdre 1point!
		Esclave es = new Esclave("es", 10, 10, 10, 10, 2);
		Esclave es2 = new Esclave("es", 10, 10, 10, 10, 2);
		Esclave es3 = new Esclave("es", 10, 10, 10, 10, 2);
		j2.setEcus(0);
		j2.addOuvrier(es);
		j2.addOuvrier(es2);
		j2.addOuvrier(es3);

		
		gagnants.add(j1);
		
		gagnants.removeAll(gagnants);
		gagnants.add(j1);
		//j1 gagne car il aura 19 points et j2 en aura 18 car on lui a retiré
		// 3 points du fait qu'il ait 3 esclaves non affranchis
		 assertEquals(p.getGagnants(joueurs), gagnants);		
		
			
	}
}

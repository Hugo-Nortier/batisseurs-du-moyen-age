package jeu;

import static org.junit.jupiter.api.Assertions.*;




import java.util.ArrayList;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import cartes.*;

public class TestPlateau {
	private Plateau p = new Plateau();
	private ArrayList<Joueur> joueurs = new ArrayList<>();

	@Test
	public void testInitDesPioches() {
		p = new Plateau();
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
		Affichage af = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",af);
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
		p = new Plateau();
		ArrayList<Ouvrier> piocheOuvriersTemp = new ArrayList<>();
		for(int i=0;i<p.getPiocheOuvriers().size();i++) 
			piocheOuvriersTemp.addAll(p.getPiocheOuvriers());
		// rappel: initPartie() mélange les pioches!
		p.initPartie(joueurs);
		// on vérifie que les cartes sont pas dans le même ordre
		assertFalse(piocheOuvriersTemp.equals(p.getPiocheOuvriers()));
		// on vérifie qu'il y a bien 5 cartes dans les paquets 
		assertTrue(p.getPaquetBatiments().size()==5);
		assertTrue(p.getPaquetOuvriers().size()==5);

	}
	@Test
	void testGagnants() {
		
	    Affichage a = new Affichage("MOYENAGE",5);
		Joueur j = new Joueur(1,"MOYENAGE",a);
		Joueur j2 = new Joueur(2,"MOYENAGE",a);
		Joueur j3 = new Joueur(3,"MOYENAGE",a);
		ArrayList<Joueur> js = new ArrayList<>();
		js.add(j);
		js.add(j2);
		js.add(j3);
		Plateau p = new Plateau();
		p.initPartie(js);
		j.ajouterPtsVictoire(20);
		ArrayList<Joueur> res = new ArrayList<>();
		res.add(j);
		assertEquals(p.getGagnants(js),res);
		j2.ajouterPtsVictoire(20);
		res.add(j2);
		assertEquals(p.getGagnants(js),res);
		res.removeAll(res);
		j2.ajouterPtsVictoire(20);
		j2.ajoutNbEcus(10);
		res.add(j2);
		assertEquals(p.getGagnants(js),res);
		res.removeAll(res);
		j.ajoutNbEcus(11);
		j.ajouterPtsVictoire(20);
		res.add(j);
		assertEquals(p.getGagnants(js),res);



	}
}
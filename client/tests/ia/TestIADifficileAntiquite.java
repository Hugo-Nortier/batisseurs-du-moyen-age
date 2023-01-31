package ia;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;
import infojeu.InfoJoueur;
import infojeu.InfoPlateau;




public class TestIADifficileAntiquite {
	
	@BeforeEach
	public void init() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);

	}
	
	@Test
	public void TestPoidsInstruireOuvrier() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		p.getPaquetEmprunt().removeAll(p.getPaquetEmprunt());

		assertEquals(iatest.poidsInstruireOuvrier(p, j1.getNbEcus(), j1.getOuvriers()),-1);
		p.getPaquetUniversite().add(new Universite("Univ ", 0, 0, 0, 0, 0));

		assertEquals(iatest.poidsInstruireOuvrier(p, j1.getNbEcus(), j1.getOuvriers()),-1);
		j1.addOuvrier(new Ouvrier("Ouvrier 1", 0, 0, 0, 0, 0));
		iatest.setInvestissement(false);
		assertNotEquals(iatest.poidsInstruireOuvrier(p, j1.getNbEcus(), j1.getOuvriers()),-1);
		
	}
	
	@Test
	public void TestPoidsAcheterEsclave() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		p.getPaquetEsclaves().add(new Esclave ("Ouvrier 1", 1, 1, 1, 1, 1));
		assertEquals(iatest.poidsAcheterEsclave(p, j1.getNbEcus()),3);
		j1.setEcus(0);
		assertEquals(iatest.poidsAcheterEsclave(p, j1.getNbEcus()),-1);
		assertEquals(iatest.poidsAcheterEsclave(p, j2.getNbEcus()),3);
		p.getPaquetEsclaves().clear();
		assertEquals(iatest.poidsAcheterEsclave(p, j2.getNbEcus()),-1);
		Esclave e = new Esclave("Esclave",4,4,4,4,4);
		p.getPaquetEsclaves().add(e);
		assertEquals(iatest.poidsAcheterEsclave(p, j2.getNbEcus()),3);
	}
	
	@Test
	public void TestPoidsAcheterOutil() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		Outil o1 = new Outil("Outil",4,4,4,4,4);
		p.getPaquetOutil().add(o1);
		assertEquals(iatest.poidsAcheterOutil(p, j1.getNbEcus()),3);
		j1.setEcus(0);
		assertEquals(iatest.poidsAcheterOutil(p, j1.getNbEcus()),-1);
		assertEquals(iatest.poidsAcheterOutil(p, j2.getNbEcus()),3);
		p.getPaquetOutil().clear();
		assertEquals(iatest.poidsAcheterOutil(p, j2.getNbEcus()),-1);
		Outil o = new Outil("Maillet", 200, 100, 15, 50, 2);
		p.getPaquetOutil().add(o);
		assertEquals(iatest.poidsAcheterOutil(p, j2.getNbEcus()),3);
	}
	@Test
	public void TestPoidsAffranchirEsclave() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		assertEquals(iatest.poidsAffranchirEsclave(j1.getOuvriers(),j1.getPtsVictoire()),-1);
		j1.addOuvrier(new Esclave("Esclave 1", 0, 0, 0, 0, 0));
		j1.ajouterPtsVictoire(20);
		assertEquals(iatest.poidsAffranchirEsclave(j1.getOuvriers(),j1.getPtsVictoire()),2);
		j1.ajouterPtsVictoire(-20);
		assertEquals(iatest.poidsAffranchirEsclave(j1.getOuvriers(),j1.getPtsVictoire()),-1);

		
	}
	@Test
	public void TestPoidsDonnerOutil() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		assertEquals(iatest.poidsDonnerOutil(j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3),-1);
		j1.addOutil(new Outil("Outil 1", 0, 0, 0, 0, 0));
		j1.addOuvrier(new Ouvrier("Ouvrier 1", 0, 0, 0, 0, 0));
		j1.addBatiment(new Batiment("Batiment", 0, 0, 0, 0, 0, 0));
		assertEquals(iatest.poidsDonnerOutil(j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3),3);		
		
	}
	@Test
	public void TestChoixActionl() {
		InfoJoueur j1 = new InfoJoueur();
		InfoJoueur j2 = new InfoJoueur();
		IADifficileAntiquite iatest = new IADifficileAntiquite();
		j1.setEcus(10);
		j2.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j1);
		js.add(j2);
		p.getPaquetBatiments().removeAll(p.getPaquetBatiments());
		p.getPiocheBatiments().removeAll(p.getPiocheBatiments());
		p.getPaquetEsclaves().removeAll(p.getPaquetEsclaves());
		p.sousReserveEcus(40);
		p.getPaquetOuvriers().add(new Ouvrier("O", 0, 0, 0, 0, 0));
		p.getPaquetEmprunt().add(new Emprunt("O", 0, 0, 0));

		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()), "RecruterOuvrier");
		p.getPaquetOuvriers().removeAll(p.getPaquetOuvriers());
		p.getPiocheOuvriers().removeAll(p.getPiocheOuvriers());
		j1.setEcus(0);
		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()), "ContracterEmprunt");
		p.getPaquetBatiments().add(new Batiment("Bat 1", 0, 0, 0, 0, 0, 0));
		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()), "OuvrirChantier");
		p.getPaquetBatiments().removeAll(p.getPiocheBatiments());
		p.ajouterReserveEcus(5);
		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()),"PrendreEcus");
		p.sousReserveEcus(5);
		j1.addOuvrier(new Esclave("Esclave 1", 0, 0, 0, 0, 0));
		j1.addBatiment(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
		p.getPaquetBatiments().remove(0);
		
		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()), "EnvoyerTravailler");
		j1.getBatiments().removeAll(j1.getBatiments());
		p.getPaquetEsclaves().removeAll(p.getPaquetEsclaves());
		iatest.resetGI();
		p.getPaquetUniversite().add(new Universite("Univ", 0, 0, 0, 0, 0));
		j1.addOuvrier(new Ouvrier("Ouvrier ", 0, 0, 0, 0, 0));
		j1.ajoutNbEcus(7);
		assertEquals(iatest.choixAction(p, j1.getOutils(), j1.getOuvriers(), j1.getBatiments(), j1.getNbEcus(), j1.getPtsVictoire(), 3,new Random()), "InstruireOuvrier");

		
		

	}

}

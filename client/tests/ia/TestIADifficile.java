package ia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;

import cartes.*;
import infojeu.InfoJoueur;
import infojeu.InfoPlateau;

public class TestIADifficile {

	@Test
	public void TestPoidsPrendreEcus() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		assertEquals(gIA.poidsPrendreEcus(p, j.getNbEcus(), j.getPtsVictoire(), 3), 3);

		p.sousReserveEcus(40);
		assertEquals(gIA.poidsPrendreEcus(p, j.getNbEcus(), j.getPtsVictoire(), 1), -1);
		js.get(0).ajouterPtsVictoire(16);
		js.get(0).ajoutNbEcus(-10);

		js.get(0).ajoutNbEcus(8);
		p.ajouterReserveEcus(40);
		assertEquals(gIA.poidsPrendreEcus(p, j.getNbEcus(), j.getPtsVictoire(), 1), 3);
		assertEquals(gIA.poidsPrendreEcus(p, j.getNbEcus(), j.getPtsVictoire(), 2), 2);
		js.get(0).ajoutNbEcus(-2);
		assertTrue(gIA.poidsPrendreEcus(p, j.getNbEcus(), j.getPtsVictoire(), 1) == 3);

	}

	@Test
	public void TestPoidsEnvoyerOuvrierTravailler() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		assertEquals(gIA.poidsEnvoyerOuvrierTravailler(j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1), -1);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1));
		assertEquals(gIA.poidsEnvoyerOuvrierTravailler(j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1), -1);
		j.getBatiments().add(new Batiment("Batiment 1", 1, 1, 1, 1, 1, 0));
		assertEquals(gIA.poidsEnvoyerOuvrierTravailler(j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1), 3);
		j.ajoutNbEcus(-40);
		assertEquals(gIA.poidsEnvoyerOuvrierTravailler(j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1), -1);

	}

	@Test
	public void TestPoidsRecruter() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		ArrayList<Ouvrier> ouvriers = new ArrayList<Ouvrier>();
		assertEquals(gIA.poidsRecruter(ouvriers, j.getOuvriers()), -1);
		Ouvrier o = new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		assertEquals(gIA.poidsRecruter(j.getOuvriers(), j.getOuvriers()), 3);
		j.addOuvrier(o);
		assertEquals(gIA.poidsRecruter(j.getOuvriers(), j.getOuvriers()), 0);

	}

	@Test
	public void TestPoidsOuvrirChantier() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		ArrayList<Batiment> batiments = new ArrayList<Batiment>();
		assertEquals(gIA.poidsOuvrirChantier(batiments, j.getOuvriers(), j.getBatiments()), -1);
		batiments.add(new Batiment("Batiment 1", 1, 1, 1, 1, 1, 0));
		assertEquals(gIA.poidsOuvrirChantier(batiments, j.getOuvriers(), j.getBatiments()), 0);
		batiments.add(new Machine("Machine 1", 1, 1, 1, 1, 1, 0, null));
		assertEquals(gIA.poidsOuvrirChantier(batiments, j.getOuvriers(), j.getBatiments()), 0);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 0));
		assertEquals(gIA.poidsOuvrirChantier(batiments, j.getOuvriers(), j.getBatiments()), 1);

	}

	@Test
	public void TestPoidsAchatAction() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		j.ajoutNbEcus(-10);
		assertEquals(gIA.poidsAchatAction(j.getOuvriers(), j.getBatiments(), j.getNbEcus(), j.getPtsVictoire(), 1), -1);
		j.ajoutNbEcus(20);
		assertEquals(gIA.poidsAchatAction(j.getOuvriers(), j.getBatiments(), j.getNbEcus(), j.getPtsVictoire(), 1), 0);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1));
		Batiment b = new Batiment("Batiment 1", 6, 5, 2, 1, 1, 0);
		j.getBatiments().add(b);
		j.getBatiments().add(b);

		assertEquals(gIA.poidsAchatAction(j.getOuvriers(), j.getBatiments(), j.getNbEcus(), j.getPtsVictoire(), 0), 3);
		j.ajouterPtsVictoire(16);

		assertEquals(gIA.poidsAchatAction(j.getOuvriers(), j.getBatiments(), j.getNbEcus(), j.getPtsVictoire(), 0), 2);

	}

	@Test
	public void testChoixAction() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		j.ajoutNbEcus(-10);
		p.ajouterReserveEcus(-40);
		j.getOuvriers().clear();
		p.getPaquetBatiments().removeAll(p.getPaquetBatiments());
		assertEquals(j.getOuvriers().size(), 0);
		
		Ouvrier o = new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1);
		p.getPaquetOuvriers().add(o);
		
		gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(), j.getPtsVictoire(), 1,
				new Random());
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1, new Random()), "RecruterOuvrier");
		p.getPaquetOuvriers().removeAll(p.getPaquetOuvriers());
		p.getPiocheOuvriers().removeAll(p.getPiocheOuvriers());
		p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
		p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
		p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1, new Random()), "OuvrirChantier");
		p.getPaquetBatiments().removeAll(p.getPaquetBatiments());
		p.getPiocheBatiments().removeAll(p.getPiocheBatiments());
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1, new Random()), "FinirTour");
		p.ajouterReserveEcus(10);
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1, new Random()), "PrendreEcus");
		j.addOuvrier(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1));
		j.getBatiments().add(new Batiment("Batiment 1", 0, 0, 1, 0, 1, 7));
		p.sousReserveEcus(10);
		j.ajoutNbEcus(1);
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 1, new Random()), "EnvoyerTravailler");
		j.ajoutNbEcus(5);
		j.ajouterPtsVictoire(16);
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 0, new Random()), "AcheterAction");
		j.ajouterPtsVictoire(-1);
		assertEquals(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
				j.getPtsVictoire(), 0, new Random()), "AcheterAction");

	}

}

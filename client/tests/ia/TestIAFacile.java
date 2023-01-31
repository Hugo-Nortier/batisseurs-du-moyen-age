package ia;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import cartes.Batiment;
import cartes.Machine;
import cartes.Ouvrier;
import infojeu.InfoJoueur;
import infojeu.InfoPlateau;

public class TestIAFacile {

	@Test
	public void TestPoidsPrendreEcus() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		assertEquals(iatest.poidsPrendreEcus(p, j.getNbEcus(),j.getPtsVictoire(), 3),3);

		p.sousReserveEcus(40);
		assertEquals(iatest.poidsPrendreEcus(p, j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		js.get(0).ajouterPtsVictoire(16);
		js.get(0).ajoutNbEcus(-10);

		js.get(0).ajoutNbEcus(8);
		p.ajouterReserveEcus(40);
		assertEquals(iatest.poidsPrendreEcus(p, j.getNbEcus(),j.getPtsVictoire(), 1),3);
		assertEquals(iatest.poidsPrendreEcus(p, j.getNbEcus(),j.getPtsVictoire(), 2),3);
		js.get(0).ajoutNbEcus(-2);
		assertTrue(iatest.poidsPrendreEcus(p, j.getNbEcus(),j.getPtsVictoire(), 1)==3);		
	}
	
	@Test
	public void TestPoidsEnvoyerOuvrierTravailler() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		assertEquals(iatest.poidsEnvoyerOuvrierTravailler(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		j.addOuvrier(new Ouvrier("Ouvrier 1",1,1,1,1,1));
		assertEquals(iatest.poidsEnvoyerOuvrierTravailler(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		j.getBatiments().add(new Batiment("Batiment 1",1,1,1,1,1, 0));
		assertEquals(iatest.poidsEnvoyerOuvrierTravailler(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),3);
		j.ajoutNbEcus(-40);
		assertEquals(iatest.poidsEnvoyerOuvrierTravailler(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		
	}
	@Test
	public void TestPoidsRecruter() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		ArrayList<Ouvrier> ouvriers=new ArrayList<Ouvrier>();
		assertEquals(iatest.poidsRecruter(ouvriers, j.getOuvriers()),-1);
		Ouvrier o=new Ouvrier("Ouvrier 1",1,1,1,1,1);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		j.addOuvrier(o);
		assertEquals(iatest.poidsRecruter(j.getOuvriers(), j.getOuvriers()),3);
		j.addOuvrier(o);
		assertEquals(iatest.poidsRecruter(j.getOuvriers(), j.getOuvriers()),-1);

		
	}
	@Test
	public void TestPoidsOuvrirChantier() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		ArrayList<Batiment>batiments=new ArrayList<Batiment>();
		assertEquals(iatest.poidsOuvrirChantier(batiments, j.getOuvriers(),j.getBatiments()),-1);
		batiments.add(new Batiment("Batiment 1",1,1,1,1,1, 0));
		assertEquals(iatest.poidsOuvrirChantier(batiments, j.getOuvriers(),j.getBatiments()),-1);
		batiments.add(new Machine("Machine 1",1,1,1,1,1,0, null));
		assertEquals(iatest.poidsOuvrirChantier(batiments, j.getOuvriers(),j.getBatiments()),-1);
		j.addOuvrier(new Ouvrier("Ouvrier 1",1,1,1,1, 0));
		assertEquals(iatest.poidsOuvrirChantier(batiments, j.getOuvriers(),j.getBatiments()),3);

	
		
	}
	@Test
	public void TestPoidsAchatAction() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
		js.add(j);
		j.ajoutNbEcus(-10);
		assertEquals(iatest.poidsAchatAction(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		j.ajoutNbEcus(20);
		assertEquals(iatest.poidsAchatAction(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1),-1);
		j.addOuvrier(new Ouvrier("Ouvrier 1",1,1,1,1,1));
		Batiment b=new Batiment("Batiment 1",6,5,2,1,1, 0);
		j.getBatiments().add(b);
		j.getBatiments().add(b);

		assertEquals(iatest.poidsAchatAction(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 0),3);
		j.ajouterPtsVictoire(16);
	 
		assertEquals(iatest.poidsAchatAction(j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 0),3);
		
	}

	@Test
	public void testChoixAction() {
		InfoJoueur j = new InfoJoueur();
		IAFacile iatest = new IAFacile();
		j.setEcus(10);
		ArrayList<InfoJoueur> joueurs = new ArrayList<>();
		InfoPlateau p = new InfoPlateau();
		p.ajouterReserveEcus(40);
	joueurs.add(j);
	j.ajoutNbEcus(-10);
	p.ajouterReserveEcus(-40);
	j.getOuvriers().clear();
	p.getPaquetBatiments().removeAll(p.getPaquetBatiments());
	Random mocks = mock(Random.class);

	when((Integer)mocks.nextInt(2)).thenReturn(1);

	assertEquals(j.getOuvriers().size(),0);
	p.getPaquetOuvriers().add(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1));
	iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks);
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks),"RecruterOuvrier");
	p.getPaquetOuvriers().removeAll(p.getPaquetOuvriers());
	p.getPiocheOuvriers().removeAll(p.getPiocheOuvriers());
	p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
	p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
	p.getPaquetBatiments().add(new Batiment("Batiment 1", 0, 0, 0, 0, 0, 0));
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks),"FinirTour");
	p.getPaquetBatiments().removeAll(p.getPaquetBatiments());
	p.getPiocheBatiments().removeAll(p.getPiocheBatiments());
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks),"FinirTour");
	p.ajouterReserveEcus(10);
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks),"PrendreEcus");
	j.addOuvrier(new Ouvrier("Ouvrier 1", 1, 1, 1, 1, 1));
	j.getBatiments().add(new Batiment("Batiment 1", 0, 0, 1, 0, 1, 7));
	p.sousReserveEcus(10);
	j.ajoutNbEcus(1);
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 1,mocks),"EnvoyerTravailler");
	j.ajoutNbEcus(5);
j.ajouterPtsVictoire(16);	
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 0,mocks),"FinirTour");
	j.ajouterPtsVictoire(-1);
	assertEquals(iatest.choixAction(p,j.getOutils(), j.getOuvriers(),j.getBatiments(),j.getNbEcus(),j.getPtsVictoire(), 0,mocks),"FinirTour");
		
		}


	
}

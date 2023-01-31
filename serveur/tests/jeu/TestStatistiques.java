package jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Random;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

import action.AcheterAction;
import action.AcheterEsclave;
import action.AcheterOutil;
import action.Action;
import action.DonnerOutil;
import action.EnvoyerTravailler;
import action.InstruireOuvrier;
import action.OuvrirChantier;
import action.PrendreEcus;
import action.RecruterOuvrier;
import cartes.Batiment;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;

public class TestStatistiques {
	@Test
	public void testUpdateOuvrirBat() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new OuvrirChantier();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		InfoRepetition ir = new InfoRepetition();
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getnbOuvrirBatiment(), 1);
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getnbOuvrirBatiment(), 2);

	}

	@Test
	public void testUpdateRecruter() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new RecruterOuvrier();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		InfoRepetition ir = new InfoRepetition();

		p.initPartie(joueurs);
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getRecruter(), 1);

	}

	@Test
	public void testUpdateEnvoyerTravailler() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new EnvoyerTravailler();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		InfoRepetition ir = new InfoRepetition();

		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 1, 4, 0, 0));
		j.addBatiment(new Batiment("Batiment 1", 2, 0, 0, 4, 0, 0));
		p.initPartie(joueurs);
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getEnvoyerTravailler(), 1);
	}

	@Test
	public void testUpdatePrendreEcu() {
		int action = 3;
		Random mocks = mock(Random.class);

		when((Integer) mocks.nextInt(action) + 1).thenReturn(1);
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new PrendreEcus();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 1, 4, 0, 0));
		j.addBatiment(new Batiment("Batiment 1", 2, 0, 0, 4, 0, 0));
		p.initPartie(joueurs);
		InfoRepetition ir = new InfoRepetition();

		act.effectuerAction(p, j, mocks, 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getPrendreEcus(), 3);
		when((Integer) mocks.nextInt(action) + 1).thenReturn(3);
		act.effectuerAction(p, j, mocks, 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getPrendreEcus(), 9);
		// when((Integer)mocks.nextInt(action)).thenReturn(2);
		act.effectuerAction(p, j, mocks, 3, a, ir, new ChoixCartes());

		assertEquals(j.getStats().getPrendreEcus(), 15);

	}

	@Test
	public void testUpdateAchatAction() {

		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new AcheterAction();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		InfoRepetition ir = new InfoRepetition();

		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 1, 4, 0, 0));
		j.addBatiment(new Batiment("Batiment 1", 2, 0, 0, 4, 0, 0));
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		p.initPartie(joueurs);

		assertEquals(j.getStats().getAchatAction(), 1);

	}

	@Test
	public void testUpdateDonnerOutil() {

		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new DonnerOutil();
		Plateau p = new Plateau();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		InfoRepetition ir = new InfoRepetition();

		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 1, 4, 0, 0));
		j.addOutil(new Outil("Outil 1", 0, 4, 0, 0, 0));
		j.addBatiment(new Batiment("Batiment 1", 2, 0, 0, 4, 0, 0));
		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());

		assertEquals(j.getOuvriers().get(0).getRessources().getBois(), 5);

	}

	@Test
	public void testUpdateAchatEsclave() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new AcheterEsclave();
		PlateauAntiquite p = new PlateauAntiquite();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		p.initPartie(joueurs);
		InfoRepetition ir = new InfoRepetition();

		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getAchatEsclave(), 1);

	}

	@Test
	public void testUpdateInstruireOuvrier() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new InstruireOuvrier();
		PlateauAntiquite p = new PlateauAntiquite();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 0, 0, 0, 0));
		p.initPartie(joueurs);
		InfoRepetition ir = new InfoRepetition();

		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getInstruireOuvrier(), 1);

	}

	@Test
	public void testUpdateAchatOutil() {
		Affichage a = new Affichage("MOYENAGE", 0);
		Joueur j = new Joueur(0, "joueur 1", a);
		Action act = new AcheterOutil();
		PlateauAntiquite p = new PlateauAntiquite();
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(j);
		j.addOuvrier(new Ouvrier("Ouvrier 1", 0, 0, 0, 0, 0));
		p.initPartie(joueurs);
		InfoRepetition ir = new InfoRepetition();

		act.effectuerAction(p, j, new Random(), 3, a, ir, new ChoixCartes());
		assertEquals(j.getStats().getAchatOutil(), 1);

	}

	@Test
	public void testAdditionnerStat() {
		Statistiques s = new Statistiques();
		s.additionnerStat(new Statistiques());
		assertEquals(s.getAchatAction(), 0);
		Statistiques s2 = new Statistiques();
		s2.updateAchatOutil();
		s.additionnerStat(s2);
		assertEquals(s.getAchatOutil(), 1);
		s.updateAchatOutil();
		s.additionnerStat(s2);
		assertEquals(s.getAchatOutil(), 3);

	}

	@Test
	public void testupdateMachineConstruire() {
		Affichage a = new Affichage("MOYENAGE",0);
		Joueur j = new Joueur(0, "joueur 1", a);
		
		assertEquals(j.getStats().getMachineConstruite(), 0);
		j.getStats().updateMachineConstruire();
		assertEquals(j.getStats().getMachineConstruite(), 1);
	}
	
	@Test
	public void testupdateGagnant() {
		Affichage a = new Affichage("MOYENAGE",0);
		Joueur j = new Joueur(0, "joueur 1", a);
		
		assertEquals(j.getStats().getGagnant(),0);
		j.getStats().updateGagnant();
		assertEquals(j.getStats().getGagnant(),1);
	}
}

package ia;

import static org.junit.jupiter.api.Assertions.assertEquals;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

import cartes.Batiment;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.InfoJoueur;


public class TestGestionnaireIA {
	@Test
	public void testChoixOuvrier() {
		Ouvrier o=new Ouvrier("Ouvrier 1",1,1,1,1,1);
		Ouvrier o2=new Ouvrier("Ouvrier 2",1,1,2,1,1);
		Ouvrier o3=new Ouvrier("Ouvrier 3",1,4,2,1,1);


		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoJoueur j = new InfoJoueur();
		js.add(j);
		j.addOuvrier(o);
		Random mocks = mock(Random.class);
		GestionnaireIA gIA = new IADifficile();
		when((Integer)mocks.nextInt(2)).thenReturn(0);
		assertEquals(gIA.choixOuvrier(j.getOuvriers(), mocks),o);
		j.addOuvrier(o2);
		j.addOuvrier(o3);
		when((Integer)mocks.nextInt(2)).thenReturn(1);
		assertEquals(gIA.choixOuvrier(j.getOuvriers(), mocks),o2);
		when((Integer)mocks.nextInt(2)).thenReturn(0);
		assertEquals(gIA.choixOuvrier(j.getOuvriers(), mocks),o3);



	}
	
	@Test
	public void testChoixBatiment() {

		Batiment o=new Batiment("batiment 1",1,1,1,1,1, 0);
		Batiment o2=new Batiment("batiment 2",1,1,2,1,1,3);
		Batiment o3=new Batiment("batiment 3",1,4,2,1,1,1);


		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoJoueur j = new InfoJoueur();
		js.add(j);
		
		j.getBatiments().add(o);

		Random mocks = mock(Random.class);
		GestionnaireIA gIA = new IADifficile();

		when((Integer)mocks.nextInt(2)).thenReturn(0);
		assertEquals(gIA.choixBatiment(j.getBatiments(), mocks),o);
		j.getBatiments().add(o2);

		when((Integer)mocks.nextInt(2)).thenReturn(1);
		assertEquals(gIA.choixBatiment(j.getBatiments(), mocks),o2);
		j.getBatiments().add(o3);
		when((Integer)mocks.nextInt(2)).thenReturn(0);

		assertEquals(gIA.choixBatiment(j.getBatiments(), mocks),o2);
		when((Integer)mocks.nextInt(2)).thenReturn(1);
		assertEquals(gIA.choixBatiment(j.getBatiments(), mocks),o);

	}
	
	@Test
	public void testOuvrierTravailler() {
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IADifficile();
		j.setEcus(10);
		Batiment b = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		Ouvrier o = new Ouvrier("Ouvrier 1", 2000, 200, 3, 1000, 4);
		Ouvrier o1 = new Ouvrier("Ouvrier 2", 2000, 200, 3, 1000, 4);
		Ouvrier o2 = new Ouvrier("Ouvrier 3", 2000, 200, 3, 1000, 4);
		Ouvrier o3 = new Ouvrier("Ouvrier 4", 25000, 10200, 250, 14700, 10);
		Ouvrier o4 = new Ouvrier("Ouvrier 5", 25000, 10200, 250, 14700, 0);
		j.addOuvrier(o);
		j.addOuvrier(o1);
		j.addOuvrier(o2);
		//on renvoit le premier des ouvriers qui a des bonnes stat par rapport au batiment dans l'ordre si ils ont les memes stat
		assertEquals(gIA.ouvrierTravailler(j.getOuvriers(),j.getNbEcus(),b),o);
		j.addOuvrier(o3);
		//on renvoit le meilleur ouvrier niveau ressources maintenant qu'il y en a un meilleur
		assertEquals(gIA.ouvrierTravailler(j.getOuvriers(),j.getNbEcus(),b),o3);
		j.addOuvrier(o4);
		//on renvoit le meilleur ouvrier niveau cout de travail maintenant qu'il y en a un meilleur mais avec les memes resources que le precedent
		assertEquals(gIA.ouvrierTravailler(j.getOuvriers(),j.getNbEcus(),b),o4);
	}
	
	@Test
	public void testBatimentTravailler() { 
		InfoJoueur j = new InfoJoueur();
		GestionnaireIA gIA = new IAFacile();
		j.setEcus(10);

		Ouvrier ouvrier = new Ouvrier("Hector le raptor", 23000, 9900, 400, 1000, 4);
		Batiment eglise = new Batiment("eglise", 25000, 10200, 250, 14700, 10, 15);
		Batiment moulin = new Batiment("moulin",19000,7500,150,7500,5,10);
		Batiment maconnerie = new Batiment("maconnerie",19000,7500,150,7500,4,10);
		Batiment boulangerie = new Batiment("boulangerie",12000,15000,125,8500,5,8);
		Batiment armurerie = new Batiment("armurerie",2,100,35,20,8,10);
		Batiment chateauLuxueux = new Batiment("chateau luxueux",45000,45000,550,69000,20,20);
		j.addOuvrier(ouvrier);
		j.getBatiments().add(moulin);
		j.getBatiments().add(eglise);
		j.getBatiments().add(maconnerie);
		j.getBatiments().add(boulangerie);
		j.getBatiments().add(armurerie);
		j.getBatiments().add(chateauLuxueux);
		assertEquals(gIA.batimentTravailler(j.getBatiments(), 1), armurerie);
	}
	@Test
	public void testChoixOuvrierInstruire() { 
		GestionnaireIA gIA = new IAFacile();
		ArrayList<Ouvrier>o=new ArrayList<Ouvrier>();
		o.add(new Ouvrier("o1", 0, 0, 0, 0, 0));
		assertEquals(gIA.choixOuvrierInstruire(o).getRessources().getNom(),"o1");
		
	}
	@Test
	public void testChoixOutil() { 
		GestionnaireIA gIA = new IAFacile();
		ArrayList<Outil>o=new ArrayList<Outil>();
		o.add(new Outil("o1", 0, 0, 0, 0, 0));
		ArrayList<Batiment>b=new ArrayList<Batiment>();
		ArrayList<Ouvrier>o2=new ArrayList<Ouvrier>();


		assertEquals(gIA.choixOutil(b,o2,o).getRessources().getNom(),"o1");
		
	}
	@Test
	public void testChoixEsclave() { 
		GestionnaireIA gIA = new IAFacile();
		ArrayList<Esclave>o=new ArrayList<Esclave>();
		o.add(new Esclave("o1", 0, 0, 0, 0, 0));
		assertEquals(gIA.choixEsclave(o).getRessources().getNom(),"o1");
		
	}

}

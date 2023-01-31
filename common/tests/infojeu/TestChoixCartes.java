package infojeu;

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
import cartes.Universite;






public class TestChoixCartes {
	@Test
	public void testBatimentTravailler() { 
		InfoJoueur j = new InfoJoueur();
		ChoixCartes gIA = new ChoixCartes();
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
	public void testOuvrierTravailler() { //Changer
		InfoJoueur j = new InfoJoueur();
		ChoixCartes gIA = new ChoixCartes();
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
	public void testChoixBatiment() {

		Batiment o=new Batiment("batiment 1",1,1,1,1,1, 0);
		Batiment o2=new Batiment("batiment 2",1,1,2,1,1,3);
		Batiment o3=new Batiment("batiment 3",1,4,2,1,1,1);


		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoJoueur j = new InfoJoueur();
		js.add(j);
		
		j.getBatiments().add(o);

		Random mocks = mock(Random.class);
		ChoixCartes gIA = new ChoixCartes();

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
	public void testChoixOuvrier() {
		Ouvrier o=new Ouvrier("Ouvrier 1",1,1,1,1,1);
		Ouvrier o2=new Ouvrier("Ouvrier 2",1,1,2,1,1);
		Ouvrier o3=new Ouvrier("Ouvrier 3",1,4,2,1,1);


		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoJoueur j = new InfoJoueur();
		js.add(j);
		j.addOuvrier(o);
		Random mocks = mock(Random.class);
		ChoixCartes gIA = new ChoixCartes();
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
	public void testChoixOuvrierInstruire() {
		Ouvrier o=new Ouvrier("Ouvrier 1",1,1,1,1,1);
		Ouvrier o2=new Ouvrier("Ouvrier 2",1,1,2,1,1);
		Ouvrier o3=new Ouvrier("Ouvrier 3",1,4,2,1,1);


		ArrayList<InfoJoueur> js = new ArrayList<>();
		InfoJoueur j = new InfoJoueur();
		js.add(j);
		j.addOuvrier(o);
		ChoixCartes gIA = new ChoixCartes();
		assertEquals(gIA.choixOuvrierInstruire(j.getOuvriers()),j.getOuvriers().get(0));
		j.addOuvrier(o2);
		j.addOuvrier(o3);
		assertEquals(gIA.choixOuvrierInstruire(j.getOuvriers()),j.getOuvriers().get(2));


	}
	@Test
	public void testChoixEsclave() {
		ChoixCartes gIA = new ChoixCartes();
		Esclave o = new Esclave("e 1", 2000, 200, 3, 1000, 4);
		Esclave o1 = new Esclave("e 2", 2000, 200, 3, 1000, 4);
		Esclave o2 = new Esclave("e 3", 2000, 200, 3, 1000, 4);
		Esclave o3 = new Esclave("e 4", 25000, 10200, 250, 14700, 10);
		ArrayList<Esclave>esclaves=new ArrayList<Esclave>();
		esclaves.add(o);
		esclaves.add(o1);
		esclaves.add(o2);
		assertEquals(gIA.choixEsclave(esclaves),o);
		esclaves.add(o3);
		assertEquals(gIA.choixEsclave(esclaves),o3);
	

	}
	
	@Test
	public void testChoixUniv() {
ArrayList<Universite>univs=new ArrayList<Universite>();	
univs.add(new Universite("Univ1", 1, 2, 4, 4, 4));
univs.add(new Universite("Univ1", 1, 2, 4, 7, 4));
univs.add(new Universite("Univ2", 1, 2, 4, 4, 4));
univs.add(new Universite("Univ1", 1, 4, 4, 4, 4));
ChoixCartes cc=new ChoixCartes();
Random mocks = mock(Random.class);
when((Integer)mocks.nextInt(univs.size())).thenReturn(0);
assertEquals( cc.choixUniversite(univs, mocks),univs.get(0));




	}
	@Test
	public void testChoixOutil() {
ArrayList<Outil>outil=new ArrayList<Outil>();	
outil.add(new Outil("o1", 1, 0, 1, 0, 0));
outil.add(new Outil("o2", 0, 0, 0, 3, 0));
outil.add(new Outil("o3", 0, 5, 0, 0, 4));
outil.add(new Outil("o4", 1, 4, 4, 5, 0));
ArrayList<Ouvrier>ouvriers=new ArrayList<Ouvrier>();	

ouvriers.add(new Ouvrier("Ouvrier 1",1,1,1,1,1));
ouvriers.add(new Ouvrier("Ouvrier 2",1,1,2,1,1));
ouvriers.add(new Ouvrier("Ouvrier 3",1,4,2,1,1));

ArrayList<Batiment>batiments=new ArrayList<Batiment>();	
batiments.add(new Batiment("Bat", 1, 5, 8, 9, 1, 3));
ChoixCartes cc=new ChoixCartes();
assertEquals(cc.choixOutil(batiments, ouvriers, outil).getRessources().getNom(),"o2");
ArrayList<Outil>outil2=new ArrayList<Outil>();	
outil2.add(new Outil("o4", 3, 4, 4, 5, 16));

assertEquals(cc.choixOutil(batiments, ouvriers, outil2).getRessources().getNom(),"o4");
batiments.removeAll(batiments);
batiments.add(new Batiment("Bat", 9, 5, 8, 9, 1, 3));
assertEquals(cc.choixOutil(batiments, ouvriers, outil2).getRessources().getNom(),"o4");



	}
}

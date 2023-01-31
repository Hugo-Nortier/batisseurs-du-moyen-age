package jeu;
import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.*;



public class TestGestionnaireJeu {
	@Test
	public void testInit() {
		String antiquite = "ANTIQUITE";
		String bidule = "BIDULE";
		GestionnaireJeu gj = new GestionnaireJeu(antiquite,2);
		assertEquals(gj.getTypepartie(),antiquite);
		gj = new GestionnaireJeu(bidule, 2);
		assertEquals(gj.getTypepartie(),"MOYENAGE");
	}
	
	@Test
	public void testAddJoueurs() {
		GestionnaireJeu gj = new GestionnaireJeu("ANTIQUITE", 2);
		ArrayList<Joueur> joueurs = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			joueurs.add(new Joueur(i, "ANTIQUITE", gj.getA()));
		gj.addJoueurs(joueurs);
		assertEquals(gj.getJoueurs().size(),4);
	}
	
	@Test
	public void testUpdateGrandGagnants() {
		HashMap<Integer,Statistiques> grandgagnants = new HashMap<>();
		for (int i = 0; i < 4; i++)
			grandgagnants.put(i, new Statistiques());
		GestionnaireJeu gj = new GestionnaireJeu("ANTIQUITE", 2);
		ArrayList<Joueur> joueurs = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			joueurs.add(new Joueur(i,"ANTIQUITE", gj.getA()));
		//TODO : gj.lancerPartie(joueurs);
		gj.updateGrandGagnants(grandgagnants);
		boolean changer = false;
		for (int i = 0; i < 4; i++) {
			if(grandgagnants.get(i).getGagnant()>0)
				changer=true;
		}
		assertEquals(changer,false);
	}
	
	@Test
	public void testGestionnaireJeu() {
		GestionnaireJeu gj = new GestionnaireJeu("ANTIQUITE",2,2);
		assertEquals(gj.getTypepartie(),"ANTIQUITE");
		
		GestionnaireJeu gk = new GestionnaireJeu("MOYENAGE",1,2);
		assertEquals(gk.getTypepartie(),"MOYENAGE");
		//gj.run();
		
	}
	
	/*@Test
	public void testaddClient() throws IOException {
		GestionnaireJeu gj = new GestionnaireJeu("ANTIQUITE",2,2);
		ArrayList<ClientJoueur> cjs = null;
		String tp = "ANTIQUITE";
		
		ServerSocket ss = new ServerSocket(8888);
		Socket socket = ss.accept();
		
		InputStream is = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(tp);
		for(int i = 0 ; i < 4 ; i++ ) {
		cjs.add(new ClientJoueur(oos, ois, socket, new Joueur(i+1, tp,  new Affichage(tp, 1))));
		assertEquals(cjs.get(i),new ClientJoueur(oos, ois, socket, new Joueur(i+1, tp,  new Affichage(tp, 1))));
		}
	}*/
}

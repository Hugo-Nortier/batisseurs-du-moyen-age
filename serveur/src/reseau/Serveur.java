package reseau;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import jeu.Affichage;
import jeu.GestionnaireJeu;
import jeu.Joueur;
import jeu.Statistiques;

/**
 * Serveur du jeu
 * 
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 *         NORTIER Hugo
 *
 */
public class Serveur {
	/**
	 * Main qui fait tourner le programme
	 * 
	 * @param args argument qui permet de choisir un mode et 500 parties ou non
	 * @throws IOException : Lève une exception s'il y a une erreur réseau
	 * @throws ClassNotFoundException : Lève une exception si la classe n'est pas trouvée
	 * @throws InterruptedException : Exception si une execution est interrompue
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		int nbj = 4;
		int nbp = 1;
		String tp = "MOYENAGE";
		ServerSocket ss = new ServerSocket(8888);
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("antiquite"))
				tp = "ANTIQUITE";
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("antiquite"))
				tp = "ANTIQUITE";
			try {
				nbp = Integer.parseInt(args[1]);
			} catch (NumberFormatException nfe) {
				nbp = 1;
			}
		}
		if (args.length == 3) {
			if (args[0].equalsIgnoreCase("antiquite"))
				tp = "ANTIQUITE";
			try {
				nbp = Integer.parseInt(args[1]);
			} catch (NumberFormatException nfe) {
				nbp = 1;
			}
			try {
				nbj = Integer.parseInt(args[2]);
				if(nbj<2)
					nbj = 2;
			} catch (NumberFormatException nfe) {
				nbj = 4;
			}
		}
		ArrayList<ClientJoueur> cj = connexionJoueurs(tp, nbj, ss);
		if (nbp == 1)
			lancerPartieSimple(tp, cj, ss);
		else
			lancerMultipleParties(tp,nbp, cj, ss);
		finirJeu(cj);
		ss.close();
	}
	
	/**
	 * Permet d'envoyer un signal de fin de partie aux clients
	 * 
	 * @param cj : la liste de clients
	 */
	private static void finirJeu(ArrayList<ClientJoueur> cj) throws IOException {
		for (ClientJoueur c : cj)
			c.getOOS().writeObject("finpartie");
	}

	/**
	 * Permet la connexion des joueurs au serveur
	 * 
	 * @param tp
	 * @param nbj : nombre de joueur
	 * @param ss
	 */
	private static ArrayList<ClientJoueur> connexionJoueurs(String tp, int nbj, ServerSocket ss) {
		int nbjpasco = nbj;
		int i = 0;
		ArrayList<ClientJoueur> cj = new ArrayList<>();
		while (nbjpasco > 0) {
			Affichage.attenteJoueurs(nbjpasco);
			try {
				Socket socket = ss.accept();
				// si un joueur est connecté on affiche qu'il est connecté
				Affichage.connectionClientSuccess();
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(tp);
				cj.add(new ClientJoueur(oos, ois, socket, new Joueur(i, tp,  new Affichage(tp, 1))));
				nbjpasco--;
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Affichage.finAttenteJoueurs(nbj);
		return cj;
	}

	/**
	 * Permet de lancer une partie simple
	 * 
	 * @param tp  : nom du mode de jue
	 * @param nbj : nombre de joueur
	 * @param ss
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void lancerPartieSimple(String tp, ArrayList<ClientJoueur> cj, ServerSocket ss)
			throws ClassNotFoundException, IOException {
		GestionnaireJeu gj = new GestionnaireJeu(tp, 1);
		ArrayList<Joueur> joueurs = new ArrayList<>();
		for (ClientJoueur c : cj)
			joueurs.add(c.getJoueur());
		gj.addJoueurs(joueurs);
		gj.addClients(cj);
		gj.lancerPartie(joueurs);
	}

	/**
	 * Permet de lancer plusieurs partie
	 * 
	 * @param tp  : mode de jeu
	 * @param nbj : nombre de joueur
	 * @param nbp : nombre de parties
	 * @param ss
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void lancerMultipleParties(String tp, int nbp, ArrayList<ClientJoueur> cj, ServerSocket ss)
			throws IOException, ClassNotFoundException {
		GestionnaireJeu[] gjs = new GestionnaireJeu[nbp];
		Thread[] threads = new Thread[nbp];
		HashMap<Integer, Statistiques> grandgagnants = new HashMap<>();
		int nbj = cj.size();

		for (int i = 0; i < nbj; i++) {
			grandgagnants.put(i, new Statistiques());
		}
		Affichage.debutMultiParties(nbp);
		for (int index = 0; index < nbp; index++) {
			ArrayList<Joueur> joueurs = new ArrayList<>();
			ArrayList<ClientJoueur> clientsjoueur = new ArrayList<>();
			GestionnaireJeu gj = new GestionnaireJeu(tp, 2, index+1);
			for (ClientJoueur c : cj) {
				Joueur j = new Joueur(c.getJoueur().getNb(), tp, gj.getA());
				joueurs.add(j);
				clientsjoueur.add(new ClientJoueur(c.getOOS(), c.getOIS(), c.getSocket(), j));
			}
			gj.addJoueurs(joueurs);
			gj.addClients(clientsjoueur);
			gjs[index] = gj;
			threads[index] = new Thread(gjs[index]);
			threads[index].start();
			;
		}
		boolean isProcessing = false;
		do {
			isProcessing = false;
			for (Thread t : threads) {
				if (t.isAlive()) {
					isProcessing = true;
					break;
				}
			}
		} while (isProcessing);

		for (int index = 0; index < nbp; index++)
			gjs[index].updateGrandGagnants(grandgagnants);

		gjs[0].getA().affichageMultiParties(grandgagnants, nbp);

	}

}

package reseau;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Random;

import ia.GestionnaireIA;
import ia.IADifficile;
import ia.IADifficileAntiquite;
import ia.IAFacile;
import ia.IAFacileAntiquite;
import infojeu.*;
/**
 * Le client qui va vouloir lancer une partie
 *  @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class Client {
	/**
	 * Main qui fait tourner le programme
	 * 
	 * @param args argument qui permet de choisir les param du joueur
	 * @throws IOException : Leve une exception s'il y a une erreur réseau
	 * @throws ClassNotFoundException : Prend en compte l'erreur d'une classe non trouvé
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try {
		Socket socket = new Socket("localhost", 8888);
		
		System.out.println("Connecté à une partie!");
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		int difficulte = new Random().nextInt(2);
		afficherDifficulte(difficulte);
		InputStream is = socket.getInputStream();
		
		ObjectInputStream ois = new ObjectInputStream(is);
		String jeu = (String) ois.readObject();
		System.out.println("Reception type jeu reussi : " + jeu);
		GestionnaireIA gIA = creationIA(difficulte, jeu);
		boolean partie = true;
		InfoPlateau p = null;
		InfoJoueur j = null;
		HashMap<String, Info> infospartie = null;
		System.out.println("En attente de signal du serveur... ");
		while (partie) {
			Object res = ois.readObject();
			if (res instanceof HashMap) {
				infospartie = (HashMap<String, Info>)res;
				j=(InfoJoueur)infospartie.get("joueur");
				p=(InfoPlateau)infospartie.get("plateau");
				gIA.setInfoRepetition((InfoRepetition)infospartie.get("repetition"));
				oos.writeObject(gIA.choixAction(p, j.getOutils(), j.getOuvriers(), j.getBatiments(), j.getNbEcus(),
						j.getPtsVictoire(), j.getNbAction(),new Random()));
				oos.reset();
				oos.writeObject(gIA.getInfoRepetition());
				oos.reset();
			}
			if (res instanceof String) {
				switch ((String) res) {
				case "fintour":
					gIA.resetGI();
					break;
				case "finpartie":
					partie = false;
				}
				oos.reset();
			}
		}
		System.out.println("Partie terminée, fin d'exécution");
		socket.close();
		}
		catch(ConnectException e) {
			System.out.println("Serveur inactif! Connexion impossible!");
		}
		catch(SocketException e) {
			System.out.println("Serveur déjà occupé dans une partie!");
		}
	}
	
	/**
	 * méthode pour afficher la difficulté choisie aléatoirement du client
	 * 
	 * @param difficulte : difficulté du client
	 */
	private static void afficherDifficulte(int difficulte) {
		switch(difficulte) {
		case 1 :
			System.out.println("Ce client possède une IA de difficulté DIFFICILE");
			break;
		case 0 : 
			System.out.println("Ce client possède une IA de difficulté FACILE");
		}
		
	}
	
	/**
	 * méthode pour afficher la difficulté choisie aléatoirement du client
	 * 
	 * @param difficulte : difficulté du client
	 * @param typejeu : type de jeu, soit antiquité soit moyenage
	 * @return une IA correspondante au type de jeu et la difficulté du client
	 */

	public static GestionnaireIA creationIA(int difficulte, String typejeu) {
		switch (difficulte) {
		case 1:
			if (typejeu.equals("ANTIQUITE"))
				return new IADifficileAntiquite();
			return new IADifficile();
		default:
			if (typejeu.equals("ANTIQUITE"))
				return new IAFacileAntiquite();
			return new IAFacile();
		}
	}
}

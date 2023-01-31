package reseau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import jeu.Joueur;

/**
 * Classe permettant de lier le joueur au client
 *  @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class ClientJoueur {
	Socket socket;
	ObjectOutputStream os;
	ObjectInputStream is;
	Joueur joueur;
/**
 * 
 * @param oos : Objet à envoyer
 * @param ois : Objet reçu
 * @param s : La socket
 * @param j : Le joueur à laquelle le client est lié
 */
	public ClientJoueur(ObjectOutputStream oos, ObjectInputStream ois, Socket s, Joueur j) {
		os = oos;
		is = ois;
		socket = s;
		joueur = j;
	}

	public Socket getSocket() {
		return socket;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public ObjectOutputStream getOOS() throws IOException {
		return os;
	}

	public ObjectInputStream getOIS() {
		return is;
	}
}

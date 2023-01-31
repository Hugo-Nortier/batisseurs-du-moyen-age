package jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;




import org.junit.jupiter.api.Test;

import reseau.ClientJoueur;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClientJoueur {
	@Test
	public void testConstructeurClientJoueur() throws UnknownHostException, IOException {
		//TODO à améliorer
		Socket socket = new Socket();
	/*	OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		InputStream is = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);*/

		ClientJoueur cj=new ClientJoueur(null, null, socket, new Joueur(0, "MOYENAGE",  new Affichage("MOYENAGE", 0)));
		assertEquals(cj.getSocket(),socket);
		assertEquals(cj.getJoueur().getNb(),0);
	}
	
}

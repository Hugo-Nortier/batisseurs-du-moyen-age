package action;

import java.util.Random;

import cartes.Outil;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * c'est une action qui permet de donner un outil
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class DonnerOutil extends Action{

	@Override 
	/**
	 * action permettant de donner un outil a un ouvrier
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur sur le plateau
	 * @param r :  Random
	 * @param totalAction : cout total
	 * @param a : Affichage
	 * @return totalAction : retourne le cout total
	 */
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		Ouvrier o =choixCartes.ouvrierTravailler(choixCartes.getOuvriers(j.getOuvriers()), j.getNbEcus(),choixCartes.batimentTravailler(j.getBatiments(), totalAction));
		Outil ot =j.getOutils().remove(0);
		o.addOutil(ot);
		a.donnerOutil(j.getNb(),o.getRessources().getNom(),ot.getRessources().getNom());
		j.getStats().updateDonnerOutil();
		return totalAction;
	}

}

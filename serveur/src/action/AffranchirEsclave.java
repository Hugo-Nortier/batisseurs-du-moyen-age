package action;

import java.util.Random;

import cartes.Esclave;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * C'est une action qui permet d'affranchr un esclave
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class AffranchirEsclave extends Action{

	@Override
	/**
	 * Action permettant d'affranchir un esclave
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur sur le plateau
	 * @param r : Random
	 * @param totalAction : cout total
	 * @param a :  Affichage
	 * @return totalAction : retourne le cout total
	 */
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a, InfoRepetition ir, ChoixCartes choixCartes) {
		Esclave e = j.getEsclaveAffranchir();
		e.affranchir();
		a.affranchirEsclave(j.getNb(),e.getRessources().getEcus());
		ir.investi();
		totalAction = totalAction - 1;
		return totalAction;
	}

}

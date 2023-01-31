package action;

import java.util.Random;

import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * Une action permettant au joueur de recruter un ouvrier
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class RecruterOuvrier extends Action{

	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return recruterOuvrier(p, j, r, totalAction, a,choixCartes);
	}
	
	/**
	 * action permettant de recruter un ouvrier
	 * @param p : un plateau pour obtenir le paquet de carte des ouvriers
	 * @param j : Joueur 
	 * @param r : un random pour pouvoir tester la méthode
	 * @param totalAction : le total d'action
	 * @param a : affichage
	 * @param choixCartes : Le choix de la carte par l'IA
	 * @return totalAction : retourne le total d'action
	 */
	public int recruterOuvrier(Plateau p, Joueur j, Random r, int totalAction, Affichage a, ChoixCartes choixCartes) {
		j.getStats().reinitialiseAchatConse();
		Ouvrier o;
		o = choixCartes.choixOuvrier(p.getPaquetOuvriers(), r);
		a.recruterOuvrier(j.getNb(), o);
		j.addOuvrier(o);
		j.getStats().updateRecruter();
		p.getPaquetOuvriers().remove(o);
		if (p.getPiocheOuvriers().size() != 0) { // Si la pioche est vide on ajoute rien
			p.getPaquetOuvriers().add(p.getPiocheOuvriers().remove(0)); // Ajout d'un ouvrier de la pioche
			totalAction = totalAction - 1;
		} else
			totalAction = totalAction - 1;
		return totalAction;
	}

}

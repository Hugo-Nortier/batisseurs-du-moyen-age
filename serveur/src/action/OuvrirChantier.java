package action;

import java.util.Random;

import cartes.Batiment;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
	/**
	 * C'est une action permettant au joueur d'ouvrir un chantier
	 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
	 *
	 */
public class OuvrirChantier extends Action {

	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return ouvrirChantier(p, j, r, totalAction, a,ir,choixCartes);
	}
	
	/**
	 * action permettant d'acheter une fonction
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur
	 * @param r : random permettant de tester la méthode
	 * @param totalAction : cout total
	 * @param a :  affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return totalAction : retourne le cout totale
	 */
	public int ouvrirChantier(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().reinitialiseAchatConse();
		Batiment b;
		b = choixCartes.choixBatiment(p.getPaquetBatiments(), r);
		j.addBatiment(b);
		j.getStats().updateOuvrirBat();
		a.ouvrirChantier(j.getNb(), b);
		p.getPaquetBatiments().remove(b);
		if (p.getPiocheBatiments().size() != 0) { // Si la pioche est vide on ajoute rien
			p.getPaquetBatiments().add(p.getPiocheBatiments().remove(0)); // Ajout d'un batiment de la pioche
			totalAction = totalAction - 1;
		} else
			totalAction = totalAction - 1;
		return totalAction;
	}

}

package action;

import java.util.*;

import cartes.Ouvrier;
import cartes.Universite;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
import jeu.PlateauAntiquite;

/**
 *  C'est une action, cela permet au joueur de batisseurantiquité d'instruire un ouvrier
 *         
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 *         NORTIER Hugo
 */
public class InstruireOuvrier extends Action{

	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return instruireOuvrier((PlateauAntiquite)p, j, r, totalAction, a,ir,choixCartes);
	}

	/**
	 * action qui permet d'instruire un ouvrier
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur sur le plateau
	 * @param r :  Random
	 * @param totalAction : cout total
	 * @param a : Affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return totalAction : retourne le cout total
	 */
	public int instruireOuvrier(PlateauAntiquite p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().updateInstruireOuvrier();
		Ouvrier o=choixCartes.choixOuvrierInstruire(j.getOuvriers());
			//il paye 1 action et 7 sesters
			p.ajouterReserveEcus(7);
			j.setEcus(j.getNbEcus()-7);
			totalAction = totalAction - 1;
			//l'ouvrier s'instruit (un ouvrier peut être instruit une fois)
			Universite u = choixCartes.choixUniversite(p.getPaquetUniversite(), r);
			a.instruireOuvrier(j.getNb(),o.getRessources().getNom());
			o.instruire(u);
			p.getPaquetUniversite().remove(u);
			ir.investi();
		
		return totalAction;
	}

	

}

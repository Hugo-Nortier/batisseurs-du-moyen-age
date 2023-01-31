package action;

import java.util.Random;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * C'est une action, cela permet au joueur d'acheter une action.
 * 
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 * 
 */
public class AcheterAction extends Action {
	
	
	/**
	 *Permet d'effectuer l'action
	 * @param totalAction : cout total
	 * @param p           : plateau où le jeu se déroule pour obtenir les paquets de
	 *                    cartes que possèdent ce plateau
	 * @return totalAction : retourne le cout totale
	 */
	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return acheterAction(p, j, r, totalAction, a,ir,choixCartes);
	}
	

	/**
	 *Action permettant d'acheter une action 
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : le joueur sur le plateau
	 * @param r : radnom
	 * @param totalAction : le cout total
	 * @param a :  Affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return totalAction :  retourne le cout total
	 */
	public int acheterAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {

		j.getStats().updateAchatPasserTourConsecutif();
		int ecus = j.getNbEcus();
		if (choixAchatAction(ecus)) {
			ecus -= 5;
			j.setEcus(ecus);
			p.ajouterReserveEcus(5);
			totalAction++;
			a.achatActionSucces(j.getNb(), ecus, totalAction);
			
			j.getStats().updateAchatAction();
			ir.acheteAction();
		} else {
			a.achatActionEchec(j.getNb(), ecus);
		}
		return totalAction;
	}
	/**Permet de savoir si le joueur peut acheter une action ou pas
	 * @param ecus : nb d'ecus
	 * @return boolean : true si les ecus sont suffiant pour acheter une action
	 *         sinon false
	 */
	private boolean choixAchatAction(int ecus) {
		if (ecus >= 5)
			return true;
		return false;
	}

}

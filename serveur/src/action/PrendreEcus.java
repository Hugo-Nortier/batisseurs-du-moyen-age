package action;

import java.util.Random;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * 	Une action permettant au joueur de prendre des écus du plateau
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class PrendreEcus extends Action{

	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().updateAchatPasserTourConsecutif();;
		return prendreEcus(p,getNbActionsEchangees(r,totalAction),totalAction, j, a,ir,choixCartes);
	}
	
	/**
	 * @param r : random
	 * @param totalAction : le total d'action que possède le joueur
	 * @return res : retourne le nombre d action échangé
	 */
	public int getNbActionsEchangees(Random r, int totalAction) {
		int act = 0;
		if (totalAction >= 3)
			act = 3;
		else
			act = totalAction;
		int res = r.nextInt(act) + 1;
		return res;

	}
	

	/**
	 * action qui permet au joueur de prendre des écus
	 * @param p : le plateau de jeu
	 * @param nbActionsEchangees : le nombres d'actions que le joueur souhaiterait échanger
	 * @param totalAction : nb d'actions que le joueur possède
	 * @param j : joueur
	 * @param a : affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return nbActionsRestantesAuJoueur : le nombre d'actions restantes au joueur après échange
	 */
	public int prendreEcus(Plateau p, int nbActionsEchangees, int totalAction, Joueur j, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {

		// on return le nombre d'actions qu'il restera au joueur après avoir échangé ses
		// actions
		// car un joueur pourrait vouloir échanger ses actions puis ouvrir un batiment
		// par expl
		int nbActionsRestantesAuJoueur = totalAction - nbActionsEchangees;
		// si le joueur possède le nombre d'actions suffisantes pour les échanger
		if (nbActionsEchangees <= totalAction) {
			echangerActEcu(p, nbActionsEchangees, j, a,ir);
		}
		// si le joueur échange plus d'actions que ce qu'il n'en a
		// alors on lui échange le max qu'il puisse.
		else {
			echangerActEcu(p, totalAction, j , a,ir);
			nbActionsRestantesAuJoueur = totalAction;
		}
		
		// return le nombre d'actions que le joueur peut encore éffectuer dans le tour
		return nbActionsRestantesAuJoueur;
	}

	/**
	 * 
	 * @param p   : plateau du jeu
	 * @param act : nombre d'action qu'on veut échanger
	 * @param a : affichage
	 * @param j : joueur
	 */
	private void echangerActEcu(Plateau p, int act, Joueur j, Affichage a,InfoRepetition ir) {
		ir.prisEcus();
		switch (act) {
		case 1:
			effectuerEchange(p, act, 1, j, a,ir);
			j.getStats().updatePrendreEcus(1);
			break;
		case 2:
			effectuerEchange(p, act, 3, j, a,ir);
			j.getStats().updatePrendreEcus(3);

			break;
		case 3:
			effectuerEchange(p, act, 6,j,a,ir);
			j.getStats().updatePrendreEcus(6);

			break;
		}
	}

	/**
	 * fais un echange avec les écus
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param nbactions :  nombre d'actions que possède le joueur
	 * @param ecusgagnes : ecus gagnés par le joueur
	 * @param j : joueur
	 * @param a : affichage
	 */
	private void effectuerEchange(Plateau p, int nbactions, int ecusgagnes, Joueur j, Affichage a,InfoRepetition ir) {
		// s'il reste des écus en réserve
		if (p.getReserveEcus() >= ecusgagnes) {
			// on prend des écu du plateau et on le donne au joueur
			j.ajoutNbEcus(ecusgagnes);
			p.sousReserveEcus(ecusgagnes);
			a.echangerEcus(j.getNb(), nbactions, ecusgagnes, j.getNbEcus());
			compenserRepetition(ir.getRepetition(),nbactions, j, a,ir);
		}
	}

	/**
	 * 
	 * @param repetition
	 * @param nbactions : nombre d'actions du joueur
	 * @param j : joueur
	 * @param a : affichage
	 */
	private void compenserRepetition(int repetition, int nbactions, Joueur j, Affichage a, InfoRepetition ir) {
		switch(repetition) {
		case 0 :
			break;
		default :
			if(repetition+nbactions==2) {
				j.ajoutNbEcus(1);
				a.compensationEcus(j.getNb(),1);
			}
			if(repetition+nbactions==3) {
				j.ajoutNbEcus(2);
				a.compensationEcus(j.getNb(),2);
			}
		}
		ir.ajouterRepetition(nbactions);
	}


}

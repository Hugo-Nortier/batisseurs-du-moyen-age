package action;

import java.util.Random;

import cartes.Esclave;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
import jeu.PlateauAntiquite;
/**
 * C'est une action permettant d'acheter un esclave
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class AcheterEsclave extends Action{


	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return acheterEsclave((PlateauAntiquite)p, j, r, totalAction, a,ir,choixCartes);
	}

	/**
	 *Action permettant d'acheter un esclave 
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur sur le plateau
	 * @param r :  Random
	 * @param totalAction : cout total
	 * @param a : Affichage
	 * @param ir : Savoir si le joueur a déjà investi ou s'il y a blocage
	 * @param choixCartes : Connaitre le choix de l'IA
	 * @return totalAction : retourne le cout total
	 */
	public int acheterEsclave(PlateauAntiquite p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().updateAchatEsclave();
		Esclave e=choixCartes.choixEsclave(p.getPaquetEsclaves());
		a.achatEsclave(j.getNb(), e);
		int ecus = j.getNbEcus();
		ecus -= 7 ;
		j.setEcus(ecus);
		p.ajouterReserveEcus(7);
		j.addOuvrier(e);
		p.getPaquetEsclaves().remove(e);
		ir.investi();
		totalAction = totalAction - 1;
		return totalAction;
	}


}

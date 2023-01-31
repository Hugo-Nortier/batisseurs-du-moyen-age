package action;

import java.util.Random;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
import jeu.PlateauAntiquite;
/**
 * Action permettant de faire un emprunt
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class ContracterEmprunt extends Action {

	
	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return contracterEmprunt((PlateauAntiquite) p,j,r,totalAction,a,ir,choixCartes);
	}
	/**
	 * action permettant de faire un emprunt 
	 * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
	 * @param j : Joueur sur le plateau
	 * @param r :  Random
	 * @param totalAction : cout total
	 * @param a : Affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return totalAction : retourne le cout total
	 */
	public int contracterEmprunt(PlateauAntiquite p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().reinitialiseAchatConse();
		j.getEmprunts().add(p.getPaquetEmprunt().remove(0));
		j.ajoutNbEcus(10);
		a.contracterEmprunt(j.getNb());
        j.getStats().updateContracterEmprunt(); 
		totalAction = totalAction - 1;
		ir.investi();
		return totalAction;
	}
}

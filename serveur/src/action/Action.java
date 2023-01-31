package action;

import java.util.Random;

import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * Classe abstraite regroupant toutes les actions du joueur
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 * 	
 */
public abstract class Action{
	

	public abstract int effectuerAction(Plateau p, Joueur joueur, Random r, int nbAction, Affichage a,
			InfoRepetition ir, ChoixCartes choixCartes);
}

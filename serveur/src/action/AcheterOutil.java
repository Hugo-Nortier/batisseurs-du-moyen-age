package action;

import java.util.Random;

import cartes.Outil;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
import jeu.PlateauAntiquite;
/**
 * C'est une action qui permet d'acheter des outils
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class AcheterOutil extends Action {

   

	@Override
    public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
        return acheterOutil((PlateauAntiquite)p,j,r,totalAction,a,ir,choixCartes);
    }

    /**
     * action permettant d'acheter un outil
     * @param p : plateau où le jeu se déroule pour obtenir les paquets de cartes que possèdent ce plateau
     * @param j : Joueur sur le plateau
     * @param r : Random
     * @param totalAction : cout total
     * @param a :  Affichage
     * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
     * @return totalAction : retourne le cout total 
     */
    public int acheterOutil(PlateauAntiquite p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
        Outil o = choixCartes.choixOutil(p.getPaquetBatiments(),p.getPaquetOuvriers(),p.getPaquetOutils());
        j.setEcus(j.getNbEcus()-2);
        p.ajouterReserveEcus(2);
        a.acheterOutil(j.getNb(), o);
        j.addOutil(o);
        p.getPaquetOutils().remove(o);
        j.getStats().updateAchatOutil(); 
        totalAction = totalAction - 1;
		ir.investi();
        return totalAction;
    }


}

package cartes;

import infojeu.InfoJoueur;
import infojeu.InfoPlateau;
/**
 * Une machine est un batiment mais devient un ouvrier quand elle est fini, c'est une carte.
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 * 
 */
public class Machine extends Batiment {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1166329870554969917L;
	private Ouvrier o;

	/**
	 * Constructeur de Machine qui permet d'initialiser une machine avec ses attributs
	 * @param nom      : Le nom de la machine
	 * @param pierre   : Les ressources pierre qu'il faut pour construire ce
	 *                 batiment
	 * @param bois     : Les ressources bois qu'il faut pour construire ce batiment
	 * @param savoir   : Les ressources savoir qu'il faut pour construire ce
	 *                 batiment
	 * @param tuile    : Les ressources tuiles qu'il faut pour construire ce
	 *                 batiment
	 * @param ptsVict: Les points victoires que rapportent la machine après avoir
	 *                 été crée
	 * @param ecusRec: Les écus reçu après avoir complété la machine
	 * @param o        : Quand la machine est crée alors elle peut aller travailler,
	 *                 elle devient un ouvrier gratuit
	 */
	public Machine(String nom, int pierre, int bois, int savoir, int tuile, int ptsVict, int ecusRec, Ouvrier o) {
		super(nom, pierre, bois, savoir, tuile, ptsVict, ecusRec);
		this.o = o;
	}

	/**
	 * Getter pour obtenir l'ouvrier que deviendra la machine après être construite
	 * @return o : l'ouvrier que deviendra la machine après être construite
	 */
	public Ouvrier getOuvrier() {
		return o;
	}

	/**
	 * Si la construction est fini, permet de modifier le plateau et les récompenses du joueur en conséquence et de mettre à jour les statistiques
	 * @param j : Les différentes informations du joueur
	 * @param p : Les différentes informations du plateau
	 * @return boolean : si le batiment est fini ou pas
	 */
	@Override
	public boolean finirBat(InfoJoueur j, InfoPlateau p) {
		if (estFini()) {
			j.addOuvrier(o);
			for (Ouvrier of : ouvriers)
				j.addOuvrier(of);
			ouvriers.clear();
			int ecusRec = this.getRessources().getEcusRecompense();
			if (p.getReserveEcus() < ecusRec)
				ecusRec = p.getReserveEcus();
			p.sousReserveEcus(ecusRec);
			j.ajoutNbEcus(ecusRec);
			j.getBatFinis().add(this);
			j.removeBat(this);
			j.ajouterPtsVictoire(ressources.getPtsVictoire());
			return true;
		}
		return false;
	}
}

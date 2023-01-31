package cartes;

import java.io.Serializable;
import java.util.ArrayList;

import infojeu.InfoJoueur;
import infojeu.InfoPlateau;
import ressourcesCartes.RessourceBatiment;
/**
 *  
 * Carte batiment, un batiment possède des ressources (pierre,bois,savoir,tuile et cout) et des points de victoires qui sont notés sur la carte, les points de victoires sont données au joueur l'ayant terminé
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class Batiment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1826773644457925978L;
	protected RessourceBatiment ressources;
	protected ArrayList<Ouvrier> ouvriers = new ArrayList<>();
	private int coutActionTravail = 1;
	private boolean enConstruction = false;

	public ArrayList<Ouvrier> getOuvriers() {
		return ouvriers;
	}

	/**
	 * Constructeur d'ouvrier afin de créer un ouvrier avec toutes ses attributions
	 * @param nom     : le nom du batiment
	 * @param pierre  : les ressources pierre pour construire le batiment
	 * @param bois    : les ressources pierre pour construire le batiment
	 * @param savoir  : les ressources savoir pour construire le batiment
	 * @param tuile   : les ressources tuile pour construire le batiment
	 * @param ptsVict : les points victoires que donne le bâtiment apres qu'il soit
	 *                construit
	 * @param ecusRec : les écus reçus après avoir construit le bâtiment
	 */
	public Batiment(String nom, int pierre, int bois, int savoir, int tuile, int ptsVict, int ecusRec) {
		ressources = new RessourceBatiment(nom, pierre, bois, savoir, tuile, ptsVict, ecusRec);
	}

	/**
	 * Getter pour accéder aux ressources du bâtiment
	 * @return ressources : Les ressources du batiments
	 */
	public RessourceBatiment getRessources() {
		return ressources;
	}

	
	/**
	 * Permet d'obtenir le nombre de bois restant à la construction du bâtiment
	 * @return totOuvriers : le nombre de bois restants pour construire le bâtiment
	 */
	public int boisRestant() {
		int totresources = ressources.getBois();
		for (Ouvrier o : ouvriers)
			totresources -= o.getRessources().getBois();
		if (totresources < 0)
			totresources = 0;
		return totresources;
	}

	/**
	 * Permet d'obtenir le nombre de tuiles restantes pour construire le bâtiment
	 * @return totOuvriers : le nombre de tuiles restantes pour construire le
	 *         bâtiment
	 */
	public int tuileRestant() {
		int totresources = ressources.getTuile();
		for (Ouvrier o : ouvriers)
			totresources -= o.getRessources().getTuile();
		if (totresources < 0)
			totresources = 0;
		return totresources;
	}

	/**
	 * Permet d'obtenir le nombre de pierre restantes pour construire le bâtiment
	 * @return totOuvriers : le nombre de pierres restantes pour construire le
	 *         bâtiment
	 */
	public int pierreRestant() {
		int totresources = ressources.getPierre();
		for (Ouvrier o : ouvriers)
			totresources -= o.getRessources().getPierre();
		if (totresources < 0)
			totresources = 0;
		return totresources;
	}

	/**
	 * Permet d'obtenir le nombre de savoir restant pour construire le bâtiment
	 * @return totOuvriers : le nombre de savoir restant pour construire le batiment
	 */
	public int savoirRestant() {
		int totresources = ressources.getSavoir();
		for (Ouvrier o : ouvriers)
			totresources -= o.getRessources().getSavoir();
		if (totresources < 0)
			totresources = 0;
		return totresources;
	}
	
/**
 * Permet de savoir à l'aide d'un booléen si la construction du bâtiment a été achêvée
 * @return boolean : booléen qui indique si le bâtiment est fini
 */
	public boolean estFini() {
		int totalBois = boisRestant();
		int totalTuile = tuileRestant();
		int totalPierre = pierreRestant();
		int totalSavoir = savoirRestant();

		if (totalBois == 0 && totalTuile == 0 && totalPierre == 0 && totalSavoir == 0) {
			return true;
		} else
			return false;

	}

	/**
	 * Lorsque le joueur fini son bâtiment, le plateau est modifié et des récompenses sont données
	 * @param j : le joueur qui a fini le bâtiment
	 * @param p : le plateau où se trouve le joueur
	 * @return boolean si batiment est fini ou pas
	 */
	public boolean finirBat(InfoJoueur j, InfoPlateau p) {
		if (estFini()) {
			for (Ouvrier o : ouvriers) {
				if(o.getOutil()!=null) {
					Outil ot =o.recupererOutil();
					j.addOutil(ot);
					//j.getAffichage().retrouverOutil(j.getNb(),ot.getRessources().getNom(),o.getRessources().getNom());
				}
				j.addOuvrier(o);
			}
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

	/**
	 * 
	 * Getter qui retourne le cout d'une action travail
	 * @return coutActionTravail le cout pour envoyer travailler un ouvrier en plus de ses ressourcess
	 */
	public int getCoutActionTravail() {
		return coutActionTravail;
	}

	/**
	 * Permet d'augmenter le cout d'une action travail de 1
	 */
	public void addCoutActionTravail() {
		coutActionTravail++;
	}

	/**
	 * Permet de réinitialiser le cout d'action travail à 1
	 */
	public void resetCoutActionTravail() {
		coutActionTravail = 1;
	}

	/**
	 * Pour estimer la rentabilité d'un bâtiment
	 * @return le nombre d'écus et de point victoire - les autres ressources additionnée 
	 */
	public int rentabilite() {
		return this.ressources.getEcusRecompense() + this.ressources.getPtsVictoire() - this.ressources.getBois() - this.ressources.getPierre() - this.ressources.getTuile() - this.ressources.getSavoir();

	}

	/**
	 * Permet de definir le bâtiment comme étant en construction 
	 */
	public void enConstruction() {
		enConstruction=true;
	}
	
	/**
	 * Permet de savoir si le bâtiment est en construction ou non
	 * @return enConstruction : booléen pour savoir si le bâtiment est en construction
	 */
	public boolean construction() {
		return enConstruction;
	}

	@Override
	public String toString() {
		return this.ressources.getNom()+": bois=" + this.ressources.getBois() +", pierres="+ this.ressources.getPierre() +", tuiles="+ this.ressources.getTuile() +", savoir="+ this.ressources.getSavoir() 
		+", monnaie récompense=" +ressources.getEcusRecompense() + ", points victoire="+ this.ressources.getPtsVictoire();
	}

	
}

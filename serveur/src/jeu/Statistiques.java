package jeu;

/**
 * 
 *
 * Classe permettant de savoir les statistiques d'un joueur
 *
 * * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 * NORTIER Hugo
 *
 * 
 */
public class Statistiques{

	private int nbOuvrirBatiment = 0;
	private int recruter = 0;
	private int envoyerTravailler = 0;
	private int prendreEcus = 0;
	private int achatAction = 0;
	private int depenseOuvrier = 0;
	private int revenuBatiment = 0;
	private int machineConstruite = 0;
	private int nbAchatPasseTourConsecutif = 0;
	private int achatOutil = 0;
	private int achatEsclave = 0;
	private int instruireOuvrier = 0;
	private int donnerOutil = 0;
	private int contracterEmprunt = 0;
	private int gagnant = 0;

	/**
	 * Met à jour le nombre de bâtiment ouvert
	 */
	public void updateOuvrirBat() {
		nbOuvrirBatiment = getnbOuvrirBatiment() + 1;
	}

	/**
	 * Met à jour le nombre d'ouvrier recruté
	 */
	public void updateRecruter() {
		recruter = getRecruter() + 1;
	}

	/**
	 * Met à jour le nombre d'esclave recruté
	 */
	public void updateAchatEsclave() {
		achatEsclave = getAchatEsclave() + 1;
	}

	/**
	 * Met à jour le nombre d'ouvrier instruit
	 */
	public void updateInstruireOuvrier() {
		instruireOuvrier = getInstruireOuvrier() + 1;
	}

	/**
	 * Met à jour le nombre d'ouvrier envoyé travaillé
	 */
	public void updateEnvoyerTravailler() {
		envoyerTravailler = getEnvoyerTravailler() + 1;
	}

	/**
	 * Met à jour le nombre d'ecus pris
	 * 
	 * @param ecus : le nombre d'écus qu'on ajoute
	 */
	public void updatePrendreEcus(int ecus) {
		prendreEcus = getPrendreEcus() + ecus;
	}

	/**
	 * Met à jour le nombre d'action acheté
	 */
	public void updateAchatAction() {
		achatAction = getAchatAction() + 1;
	}

	/**
	 * Met à jour le nombre de depense en ouvrir
	 * 
	 * @param cout : le cout d'un ouvrier
	 */
	public void updateDepenseOuvrier(int cout) {
		depenseOuvrier = getDepenseOuvrier() + cout;
	}

	/**
	 * Met à jour le revenu du batiment
	 * 
	 * @param cout : le cout d'un batiment
	 */
	public void updateRevenuBatiment(int cout) {
		revenuBatiment = getRevenuBatiment() + cout;
	}

	/**
	 * Met à jour le nombre de machine construite
	 * 
	 */
	public void updateMachineConstruire() {
		machineConstruite = getMachineConstruite() + 1;
	}

	/**
	 * Met à jour le nombre de tour consécutif sans action
	 */
	public void updateAchatPasserTourConsecutif() {
		nbAchatPasseTourConsecutif++;
	}

	/**
	 * Reinitialise le nombre de tour consécutif sans action
	 */
	public void reinitialiseAchatConse() {
		nbAchatPasseTourConsecutif = 0;
	}

	/**
	 * Met a jour le nombre d outil acheté
	 */
	public void updateAchatOutil() {
		achatOutil = getAchatOutil() + 1;
	}

	/**
	 * Met a jour le nombre d outil donné
	 */
	public void updateDonnerOutil() {
		donnerOutil = getDonnerOutil() + 1;
	}

	/**
	 * Met à jour le nombre d emprunt contracté
	 */
	public void updateContracterEmprunt() {
		contracterEmprunt = getContracterEmprunt() + 1;
	}

	/**
	 * Met à jour la statistique gagnant pour 500 parties
	 */
	public void updateGagnant() {
		gagnant++;
	}

	public int getRecruter() {
		return recruter;
	}

	public int getEnvoyerTravailler() {
		return envoyerTravailler;
	}

	public int getPrendreEcus() {
		return prendreEcus;
	}

	public int getAchatAction() {
		return achatAction;
	}

	public int getnbOuvrirBatiment() {
		return nbOuvrirBatiment;
	}

	public int getDepenseOuvrier() {
		return depenseOuvrier;
	}

	public int getMachineConstruite() {
		return machineConstruite;
	}

	public int getRevenuBatiment() {
		return revenuBatiment;
	}

	public int getNbAchatPasseTourConsecutif() {
		return nbAchatPasseTourConsecutif;
	}

	public int getAchatOutil() {
		return achatOutil;
	}

	public int getAchatEsclave() {
		return achatEsclave;
	}

	public int getInstruireOuvrier() {
		return instruireOuvrier;
	}

	public int getDonnerOutil() {
		return donnerOutil;
	}

	public int getContracterEmprunt() {
		return contracterEmprunt;
	}

	public int getGagnant() {
		return gagnant;
	}

	public void additionnerStat(Statistiques s) {
		achatAction += s.achatAction;
		achatEsclave +=s.achatEsclave;
		achatOutil +=s.achatOutil;
		contracterEmprunt +=s.contracterEmprunt;
		depenseOuvrier +=s.depenseOuvrier;
		donnerOutil += s.donnerOutil;
		envoyerTravailler += s.envoyerTravailler;
		instruireOuvrier += s.instruireOuvrier;
		machineConstruite += s.machineConstruite;
		nbOuvrirBatiment += s.nbOuvrirBatiment;
		prendreEcus += s.prendreEcus;
		recruter += s.recruter;
		revenuBatiment += s.revenuBatiment;
		gagnant += s.gagnant;
		
	}

}
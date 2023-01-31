package cartes;

import java.io.Serializable;

import ressourcesCartes.Ressources;
/**
 * 
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 * 	Carte ouvrier, un ouvrier possède des ressources (pierre,bois,savoir,tuile et cout) qui sont notés sur la carte
 */
public class Ouvrier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7186842587544264947L;
	private Ressources ressources;
	Outil outil;
	private boolean isInstruit;
	/**
	 * Constructeur de la classe Ouvrier qui permet d'initialiser un ouvrier avec ses attributs
	 * @param nom : nom de l'ouvrier
	 * @param pierre : ressources pierre de l'ouvrier
	 * @param bois : ressource bois de l'ouvrier
	 * @param savoir : ressources savoir de l'ouvrier
	 * @param tuile : ressources tuile de l'ouvrier
	 * @param cout : ressources cout de l'ouvrier
	 */
	public Ouvrier(String nom, int pierre, int bois, int savoir, int tuile, int cout) {
		ressources = new Ressources(nom, pierre, bois, savoir, tuile, cout);
		isInstruit=false;

	}

	/** Permet de savoir si deux ouvrier ont les même attributs 
	 *  @param obj : une instance d'Ouvrier 
	 * @return this.ressources.equals(o.ressources): comparaison entre 2 ouvriers afin de savoir s'ils ont les mêmes attributs*/
	public boolean equals(Object obj) {
		Ouvrier o = (Ouvrier) obj;
		return this.ressources.equals(o.ressources);
	}

	/**
	 * Getter qui permet de retourner le nombre de ressources indiquées sur la cartes
	 * @return ressources : Ce sont les ressources indiquées sur la carte
	 */
	public Ressources getRessources() {return ressources;}

	
	/**
	 * Pour estimer la rentabilité d'un ouvrier
	 * @return le nombre d'écus et de point victoire - les autres ressources additionnée 
	 */
	public int rentabilite() {
		return this.ressources.getBois() + this.ressources.getPierre() + this.ressources.getSavoir() + this.ressources.getTuile() - this.ressources.getEcus();

	}
	
	/**
	 * Permet d'instruire un ouvrier en l'envoyant à l'université 
	 * @param u : une université 
	 */
	public void instruire(Universite u) {
		ressources.addBois(u.getRessources().getBois());
		ressources.addSavoir(u.getRessources().getSavoir());
		ressources.addTuile(u.getRessources().getTuile());
		ressources.addPierre(u.getRessources().getPierre());
		isInstruit=true;
	}
	
	/**
	 * Permet de savoir si l'ouvrier est instruit
	 * @return isInstruit : booleen pour savoir si c est instruit ou pas
	 */
	public boolean isInstruit() {
		return isInstruit;
	}

	/**
	 * Permet d'ajouter un outil à l'ouvrier
	 * @param o : outil que l'on veut ajouter
	 */
	public void addOutil(Outil o) {
		outil = o;
		ressources.addBois(o.getRessources().getBois());
		ressources.addSavoir(o.getRessources().getSavoir());
		ressources.addTuile(o.getRessources().getTuile());
		ressources.addPierre(o.getRessources().getPierre());
	}

	/**
	 * Getter outil qui permet d obtenir l'outil de l'ouvrier
	 * 
	 * @return outil : outil de l'ouvrier
	 */
	public Outil getOutil() {
		return outil;
	}

	/**
	 * Permet de récupérer un outil pour l'ouvrier
	 * @return res : Retourne l'outil que l'on récupère
	 */
	public Outil recupererOutil() {
		ressources.sousBois(outil.getRessources().getBois());
		ressources.sousSavoir(outil.getRessources().getSavoir());
		ressources.sousTuile(outil.getRessources().getTuile());
		ressources.sousPierre(outil.getRessources().getPierre());
		Outil res = outil;
		outil =null;
		return res;
	}
	
	@Override
	public String toString() {
		return this.ressources.getNom()+": bois=" + this.ressources.getBois() +", pierres="+ this.ressources.getPierre() +", tuiles="+ this.ressources.getTuile() +", savoir="+ this.ressources.getSavoir() 
		+", cout=" +ressources.getEcus();
	}
}
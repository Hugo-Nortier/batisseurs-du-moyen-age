package cartes;
/**
 * Carte esclave, un esclave possède des ressources (pierre,bois,savoir,tuile et cout) qui sont notés sur la carte
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 * 	
 */
public class Esclave extends Ouvrier {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6836166919588956109L;
	private boolean affranchi;
	private int coutAffranchi;
	/**
	 * Constructeur de la classe Esclave qui permet d'initialiser un esclave avec ses attributs
	 * @param nom : nom de l'esclave
	 * @param pierre : ressource pierre de l'esclave
	 * @param bois : ressource bois de l'esclave
	 * @param savoir : ressource savoir de l'esclave
	 * @param tuile : ressource tuile de l'esclave
	 * @param coutAff : son cout pour affranchir un ouvrier
	 */
	public Esclave(String nom, int pierre, int bois, int savoir, int tuile,int coutAff) {
		super(nom, pierre, bois, savoir, tuile, 0);
		coutAffranchi = coutAff;
		affranchi=false;
	}
	
	/**
	 * Getter pour obtenir le cout d'affranchissement de l'esclave
	 * @return coutAffranchi : cout d affranchissement
	 */
	public int getCoutAffranchi() {
		return coutAffranchi;
	}
	
	/**
	 * Pour savoir si l'esclave est affranchi ou non
	 * @return affranchi : booleen pour savoir s'il est affranchi
	 */
	public boolean isAffranchi() {
		return affranchi;
	}
	
	/**
	 * Permet d'affranchir un esclave
	 */
	public void affranchir() {
		affranchi=true;
		super.getRessources().setCoutTravail(coutAffranchi);
	}

	@Override
	public String toString() {
		return "Esclave: \""+ this.getRessources().getNom()+"\" bois=" + this.getRessources().getBois() +" pierres="+ this.getRessources().getPierre() +" tuiles="+ this.getRessources().getTuile() +" savoir="+ this.getRessources().getSavoir() 
		+" cout=" +this.getRessources().getEcus();
	}
}

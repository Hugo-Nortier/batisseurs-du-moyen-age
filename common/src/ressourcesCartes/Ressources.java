package ressourcesCartes;

import java.io.Serializable;

/**
 * Les données écrites sur les cartes batiments (nom, pierre, bois, savoir, tuile, ecus, points de victoires)
 *
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 *	
 */
public class Ressources implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7909530165951135318L;
	private int ecus;
	
	public int getEcus() {
		return ecus;
	}

	private String nom;
	private int pierre;
	private int bois;
	private int savoir;
	private int tuile;
	
	public String getNom() {
		return nom;
	}
	
	public int getPierre() {
		return pierre;
	}

	public int getBois() {
		return bois;
	}

	public int getSavoir() {
		return savoir;
	}

	public int getTuile() {
		return tuile;
	}
	
	/**
	 * Enlève des ressource pierre
	 * @param p : nombre de pierre en moins
	 */
	public void sousPierre(int p) {
		pierre -=p;
	}
	
	/**
	 * Enlève des ressources tuile
	 * @param t : nb de tuile en moins
	 */
	public void sousTuile(int t) {
		tuile -=t;
	}
	
	/**
	 * Enleve des ressources bois
	 * @param b : nb bois en moins
	 */
	public void sousBois(int b) {
		bois -=b;
	}
	
	/**
	 * Enleve des ressources savoir
	 * @param s : nombre de savoir en moins
	 */
	public void sousSavoir(int s) {
		savoir -=s;
	}
	
	/**
	 * Ajout des ressources pierre
	 * @param p : nombre de pierre en plus
	 */
	public void addPierre(int p) {
		pierre +=p;
	}
	
	/**
	 * Ajout de tuile
	 * @param t : nb tuile en plus
	 */
	public void addTuile(int t) {
		tuile +=t;
	}
	
	/**
	 * Ajout de bois
	 * @param b : nb bois en plus
	 */
	public void addBois(int b) {
		bois +=b;
	}
	
	/**
	 * Ajout savoir
	 * @param s : nb savoir en plus
	 */
	public void addSavoir(int s) {
		savoir +=s;
	}

	/**
	 * permet d'initialiser les ressources 
	 * @param n : nom
	 * @param p : pierre
	 * @param b : bois
	 * @param s : savoir
	 * @param t : tuile
	 * @param ecus : cout travail
	 */
	public Ressources (String n, int p, int b, int s, int t,int ecus)
	{
		nom=n;
		pierre=p;
		bois=b;
		savoir=s;
		tuile=t;
		this.ecus=ecus;
	}
	/**
	 * Permet de savoir si deux objet sont egaux
	 * @return booleen : true si egaux false sinon
	 */
	public boolean equals(Object obj) {
	       Ressources o=(Ressources)obj;
	       if(o.getBois()==this.getBois() && o.ecus==this.ecus && o.getNom().equals(this.getNom())&&o.getPierre()==this.getPierre()&&o.getSavoir()==this.getSavoir()&&o.getTuile()==this.getTuile()) {
	    	   return true;
	       }
	     return false;  
	}

	public void setCoutTravail(int cout) {
		ecus = cout;
		
	}
	
	
}

package cartes;

import java.io.Serializable;

/**
 * Carte emprunt, un emprunt permet de donner des écus mais il faut soit le rembourser soit recevoir une pénalité
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 * 	
 */
public class Emprunt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1755913387486191478L;
	private String nom;
	private int gainEcus;
	private int penalite;
	private int ecusRembours;
	private boolean isRembourse;
	/**
	 * Constructeur de la classe Emprunt qui permet d'initialiser un emprunt avec ses caractéristiques
	 * @param nom : nom de l'emprunt
	 * @param ecus : Le nombre d'écus emprunté
	 * @param penalite : La pénalité de point de victoire si ce n'est pas remboursé
	 * @param ecusRemb : Les écus que doit rembourser le joueur s'il a emprunté
	 */
	public Emprunt(String nom, int ecus, int penalite, int ecusRemb) {
		this.nom = nom;
		this.gainEcus = ecus;
		this.penalite=penalite;
		this.ecusRembours=ecusRemb;
		this.isRembourse=false;
	}
	/**
	 * Getter pour obtenir le nom de l'emprunt
	 * @return nom : le nom de l'emprunt
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Getter pour obtenir le nombre d'écus gagné
	 * @return gainEcus : le nombre de gains d'écus
	 */
	public int getGainEcus() {
		return gainEcus;
	}
	
	/**
	 * GetterP pour obtenir la valeur de la pénalité de l'emprunt
	 * @return penalite : retourne la pénalité
	 */
	public int getPenalite() {
		return penalite;
	}
	
	/**
	 * Getter pour obtenir le nombre d'écus à rembourser sur son emprunt
	 * @return ecusRembours : le nombre d'écus à rembourser
	 */
	public int getEcusRembours() {
		return ecusRembours;
	}
	
	/**
	 * Pour connaître l'état de l'emprunt et savoir s'il est remboursé ou non
	 * @return isRembourse : si l'emprunt est remboursé
	 */
	public boolean isRembourse() {
		return isRembourse;
	}
	
	/**
	 * Pour changer le statut de l'emprunt et signifier que l'emprunt est remboursé
	 */
	public void rembourser() {
		isRembourse=true;
	}

}

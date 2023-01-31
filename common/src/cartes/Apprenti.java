package cartes;

/**
 * Carte apprenti, un apprenti possède des ressources (pierre,bois,savoir,tuile et cout) qui sont notés sur la carte
 *
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class Apprenti extends Ouvrier {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5163305746011751385L;

	/**
	 * Constructeur d'apprenti afin de créer un apprenti avec toutes ses attributions
	 * @param nom : Le nom de l'ouvrier
	 * @param pierre : Le nombre de pierre que produit l'ouvrier
	 * @param bois : Le nombre de bois que produit l'ouvrier
	 * @param savoir : Le nombre de savoir que produit l'ouvrier
	 * @param tuile : Le nombre de tuile que produit l'ouvrier
	 * @param cout : Le cout de l'ouvrier (nombre d'écus qu'il faut payer)
	 */
	public Apprenti(String nom, int pierre, int bois, int savoir, int tuile, int cout) {
		super(nom, pierre, bois, savoir, tuile, cout);
	}

}

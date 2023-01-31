package cartes;

import java.io.Serializable;

import ressourcesCartes.Ressources;
/**
 * Carte universite, il y en a 4 au total, une université possède des ressources (pierre, bois, savoir, tuile, cout) qui sont notés sur la carte
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 * 
 */
public class Universite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5470304137649289403L;
	private Ressources ressources;

/**
 * Constructeur Université pour instancier une université avec ses attributs
 * @param nom : ressource nom de l'université
 * @param pierre : ressource pierre de l'université
 * @param bois : ressource bois de l'université
 * @param savoir : ressource savoir de l'université 
 * @param tuile : ressource tuile de l'université
 * @param cout : ressource cout de l'université
 */
	public Universite(String nom, int pierre, int bois, int savoir, int tuile, int cout) {
		ressources = new Ressources(nom, pierre, bois, savoir, tuile, cout);

	}

/**
 * Getter pour obtenir les ressources de l'université 
 * @return ressources : les ressources de l'université 
 */
	public Ressources getRessources() {
		return ressources;
	}

}

package cartes;

import java.io.Serializable;

import ressourcesCartes.Ressources;
/**
 * 

 *
 * 	Carte outil, un outil possède des ressources (pierre,bois,savoir,tuile et cout) qui sont notés sur la carte
 *  @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 */
public class Outil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8206398779923979154L;
	private Ressources ressources;

/**
 * Constructeur d'Outil qui permet d'initialiser un outil avec ses attributs
 * @param nom : nom de l'outil
 * @param pierre : ressource pierre de l'outil
 * @param bois : ressource bois de l'outil
 * @param savoir : ressource savoir de l'outil
 * @param tuile : ressource tuile de l'outil
 * @param cout : le cout de l'outil
 */
	public Outil(String nom, int pierre, int bois, int savoir, int tuile, int cout) {
		ressources = new Ressources(nom, pierre, bois, savoir, tuile, cout);

	}

/**
 * Permet d'obtenir toutes les ressources de l'outil
 * @return ressources : les ressources de l'outil
 */
	public Ressources getRessources() {
		return ressources;
	}
	
	@Override
	public String toString() {
		return "Outil: \""+ this.getRessources().getNom()+"\" bois=" + this.getRessources().getBois() +" pierres="+ this.getRessources().getPierre() +" tuiles="+ this.getRessources().getTuile() +" savoir="+ this.getRessources().getSavoir() 
		+" cout=" +this.getRessources().getEcus();
	}

}
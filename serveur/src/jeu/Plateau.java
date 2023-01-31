package jeu;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Apprenti;
import cartes.Batiment;
import cartes.Ouvrier;
import infojeu.InfoPlateau;
/**
 * 
 *
 *
 *	Le plateau du jeu batisseur moyen-âge, il contient 2 pioches (batiments, ouvriers) et 2 paquets (batiments, ouvriers)
 *  Une réserve d'écus qui commence à 40 quand le jeu commence
 *  Une carte Builder pour pouvoir créer des cartes avec les bonnes informations
 *  
 *   @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 */
public class Plateau{
	
	protected InfoPlateau ip = new InfoPlateau();
	public InfoPlateau getIp() {
		return ip;
	}

	public void setIp(InfoPlateau ip) {
		this.ip = ip;
	}
	protected CarteBuilder cb;

	/**
	 * Initialise le mode de jeu MOYENAGE par default
	 */
	public Plateau() {
		this("MOYENAGE");
	}
	
	/**
	 * Pour instancier les cartes du jeu quand la fonction est appelé
	 * @param s : Le fichier csv de carte que l'on veut instancier
	 */
	protected Plateau(String s) {
		cb = new CarteBuilder(s);
		initPioches();
		ip.setReserveEcus(40);
	}

	/** appelle les méthodes qui initialisent les pioches des cartes */
	private void initPioches() {
		cb.construireCartes("cartes_moyen_age.csv");
		initPiocheBatiments();
		initPiocheOuvriers();
	}
	/** 
	 * initialise pioche carte batiments
	  */
	private void initPiocheBatiments(){
		getPiocheBatiments().addAll(cb.getBat());
		getPiocheBatiments().addAll(cb.getMach());
	}
	/** initialise pioche carte ouvriers */
	private void initPiocheOuvriers() {
		getPiocheOuvriers().addAll(cb.getOuv());
		getPiocheOuvriers().addAll(cb.getAppr());
	}

	/** initialise la partie (mélange des pioches ...)*/
	/**
	 * 
	 * @param joueurs : liste de joueurs
	 */
	public void initPartie(ArrayList<Joueur> joueurs) {
		Collections.shuffle(getPiocheOuvriers());
		Collections.shuffle(getPiocheBatiments());
		for (Joueur j : joueurs) 
			j.addOuvrier(piocherApprenti());
		for (int i = 0; i < 5; i++) {
			getPaquetBatiments().add(getPiocheBatiments().remove(i));
			getPaquetOuvriers().add(getPiocheOuvriers().remove(i));
		}
	}
	/**
	 * Permet de piocher un apprenti
	 * @return piocheOuvriers.remove(piocheOuvriers.size()) : un Ouvrier 
	 */
	private Ouvrier piocherApprenti() {
		for (int i = 0; i < getPiocheOuvriers().size(); i++) {
			if (getPiocheOuvriers().get(i) instanceof Apprenti)
				return getPiocheOuvriers().remove(i);
		}
		//on pioche la carte du dessus (celle entrée en dernière dans la pioche)
		return getPiocheOuvriers().remove(getPiocheOuvriers().size());
	}

	/**
	 * Getter pour obtenir le paquet de bâtiment
	 * @return paquetBatiment : retourne le paquet
	 */
	public ArrayList<Batiment> getPaquetBatiments() {return ip.getPaquetBatiments();}
	/**
	 * Getter pour obtenir le paquet d ouvrier
	 * @return paquetOuvrier : retourne le paquet d ouvrier
	 */
	public ArrayList<Ouvrier> getPaquetOuvriers() {return ip.getPaquetOuvriers();}
	/**
	 * Getter pour obtenir la pioche d ouvrier
	 * @return piocheOuvrier : retourne la pioche d ouvrier
	 */
	public ArrayList<Ouvrier> getPiocheOuvriers() {return ip.getPiocheOuvriers();}
	/**
	 * Getter pour obtenir la pioche de bâtiment
	 * @return piocheBatiment : retourne la pioche de batiments
	 */
	public ArrayList<Batiment> getPiocheBatiments() {return ip.getPiocheBatiments();}
	
	/** Getter pour obtenir le nombre d ecus en reserve 
	 * @return reserveEcus : les écus qu'il y a sur le plateau*/
	public int getReserveEcus() {return ip.getReserveEcus();}
/**
 * Pour ajouter un nombre d ecus à la reserve
 * @param newR = Nombre d'écus que l'on veut ajouter
 */
	public void ajouterReserveEcus(int newR) {
		ip.ajouterReserveEcus(newR);
	}
	
	/**
	 * Enleve un nombre d ecus de la reserve
	 * @param ecus : le nombre d ecus a soustraire
	 */
	public void sousReserveEcus(int ecus) {
		ip.sousReserveEcus(ecus);
	}
	/**
	 * 
	 * Un gagnant est celui qui a ouvert le plus de chantiers et qui a le plus d'écus!
	 * @param joueurs : l'arraylist de joueurs
	 * @return res : le(s) gagnant(s)
	 */
	public  ArrayList<Joueur> getGagnants(ArrayList<Joueur> joueurs) {

		int scoreMax = 0;
		ArrayList<Joueur> res = new ArrayList<>();
		for (Joueur j : joueurs) {
			int scoreJoueur = j.getScore();
			if (scoreJoueur > scoreMax)
				scoreMax = scoreJoueur ;
		}
		for (Joueur j : joueurs) {
			if (j.getScore()== scoreMax)
				res.add(j);
		}
		
		int scoreMaxPtsVict = 0;
		ArrayList<Joueur> gag = new ArrayList<>();
		for(Joueur jo : res) {
			int ptsVictoire = jo.getPtsVictoire();
			if(ptsVictoire > scoreMaxPtsVict) {
				scoreMaxPtsVict = ptsVictoire;
			}
		
		}
		for (Joueur jo : res) {
			if(jo.getPtsVictoire() == scoreMaxPtsVict) {
				gag.add(jo);
			}
		}
		
		int scoreMaxEcus = 0;
		ArrayList<Joueur> ec = new ArrayList<>();
		for(Joueur g : gag) {
			int nbEcus = g.getNbEcus();
			if(nbEcus > scoreMaxEcus) {
				scoreMaxEcus = nbEcus;
			}
		}
		for (Joueur g : gag) {
			if(g.getNbEcus() == scoreMaxEcus) {
				ec.add(g);
			}
		}

		return ec;
	}


}

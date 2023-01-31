package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import reseau.ClientJoueur;

/**
 * La classe qui gère les différents jeux, permet le lancement d'une
 *         partie que ce soit batisseur moyen-age ou batisseur antiquité
 *         
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 *         NORTIER Hugo
 *
 *         
 */
public class GestionnaireJeu implements Runnable{
	private String typepartie = "MOYENAGE";
	private Affichage a;
	private ArrayList<ClientJoueur> clientjoueurs = new ArrayList<>();
	private ArrayList<Joueur> joueurs = new ArrayList<>();
	private int index = 0;

	/**
	 * Permet de lancer le mode selon l'argument
	 * @param tp  : tp
	 * @param nbp : nombre partie
	 */
	public GestionnaireJeu(String tp, int nbp) {
		if (tp.equals("ANTIQUITE"))
			typepartie = tp;
		a = new Affichage(tp, nbp);
	}
	
	/**
	 * Permet de lancer le mode selon l'argument
	 * @param tp  : tp
	 * @param nbp : nombre partie
	 * @param index : permet d'avoir le bon affichage en réseau
	 */
	public GestionnaireJeu(String tp, int nbp, int index) {
		this(tp,nbp);
		this.index = index;
	}

	/**
	 * méthode permettant de lancer une partie
	 * @param joueurs : liste de joueurs 
	 * @throws IOException : Levée l'exception s'il y a une erreur
	 * @throws ClassNotFoundException : La classe n'est pas trouvée
	 **/
	public void lancerPartie(ArrayList<Joueur> joueurs) throws IOException, ClassNotFoundException {
		switch (typepartie) {
		case "MOYENAGE":
			lancerJeu(new Plateau(), joueurs);
			break;
		case "ANTIQUITE":
			lancerJeu(new PlateauAntiquite(), joueurs);
			break;
		}
	}

	/**
	 * Permet de lancer le jeu et d'initialiser le plateau
	 * @param plt : Plateau
	 * @param joueurs : Liste de joueurs
	 * @throws IOException : Levée une exception s'il y a une erreur réseau
	 * @throws ClassNotFoundException  : Si la classe n'est pas trouvée
	 */
	public void lancerJeu(Plateau plt, ArrayList<Joueur> joueurs) throws IOException, ClassNotFoundException {
		Plateau p = plt;
		Random r = new Random();
		boolean gagnant = false;
		boolean jeuBloque = false;
		p.initPartie(joueurs);
		a.debutPartie(p, joueurs);
		int i = 1;
		int compteur = 0;
		while (!gagnant && !jeuBloque) {
			a.debutTour(i);
			for (int d = 0;d<joueurs.size();d++) {
				joueurs.get(d).joueurTour(p, r,clientjoueurs.get(d));
				
				a.finTourJoueur(joueurs.get(d).getNb());
				if (joueurs.get(d).getPtsVictoire() >= 17) {
					gagnant = true;
				}

			}
			a.finTour(p);
			i++;
			for (int j = 0; j < joueurs.size(); j++) {
				if (joueurs.get(j).getStats().getNbAchatPasseTourConsecutif() >= 20) {
					compteur++;
				}
			}
			if (compteur == joueurs.size()) {
				jeuBloque = true;
			}
			compteur = 0;
		}
		if (jeuBloque) {
			a.partieNulle();
		} else {
			ArrayList<Joueur> gagnants = p.getGagnants(joueurs);
			for (Joueur j : gagnants) {
				j.getStats().updateGagnant();
				
			}
			a.finDePartie(gagnants);
		}
		a.afficherStatistiques(joueurs);
	}

	/**
	 * Actualiser le gagnant finale
	 * @param grandgagnants : les gagnants après les malus du decompte final
	 */
	public void updateGrandGagnants(HashMap<Integer, Statistiques> grandgagnants) {
		for (Joueur j : joueurs) 
				grandgagnants.get(j.getNb()).additionnerStat(j.getStats());
	}
		
	/**
	 * Permet d'ajouter un joueur 
	 * @param js : liste de joueur déjà initiliasé
	 */
	public void addJoueurs(ArrayList<Joueur> js) {
		joueurs.addAll(js);

	}
	
	/**
	 * Permet d'ajouter un joueur 
	 * @param cjs : liste de joueur déjà initiliasé
	 */
	public void addClients(ArrayList<ClientJoueur> cjs) {
		clientjoueurs.addAll(cjs);

	}


	public Affichage getA() {
		return a;
	}
	
	public String getTypepartie() {
		return typepartie;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	@Override
	public void run() {
		try {
			lancerPartie(joueurs);
			a.finMultiPartie(index);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import action.*;
import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.Info;
import infojeu.InfoJoueur;
import infojeu.InfoRepetition;
import reseau.ClientJoueur;

/**
 * 
 *
 *   Joueur possède des ouvriers, des batiments, des batiments finis, des
 *   statistiques, des points victoires etc. C'est celui qui joue au jeu
 *         
 *    @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 *    NORTIER Hugo
 */
public class Joueur{
	/**
	 * 
	 */
	private InfoJoueur ij = new InfoJoueur();
	private int nb;
	private Affichage a;
	private Statistiques stats = new Statistiques();

	/**
	 * Initialise le joueur avec le mode de jeu
	 * @param idj : id du joueur
	 * @param jeu : mode de jeu
	 * @param af : affichage
	 */
	public Joueur(int idj, String jeu, Affichage af) {
		nb = idj;
		setEcus(10);
		a = af;
	}

	/**
	 * Ajoute un ouvrier à la liste d ouvrier
	 *  @param ouvrier : un ouvrier à ajouter à l'ArrayList ouvriers */
	public void addOuvrier(Ouvrier ouvrier) {
		getIj().addOuvrier(ouvrier);
	}

	/**
	 * Ajoute un outil à liste d outils
	 *  @param outil : un outil à ajouter à l'ArrayList outils */
	public void addOutil(Outil outil) {
		getIj().addOutil(outil);
	}

	/**
	 * Permet d executer le tour du joueur, soit il fait une action ou il passe son tour
	 * @param p : un plateau de jeu sur lequel le joueur jouera ou finira son tour
	 * @param r : un random permettant la réalisation des tests
	 * @param clientJoueur : Permet de lier le client à un joueur
	 * @throws IOException : Levée une exception s'il y a une erreur réseau
	 * @throws ClassNotFoundException : Permet d'émettre une erreur si la classe n'est pas trouvée
	 */
	public void joueurTour(Plateau p, Random r, ClientJoueur clientJoueur) throws IOException, ClassNotFoundException {
		setNbAction(3);
		InfoRepetition ir = new InfoRepetition();
		HashMap<String, Info> infospartie = new HashMap<>();
		synchronized(clientJoueur.getOOS()) {
		while ( getNbAction()>= 0) {
			infospartie.put("joueur", getIj());
			infospartie.put("plateau", p.getIp());
			infospartie.put("repetition", ir);
			clientJoueur.getOOS().writeObject(infospartie);
			clientJoueur.getOOS().reset();
			Action act = getAction((String)clientJoueur.getOIS().readObject());
			ir = (InfoRepetition)clientJoueur.getOIS().readObject();
			setNbAction(act.effectuerAction(p, this, r, getNbAction(), a, ir, new ChoixCartes()));
		}
		clientJoueur.getOOS().writeObject("fintour");
		clientJoueur.getOOS().reset();
		}
		resetCoutActionsBatiments(); // fin du tour, on remet le cout des actions de chaque batiment à 1
		
	}

	private Action getAction(String nomAction) {
		HashMap<String,Action> actions = new HashMap<>();
		actions.put("EnvoyerTravailler", new EnvoyerTravailler());
		actions.put("PrendreEcus",new PrendreEcus());
		actions.put("RecruterOuvrier",new RecruterOuvrier());
		actions.put("OuvrirChantier",new OuvrirChantier());
		actions.put("AcheterAction",new AcheterAction());
		actions.put("FinirTour",new FinirTour());
		actions.put("AcheterEsclave",new AcheterEsclave());
		actions.put("AcheterOutil",new AcheterOutil());
		actions.put("AffranchirEsclave",new AffranchirEsclave());
		actions.put("InstruireOuvrier",new InstruireOuvrier());
		actions.put("DonnerOutil",new DonnerOutil());
		actions.put("ContracterEmprunt",new ContracterEmprunt());
		return actions.get(nomAction);
	}

	/**
	 * Permet de reset le cout d'action du batiment
	 */
	public void resetCoutActionsBatiments() {
		for (Batiment b : getBatiments())
			b.resetCoutActionTravail();
	}


	/**
	 * 
	 * @return getStats().getnbOuvrirBatiment() + getTrancheDeDix() : Permet de
	 *         retourner le score du joueur en additionnant le nombre de batiment
	 *         ouvert et les tranches de dix écus que possède le joueur
	 */
	public int getScore() {
		return getPtsVictoire() + getTrancheDeDix();
	}

	public ArrayList<Ouvrier> getOuvriers() {
		return getIj().getOuvriers();
	}

	public ArrayList<Batiment> getBatiments() {
		return getIj().getBatiments();
	}

	public int getNb() {
		return nb;
	}

	public int getNbEcus() {
		return getIj().getNbEcus();
	}

	/**
	 * Ajoute un nombre d ecus à la reserve
	 * @param nbEcus : le nombre d'écus en plus à rajouter à la reserve actuelle du
	 *               joueur
	 */

	public void ajoutNbEcus(int nbEcus) {
		getIj().ajoutNbEcus(nbEcus);
	}

	/**
	 * Le nombre de tranche de 10 ecus que le joueur possède
	 * @return tdd : le nombre de tranche de dix écus que possède le joueur
	 */
	public int getTrancheDeDix() {
		return getIj().getTrancheDeDix();
	}

	/**
	 * 
	 * @return stats : les statistiques du joueur permettant de les afficher à la
	 *         fin du jeu
	 */
	public Statistiques getStats() {
		return stats;
	}

	public ArrayList<Batiment> getBatFinis() {
		return getIj().getBatFinis();
	}

	public int getPtsVictoire() {
		return getIj().getPtsVictoire();
	}

	public void ajouterPtsVictoire(int ptsVictoire) {
		getIj().ajouterPtsVictoire(ptsVictoire);
	}

	public Affichage getAffichage() {
		return a;
	}

	public void addBatiment(Batiment b) {
		getIj().addBatiment(b);

	}

	public void setEcus(int ec) {
		getIj().setEcus(ec);;
	}

	public ArrayList<Emprunt> getEmprunts() {
		return getIj().getEmprunts();
	}

	public ArrayList<Outil> getOutils() {
		return getIj().getOutils();
	}
	
	public ArrayList<Esclave> getEsclaves() {
		ArrayList<Esclave> esclavesj= getEsclavesO(getOuvriers());
		return esclavesj;
	}
	public ArrayList<Esclave> getEsclavesO(ArrayList<Ouvrier> ouv){
		ArrayList<Esclave> esclavesj= new ArrayList<>();
		for(Ouvrier o : ouv) {
			if(o instanceof Esclave) {
				if(!((Esclave)o).isAffranchi())
					esclavesj.add((Esclave)o);
			}
		}
		return esclavesj;
	}

	public Esclave getEsclaveAffranchir() {
		return getEsclaves().get(0);
		
	}

	public ArrayList<Esclave> getEsclavesFinal() {
		ArrayList<Esclave> esclavesj = new ArrayList<>();
		esclavesj.addAll(getEsclaves());
		for(Batiment b : getBatiments()) {
			esclavesj.addAll(getEsclavesO(b.getOuvriers()));
		}
		return esclavesj;
	}

	public int getMalusdecompte() {
		return getIj().getMalusdecompte();
	}

	/**
	 * Ajout un malus au joueur
	 * @param malusdecompte : le malus qui est ajouté 
	 */
	public void ajoutMalusdecompte(int malusdecompte) {
		getIj().ajoutMalusdecompte(malusdecompte);
	}

	public int getNbAction() {
		return getIj().getNbAction();
	}
	public void setNbAction(int nb) {
		getIj().setNbactions(nb);
	}

	

	public InfoJoueur getIj() {
		return ij;
	}

}
package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Machine;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;

/**
 *  Classe permettant l'affichage de l'état du jeu
 *  
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef,
 *         NORTIER Hugo
 *
 *        
 */
public class Affichage{
	
	private boolean isVerbose = false;
	private String monnaie;
	private String jeu;

	/**
	 * Constructeur de la classe Affichage afin d'initialiser le mode de jeu et le nombre de partie
	 * @param tp : string définissant le mode du jeu
	 * @param nbp : nombre de partie
	 */
	public Affichage(String tp, int nbp) {
		switch (tp) {
		case "MOYENAGE":
			monnaie = "écus";
			break;
		case "ANTIQUITE":
			monnaie = "sesterces";
			break;
		}
		jeu = tp;
		if (nbp == 1)
			isVerbose = true;
	}

	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	/**
	 * initialise et débute la partie de batisseurs du Moyen-âge
	 *
	 * @param p       : le plateau sur lequel on joue
	 * @param joueurs : une liste de joueurs
	 */
	public void debutPartie(Plateau p, ArrayList<Joueur> joueurs) {
		if (isVerbose) {
			System.out.println(ANSI_BLUE + "Demarrage du jeu");
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);

				System.out.println(ANSI_BLACK + "1 apprenti est distribué à chaque joueur."+ ANSI_RESET);
			
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);

			System.out.println(ANSI_PURPLE + "Initialisation des pioches sur le plateau :" + ANSI_RESET);
			
			System.out.println(ANSI_PURPLE + "Pioche batiments: " + ANSI_RESET);
			for(int i=0;i<p.getPiocheBatiments().size();i++) {
				System.out.println(ANSI_BLACK + p.getPiocheBatiments().get(i).toString() + ANSI_RESET);
			}
			System.out.println(ANSI_PURPLE + "Pioche ouvriers: " + ANSI_RESET);
			for(int i=0;i<p.getPiocheOuvriers().size();i++) {
				System.out.println(ANSI_BLACK + p.getPiocheOuvriers().get(i).toString() + ANSI_RESET);
			}
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			
				System.out.println(ANSI_BLACK + "5 batiments sont distribués."+ ANSI_RESET);

				System.out.println(ANSI_BLACK + "5 ouvriers sont distribués." + ANSI_RESET);
			
			if (jeu.equals("ANTIQUITE")) {

				System.out.println(ANSI_BLACK + "6 esclaves sont distribués." + ANSI_RESET);

				System.out.println(ANSI_BLACK + "4 emprunts sont distribués." + ANSI_RESET);

				System.out.println(ANSI_BLACK + "4 outils sont distribués." + ANSI_RESET);

				System.out.println(ANSI_BLACK + "4 universités sont distribuées." + ANSI_RESET);

			}
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			System.out.println(ANSI_BLACK + "10 " + monnaie + " sont distribués à chaque joueur.\n40 " + monnaie
					+ " sont stockés dans la reserve sur le plateau." + ANSI_RESET);
			
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
		}
	}

	/**
	 * ouvre le chantier
	 *
	 * @param b : le batiment dont on commence les travaux
	 * @param i : le numero de joueur
	 */
	public void ouvrirChantier(int i, Batiment b) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + i + " a ouvert le chantier \"" + b.toString()
					+ "\"." + ANSI_RESET);
	}

	/**
	 * termine le tour du joueur numéro "nb"
	 *
	 * @param nb : le numéro du joueur qui doit finir son tour
	 */
	public void finTourJoueur(int nb) {
		if (isVerbose) {
			System.out.println(ANSI_RED + "Le joueur " + nb + " a fini son tour." + ANSI_RESET);
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
		}
	}

	/**
	 * affiche l'état actuel du plateau à la fin d'un tour
	 *
	 * @param p  : le plateau sur lequel on joue
	 */
	public void finTour(Plateau p) {
		if (isVerbose) {
			ArrayList<Batiment> paquetBatiments = p.getPaquetBatiments();
			ArrayList<Ouvrier> paquetOuvriers = p.getPaquetOuvriers();
			String res = ANSI_BLACK;
			if(!paquetBatiments.isEmpty()) {
			
			System.out.println(ANSI_PURPLE + "Batiments actuels sur le plateau :" + ANSI_RESET);
			for (int i = 0; i < paquetBatiments.size(); i++) {
				res += paquetBatiments.get(i).getRessources().getNom();
				if (i != paquetBatiments.size() - 1)
					res += " - ";
			}
			System.out.println(res + ANSI_RESET);

			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			}
			if(!paquetOuvriers.isEmpty()) {

			System.out.println(ANSI_PURPLE + "Ouvriers actuels sur le plateau :" + ANSI_RESET);
			res = ANSI_BLACK;
			for (int i = 0; i < paquetOuvriers.size(); i++) {
				res += paquetOuvriers.get(i).getRessources().getNom();
				if (i != paquetOuvriers.size() - 1)
					res += " - ";
			}
			System.out.println(res + ANSI_RESET);

			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			}
			
			if (this.monnaie.equals("sesterces")) {
				ArrayList<Esclave> esclaves = ((PlateauAntiquite) p).getPaquetEsclaves();
				ArrayList<Emprunt> emprunts = ((PlateauAntiquite) p).getPaquetEmprunt();
				ArrayList<Outil> outils = ((PlateauAntiquite) p).getPaquetOutils();
				ArrayList<Universite> universites = ((PlateauAntiquite) p).getPaquetUniversite();
				if(!esclaves.isEmpty()) {
				System.out.println(ANSI_PURPLE + "Esclaves actuels sur le plateau :" + ANSI_RESET);
				res = ANSI_BLACK;
				for (int i = 0; i < esclaves.size(); i++) {
					res += esclaves.get(i).getRessources().getNom();
					if (i != esclaves.size() - 1)
						res += " - ";
				}
				System.out.println(res + ANSI_RESET);

				System.out.println(ANSI_CYAN
						+ "---------------------------------------------------------------------------------------------------"
						+ ANSI_RESET);
				}
				if(!universites.isEmpty()) {

				System.out.println(ANSI_PURPLE + "Universités actuelles sur le plateau :" + ANSI_RESET);
				res = ANSI_BLACK;
				for (int i = 0; i < universites.size(); i++) {
					res += universites.get(i).getRessources().getNom();
					if (i != universites.size() - 1)
						res += " - ";
				}
				System.out.println(res + ANSI_RESET);

				System.out.println(ANSI_CYAN
						+ "---------------------------------------------------------------------------------------------------"
						+ ANSI_RESET);
				}
				if(!outils.isEmpty()) {

				System.out.println(ANSI_PURPLE + "Outils actuels sur le plateau:" + ANSI_RESET);
				res = ANSI_BLACK;
				for (int i = 0; i < outils.size(); i++) {
					res += outils.get(i).getRessources().getNom();
					if (i != outils.size() - 1)
						res += " - ";
				}
				System.out.println(res + ANSI_RESET);

				System.out.println(ANSI_CYAN
						+ "---------------------------------------------------------------------------------------------------"
						+ ANSI_RESET);
				}
				if(!emprunts.isEmpty()) {

				System.out.println(ANSI_BLACK + "Il ne reste plus que " + emprunts.size()
						+ " emprunts disponibles sur le plateau" + ANSI_BLACK);

				System.out.println(ANSI_CYAN
						+ "---------------------------------------------------------------------------------------------------"
						+ ANSI_RESET);
				}
			}
		}
	}

	/**
	 * Termine la partie et affiche le ou les gagnants
	 *
	 * @param gagnants : Liste des gagnants
	 *
	 */
	public void finDePartie(ArrayList<Joueur> gagnants) {
		if (isVerbose) {
			System.out.println(ANSI_BLUE + "Fin de partie." + ANSI_RESET);
			System.out.println(ANSI_RED + "Gagnant(s) ayant le plus de points victoires et " + monnaie + ":");
			for (Joueur j : gagnants)
				System.out.println("Joueur " + j.getNb() + " : " + (j.getPtsVictoire()-j.getMalusdecompte()) + " de points victoires et "
						+ j.getTrancheDeDix() + " tranche de 10 " + monnaie + "." + ANSI_RESET);
		}
	}

	/**
	 * Affiche le numero de joueur qui recrute un ouvrier
	 *
	 * @param nb : le numero du joueur qui recrute un ouvrier,
	 * @param o  : l'ouvrier qui est recrute par le joueur.
	 */
	public void recruterOuvrier(int nb, Ouvrier o) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a recruté l'ouvrier \"" + o.toString()
					+ "\"." + ANSI_RESET);

	}

	/**
	 * Affiche le numero de joueur qui recrute un ouvrier
	 *
	 * @param ecus        : le nombre d'ecus.
	 * @param nb          : Le numéro du joueur qui a acheté l'action
	 * @param totalAction : le nombre total d'action.
	 */
	public void achatActionSucces(int nb, int ecus, int totalAction) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a acheté une action supplementaire pour 5 " + monnaie
					+ ". Il possède maintenant " + ecus + " " + monnaie + "." + ANSI_RESET);
	}

	/**
	 * Affiche le numero de joueur qui recrute un ouvrier
	 *
	 * @param ecus : le nombre d'"+monnaie+".
	 * @param nb   : Le numéro du joueur qui a acheté l'action
	 */
	public void achatActionEchec(int nb, int ecus) {
		if (isVerbose)
			System.out.println(ANSI_RED + "Le joueur " + nb + " a essayé d'acheter une action, mais il n'a pas assez d'"
					+ monnaie + "!" + ANSI_RESET);
	}

	/**
	 * Permet d'afficher les ecus echange par des actions par un joueur
	 * @param actionsechangees : le nombres d'actions que le joueur souhaiterait
	 *                         échanger
	 * @param nb               : numero de joueur
	 * @param nbecus           : nombre d'ecus pris
	 * @param ecus             : nombre d'ecus total que le joueur possede
	 */
	public void echangerEcus(int nb, int actionsechangees, int nbecus, int ecus) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a echangé " + actionsechangees + " action(s) contre "
					+ nbecus + " " + monnaie + ". Il possède maintenant " + ecus + " " + monnaie + "." + ANSI_RESET);
	}

	/**
	 *Affiche lorsqu'un chantier d'un joueur se termine
	 * @param nb : numero de joueur
	 * @param b  : le batiment qui est terminé
	 */
	public void chantierTermine(int nb, Batiment b) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le chantier " + b.getRessources().getNom() + " du joueur " + nb
					+ " est terminé !" + ANSI_RESET);
	}

	/**
	 * Affiche la machine crée en identifiant le joueur
	 * @param nb : numero de joueur
	 * @param m  : La machine qui a été crée
	 */
	public void machineCree(int nb, Machine m) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " à crée la machine " + m.getRessources().getNom() + ":"
					+ m.getRessources().getPierre() + " pierre - " + m.getRessources().getBois() + " bois - "
					+ m.getRessources().getSavoir() + " savoir - " + m.getRessources().getTuile() + " tuile - "
					+ m.getRessources().getPtsVictoire() + ANSI_BLUE + " points de victoire - " + ANSI_GREEN
					+ m.getRessources().getEcusRecompense() + " ecus reçus." + ANSI_RESET);
	}

	/**
	 *Affiche lorsqu'un joueur envoi un ouvrier travailler ou le cas échéant quand il n'est pas possible de l'envoyer
	 * @param numJoueur : le joueur
	 * @param ouvrier   : l'ouvrier qui part travailler
	 * @param batiment  : le chantier
	 * @param nbecus    : le nombre d'"+monnaie+" nécessaires pour envoyer
	 *                  travailler
	 * @param resultat  : un code erreur si "envoyer travailler" a fonctionné ou non
	 */
	public void envoyerOuvrierTravailler(int numJoueur, Ouvrier ouvrier, Batiment batiment, int nbecus, int resultat) {
		if (isVerbose) {
			// ecus insuffisant
			if (resultat == 1)
				System.out.println(ANSI_RED + "Le joueur " + numJoueur + " NE PEUT PAS envoyer "
						+ ouvrier.getRessources().getNom() + " sur le chantier " + batiment.getRessources().getNom()
						+ " car il n'a que " + nbecus + " " + monnaie + "." + ANSI_RESET);
			// nb actions insuffisant
			else if (resultat == 2)
				System.out.println(ANSI_RED + "Le joueur " + numJoueur + " NE PEUT PAS envoyer "
						+ ouvrier.getRessources().getNom() + " sur le chantier " + batiment.getRessources().getNom()
						+ " car il N'A PAS le nombre d'actions nécessaires!" + ANSI_RESET);
			// batiment déjà fini
			else if (resultat == 3)
				System.out.println(ANSI_RED + "Le bâtiment " + batiment + " du joueur " + numJoueur
						+ " est déjà fini ! Pas d'ouvrier nécessaire!" + ANSI_RESET);
			// ouvrier déjà occupé
			else if (resultat == 4)
				System.out.println(ANSI_RED + "Le joueur " + numJoueur + " NE PEUT PAS envoyer "
						+ ouvrier.getRessources().getNom() + " sur le chantier " + batiment.getRessources().getNom()
						+ " car l'ouvrier est déjà occupé !" + ANSI_RESET);
			// cas normal
			else if (resultat == 5)
				System.out.println(ANSI_GREEN + "Le joueur " + numJoueur + " a envoyé travailler \""
						+ ouvrier.getRessources().getNom() + "\" sur le chantier \"" + batiment.getRessources().getNom()
						+ "\" contre " + nbecus + " " + monnaie + "." + ANSI_RESET);
		}
	}

	/**
	 * Affiche la fin de la construction d'un batiment par l'un des joueurs et les récompenses obtenue
	 * @param nomBat      : le batiment qu'on a fini
	 * @param numJoueur   : le numéro du joueur qui l'a fini pour l'identifier
	 * @param nbEcus      : Le nombre d'écus que donne le batiment après l'avoir
	 *                    terminé
	 * @param ptsvictoire : Le nombre de points victoires remportés par le joueur
	 */
	public void finBatiment(String nomBat, int numJoueur, int nbEcus, int ptsvictoire) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + numJoueur + " a fini le chantier \"" + nomBat
					+ "\"! Comme récompense il obtient " + nbEcus + " " + monnaie + " et " + ANSI_BLUE + ptsvictoire
					+ " points victoire!" + ANSI_RESET);
	}

	/**
	 * Affiche la fin de la construction d'une machine par l'un des joueurs et les récompenses obtenue
	 * @param nomBat       : le batiment qu'on a fini
	 * @param nomMach      : le nom de la machine
	 * @param numJoueur:   le numéro du joueur qui l'a fini pour l'identifier
	 * @param nbEcus:      Le nombre d'écus que donne le batiment après l'avoir
	 *                     terminé
	 * @param ptsvictoire: Le nombre de points victoires remportés par le joueur
	 */
	public void finMachine(String nomBat, String nomMach, int numJoueur, int nbEcus, int ptsvictoire) {
		if (isVerbose)
			System.out.println(ANSI_GREEN + "Le joueur " + numJoueur + " a fini le machine \"" + nomBat
					+ "\"! Comme récompense il obtient " + nbEcus + " " + monnaie + " et " + ANSI_BLUE + ptsvictoire
					+ " points victoire! " + ANSI_GREEN + " Il obtient aussi le nouvel ouvrier \"" + nomMach + "\"!"
					+ ANSI_RESET);
	}

	/**
	 * Annonce le debut du tour
	 * @param i : le numéro du tour
	 */
	public void debutTour(int i) {
		if (isVerbose) {
			System.out.println(ANSI_BLUE + "Début du tour " + i + "." + ANSI_RESET);
		System.out.println(ANSI_CYAN
				+ "---------------------------------------------------------------------------------------------------"
				+ ANSI_RESET);
		}

	}

	/**
	 * 
	 * Affiche les statistiques du ou des gagnant(s)
	 * @param gagnants : les joueurs qui jouent sur le plateau
	 */
	public void afficherStatistiques(ArrayList<Joueur> gagnants) {
		if(isVerbose) {
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			System.out.println(ANSI_BLUE + "Statistiques de fin de partie :" + ANSI_BLACK);

			ArrayList<String> lignes = new ArrayList<>();
			lignes.add("joueurs ");

			lignes.add("points : batiments ");

			lignes.add("points : ecus ");

			lignes.add(monnaie+" possèdés");

			lignes.add("chantiers ouverts ");

			lignes.add("ouvriers recrutés ");

			lignes.add("ouvriers envoyés travailler ");

			lignes.add(monnaie+" pris ");

			lignes.add("achat action ");

			lignes.add(monnaie+" depensés ouvrier ");

			lignes.add("revenus batiments ");

			lignes.add("machines construites ");		
			
			if(jeu.equals("ANTIQUITE")) {
				lignes.add("achat : outils ");
				
				lignes.add("outils attribués ");
				
				lignes.add("achat : esclave ");
				
				lignes.add("emprunts contractés ");
				
				lignes.add("points : malus decompte final ");
			}
			
			String res = "";
			int length = getMaxLength(lignes);
			lignes = remplirEspaces(length, lignes);

			for (Joueur j : gagnants) {
				lignes.set(0, lignes.get(0) + j.getNb() + "(" + (j.getPtsVictoire() + j.getTrancheDeDix()+j.getMalusdecompte()) + ")");
				lignes.set(1, lignes.get(1) + j.getPtsVictoire());
				lignes.set(2, lignes.get(2) + j.getTrancheDeDix());
				lignes.set(3, lignes.get(3) + j.getNbEcus());
				lignes.set(4, lignes.get(4) + j.getStats().getnbOuvrirBatiment());
				lignes.set(5, lignes.get(5) + j.getStats().getRecruter());
				lignes.set(6, lignes.get(6) + j.getStats().getEnvoyerTravailler());
				lignes.set(7, lignes.get(7) + j.getStats().getPrendreEcus());
				lignes.set(8, lignes.get(8) + j.getStats().getAchatAction());
				lignes.set(9, lignes.get(9) + j.getStats().getDepenseOuvrier());
				lignes.set(10, lignes.get(10) + j.getStats().getRevenuBatiment());
				lignes.set(11, lignes.get(11) + j.getStats().getMachineConstruite());
				if(jeu.equals("ANTIQUITE")) {
					lignes.set(12, lignes.get(12) + j.getStats().getAchatOutil());
					
					lignes.set(13, lignes.get(13) + j.getStats().getDonnerOutil());
					
					lignes.set(14, lignes.get(14) + j.getStats().getAchatEsclave());
					
					lignes.set(15, lignes.get(15) + j.getStats().getContracterEmprunt());
					lignes.set(16, lignes.get(16) + j.getMalusdecompte());

				}
				
				length = getMaxLength(lignes);
				lignes = remplirEspaces(length, lignes);
			}
			for (String s : lignes) {
				res += s + "\n";
			}
			System.out.println(res);
		}
		
	}

	/**
	 * Permet de combler les espaces pour creer notre tableau
	 * @param length : la taille 
	 * @param lignes : les lignes de statistiques que l'on veut afficher
	 * @return
	 */
	private ArrayList<String> remplirEspaces(int length, ArrayList<String> lignes) {
		for (int i = 0; i < lignes.size(); i++) {
			lignes.set(i, String.format("%-" + length + "s", lignes.get(i)) + "|");
		}
		return lignes;
	}

	/**
	 * Getter qui permet d'obtenir la taille maximum d'une stat (chiffre)
	 * @param lignes : tableau de ligne des stats 
	 * @return la taille maximum
	 */
	private int getMaxLength(ArrayList<String> lignes) {
		int maxLength = 0;
		for (String s : lignes) {
			if (maxLength < s.length())
				maxLength = s.length();
		}
		return maxLength;
	}

	/**
	 * Affiche le cas ou la partie est nulle sans gagnant
	 */
	public void partieNulle() {
		if(isVerbose) {
			System.out.println(ANSI_RED+"Il y'a eu 20 tours consécutif sans action : la partie est bloquée, c'est un match NUL"+ANSI_RESET);
		}
		
	}

	/**
	 * Affiche le moment ou un joueur achète un esclave
	 * @param nb : numero du joueur
	 * @param e : l'esclave acheté
	 */
	public void achatEsclave(int nb, Esclave e) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a acheté l'esclave \"" + e.toString()
					+ "\"." + ANSI_RESET);
		}
		
	}

	/**
	 * 
	 * @param nb : id du joueur
	 * @param o : outil acheté
	 */
	public void acheterOutil(int nb, Outil o) {
		 if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a acheté l'outil \"" + o.toString()
					+ "\"." + ANSI_RESET);
		}
		
	}

	/**
	 * Affiche une compensation d ecus envers un joueur
	 * @param nb : le numero de joueur
	 * @param i : Le montant de la compensation
	 */
	public void compensationEcus(int nb, int i) {
		 if(isVerbose) {
				System.out.println(ANSI_GREEN + "Le joueur " + nb + " reçoit une compensation de "+i+" "+monnaie+"." + ANSI_RESET);
			}
	}

	/**
	 * Afficher lorsqu'un joueur affranchit un esclave
	 * @param nb : numero de joueur
	 * @param coutTravail : cout de travail
	 */
	public void affranchirEsclave(int nb, int coutTravail) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a affranchi un esclave! Celui ci côutera maintenant "+coutTravail+" "+monnaie+"."+ ANSI_RESET);
		}
	}
	
	/**
	 * Afficher lorsque la partie se termine, les esclaves qiu peuvent être affranchi et non
	 * @param nb : numero joueur
	 * @param nbEsclave : nombre d esclave affranci
	 * @param esclave : liste d esclave non affranchi
	 */
	public void affranchirEsclaveDecompte(int nb, int nbEsclave, ArrayList<Esclave> esclave) {
		if(isVerbose) {
			if(esclave.size()==0)
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a affranchi " + nbEsclave + " esclave(s)!"+ ANSI_RESET);
			else
				System.out.println(ANSI_GREEN + "Le joueur " + nb + " a affranchi " + nbEsclave + " esclave(s)! "+ "Il lui en reste "+esclave.size()+" à affranchir. Il perd "+esclave.size()+" point(s) victoire(s)!"+ANSI_RESET);

		}
	}
	
	/**
	 * Permet d afficher lorsqu'un joueur contract un emprunt
	 * @param nb : numero de joueur qui contract l emprunt
	 */
	public void contracterEmprunt(int nb) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur " + nb + " a contracté un emprunt de 10 "+monnaie+". Il devra le rembourser en fin de partie."+ ANSI_RESET);
		}		
	}

	/**
	 * Permet d afficher lorsqu'un joueur instruit un ouvrier
	 * @param nb : numero de joueur
	 * @param nomo : le nom de l ouvrier
	
	 */
	public void instruireOuvrier(int nb, String nomo) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur" +nb+" a envoyé l'ouvrier \"" + nomo + "\" s'instruire à l'université pour 7 écus!"+ ANSI_RESET);
		}			
	}

	/**
	 * Permet d afficher lorsqu'un joueur donne un outil à son ouvrier
	 * @param nb : numero de joueur 
	 * @param nomo : nom de l ouvrier
	 * @param nomt : nom de l outil
	 */
	public void donnerOutil(int nb, String nomo, String nomt) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur" +nb+" a donné à l'ouvrier \"" +nomo+"\" l'outil \""+nomt+"\"."+ ANSI_RESET);
		}	
		
	}

	/**
	 * Permet d afficher lorsque le joueur recupère l'outil de l'ouvrier
	 * @param nb : numero de joueur
	 * @param nomt : nom de l outil
	 * @param nomo : nom de l'ouvrier
	 */
	public void retrouverOutil(int nb, String nomt, String nomo) {
		if(isVerbose) {
			System.out.println(ANSI_GREEN + "Le joueur" +nb+" a recuperé l'outil \"" +nomt+"\" de l'ouvrier \""+nomo+"\"."+ ANSI_RESET);
		}			
	}

	/**
	 * Permet d'afficher si le joueur rembourse son emprunt ou non
	 * @param nb : numero joueur
	 * @param empruntsRembourse : valeur dece qui a été remboursé
	 * @param ptsVictoirePenalite : Le nombre de point de victoire enlevé en cas de non remboursement
	 */
	public void rembourserEmprunt(int nb, int empruntsRembourse,int ptsVictoirePenalite) {
		String res = ANSI_GREEN;
		if(isVerbose) {
			if(empruntsRembourse>0)
				res+="Le joueur " + nb + " a remboursé "+empruntsRembourse+" emprunt(s)!";
			if(ptsVictoirePenalite>0)
				res+="Le joueur " + nb + " a remboursé "+empruntsRembourse+" emprunt(s)! Il lui reste "+ptsVictoirePenalite+" emprunts à rembourser. Il perd donc "+ptsVictoirePenalite*2+" points victoires";
			
			System.out.println(res+ANSI_RESET);
		}	
	}
	
	/**
	 * Affiche l'annonce du decompte final
	 */
	public void decompteFinal() {
		if(isVerbose) 
			System.out.println(ANSI_BLUE+"Decompte final. ");
		}

	/**
	 * Apparaît lorsqu'il ny'a pas besoin de faire de decompte final
	 */
	public void pasDecompte() {
		if(isVerbose) 
			System.out.println(ANSI_BLUE + "Il n'y avait aucun esclave à affranchir ou emprunt à rembourser..."+ ANSI_RESET);		
	}
	
	/**
	 * Affiche le debut des multi parties que l'on veut executer
	 * @param nbp : nombre de partie lancé
	 */
	public static void debutMultiParties(int nbp) {
		System.out.println(ANSI_BLUE + "Execution des "+nbp+" parties..." + ANSI_BLACK);
	}
	
	/**
	 * Affichage des statistiques de multi parties joué 
	 * @param grandgagnants : les gagnants après les malus du decompte final
	 * @param nbparties : nombre de parties totales lancées
	 */
	public void affichageMultiParties(HashMap<Integer, Statistiques> grandgagnants, int nbparties) {
			System.out.println(ANSI_CYAN
					+ "---------------------------------------------------------------------------------------------------"
					+ ANSI_RESET);
			System.out.println(ANSI_BLUE + "Statistiques finales :" + ANSI_BLACK);

			ArrayList<String> lignes = new ArrayList<>();
			lignes.add("joueurs ");

			lignes.add("parties gagnées ");

			lignes.add("parties gagnées % ");

			lignes.add("chantiers ouverts ");

			lignes.add("ouvriers recrutés ");

			lignes.add("ouvriers envoyés travailler ");

			lignes.add(monnaie+" pris ");

			lignes.add("achat action ");

			lignes.add(monnaie+" depensés ouvrier ");

			lignes.add("revenus batiments ");

			lignes.add("machines construites ");		
			
			if(jeu.equals("ANTIQUITE")) {
				lignes.add("achat : outils ");
				
				lignes.add("outils attribués ");
				
				lignes.add("achat : esclave ");
				
				lignes.add("emprunts contractés ");
				}
			
			String res = "";
			
			int length = getMaxLength(lignes);
			lignes = remplirEspaces(length, lignes);
			Set<Integer> keys = grandgagnants.keySet();
			Iterator<Integer> i = keys.iterator();
			while (i.hasNext()) {
				int key = i.next();
				lignes.set(0,lignes.get(0) +key);
				lignes.set(1,lignes.get(1) +grandgagnants.get(key).getGagnant());
				double purcentage = (double)grandgagnants.get(key).getGagnant()/nbparties*100;
				lignes.set(2,lignes.get(2) +Math.round(purcentage)+"%");
				lignes.set(3, lignes.get(3) + grandgagnants.get(key).getnbOuvrirBatiment());
				lignes.set(4, lignes.get(4) + grandgagnants.get(key).getRecruter());
				lignes.set(5, lignes.get(5) + grandgagnants.get(key).getEnvoyerTravailler());
				lignes.set(6, lignes.get(6) + grandgagnants.get(key).getPrendreEcus());
				lignes.set(7, lignes.get(7) + grandgagnants.get(key).getAchatAction());
				lignes.set(8, lignes.get(8) + grandgagnants.get(key).getDepenseOuvrier());
				lignes.set(9, lignes.get(9) + grandgagnants.get(key).getRevenuBatiment());
				lignes.set(10, lignes.get(10) + grandgagnants.get(key).getMachineConstruite());
				if(jeu.equals("ANTIQUITE")) {
					lignes.set(11, lignes.get(11) + grandgagnants.get(key).getAchatOutil());
					
					lignes.set(12, lignes.get(12) + grandgagnants.get(key).getDonnerOutil());
					
					lignes.set(13, lignes.get(13) + grandgagnants.get(key).getAchatEsclave());
					
					lignes.set(14, lignes.get(14) + grandgagnants.get(key).getContracterEmprunt());

				}
				length = getMaxLength(lignes);
				lignes = remplirEspaces(length, lignes);
				
			}
			for (String s : lignes) {
				res += s + "\n";
			}
			System.out.println(res);
		}

	/**
	 * Affichage l'attente de connection du joueur
	 * @param nbjpasco : le nom du joueur
	 */
	public static void attenteJoueurs(int nbjpasco) {
		System.out.println(ANSI_GREEN+"En attente de connexion de "+nbjpasco+" joueur(s).");
	}

	/**
	 * Affiche le succès de connection du joueur 
	 */
	public static void connectionClientSuccess() {
		System.out.println(ANSI_GREEN+"Un joueur s'est connecté à la partie!");
	}

	/**
	 * S affiche lorsque tous les joueurs sont connectés
	 * @param nbj : Le nombre de joueur
	 */
	public static void finAttenteJoueurs(int nbj) {
		System.out.println(ANSI_GREEN+"Tous les "+nbj+" joueurs se sont connectés!");
		System.out.println(ANSI_CYAN
				+ "---------------------------------------------------------------------------------------------------"
				+ ANSI_RESET);
		
	}
	
	public void finMultiPartie(int index) {
		System.out.println(ANSI_GREEN+"Fin de la partie n°"+index);	
	}
}


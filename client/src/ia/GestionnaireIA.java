package ia;

import java.util.ArrayList;

import java.util.Random;

import cartes.Batiment;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoPlateau;
import infojeu.InfoRepetition;
/**
 *  *
 *	Gestionnaire d'IA, regroupe toutes les difficultés d'IA et possède leur méthode qui ne changent pas d'une IA à une autre
 * * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo

 */
public abstract class GestionnaireIA{
	/**
	 * 
	 */
	protected InfoRepetition ir = new InfoRepetition();
	protected ChoixCartes cc = new ChoixCartes();
	
	/**
	 * Methode qui permet  d'obtenir l'un des deux meilleur batiment parmis les deux moins rentable
	 * @param batiments : liste de batiments
	 * @param r         : random permettant la réalisation des tests
	 * @return temp2.get(res) : L'un des deux meilleur batiment de la liste (Les
	 *         deux moins rentables)
	 */
	public Batiment choixBatiment(ArrayList<Batiment> batiments, Random r) {
		return cc.choixBatiment(batiments, r);
	}

	/**
	 * Permet de retourner l'ouvrier le plus rentable à envoyer sur le batiment désigné
	 * @param ouvriersjoueur : La liste des ouvriers dans lequel on veut choisir un ouvrier à envoyer travailler
	 * @param nbecusj : nombre d'écus possèdés par le joueur
	 * @param b : le batiment sur lequel on veut envoyer un ouvrier
	 * @return Ouvrier : L'ouvrier le plus rentable à envoyer sur le batiment en paramètre
	 */
	public Ouvrier ouvrierTravailler(ArrayList<Ouvrier> ouvriersjoueur,int nbecusj, Batiment b) {
		return cc.ouvrierTravailler(ouvriersjoueur, nbecusj, b);
	}

	/**
	 * Permet de retourner l'un de deux ouvrier les plus rentable ou l'un des deux ouvrier les moins rentable selon l'IA et la difficulté
	 * @param ouvriers : La liste des ouvriers dans lequel on veut choisir un ouvrier
	 * @param r : un random pour tester
	 * @return Ouvrier : L'un des deux les plus rentables ou l'un des deux les moins rentable Ouvrier suivant le niveau de difficulté de l'IA
	 */
	public Ouvrier choixOuvrier(ArrayList<Ouvrier> ouvriers, Random r) {
		return cc.choixOuvrier(ouvriers, r);
	}
	
	public ArrayList<Ouvrier> getOuvriers(ArrayList<Ouvrier> ouvriersj){
		return cc.getOuvriers(ouvriersj);
	}
	
	/**
	 * Permet d obtenir l ouvrier qui a été choisi pour instruire
	 * @param ouvriersj : liste des ouvriers du joueurs
	 * @return Ouvrier : l'ouvrier a instruire
	 */
	public Ouvrier choixOuvrierInstruire(ArrayList<Ouvrier> ouvriersj) {
		return cc.choixOuvrierInstruire(ouvriersj);
		
	}
	/**
	 * Retourne le batiment le plus rentable suivant l etat du jeu et le joueur
	 * @param batimentsjoueur : la liste de batiments d'où on choisira quel est le batiment le plus rentable à cet état du jeu pour lui envoyer les ouvriers
	 * @param nbActions : nombres d'actions pour le joueur
	 * @return Batiment : le batiment le plus rentable
	 */
	public Batiment batimentTravailler(ArrayList<Batiment> batimentsjoueur, int nbActions) {
		return cc.batimentTravailler(batimentsjoueur, nbActions);
	}
	
	/**
	 * Retourne le choix d'esclave choisi le plus rentable parmis tous les esclaves
	 * @param esclaves : liste des esclaves
	 * @return Esclave : esclave le plus rentable
	 */
	public Esclave choixEsclave(ArrayList<Esclave> esclaves) {
		return cc.choixEsclave(esclaves);
	}
	
	/**
	 * Retourne l'outil le plus rentable selon le batiment et les ouvrier sur le plateau
	 * @param batimentsjoueur : liste des batiment du jouerus
	 * @param ouvrier : liste des ouvrier du joueur
	 * @param outil : liste des outils disponible
	 * @return Outil : outil le plus adequate
	 */
	public Outil choixOutil(ArrayList<Batiment> batimentsjoueur, ArrayList<Ouvrier>ouvrier,ArrayList<Outil> outil) {
		return cc.choixOutil(batimentsjoueur, ouvrier, outil);
	}
	/**
	 * renvoie une des actions que le joueur a choisi suivant l'état du jeu
	 * @param p : Le plateau où le jeu se déroule
	 * @param ouvriersj: les ouvriers du joueur
	 * @param outilsj: les outils du joueur
	 * @param batimentsj: les chantiers du joueur
	 * @param nbecus : le nb d'ecus du joueur
	 * @param ptsvictoire : les points victoire du joueur
	 * @param nbActions : le nombre d'action qu'il possède
	 * @param r : Random permettant de faire des tests (mocks)
	 * @return actionspossibles.get(new Random().nextInt(actionspossibles.size()) : renvoie une des actions que le joueur a choisi suivant l'état du jeu
	 */
	public abstract String choixAction(InfoPlateau p,ArrayList<Outil> outilsj, ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj,int nbecus, int ptsvictoire, int nbActions,Random r);
	/**
	 * Permet de determiner si le joueur fais l action ou pas 
	 * @param piocheOuvriers : la pioche d'ouvriers qui est sur le plateau
	 * @param ouvriersjoueur : Les ouvriers du joueur qui effectue l'action
	 * @return int : la rentabilité qui va déterminer si le joueur fait l'action ou pas
	 */
	public abstract int poidsRecruter(ArrayList<Ouvrier> piocheOuvriers,  ArrayList<Ouvrier> ouvriersjoueur);
	/**
	 * Permet de determiner si le joueur prend un ecu ou pas
	 * @param p : le plateau sur lequel se passe le jeu
	 * @param nbecus : le nb d'ecus du joueur
	 * @param ptsvictoire : les points victoire du joueur
	 * @param coutAction : le nombre d'action que veut échanger le joueur
	 * @return int : la rentabilité qui va déterminer si le joueur fait l'action ou pas
	 */
	public abstract int poidsPrendreEcus(InfoPlateau p, int nbecus, int ptsvictoire,int coutAction);
	/**
	 * la rentabilité qui va déterminer si le joueur ouvre un chantier ou pas
	 * @param piocheBatimentsDuPlateau : la pioche des bâtiments sur le plateau
	 * @param ouvriersj: les ouvriers du joueur
	 * @param batimentsj: les chantiers du joueur
	 * @return int : la rentabilité qui va déterminer si le joueur fait l'action ou pas
	 */
	public abstract int poidsOuvrirChantier(ArrayList<Batiment> piocheBatimentsDuPlateau, ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj);
	/**
	 * la rentabilité qui va déterminer si le joueur acheter une action ou pas
	 * @param ouvriersj: les ouvriers du joueur
	 * @param batimentsj: les chantiers du joueur
	 * @param nbecus : le nb d'ecus du joueur
	 * @param ptsvictoire : les points victoire du joueur
	 * @param action : le nombre d'action que possède le joueur
	 * @return int : la rentabilité qui va déterminer si le joueur fait l'action
	 */
	public abstract int poidsAchatAction(ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj,int nbecus, int ptsvictoire, int action);
	/**
	 * la rentabilité qui permet de déterminer sil faut envoyer l ouvrier travailler ou pas
	 * @param ouvriersj: les ouvriers du joueur
	 * @param batimentsj: les chantiers du joueur
	 * @param nbecus : le nb d'ecus du joueur
	 * @param ptsvictoire : les points victoire du joueur
	 * @param nbActions : le nombre d'action qu'il possède
	 * @return int : la rentabilité qui va déterminer si le joueur fait l'action ou pas
	 */
	public abstract int poidsEnvoyerOuvrierTravailler(ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj,int nbecus, int ptsvictoire, int nbActions);

	
	
	public void resetGI(){
		ir.resetGI();
	}

	
	public void setInfoRepetition(InfoRepetition ir) {
		this.ir = ir;
	}

	public void setInvestissement(boolean investissement) {
		ir.setInvestissement(investissement);
	}

	public InfoRepetition getInfoRepetition() {
		return ir;
	}
}

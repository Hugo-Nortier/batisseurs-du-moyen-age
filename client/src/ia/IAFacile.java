package ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cartes.Batiment;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.InfoPlateau;
/**
 *  *
 *	IAFaible est une IA prenant des actions pas toujours très rentable, c'est la plus faible des IA
 * * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo

 */
public class IAFacile extends IADifficile {

	/**
	 * Permet de choisir les actions les plus adequate selon l etat du jeu
	 * @param outilsj : liste des outils du joueurs
	 * @param ouvriersj : liste des ouvriers du joueurs
	 * @param batimentsj : liste des batiments du joueurs
	 * @param nbecus : le nombre d ecus
	 * @param ptsvictoire : le nombre de points de victoie
	 * @param nbActions : le nombre d action
	 * @return String : string de l'action choisie
	 */
	public String choixAction(InfoPlateau p,ArrayList<Outil> outilsj, ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj,int nbecus, int ptsvictoire, int nbActions,Random r) {
		int envoyertravailler = 0;
		int prendreecus = 1;
		int recruterouvrier = 2;
		int ouvrirchantier = 3;
		int achataction = 4;
		int finirtour = 5;

		ArrayList<Integer> actionspossibles = new ArrayList<Integer>();

		int poidsRecruter = poidsRecruter(p.getPaquetOuvriers(), ouvriersj);
		int poidsOuvrir = poidsOuvrirChantier(p.getPaquetBatiments(), ouvriersj, batimentsj);

		if (nbActions == 0) {
			int poidsAchatAction = poidsAchatAction(ouvriersj, batimentsj,nbecus, ptsvictoire, nbActions);
			if (poidsAchatAction == 2)
				return "AcheterAction";
			if (poidsAchatAction == 3)
				actionspossibles.add(achataction);
			actionspossibles.add(finirtour);
		} else {
			int poidsEnvoyerTravailler = poidsEnvoyerOuvrierTravailler(ouvriersj, batimentsj,nbecus, ptsvictoire,nbActions);
			int poidsPrendreEcus = poidsPrendreEcus(p, nbecus,ptsvictoire, nbActions);
		

			if (poidsEnvoyerTravailler == 3)
				actionspossibles.add(envoyertravailler);
			if (poidsPrendreEcus == 3)
				actionspossibles.add(prendreecus);
			if (poidsOuvrir == 3)
				actionspossibles.add(ouvrirchantier);
			if (poidsRecruter == 3)
				actionspossibles.add(recruterouvrier);
		

		if (actionspossibles.isEmpty()) {
			if (poidsRecruter != -1)
				actionspossibles.add(recruterouvrier);
			if (poidsOuvrir != -1)
				actionspossibles.add(ouvrirchantier);
		}
		actionspossibles.add(5);
		actionspossibles.add(5);

		if (actionspossibles.isEmpty())
			return "FinirTour";
		}
		HashMap<Integer, String> actions = new HashMap<>();
		actions.put(envoyertravailler, "EnvoyerTravailler");
		actions.put(prendreecus, "PrendreEcus");
		actions.put(recruterouvrier, "RecruterOuvrier");
		actions.put(ouvrirchantier, "OuvrirChantier");
		actions.put(achataction, "AcheterAction");
		actions.put(finirtour, "FinirTour");
		return actions.get(actionspossibles.get(r.nextInt(actionspossibles.size())));
	}


	@Override
	public int poidsPrendreEcus(InfoPlateau p, int nbecus, int ptsvictoire,int action) {
		if(ir.isAcheteAction())
			return -1;
		if (p.getReserveEcus() == 0) {
			return -1;
		}
	
		return 3;
	}

	@Override
	public int poidsRecruter(ArrayList<Ouvrier> piocheOuvriers, ArrayList<Ouvrier> ouvriersjoueur) {
		if (piocheOuvriers.isEmpty())
			return -1;
		if (ouvriersjoueur.size() >= 6)
			return -1;
		return 3;
	}

	@Override
	public int poidsOuvrirChantier(ArrayList<Batiment> piocheBatimentsDuPlateau, ArrayList<Ouvrier> ouvriersj,
			ArrayList<Batiment> batimentsj) {
		if (piocheBatimentsDuPlateau.isEmpty())
			return -1;
		if (ouvriersj.isEmpty() || batimentsj.size() >= 6)
			return -1;
		
		return 3;
	}

	

	@Override
	public int poidsAchatAction(ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj, int nbecus,
			int ptsvictoire, int action) {
		if(ir.isPrisEcus())
			return -1;
		if (nbecus < 5)
			return -1;
		if (action > 0)
			return -1;
		if (!(batimentsj.isEmpty()) && !(ouvriersj.isEmpty())) {
			Batiment b = batimentTravailler(batimentsj,1);
			if(b==null)
				return -1;
			Ouvrier o = ouvrierTravailler(ouvriersj,nbecus, b);
			if(o==null)
				return -1;
			
		}
		return 3;
	}

	@Override
	public int poidsEnvoyerOuvrierTravailler(ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj, int nbecus,
			int ptsvictoire, int nbActions) {
		if (ouvriersj.isEmpty() || batimentsj.isEmpty()) {
			return -1;
		}
		boolean capacite = false;
		for (int i = 0; i < ouvriersj.size(); i++) {
			if (ouvriersj.get(i).getRessources().getEcus() <= nbecus) {
				capacite = true;
			}
			if (!capacite) {
				return -1;
			}
			Batiment b = this.batimentTravailler(batimentsj, nbActions);
			if(b==null)
				return -1;
			Ouvrier o = this.ouvrierTravailler(ouvriersj,nbecus, b);
			if(o==null)
				return -1;
			
		}

		return 3;
	}

	}



package ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cartes.Batiment;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import infojeu.InfoPlateau;
/**
 *  *
 *	IAFaible est une IA prenant des actions pas toujours très rentable, c'est la plus faible des IA
 * * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo

 */
public class IAFacileAntiquite extends IAFacile{
	

	@Override
	public String choixAction(InfoPlateau p, ArrayList<Outil> outilsj, ArrayList<Ouvrier> ouvriersj,
			ArrayList<Batiment> batimentsj, int nbecus, int ptsvictoire, int nbActions,Random r) {
		int envoyertravailler = 0;
		int prendreecus = 1;
		int recruterouvrier = 2;
		int ouvrirchantier = 3;
		int achataction = 4;
		int finirtour = 5;
		int contracteremprunt = 6;
		int instruireouvrier = 7;
		int acheteresclave = 8;
		int affranchiresclave = 9;
		int acheteroutil = 10;
		int donneroutil = 11;

		ArrayList<Integer> actionspossibles = new ArrayList<Integer>();
		ArrayList<Integer> actionsinvestir = new ArrayList<Integer>();
		ArrayList<Integer> actionsfinal = new ArrayList<Integer>();

		int poidsRecruter = poidsRecruter(p.getPaquetOuvriers(), ouvriersj);
		int poidsOuvrir = poidsOuvrirChantier(p.getPaquetBatiments(), ouvriersj, batimentsj);

		if (nbActions == 0) {
			int poidsAchatAction = poidsAchatAction(ouvriersj, batimentsj, nbecus, ptsvictoire, nbActions);
			if (poidsAchatAction == 2)
				return "AcheterAction";
			if (poidsAchatAction == 3)
				actionsfinal.add(achataction);
			actionsfinal.add(finirtour);
		} else {

			int poidsEnvoyerTravailler = poidsEnvoyerOuvrierTravailler(ouvriersj, batimentsj, nbecus, ptsvictoire,
					nbActions);
			int poidsPrendreEcus = poidsPrendreEcus(p, nbecus, ptsvictoire, nbActions);
			int poidsinstruireouvrier = poidsInstruireOuvrier(p, nbecus, ouvriersj);
			int poidsacheteresclave = poidsAcheterEsclave(p, nbecus);
			int poidsacheteroutil = poidsAcheterOutil(p, ptsvictoire);
			int poidsdonneroutil = poidsDonnerOutil(outilsj, ouvriersj, batimentsj, nbecus, ptsvictoire, nbActions);

			
			if (poidsEnvoyerTravailler == 3 && r.nextInt(100) < 40)
				actionspossibles.add(envoyertravailler);
			if (poidsPrendreEcus == 3 && r.nextInt(100) < 40)
				actionspossibles.add(prendreecus);
			if (poidsOuvrir == 3 && r.nextInt(100) < 40)
				actionspossibles.add(ouvrirchantier);
			if (poidsRecruter == 3 && r.nextInt(100) < 40)
				actionspossibles.add(recruterouvrier);

			if (poidsinstruireouvrier == 3 && r.nextInt(100) < 40)
				actionsinvestir.add(instruireouvrier);
			if (poidsacheteresclave == 3 && r.nextInt(100) < 40)
				actionsinvestir.add(acheteresclave);
		
			if (poidsacheteroutil == 3 && r.nextInt(100) < 40)
				actionsinvestir.add(acheteroutil);
			if (poidsdonneroutil == 3 && r.nextInt(100) < 40)
				actionsinvestir.add(donneroutil);
			if (actionspossibles.isEmpty()) {
				if (poidsRecruter != -1)
					actionspossibles.add(recruterouvrier);
				if (poidsOuvrir != -1)
					actionspossibles.add(ouvrirchantier);
			}
			
			if (actionsinvestir.isEmpty() && actionspossibles.isEmpty() && !ir.isInvestissement()
					&& !p.getPaquetEmprunt().isEmpty())
				return "ContracterEmprunt";
			else if (actionsinvestir.isEmpty() && actionspossibles.isEmpty())
				return "FinirTour";
			if (actionsinvestir.isEmpty())
				actionsfinal = actionspossibles;
			else if (actionspossibles.isEmpty())
				actionsfinal = actionsinvestir;
			else if (r.nextInt(100) < 75)
				actionsfinal = actionspossibles;
			else
				actionsfinal = actionsinvestir;
		}

		HashMap<Integer, String> actions = new HashMap<>();
		actions.put(envoyertravailler, "EnvoyerTravailler");
		actions.put(prendreecus, "PrendreEcus");
		actions.put(recruterouvrier, "RecruterOuvrier");
		actions.put(ouvrirchantier, "OuvrirChantier");
		actions.put(achataction, "AcheterAction");
		actions.put(finirtour, "FinirTour");
		actions.put(acheteresclave, "AcheterEsclave");
		actions.put(acheteroutil, "AcheterOutil");
		actions.put(affranchiresclave, "AffranchirEsclave");
		actions.put(instruireouvrier, "InstruireOuvrier");
		actions.put(donneroutil, "DonnerOutil");
		actions.put(contracteremprunt, "ContracterEmprunt");
		return actions.get(actionsfinal.get(r.nextInt(actionsfinal.size())));
	}
	public int poidsAcheterOutil(InfoPlateau p, int nbecus) {
		if (ir.isInvestissement())
			return -1;
		if (p.getPaquetOutil().isEmpty() || nbecus < 2) {
			return -1;
		}
		return 3;
	}

	public int poidsAcheterEsclave(InfoPlateau p, int nbecus) {
		if (ir.isInvestissement())
			return -1;
		if (p.getPaquetEsclaves().isEmpty() || nbecus < 5) {
			return -1;
		}
		return 3;
	}

	public int poidsInstruireOuvrier(InfoPlateau p, int nbecus, ArrayList<Ouvrier> ouvriersj) {
		if (ir.isInvestissement())
			return -1;
		if (p.getPaquetUniversite().isEmpty() || nbecus < 7 || getOuvriers(ouvriersj).isEmpty()) {
			return -1;
		}
		return 3;
	}

	public int poidsAffranchirEsclave(ArrayList<Ouvrier> ouvrierj, int ptsvictoire) {
		if (ir.isInvestissement())
			return -1;
		ArrayList<Esclave> esclavesj = new ArrayList<>();
		for (Ouvrier o : ouvrierj) {
			if (o instanceof Esclave) {
				if (!((Esclave) o).isAffranchi())
					esclavesj.add((Esclave) o);
			}
		}
		if (esclavesj.isEmpty())
			return -1;
		
		return -1;
	}

	public int poidsDonnerOutil(ArrayList<Outil> outilsj, ArrayList<Ouvrier> ouvriersj, ArrayList<Batiment> batimentsj,
			int nbecus, int ptsvictoire, int nbActions) {
		if (ir.isInvestissement())
			return -1;
		if (outilsj.isEmpty())
			return -1;
		int poidsenvoyer = poidsEnvoyerOuvrierTravailler(getOuvriers(ouvriersj), batimentsj, nbecus, ptsvictoire, nbActions);
		if (poidsenvoyer == -1 || poidsenvoyer == 0)
			return -1;
		return 3;
	}
}

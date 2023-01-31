package action;

import java.util.Random;

import cartes.Batiment;
import cartes.Machine;
import cartes.Ouvrier;
import infojeu.ChoixCartes;
import infojeu.InfoRepetition;
import jeu.Affichage;
import jeu.Joueur;
import jeu.Plateau;
/**
 * Permet au joueur d'envoyer travailler un ouvrier
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 * 	
 */
public class EnvoyerTravailler extends Action {


	@Override
	public int effectuerAction(Plateau p, Joueur j, Random r, int totalAction, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		return envoyerOuvrierTravailler(p,totalAction,j,a,ir,choixCartes);
	}
	
	/**
	 * action permettant d'envoyer un ouvrier travailler sur un batiment
	 * @param p : plateau sur lequel on joue
	 * @param nbActions : nombre d'actions que possède le joueur
	 * @param j : Joueur
	 * @param a : affichage
	 * @param ir : Pour savoir s'il y a blocage, si le joueur a déjà investi
	 * @param choixCartes : Pour savoir le choix de carte que l'IA a fait
	 * @return nbActions : nombre d'actions du joueurs
	 */
	public int envoyerOuvrierTravailler(Plateau p, int nbActions, Joueur j, Affichage a,InfoRepetition ir, ChoixCartes choixCartes) {
		j.getStats().reinitialiseAchatConse();

		Batiment batiment = choixCartes.batimentTravailler(j.getBatiments(), nbActions);
		Ouvrier ouvrier = choixCartes.ouvrierTravailler(j.getOuvriers(), j.getNbEcus(), batiment);
		int coutActions = batiment.getCoutActionTravail();
	
		// le joueur a les écus nécessaires et les conditions précédentes sont ok
		
			// le joueur paye plus ou moins d'actions selon si le chantier possède déjà ou
			// non un ouvrier présent et placé LE MEME TOUR

			if (nbActions >= coutActions) {
				// on ajoute au batiment l'ouvrier
				batiment.getOuvriers().add(ouvrier);

				j.getOuvriers().remove(ouvrier);
				// on paye
				nbActions -= coutActions;
				// on augmente le cout en actions du batiment
				batiment.addCoutActionTravail();
				batiment.enConstruction();
				j.ajoutNbEcus(-ouvrier.getRessources().getEcus());
				p.ajouterReserveEcus(ouvrier.getRessources().getEcus());
				// affichage cas classique où tout a fonctionné
				j.getStats().updateEnvoyerTravailler();
				j.getStats().updateDepenseOuvrier(ouvrier.getRessources().getEcus());
				a.envoyerOuvrierTravailler(j.getNb(), ouvrier, batiment, ouvrier.getRessources().getEcus(),
						5);
				j.getStats().updateDepenseOuvrier(ouvrier.getRessources().getEcus());
				boolean fini = batiment.finirBat(j.getIj(), p.getIp());
				if(fini) {
					j.getStats().updateRevenuBatiment(batiment.getRessources().getEcusRecompense());
					if (!(batiment instanceof Machine))
						j.getAffichage().finBatiment(batiment.getRessources().getNom(), j.getNb(), batiment.getRessources().getEcusRecompense(),
								batiment.getRessources().getPtsVictoire());
						
					else {
						j.getStats().updateMachineConstruire();
					j.getAffichage().finMachine(batiment.getRessources().getNom(), batiment.getRessources().getNom()+"_ouvrier", j.getNb(),
							batiment.getRessources().getEcusRecompense(), batiment.getRessources().getPtsVictoire());
					}
				}

			} else {
				
			}
			return nbActions;
		}
	}



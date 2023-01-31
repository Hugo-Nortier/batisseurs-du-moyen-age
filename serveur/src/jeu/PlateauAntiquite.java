package jeu;

import java.util.ArrayList;

import cartes.Emprunt;
import cartes.Esclave;
import cartes.Outil;
import cartes.Universite;

/**
 * 
 * 
 *
 *	Le plateau du jeu batisseur antiquité, il contient 2 pioches (batiments, ouvriers) et 2 paquets (batiments, ouvriers), ainsi que des paquets d'esclaves, d'outils, d'emprunt et d'université
 *  Une réserve d'écus qui commence à 40 quand le jeu commence
 *  Une carte Builder pour pouvoir créer des cartes avec les bonnes informations
 *  
 *  @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 */
public class PlateauAntiquite extends Plateau {
	

	/**
	 * Initialise le mode du jeu ANTIQUITE 
	 */
	public PlateauAntiquite() {

		super("ANTIQUITE");
	}

	/**
	 * Initialise le paquet d'esclave
	 */
	private void initPaquetEsclave() {
		getPaquetEsclaves().addAll(cb.getEscl());
	}

	/**
	 * Initialise le paquet d'emprunt
	 */
	private void initPaquetEmprunt() {
		getPaquetEmprunt().addAll(cb.getEmprunts());
	}

	/**
	 * Initialise le paquet d'outil
	 */
	private void initPaquetOutil() {
		getPaquetOutils().addAll(cb.getOutils());
	}

	/**
	 * Initialise le paquet d'Université
	 */
	private void initPaquetUniversite() {
		getPaquetUniversite().addAll(cb.getUniversites());
	}


	@Override
	public void initPartie(ArrayList<Joueur> joueurs) {
		super.cb.construireCartes("cartes_antiquite.csv");
		super.initPartie(joueurs);
		initPaquetEmprunt();
		initPaquetEsclave();
		initPaquetOutil();
		initPaquetUniversite();
	}

	public ArrayList<Esclave> getPaquetEsclaves() {
		return ip.getPaquetEsclaves();
	}

	public ArrayList<Outil> getPaquetOutils() {
		return ip.getPaquetOutil();
	}

	public ArrayList<Emprunt> getPaquetEmprunt() {
		return ip.getPaquetEmprunt();
	}

	public ArrayList<Universite> getPaquetUniversite() {
		return ip.getPaquetUniversite();
	}

	@Override
	public ArrayList<Joueur> getGagnants(ArrayList<Joueur> joueurs){
		boolean t=false;
		boolean affichage = false;
		Affichage b=joueurs.get(0).getAffichage();
		b.decompteFinal();
		for(int i=0;i<joueurs.size();i++) {
			int j=0;
			if(!joueurs.get(i).getEsclavesFinal().isEmpty()) {
				while(joueurs.get(i).getEsclavesFinal().size()!=0 && joueurs.get(i).getNbEcus()>=5) {

					joueurs.get(i).getEsclavesFinal().get(0).affranchir();
					joueurs.get(i).ajoutNbEcus(-5);
					j++;
				}
				joueurs.get(i).ajoutMalusdecompte(-joueurs.get(i).getEsclavesFinal().size());
				t=true;
				affichage = true;
				b.affranchirEsclaveDecompte(joueurs.get(i).getNb(), j,joueurs.get(i).getEsclavesFinal());
			}
			
		}
		
		for(int i=0;i<joueurs.size();i++) {
			int nbEmprunt=0;
			t=false;
				for(int j=0;j<joueurs.get(i).getEmprunts().size();j++) {
					t=true;
					if(joueurs.get(i).getNbEcus()>=15) {
						joueurs.get(i).setEcus(joueurs.get(i).getNbEcus()-15);
						nbEmprunt++;
						joueurs.get(i).getEmprunts().get(j).rembourser();
					}
				}
				int ptsvict=(joueurs.get(i).getEmprunts().size()-nbEmprunt)*2;
				if(ptsvict>0)
				joueurs.get(i).ajoutMalusdecompte(-ptsvict);
					
				if(joueurs.get(i).getEmprunts().size()-nbEmprunt>0 || nbEmprunt==0) {
					if(t) {
						affichage = true;
					b.rembourserEmprunt(joueurs.get(i).getNb(),nbEmprunt,joueurs.get(i).getEmprunts().size()-nbEmprunt);
					}

				}
		
		}
		if(!affichage)
			b.pasDecompte();
		ArrayList<Joueur>gagnants=new ArrayList<Joueur>();

		gagnants.add(joueurs.get(0));
		double score=joueurs.get(0).getNbEcus()*0.1+joueurs.get(0).getPtsVictoire()+joueurs.get(0).getMalusdecompte();
		
		for(int i=1;i<joueurs.size();i++) {
			double score2=joueurs.get(i).getNbEcus()*0.1+joueurs.get(i).getPtsVictoire()+joueurs.get(i).getMalusdecompte();
			if(score2>score){
				score=score2;
				gagnants.removeAll(gagnants);
				gagnants.add(joueurs.get(i));
			}
			else if(score2==score) {
				gagnants.add(joueurs.get(i));
			}
		}
		
		return gagnants;
	}
		
		
		

}

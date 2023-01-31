package infojeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import cartes.Batiment;
import cartes.Esclave;
import cartes.Machine;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;

public class ChoixCartes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7489155835743510080L;

	/**
	 * Methode qui permet  d'obtenir l'un des deux meilleur batiment parmis les deux moins rentable
	 * @param batiments : liste de batiments
	 * @param r         : random permettant la réalisation des tests
	 * @return temp2.get(res) : L'un des deux meilleur batiment de la liste (Les
	 *         deux moins rentables)
	 */
	public Batiment choixBatiment(ArrayList<Batiment> batiments, Random r) {
		ArrayList<Batiment> temp = new ArrayList<Batiment>();
		ArrayList<Batiment> temp2 = new ArrayList<Batiment>();
		for (int i = 0; i < batiments.size(); i++) {
			temp.add(batiments.get(i));
		}
		if(temp.size()>2) {
		int rentabilite = temp.get(0).rentabilite();
		int indice = 0;
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i < temp.size(); i++) {
				if (temp.get(i).rentabilite() > rentabilite) {
					rentabilite = temp.get(i).rentabilite();

					indice = i;
				}
			}
			temp2.add(temp.remove(indice));
			indice = 0;
			rentabilite = temp.get(0).rentabilite();
		}
		Random random = r;
		int res = random.nextInt(2);

		return temp2.get(res);
		}
		else if(temp.size()==2) {
			Random random = r;
			int res = random.nextInt(2);

			return batiments.get(res);
		}
		else if(temp.size()==1) {
			return temp.get(0);
		}
		else {
			return null;
		}
	}

	/**
	 * Permet de retourner l'ouvrier le plus rentable à envoyer sur le batiment désigné
	 * @param ouvriersjoueur : La liste des ouvriers dans lequel on veut choisir un ouvrier à envoyer travailler
	 * @param nbecusj : nombre d'écus possèdés par le joueur
	 * @param b : le batiment sur lequel on veut envoyer un ouvrier
	 * @return Ouvrier : L'ouvrier le plus rentable à envoyer sur le batiment en paramètre
	 */
	public Ouvrier ouvrierTravailler(ArrayList<Ouvrier> ouvriersjoueur,int nbecusj, Batiment b) {
		ArrayList<Ouvrier> ouvriersdispo= new ArrayList<>();
		for(Ouvrier o : ouvriersjoueur) {
			if(o.getRessources().getEcus()==0)
				return o;
			if(o.getRessources().getEcus()<=nbecusj)
				ouvriersdispo.add(o);
		}
		if(ouvriersdispo.isEmpty())
			return null;
		if(ouvriersdispo.size()==1)
			return ouvriersdispo.get(0);
		int a = 0;
		
		int rent = b.boisRestant() - ouvriersdispo.get(0).getRessources().getBois()
				+ b.pierreRestant() - ouvriersdispo.get(0).getRessources().getPierre()
				+ b.savoirRestant() - ouvriersdispo.get(0).getRessources().getSavoir()
				+ b.tuileRestant() - ouvriersdispo.get(0).getRessources().getTuile()
				+ ouvriersdispo.get(0).getRessources().getEcus()*2;

		for (int i = 1; i < ouvriersdispo.size(); i++) {
			int res = b.boisRestant() - ouvriersdispo.get(i).getRessources().getBois()
					+ b.pierreRestant() - ouvriersdispo.get(i).getRessources().getPierre()
					+ b.savoirRestant() - ouvriersdispo.get(i).getRessources().getSavoir()
					+ b.tuileRestant() - ouvriersdispo.get(i).getRessources().getTuile()
					+ ouvriersdispo.get(i).getRessources().getEcus()*2;
		
			if (res < rent) {
				rent = res;
				a = i;
			}
		}
		return ouvriersdispo.get(a);
	}

	/**
	 * Permet de retourner l'un de deux ouvrier les plus rentable ou l'un des deux ouvrier les moins rentable selon l'IA et la difficulté
	 * @param ouvriers : La liste des ouvriers dans lequel on veut choisir un ouvrier
	 * @param r : un random pour tester
	 * @return Ouvrier : L'un des deux les plus rentables ou l'un des deux les moins rentable Ouvrier suivant le niveau de difficulté de l'IA
	 */
	public Ouvrier choixOuvrier(ArrayList<Ouvrier> ouvriers, Random r) {
		ArrayList<Ouvrier> temp = new ArrayList<Ouvrier>();
		ArrayList<Ouvrier> temp2 = new ArrayList<Ouvrier>();
		for (int i = 0; i < ouvriers.size(); i++) {
			temp.add(ouvriers.get(i));
		}
		if(temp.size()>2) {
		int rentabilite = temp.get(0).rentabilite();
		int indice = 0;
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i < temp.size(); i++) {
				if (temp.get(i).rentabilite() > rentabilite) {
					rentabilite = temp.get(i).rentabilite();

					indice = i;
				}
			}
			temp2.add(temp.remove(indice));
			indice = 0;
			rentabilite = temp.get(0).rentabilite();
		}
		Random random = r;
		int res = random.nextInt(2);

		return temp2.get(res);
		}
		else if(temp.size()==1) {
			return temp.get(0);
		}
		else if(temp.size()==2) {
			Random random = r;
			int res = random.nextInt(2);
			return ouvriers.get(res);
		}
		else
			return null;
	}
	
	public ArrayList<Ouvrier> getOuvriers(ArrayList<Ouvrier> ouvriersj){
		ArrayList<Ouvrier> ouvriers = new ArrayList<>();
		for(Ouvrier o : ouvriersj) {
			if(!(o instanceof Esclave))
				ouvriers.add(o);
			else if(((Esclave) o).isAffranchi())
				ouvriers.add(o);
		}
		return ouvriers;
	}
	
	/**
	 * Permet d obtenir l ouvrier qui a été choisi pour instruire
	 * @param ouvriersj : liste des ouvriers du joueurs
	 * @return Ouvrier : l'ouvrier a instruire
	 */
	public Ouvrier choixOuvrierInstruire(ArrayList<Ouvrier> ouvriersj) {
		ArrayList<Ouvrier> ouvriers = getOuvriers(ouvriersj);
		Ouvrier o=ouvriers.get(0);
		int cout=ouvriers.get(0).getRessources().getEcus();
		for(int i=0;i<ouvriers.size();i++) {
			if(cout>ouvriers.get(i).getRessources().getEcus()) {
				cout=ouvriers.get(i).getRessources().getEcus();
				o=ouvriers.get(i);
				
			}
			else if(cout==ouvriers.get(i).getRessources().getEcus()) {
				if(o.rentabilite()<ouvriers.get(i).rentabilite()) {
					o=ouvriers.get(i);
				}
			}
		}
		
		return o;
		
	}
	/**
	 * Retourne le batiment le plus rentable suivant l etat du jeu et le joueur
	 * @param batimentsjoueur : la liste de batiments d'où on choisira quel est le batiment le plus rentable à cet état du jeu pour lui envoyer les ouvriers
	 * @param nbActions : nombres d'actions pour le joueur
	 * @return Batiment : le batiment le plus rentable
	 */
	public Batiment batimentTravailler(ArrayList<Batiment> batimentsjoueur, int nbActions) {
		for (Batiment bt : batimentsjoueur) {
			if (bt instanceof Machine && bt.getCoutActionTravail()<=nbActions)
				return bt;
		}
		ArrayList<Batiment> batsconstr = new ArrayList<>();
		
		for(Batiment bt : batimentsjoueur) {
			if(bt.construction() && bt.getCoutActionTravail()<=nbActions)
				batsconstr.add(bt);
		}
		int j = 999999;
		int res = 999999999;
		if(batsconstr.isEmpty())
			batsconstr=batimentsjoueur;
		else return batsconstr.get(0);
		for (int i = 0; i < batsconstr.size(); i++) {
			int resBat = batsconstr.get(i).boisRestant() +batsconstr.get(i).savoirRestant()
					+ batsconstr.get(i).pierreRestant() + batsconstr.get(i).tuileRestant();
			if (res > resBat && batsconstr.get(i).getCoutActionTravail()<=nbActions) {
				res = resBat;
				j = i;
			}
		}
		
		if(j==999999)
			return null;
			
		return batimentsjoueur.get(j);
	}
	
	/**
	 * Retourne le choix d'esclave choisi le plus rentable parmis tous les esclaves
	 * @param esclaves : liste des esclaves
	 * @return Esclave : esclave le plus rentable
	 */
	public Esclave choixEsclave(ArrayList<Esclave> esclaves) {
			ArrayList<Esclave>escla=new ArrayList<Esclave>();
			Esclave e = null;
			escla.add(esclaves.get(0));
		int cout=esclaves.get(0).getRessources().getEcus();
		for(int i=1;i<esclaves.size();i++) {
			if(esclaves.get(i).getRessources().getEcus()<cout) {
				cout=esclaves.get(0).getRessources().getEcus();
				escla.removeAll(escla);
				escla.add(esclaves.get(i));
				
			}
			else if(esclaves.get(i).getRessources().getEcus()==cout) {
				escla.add(esclaves.get(i));

			}

		}
		
		if(escla.size()>1) {
			e=escla.get(0);
			int renta=escla.get(0).rentabilite();
			for(int i=0;i<escla.size();i++) {
				if(renta<escla.get(i).rentabilite()) {
					renta=escla.get(i).rentabilite();
					e=escla.get(i);
				}
			}
			return e;
		}
		else
			return escla.get(0);	
	}
	
	/**
	 * Retourne l'outil le plus rentable selon le batiment et les ouvrier sur le plateau
	 * @param batimentsjoueur : liste des batiment du jouerus
	 * @param ouvrier : liste des ouvrier du joueur
	 * @param outil : liste des outils disponible
	 * @return Outil : outil le plus adequate
	 */
	public Outil choixOutil(ArrayList<Batiment> batimentsjoueur, ArrayList<Ouvrier>ouvrier,ArrayList<Outil> outil) {
		int besoinressource=0;
		Outil o=null;
		for(int i = 0 ; i < batimentsjoueur.size();i++) {
			for(int j = 0 ; j < ouvrier.size(); j++) {
				for(int k = 0 ; k < outil.size(); k++) {
					if(outil.get(k).getRessources().getBois()!=0) {
						
						if(batimentsjoueur.get(i).boisRestant()> ouvrier.get(j).getRessources().getBois()) {
							if(besoinressource< batimentsjoueur.get(i).boisRestant()+ouvrier.get(j).getRessources().getBois()) {
								besoinressource = batimentsjoueur.get(i).boisRestant()+ouvrier.get(j).getRessources().getBois();
								o = outil.get(k); 
							}
						}
					}
					
					if(outil.get(k).getRessources().getPierre()!=0) {
						if(batimentsjoueur.get(i).pierreRestant()> ouvrier.get(j).getRessources().getPierre()) {
							if(besoinressource< batimentsjoueur.get(i).pierreRestant()+ouvrier.get(j).getRessources().getPierre()) {
								besoinressource = batimentsjoueur.get(i).pierreRestant()+ouvrier.get(j).getRessources().getPierre();
								o = outil.get(k); 
							}
						}
					}
					if(outil.get(k).getRessources().getTuile()!=0) {
						if(batimentsjoueur.get(i).tuileRestant()> ouvrier.get(j).getRessources().getTuile()) {
							if(besoinressource< batimentsjoueur.get(i).tuileRestant()+ouvrier.get(j).getRessources().getTuile()) {
								besoinressource = batimentsjoueur.get(i).tuileRestant()+ouvrier.get(j).getRessources().getTuile();
								o = outil.get(k); 
							}
						}
					}
						
					if(outil.get(k).getRessources().getSavoir()!=0) {
						if(batimentsjoueur.get(i).savoirRestant()> ouvrier.get(j).getRessources().getSavoir()) {
							if(besoinressource< batimentsjoueur.get(i).savoirRestant()+ouvrier.get(j).getRessources().getSavoir()) {
								besoinressource = batimentsjoueur.get(i).savoirRestant()+ouvrier.get(j).getRessources().getSavoir();
								o = outil.get(k); 
							}
						}
					}
				}			
			}
		}
		if(o==null)
			o=outil.get(0);
		return o;
	}

	public Universite choixUniversite(ArrayList<Universite> univ, Random r) {
		return univ.get(r.nextInt(univ.size()));
		
	}
}

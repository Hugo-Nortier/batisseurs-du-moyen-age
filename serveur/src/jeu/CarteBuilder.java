package jeu;

import java.io.*;
import java.util.*;

import cartes.Apprenti;
import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Machine;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;
/**
 * CarteBuilder permet de créer les cartes avec les vraies données du jeu
 * @author AHMED Saad El Din, BEN EL BEY Yessine, DIB Salim, HACHIMI Youssef, NORTIER Hugo
 *
 */
public class CarteBuilder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622051430922070011L;
	private ArrayList<Batiment> bat = new ArrayList<>();
	private ArrayList<Ouvrier> ouv = new ArrayList<>();
	private ArrayList<Machine> mach = new ArrayList<>();
	private ArrayList<Apprenti> appr = new ArrayList<>();
	private ArrayList<Esclave> escl = new ArrayList<>();
	private ArrayList<Emprunt> emprunts = new ArrayList<>();
	private ArrayList<Outil> outils = new ArrayList<>();
	private ArrayList<Universite> universites = new ArrayList<>();
	private boolean isAntiquite;
	
	public CarteBuilder(String s) {
		if(s.equals("MOYENAGE"))
			this.isAntiquite=false;
		else 
			this.isAntiquite=true;
	}

	/**
	 * Permet d'initialiser les cartes grâces aux information stocké dans le fichier csv en paramètre
	 * @param csvFile : le fichier CSV des cartes avec ";" comme séparateur
	 */
	public  void construireCartes(String csvFile) {
		BufferedReader br = null;
		String line = "";
		// NB: svp, dans le fichier csv, le ";" est utilisé comme séparateur
		String splitter = ";";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] items = line.split(splitter);
				for (int i = 0; i < items.length; i++) {
					if (items[i].equals(""))
						items[i] = "0";
				}
				
				// si on a pas des valeurs adéquates à la création des cartes
				if (!items[0].substring(0, 1).equals("#")) {

					// cartes apprentis!
					if (items[0].equals("apprenti"))
						appr.add(new Apprenti(items[0], cast(items[2]), cast(items[3]), cast(items[4]), cast(items[5]),
								cast(items[1])));
					if (items[0].equals("maitre") || items[0].equals("compagnon") || items[0].equals("manoeuvre"))
						// cartes ouvriers!
						ouv.add(new Ouvrier(items[0], cast(items[2]), cast(items[3]), cast(items[4]), cast(items[5]),
								cast(items[1])));
					
					if (items.length == 11) {
						// cartes batiments!
						if (items[7].equals("0") && items[8].equals("0") && items[9].equals("0")
								&& items[10].equals("0")) {
							bat.add(new Batiment(items[0], cast(items[1]), cast(items[2]), cast(items[3]),
									cast(items[4]), cast(items[6]), cast(items[5])));
						} else
							// cartes machines !
							mach.add(new Machine(items[0], cast(items[1]), cast(items[2]), cast(items[3]),
									cast(items[4]), cast(items[6]), cast(items[5]),
									new Ouvrier(items[0] + "_ouvrier", cast(items[7]), cast(items[8]),
											cast(items[9]), cast(items[10]), 0)));
					}
					
					if (isAntiquite == true) {
						// cartes esclaves
						if (items[0].equals("esclave"))
							escl.add(new Esclave(items[0], cast(items[1]), cast(items[2]), cast(items[3]),
									cast(items[4]), cast(items[7])));
						// cartes universites
						else if (items[0].equals("carte universite"))
							universites.add(new Universite(items[0], cast(items[1]), cast(items[2]), cast(items[3]),
									cast(items[4]), cast(items[5])));
						// cartes emprunt
						else if (items[0].equals("emprunt")) {
							for(int i=0;i<4;i++) {
								emprunts.add(new Emprunt(items[0], cast(items[1]), cast(items[2]), cast(items[3])));
							}	
						}
							
						 else  {
							// cartes outils
							 if(!(items[0].equals("apprenti")|| (items[0].equals("maitre")) || (items[0].equals("compagnon")) || (items[0].equals("manoeuvre") || items.length==11))) {
								 outils.add(new Outil(items[0], cast(items[1]), cast(items[2]), cast(items[3]),
											cast(items[4]), cast(items[5]))); 
							 }
							
						}

					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("fichier introuvable. Vérifiez le chemin d'accès ou ses droits.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	

	/**
	 * Permet de caster un string afin d'obtenir sa valeur mais en entier
	 * @param string : le nombre dont on veut son entier
	 * @return la chaine de caractère sous le format d'un nombre entier
	 */
	public static int cast(String string) {
		return Integer.parseInt(string);
	}

	/**
	 * Getter pour obtenir la liste des bâtiments du jeu
	 * @return bat : liste des batiments
	 */
	public ArrayList<Batiment> getBat() {
		return bat;
	}

	/**
	 * Getter pour obtenir la liste des ouvrier du jeu
	 * @return ouv : liste des ouvriers
	 */
	public ArrayList<Ouvrier> getOuv() {
		return ouv;
	}

	/**
	 * Getter pour obtenir la liste des bâtiments du jeu
	 * @return mach : liste des batiments
	 */
	public ArrayList<Machine> getMach() {
		return mach;
	}

	/**
	 * Getter pour obtenir la liste des apprentis du jeu
	 * @return appr : liste des apprentis
	 */
	public ArrayList<Apprenti> getAppr() {
		return appr;
	}

	/**
	 * Getter pour obtenir la liste des esclaves du jeu
	 * @return escl : liste des esclaves
	 */
	public ArrayList<Esclave> getEscl() {
		return escl;
	}

	/**
	 * Getter pour obtenir la liste des emprunts du jeu
	 * @return emprunts : liste des emprunts
	 */
	public ArrayList<Emprunt> getEmprunts() {
		return emprunts;
	}

	/**
	 * Getter pour obtenir la liste des outils du jeu
	 * @return outils : liste des outils
	 */
	public ArrayList<Outil> getOutils() {
		return outils;
	}
	/**
	 * Getter pour obtenir la liste des universités du jeu
	 * @return universites : liste des universités
	 */
	public ArrayList<Universite> getUniversites() {
		return universites;
	}
}
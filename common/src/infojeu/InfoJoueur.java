package infojeu;

import java.io.Serializable;

import java.util.ArrayList;

import cartes.Batiment;
import cartes.Emprunt;
import cartes.Outil;
import cartes.Ouvrier;

public class InfoJoueur extends Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4418401982412598840L;
	private ArrayList<Ouvrier> ouvriers = new ArrayList<>();
	private ArrayList<Batiment> batiments = new ArrayList<>();
	private ArrayList<Batiment> batFinis = new ArrayList<>();
	private ArrayList<Emprunt> emprunts = new ArrayList<>();
	private ArrayList<Outil> outils = new ArrayList<>();

	private int ecus;
	private int ptsVictoire;
	private int malusdecompte = 0;
	private int nbactions = 0;
	public ArrayList<Ouvrier> getOuvriers() {
		return ouvriers;
	}
	public ArrayList<Batiment> getBatiments() {
		return batiments;
	}
	public void addBatiment(Batiment batiment) {
		this.batiments.add(batiment);
	}
	public ArrayList<Batiment> getBatFinis() {
		return batFinis;
	}
	
	public ArrayList<Emprunt> getEmprunts() {
		return emprunts;
	}
	public void addEmprunt(Emprunt emprunt) {
		this.emprunts.add(emprunt);
	}
	public ArrayList<Outil> getOutils() {
		return outils;
	}
	public void addOutil(Outil outil) {
		this.outils.add(outil);
	}
	public int getNbEcus() {
		return ecus;
	}
	public void setEcus(int ecus) {
		this.ecus = ecus;
	}
	public int getPtsVictoire() {
		return ptsVictoire;
	}

	public int getMalusdecompte() {
		return malusdecompte;
	}
	
	public int getNbAction() {
		return nbactions;
	}
	public void setNbactions(int nbactions) {
		this.nbactions = nbactions;
	}
	
	public void ajoutMalusdecompte(int malusdecompte) {
		this.malusdecompte += malusdecompte;
	}
	
	public void ajouterPtsVictoire(int ptsVictoire) {
		this.ptsVictoire += ptsVictoire;
	}
	public void addOuvrier(Ouvrier ouvrier) {
		this.ouvriers.add(ouvrier);
	}
	public void ajoutNbEcus(int nbEcus) {
		ecus += nbEcus;
	}
	
	public int getTrancheDeDix() {
		int tdd = getNbEcus() / 10;
		return tdd;
	}
	public boolean removeBat(Batiment b) {
		for (int i = 0; i < getBatiments().size(); i++) {
			if (getBatiments().get(i).getRessources().getNom().equals(b.getRessources().getNom())) {
				getBatiments().remove(i);
				return true;
			}
		}
		return false;
	}
	
}

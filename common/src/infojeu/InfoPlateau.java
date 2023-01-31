package infojeu;

import java.io.Serializable;
import java.util.ArrayList;

import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;

public class InfoPlateau extends Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 936652141738470051L;
	private ArrayList<Batiment> paquetBatiments = new ArrayList<>();
	private ArrayList<Ouvrier> paquetOuvriers = new ArrayList<>();
	private ArrayList<Ouvrier> piocheOuvriers = new ArrayList<>();
	private ArrayList<Batiment> piocheBatiments = new ArrayList<>();
	private ArrayList<Esclave> paquetEsclaves = new ArrayList<>();
	private ArrayList<Outil> paquetOutil = new ArrayList<>();
	private ArrayList<Emprunt> paquetEmprunt = new ArrayList<>();
	private ArrayList<Universite> paquetUniversite = new ArrayList<>();
	public ArrayList<Esclave> getPaquetEsclaves() {
		return paquetEsclaves;
	}


	public ArrayList<Outil> getPaquetOutil() {
		return paquetOutil;
	}

	

	public ArrayList<Emprunt> getPaquetEmprunt() {
		return paquetEmprunt;
	}



	public ArrayList<Universite> getPaquetUniversite() {
		return paquetUniversite;
	}

	

	private int reserveEcus = 0;

	public int getReserveEcus() {
		return reserveEcus;
	}

	public void setReserveEcus(int reserveEcus) {
		this.reserveEcus = reserveEcus;
	}

	public ArrayList<Batiment> getPaquetBatiments() {
		return paquetBatiments;
	}

	

	public ArrayList<Ouvrier> getPaquetOuvriers() {
		return paquetOuvriers;
	}

	

	public ArrayList<Ouvrier> getPiocheOuvriers() {
		return piocheOuvriers;
	}

	

	public ArrayList<Batiment> getPiocheBatiments() {
		return piocheBatiments;
	}

	

	/**
	 * Pour ajouter un nombre d ecus à la reserve
	 * 
	 * @param newR = Nombre d'écus que l'on veut ajouter
	 */
	public void ajouterReserveEcus(int newR) {
		this.reserveEcus += newR;
	}

	/**
	 * Enleve un nombre d ecus de la reserve
	 * 
	 * @param ecus : le nombre d ecus a soustraire
	 */
	public void sousReserveEcus(int ecus) {
		this.reserveEcus -= ecus;
	}
}

package infojeu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cartes.Batiment;
import cartes.Emprunt;
import cartes.Esclave;
import cartes.Outil;
import cartes.Ouvrier;
import cartes.Universite;

public class TestInfoPlateau {

	@Test
	public void testInfoPlateau() {
		InfoPlateau ip = new InfoPlateau();
		Batiment b= new Batiment("B", 0, 0, 0, 0, 0, 0);
		ip.getPaquetBatiments().add(b);
		assertEquals(ip.getPaquetBatiments().get(0).getRessources().getNom() , "B");
		ip.getPiocheBatiments().add(b);
		assertEquals(ip.getPiocheBatiments().get(0).getRessources().getNom() , "B");


		Emprunt e = new Emprunt("E" , 0 , 0 , 0);
		ip.getPaquetEmprunt().add(e);

		assertEquals(ip.getPaquetEmprunt().get(0).getNom(), "E");
		Esclave es = new Esclave("D",0,0,0,0,0);
		ip.getPaquetEsclaves().add(es);

		assertEquals(ip.getPaquetEsclaves().get(0).getRessources().getNom(), "D");
		Universite u = new Universite("U", 0, 0, 0, 0, 0);
		ip.getPaquetUniversite().add(u);
		assertEquals(ip.getPaquetUniversite().get(0).getRessources().getNom(), "U");
		Outil o = new Outil("O", 0, 0, 0, 0, 0);
		ip.getPaquetOutil().add(o);
		assertEquals(ip.getPaquetOutil().get(0).getRessources().getNom(), "O");
		Ouvrier ou = new Ouvrier("OU", 0, 0, 0, 0, 0);
		ip.getPaquetOuvriers().add(ou);
		assertEquals(ip.getPaquetOuvriers().get(0).getRessources().getNom(), "OU");
		ip.getPiocheOuvriers().add(ou);
		assertEquals(ip.getPiocheOuvriers().get(0).getRessources().getNom(), "OU");
		ip.setReserveEcus(10);
		assertEquals(ip.getReserveEcus(),10);

	}
}

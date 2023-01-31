package infojeu;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;

import cartes.Batiment;
import cartes.Emprunt;
import cartes.Outil;

public class TestInfoJoueur {
	@Test
	public void testInfoJoueur() { 
		InfoJoueur ir=new InfoJoueur();
		ir.addBatiment(new Batiment("B", 0, 0, 0, 0, 0, 0));
		assertEquals(ir.getBatiments().get(0).getRessources().getNom(),"B");
		ir.addEmprunt(new Emprunt("E", 0, 0, 0));
		assertEquals(ir.getEmprunts().get(0).getNom(),"E");

		ir.addOutil(new Outil("O", 0, 0, 0, 0, 0));
		assertEquals(ir.getOutils().get(0).getRessources().getNom(),"O");
ir.ajoutMalusdecompte(4);
ir.ajouterPtsVictoire(2);
assertEquals(ir.getMalusdecompte(),4);
assertEquals(ir.getPtsVictoire(),2);
assertEquals(ir.getNbAction(),0);
ir.setNbactions(6);
assertEquals(ir.getNbAction(),6);
assertEquals(ir.getTrancheDeDix(),0);



		
	}
}

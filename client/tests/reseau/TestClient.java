package reseau;


import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import ia.GestionnaireIA;



public class TestClient {
	@Test
	public void testCreationIA() {
	GestionnaireIA gi=Client.creationIA(1, "MOYENAGE");
	assertEquals(gi.getClass(),ia.IADifficile.class);
	gi=Client.creationIA(1, "ANTIQUITE");
	assertEquals(gi.getClass(),ia.IADifficileAntiquite.class);
	gi=Client.creationIA(0, "ANTIQUITE");
	assertEquals(gi.getClass(),ia.IAFacileAntiquite.class);
	gi=Client.creationIA(0, "MOYENAGE");
	assertEquals(gi.getClass(),ia.IAFacile.class);

	} 
}

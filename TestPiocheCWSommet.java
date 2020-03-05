
public class TestPiocheCWSommet {

	public static void main(String[] args) {
		TestPiocheCWSommet testPCWS = new TestPiocheCWSommet();
		testPCWS.testeSommetPileNonVide();
		testPCWS.testeSommetPileVide();
	}

	void testeSommetPileNonVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte = new CarteWagon(null);
		pile.cartes[0] = carte;
		pile.indiceSommet++;
		Test.assertEquals(pile.getSommet(),carte, "Sommet PiocheCW NonVide => Le sommet de la pioche est une carte préenregistrée");
	}	
	
	void testeSommetPileVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		Test.assertError(() -> pile.getSommet(), "Sommet PiocheCW Vide => Impossible de lire le sommet d'une pile vide");
	}
}
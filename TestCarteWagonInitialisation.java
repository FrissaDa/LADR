public class TestCarteWagonInitialisation {

	public static void main(String[] args) {
		TestCarteWagonInitialisation testCWI = new TestCarteWagonInitialisation();
		testCWI.testeCreationCarteWagonCouleur();
		testCWI.testeCreationCarteWagonLocomotive();
	}
	
	void testeCreationCarteWagonCouleur() {
		CarteWagon carte = new CarteWagon("bleu");
		Test.assertEquals(carte.getCouleur(), "bleu", "Init CW Couleur => Création carte wagon bleu et couleur bleue");
		Test.assertFalse(carte.estLocomotive(), "Init CW Couleur => CW bleu n'est pas une locomotive");
	}

	void testeCreationCarteWagonLocomotive() {
		CarteWagon loco = new CarteWagon(null);
		Test.assertEquals(loco.getCouleur(), null, "Init CW Locomotive => Création carte locomotive et couleur nulle");
		Test.assertTrue(loco.estLocomotive(), "Init CW Locomotive => CW locomotive est une locomotive");
	}
}

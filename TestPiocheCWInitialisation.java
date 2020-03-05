public class TestPiocheCWInitialisation {

	public static void main(String[] args) {
		TestPiocheCWInitialisation testPCWI = new TestPiocheCWInitialisation();
		testPCWI.testeCreationPile();
	}

	void testeCreationPile() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		Test.assertEquals(pile.cartes.length, 110, "Init PiocheCW => La taille de la pioche est 110.");
		Test.assertEquals(pile.indiceSommet, -1, "Init PiocheCW => L'indice du sommet est initialisé à -1");
	}
}

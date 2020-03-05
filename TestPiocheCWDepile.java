public class TestPiocheCWDepile {

	public static void main(String[] args) {
		TestPiocheCWDepile testPCWD = new TestPiocheCWDepile();
		testPCWD.testeDepilement();
		testPCWD.testeDepilementPileUneCarte();
		testPCWD.testeDepilementPileVide();
	}

	void testeDepilement() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		CarteWagon carte2 = new CarteWagon(Donnees.COULEURS_WAGON[1]);

		pile.empile(carte1);
		pile.empile(carte2);
		Test.assertEquals(pile.depile(), carte2, "Depile PileCW => La carte dépilée est la carte 2");
		Test.assertFalse(pile.estVide(), "Depile PileCW => La pile n'est pas vide");
		Test.assertEquals(pile.depile(), carte1, "Depile PileCW => La carte dépilée est la carte 1");
		Test.assertTrue(pile.estVide(), "Depile PileCW => La pile est vide");
	}
	
	void testeDepilementPileUneCarte() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);

		pile.empile(carte1);
		Test.assertEquals(pile.depile(), carte1, "Depile PileCW PileUneCarte => La carte dépilée est la carte 1");
		Test.assertTrue(pile.estVide(), "Depile PileCW PileUneCarte => La pile est vide");
	}

	void testeDepilementPileVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		Test.assertError(() -> pile.depile(), "Depile PileCW PileVide => Impossible dépiler une pile vide");
	}
}

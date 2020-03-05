public class TestPiocheCWEmpile {

	public static void main(String[] args) {
		TestPiocheCWEmpile testPCWE = new TestPiocheCWEmpile();
		testPCWE.testeEmpilement();
		testPCWE.testeEmpilementPilePresquePleine();
		testPCWE.testeEmpilementPilePleine();
	}

	void testeEmpilement() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		CarteWagon carte2 = new CarteWagon(Donnees.COULEURS_WAGON[1]);

		pile.empile(carte1);
		Test.assertFalse(pile.estVide(), "Empile PileCW => La pile n'est pas vide");
		Test.assertFalse(pile.estPleine(), "Empile PileCW => La pile n'est pas pleine");
		Test.assertEquals(pile.getSommet(), carte1, "Empile PileCW => La carte au sommet est la carte 1");

		pile.empile(carte2);
		Test.assertEquals(pile.depile(), carte2, "Empile PileCW => La carte dépilée du sommet est la carte 2");
	}

	void testeEmpilementPilePleine() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for (int i = 0; i < 110; i++) {
			pile.empile(new CarteWagon(null));
		}
		Test.assertTrue(pile.estPleine(), "Empile PileCW PilePleine => La pile est pleine");
		Test.assertError(() -> pile.empile(new CarteWagon(null)), "Empile PileCW PilePleine => Impossible d'empiler sur une pile pleine");
	}
	
	void testeEmpilementPilePresquePleine() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for (int i = 0; i < 109; i++) {
			pile.empile(new CarteWagon(null));
		}
		Test.assertFalse(pile.estPleine(), "Empile PileCW PilePresquePleine => La pile n'est pas pleine");
		pile.empile(new CarteWagon(null));
		Test.assertTrue(pile.estPleine(), "Empile PileCW PilePresquePleine => La pile est pleine");
	}
}

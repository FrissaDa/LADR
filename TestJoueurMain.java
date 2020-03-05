
public class TestJoueurMain {

	public static void main(String[] args) {
		TestJoueurMain test = new TestJoueurMain();
		test.testInitialisationMainJoueur();
		test.testSetMain();
		test.testPrendCarteCouleur();
		test.testPrendCarteLocomotive();
	}

	void testInitialisationMainJoueur() {
		Joueur joueur = new Joueur(0,"bleu");
		Test.assertEquals(joueur.getMainCartesWagon(),ListeCartesWagon.VIDE,"Init Joueur Main => La main du joueur est initialisée à VIDE");
	}

	void testSetMain() {
		Joueur joueur = new Joueur(4,"noir");
		ListeCartesWagon lcw = ListeCartesWagon.VIDE;
		CarteWagon cw = new CarteWagon(null);
		lcw = lcw.ajoute(cw);
		joueur.setMainCartesWagon(lcw);
		Test.assertFalse(joueur.getMainCartesWagon().estVide(), "Modification Joueur Main => La main du joueur n'est pas vide");
		Test.assertEquals(joueur.getMainCartesWagon().getTete().estLocomotive(), cw.estLocomotive(), "Modification Joueur Main => La première carte de la main est une locomotive");
	}

	void testPrendCarteCouleur() {
		Joueur joueur = new Joueur(2,"jaune");
		CarteWagon couleur = new CarteWagon("rouge");
		joueur.prendsCarteWagon(couleur);
		Test.assertTrue(joueur.getMainCartesWagon().getTete().estDeCouleur("rouge"),"PrendCouleur Joueur Main Couleur => La première carte de la main est rouge");
	}

	void testPrendCarteLocomotive() {
		Joueur joueur = new Joueur(1,"rouge");
		CarteWagon loco = new CarteWagon(null);
		joueur.prendsCarteWagon(loco);
		Test.assertTrue(joueur.getMainCartesWagon().getTete().estDeCouleur(null),"PrendCouleur Joueur Main Locomotive => La première carte de la main est une locomotive");
	}
}

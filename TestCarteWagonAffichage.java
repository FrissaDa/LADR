
public class TestCarteWagonAffichage {

	public static void main(String[] args) {
		TestCarteWagonAffichage testCWA = new TestCarteWagonAffichage();
		testCWA.testeCWAffichageCouleur();
		testCWA.testeCWAffichageLocomotive();
	}

	void testeCWAffichageCouleur() {
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			CarteWagon carte = new CarteWagon(Donnees.COULEURS_WAGON[i]);
			Test.assertEquals(carte.versChaine(), "Wagon "+Donnees.COULEURS_WAGON[i], "Affichage CW Couleur => La représentation textuelle de la carte 1 est Wagon "+Donnees.COULEURS_WAGON[i]);
		}
	}
	
	void testeCWAffichageLocomotive() {
		CarteWagon carte = new CarteWagon(null);
		Test.assertEquals(carte.versChaine(), "Locomotive", "Affichage CW Locomotive => La représentation textuelle de la carte locomotive est Locomotive");
	}
}

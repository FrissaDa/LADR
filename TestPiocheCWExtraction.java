
public class TestPiocheCWExtraction {

	public static void main(String[] args) {
		TestPiocheCWExtraction testPCWEx = new TestPiocheCWExtraction();
		testPCWEx.testeExtractionAucuneCartePileNonVide();
		testPCWEx.testeExtractionDeuxCartesTrois();
		testPCWEx.testeExtractionDixCartesOnze();
		testPCWEx.testeExtractionToutesCartesCentDix();
		testPCWEx.testeExtractionAucuneCartePileVide();
		testPCWEx.testeExtractionDeuxCartesDeux();
		testPCWEx.testeExtractionDeuxCartesUne();
		testPCWEx.testeExtractionDeuxCartesVide();
		testPCWEx.testeExtractionDixCartesDix();
		testPCWEx.testeExtractionDixCartesNeuf();
		testPCWEx.testeExtractionToutesCartesPasAssez();
	}
	
	void testeExtractionAucuneCartePileNonVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		pile.empile(carte1);
		CarteWagon[] cartes = pile.extraitCartes(0);
		Test.assertFalse(pile.estVide(), "Extraction PiocheCW Zero/NonVide => La pile n'est pas vide");
		Test.assertEquals(cartes.length, 0, "Extraction PiocheCW Zero/NonVide => Aucune carte extraite");
	}
	
	void testeExtractionDeuxCartesTrois() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		CarteWagon carte2 = new CarteWagon(Donnees.COULEURS_WAGON[1]);
		CarteWagon carte3 = new CarteWagon(Donnees.COULEURS_WAGON[2]);
		pile.empile(carte1);
		pile.empile(carte2);
		pile.empile(carte3);
		CarteWagon[] cartes = pile.extraitCartes(2);
		Test.assertFalse(pile.estVide(), "Extraction PiocheCW Deux/Trois => La pile n'est pas vide");
		Test.assertEquals(cartes[0], carte3, "Extraction PiocheCW Deux/Trois => La carte 3 est la 1ère carte extraite");
		Test.assertEquals(cartes[1], carte2, "Extraction PiocheCW Deux/Trois => La carte 2 est la 2nde carte extraite");
	}

	void testeExtractionDixCartesOnze() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for(int i=0;i<11;i++){
			pile.empile(new CarteWagon(null));
		}
		CarteWagon[] cartes = pile.extraitCartes(10);
		Test.assertFalse(pile.estPleine(), "Extraction PiocheCW Dix/Onze => La pile n'est pas pleine");
		Test.assertFalse(pile.estVide(), "Extraction PiocheCW Dix/Onze => La pile n'est pas vide");
		Test.assertEquals(cartes.length, 10, "Extraction PiocheCW Dix/Onze => 10 cartes ont été extraites");
	}

	void testeExtractionToutesCartesCentDix() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		pile.ajouteCartes();

		CarteWagon[] cartes = pile.extraitCartes(110);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Toutes/Toutes => La pile est vide");
		Test.assertEquals(cartes.length, 110, "Extraction PiocheCW Toutes/Toutes => 110 cartes ont été extraite");
		Test.assertEquals(compteCartesCouleur(cartes, null), 14, "Extraction PiocheCW Toutes/Toutes => 14 cartes locomotive extraites");
		for (int i = 0; i < Donnees.COULEURS_WAGON.length; i++) {
			int nbCartesCouleur = compteCartesCouleur(cartes, Donnees.COULEURS_WAGON[i]);
			Test.assertEquals(nbCartesCouleur, 12, "Extraction PiocheCW Toutes/Toutes => 12 cartes de couleur " + Donnees.COULEURS_WAGON[i]+" extraites");
		}
	}
	
	void testeExtractionAucuneCartePileVide(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon[] cartes = pile.extraitCartes(0);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Zero/Vide => La pile est vide");
		Test.assertEquals(cartes.length, 0, "Extraction PiocheCW Zero/Vide => Aucune carte extraite");
	}
	
	void testeExtractionDeuxCartesDeux(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		CarteWagon carte2 = new CarteWagon(Donnees.COULEURS_WAGON[1]);
		pile.empile(carte1);
		pile.empile(carte2);
		CarteWagon[] cartes = pile.extraitCartes(2);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Deux/Deux => La pile est vide");
		Test.assertEquals(cartes[0], carte2, "Extraction PiocheCW Deux/Deux => La carte 2 est la 1ère carte extraite");
		Test.assertEquals(cartes[1], carte1, "Extraction PiocheCW Deux/Deux => La carte 1 est la 2nde carte extraite");
	}
	
	void testeExtractionDeuxCartesUne(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon carte1 = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		pile.empile(carte1);
		CarteWagon[] cartes = pile.extraitCartes(2);
		//Test.assertTrue(pile.estVide(), "Extraction PiocheCW Deux/Un => La pile est vide");
		//Test.assertEquals(cartes[0], carte1, "Extraction PiocheCW Deux/Un => La carte 1 est la 1ère carte extraite");
		Test.assertEquals(cartes.length,1,"Extraction PiocheCW Deux/Un => Une seule carte extraite");
	}
	
	void testeExtractionDeuxCartesVide(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		CarteWagon[] cartes = pile.extraitCartes(2);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Deux/Zero => La pile est vide");
		Test.assertEquals(cartes.length,0,"Extraction PiocheCW Deux/Zero => Aucune carte extraite");
	}
	
	void testeExtractionDixCartesDix(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for(int i=0;i<10;i++){
			pile.empile(new CarteWagon(null));
		}
		CarteWagon[] cartes = pile.extraitCartes(10);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Dix/Dix => La pile est vide");
		Test.assertEquals(cartes.length, 10, "Extraction PiocheCW Dix/Dix => 10 cartes ont été extraites");
	}
	
	void testeExtractionDixCartesNeuf(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for(int i=0;i<9;i++){
			pile.empile(new CarteWagon(null));
		}
		CarteWagon[] cartes = pile.extraitCartes(10);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Dix/Neuf => La pile est vide");
		Test.assertEquals(cartes.length, 9, "Extraction PiocheCW Dix/Neuf => 9 cartes ont été extraites");
	}
	
	void testeExtractionToutesCartesPasAssez(){
		PiocheCartesWagon pile = new PiocheCartesWagon();
		for(int i=0;i<100;i++){
			pile.empile(new CarteWagon(null));
		}

		CarteWagon[] cartes = pile.extraitCartes(110);
		Test.assertTrue(pile.estVide(), "Extraction PiocheCW Toutes/PasAssez => La pile est vide");
		Test.assertEquals(cartes.length, 100, "Extraction PiocheCW Toutes/PasAssez => 100 cartes ont été extraite");
	}

	int compteCartesCouleur(CarteWagon[] cartes, String couleur) {
		int compteur = 0;
		for (CarteWagon carteWagon : cartes) {
			if (carteWagon.estDeCouleur(couleur)) {
				compteur++;
			}
		}
		return compteur;
	}
}
public class TestPiocheCWAjoutCartes {

	public static void main(String[] args) {
		TestPiocheCWAjoutCartes testPCWAC = new TestPiocheCWAjoutCartes();
		testPCWAC.testeAjouteCartes();
	}

	void testeAjouteCartes() {
		PiocheCartesWagon pile = new PiocheCartesWagon();

		pile.ajouteCartes();
		Test.assertTrue(pile.estPleine(), "Ajout PileCW => La pile est pleine");

		int[] tabCouleurs = new int[Donnees.COULEURS_WAGON.length+1];
		int compteur = 0;
		while (!pile.estVide()) {
			CarteWagon cw = pile.depile();
			int i=0;
			if(cw.getCouleur()==null){
				tabCouleurs[tabCouleurs.length-1]++;
			} else {
				while(!cw.getCouleur().equals(Donnees.COULEURS_WAGON[i])){
					i++;
				}
				tabCouleurs[i]++;
			}
			compteur++;
		}
		Test.assertEquals(compteur, 110, "Ajout PileCW => La pile contenait 110 cartes wagon");
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			Test.assertEquals(tabCouleurs[i],12, "Ajout PileCW => 12 cartes wagon de couleur "+Donnees.COULEURS_WAGON[i]);
		}
		Test.assertEquals(tabCouleurs[tabCouleurs.length-1],14, "Ajout PileCW => 14 cartes wagon locomotives");
	}
}

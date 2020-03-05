public class TestJeuInitialisationPioches {

	public static void main(String[] args) {
		TestJeuInitialisationPioches testJeuInitPioches = new TestJeuInitialisationPioches();
		testJeuInitPioches.testeJeuInitialisePioches();
	}
	
	void testeJeuInitialisePioches() {
		Jeu jeu = new Jeu();
		jeu.initialisePioches();
		Test.assertTrue(jeu.getPiocheCartesWagon()!=null, "Jeu Init Pioches => Pioche Cartes Wagon cachée existe");
		Test.assertTrue(jeu.getPiocheVisible()!=null, "Jeu Init Pioches => Pioche Cartes Wagon visible existe");
		Test.assertTrue(jeu.getDefausseCartesWagon()!=null, "Jeu Init Pioches => Défausse Cartes Wagon existe");
		Test.assertFalse(jeu.getPiocheCartesWagon().estVide(), "Jeu Init Pioches => Pioche Cartes Wagon n'est pas vide");
		Test.assertTrue(jeu.getDefausseCartesWagon().estVide(), "Jeu Init Pioches => Défausse Cartes Wagon est vide");
		Test.assertTrue(jeu.getDefausseCartesWagon().estVide(), "Jeu Init Pioches => Pioche Cartes Wagon visible a été initialisée");
		Test.assertEquals(jeu.getPiocheVisible().length,5,"Jeu Init Pioches => La taille de la Pioche Cartes Wagon visible est de cinq cartes");
		for(int i=0;i<5;i++){
			Test.assertTrue(jeu.getPiocheVisible()[i]!=null, "Jeu Init Pioches => Il existe un carte à la position "+i+" de la Pioche Cartes Wagon visible");
		}
		Test.assertFalse(jeu.getPiocheCartesWagon().estPleine(),"Jeu Init Pioches => Pioche Cartes Wagon cachée n'est pas pleine");
		jeu.getPiocheCartesWagon().extraitCartes(105);
		Test.assertTrue(jeu.getPiocheCartesWagon().estVide(), "Jeu Init Pioches => Pioche Cartes Wagon cachée contient 105 cartes");
	}
}

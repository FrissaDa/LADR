public class TestJeuDernierTour{
	
	public static void main(String[] args) {
		TestJeuDernierTour testJeuDernierTour = new TestJeuDernierTour();
		testJeuDernierTour.testeJeuActionsDernierTour();
		// TODO à compléter
	}

	// TODO déclarer les méthodes de test

	/**
	 * Semi-auto
	 * @since Increment 4
	 */
	void testeJeuActionsDernierTour(){
		// Initialisation du jeu
		Jeu jeu = new Jeu();
		jeu.affichePlateau();
		jeu.creeJoueurs(5);
		// Préparation détection dernier tour
		jeu.getJoueurCourant().enleveWagons(42);
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		// Détection dernier tour ?
		Test.assertEquals(jeu.getJoueursRestantsAJouer(), 5, "nombre de joueurs restants à jouer : 5");
		jeu.changeJoueur();
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		jeu.changeJoueur();
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		jeu.changeJoueur();
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		jeu.changeJoueur();
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		jeu.changeJoueur();
		// Sélectionner une route libre dont la taille est inférieure au nombre de wagons du joueur courant
		jeu.tourDeJeu();
		jeu.changeJoueur();
		// Fin du dernier tour ?
		Test.assertEquals(jeu.getJoueursRestantsAJouer(), 0, "nombre de joueurs restants à jouer : 0");
		// Test visuel => l'interface ne permet plus de sélectionner une route ou de changer de joueur
	}
}

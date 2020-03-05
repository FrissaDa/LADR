
public class TestGrapheCreationJoueur{
	
	public static void main(String[] args) {
		TestGrapheCreationJoueur testGrapheCreationJoueur = new TestGrapheCreationJoueur();
		testGrapheCreationJoueur.testeGrapheJoueurRoutes();
		testGrapheCreationJoueur.testeGrapheJoueurAucuneRoute();
	}

	void testeGrapheJoueurRoutes(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0];
		Route route2 = plateau.getRoutes()[35];
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		int v1d = route1.getVilleDepart().getNumero();
		int v1a = route1.getVilleArrivee().getNumero();
		int v2d = route2.getVilleDepart().getNumero();
		int v2a = route2.getVilleArrivee().getNumero();
		// La topologie contient la longueur de la route pour chaque couple de villes
		// d'arrivée et de départ de chaque route
		Test.assertEquals(graphe.getTopologie()[v1d][v1a], route1.getLongueur(), "route 0 -> longueur 1");
		Test.assertEquals(graphe.getTopologie()[v2d][v2a], route2.getLongueur(), "route 35 -> longueur 2");
		Test.assertEquals(graphe.getTopologie()[v1a][v1d], route1.getLongueur(), "route 0 (inverse) -> longueur 1");
		Test.assertEquals(graphe.getTopologie()[v2a][v2d], route2.getLongueur(), "route 35 (inverse) -> longueur 2");
		boolean valeurParDefaut = true;
		for(int i=0;i<graphe.getTopologie().length;i++){
			for(int j=0;j<graphe.getTopologie()[i].length;j++){
				if(i!=v1d && i!= v1a && i!=v2d && i!=v2a && j!=v1d && j!= v1a && j!=v2d && j!=v2a)
				valeurParDefaut &= (graphe.getTopologie()[i][j] == 0);
			}
		}
		Test.assertTrue(valeurParDefaut,"valeurs 0 par défaut");
	}
	
	void testeGrapheJoueurAucuneRoute(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		graphe.creeGraphePourUnJoueur(joueur);
		boolean valeurParDefaut = true;
		for(int[] ligne : graphe.getTopologie()){
			for(int valeur : ligne){
				valeurParDefaut &= (valeur == 0);
			}
		}
		Test.assertTrue(valeurParDefaut,"valeurs 0 par défaut");
	}
}

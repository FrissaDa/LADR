
public class TestGrapheConnexite{
	
	public static void main(String[] args) {
		TestGrapheConnexite testGrapheConnexite = new TestGrapheConnexite();
		testGrapheConnexite.testeGrapheConnexiteNulle();
		testGrapheConnexite.testeGrapheConnexiteUnGroupeUneRoute();
		testGrapheConnexite.testeGrapheConnexiteUnGroupePlusieursRoutes();
		testGrapheConnexite.testeGrapheConnexitePlusieursGroupesMonoRoute();
		testGrapheConnexite.testeGrapheConnexitePlusieursGroupesPlusieursRoutes();
	}

	private void testeGrapheConnexiteNulle(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.calculConnexite(),0,"connexite nulle => 0");
	}

	private void testeGrapheConnexiteUnGroupeUneRoute(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle
		route1.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.calculConnexite(),1,"connexite une route => 1");
	}

	private void testeGrapheConnexiteUnGroupePlusieursRoutes(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle
		Route route2 = plateau.getRoutes()[4]; // Seattle - Helena
		Route route3 = plateau.getRoutes()[22]; // Helena - Omaha
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.calculConnexite(),1,"connexite routes connectees => 1");
		
	}
	
	void testeGrapheConnexitePlusieursGroupesMonoRoute(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle
		Route route2 = plateau.getRoutes()[11]; // Los Angeles - El Paso
		Route route3 = plateau.getRoutes()[35]; // New York - Washington
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.calculConnexite(),3,"connexite routes non connectees => 3");
	}
	
	void testeGrapheConnexitePlusieursGroupesPlusieursRoutes(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle
		Route route2 = plateau.getRoutes()[4]; // Seattle - Helena
		Route route3 = plateau.getRoutes()[11]; // Los Angeles - El Paso
		Route route4 = plateau.getRoutes()[35]; // New York - Washington
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		route4.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.calculConnexite(),3,"connexite groupe + routes non connectees => 3");
	}
}

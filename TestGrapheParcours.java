
public class TestGrapheParcours{
	
	public static void main(String[] args) {
		TestGrapheParcours testGrapheParcours = new TestGrapheParcours();
		testGrapheParcours.testeGrapheParcoursProfondeur0();
		testGrapheParcours.testeGrapheParcoursProfondeur1();
		testGrapheParcours.testeGrapheParcoursProfondeur3();
		testGrapheParcours.testeGrapheAtteignableVillesConnectees();
		testGrapheParcours.testeGrapheAtteignableVillesNonConnectees();
	}

	private void testeGrapheParcoursProfondeur0(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		graphe.creeGraphePourUnJoueur(joueur);
		int nombreMarques = 0;
		boolean[] marques = graphe.parcoursProfondeur(0, new boolean[graphe.getNombreSommets()]);
		for(boolean valeur : marques){
			nombreMarques += valeur?1:0;
		}
		// Aucune route donc pas de parcours, seule la ville de départ est marquée
		Test.assertEquals(nombreMarques,1,"parcours profondeur 0 => 1 marque");
		Test.assertTrue(marques[0],"parcours profondeur 0 => ville départ marquée");
	}

	private void testeGrapheParcoursProfondeur1(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle
		route1.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		int nombreMarques = 0;
		boolean[] marques = graphe.parcoursProfondeur(0, new boolean[graphe.getNombreSommets()]);
		for(boolean valeur : marques){
			nombreMarques += valeur?1:0;
		}
		// Parcourt une route, deux marques
		Test.assertEquals(nombreMarques,2, "parcours profondeur 1 => 2 marques");
		Test.assertTrue(marques[route1.getVilleDepart().getNumero()],"parcours profondeur 1 => ville départ marquée");
		Test.assertTrue(marques[route1.getVilleArrivee().getNumero()],"parcours profondeur 1 => ville arrivée marquée");
	}

	private void testeGrapheParcoursProfondeur3(){
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
		int nombreMarques = 0;
		boolean[] marques = graphe.parcoursProfondeur(0, new boolean[graphe.getNombreSommets()]);
		for(boolean valeur : marques){
			nombreMarques += valeur?1:0;
		}
		// Parcours trois routes contigues, 4 villes correspondantes, 4 marques
		Test.assertEquals(nombreMarques,4, "parcours profondeur 3 => 4 marques");
		Test.assertTrue(marques[route1.getVilleDepart().getNumero()],"parcours profondeur 3 => route 1 ville départ marquée");
		Test.assertTrue(marques[route1.getVilleArrivee().getNumero()],"parcours profondeur 3 => route 1 ville arrivée marquée");
		Test.assertTrue(marques[route2.getVilleDepart().getNumero()],"parcours profondeur 3 => route 2 ville départ marquée");
		Test.assertTrue(marques[route2.getVilleArrivee().getNumero()],"parcours profondeur 3 => route 2 ville arrivée marquée");
		Test.assertTrue(marques[route3.getVilleDepart().getNumero()],"parcours profondeur 3 => route 3 ville départ marquée");
		Test.assertTrue(marques[route3.getVilleArrivee().getNumero()],"parcours profondeur 3 => route 3 ville arrivée marquée");
		
	}
	
	void testeGrapheAtteignableVillesConnectees(){
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
		// Le "chemin" permet d'accèder à toutes les villes à partir de
		// n'importe quelle ville de départ
		Test.assertTrue(graphe.sommetAtteignable(route1.getVilleDepart().getNumero(), route3.getVilleArrivee().getNumero()), "atteignable -> Vancouver - Omaha");
		Test.assertTrue(graphe.sommetAtteignable(route3.getVilleArrivee().getNumero(), route1.getVilleDepart().getNumero()), "atteignable -> Omaha - Vancouver");
		Test.assertTrue(graphe.sommetAtteignable(route1.getVilleDepart().getNumero(), route2.getVilleArrivee().getNumero()), "atteignable -> Vancouver - Helena");
		Test.assertTrue(graphe.sommetAtteignable(route2.getVilleArrivee().getNumero(), route1.getVilleDepart().getNumero()), "atteignable -> Helena - Vancouver");
		Test.assertTrue(graphe.sommetAtteignable(route1.getVilleDepart().getNumero(), route1.getVilleArrivee().getNumero()), "atteignable -> Vancouver - Seattle");
		Test.assertTrue(graphe.sommetAtteignable(route2.getVilleDepart().getNumero(), route3.getVilleArrivee().getNumero()), "atteignable -> Seattle - Omaha");
		Test.assertTrue(graphe.sommetAtteignable(route3.getVilleArrivee().getNumero(), route2.getVilleArrivee().getNumero()), "atteignable -> Omaha - Seattle");
		Test.assertTrue(graphe.sommetAtteignable(route2.getVilleDepart().getNumero(), route2.getVilleArrivee().getNumero()), "atteignable -> Seattle - Helena");
		Test.assertTrue(graphe.sommetAtteignable(route3.getVilleDepart().getNumero(), route3.getVilleArrivee().getNumero()), "atteignable -> Helena - Omaha");
	}
	
	void testeGrapheAtteignableVillesNonConnectees(){
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
		// Routes non contigues, pas de chemin possible
		Test.assertFalse(graphe.sommetAtteignable(0, 35), "non atteignable -> routes non connectées");
	}
}

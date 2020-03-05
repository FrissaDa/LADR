
public class TestGrapheDiametre{
	
	public static void main(String[] args) {
		TestGrapheDiametre testGrapheDiametre = new TestGrapheDiametre();
		testGrapheDiametre.testeGrapheFloydNul();
		testGrapheDiametre.testeGrapheFloydUneRoute();
		testGrapheDiametre.testeGrapheFloydPlusieursRoutesConnectees();
		testGrapheDiametre.testeGrapheFloydPlusieursRoutesNonConnectees();
		testGrapheDiametre.testeGrapheDiametreNul();
		testGrapheDiametre.testeGrapheDiametreUneRoute();
		testGrapheDiametre.testeGrapheDiametrePlusGrandConnectees();
		testGrapheDiametre.testeGrapheDiametrePlusGrandNonConnectees();
	}

	void testeGrapheFloydNul(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		graphe.creeGraphePourUnJoueur(joueur);
		int[][] chemins = graphe.plusCourtsCheminsFloyd();
		boolean aucunDiametre = true;
		for(int i=0;i<chemins.length;i++){
			for(int j=0;j<chemins[i].length;j++){
				if(i!=j){ // on ignore les diagonales
					aucunDiametre &= (chemins[i][j] == Integer.MAX_VALUE);
				}
			}
		}
		Test.assertTrue(aucunDiametre,"floyd => aucun diametre");
	}

	void testeGrapheFloydUneRoute(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		route1.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		int[][] chemins = graphe.plusCourtsCheminsFloyd();
		int nombreDiametres = 0;
		int diametre = 0;
		for(int i=0;i<chemins.length;i++){
			for(int j=0;j<chemins[i].length;j++){
				if(chemins[i][j]!= 0 && chemins[i][j] < Integer.MAX_VALUE){
					nombreDiametres++;
					diametre = chemins[i][j];
				}
			}
		}
		Test.assertEquals(nombreDiametres,2,"floyd => 1 route : 2 valeurs");
		Test.assertEquals(diametre, route1.getLongueur(), "floyd => 1 route de "+route1.getLongueur());
	}

	void testeGrapheFloydPlusieursRoutesConnectees(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		Route route2 = plateau.getRoutes()[4]; // Seattle - Helena - 4
		Route route3 = plateau.getRoutes()[22]; // Helena - Omaha - 6
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		int[][] chemins = graphe.plusCourtsCheminsFloyd();
		int nombreDiametres = 0;
		for(int i=0;i<chemins.length;i++){
			for(int j=0;j<chemins[i].length;j++){
				if(chemins[i][j]!= 0 && chemins[i][j] < Integer.MAX_VALUE){
					nombreDiametres++;
				}
			}
		}
		Test.assertEquals(nombreDiametres,12,"floyd => 3 routes : 12 valeurs ");
		Test.assertEquals(chemins[route1.getVilleDepart().getNumero()][route1.getVilleArrivee().getNumero()],1, "floyd => 3 routes connectees : diametre 1 = 1");
		Test.assertEquals(chemins[route1.getVilleDepart().getNumero()][route2.getVilleArrivee().getNumero()],route1.getLongueur()+route2.getLongueur(), "floyd => 3 routes : diametre 2 = "+(route1.getLongueur()+route2.getLongueur()));
		Test.assertEquals(chemins[route1.getVilleDepart().getNumero()][route3.getVilleArrivee().getNumero()],route1.getLongueur()+route2.getLongueur()+route3.getLongueur(), "floyd => 3 routes : diametre 3 = "+(route1.getLongueur()+route2.getLongueur()+route3.getLongueur()));
	}
	
	void testeGrapheFloydPlusieursRoutesNonConnectees(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		Route route2 = plateau.getRoutes()[11]; // Los Angeles - El Paso - 6
		Route route3 = plateau.getRoutes()[35]; // New York - Washington - 2
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		int[][] chemins = graphe.plusCourtsCheminsFloyd();
		int nombreDiametres = 0;
		for(int i=0;i<chemins.length;i++){
			for(int j=0;j<chemins[i].length;j++){
				if(chemins[i][j]!= 0 && chemins[i][j] < Integer.MAX_VALUE){
					nombreDiametres++;
				}
			}
		}
		Test.assertEquals(nombreDiametres,6,"floyd => 3 routes : 6 valeurs ");
		Test.assertEquals(chemins[route1.getVilleDepart().getNumero()][route1.getVilleArrivee().getNumero()],1, "floyd => 3 routes non connectees : diametre 1 = 1");
		Test.assertEquals(chemins[route2.getVilleDepart().getNumero()][route2.getVilleArrivee().getNumero()],route2.getLongueur(), "floyd => 3 routes : diametre 2 = "+route2.getLongueur());
		Test.assertEquals(chemins[route3.getVilleDepart().getNumero()][route3.getVilleArrivee().getNumero()],route3.getLongueur(), "floyd => 3 routes : diametre 3 = "+route3.getLongueur());
	}
	
	void testeGrapheDiametreNul(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.diametre(),0,"diametre nul => 0");
	}
	
	void testeGrapheDiametreUneRoute(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		route1.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.diametre(), route1.getLongueur(), "diametre => 1 route : "+route1.getLongueur());
	}
	
	void testeGrapheDiametrePlusGrandConnectees(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		Route route2 = plateau.getRoutes()[4]; // Seattle - Helena - 4
		Route route3 = plateau.getRoutes()[22]; // Helena - Omaha - 6
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);
		graphe.creeGraphePourUnJoueur(joueur);
		Test.assertEquals(graphe.diametre(),route1.getLongueur()+route2.getLongueur()+route3.getLongueur(), "diametre => routes connectees : "+(route1.getLongueur()+route2.getLongueur()+route3.getLongueur()));
	}
	
	void testeGrapheDiametrePlusGrandNonConnectees(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		Joueur joueur = new Joueur(1,"rouge");
		Route route1 = plateau.getRoutes()[0]; // Vancouver - Seattle - 1
		Route route2 = plateau.getRoutes()[11]; // Los Angeles - El Paso - 6
		Route route3 = plateau.getRoutes()[35]; // New York - Washington - 2
		route1.setProprietaire(joueur);
		route2.setProprietaire(joueur);
		route3.setProprietaire(joueur);		
		graphe.creeGraphePourUnJoueur(joueur);
		int max = Math.max(Math.max(route1.getLongueur(), route2.getLongueur()),route3.getLongueur());
		Test.assertEquals(graphe.diametre(),max, "diametre => routes connectees : "+max);
	}
}

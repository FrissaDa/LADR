
public class TestGrapheInitialisation{
	
	public static void main(String[] args) {
		TestGrapheInitialisation testGrapheInitialisation = new TestGrapheInitialisation();
		testGrapheInitialisation.testeGrapheTopologie();
	}

	void testeGrapheTopologie(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		// Vérifie le nombre de sommets du graphe (il existe 36 villes dans ADR)
		Test.assertEquals(graphe.getNombreSommets(), 36, "nombre de sommets = nombre de villes = 36");
		// La taille de la matrice est définie en fonction du nombre de sommets
		Test.assertEquals(graphe.getTopologie().length, graphe.getNombreSommets(), "taille topologie lignes = nombreSommets=36");
		Test.assertEquals(graphe.getTopologie()[0].length, graphe.getNombreSommets(), "taille topologie colonnes = nombreSommets=36");
		boolean valeurParDefaut = true;
		for(int[] ligne : graphe.getTopologie()){
			for(int valeur : ligne){
				valeurParDefaut &= (valeur == 0);
			}
		}
		// La topologie est initialisée à la valeur 0 après initialisation
		Test.assertTrue(valeurParDefaut,"valeurs 0 par défaut");
	}
}

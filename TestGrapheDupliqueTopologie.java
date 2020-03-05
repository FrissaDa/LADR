
public class TestGrapheDupliqueTopologie{
	
	public static void main(String[] args) {
		TestGrapheDupliqueTopologie testGrapheDupliqueTopologie = new TestGrapheDupliqueTopologie();
		testGrapheDupliqueTopologie.testeGrapheDupliqueTopologie();
	}

	/**
	 * Vérifie le résultat de la duplication de la topologie
	 */
	void testeGrapheDupliqueTopologie(){
		Plateau plateau = new Plateau();
		Graphe graphe = new Graphe(plateau);
		boolean valeurParDefaut = true;
		for(int[] ligne : graphe.getTopologie()){
			for(int valeur : ligne){
				valeurParDefaut &= (valeur == 0);
			}
		}
		Test.assertTrue(valeurParDefaut,"duplique => origine valeur 0 par défaut");
		
		int[][] nouvelleTopologie = graphe.dupliqueTopologie();
		// Valeurs nouvelle topologie identiques à l'original
		boolean valeursIdentiques = true;
		for(int i=0;i<graphe.getTopologie().length;i++){
			for(int j=0;j<graphe.getTopologie()[i].length;j++){
				valeursIdentiques &= (graphe.getTopologie()[i][j] == nouvelleTopologie[i][j]);
			}
		}
		Test.assertTrue(valeursIdentiques,"duplique => valeurs identiques");
		
		// Références différentes, changement dans la nouvelle topologie n'affecte
		// pas l'original
		for(int i=0;i<nouvelleTopologie.length;i++){
			for(int j=0;j<nouvelleTopologie[i].length;j++){
				nouvelleTopologie[i][j] = i+1;
			}
		}
		for(int[] ligne : graphe.getTopologie()){
			for(int valeur : ligne){
				valeurParDefaut &= (valeur == 0);
			}
		}
		Test.assertTrue(valeurParDefaut,"duplique => origine valeurs non changées, référence différente");
	}
}

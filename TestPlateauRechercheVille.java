public class TestPlateauRechercheVille{
	
	public static void main(String[] args) {
		TestPlateauRechercheVille testPlateauRecherche = new TestPlateauRechercheVille();
		testPlateauRecherche.testeRechercheVille();
		testPlateauRecherche.testeRechercheVilleInexistante();
	}
	
	/**
	 * @since Increment 1
	 */
	void testeRechercheVille(){
		Plateau plateau = new Plateau();
		Test.assertEquals(plateau.rechercheVille("Vancouver").versChaine(),"Vancouver (94.0,15.0)", "Recherche Ville => Vancouver (94.0,15.0)");
	}
	
	/**
	 * @since Increment 1
	 */
	void testeRechercheVilleInexistante(){
		Plateau plateau = new Plateau();
		Test.assertEquals(plateau.rechercheVille("Paris"),null, "Recherche Ville => Paris n'existe pas sur le plateau");
	}
	
	
}

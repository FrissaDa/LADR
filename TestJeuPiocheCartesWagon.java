public class TestJeuPiocheCartesWagon {

	public static void main(String[] args) {
		TestJeuPiocheCartesWagon testJeuPiocheCartesWagon = new TestJeuPiocheCartesWagon();
		testJeuPiocheCartesWagon.testeJeuPiocheCWCachee();
		testJeuPiocheCartesWagon.testeJeuPiocheCWVisiblePremiereCouleur();
		testJeuPiocheCartesWagon.testeJeuPiocheCWVisibleCinquiemeCouleur();
		testJeuPiocheCartesWagon.testeJeuPiocheCWVisibleIndiceInvalide();
	}
	
	void testeJeuPiocheCWCachee() {
		Jeu jeu = new Jeu();
		jeu.creeJoueurs(5);
		jeu.initialisePioches();
		CarteWagon[] cartesVisibles = jeu.getPiocheVisible();
		CarteWagon cw = jeu.getPiocheCartesWagon().getSommet();
		jeu.piocheCarteWagon(-1);
		Test.assertFalse(jeu.getPiocheCartesWagon().getSommet().equals(cw), "Jeu Pioche CW Cachee => une carte a été piochée");
		for(int i=0;i<jeu.getPiocheVisible().length;i++){
			Test.assertEquals(jeu.getPiocheVisible()[i],cartesVisibles[i], "Jeu Pioche CW Cachee => la carte visible numéro "+(i+1)+" n'a pas été modifiée");
		}
	}
	
	void testeJeuPiocheCWVisiblePremiereCouleur(){
		Jeu jeu = new Jeu();
		jeu.creeJoueurs(5);
		jeu.initialisePioches();
		jeu.affichePlateau();
		
		CarteWagon[] cartesVisibles = jeu.getPiocheVisible();
		CarteWagon premiereCarteVisible = jeu.getPiocheVisible()[0];
		CarteWagon carteSommetPiocheCachee = jeu.getPiocheCartesWagon().getSommet();
		jeu.piocheCarteWagon(0);
		Test.assertFalse(jeu.getPiocheVisible()[0].equals(premiereCarteVisible), "Jeu Pioche CW Visible Premiere Couleur => la première carte de la pioche visible a été piochée");
		Test.assertEquals(jeu.getPiocheVisible()[0],carteSommetPiocheCachee, "Jeu Pioche CW Visible Premiere Couleur => la première carte de la pioche visible a été remplacée par une carte de la pioche cachée");
		Test.assertFalse(carteSommetPiocheCachee.equals(jeu.getPiocheCartesWagon().getSommet()), "Jeu Pioche CW Visible Premiere Couleur => la première carte de la pioche cachée a été piochée");
		for(int i=1;i<jeu.getPiocheVisible().length;i++){
			Test.assertEquals(jeu.getPiocheVisible()[i],cartesVisibles[i], "Jeu Pioche CW Cachee => la carte visible numéro "+(i+1)+" n'a pas été modifiée");
		}
		
	}
	
	void testeJeuPiocheCWVisibleCinquiemeCouleur(){
		Jeu jeu = new Jeu();
		jeu.creeJoueurs(5);
		jeu.initialisePioches();
		jeu.affichePlateau();
		CarteWagon[] cartesVisibles = jeu.getPiocheVisible();
		CarteWagon premiereCarteVisible = jeu.getPiocheVisible()[4];
		CarteWagon carteSommetPiocheCachee = jeu.getPiocheCartesWagon().getSommet();
		jeu.piocheCarteWagon(4);
		Test.assertFalse(jeu.getPiocheVisible()[4].equals(premiereCarteVisible), "Jeu Pioche CW Visible Cinquieme Couleur => la cinquième carte de la pioche visible a été piochée");
		Test.assertEquals(jeu.getPiocheVisible()[4],carteSommetPiocheCachee, "Jeu Pioche CW Visible Cinquieme Couleur => la cinquième carte de la pioche visible a été remplacée par une carte de la pioche cachée");
		Test.assertFalse(carteSommetPiocheCachee.equals(jeu.getPiocheCartesWagon().getSommet()), "Jeu Pioche CW Visible Cinquieme Couleur => la première carte de la pioche cachée a été piochée");
		for(int i=0;i<jeu.getPiocheVisible().length-1;i++){
			Test.assertEquals(jeu.getPiocheVisible()[i],cartesVisibles[i], "Jeu Pioche CW Visible => la carte visible numéro "+(i+1)+" n'a pas été modifiée");
		}
	}
	
	void testeJeuPiocheCWVisibleIndiceInvalide(){
		Jeu jeu = new Jeu();
		jeu.creeJoueurs(5);
		jeu.initialisePioches();
		jeu.affichePlateau();
		Test.assertNoError(()->jeu.piocheCarteWagon(6),"Jeu Pioche CW Visible Indice Invalide => un indice invalide ne provoque pas d'erreur");
		Test.assertNoError(()->jeu.piocheCarteWagon(5),"Jeu Pioche CW Visible Indice Invalide => un indice invalide ne provoque pas d'erreur");
		Test.assertNoError(()->jeu.piocheCarteWagon(-2),"Jeu Pioche CW Visible Indice Invalide => un indice invalide ne provoque pas d'erreur");
	}
}

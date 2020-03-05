public class TestJoueurScore {

	public static void main(String[] args) {
		TestJoueurScore testJoueurScore = new TestJoueurScore();
		testJoueurScore.testeJoueurScoreInit();
		testJoueurScore.testeJoueurScoreAjouteZeroPositif();
		testJoueurScore.testeJoueurScoreAjouteZeroNegatif();
		testJoueurScore.testeJoueurScoreAjoutePositif();
		testJoueurScore.testeJoueurScoreAjouteNegatif();
	}

	void testeJoueurScoreInit(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getScore(),0, "Init Score Joueur => score = 0");
	}
	
	void testeJoueurScoreAjouteZeroPositif(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getScore(),0, "Ajout Score Zero Positif => score = 0");
		joueur.ajouteAuScore(3);
		Test.assertEquals(joueur.getScore(),3, "Ajout Score Zero Positif => score = 3");
	}
	
	void testeJoueurScoreAjouteZeroNegatif(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getScore(),0, "Ajout Score Zero Negatif => score = 0");
		joueur.ajouteAuScore(-10);
		Test.assertEquals(joueur.getScore(),-10, "Ajout Score Zero Negatif => score = -10");
	}
	
	void testeJoueurScoreAjoutePositif(){
		Joueur joueur = new Joueur(1,"bleu");
		joueur.ajouteAuScore(12);
		Test.assertEquals(joueur.getScore(),12, "Ajout Score Positif => score = 12");
		joueur.ajouteAuScore(7);
		Test.assertEquals(joueur.getScore(),19, "Ajout Score Positif => score = 19");
	}
	
	void testeJoueurScoreAjouteNegatif(){
		Joueur joueur = new Joueur(1,"bleu");
		joueur.ajouteAuScore(12);
		Test.assertEquals(joueur.getScore(),12, "Ajout Score Negatif => score = 12");
		joueur.ajouteAuScore(-12);
		Test.assertEquals(joueur.getScore(),0, "Ajout Score Negatif => score = 0");
	}
}
public class TestPiocheCWVidePleine {

	public static void main(String[] args) {
		TestPiocheCWVidePleine testPCWVP = new TestPiocheCWVidePleine();
		testPCWVP.testePileVide();
		testPCWVP.testePileNonVide();
		testPCWVP.testePilePleine();
	}

	void testePileVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		Test.assertTrue(pile.estVide(), "VidePleine PiocheCW Vide => La pile est vide");
		Test.assertFalse(pile.estPleine(), "VidePleine PiocheCW Vide => La pile n'est pas pleine");
	}
	
	void testePileNonVide() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		
		// L'accès au tableau interne et à l'indice du sommet n'est pas autorisé dans le cas général.
		// Le principe de la pile impose de n'utiliser que les méthodes "empile" et "depile" pour manipuler les
		// élements contenus dans la pile.
		// On veut pouvoir tester "vide/pleine" indépendamment de l'implémentation des méthodes "empile" et "depile" et 
		// donc on déroge à la règle.
		pile.cartes[0] = new CarteWagon(null);
		pile.indiceSommet++;
		Test.assertFalse(pile.estPleine(), "VidePleine PiocheCW NonVide => La pile n'est pas pleine");
		Test.assertFalse(pile.estVide(), "VidePleine PiocheCW NonVide => La pile n'est pas vide");
	}
	
	void testePilePleine() {
		PiocheCartesWagon pile = new PiocheCartesWagon();
		
		// L'accès au tableau interne et à l'indice du sommet n'est pas autorisé dans le cas général.
		// Le principe de la pile impose de n'utiliser que les méthodes "empile" et "depile" pour manipuler les
		// élements contenus dans la pile.
		// On veut pouvoir tester "vide/pleine" indépendamment de l'implémentation des méthodes "empile" et "depile" et 
		// donc on déroge à la règle.
		for(int i=0;i<pile.cartes.length;i++){
			pile.cartes[i] = new CarteWagon(null);
			pile.indiceSommet++;
		}
		Test.assertTrue(pile.estPleine(), "VidePleine PiocheCW Pleine => La pile est pleine");
		Test.assertFalse(pile.estVide(), "VidePleine PiocheCW Pleine => La pile n'est pas vide");
	}
}
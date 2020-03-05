public class TestJoueurWagons {

	public static void main(String[] args) {
		TestJoueurWagons testJoueurWagon = new TestJoueurWagons();
		testJoueurWagon.testeJoueurWagonInit();
		testJoueurWagon.testeJoueurWagonsRetireZeroPositif();
		testJoueurWagon.testeJoueurWagonsRetireZeroNegatif();
		testJoueurWagon.testeJoueurWagonsRetirePositif();
		testJoueurWagon.testeJoueurWagonsRetireNegatif();
	}

	void testeJoueurWagonInit(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getNombreWagons(),45, "Init Wagon Joueur => Wagons = 45");
	}
	
	void testeJoueurWagonsRetireZeroPositif(){
		Joueur joueur = new Joueur(1,"bleu");
		joueur.enleveWagons(45);
		Test.assertEquals(joueur.getNombreWagons(),0, "Retire Wagons Zero Positif =>  Wagons = 0");
		joueur.enleveWagons(3);
		Test.assertEquals(joueur.getNombreWagons(),0, "Retire Wagons Zero Positif =>  Wagons = 0");
	}
	
	void testeJoueurWagonsRetireZeroNegatif(){
		Joueur joueur = new Joueur(1,"bleu");
		joueur.enleveWagons(45);
		Test.assertEquals(joueur.getNombreWagons(),0, "Retire Wagons Zero Negatif =>  Wagons = 0");
		joueur.enleveWagons(-10);
		Test.assertEquals(joueur.getNombreWagons(),0, "Retire Wagons Zero Negatif =>  Wagons = 0");
	}
	
	void testeJoueurWagonsRetirePositif(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getNombreWagons(),45, "Retire Wagons Positif =>  Wagons = 45");
		joueur.enleveWagons(7);
		Test.assertEquals(joueur.getNombreWagons(),38, "Retire Wagons Positif =>  Wagons = 38");
	}
	
	void testeJoueurWagonsRetireNegatif(){
		Joueur joueur = new Joueur(1,"bleu");
		Test.assertEquals(joueur.getNombreWagons(),45, "Retire Wagons Negatif =>  Wagons = 45");
		joueur.enleveWagons(-12);
		Test.assertEquals(joueur.getNombreWagons(),45, "Retire Wagons Negatif =>  Wagons = 45");
	}
}
public class TestPiocheCWAffichage {

	public static void main(String[] args) {
		TestPiocheCWAffichage testPCWA = new TestPiocheCWAffichage();
		testPCWA.testeCWAffichageVide();
		testPCWA.testeCWAffichageUne();
		testPCWA.testeCWAffichagePlusieurs();
		testPCWA.testeCWAffichagePleine();
		
	}

	void testeCWAffichageVide() {
		PiocheCartesWagon pioche = new PiocheCartesWagon();
		Test.assertTrue(pioche.estVide(), "Affichage PiocheCW Vide => pile vide");
		Test.assertEquals(pioche.versChaine(), "", "Affichage PiocheCW Vide => La représentation textuelle de la pioche est vide.");
	}
	
	void testeCWAffichageUne() {
		PiocheCartesWagon pioche = new PiocheCartesWagon();
		pioche.empile(new CarteWagon("noir"));
		Test.assertFalse(pioche.estVide(), "Affichage PiocheCW Une => pile non vide");
		Test.assertEquals(pioche.versChaine(), "1: "+pioche.getSommet().versChaine()+"\n", "Affichage PiocheCW Une => La représentation textuelle de la pioche est "+pioche.getSommet().versChaine());
	}
	
	void testeCWAffichagePlusieurs() {
		PiocheCartesWagon pioche = new PiocheCartesWagon();
		pioche.empile(new CarteWagon("noir"));
		pioche.empile(new CarteWagon("bleu"));
		pioche.empile(new CarteWagon("rouge"));
		pioche.empile(new CarteWagon("vert"));
		Test.assertFalse(pioche.estVide(), "Affichage PiocheCW Plusieurs => pile non vide");
		Test.assertEquals(pioche.versChaine(), "1: Wagon noir\n2: Wagon bleu\n3: Wagon rouge\n4: Wagon vert\n", "Affichage PiocheCW Plusieurs => La représentation textuelle de la pioche est \n1: Wagon noir\n2: Wagon bleu\n3: Wagon rouge\n4: Wagon vert");
	}
	
	void testeCWAffichagePleine() {
		PiocheCartesWagon pioche = new PiocheCartesWagon();
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			for(int j=0;j<12;j++){
				pioche.empile(new CarteWagon(Donnees.COULEURS_WAGON[i]));
			}
		}
		for(int j=0;j<14;j++){
			pioche.empile(new CarteWagon(null));
		}
		
		StringBuilder sb = new StringBuilder();
		int numero=1;
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			for(int j=0;j<12;j++){
				sb.append(numero+": "+new CarteWagon(Donnees.COULEURS_WAGON[i]).versChaine());
				sb.append("\n");
				numero++;
			}
		}
		for(int j=0;j<14;j++,numero++){
			sb.append(numero+": "+new CarteWagon(null).versChaine());
			sb.append("\n");
		}
		Test.assertTrue(pioche.estPleine(), "Affichage PiocheCW Pleine => pile pleine");
		Test.assertEquals(pioche.versChaine(), sb.toString(), "Affichage PiocheCW Pleine => La représentation textuelle de la pioche est "+sb.toString());
	}
}
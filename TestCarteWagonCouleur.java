public class TestCarteWagonCouleur {

	public static void main(String[] args) {
		TestCarteWagonCouleur testCWC = new TestCarteWagonCouleur();
		testCWC.testeCouleurIdentique();
		testCWC.testeCouleurDifferente();
		testCWC.testeCouleurLocomotive();
		testCWC.testeCouleurNonLocomotive();
		testCWC.testeCouleurInexistante();
	}
	
	void testeCouleurIdentique(){
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			CarteWagon carte = new CarteWagon(Donnees.COULEURS_WAGON[i]);
			Test.assertTrue(carte.estDeCouleur(Donnees.COULEURS_WAGON[i]),	"Couleur CW Couleur OK => carte wagon "+Donnees.COULEURS_WAGON[i]+" est de couleur "+Donnees.COULEURS_WAGON[i]);
		}
	}
	
	void testeCouleurDifferente(){
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			CarteWagon carte = new CarteWagon(Donnees.COULEURS_WAGON[i]);
			if(i<Donnees.COULEURS_WAGON.length-1){
				Test.assertFalse(carte.estDeCouleur(Donnees.COULEURS_WAGON[i+1]),"Couleur CW Couleur Différente => carte wagon "+Donnees.COULEURS_WAGON[i]+" n'est pas de couleur "+Donnees.COULEURS_WAGON[i+1]);
			} else {
				Test.assertFalse(carte.estDeCouleur(Donnees.COULEURS_WAGON[0]),"Couleur CW Couleur Différente => carte wagon "+Donnees.COULEURS_WAGON[i]+" n'est pas de couleur "+Donnees.COULEURS_WAGON[0]);
			}
		}
	}
	
	void testeCouleurLocomotive(){
		CarteWagon locomotive = new CarteWagon(null);
		Test.assertTrue(locomotive.estDeCouleur(null),"Couleur CW Locomotive => carte locomotive est locomotive");
		Test.assertFalse(locomotive.estDeCouleur(Donnees.COULEURS_WAGON[0]),"Couleur CW Locomotive => carte locomotive n'est pas de couleur "+Donnees.COULEURS_WAGON[0]);
	}
	
	void testeCouleurNonLocomotive(){
		for(int i=0;i<Donnees.COULEURS_WAGON.length;i++){
			CarteWagon carte = new CarteWagon(Donnees.COULEURS_WAGON[i]);
			Test.assertFalse(carte.estDeCouleur(null),"Couleur CW Couleur Non Locomotive => carte wagon "+Donnees.COULEURS_WAGON[i]+" n'est pas une locomotive");
		}
	}
	
	void testeCouleurInexistante(){
		CarteWagon carte = new CarteWagon(Donnees.COULEURS_WAGON[0]);
		Test.assertFalse(carte.estDeCouleur("bleuclair"),"Couleur CW Couleur Inexistante => carte "+Donnees.COULEURS_WAGON[0]+" n'est pas d'une couleur invalide");
		carte = new CarteWagon("bleuclair");
		Test.assertTrue(carte.estDeCouleur("bleuclair"),"Couleur CW Couleur Inexistante => carte bleuclair détectée");
	}
}

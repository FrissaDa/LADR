/** e
 * La classe Ville represente une ville du jeu des Aventuriers du Rail.
 * Une ville dispose d'un nom, d'une abscisse et d'une ordonnee pour l'affichage
 * sur la carte. e
 * @since Increment 1
 * @ACOMPLETER
 */
class Ville {
	

	String nom;

	double x;

	double y;

	int numero;

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	String versChaine(){
		return getNom() + " (" + getX() + "," + getY() +")" ;
	}
	

	Ville(String nom, double x, double y, int numero){
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.numero=numero;
	}
}

/** 
 * La classe Joueur represente un joueur des Aventuriers du Rail.
 * Un joueur dispose d'un numero, d'une couleur, d'un nombre de wagon et d'un score.
 * A partir de l'increment 7, le joueur est associe a une liste de cartes qui constituent sa main. 
 * @since Increment 2
 * @ACOMPLETER
 */
class Joueur {
	/**
	 * le numero du joueur
	 */
	// TODO declarer le numero du joueur
	int numero;

	/**
	 * la couleur du joueur
	 */
	// TODO declarer la couleur du joueur
	String couleur;
	/**
	 * le nombre de wagons restants au joueur
	 */
	// TODO declarer le nombre de wagons du joueur
	int nombreWagons;
	/**
	 * le score du joueur
	 */
	// TODO declarer le score du joueur
	int score;
	/**
	 * Cree un joueur et initialise son score a 0 et son nombre de wagon a 45, suivant
	 * les regles du jeu.
	 * A partir de l'increment 7, initialise la main du joueur.
	 * @param numero le numero du joueur
	 * @param couleur la couleur du joueur
	 */
	// TODO Declarer le constructeur
	Joueur(int numero, String col){
		this.couleur = col;
		this.score = 0;
		this.numero = numero;
		this.nombreWagons = 15;
		//this.nombreWagons=45;
	}
	// ACCESSEURS
	// TODO declarer les accesseurs pour chaque attribut de la classe
	/**
	 * @return the couleur
	 */
	public String getCouleur() {
		return couleur;
	}
	/**
	 * @return the nombreWagons
	 */
	public int getNombreWagons() {
		return nombreWagons;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param nombreWagons the nombreWagons to set
	 */

	public void enleveWagons(int nbWag){
		if (nbWag>0){
			this.nombreWagons = this.nombreWagons - nbWag;
			if(this.nombreWagons<0){
				this.nombreWagons=0;
			}
		}
		
	}


	public void ajouteAuScore(int scoreAdd){
		this.score += scoreAdd;
	}



	ListeCartesWagon mainCartesWagon = ListeCartesWagon.VIDE;
	
	/**
	 * @return la main (liste de cartes "wagon") du joueur
	 * @since Incr�ment 7
	 */
	// TODO d�clarer l'accesseur en lecture de la main du joueur
	ListeCartesWagon getMainCartesWagon(){
		return this.mainCartesWagon;
	}

	/**
	 * Associe une liste de cartes "wagon" au joueur
	 * @param mainCartesWagon l'objet {@link ListeCartesWagon} qui correspond �
	 *            la liste de cartes "wagon"
	 * @since Incr�ment 7
	 */
	// TODO d�clarer l'accesseur en �criture de la main du joueur
	void setMainCartesWagon(ListeCartesWagon mainCartesWagon){
		this.mainCartesWagon = mainCartesWagon;
	}

	/**
	 * Ajoute une carte "wagon" � la main du joueur
	 * @param carte l'objet {@link CarteWagon} � ajouter
	 * @since Incr�ment 7
	 */
	// TODO d�clarer la m�thode prendCarteWagon
	void prendsCarteWagon(CarteWagon carte){
		this.mainCartesWagon = new ListeCartesWagon(carte,mainCartesWagon);
	}
	
}

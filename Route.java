/** 
 * La classe Route represente une route dans le jeu des Aventuriers du Rail.
 * Une route dispose d'une ville de depart, d'une ville d'arrivee et d'une couleur.
 * A partir de l'increment 3, une route pourra etre possedee par un joueur.
 * @since Increment 1
 * @ACOMPLETER
 */
class Route {



	
	Ville villeDepart;
    Ville villeArrivee;
	String couleur;
	Joueur proprietaire;

	// ACCESSEURS

	Ville getVilleDepart(){
        return this.villeDepart;
    }

    Ville getVilleArrivee(){
        return this.villeArrivee;
    }

    String getCouleur(){
        return this.couleur;
    }

	/**
	 * @return the proprietaire
	 */
	public Joueur getProprietaire() {
		return proprietaire;
	}

	/**
	 * @param proprietaire the proprietaire to set
	 */
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}


	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	

	/**
	 * Le calcul de la longueur de la route depend des coordonnees des villes
	 * sur l'ecran. Pour respecter le ratio present sur le plateau de jeu
	 * original, on propose d'implementer l'algorithme suivant : 1. on calcule
	 * la distance entre la ville de depart et la ville d'arrivee (longueur du
	 * segment) 2. on utilise la valeur 33 pour appliquer ratio sur la distance
	 * calculee 3. on conserve le minimum entre la longueur obtenue apres ratio
	 * et la valeur maximale 6.
	 * 
	 * @return la longueur de la route (entre 1 et 6), calculee en fonction de
	 *         la position des villes
	 */

	private double sqr(double a){
		return a*a;
	}

	int getLongueur() {
		int temp = (int) Math.sqrt(sqr(villeArrivee.getY()-villeDepart.getY()) + sqr(villeArrivee.getX()-villeDepart.getX()));
		temp = (int) temp/33;
		if (temp<6)
			return temp;
		else
			return 6;
	}
	


	
	/**
	 * Transforme la longueur de la route en un nombre de points qu'elle
	 * rapporte au joueur qui en devient proprietaire
	 * @return une valeur entiere entre 1 et 15; une autre valeur indique une
	 *         erreur dans les donnees de la route
	 */
	// TODO Transformer la longueur de la route en points
	
	int getNombrePoints(){
		//int longueur = getLongueur();
		switch (getLongueur()){
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 4;
			case 4:
				return 7;
			case 5:
				return 10;
			case 6:
				return 15;
			default:
				return -1;
		}
	}

	/**
	 * @return une representation textuelle de la route. On indique le nom de la
	 *         route puis sa couleur et sa longueur separees par un tiret '-'.
	 */
	// TODO Renvoyer la representation textuelle de la route

    String getNom(){
        return this.villeDepart.getNom() + " - " + this.villeArrivee.getNom();
	}
	
	String versChaine(){
		return getNom() + " /" + this.couleur + "-" + getLongueur();
	}

    Route (Ville villeDepart, Ville villeArrivee, String couleur){
        this.villeArrivee = villeArrivee;
        this.villeDepart = villeDepart;
        this.couleur = couleur;
    }
}

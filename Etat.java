/**
 * La classe Etat represente un etat des Etats-Unis a dessiner sur le plateau de jeu.
 * Chaque etat dispose d'un nom et d'un contour (ensemble de coordonnees permettant 
 * de dessiner la carte).
 * @since Increment 1
 * @FOURNI
 */


class Etat {

	/**
	 * le nom de l'etat
	 */
	String nom;
	
	/**
	 * les coordonnees des points permettant de dessiner l'etat
	 */
	String contour;

	/**
	 * Cree un etat
	 * @param nom le nom de l'etat
	 * @param contour les coordonnees des points a dessiner
	 */
	Etat(String nom, String contour) {
		this.nom = nom;
		this.contour = contour;
	}

	// ACCESSEURS
	/**
	 * @return le nom de l'etat
	 * @FOURNI
	 */
	String getNom() {
		return this.nom;
	}

	/**
	 * @return les coordonnees des points permettant de dessiner l'etat
	 * @FOURNI
	 */
	String getContour() {
		return this.contour;
	}

}

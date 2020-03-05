/**
 * La classe Plateau représente le plateau de jeu des Aventuriers du Rail.
 * Il se compose d'un ensemble d'objets {@link Etat}, d'un ensemble d'objets {@link Ville} et d'un
 * ensemble d'objets {@link Route}.
 * @since Incrément 1
 * @ACOMPLETER
 */
class Plateau {

	/**
	 * La liste des états du plateau de jeu
	 * @FOURNI
	 */
	Etat[] etats;

	/**
	 * La liste des villes du plateau de jeu
	 */
	// TODO déclarer les villes du plateau
	Ville[] villes;
	/**
	 * La liste des routes du plateau de jeu
	 */
	// TODO déclarer les routes du plateau
	Route[] routes;
	
	/**
	 * Crée le plateau de jeu
	 */
	Plateau() {
		initialiseEtats(); // @FOURNI
		// TODO Compléter avec l'initialisation des villes et des routes
		initialiseVilles();
		initialiseRoutes();
	}

	// ACCESSEURS
	/**
	 * @return les états du plateau de jeu
	 * @FOURNI
	 */
	Etat[] getEtats() {
		return this.etats;
	}
	Ville[] getVilles(){
		return this.villes;
	}
	Route[] getRoutes(){
		return this.routes;
	}

	
	/**
	 * Initialise l'ensemble des états du jeu à partir des données fournies (
	 * {@link Donnees#ETATS}) Les données utilisées sont transformées pour
	 * isoler le nom de l'état et son "contour".
	 * @FOURNI
	 */
	void initialiseEtats() {
		this.etats = new Etat[Donnees.ETATS.length];
		for(int i=0;i<Donnees.ETATS.length;i++){
			etats[i] = new Etat(Donnees.ETATS[i][0],Donnees.ETATS[i][1]);
		}
	}	
	/**
	 * Initialise l'ensemble des villes du jeu à partir des données fournies (
	 * {@link Donnees#VILLES}) Les données utilisées sont transformées pour
	 * isoler le nom de la ville, son abscisse et son ordonnée.
	 */
	// TODO Initialiser les villes du plateau de jeu
	void initialiseVilles(){
		this.villes = new Ville[Donnees.VILLES.length]; //villes = tableau de villes
		for(int i=0;i<Donnees.VILLES.length;i++){ //on parcourt chaque ville et ses coordonnées
			String part[] = Donnees.VILLES[i].split(" "); //Las Vegas 94 25
			switch (part.length){
				case 3:
					double x = Double.parseDouble(part[1]);
					double y = Double.parseDouble(part[2]);
					villes[i] = new Ville(part[0],x,y,i);
					break;
				case 4:
					double x2 = Double.parseDouble(part[2]);
					double y2 = Double.parseDouble(part[3]);
					villes[i] = new Ville(part[0]+" "+part[1],x2,y2,i);
					break;
				case 5:
					double x3 = Double.parseDouble(part[3]);
					double y3 = Double.parseDouble(part[4]);
					villes[i] = new Ville(part[0]+" "+part[1]+" "+part[2],x3,y3,i);
					break;
				default:
					break;
			}
		}
}
	/**
	 * Initialise l'ensemble des routes du jeu à partir des données fournies (
	 * {@link Donnees#ROUTES}) Les données utilisées sont transformées pour
	 * isoler le nom de la ville de départ, le nom de la ville d'arrivée et la
	 * couleur de la route. La taille de la route est calculée à partir des
	 * coordonnées des villes ({@link Route#getLongueur()})
	 */
	// TODO Initialiser les routes du plateau de jeu
	// Attention : Route(Ville villeDepart, Ville villeArrivee, String couleur) - ne pas faire Route(string,string,string)
	void initialiseRoutes(){
		this.routes = new Route[Donnees.ROUTES.length]; //la liste routes est un objet de type ROUTES de la longueur qui convient
		for(int i=0;i<Donnees.ROUTES.length;i++){
			Ville villeDepart = rechercheVille(Donnees.ROUTES[i][0]);
			Ville villeArrivee = rechercheVille(Donnees.ROUTES[i][1]);
			routes[i] = new Route(villeDepart,villeArrivee,Donnees.ROUTES[i][2]);	
		}
	}

	
	/**
	 * Recherche d'une ville à partir de son nom
	 * @param nom le nom de la ville
	 * @return un objet {@link Ville} correspond au nom recherché, {@code null} sinon
	 */

	Ville rechercheVille(String nom){
		for(int i=0;i<villes.length;i++){
			if(nom.equals(villes[i].nom)) //Erreurs faites : villes[i][0] ne fonctionne pas car c'est un objet ville, nom.equals au lieu de ==, return null en sortie
				return villes[i];
		}
		return null;
	}
	
	/**
	 * Recherche d'une route à partir de son nom
	 * @param nom le nom de la route
	 * @return un objet {@link Route} correspond au nom recherché, {@code null} sinon
	 * @EXTENSION
	 */
	// TODO Implementer la recherche d'une route
	public Route rechercheRoute(String nom){
		for(int i=0;i<routes.length;i++){
			if(nom.equals(routes[i].getNom())){
				return routes[i];
			}
		}
		return null;
	}
}

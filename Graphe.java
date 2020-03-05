/**
 * La classe Graphe est la représentation du plateau de jeu pour le calcul des bonus du jeu.
 * Un graphe dispose d'un nombre de sommets correspondants aux villes du plateau de jeu. 
 * Pour l'implémentation des algorithmes principaux liés à la théorie des graphes, la topologie
 * du graphe représente les connexions existantes entre les sommets et est construite à partir 
 * des données du plateau de jeu et de la partie en cours.
 * @since Incrément 8
 */
class Graphe {
	
	/**
	 * Matrice d'entiers qui représente la topologie du graphe. Pour chaque arc
	 * existant entre deux sommets i et j, on insère la valeur 1 pour le couple
	 * [i][j] et [j][i].
	 */
	// TODO déclarer la topologie du graphe
	int[][] topologie = new int[36][36];
	/**
	 * Le plateau de jeu
	 */
	// TODO déclarer le plateau de jeu
	Plateau plateau;
	/**
	 * Le nombre de sommets du graphe
	 */
	// TODO déclarer le nombre de sommet
	int nombreSommets=36;
	/**
	 * Crée un graphe à partir du plateau de jeu et initialise toutes les valeurs
	 * de la matrice de topologie à 0.
	 * @param plateau le plateau de jeu
	 */
	// TODO déclarer le constructeur
	Graphe(Plateau plateau){
		for (int i=0;i<36;i++){
			for(int j=0;j<36;j++){
				this.topologie[i][j]=0;
			}
		}
		this.plateau=plateau;
	}
	
	// ACCESSEURS
	
	// TODO déclarer les accesseurs en lecture
	/**
	 * @return the nombreSommet
	 */
	public int getNombreSommets() {
		return nombreSommets;
	}
	/**
	 * @return the plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	/**
	 * @return the topologie
	 */
	public int[][] getTopologie() {
		return topologie;
	}
	/**
	 * Initialise la topologie pour représenter le graphe d'un joueur. Le graphe
	 * d'un joueur contient un arc entre deux sommets si le joueur est 
	 * propriétaire de la route entre les deux villes correspondantes
	 * @param joueur un joueur
	 */
	// TODO déclarer la méthode creeGraphePourUnJoueur
	void creeGraphePourUnJoueur(Joueur joueur){
		for(Route r : this.plateau.getRoutes()){
			if(r.getProprietaire()==joueur){
				this.topologie[r.getVilleDepart().getNumero()][r.getVilleArrivee().getNumero()]=r.getLongueur();
				this.topologie[r.getVilleArrivee().getNumero()][r.getVilleDepart().getNumero()]=r.getLongueur();
			}
		}
	}
	
	/**
	 * Parcourt en profondeur le graphe du joueur à partir du numéro de la
	 * ville, passé en paramètre
	 * @param numeroVille la ville de départ du parcours
	 * @param marques tableau de marques créées lorsque l'algorithme passe dans
	 *            une ville du graphe
	 * @return le tableau de marques après parcours
	 */
	// TODO déclarer la méthode parcoursProfondeur
	boolean[] parcoursProfondeur (int numeroVille, boolean [] marques) {
		marques[numeroVille]=true;
		for (int i=0;i<this.getNombreSommets();i++){
			if(this.topologie[numeroVille][i]!=0){
				if(!marques[i]){
					parcoursProfondeur(i, marques);
				}
			}
		}
		return marques;
   
   	}
	/**
     * Indique si une ville d'arrivée est accessible à partir d'une ville de 
     * départ en utilisant les routes en possession du joueur.
     * @param villeDepart la ville de départ
     * @param villeArrivee la ville d'arrivée
     * @return vrai si la ville est atteignable, faux sinon
     */
	// TODO déclarer la méthode sommetAtteignable
	boolean sommetAtteignable(int numeroSommetDepart, int numeroSommetArrivee) {
		boolean mark[] = new boolean [this.getNombreSommets()];
		this.parcoursProfondeur(numeroSommetDepart, mark);
		return mark[numeroSommetArrivee];
    }
	/**
	 * Calcule le nombre de groupes connexes présents dans la topologie.
     *  Nécessaire pour l'attribution du bonus "connexité"
	 * @return le nombre de groupes connexes présents dans la topologie
	 */
	// TODO déclarer la méthode calculConnexite
	
	/**
	 * Crée une copie de la topologie du graphe
	 * @return une nouvelle matrice, copie de la topologie
	 */
	// TODO déclarer la méthode dupliqueTopologie
	
	/**
	 * Implémentation de l'algorithme de Floyd pour le calcul des plus courts 
	 * chemins.
	 * @return une matrice contenant la distance minimale entre deux sommets
	 */
	// TODO déclarer la méthode plusCourtsCheminsFloyd
	
	/**
	 * Calcule le diamètre du graphe en utilisant l'algorithme de Floyd.
	 * @return le diamètre du graphe
	 */
	// TODO déclarer la méthode diametre
}

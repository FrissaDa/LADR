/** 
 * La classe ActionUtilisateur est utilisee pour recuperer les actions
 * effectuees par le joueur et les donnees correspondantes. L'action de
 * l'utilisateur est identifiee par une valeur de l'enumeration
 * {@link TypeActionUtilisateur} et est liee a une valeur, appelee
 * {@code parametre} sous la forme d'une chaine de caracteres.
 */
class ActionUtilisateur {
	
	/**
	 * Le type de l'action realisee par l'utilisateur
	 */
	TypeActionUtilisateur type;
	
	/**
	 * Les donnees produites par l'action de l'utilisateur
	 */
	String parametre;
	
	/**
	 * Cree une action utilisateur
	 * @param type le type de l'action
	 * @param param les donnees liees a l'action
	 */
	ActionUtilisateur(TypeActionUtilisateur type, String param){
		this.type = type;
		this.parametre = param;
	}
	
	// ACCESSEURS
	TypeActionUtilisateur getType(){
		return this.type;
	}
	
	String getParametre(){
		return this.parametre;
	}
}

/** 
 * La classe TypeActionUtilisateur sert a identifier une action de l'utilisateur
 * sur l'interface graphique de maniere simplifiee.
 * Dans la version actuelle du jeu des ADR, l'interface graphique offre trois 
 * actions a l'utilisateur : (1) la fin du tour ({@code FINTOUR}), la selection
 * d'une route ({@code ROUTE}), et la pioche d'une carte wagon ({@code PIOCHECW}).
 * @since Increment 1
 */
enum TypeActionUtilisateur {
	FINTOUR,
	ROUTE,
	PIOCHECW;
}

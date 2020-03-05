import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * La classe Fenetre est la fenetre principale du jeu. La fenetre principale permet
 * d'afficher des formes, de rechercher une forme dessinee sur la fenetre, de selectionner
 * une forme dessinee sur la fenetre ou de supprimer une forme dessinee sur la fenetre.
 */
class Fenetre {
	
	/**
	 * Constantes utilisees pour la fonctionnalite de zoom
	 */
	public static final int SCENE_HAUTEUR_MARGE = 50;
	public static final int SCENE_LARGEUR_MARGE = 50;
	public static final double ZOOM_FACTOR_STEP = 1.02;


	/**
	 * Groupe racine contenant l'ensemble des elements graphiques de la fenetre
	 */
	Group groupeFormes;

	/**
	 * Liste les differents elements affiches sur la fenetre
	 */
	List<Node> listeFormes;

	/**
	 * Suite  la selection d'une forme sur la fenetre, contient la forme selectionnee,
	 * {@code null} sinon.
	 */
	Node formeSelectionnee;
	
	/**
	 * Conteneur principal de la fenetre
	 */
	Stage stage;
	
	/**
	 * Graphe de scene qui contient l'ensemble du contenu affichee sur la fenetre
	 */
	Scene scene;

	/**
	 * Abcisse pour le positionnement d'une fenetre de popup lorsqu'un evenement
	 * genere un message d'erreur
	 */
	double popupX;

	/**
	 * Ordonnee pour le positionnement d'une fenetre de popup lorsqu'un
	 * evenement genere un message d'erreur
	 */
	double popupY;

	/**
	 * Contient le ratio utilise pour l'affichage des elements graphiques.
	 * Est modifie par le zoom 
	 */ 
	DoubleProperty zoomProperty;
	
	/**
	 * Fond de carte pour l'affichage des etats, villes et routes.
	 */
	Rectangle fondCarte;
	
	// ACCESSEURS
	Group getGroupeFormes(){
		return this.groupeFormes;
	}

	List<Node> getListeFormes(){
		return this.listeFormes;
	}

	Node getFormeSelectionnee(){
		return this.formeSelectionnee;
	}

	Stage getStage(){
		return this.stage;
	}

	Scene getScene(){
		return this.scene;
	}

	double getPopupX(){
		return this.popupX;
	}

	double getPopupY(){
		return this.popupY;
	}
	
	double getRatio(){
		return this.zoomProperty.get();
	}
	
	DoubleProperty getZoomProperty() {
		return this.zoomProperty;
	}
	
	/**
	 * Getting rid of JavaFX unexpected errors.
	 * Should be deactivated when debugging applications.
	 * @param t the thread that causes an error
	 * @param e the exception thrown
	 */
	private static void showError(Thread t, Throwable e) {
        if (Platform.isFxApplicationThread()) {
        	// Ignore errors but kept for future use
        } else {
        	// Ignore errors but kept for future use
        }
    }

	/**
	 * Affiche la fenetre principale du jeu
	 * @param title le titre de la fenetre
	 * @param largeur la largeur de la fenetre
	 * @param hauteur la hauteur de la fenetre
	 * @param echelle le grossissement de la fenetre
	 * @param couleurFond la couleur de fond de la fenetre
	 */
	void affiche(String title, int largeur, int hauteur, double echelle, String couleurFond) {
		this.listeFormes = new ArrayList<>();
		// Initialisation du ToolKit JavaFX
		new JFXPanel();
		Platform.runLater(() -> {
			// Construction du groupe racine
			this.groupeFormes = new Group();
			Scale scale = new Scale();
			scale.setPivotX(0);
			scale.setPivotY(0);
			
			// Gestion du zoom (zoomProperty est la valeur de l'echelle)
			this.zoomProperty = new SimpleDoubleProperty(echelle);
			scale.setX(this.zoomProperty.get());
			scale.setY(this.zoomProperty.get());

			// Application du zoom lorsque la propriete est modifiee
			this.zoomProperty.addListener(new InvalidationListener() {
	            @Override
	            public void invalidated(Observable param) {
	            	boolean agrandie = (scale.getX()<((DoubleProperty)param).get());
	            	// Modification de l'echelle
	            	scale.setX(getZoomProperty().get());
	            	scale.setY(getZoomProperty().get());
	            	// Adaptation de la taille de la fenetre au contenu
	            	if(((DoubleProperty)param).get()!=IHM.DISPLAY_RATIO){
		            	if(agrandie){
		            		getStage().setWidth(getStage().getWidth() * ZOOM_FACTOR_STEP);
		            		getStage().setHeight(getStage().getHeight() * ZOOM_FACTOR_STEP);
		            	} else {
		            		getStage().setWidth(getStage().getWidth() / ZOOM_FACTOR_STEP);
		            		getStage().setHeight(getStage().getHeight() / ZOOM_FACTOR_STEP);
		            	}
	            	}
	            }
	        });
			
			this.groupeFormes.getTransforms().add(scale);
			
			// Construction de la scene
			this.scene = new Scene(this.groupeFormes, largeur, hauteur);
			this.scene.setFill(creeCouleur(couleurFond, 1.0));

			// Capture des evenements de scrolling
			this.scene.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
	            @Override
	            public void handle(ScrollEvent event) {
	                if (event.getDeltaY() > 0) {
	                	getZoomProperty().set(getZoomProperty().get() * ZOOM_FACTOR_STEP);
	                } else if (event.getDeltaY() < 0) {
	                	getZoomProperty().set(getZoomProperty().get() / ZOOM_FACTOR_STEP);
	                }
	            }
	        });
			// Capture des evenements du clavier
			// Le facteur de zoom est remis  la valeur par defaut lorsque
			// l'utilisateur tape sur la touche "HOME"
			this.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent key) {
					if(key.getCode() == KeyCode.ESCAPE){
						getZoomProperty().set(IHM.DISPLAY_RATIO);
						getStage().setWidth(largeur);
						getStage().setHeight(hauteur);
					}
				}
			});
			
			this.stage = new Stage();
			this.stage.setResizable(false);

			// Fond de carte
			dessineFondCarte(couleurFond);
			this.groupeFormes.getChildren().add(this.fondCarte);
			
			// Construction de la fenetre
			this.stage.setTitle(title);
			this.stage.setScene(this.scene);
			this.stage.setOnCloseRequest(event -> {
				System.exit(0);
			});
			
			// Capture des evenements "souris" lors d'un clic sur une forme
			this.groupeFormes.setOnMouseClicked(event -> {
				if (event.getTarget() instanceof Shape) {
					this.formeSelectionnee = (Shape) event.getTarget();
				} else {
					this.formeSelectionnee = (Node) event.getTarget();
				}
				this.popupX = event.getScreenX();
				this.popupY = event.getScreenY()-20;
			});
			
			// Affichage de la fenetre
			this.stage.show();
		});
	}

	/**
	 * Dessine un rectangle pour isoler la carte du plateau du fond de la fenetre.
	 * Permet de simuler une bordure de 20 pixels sur les bords de l'interface
	 * pour afficher notamment la couleur du joueur.
	 * @param couleurFond la couleur de fond de la carte
	 */
	void dessineFondCarte(String couleurFond){
		this.fondCarte = new Rectangle();
		this.fondCarte.setId("back");
		this.fondCarte.setWidth(0.785*this.scene.getWidth());
		this.fondCarte.setHeight(0.75*this.scene.getHeight());
		this.fondCarte.setFill(Fenetre.creeCouleur(couleurFond, 1.0));
		this.fondCarte.setX(10);
		this.fondCarte.setY(10);
		this.fondCarte.setArcWidth(20);
		this.fondCarte.setArcHeight(20);
	}

	/**
	 * Dessine une forme sur la fenetre
	 * @param forme la forme  dessiner
	 */
	void dessineForme(Node forme) {
		this.listeFormes.add(forme);
		Platform.runLater(() -> {
			if(forme!=null) {
				try{
					this.groupeFormes.getChildren().add(forme);
				} catch(Exception npe) {
					// FIXME can't figure out what make the exception thrown...
				}
			}
		});
	}

	/**
	 * Recherche les formes dessinees sur la fenetre  partir d'un identifiant
	 * @param id l'identifiant des formes recherchees
	 * @return les formes si l'identifiant est trouve, {@code null} sinon.
	 */
	List<Node> rechercheFormes(String id) {
		List<Node> list = this.groupeFormes.getChildrenUnmodifiable().filtered(f -> 
			f.getId().startsWith(id));
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	/**
	 * Recherche une forme dessinee sur la fenetre  partir de son identifiant
	 * @param id l'identifiant de la forme recherchee
	 * @return la forme si l'identifiant est trouve, {@code null} sinon.
	 */
	Node rechercheForme(String id) {
		List<Node> list = rechercheFormes(id);
		if (list==null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * Efface une forme dessinee sur la fenetre
	 * @param id l'identifiant de la forme  effacer
	 */
	void effaceFormes(String id) {
		Platform.runLater(() -> {
			Node forme = this.rechercheForme(id);
			while (forme != null) {
				try{
					this.groupeFormes.getChildren().remove(forme);
				} catch(Exception npe) {
					// FIXME can't figure out what make the exception thrown...
				}
				this.listeFormes.remove(forme);
				forme = this.rechercheForme(id);
			}
		});
	}

	/**
	 * Attend la selection d'une forme specifique par l'utilisateur (clic souris).
	 * Les formes pouvant etre selectionnees sont filtrees par un prefixe.
	 * Cette methode est "bloquante" tant que l'utilisateur n'a pas selectionne la forme
	 * desiree.
	 * @param prefixe le prefixe de l'identifiant de la forme.
	 * @return l'identifiant de la forme selectionnee tronquee du prefixe
	 */
	String selectionneForme(String prefixe) {
		this.formeSelectionnee = null;
		while ((this.formeSelectionnee == null) || (this.formeSelectionnee.getId() == null)
				|| !this.formeSelectionnee.getId().startsWith(prefixe)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				System.err.println("Thread management problems : "+ie.getMessage());
			}
		}
		return this.formeSelectionnee.getId().substring(prefixe.length());
	}

	/**
	 * Genere une couleur en Java ({@link java.awt.Color})  partir du nom de la
	 * couleur et de la correspondance fournie par {@link Donnees#COULEURS_IHM}
	 * @param couleur la couleur demandee
	 * @param opacite l'opacite de la couleur (variant de 0  1)
	 * @return la couleur Java correspondant  la couleur demandee ou "blanc" si
	 *         la couleur fournie est nulle.
	 */
	static Color creeCouleur(String couleur, double opacite) {
		if (couleur == null) {
			couleur = "blanc";
			opacite = 0;
		}
		if (Donnees.COULEURS_IHM.containsKey(couleur)) {
			couleur = Donnees.COULEURS_IHM.get(couleur);
		}
		return Color.web(couleur, opacite);
	}

	/**
	 * Modifie la couleur de fond de la scene qui correspond  la fenetre
	 * affichee.
	 * La couleur affichee a une opacite par defaut de 0.75.
	 * @param couleur la couleur de fond de la fenetre
	 */
	void changeCouleurDeFond(String couleur){
		this.getScene().setFill(creeCouleur(couleur, .75));
	}
}

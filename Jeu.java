import java.lang.ProcessBuilder.Redirect.Type;

class Jeu {

	int nbJr = 5;
	Joueur[] joueurs;
	int indiceJoueurCourant;
	boolean DernierTour;
	int joueursRestantsAJouer;

	PiocheCartesWagon pcw = new PiocheCartesWagon();
	CarteWagon[] PiocheVisible;
	CarteWagon[] DefausseCarteWagon;

	public int getJoueursRestantsAJouer() {
		return joueursRestantsAJouer;
	}

	Joueur[] getJoueurs() {
		return this.joueurs;
	}

	void creeJoueurs(int nbJoueurs) {
		this.joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs; i++) {
			joueurs[i] = new Joueur(i, Donnees.COULEURS_JOUEUR[i]);
		}
	}

	Joueur getJoueurCourant() {
		return joueurs[indiceJoueurCourant];
	}

	void changeJoueur() {
		if (indiceJoueurCourant != joueurs.length - 1) {
			indiceJoueurCourant++;
		} else {
			indiceJoueurCourant = 0;
		}
	}

	void demandeAction() {
		this.ihm.effaceBoutons();
		this.ihm.dessineBoutonSuivant(false);
		ActionUtilisateur reponse = this.ihm.attenteActionJoueur();
		switch (reponse.getType()) {
		case FINTOUR:
			if (DernierTour) {
				joueursRestantsAJouer = getJoueursRestantsAJouer() - 1;
			}
			this.changeJoueur();
			break;
		case ROUTE:
			selectionRoute(reponse.getParametre());
			break;
		case PIOCHECW:
			piocheCarteWagon(Integer.parseInt(reponse.getParametre()));
			break;
		default:
			break;
		}
	}

	void afficheJoueurCourant() {
		String score = String.valueOf(getJoueurCourant().getScore());
		String col = getJoueurCourant().getCouleur();
		String nbW = String.valueOf(getJoueurCourant().getNombreWagons());
		String msg = " Joueur " + getJoueurCourant().getNumero();
		this.ihm.afficheInformation(msg, 100, 500, false);
		this.ihm.afficheInformation(" Wagons : " + nbW, 100, 515, false);
		this.ihm.afficheInformation(" Score : " + score, 100, 530, false);
		this.ihm.afficheCouleurJoueurCourant(col);
		this.ihm.afficheCartesJoueur(getJoueurCourant());
	}

	Plateau plateau;
	IHM ihm;

	Plateau getPlateau() {
		return this.plateau;
	}

	Jeu() {
		DernierTour = false;
		plateau = new Plateau();
		ihm = new IHM();

	}

	void selectionRoute(String route) {
		Route temp = this.plateau.rechercheRoute(route);
		if (temp.getProprietaire() != null) {
			ihm.afficheMessageErreur("Route deja prise!");
		} else if (temp.getProprietaire() == null) {
			int l = temp.getLongueur();
			if (getJoueurCourant().getNombreWagons() >= l) {
				temp.setProprietaire(getJoueurCourant());
				this.ihm.effaceRoute(temp);
				this.ihm.dessineRoute(temp);
				this.ihm.dessineVille(temp.getVilleArrivee());
				this.ihm.dessineVille(temp.getVilleDepart());
				getJoueurCourant().enleveWagons(l);
				getJoueurCourant().ajouteAuScore(temp.getNombrePoints());
				detecteDernierTour();
				if (DernierTour) {
					joueursRestantsAJouer = getJoueursRestantsAJouer() - 1;
				}
				changeJoueur();
			} else {
				this.ihm.afficheMessageErreur("Pas assez de wagons!");
			}
		}
	}

	void detecteDernierTour() {
		if (getJoueurCourant().getNombreWagons() < 3 && DernierTour == false) {
			DernierTour = true;
			ihm.afficheDernierTour();
		}
	}

	CarteWagon piocheCarteWagon(int id) {
		//type: 1 for visible, -1 for hidden
		CarteWagon selected;

		if(id==-1){
			selected = getPiocheCartesWagon().depile();
			this.getJoueurCourant().prendsCarteWagon(selected);
		}
		else if (id>=0 && id<6){
			selected = getPiocheVisible()[id];
			PiocheVisible[id]=getPiocheCartesWagon().depile();
			this.getJoueurCourant().prendsCarteWagon(selected);
			affichePioche();
		}
		else{
			selected=null;
		}
		return selected;
	}


	void distribueCartesWagon(){
		for(int i=0;i<joueurs.length;i++){
			for(int j=0;j<4;j++){
				CarteWagon carte = getPiocheCartesWagon().depile(); //PiocheCartesWagon seul ne marche pas (pas initialisé)
				joueurs[i].prendsCarteWagon(carte);
			}
		}
	}



	void affichePlateau() {
		// FOURNI
		this.ihm.afficheFenetre("Les Aventuriers du Rail", 1366, 768, "lightGray");
		creeJoueurs(5);
		joueursRestantsAJouer = 5;

		for (Etat e : plateau.getEtats()) {
			this.ihm.dessineEtat(e);
		}
		for (Route r : plateau.getRoutes()) {
			this.ihm.dessineRoute(r);
		}
		for (Ville v : plateau.getVilles()) {
			this.ihm.dessineVille(v);
		}
		afficheJoueurCourant();
		
		affichePioche();
		do {
			tourDeJeu();
		} while (joueursRestantsAJouer > 0);

		ihm.afficheFin();
	}

	void tourDeJeu() {
		if (joueursRestantsAJouer != 0) {
			ihm.effaceInformation();
			afficheJoueurCourant();
			demandeAction();
		}

	}

	void affichePioche() {
		ihm.affichePioches(getPiocheCartesWagon(), getPiocheVisible());
	}

	/**
	 * @return the pcw
	 */
	public PiocheCartesWagon getPiocheCartesWagon() {
		return pcw;
	}

	/**
	 * @return the piocheVisible
	 */
	public CarteWagon[] getPiocheVisible() {
		return PiocheVisible;
	}

	/**
	 * @return the defausseCarteWagons
	 */
	public CarteWagon[] getDefausseCartesWagon() {
		return DefausseCarteWagon;
	}

	void initialisePioches() {
		pcw.ajouteCartes();
		PiocheVisible = pcw.extraitCartes(5);
		DefausseCarteWagon = new CarteWagon[110];
	}

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		jeu.initialisePioches();
		jeu.affichePlateau();
	}
}
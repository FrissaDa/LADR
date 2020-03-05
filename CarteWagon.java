class CarteWagon {

	String couleur;

	String getCouleur(){
		return this.couleur;
	}

	void setCouleur(String couleur){
		this.couleur = couleur;
	}

	boolean estLocomotive(){
		if (couleur==null) return true;
		else
			return false;
	}

	boolean estDeCouleur(String couleur){
		if (couleur == this.couleur) return true;
		else
			return false;
	}

	String versChaine(){
		String type;
		if (estLocomotive())
			type="Locomotive";
		else
			type="Wagon";

		return type+" "+getCouleur();
	}


	CarteWagon(String couleur){
		this.couleur = couleur;
	}
	
	
}

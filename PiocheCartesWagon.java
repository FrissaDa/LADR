import java.util.Random;
class PiocheCartesWagon{
	int indiceSommet=-1;
	CarteWagon cartes[];

	PiocheCartesWagon(){
		this.cartes = new CarteWagon[110];
	}

	boolean estVide() {
		if(indiceSommet==-1){
			return true;
		}
		return false;
	}

	boolean estPleine() {
		if(indiceSommet==109){
			return true;
		}
		return false;
	}

	CarteWagon getSommet() {
		// à compléter/modifier
		if(!estVide()){
			return cartes[indiceSommet];
		}
		else {
			throw new Error("Pile vide");
		}
	}

	void empile(CarteWagon wagon) {
		// à compléter
		if(estPleine()){
			throw new Error("Pile pleine");
		}
		indiceSommet++;
		cartes[indiceSommet]=wagon;
	}

	String versChaine(){
		if(estVide()){
			return "";		
		}
		else {
			String out="";
			for (int i=0;i<=indiceSommet;i++){
				out += ((i+1)+": "+cartes[i].versChaine()+"\n");
			}
			return out;
		}
	}

	CarteWagon depile() {
		if(estVide()){
			throw new Error("Pile vide");
		}
		CarteWagon elementSommet = cartes[indiceSommet];
		System.arraycopy(cartes, 0, cartes, 0, indiceSommet);
		indiceSommet--;
		return elementSommet;
	}

	public void ajouteCartes(){
		for (int i=0;i<8;i++){
			for(int j=0;j<12;j++){
				CarteWagon new_carte = new CarteWagon(Donnees.COULEURS_WAGON[i]);
				empile(new_carte);
			}
		}
		for (int i=0;i<14;i++){
			CarteWagon new_carte = new CarteWagon(null);
				empile(new_carte);
		}
		melange();
	}



	private void melange(){
		Random r = new Random();
		CarteWagon temp;
		CarteWagon temp2;
		for (int j=0;j<r.nextInt(1000)+1;j++){
			for (int i=0;i<r.nextInt(1000)+1;i++){
				int k = r.nextInt(cartes.length);
				temp = cartes[k];
				int l = r.nextInt(cartes.length);
				temp2=cartes[l];
				cartes[l]=temp;
				cartes[k]=temp2;
			}
		}
	}
	

	CarteWagon[] extraitCartes(int i){
		
		int k = Math.min(i, indiceSommet+1);
		CarteWagon temp [] = new CarteWagon[k];
		for (int j=0;j<i;j++){
			if(!estVide()){
				temp[j]=depile();
			}
		}
		return temp;	
	}
}
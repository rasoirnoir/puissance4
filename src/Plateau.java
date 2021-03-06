import java.util.HashMap;

/**
 * Gère le plateau du jeu Puissance 4.
 * Ce plateau est représenté par un tableau à 2 dimensions contenant des 0 pour le cases vides,
 * des 1 pour les pions du joueur 1, et des 2 pour les pions du joueur 2
 * Cette classe est résponsable de l'ajout des pions sur le plateau, ce qui déclenche automatiquement 
 * la vérification des conditions de victoire (4 pions identiques alignés)
 * @author William Noris
 *
 */
public class Plateau {
	
	private int[][] plateau;
	
	/**
	 * initialise un plateau de jeu vide
	 */
	public Plateau() {
		plateau = new int[6][7];
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[0].length; j++) {
				plateau[i][j] = 0;
			}
		}
	}
	
	/**
	 * Le tableau à 2 dimensions qui représente le plateau de jeu
	 * Ce plateau est en lecture seule, donc pas de setter
	 * @return Le tableau à 2 dimensions représentant le plateau de jeu
	 */
	public int[][] getPlateau() {
		return this.plateau;
	}

	
	/**
	 * Ajoute un pion au plateau lorsqu'un joueur le joue et vérifie si il gagne
	 * @param joueur Le joueur qui joue (1 ou 2)
	 * @param colonne La colonne dans laquelle le joueur joue le pion (Le joueur choisira entre 1 et 7, donc il faut remettre entre 0 et 6 pour le système
	 * @return -1 si le pion n'a pas été ajouté, 0 si l epion a été ajouté mais le joueur ne gagne pas, le numéro du joueur si le pion a été ajouté et que le joueur gagne
	 */
	public int ajouterPion(int joueur, int colonne) {
		colonne--;
		//On récupère l'indice de la dernière ligne vide
		int derniereLigneVide = this.derniereLigneVide(colonne);
		if (derniereLigneVide == -1) return -1; //Cette condition est remplie si la colonne est pleine
		this.plateau[derniereLigneVide][colonne] = joueur;
		boolean victoire = puissance4(derniereLigneVide, colonne); //Après avoir ajouté le pion, on vérifie si les conditions de victoire sont remplies
		if(victoire) return joueur;
		return 0;
	}
	
	/**
	 * Determine si une colonne est jouable ou non
	 * @param colonne La colonne à tester
	 * @return true si la colonne est jouable, false si la colone est pleine
	 */
	public boolean colonneJouable(int colonne) {
		if(derniereLigneVide(colonne) == -1) return false;
		return true;
	}
	
	/**
	 * Renvoie l'indice de la dernière ligne vide d'une colonne
	 * @param colonne La colonne dans laquelle chercher
	 * @return L'indice de la dernière ligne vide, -1 si la colonne est pleine
	 */
	private int derniereLigneVide(int colonne) {
		for(int i = 0; i < this.plateau.length; i++) {
			if(this.plateau[i][colonne] != 0) return i-1;
		}
		return this.plateau.length - 1;
	}
	
	/**
	 * Determin esi le plateau est rempli ou non
	 * @return true si le plateau est plein, false sinon
	 */
	public boolean plein() {
		for(int i = 0; i < this.plateau[0].length; i++) {
			//On parcours toutes les colonnes du tableau et vérifie si elles sont pleine
			if(derniereLigneVide(i) != -1) return false;
		}
		return true;
	}
	
	/**
	 * Determine si un puissance 4 existe sur une ligne a partir d'un pion
	 * @param coordonneesLigne La ligne du pion
	 * @param coordonneesColonne La colonne du pion
	 * @return true si il y a puissance 4, false sinon
	 */
	private boolean puissanceLigne(int coordonneesLigne, int coordonneesColonne) {
		int compteur = 1; //Le pion de depart. Si il arrive à 4, c'est gagné
		int joueur = this.plateau[coordonneesLigne][coordonneesColonne];
		for(int i = coordonneesLigne - 1; i >= 0; i--) { //On test à gauche
			if (this.plateau[i][coordonneesColonne] == joueur) compteur++;
			else break;
		}
		for(int i = coordonneesLigne + 1; i < this.plateau.length; i++) { //On test à droite
			if (this.plateau[i][coordonneesColonne] == joueur) compteur++;
			else break;
		}
		if(compteur >= 4) return true;
		return false;
	}
	
	/**
	 * Determine si un puissance 4 existe sur une colonne à partir d'un pion
	 * @param coordonneesLigne La ligne du pion
	 * @param coordonneesColonne La colonne du pion
	 * @return true si il y a puissance 4, false sinon
	 */
	private boolean puissanceColonne(int coordonneesLigne, int coordonneesColonne) {
		int compteur = 1; //Le pion de depart. Si il arrive à 4, c'est gagné
		int joueur = this.plateau[coordonneesLigne][coordonneesColonne];
		for(int i = coordonneesColonne - 1; i >= 0; i--) { //On test vers le haut
			if (this.plateau[coordonneesLigne][i] == joueur) compteur++;
			else break;
		}
		for(int i = coordonneesColonne + 1; i < this.plateau[coordonneesLigne].length; i++) { //On test vers le bas
			if (this.plateau[coordonneesLigne][i] == joueur) compteur++;
			else break;
		}
		if(compteur >= 4) return true;
		return false;
	}
	
	/**
	 * Determine si une puissance 4 existe dans la première diagonale à partir d'un pion
	 * @param coordonneesLigne La ligne du pion
	 * @param coordonneesColonne La colonne du pion
	 * @return true si puissance 4, false sinon
	 */
	private boolean puissanceDiagonale1(int coordonneesLigne, int coordonneesColonne){
		int compteur = 1; //on compte le pion de depart. Si compteur arrive à 4, c'est gagné
		int joueur = this.plateau[coordonneesLigne][coordonneesColonne];
		int i = coordonneesLigne - 1;
		int j = coordonneesColonne - 1;
		//On cherche les pions adjacents en haut à gauche
		while(i >= 0 && j >= 0) {
			if (this.plateau[i][j] == joueur) compteur++;
			else break;
			i--;
			j--;
		}
		i = coordonneesLigne + 1;
		j = coordonneesColonne + 1;
		//On cherche les pions adjacents en bas à droite
		while(i < this.plateau.length && j < this.plateau[coordonneesLigne].length) {
			if (this.plateau[i][j] == joueur) compteur++;
			else break;
			i++;
			j++;
		}
		if(compteur >= 4) return true;
		return false;
	}
	
	/**
	 * Determine si une puissance 4 existe dans la deuxième diagonale à partir d'un pion
	 * @param coordonneesLigne La ligne du pion
	 * @param coordonneesColonne La colonne du pion
	 * @return true si puissance 4, false sinon
	 */
	private boolean puissanceDiagonale2(int coordonneesLigne, int coordonneesColonne){
		int compteur = 1; //Le pion de depart. Si il arrive à 4, c'est gagné
		int joueur = this.plateau[coordonneesLigne][coordonneesColonne];
		int i = coordonneesLigne - 1;
		int j = coordonneesColonne + 1;
		//On cherche les pions adjacents en haut à droite
		while(i >= 0 && j  < this.plateau[coordonneesLigne].length) {
			if (this.plateau[i][j] == joueur) compteur++;
			else break;
			i--;
			j++;
		}
		i = coordonneesLigne + 1;
		j = coordonneesColonne - 1;
		//On cherche les pions adjacents en bas à gauche
		while(i < this.plateau.length && j >= 0) {
			if (this.plateau[i][j] == joueur) compteur++;
			else break;
			i++;
			j--;
		}
		if(compteur >= 4) return true;
		return false;
	}
	
	/**
	 * Determine si il y a puissance 4 !!
	 * @param coordonneesLigne Ligne du dernier pion joué
	 * @param coordonneesColonne Colonne du dernier pion joué
	 * @return
	 */
	public boolean puissance4(int coordonneesLigne, int coordonneesColonne) {
		if(puissanceLigne(coordonneesLigne, coordonneesColonne) || puissanceColonne(coordonneesLigne, coordonneesColonne) || puissanceDiagonale1(coordonneesLigne, coordonneesColonne) || puissanceDiagonale2(coordonneesLigne, coordonneesColonne)) return true;
		return false;
	}
	
	
	/**
	 * Affiche un beau plateau dans la console
	 */
	public void afficherPlateau() {
		String output = "\n";
		char blanc = '\u25CB'; //joueur 1
		char noir = '\u25CF'; //joueur 2
		for(int i = 0; i < this.plateau.length; i++){
			for(int j = 0; j < this.plateau[0].length; j++) {
				String charAffiche = " ";
				int intAffiche = this.plateau[i][j];
				if(intAffiche == 1) charAffiche = "" + blanc;
				if(intAffiche == 2) charAffiche = "" + noir;
				output += "|" + charAffiche;
			}
			output += "|";
			if(i == this.plateau.length - 3) output += "\t" + GameData.getJoueurs().get(1) + " : " + blanc;
			if(i == this.plateau.length - 2) output += "\t" + GameData.getJoueurs().get(2) + " : " + noir;
			output += "\n";
		}
		output += " 1 2 3 4 5 6 7";
		System.out.println(output);
	}
	
	/**
	 * Affiche le plateau de jeu
	 */
	public String toString() {
		String output = "";
		for(int i = 0; i < this.plateau.length; i++){
			for(int j = 0; j < this.plateau[0].length; j++) {
				output += " " + this.plateau[i][j];
			}
			output += "\n";
		}
		return output;
	}
}

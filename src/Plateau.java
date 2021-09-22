
public class Plateau {
	
	private int[][] plateau;
	
	public Plateau() {
		plateau = new int[6][7];
		for(int i = 0; i < plateau.length; i++){
			for(int j = 0; j < plateau[0].length; j++) {
				plateau[i][j] = 0;
			}
		}
	}
	
	/**
	 * Ajoute un pion au plateau lorsqu'un joueur le joue
	 * @param joueur Le joueur qui joue (1 ou 2)
	 * @param colonne La colonne dans laquelle le joueur joue le pion (Le joueur choisira entre 1 et 7, donc il faut remettre entre 0 et 6 pour le système
	 */
	public boolean ajouterPion(int joueur, int colonne) {
		colonne--;
		int derniereLigneVide = this.derniereLigneVide(colonne);
		if (derniereLigneVide == -1) return false;
		this.plateau[derniereLigneVide][colonne] = joueur;
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

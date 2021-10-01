import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static GameData data;

	public static void main(String[] args) {
		GameMenus.menuPrincipal();
		GameData.initData();
		GameMenus.nomJoueurs();
		boucleJeu();
	}
	

	
	/**
	 * La boucle principale du jeu
	 */
	public static void boucleJeu() {
		while(true) {
			//La seule façon de sortir de cette boucle est un choix volontaire de la part des joueurs d'arreter de jouer
			int colonneChoisie = -1;
			
			colonneChoisie = GameMenus.choisirColonne();
			
			int ajoutPion = GameData.getPlateau().ajouterPion(GameData.getJoueurCourant(), colonneChoisie);
			GameData.getPlateau().afficherPlateau();
			if(ajoutPion == GameData.getJoueurCourant()) { //Le joueur a gagné !!!
				System.out.println("Bravo !! " + GameData.getJoueurs().get(GameData.getJoueurCourant()) + " a gagné !!");
				if(GameMenus.rejouer()) {
					GameData.initData();
				}else {
					GameMenus.finDuJeu();
				}
			}
			else { //Le joueur n'a pas encore gagné, le jeu contine
				GameData.setJoueurCourant((GameData.getJoueurCourant() == 1) ? 2 : 1); //Joueur suivant
			}
			if(GameData.getPlateau().plein()) {
				System.out.println();
				System.out.println("Les deux joueurs sont à égalité.");
				if(GameMenus.rejouer()) {
					GameData.initData();
				}else {
					GameMenus.finDuJeu();
				}
			}
		}	
	}
}

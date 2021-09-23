import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {		
		Plateau plateau = new Plateau();
		System.out.println(plateau);
		
		int joueur = 1; //joueur 1 ou joueur 2
		while(!plateau.plein()) {
			
			int colonneChoisie = -1;
			boolean choixOK = false;
			
			do {
				System.out.println("Au tour de Joueur 1 : (choisissez une colonne entre 1 et 7)\nEntrez 0 pour quitter le jeux.");
				Scanner scanner = new Scanner(System.in);
				try {
					colonneChoisie = scanner.nextInt();
				}
				catch(InputMismatchException e) { //Excception déclenchée si le joueur entre autre chose qu'un nombre
					System.out.println("Veuillez entrer un nombre.");
				}
				if(0 <= colonneChoisie && colonneChoisie <= 7) choixOK = true;
			} while(choixOK == false);
			if(colonneChoisie == 0) finDuJeu();
			plateau.ajouterPion(joueur, colonneChoisie);
			System.out.println(plateau);
			joueur = (joueur == 1) ? 2 : 1; //Joueur suivant
		}
		finDuJeu();
	}
	
	/**
	 * Envoi un message de remerciement avant de quitter le programme
	 */
	public static void finDuJeu() {
		System.out.println("Merci d'avoir joué à Puissance 4 !\nFermeture du jeu...");
		System.exit(0);
	}
}

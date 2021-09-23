import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {		
		Plateau plateau = new Plateau();
		System.out.println(plateau);
		
		int joueur = 1; //joueur 1 ou joueur 2
		boolean rejoue = true;
		while(!plateau.plein() && rejoue) {
			
			int colonneChoisie = -1;
			boolean choixOK = false;
			
			do {
				colonneChoisie = choisirColonne(joueur);
				if(0 <= colonneChoisie && colonneChoisie <= 7) {
					choixOK = true;
					if(colonneChoisie == 0) finDuJeu();
					int ajoutPion = plateau.ajouterPion(joueur, colonneChoisie);
					if(ajoutPion == -1) {
						choixOK = false;
						System.out.println("La colonne sélectionnée est pleine.");
					}
					else {
						if(ajoutPion == joueur) { //Le joueur a gagné !!!
							System.out.println("Bravo !! Le joueur " + joueur + " a gagné !!");
							if(!rejouer()) rejoue = false;
						}
					}
				}
				else {
					System.out.println("Le nombre choisi doit être compris entre 1 et 7.");
				}
			} while(choixOK == false);
			
			System.out.println(plateau);
			joueur = (joueur == 1) ? 2 : 1; //Joueur suivant
		}
		finDuJeu();
	}
	
	/**
	 * Envoi un message de remerciement avant de quitter le programme
	 */
	public static void finDuJeu() {
		System.out.println("Merci d'avoir joué à Puissance 4 !\nA bientôt.");
		System.exit(0);
	}
	
	/**
	 * Demande aux joueurs s'ils veulent rejouer
	 * @return
	 */
	public static boolean rejouer() {
		System.out.println("Voulez-vous rejouer ?");
		System.out.println("1 : oui");
		System.out.println("2 : non");
		Scanner scanner = new Scanner(System.in);
		try {
			
		}catch(InputMismatchException e) { //Excception déclenchée si le joueur entre autre chose qu'un nombre
			System.out.println("Veuillez entrer un nombre.");
		}
		finally {
			scanner.close();
		}
		return true;
	}
	
	public static int choisirColonne(int joueur) {
		int colonne = -1;
		System.out.println("Au tour de Joueur " + joueur + " : (choisissez une colonne entre 1 et 7)\nEntrez 0 pour quitter le jeux.");
		Scanner scanner = new Scanner(System.in);
		try {
			colonne = scanner.nextInt();
		}
		catch(InputMismatchException e) { //Excception déclenchée si le joueur entre autre chose qu'un nombre
			System.out.println("Veuillez entrer un nombre.");
		}
		finally {
			scanner.close();
		}		
		return colonne;
	}
}

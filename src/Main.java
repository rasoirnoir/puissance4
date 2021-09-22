import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {		
		Plateau plateau = new Plateau();
		System.out.println(plateau);
		
		boolean enJeu = true;
		
		while(enJeu) {
			
			System.out.println("Au tour de Joueur 1 : (choisissez une colonne entre 1 et 7)");
			Scanner scanner = new Scanner(System.in);
			int colonneChoisie;
			try {
				colonneChoisie = scanner.nextInt();
			}
			finally {
				scanner.close();
			}
			plateau.ajouterPion(1, colonneChoisie);
			System.out.println(plateau);
			
			enJeu = false;
		}		
	}

}

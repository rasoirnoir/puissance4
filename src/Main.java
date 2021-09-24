import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	static Plateau plateau;
	static int joueur;
	static HashMap<Integer, String> joueurs; //Mapping du nom des joueurs. On pourra récupérer le nom des joueurs grâce à joueurs.get(joueur)
	
	public static void main(String[] args) {
		joueurs = new HashMap<Integer, String>();
		joueurs.put(1, "joueur 1");
		joueurs.put(2, "joueur 2");
		int rep = -1;
		do {
			rep = menuPrincipal();
			if(rep == 2) finDuJeu();
		} while(rep != 1 && rep != 2);
		nomJoueurs();
		initGame();
		while(!plateau.plein()) {
			
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
						plateau.afficherPlateau(joueurs);
					}
					else {
						if(ajoutPion == joueur) { //Le joueur a gagné !!!
							System.out.println("Bravo !! Le joueur " + joueurs.get(joueur) + " a gagné !!");
							plateau.afficherPlateau(joueurs);
							if(rejouer()) {
								initGame();
							}else {
								finDuJeu();
							}
						}
						else { //Le joueur n'a pas encore gagné, le jeu contine
							plateau.afficherPlateau(joueurs);
							joueur = (joueur == 1) ? 2 : 1; //Joueur suivant
						}
					}
				}
				else {
					System.out.println("Le nombre choisi doit être compris entre 1 et 7.");
					plateau.afficherPlateau(joueurs);
				}
			} while(choixOK == false);
		}
		System.out.println("Les deux joueurs sont à égalité.");
		finDuJeu();
	}
	
	/**
	 * Envoi un message de remerciement avant de quitter le programme
	 */
	public static void finDuJeu() {
		System.out.println("A bientôt sur Puissance 4 !");
		System.exit(0);
	}
	
	/**
	 * Demande aux joueurs s'ils veulent rejouer
	 * @return
	 */
	public static boolean rejouer() {
		int choix = 0;
		do {
			System.out.println("Voulez-vous rejouer ?");
			System.out.println("1 : oui");
			System.out.println("2 : non");
			
			Scanner scanner = new Scanner(System.in);
			try {
				choix = scanner.nextInt();
			}catch(InputMismatchException e) { //Excception déclenchée si le joueur entre autre chose qu'un nombre
				System.out.println("Veuillez entrer un nombre. (1 ou 2)");
			}
	//		finally {
	//			scanner.close();
	//		}
			if(choix == 2) return false;
		} while(!(choix == 1 || choix == 2)); //Tant que le joueur n'a pas entré 1 ou 2, on lui repose la question :)
		return true;
	}
	
	/**
	 * Demande au joueur de choisir une colonne
	 * @param joueur le joueur qui doit choisir
	 * @return Le numéro de la colonne
	 */
	public static int choisirColonne(int joueur) {
		int colonne = -1;
		System.out.println("Au tour de " + joueurs.get(joueur));
		System.out.println("Choisissez une colonne (1 à 7)");
		System.out.println("(0 pour quitter)");
		Scanner scanner = new Scanner(System.in);
		try {
			colonne = scanner.nextInt();
		}
		catch(InputMismatchException e) { //Excception déclenchée si le joueur entre autre chose qu'un nombre
			System.out.println("Veuillez entrer un nombre.");
		}
//		finally {
//			scanner.close();
//		}		
		return colonne;
	}
	
	/**
	 * initialise une nouvelle session de jeu
	 */
	public static void initGame() {
		plateau = new Plateau();		
		joueur = 1; //joueur 1 ou joueur 2
		plateau.afficherPlateau(joueurs);
	}
	
	/**
	 * Affiche le menu principal du jeu
	 * @return la réponse des joueurs
	 */
	public static int menuPrincipal() {
		System.out.println(" ____  _   _ ___ ____ ____    _    _   _  ____ _____   _  _   \r\n"
				+ "|  _ \\| | | |_ _/ ___/ ___|  / \\  | \\ | |/ ___| ____| | || |  \r\n"
				+ "| |_) | | | || |\\___ \\___ \\ / _ \\ |  \\| | |   |  _|   | || |_ \r\n"
				+ "|  __/| |_| || | ___) |__) / ___ \\| |\\  | |___| |___  |__   _|\r\n"
				+ "|_|    \\___/|___|____/____/_/   \\_\\_| \\_|\\____|_____|    |_|  \r\n"
				+ "                                                              ");
		System.out.println("1) JOUER");
		System.out.println("2) QUITTER");
		int reponse = -1;
		Scanner scanner = new Scanner(System.in);
		try {
			reponse = scanner.nextInt();
		}
		catch(InputMismatchException e) { //Exception déclenchée si le joueur entre autre chose qu'un nombre
			System.out.println("Veuillez entrer un nombre. (1 ou 2)");
		}
		return reponse;
	}
	
	/**
	 * Demande aux joueurs leur nom et l'enregistre dans le jeu
	 */
	public static void nomJoueurs() {
		System.out.println("Entrez le nom du joueur 1 (joueur 1):");
		String reponse = "joueur 1";
		Scanner scanner = new Scanner(System.in);
		try {
			reponse = scanner.nextLine();
		}finally {}
		reponse = reponse.equals("") ? "joueur 1" : reponse;
		joueurs.replace(1, reponse);
		
		System.out.println("Entrez le nom du jouer 2 (joueur 2):");
		reponse = "joueur 2";
		try {
			reponse = scanner.nextLine();
		}finally {}
		reponse = reponse.equals("") ? "joueur 2" : reponse;
		joueurs.replace(2, reponse);
	}
}

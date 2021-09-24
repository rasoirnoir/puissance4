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
		
		menuPrincipal();
		nomJoueurs();
		initGame();
		boucleJeu();
	}
	
	/**
	 * Envoi un message de remerciement avant de quitter le programme
	 */
	public static void finDuJeu() {
		System.out.println();
		System.out.println("*******************************");
		System.out.println("* A bientôt sur Puissance 4 ! *");
		System.out.println("*******************************");
		System.exit(0);
	}
	
	/**
	 * Demande aux joueurs s'ils veulent rejouer
	 * @return true si le joueur entre un choix valide
	 */
	public static boolean rejouer() {
		int choix = 0;
		do {
			System.out.println();
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
	 * @return Le numéro de la colonne que le joueur a choisi
	 */
	public static int choisirColonne() {
		int colonne = -1;
		boolean choixOK = false;
		do {
			System.out.println();
			System.out.println("Au tour de " + joueurs.get(joueur));
			System.out.println("Choisissez une colonne (1 à 7)");
			System.out.println("(0 pour quitter)");
			Scanner scanner = new Scanner(System.in);
			try {
				colonne = scanner.nextInt();
			}
			catch(InputMismatchException e) { //Exception déclenchée si le joueur entre autre chose qu'un nombre
				System.out.println("Veuillez entrer un nombre.");
			}
	//		finally {
	//			scanner.close();
	//		}
		
			if(colonne == 0) finDuJeu();
			if(0 < colonne && colonne <= 7) {
				if(plateau.colonneJouable(colonne - 1)) choixOK = true;
				else {
					System.out.println("La colonne sélectionnée est pleine.");
					plateau.afficherPlateau(joueurs);
				}
			}
			else {
				System.out.println("Le nombre choisi doit être compris entre 1 et 7.");
				plateau.afficherPlateau(joueurs);
			}
		} while(choixOK == false);
		
		
		return colonne;
	}
	
	/**
	 * initialise une nouvelle session de jeu
	 */
	public static void initGame() {
		plateau = new Plateau();	
		joueur = (int) Math.round(Math.random() + 1); //On determie aléatoirement quel joueur commence.
		plateau.afficherPlateau(joueurs);
	}
	
	/**
	 * Affiche le menu principal du jeu
	 * @return la réponse des joueurs
	 */
	public static void menuPrincipal() {
		int reponse = -1;
		System.out.println();
		do {
			System.out.println(" ____  _   _ ___ ____ ____    _    _   _  ____ _____   _  _   \r\n"
					+ "|  _ \\| | | |_ _/ ___/ ___|  / \\  | \\ | |/ ___| ____| | || |  \r\n"
					+ "| |_) | | | || |\\___ \\___ \\ / _ \\ |  \\| | |   |  _|   | || |_ \r\n"
					+ "|  __/| |_| || | ___) |__) / ___ \\| |\\  | |___| |___  |__   _|\r\n"
					+ "|_|    \\___/|___|____/____/_/   \\_\\_| \\_|\\____|_____|    |_|  \r\n"
					+ "                                                              ");
			System.out.println("1) JOUER");
			System.out.println("2) QUITTER");
			
			Scanner scanner = new Scanner(System.in);
			try {
				reponse = scanner.nextInt();
			}
			catch(InputMismatchException e) { //Exception déclenchée si le joueur entre autre chose qu'un nombre
				System.out.println("Veuillez entrer un nombre. (1 ou 2)");
			}
			if(reponse == 2) finDuJeu();
		} while(reponse != 1 && reponse != 2);
	}
	
	/**
	 * Demande aux joueurs leur nom et l'enregistre dans le jeu
	 */
	public static void nomJoueurs() {
		System.out.println();
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
	
	/**
	 * La boucle principale du jeu
	 */
	public static void boucleJeu() {
		while(true) {
			
			int colonneChoisie = -1;
			
			colonneChoisie = choisirColonne();
			
			int ajoutPion = plateau.ajouterPion(joueur, colonneChoisie);
			
			if(ajoutPion == joueur) { //Le joueur a gagné !!!
				plateau.afficherPlateau(joueurs);
				System.out.println("Bravo !! " + joueurs.get(joueur) + " a gagné !!");
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
			if(plateau.plein()) {
				System.out.println();
				System.out.println("Les deux joueurs sont à égalité.");
				if(rejouer()) {
					initGame();
				}else {
					finDuJeu();
				}
			}
		}	
	}
}

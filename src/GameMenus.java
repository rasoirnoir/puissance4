import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMenus {
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
            }catch(InputMismatchException e) { //Exception déclenchée si le joueur entre autre chose qu'un nombre
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
     * @return Le numéro de la colonne que le joueur a choisi
     */
    public static int choisirColonne() {
        int colonne = -1;
        boolean choixOK = false;
        do {
            GameData.getPlateau().afficherPlateau();
            System.out.println();
            System.out.println("Au tour de " + GameData.getJoueurs().get(GameData.getJoueurCourant()));
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
                //On vérifie que la colonne ne soit pas pleine
                if(GameData.getPlateau().colonneJouable(colonne - 1)) choixOK = true;
                else {
                    System.out.println("La colonne sélectionnée est pleine.");
                }
            }
            else {
                System.out.println("Le nombre choisi doit être compris entre 1 et 7.");
            }
        } while(choixOK == false); //On boucle tant que le joueur n'a pas donné une réponse correcte


        return colonne;
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
        } while(reponse != 1 && reponse != 2); //On boucle tant que le joueur n'a pas donné de réponse correcte
    }

    /**
     * Demande aux joueurs leur nom et l'enregistre dans le jeu
     */
    public static void nomJoueurs() {
        System.out.println();
        System.out.println("Entrez le nom du joueur 1 (Joueur 1):");
        String reponse;
        Scanner scanner = new Scanner(System.in);
        try {
            reponse = scanner.nextLine();
        }finally {}
        //Si le joueur appuie sur Entrée sans tapper de nom, on lui donne le nom joueur 1 par défaut
        reponse = reponse.equals("") ? "Joueur 1" : reponse;
        GameData.getJoueurs().put(1, reponse);

        System.out.println("Entrez le nom du joueur 2 (Joueur 2):");
        try {
            reponse = scanner.nextLine();
        }finally {}
        reponse = reponse.equals("") ? "Joueur 2" : reponse;
        GameData.getJoueurs().put(2, reponse);
    }
}

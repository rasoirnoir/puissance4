import java.util.HashMap;

public class GameData {
    static private Plateau plateau;
    static private int joueurCourant;
    static private HashMap<Integer, String> joueurs = new HashMap<Integer, String>(); //Mapping du nom des joueurs. On pourra récupérer le nom des joueurs grâce à joueurs.get(joueur)

/*
    public GameData(){
        joueurs = new HashMap<Integer, String>();
        joueurs.put(1, "joueur 1");
        joueurs.put(2, "joueur 2");
        initData();
    }*/

    public static void initData(){
        plateau = new Plateau();
        joueurCourant = (int) Math.round(Math.random() + 1); //On determine aléatoirement quel joueur commence.
    }

    public static Plateau getPlateau(){
        return plateau;
    }

    public static int getJoueurCourant(){
        return joueurCourant;
    }
    public static void setJoueurCourant(int numJoueur){
        joueurCourant = numJoueur;
    }

    public static HashMap<Integer, String> getJoueurs() {
        return joueurs;
    }

}

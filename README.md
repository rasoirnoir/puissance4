# PUISSANCE 4 (En ligne de commande)

- [PUISSANCE 4 (En ligne de commande)](#puissance-4-en-ligne-de-commande)
  - [Présentation du jeu](#présentation-du-jeu)

## Présentation du jeu

Le but du jeu est d'aligner une suite de 4 pions de même couleur sur une grille comptant 6 rangées et 7 colonnes. Chaque joueur dispose de 21 pions d'une couleur (par convention, en général jaune ou rouge). Tour à tour, les deux joueurs placent un pion dans la colonne de leur choix, le pion coulisse alors jusqu'à la position la plus basse possible dans la dite colonne à la suite de quoi c'est à l'adversaire de jouer. Le vainqueur est le joueur qui réalise le premier un alignement (horizontal, vertical ou diagonal) consécutif d'au moins quatre pions de sa couleur. Si, alors que toutes les cases de la grille de jeu sont remplies, aucun des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle. 
>source : https://fr.wikipedia.org/wiki/Puissance_4


## Présentation technique

La fonction main est divisée en plusieurs parties:
* Initialisation du HashMap contenant le nom des joueurs
* Affichage du menu principal (menu d'accueil)
* Demande du nom des joueurs
* Initialisation du plateau de jeu vide
* Démarrage de la boucle de jeu

La gestion du plateau de jeu est entièrement délégée à la classe Plateau.
Cette classe contient un tableau à 2 dimensions contenant différentes valeurs en fonction des pions qui sont joués :
- 0 si la case est vide
- 1 si la case est occupée par un pion du joueur 1
- 2 si la case est occupée par un pion du joueur 2

A sa création, le constructeur initialise un tableau vide :

  ```java
  public Plateau() {
    plateau = new int[6][7];
    for(int i = 0; i < plateau.length; i++){
      for(int j = 0; j < plateau[0].length; j++) {
        plateau[i][j] = 0;
      }
    }
  }
  ```

Cette classe est aussi résponsable de diverse actions telle que :

1. Afficher le plateau au joueurs à l'aide de la méthode :

    ```java
    public void afficherPlateau(HashMap<Integer, String> nomJoueurs)
    ```
2. Vérifier si le plateau de jeu est plein :
    ```java
    public boolean plein()
    ```
3. Permettre à un joueur de jouer un pion
  1
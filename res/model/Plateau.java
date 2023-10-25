package res.model;

import java.util.*;

/**
     * Cette classe représente le plateau de jeu pour un jeu de Risk.
     */


public class Plateau {
    private ArrayList<Continent> continents;
    private ArrayList<Joueur> joueurs;
    private List<CarteRisk> cartesPille;


    /**
     * Initialise la partie en distribuant les territoires aux joueurs.
     */

    public void initialiserParties() {

        distribuerCartes(this.joueurs);
        initialisationRegiment();
    }

    /**
     * Initialiser les régiments de chaque joueurs dans ses territoires
     *
     * @Author HP
     */

    public void initialisationRegiment() {
        // 5 joueurs -> 25 pions infanterie
        if (joueurs.size() == 5){
            placerPionDansTerritoire(25);

        } else if (joueurs.size() == 4){ // 4 joueurs -> 30 pions infanterie

        } else if (joueurs.size() == 3){ // 3 joueurs -> 35 pions infanterie

        }
    }


     /**
     * Distribue les territoires aux joueurs de manière équilibrée.
     *
     * @param js La liste des joueurs à qui distribuer les territoires.
     */

    // pluetôt distribuerTerritoires
    public void distribuerCartes(ArrayList<Joueur> js) {

        // Fonctionne:
        // Créez d'abord une liste de territoires et,
        // pour chaque joueur de la liste des joueurs, prenez le territoire correspondant dans la liste
        // et retirez ce territoire de la liste jusqu'à ce que celle-ci soit vide.
        // Ensuite, le joueur place un Pion dans sa liste de territoires,
        // pour chaque territoire, et s'il reste des Pions, le joueur choisit où les placer.

        // 1. Créez une liste de territoires
        ArrayList<Territoire> listeTtTerritoire = new ArrayList<Territoire>();
        for (Continent c : continents) {
            listeTtTerritoire.addAll(c.getTerritories());
        }

        // 2. Pour chaque joueur, get un territoire de la liste et remove ce territoire de cette liste
        while (!listeTtTerritoire.isEmpty()) {
            for (Joueur j : joueurs) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(listeTtTerritoire.size());
                j.ajouterTerritoire(listeTtTerritoire.get(randomIndex)); // <- il faut l'ajouter dans Joueur !!!
                listeTtTerritoire.remove(randomIndex);
            }
        }

    }

    /**
     * Récupère le propriétaire du territoire donné en paramètre
     *
     * @param territoire
     * @return
     */
    public Joueur getProprietaire(Territoire territoire) {
        if (Objects.nonNull(territoire)) {
            for (Joueur j : joueurs) {
                if (j.isPossessed(territoire)) {
                    return j;
                }
            }
        }

        return null;
    }


    /**
     * Obtient la liste des continents sur le plateau.
     *
     * @return La liste des continents sur le plateau.
     */

    public ArrayList<Continent> getContinents() {
        return continents;
    }

     /**
     * Obtient la carte du jeu associée à chaque quantité de cartes piochées.
     *
     * @return Une carte associée à chaque quantité de cartes piochées.
     */

    public List<CarteRisk> getCartesPille() {
        return cartesPille;
    }


     /**
     * Obtient la liste des joueurs participants à la partie.
     *
     * @return La liste des joueurs participants à la partie.
     */

    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
    }

    /**
     * Enlève un joueur de la partie.
     *
     * @param j Le joueur à enlever de la partie.
     */
    
    public void enleverJoueur(Joueur j) {
        this.joueurs.remove(j);
    }

    public void ajouterCarte(List<CarteRisk> listeEchange) {
        if (Objects.nonNull(listeEchange)) {
            cartesPille.addAll(listeEchange);
        }
    }
}

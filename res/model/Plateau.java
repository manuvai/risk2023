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
        if (joueurs.size() == 3){ // 35 pions -> infanterie
            placementAuto(35);
        } else if (joueurs.size() == 4){ // 30 pions -> infanterie
            placementAuto(30);
        } else if (joueurs.size() == 5) { // 25 pions -> infanterie
            placementAuto(25);
        }
    }

    public void placementAuto(int nbRegiment){
        for (Joueur j : joueurs){
            for (Territoire t : j.obtenirTerritoires()){
                Pion p = new Pion("pionDebut",TypePion.INFANTERIE);
                t.ajouterRegiment(p,1);
                nbRegiment --;
            }
        }

        // placement random
        Random rand = new Random();
        for (Joueur j : joueurs){
            List<Territoire> listeTerritoire = j.obtenirTerritoires();
            for (int i = 0; i < nbRegiment; i++){
                int randomIndex = rand.nextInt(listeTerritoire.size());
                Pion p = new Pion("pionPlaceAuto",TypePion.INFANTERIE);
                listeTerritoire.get(randomIndex).ajouterRegiment(p,1);
            }
        }


    }

    public void initialisationCarte(ArrayList<CarteRisk> cartes){
        this.cartesPille = cartes;
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

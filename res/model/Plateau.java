package res.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Cette classe représente le plateau de jeu pour un jeu de Risk.
 */


public class Plateau {
    private ArrayList<Continent> continents = new ArrayList<Continent>();
    private ArrayList<Joueur> joueurs;
    private ArrayList<CarteRisk> cartesPille;

    /**
     * Initialise la partie en distribuant les territoires aux joueurs.
     */

    public void initialiserParties() {
        creerContinents();
        creerTerritories();
        distribuerCartes(joueurs);
        creerCartePile();
    }

    private ArrayList<Continent> creerContinents(){
        Continent afrique = new Continent("Afrique",3);
        Continent amérique_du_sud = new Continent("Amérique du sud",2);
        Continent amérique_du_Nord = new Continent("Amérique du Nord",5);
        Continent asie = new Continent("Asie",7);
        Continent europe = new Continent("Europe",5);
        Continent océante = new Continent("Afrique",2);

        continents.add(afrique);
        continents.add(amérique_du_sud);
        continents.add(amérique_du_Nord);
        continents.add(asie);
        continents.add(europe);
        continents.add(océante);

        return continents;

    }

    public void creerTerritories(){

    }


    public void creerCartePile(){

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
            listeTtTerritoire.addAll(c.getTerritoires());
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
     * Obtient les cartes piochées pendant la partie.
     *
     * @return Les cartes piochées pendant la partie.
     */

    public List<CarteRisk> getCartes() {
        return this.cartesPille;
    }

    /**
     * Enlève un joueur de la partie.
     *
     * @param j Le joueur à enlever de la partie.
     */

    public void enleverJoueur(Joueur j) {
        this.joueurs.remove(j);
    }
}

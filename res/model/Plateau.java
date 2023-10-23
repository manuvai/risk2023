package res.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Plateau {
    private ArrayList<Continent> continents;
    private ArrayList<Joueur> joueurs;
    private Map<CarteRisk, Integer> cartesPille;



    // pluetôt distribuerTerritoires
    public void distribuerCartes(ArrayList<Joueur> js){
        /*
        * Règle du jeux:
        * Le Général prend la pile des cartes « Risk » et en retire les jokers.
        * Puis il mélange les cartes et les distribue toutes, une à une dans le sens des aiguilles d’une montre,
        * en commençant par le joueur situé à sa gauche (si vous jouez à 4 ou à 5, deux joueurs recevront une carte supplémentaire).
        * Ces cartes indiquent quels sont les territoires que chaque joueur doit occuper en début de partie, sans tenir compte, pour l’instant, des différents types de régiment.
        * Les joueurs placent un régiment sur chacun des territoires qu’ils ont reçus.
        * Puis, lorsque tous les territoires sont occupés, chaque joueur, à commencer par celui qui est à gauche du Général,
        * pose un deuxième régiment sur l’un de ses territoires au choix
        * (il n’y a pas de limite au nombre de régiments qui peuvent occuper le même territoire, mais chaque territoire doit avoir au moins un régiment)…
        * Les joueurs suivants procèdent de la même façon et ainsi de suite jusqu’à ce que l’ensemble des régiments de toutes les armées soit sur le plateau.
        *
        * */

        // Fonctionne:
        // Créez d'abord une liste de territoires et,
        // pour chaque joueur de la liste des joueurs, prenez le territoire correspondant dans la liste
        // et retirez ce territoire de la liste jusqu'à ce que celle-ci soit vide.
        // Ensuite, le joueur place un Pion dans sa liste de territoires,
        // pour chaque territoire, et s'il reste des Pions, le joueur choisit où les placer.

        // 1. Créez une liste de territoires
        ArrayList<Territoire> listeTtContinent = new ArrayList<Territoire>();
        for (Continent c : continents){
            listeTtContinent.addAll(c.getTerritories());
        }

        // 2. Pour chaque joueur, get un territoire de la liste et remove ce territoire de cette liste
        while (!listeTtContinent.isEmpty()){
            for (Joueur j : joueurs){
                Random rand = new Random();
                int randomIndex = rand.nextInt(listeTtContinent.size());
                j.setContinent(listeTtContinent.get(randomIndex)); // <- il faut l'ajouter dans Joueur !!!
                listeTtContinent.remove(randomIndex);
            }
        }

        // 3. Placer les pions ???


    }

    public void initialiserParties() {

        distribuerCartes(this.joueurs);
        // TODO: 23/10/2023
    }

    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
    }

    public Map<CarteRisk, Integer> getCartes() {
        return this.cartesPille;
    }

    public void enleverJoueur(Joueur j) {
        this.joueurs.remove(j);
    }
}

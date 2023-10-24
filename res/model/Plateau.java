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

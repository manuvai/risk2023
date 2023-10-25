package res.model;

import java.util.ArrayList;

    /**
     * Cette classe représente un continent dans un jeu.
     */
public class Continent {
    private String nomCtint;
    private int bonusRenforts;
    private ArrayList<Territoire> territoires;
  
    /**
     * Constructeur de la classe Continent avec nom et bonus de renforts.
     *
     * @param nomCtint      Le nom du continent.
     * @param bonusRenforts Le bonus de renforts attribué pour contrôler le continent.
     */

    public Continent(String nomCtint, int bonusRenforts) {
        this.nomCtint = nomCtint;
        this.bonusRenforts = bonusRenforts;
    }

     /**
     * Constructeur de la classe Continent avec nom, bonus de renforts et liste de territoires.
     *
     * @param nomCtint      Le nom du continent.
     * @param bonusRenforts Le bonus de renforts attribué pour contrôler le continent.
     * @param territoires   La liste des territoires appartenant au continent.
     */

    public Continent(String nomCtint, int bonusRenforts, ArrayList<Territoire> territoires) {
        this.nomCtint = nomCtint;
        this.bonusRenforts = bonusRenforts;
        this.territoires = new ArrayList<Territoire>();

    }

    /**
     * Obtient la liste des territoires appartenant au continent.
     *
     * @return La liste des territoires du continent.
     */

    public ArrayList<Territoire> getTerritoires() {
        return this.territoires;
    }

    /**
     * Obtient le bonus de renforts attribué pour contrôler le continent.
     *
     * @return Le bonus de renforts du continent.
     */
    
    public int getBonusTerritories() {
        return this.bonusRenforts;
    }

}


package res.model;

import java.util.ArrayList;

public class Continent {
    private String nomCtint;
    private int bonusRenforts;
    private ArrayList<Territoire> territoires;
  
    public Continent(String nomCtint, int bonusRenforts) {
        this.nomCtint = nomCtint;
        this.bonusRenforts = bonusRenforts;
    }

    public Continent(String nomCtint, int bonusRenforts, ArrayList<Territoire> territoires) {
        this.nomCtint = nomCtint;
        this.bonusRenforts = bonusRenforts;
        this.territoires = new ArrayList<Territoire>();

    }

    public ArrayList<Territoire> getTerritories() {
        return this.territoires;
    }

    public int getBonusTerritories() {
        return this.bonusRenforts;
    }

}


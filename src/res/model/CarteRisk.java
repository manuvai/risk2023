package res.model;

/**
 * Représente une carte dans le jeu Risk, associant un type de pion à un territoire.
 */

public class CarteRisk {
    private TypePion typePion;  
    private Territoire territoire;  


    /**
     * Crée une instance de carte Risk avec un type de pion et un territoire spécifiés.
     *
     * @param typePion   Le type de pion associé à la carte.
     * @param territoire Le territoire associé à la carte.
     */

    public CarteRisk(TypePion typePion, Territoire territoire) {
        this.typePion = typePion;
        this.territoire = territoire;
    }


    /**
     * Récupère le type de pion associé à la carte.
     *
     * @return Le type de pion de la carte.
     */

    public TypePion getTypePion() {
        return typePion;
    }

    /**
     * Récupère le territoire associé à la carte.
     *
     * @return Le territoire de la carte.
     */
    
    public Territoire getTerritoire() {
        return territoire;
    }
}

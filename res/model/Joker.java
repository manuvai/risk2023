package res.model;

/**
 * Représente une carte Joker dans le jeu Risk, qui est une sous-classe de {@link CarteRisk}.
 */

public class Joker extends CarteRisk {

    /**
     * Crée une instance de carte Joker avec un type de pion et un territoire spécifiés.
     *
     * @param typePion   Le type de pion associé à la carte Joker.
     * @param territoire Le territoire associé à la carte Joker.
     */
    
    public Joker(TypePion typePion, Territoire territoire) {

        super(typePion, territoire);

    }
}

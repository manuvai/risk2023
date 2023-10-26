package res.model;


import res.model.exceptions.InvalidQuantityDeploymentException;


    /**
     * Cette classe représente un déploiement d'unités sur un territoire.
     *
     *  @author Fatima MOUSLIM
     *  @since 21/10/23
     *
     */
public class Déploiement {
    private int qtéDéployée;
    private Pion pionRattachee;


    /**
     * Constructeur de la classe Déploiement.
     *
     * @param pionRattachee Le pion auquel le déploiement est rattaché.
     */

    public Déploiement(Pion pionRattachee) {
        this.qtéDéployée = 0; // Initialement, aucune quantité déployée
        this.pionRattachee = pionRattachee;
    }

    /**
     * Obtient la quantité d'unités déployées.
     *
     * @return La quantité d'unités déployées.
     */ 

    public int getQtéDéployée() {
        return qtéDéployée;
    }

    /**
     * Obtient le pion auquel le déploiement est rattaché.
     *
     * @return Le pion auquel le déploiement est rattaché.
     */

    public Pion getPionRattachee() {
        return pionRattachee;
    }


    /**
     * Ajoute une quantité donnée d'unités au déploiement.
     *
     * @param quantite La quantité à ajouter.
     */ 

    public void ajouterQuantites(int quantite) {
        if (quantite <= 0) {
            throw new InvalidQuantityDeploymentException();
        }
        qtéDéployée += quantite;
    }

     /**
     * Retire une quantité donnée d'unités du déploiement.
     *
     * @param quantite La quantité à retirer.
     */ 
    
    public void retirerQuantites(int quantite) {
        if (quantite <= 0 || qtéDéployée < quantite) {
            throw new InvalidQuantityDeploymentException();
        }
        qtéDéployée -= quantite;
    }
}

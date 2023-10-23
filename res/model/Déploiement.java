package res.model;


import res.model.exceptions.InvalidQuantityDeploymentException;

public class Déploiement {
    private int qtéDéployée;
    private Pion pionRattachee;

    public Déploiement(Pion pionRattachee) {
        this.qtéDéployée = 0; // Initialement, aucune quantité déployée
        this.pionRattachee = pionRattachee;
    }

    public int getQtéDéployée() {
        return qtéDéployée;
    }

    public Pion getPionRattachee() {
        return pionRattachee;
    }

    public void ajouterQuantites(int quantite) {
        if (quantite <= 0) {
            throw new InvalidQuantityDeploymentException();
        }
        qtéDéployée += quantite;
    }

    public void retirerQuantites(int quantite) {
        if (quantite <= 0 || qtéDéployée < quantite) {
            throw new InvalidQuantityDeploymentException();
        }
        qtéDéployée -= quantite;
    }
}

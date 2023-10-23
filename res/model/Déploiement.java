package res.model;


public class Deploiement {
    private int qtéDéployée;
    private Pion pionRattachee;

    public Deploiement(Pion pionRattachee) {
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
        if (quantite > 0) {
            qtéDéployée += quantite;
        } else {
            System.out.println("La quantité à ajouter doit être supérieure à zéro.");
        }
    }

    public void retirerQuantites(int quantite) {
        if (quantite > 0 && qtéDéployée >= quantite) {
            qtéDéployée -= quantite;
        } else {
            System.out.println("La quantité à retirer n'est pas valide.");
        }
    }
}

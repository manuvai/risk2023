package res.model;

import res.model.exceptions.InvalidQuantityDeploymentException;
import res.model.exceptions.UnpossessedTroupRemovalException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Territoire {
    private String nom;
   private List<Déploiement> unitésDéployées = new ArrayList<>(); // Nombre d'unités déployées sur le territoire
    private List<Territoire> voisins = new ArrayList<>();
    private Joueur proprio; // Joueur propriétaire du territoire

    public Territoire(String nom) {
        this.nom = nom;
    }

    // Getters et setters pour les attributs
    public String getNom() {
        return nom;
    }


    public int getNombreUnites() {
        int nbUnites = 0;

        for (Déploiement déploiement : unitésDéployées) {
            nbUnites += déploiement.getQtéDéployée();
        }

        return nbUnites;
    }

    public List<Territoire> getVoisins() {
        return voisins;
    }

    public void setVoisins(List<Territoire> voisins) {
        this.voisins = voisins;
    }

    public boolean estVoisin(Territoire autreTerritoire) {
        return voisins.contains(autreTerritoire);
    }
    
    public Joueur getProprietaire() {
        return proprio;
    }

    /**
     * Ajoute des régiments déployés sur le territoire en question
     *
     * @param pionsToAdd
     * @param qtyToAdd
     */
    public void ajouterRegiment(Pion pionsToAdd, int qtyToAdd) {
        List<Pion> listeUnites = new ArrayList<>();

        if (qtyToAdd <= 0) {
            throw new InvalidQuantityDeploymentException();
        }

        Déploiement déploiement = unitésDéployées.parallelStream()
                .filter(déploiementFilter -> déploiementFilter.getPionRattachee().equals(pionsToAdd))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(déploiement)) {
            déploiement = new Déploiement(pionsToAdd);
        }
        déploiement.ajouterQuantites(qtyToAdd);
    }

    /**
     * Retire des régiments déployés sur le territoire en question
     *
     * @param pionsToRemove
     * @param qtyToRemove
     */
    public void retirerRegiment(Pion pionsToRemove, int qtyToRemove) {

        if (qtyToRemove <= 0) {
            throw new InvalidQuantityDeploymentException();
        }

        Déploiement déploiement = unitésDéployées.parallelStream()
                .filter(déploiementFilter -> déploiementFilter.getPionRattachee().equals(pionsToRemove))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(déploiement)) {
            throw new UnpossessedTroupRemovalException();
        }

        déploiement.retirerQuantites(qtyToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Territoire that)) return false;
        return Objects.equals(getNom(), that.getNom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom());
    }
}

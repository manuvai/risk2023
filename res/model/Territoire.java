package res.model;

import res.model.exceptions.InvalidQuantityDeploymentException;
import res.model.exceptions.UnpossessedTroupRemovalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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


    /**
     * Retourne le nombre d'unités dans ce territoire
     *
     * @return
     */
    public int getNombreUnites() {
        return getNombreUnites(unitésDéployées);
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

        if (!unitésDéployées.contains(déploiement)) {
            unitésDéployées.add(déploiement);
        }
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

    /**
     * Détermine les régiments à retirer pour obtenir le nombre de régiments fournis en paramètre
     *
     * @param nbRegiments
     * @return
     */
    public List<Déploiement> retirerRegiment(int nbRegiments) {
        if (nbRegiments > getNombreUnites()) {
            throw new InvalidQuantityDeploymentException();
        }

        List<Déploiement> regimentsEnleves = new ArrayList<>();

        List<Déploiement> sortedDeploiements = unitésDéployées.stream()
                .sorted((o1, o2) -> TypePion.compare(
                        o1.getPionRattachee().getTypePion(),
                        o2.getPionRattachee().getTypePion()))
                .collect(Collectors.toList());

        int i = 0;
        while (i < sortedDeploiements.size() && getNombreUnites(regimentsEnleves) != nbRegiments) {
            Déploiement actualDeploiement = unitésDéployées.get(i);

            // TODO Ajouter la lofique pour retirer le nécessaire

            i++;
        }

        return regimentsEnleves;
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

    /**
     * Détermine le nombre de régiments disponibles à partir d'une liste de déploiement
     *
     * @param déploiements
     * @return
     */
    private int getNombreUnites(List<Déploiement> déploiements) {
        int nbUnites = 0;

        for (Déploiement déploiement : déploiements) {
            nbUnites += déploiement.getQtéDéployée() * déploiement.getPionRattachee().obtenirNbRegiment();
        }

        return nbUnites;
    }
}

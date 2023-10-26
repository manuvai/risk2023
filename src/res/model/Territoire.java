package res.model;

import res.model.exceptions.InvalidQuantityDeploymentException;
import res.model.exceptions.UnpossessedTroupRemovalException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

    /**
     * Cette classe représente un territoire dans un jeu.
     *
     * @author Fatima MOUSLIM
     * @since 21/10/23
     */

public class Territoire {
    private String nom;
    private List<Déploiement> unitésDéployées = new ArrayList<>(); // Nombre d'unités déployées sur le territoire
    private List<Territoire> voisins = new ArrayList<>();

    /**
     * Constructeur de la classe Territoire.
     *
     * @param nom Le nom du territoire.
     */
    public Territoire(String nom) {
        this.nom = nom;
    }

    // Getters et setters pour les attributs
    public String getNom() {
        return nom;
    }


    /**
     * Retourne le nombre d'unités dans ce territoire.
     *
     * @return Le nombre d'unités dans le territoire.
     */
    public int getNombreUnites() {
        return getNombreUnites(unitésDéployées);
    }


    /**
     * Obtient la liste des territoires voisins.
     *
     * @return La liste des territoires voisins.
     */

    public List<Territoire> getVoisins() {
        return voisins;
    }

    /**
     * Définit la liste des territoires voisins.
     *
     * @param voisins La liste des territoires voisins à définir.
     */

    public void setVoisins(List<Territoire> voisins) {
        this.voisins = voisins;
    }

    /**
     * Vérifie si un territoire est voisin de celui-ci.
     *
     * @param autreTerritoire Le territoire à vérifier.
     * @return Vrai si le territoire est voisin, sinon faux.
     */

    public boolean estVoisin(Territoire autreTerritoire) {
        return voisins.contains(autreTerritoire);
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
     * @return  La liste des déploiements retirés.
     */
    public List<Déploiement> retirerRegiment(int nbRegiments) {
        if (nbRegiments > getNombreUnites()) {
            throw new InvalidQuantityDeploymentException();
        }

        List<Déploiement> regimentsEnleves = new ArrayList<>();

        List<Déploiement> regimentsToRemove = new ArrayList<>();

        List<Déploiement> sortedDeploiements = unitésDéployées.stream()
                .sorted((o1, o2) -> TypePion.compare(
                        o1.getPionRattachee().getTypePion(),
                        o2.getPionRattachee().getTypePion()))
                .collect(Collectors.toList());

        int i = 0;
        while (i < sortedDeploiements.size() && getNombreUnites(regimentsEnleves) != nbRegiments) {
            Déploiement actualDeploiement = sortedDeploiements.get(i);

            int nbRegimentPionRattache = actualDeploiement.getPionRattachee()
                    .obtenirNbRegiment();

            int qtyActualDeploiement = actualDeploiement.getQtéDéployée() * nbRegimentPionRattache;

            int qtyToAddLeft = nbRegiments - getNombreUnites(regimentsEnleves);

            if (qtyToAddLeft >= qtyActualDeploiement) {
                regimentsEnleves.add(actualDeploiement);
                regimentsToRemove.add(actualDeploiement);

            } else {
                int qtyPossible = (qtyToAddLeft - nbRegimentPionRattache) / nbRegimentPionRattache;

                if (qtyPossible > 0) {
                    actualDeploiement.retirerQuantites(qtyPossible);

                    Déploiement newDeploiement = new Déploiement(actualDeploiement.getPionRattachee());
                    newDeploiement.ajouterQuantites(qtyPossible);

                    regimentsEnleves.add(newDeploiement);
                }
            }

            i++;
        }

        for (Déploiement toDeleteDeploiement : regimentsToRemove) {
            sortedDeploiements.remove(toDeleteDeploiement);
        }

        unitésDéployées = sortedDeploiements;

        return regimentsEnleves;
    }


    /**
     * Redéfinition de la méthode equals.
     *
     * @param o L'objet à comparer.
     * @return Vrai si les objets sont égaux, sinon faux.
     */ 
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Territoire)) return false;
        Territoire that = (Territoire) o;
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

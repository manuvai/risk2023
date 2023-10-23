package res.model;

import java.util.List;

public class Territoire {
    private String nom;
    private List<Déploiement> unitésDéployées = new ArrayList<>(); // Nombre d'unités déployées sur le territoire
    private List<Territoire> voisins = new ArrayList<>();
    private Joueur proprio; // Joueur propriétaire du territoire
    

    public Territoire(String nom) {
        this.nom = nom;
        this.unitésDéployées = 0; // Initialement aucune unité déployée
    }

    // Getters et setters pour les attributs
    public String getNom() {
        return nom;
    }


    public int getNombreUnites() {
        return unitésDéployées;
    }

    public void setNombreUnites(int unitésDéployées) {
        this.unitésDéployées = unitésDéployées;
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
    




public void retirerRegiment(Pion pionsToRemove, int qtyToRemove) {
    if (qtyToRemove > 0) {
        int removedCount = 0;
        Iterator<Pion> iterator = listeUnites.iterator();
        while (iterator.hasNext() && removedCount < qtyToRemove) {
            Pion pion = iterator.next();
            if (pion.equals(pionsToRemove)) {
                iterator.remove();
                removedCount++;
            }
        }

        if (removedCount > 0) {
            System.out.println(removedCount + " pions retirés du régiment avec succès.");
        } else {
            System.out.println("Aucun pion correspondant à retirer trouvé.");
        }
    } else {
        System.out.println("La quantité à retirer doit être supérieure à zéro.");
    }
}


    
}

package res.model;

import java.util.List;

public class Territoire {
    private String nom;
<<<<<<< HEAD
   private List<Déploiement> unitésDéployées = new ArrayList<>(); // Nombre d'unités déployées sur le territoire
=======
   // private int unitésDéployées; // Nombre d'unités déployées sur le territoire
>>>>>>> 6cf05247cc03dc88f73a3c88523806cabdb05fa8
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
    



    public void ajouterRegiment(Pion pionsToAdd, int qtyToAdd) {
         List<Pion> listeUnites;
            if (qtyToAdd > 0) {
                for (int i = 0; i < qtyToAdd; i++) {
                    listeUnites.add(pionsToAdd);
                }
                System.out.println(qtyToAdd + " pions ajoutés au régiment avec succès.");
            } else {
                System.out.println("La quantité à ajouter doit être supérieure à zéro.");
            }
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


     public void afficherInformations() {
        System.out.println("Nom du territoire : " + nom);
        System.out.println("Propriétaire : " + (proprio != null ? proprio.getNom() : "Aucun"));
        System.out.println("Nombre d'unités : " + unitésDéployées);
        System.out.print("Territoires voisins : ");
        for (Territoire voisin : voisins) {
            System.out.print(voisin.getNom() + " ");
        }
        System.out.println();
    }

    /*
    public void ajouterRegiment(List<Pion> pions, int nombreUnites) {
        if (nombreUnites <= 0) {
            // Vérifiez que le nombre d'unités à ajouter est valide.
            System.out.println("Le nombre d'unités à ajouter doit être supérieur à zéro.");
            return;
        }
        
        if (pions.size() >= nombreUnites) {
            // S'il y a suffisamment de pions, ajoutez-les au territoire.
            for (int i = 0; i < nombreUnites; i++) {
                Pion pion = pions.remove(0);
                // Ici, vous pouvez effectuer des opérations pour ajouter le pion au territoire,
                // par exemple, en ajoutant le pion à une liste d'unités sur le territoire.
            }
            System.out.println("Régiment ajouté avec succès !");
        } else {
            // Gérez le cas où il n'y a pas suffisamment de pions.
            System.out.println("Pas assez de pions pour former un régiment.");
        }
    }


    
    public void retirerRegiment(List<Pion> pions, int nombreUnites) {
        if (nombreUnites <= 0) {
            // Vérifiez que le nombre d'unités à retirer est valide.
            System.out.println("Le nombre d'unités à retirer doit être supérieur à zéro.");
            return;
        }
        
        if (nombreUnites <= nombreUnites) {
            // Vérifiez que le nombre d'unités à retirer est inférieur ou égal au nombre d'unités sur le territoire.
            if (nombreUnites > this.nombreUnites) {
                System.out.println("Le nombre d'unités à retirer est supérieur au nombre d'unités sur le territoire.");
                return;
            }

            for (int i = 0; i < nombreUnites; i++) {
                String listeUnites;
				// Obtenez le pion du territoire, par exemple, en supprimant le premier pion de la liste d'unités sur le territoire.
                if (!listeUnites.isEmpty()) {
                    Pion pion = listeUnites.remove(0);
                    // Effectuez des opérations pour retirer le pion du territoire, par exemple, en le stockant dans une liste de pions disponibles.
                    pions.add(pion);
                } else {
                    // Gérez le cas où il n'y a plus de pions à retirer
                    System.out.println("Il n'y a plus de pions à retirer.");
                    break;
                }
            }
            System.out.println("Régiment retiré avec succès !");
        } else {
            // Gérez le cas où le nombre d'unités à retirer n'est pas valide
            System.out.println("Le nombre d'unités à retirer n'est pas valide.");
        }
    }

*/

    
}

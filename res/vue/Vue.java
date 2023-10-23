package res.vue;

public class Vue {


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



}

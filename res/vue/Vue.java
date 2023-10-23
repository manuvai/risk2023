package res.vue;

public class Vue {

  public void afficherInformations() {
        System.out.println("Nom du joueur : " + nom);
        System.out.println("Prénom du joueur : " + prenom);
        System.out.println("Armée : " + armee.getNom()); 
        System.out.println("Cartes du joueur :");

        for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
            Carte carte = entry.getKey();
            int quantite = entry.getValue();
            System.out.println(" - " + quantite + "x " + carte.getNom()); 
        }
    }

 }

}

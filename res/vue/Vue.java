package res.vue;


import res.model.Armee;
import res.model.CarteRisk;
import res.model.Joueur;

public class Vue {

  
public void afficherInformations(Joueur joueur) {
        System.out.println("Nom du joueur : " + joueur.getNom());
        System.out.println("Prénom du joueur : " + joueur.getPrenom());
        System.out.println("Armée : " + joueur.getArmee());
        System.out.println("Cartes du joueur :");

    }

    public void afficherInformations(CarteRisk carte) {
        System.out.println("Type de pion : " + carte.getTypePion());
        System.out.println("Territoire : " + carte.getTerritoire());
        
    }

    public void afficherInformations(Armee armee) {
//        System.out.println("Couleur de l'armée : " + armee.getCouleur());
//        System.out.println("Pions : " + armee.getPions());
       
    }




}


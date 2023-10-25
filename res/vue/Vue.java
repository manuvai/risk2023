//package res.vue;
//
//
//import res.model.Armee;
//import res.model.CarteRisk;
//import res.model.Joueur;
//
//import java.util.Map;
//
//public class Vue {
//
//
//public void afficherInformations(Joueur joueur) {
//        System.out.println("Nom du joueur : " + joueur.getNom());
//        System.out.println("Prénom du joueur : " + joueur.getPrenom());
//        System.out.println("Armée : " + joueur.getArmee().getNom());
//        System.out.println("Cartes du joueur :");
//
//        for (Map.Entry<CarteRisk, Integer> entry : joueur.getCartes().entrySet()) {
//            Carte carte = entry.getKey();
//            int quantité = entry.getValue();
//            System.out.println(" - " + quantité + "x " + carte.getNom());
//        }
//    }
//
//    public void afficherInformations(CarteRisk carte) {
//        System.out.println("Type de pion : " + carte.getTypePion());
//        System.out.println("Territoire : " + carte.getTerritoire());
//
//    }
//
//    public void afficherInformations(Armee armee) {
//        System.out.println("Couleur de l'armée : " + armee.getCouleur());
//        System.out.println("Pions : " + armee.getPions());
//
//    }
//
//
//
//
//}
//

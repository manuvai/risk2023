//package tests.model;
//
//import org.junit.Test;
////import org.junit.jupiter.api.Assertions;
//import res.model.Déploiement;
//import res.model.Pion;
//import res.model.TypePion;
//import res.model.exceptions.InvalidQuantityDeploymentException;
//
//public class DéploiementTest {
//
//    @Test(expected = InvalidQuantityDeploymentException.class)
//    public void ajouterQuantites_Negative_KO() {
//        Déploiement déploiement = new Déploiement(null);
//
//        déploiement.ajouterQuantites(-1);
//    }
//
//    @Test
//    public void ajouterQuantites_OK() {
//        Déploiement déploiement = new Déploiement(new Pion("pionA", TypePion.INFANTERIE));
//
//        Assertions.assertDoesNotThrow(() -> {
//            déploiement.ajouterQuantites(10);
//
//        });
//
//    }
//}

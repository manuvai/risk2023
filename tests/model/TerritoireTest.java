//package tests.model;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import res.model.Déploiement;
//import res.model.Pion;
//import res.model.Territoire;
//import res.model.TypePion;
//import res.model.exceptions.InvalidQuantityDeploymentException;
//import res.model.exceptions.UnpossessedTroupRemovalException;
//
//import java.util.List;
//
//public class TerritoireTest {
//
//    @Test(expected = InvalidQuantityDeploymentException.class)
//    public void ajouterRegiment_Negative_KO() {
//        Territoire territoire = new Territoire("France");
//        territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), -10);
//    }
//
//    @Test
//    public void ajouterRegiment_OK() {
//        Territoire territoire = new Territoire("France");
//        Assertions.assertDoesNotThrow(() -> territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), 10));
//
//    }
//    @Test(expected = InvalidQuantityDeploymentException.class)
//    public void retirerRegiment_Negative_KO() {
//        Territoire territoire = new Territoire("France");
//        territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), 10);
//        territoire.retirerRegiment(new Pion("PionA", TypePion.INFANTERIE), -10);
//    }
//    @Test(expected = UnpossessedTroupRemovalException.class)
//    public void retirerRegiment_Unpossessed_KO() {
//        Territoire territoire = new Territoire("France");
//        territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), 1);
//        territoire.retirerRegiment(new Pion("PionB", TypePion.INFANTERIE), 1);
//    }
//
//    @Test
//    public void retirerRegiment_OK() {
//        Territoire territoire = new Territoire("France");
//        Assertions.assertDoesNotThrow(() -> {
//            territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), 10);
//            territoire.retirerRegiment(new Pion("PionA", TypePion.INFANTERIE), 10);
//        });
//
//    }
//    @Test
//    public void retirerRegiment_int_OK() {
//        Territoire territoire = new Territoire("France");
//        Assertions.assertDoesNotThrow(() -> {
//            territoire.ajouterRegiment(new Pion("PionA", TypePion.INFANTERIE), 10);
//            territoire.ajouterRegiment(new Pion("PionB", TypePion.CAVALERIE), 10);
//            List<Déploiement> deploiements = territoire.retirerRegiment(25);
//        });
//
//    }
//}

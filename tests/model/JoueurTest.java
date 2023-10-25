//package tests.model;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import res.model.Joueur;
//import res.model.exceptions.CanNotLaunchNegativeAmountOfDiceException;
//
//public class JoueurTest {
//
//    @Test(expected = CanNotLaunchNegativeAmountOfDiceException.class)
//    public void lancerDes_nbDesNegative_KO() {
//        Joueur joueur = new Joueur();
//
//        joueur.lancerDes(-10);
//    }
//
//    @Test
//    public void lancerDes_OK() {
//        Joueur joueur = new Joueur();
//
//        Assertions.assertDoesNotThrow(() -> joueur.lancerDes(3));
//
//    }
//
//}

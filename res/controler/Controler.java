package res.controler;


import res.model.Déploiement;
import res.model.Pion;
import res.model.Territoire;
import res.model.TypePion;

import java.util.Scanner;

public class Controler {

    // Phase Fortification
    public void phaseFortification(){
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Est-ce que vous voulez fortifier(1/2) ? (Reponse : 1 - Oui, 2 - Non");
                int resJ = sc.nextInt();

                if (resJ != 1 && resJ != 2){
                    throw new Exception("Saissiez numero 1 ou 2 SVP !");
                } else if (resJ == 1){
                    System.out.println("Veuillez sélectionner un territoireSource :");
                    String territoireSource = sc.nextLine();
                    // Verify if territoireSource owns by player
                    /*
                    * if (this.controlerContinent(territoireSource)){
                    *   continue;
                    * } else {
                    *   retourne au debut
                    *
                    */

                    System.out.println("Veuillez sélectionner un territoireCible :");
                    String territoireCible = sc.nextLine();
                    // Verify if territoireCible owns by player

                    System.out.println("Veuillez sélectionner le nombre de régiment :");
                    // Afficher le nombre de rigément du territoire territoireSource

                    int nbRegiment = sc.nextInt();
                    // Verify if player have enough nb regiment
                    // and at least one rigement stay at territoireSource

                    // start to move ->
                    deplacerRegiment(territoireSource,territoireCible,nbRegiment);
                    break;

                } else {
                    break;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void deplacerRegiment(
            Territoire territoireSource,
            Territoire territoireCible,
            int nombreRegiment) {

        // Calculer nb de chaque pion
        int nb_ARTILLERIE = nombreRegiment / 10;
        int nb_INFANTERIE = (nombreRegiment - nombreRegiment / 10 * 10) / 5;
        int nb_CAVALERIE = nombreRegiment - (nombreRegiment / 10 * 10) - (nombreRegiment / 5 * 5);
        // Ajouter dans le territoireCible
        // Pion Cavalerie
        Pion pC = new Pion("pionCavalerie", TypePion.CAVALERIE);

        Déploiement dC = new Déploiement(pC);
        dC.ajouterQuantites(nb_CAVALERIE);

        territoireCible.ajouterRegiment(pC,nb_CAVALERIE);

        // Pion Infanterie
        Pion pI = new Pion("pionInfanterie", TypePion.INFANTERIE);

        Déploiement dI = new Déploiement(pI);
        dI.ajouterQuantites(nb_INFANTERIE);

        territoireCible.ajouterRegiment(pI,nb_INFANTERIE);

        // Pion Artillerie
        Pion pA = new Pion("pionArtillerie", TypePion.ARTILLERIE);

        Déploiement dA = new Déploiement(pA);
        dA.ajouterQuantites(nb_INFANTERIE);

        territoireCible.ajouterRegiment(pA,nb_ARTILLERIE);

        // Enlever le nb de Regiment de territoireSource
        territoireSource.retirerRegiment(pC,nb_CAVALERIE);
        territoireSource.retirerRegiment(pI,nb_INFANTERIE);
        territoireSource.retirerRegiment(pA,nb_ARTILLERIE);
    }
}

package res.controler;

import org.w3c.dom.ls.LSOutput;
import res.model.Territoire;

import java.sql.SQLOutput;
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
    }
}

package res.controler;

import java.util.Scanner;


public class Controler {

    private Scanner scanner;

    public Controller() {
        this.scanner = new Scanner(System.in);

    }

    public void phaseAttaque(Joueur joueur) {
        System.out.println("Phase d'attaque pour le joueur : " + joueur.getNom());

        while (true) {
            // Demander au joueur de choisir le territoire source
            System.out.print("Saisissez le nom du territoire source (ou tapez 'fin' pour terminer l'attaque) : ");
            String territoireSource = scanner.nextLine();

            if (territoireSource.equalsIgnoreCase("fin")) {
                // Si le joueur entre "fin", terminez la phase d'attaque.
                break;
            }

            // Demander au joueur de choisir le territoire cible
            System.out.print("Saisissez le nom du territoire cible : ");
            String territoireCible = scanner.nextLine();

            // Demander au joueur de choisir le nombre de régiments pour l'attaque
            System.out.print("Saisissez le nombre de régiments à utiliser pour l'attaque : ");
            int nombreRegiments = scanner.nextInt();
            scanner.nextLine(); // Nettoyez la nouvelle ligne après la saisie d'un nombre.

            // Traitez les saisies et effectuez l'attaque en conséquence.
            // Ici, vous pouvez appeler des méthodes pour gérer l'attaque avec les territoires source et cible, et le nombre de régiments.

            System.out.println("Attaque effectuée depuis " + territoireSource + " vers " + territoireCible + " avec " + nombreRegiments + " régiments.");
        }

        System.out.println("Fin de la phase d'attaque.");
    }

    public void fermerScanner() {
        scanner.close();
    }




}

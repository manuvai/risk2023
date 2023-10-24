package res.controler;

import res.model.Territoire;

import java.util.Scanner;

public class Controler {

    // Phase Fortification
    public void phaseFortification() throws Exception {
        // 1.Demander joueur -> Fortification ?
        while (true) {
            int resJ = demanderFortification();
            if (resJ != 1 && resJ != 2) {
                throw new Exception("Saissiez numero 1 ou 2 SVP !");
            } else {
                // 2.Commencer Fortification
                if (resJ == 1) {
                    Territoire tS = DemanderTerritoireSource();
                    Territoire tC = DemanderTerritoireCible();
                    int nbRegiment = DemanderNbRegimentDeplace();
                    deplacerRegiment(tS, tC, nbRegiment);
                    break;
                }
            }
        }
    }


    public int demanderFortification() throws Exception {
        Scanner sc = new Scanner(System.in);
        // Demander si joueur va commencer l'étape de fortification
        System.out.println("Est-ce que vous voulez fortifier(1/2) ? (Reponse : 1 - Oui, 2 - Non");
        int resJ = sc.nextInt();
        sc.close();
        return resJ;

    }

    public Territoire DemanderTerritoireSource() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez sélectionner un territoireSource :");
            String territoireSource = sc.nextLine();
            // Si joueur controle ce territoire, on changer le String -> Territoire
            if (joueur.controleTerritoire(territoireSource)) { // Boolean <- controleTerritoire(territoireSource)
                Territoire tS = joueur.ExchangeStringTerritoire(territoireSource); // Territoire <- ExchangeStringTerritoire(territoireSource)
                sc.close();
                return tS;
            } else {
                System.err.println("Vous controle pas ce territoire ! Ressayer !");
            }
        }
    }

    public Territoire DemanderTerritoireCible() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez sélectionner un territoireCible :");
            String territoireCible = sc.nextLine();

            // Si joueur controle ce territoire, on changer le String -> Territoire
            if (joueur.controleTerritoire(territoireSource)) { // Boolean <- controleTerritoire(territoireSource)
                Territoire tC = joueur.ExchangeStringTerritoire(territoireCible); // Territoire <- ExchangeStringTerritoire(territoireSource)
                sc.close();
                return tC;
            } else {
                System.err.println("Vous controle pas ce territoire ! Ressayer !");
            }
        }
    }

    public int DemanderNbRegimentDeplace(Territoire tS) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez sélectionner un territoireCible :");
            System.out.println("Vous avez" + tS.getNbRegiment() + " regiments ici !");// int <- getNbRegiment()
            System.out.println("Vous pouvez deplacer 0 -> " + (tS.getNbRegiment() - 1) + "regiments");
            int nbRegiment = sc.nextInt();

            if (nbRegiment > (tS.getNbRegiment() - 1)) {
                System.err.println("Vous n'avez pas assez de regiments");
            } else if (nbRegiment < 0) {
                System.err.println("nbRegiment ne peut pas < 0");
            } else {
                sc.close();
                return nbRegiment;
            }
        }
    }

    public void deplacerRegiment(
            Territoire territoireSource,
            Territoire territoireCible,
            int nombreRegiment) {
        joueur.deplacerRegiment(territoireSource, territoireCible, nombreRegiment);
    }

}


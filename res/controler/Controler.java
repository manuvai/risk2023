package res.controler;

import res.model.Joueur;
import res.model.Plateau;
import res.model.Territoire;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Controler {
    private Scanner scanner;

    public static void main(String[] args) {
        Joueur j1 = new Joueur();

    }

    // Jeux
    public void Game(List<Joueur> joueurs) throws Exception {
        Plateau p = new Plateau();
        p.initialiserParties();
        while(true){
            unTour(joueurs);
            siPerdu(joueurs);
            if (siVaqueur(joueurs)){
                break;
            }
        }
    }


    // unTour
    public void unTour(List<Joueur> joueurs) throws Exception {
        Iterator<Joueur> iterator = joueurs.iterator();

        while (iterator.hasNext()) {
            Joueur j = iterator.next();

            if (j.obtenirTerritoires().isEmpty()) {
                iterator.remove();
            } else {
                phaseRenfort(j);
                phaseAttaque(j);
                phaseFortification(j);
            }
        }
    }


    public Controler() {
        this.scanner = new Scanner(System.in);
    }

    //PhaseRenfort
    public void phaseRenfort(Joueur j){

    }


    // Phase Fortification

    /**
     * Effectue la phase de fortification, permettant aux joueurs de déplacer des régiment pour renforcer leurs territoires.
     *
     * @param resJ Option indiquant si le joueur souhaite ou non effectuer une fortification.
     *             1 pour effectuer une fortification, 2 pour ne pas en effectuer.
     * @throws Exception Lorsque le joueur entre une option invalide.
     */
    public void phaseFortification(Joueur j) throws Exception {
        // 1.Demander joueur -> Fortification ?
        while (true) {
            int resJ = demanderFortification();

            if (resJ != 1 && resJ != 2) {
                throw new Exception("Saissiez numero 1 ou 2 SVP !");
            }

            // 2.Commencer Fortification
            if (resJ == 1) {
                Territoire tS = demanderTerritoireSource();
                Territoire tC = demanderTerritoireCible();
                int nbRegiment = demanderNbRegimentDeplace(tS);
                deplacerRegiment(tS, tC, nbRegiment);
                break;
            }
        }
    }

    /**
     * Demande au joueur s'il souhaite effectuer une phase de fortification.
     *
     * @return 1 si le joueur souhaite effectuer une fortification, 2 si le joueur ne souhaite pas en effectuer.
     * @throws Exception Lorsque le joueur entre une option invalide.
     */

    public int demanderFortification() throws Exception {
        Scanner sc = new Scanner(System.in);
        // Demander si joueur va commencer l'étape de fortification
        System.out.println("Est-ce que vous voulez fortifier(1/2) ? (Reponse : 1 - Oui, 2 - Non");
        int resJ = sc.nextInt();
        sc.close();

        return resJ;

    }


    public Joueur getActualJoueur() {
        Joueur joueur = new Joueur();


        // TODO Permettre de savoir de quel joueur il s'agit

        return joueur;
    }

    public Territoire recupererTerritoire(String nomTerritoire) {
        Territoire territoire = new Territoire(nomTerritoire);

        // TODO Implémenter la recherche d'un territoire à partir d'un nom

        return territoire;
    }

    /**
     * Demande au joueur de sélectionner un territoire source pour la phase de fortification.
     *
     * @return Le territoire source sélectionné par le joueur.
     */
    public Territoire demanderTerritoireSource() {
        Scanner sc = new Scanner(System.in);

        // il faut print list de territories de joueur.
        List<Territoire> territoiresJoueur = getActualJoueur().obtenirTerritoires();
        for (int i = 0; i < territoiresJoueur.size(); i++) {
            // ex. 1. ter1
            //     2. ter2
            System.out.println((i + 1) + territoiresJoueur.get(i).getNom());
        }

        while (true) {
            System.out.println("Veuillez sélectionner un territoireSource :");
            int numTerritoire = sc.nextInt();


            Territoire territoireSource = recupererTerritoire(nomTerritoireSource);
            // Si joueur controle ce territoire, on changer le String -> Territoire
            if (getActualJoueur().isPossessed(territoireSource)) { // Boolean <- controleTerritoire(territoireSource)
                // getActualJoueur().ExchangeStringTerritoire(territoireSource); // Territoire <- ExchangeStringTerritoire(territoireSource)
                return territoireSource;

            } else {
                System.err.println("Vous controle pas ce territoire ! Ressayer !");
            }
            sc.close();
        }
    }

    /**
     * Demande au joueur de sélectionner un territoire cible pour la phase de fortification.
     *
     * @return Le territoire cible sélectionné par le joueur.
     */
    public Territoire demanderTerritoireCible() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez sélectionner un territoireCible :");
            String nomTerritoireCible = sc.nextLine();

            Territoire territoireCible = recupererTerritoire(nomTerritoireCible);

            // Si joueur controle ce territoire, on changer le String -> Territoire
            if (getActualJoueur().isPossessed(territoireCible)) { // Boolean <- controleTerritoire(territoireSource)
                return territoireCible;
            } else {
                System.err.println("Vous controle pas ce territoire ! Ressayer !");
            }
            sc.close();
        }
    }


    /**
     * Demande au joueur de saisir le nombre de régiments à déplacer depuis le territoire source donné.
     *
     * @param tS Le territoire source depuis lequel le joueur souhaite déplacer des régiments.
     * @return Le nombre de régiments à déplacer, saisi par le joueur.
     */
    public int demanderNbRegimentDeplace(Territoire tS) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez sélectionner un territoireCible :");
            System.out.println("Vous avez" + tS.getNombreUnites() + " regiments ici !");// int <- getNbRegiment()
            System.out.println("Vous pouvez deplacer 0 -> " + (tS.getNombreUnites() - 1) + "regiments");
            int nbRegiment = sc.nextInt();

            if (nbRegiment > (tS.getNombreUnites() - 1)) {
                System.err.println("Vous n'avez pas assez de regiments");
            } else if (nbRegiment < 0) {
                System.err.println("nbRegiment ne peut pas < 0");
            } else {
                sc.close();
                return nbRegiment;
            }
        }
    }


    /**
     * Méthode permettant de déplacer des troupes pour renforcer ou redéployer un territoire. Les joueurs peuvent utiliser cette méthode
     * pour déplacer des troupes d'un territoire à un autre.
     *
     * @param territoireSource Le territoire de départ pour le déplacement des troupes.
     * @param territoireCible  Le territoire de destination pour le déplacement des troupes.
     * @param nombreRegiment   Le nombre de troupes à déplacer.
     */
    public void deplacerRegiment(
            Territoire territoireSource,
            Territoire territoireCible,
            int nombreRegiment) {
        getActualJoueur().deplacerRegiment(territoireSource, territoireCible, nombreRegiment);
    }


    /**
     * Effectue la phase de attaquer, permettant aux joueurs de attaquer les territoires d'autre joueurs
     *
     * @param joueur Ce joeur qui fait la phase Attaque
     */
    public void phaseAttaque(Joueur joueur) {
        System.out.println("Phase d'attaque pour le joueur : " + joueur.getNom()); // TODO Remettre getNom()


        // Demander au joueur de choisir le nombre de régiments pour l'attaque
        System.out.print("Saisissez le nombre de régiments à utiliser pour l'attaque : ");
        int nombreRegiments = scanner.nextInt();
        scanner.nextLine(); // Nettoyez la nouvelle ligne après la saisie d'un nombre.

        // Définir le nombre maximum de dés (par exemple, la moitié du nombre de régiments avec un maximum de 3 dés)
        int nombreDesMaximum = Math.min(nombreRegiments / 2, 3);

        int nombreDesChoisi = 0;

        while (true) {

            // Demander au joueur de choisir le territoire source
            System.out.print("Saisissez le nom du territoire source (ou tapez 'fin' pour terminer l'attaque) : ");
            String territoireSource = scanner.nextLine();

            if (territoireSource.equalsIgnoreCase("fin")) {
                // Si le joueur entre "fin", terminez la phase d'attaque.
                break;
            }
            // Demander au joueur de choisir le nombre de dés à lancer
            System.out.print("Saisissez le nombre de dés à lancer (maximum " + nombreDesMaximum + ") : ");
            nombreDesChoisi = scanner.nextInt();
            scanner.nextLine(); // Nettoyez la nouvelle ligne après la saisie d'un nombre.

            if (nombreDesChoisi >= 1 && nombreDesChoisi <= nombreDesMaximum) {
                // Le choix est valide, sortez de la boucle.
                break;
            } else {
                System.out.println("Choix invalide. Le nombre de dés doit être entre 1 et " + nombreDesMaximum + ".");
            }


            // Déterminer les territoires accessibles à partir du territoire source.
            List<Territoire> territoiresAccessibles = getTerritoiresAccessiblesDepuis(territoireSource, joueur);

            if (territoiresAccessibles.isEmpty()) {
                System.out.println("Aucun territoire accessible depuis " + territoireSource + ".");
            } else {
                System.out.println("Territoires pouvant être attaqués depuis " + territoireSource + " :");
                for (Territoire territoire : territoiresAccessibles) {
                    System.out.println("- " + territoire.getNom());
                }

                // Demander au joueur de choisir le territoire cible
                System.out.print("Saisissez le nom du territoire cible : ");
                String territoireCible = scanner.nextLine();

                // Demander au joueur de choisir le nombre de régiments pour l'attaque
                System.out.print("Saisissez le nombre de régiments à utiliser pour l'attaque : ");
                nombreRegiments = scanner.nextInt();
                scanner.nextLine(); // Nettoyez la nouvelle ligne après la saisie d'un nombre.

                // Traitez les saisies et effectuez l'attaque en conséquence.
                // Ici, vous pouvez appeler des méthodes pour gérer l'attaque avec les territoires source et cible, et le nombre de régiments.

                System.out.println("Attaque effectuée depuis " + territoireSource + " vers " + territoireCible + " avec " + nombreRegiments + " régiments.");
            }
        }

        System.out.println("Fin de la phase d'attaque.");
    }

    /**
     * Cette méthode permet d'obtenir la liste des territoires accessibles depuis un territoire source pour un joueur donné.
     *
     * @param territoireSource Le nom du territoire source à partir duquel l'accès est recherché.
     * @param joueur           Le joueur pour lequel on recherche les territoires accessibles.
     * @return Une liste de territoires accessibles depuis le territoire source.
     */
    private List<Territoire> getTerritoiresAccessiblesDepuis(String territoireSource, Joueur joueur) {
        List<Territoire> territoiresAccessibles = new ArrayList<>();

        // Supposons que nous avons une liste de tous les territoires du jeu
        List<Territoire> tousTerritoires = joueur.obtenirTerritoires();

        // Parcourir tous les territoires et les ajouter à la liste des territoires accessibles
        for (Territoire territoire : tousTerritoires) {
            if (!territoire.getNom().equals(territoireSource)) {
                territoiresAccessibles.add(territoire);
            }
        }

        return territoiresAccessibles;
    }


    public void fermerScanner() {
        scanner.close();
    }

}


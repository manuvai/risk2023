package res.controler;
import res.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

    /**
     * Cette classe représente le controller du jeu.
     *
     * @author Fatima MOUSLIM
     * @author Manuvai REHUA
     * @author Yifan SHI
     * @author Han PANG
     * @since 24/10/23
     */

public class Controler {
    private Scanner scanner;
    
    private Plateau plateau;//TODO Besoin d'une instance d'objet plateau pour méthodes

    private List<Joueur> joueurs;

    private Joueur actualJoueur;

    public Controler() {
        this.scanner = new Scanner(System.in);

    }

    /**
     * Obtenir la liste des joueurs actuels dans le jeu.
     *
     * @return Une liste contenant tous les joueurs actuels du jeu.
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public static void main(String... args) throws Exception {

        Controler ctrl = new Controler();

        ctrl.initializePlateau();
        System.out.println("Démarrage de la partie");
        // TODO Initialiser
        while (ctrl.getJoueurs().size() > 1) {
           ctrl.afficherTourJoueur(ctrl.getActualJoueur());

           ctrl.phaseRenforts();
           ctrl.startAttackPhase();
            ctrl.phaseFortification();

            ctrl.eliminerPerdants();
            ctrl.passerAuJoueurSuivant();
        }

        ctrl.vainqueur(ctrl.getActualJoueur());
        System.out.println("Fin de la partie");

        // TODO Décommenter la partie du bas lors de tests
        // testGauthier(ctrl);

    }

    private void vainqueur(Joueur actualJoueur) {
        if (Objects.nonNull(actualJoueur)) {
            System.out.println("\nToutes nos félicitations ".concat(actualJoueur.getPrenom()).concat(" vous avez gagné\n"));
        }
    }

    private void afficherTourJoueur(Joueur actualJoueur) {
        if (Objects.nonNull(actualJoueur)) {
            System.out.println("\n".concat(actualJoueur.getPrenom()).concat(", c'est votre tour\n"));
        }
    }

    private void phaseRenforts() {
        System.out.println("Vous allez procéder à la phase de renfort");
        int nbRenforts = getActualJoueur().calculerNbRenforts(plateau);
        distribuerRenforts(getActualJoueur(), nbRenforts);
        System.out.println("Fin de la phase de renfort");
    }

    private void eliminerPerdants() {
        List<Joueur> perdants = new ArrayList<>();

        for (Joueur joueur : getJoueurs()) {
             if (joueur.obtenirTerritoires().isEmpty()) {
                 perdants.add(joueur);
             }
        }

        afficherPerdants(perdants);

        joueurs.removeAll(perdants);
    }

        private void afficherPerdants(List<Joueur> perdants) {
            List<String> nomPerdants = perdants.parallelStream()
                    .map(Joueur::getPrenom)
                    .collect(Collectors.toList());

            if (!nomPerdants.isEmpty()) {
                System.out.println("Joueurs éliminés :".concat(String.join(", ", nomPerdants)));
            }
        }


        /**
         * tester fortification.
         *
         * @return Controler
         */

    private static void testFortification(Controler ctrl) throws Exception {
        //tester distribuer Territoires
        System.out.println("La liste des territories dans les continents: ");
        List<String> ttTerr = new ArrayList<String>();
        for (Continent c : ctrl.plateau.getContinents()) {
            for (Territoire t : c.getTerritories()) {
                ttTerr.add(t.getNom());
            }
        }
        System.out.println(Arrays.toString(ttTerr.toArray()));

        System.out.println("apres distribution des territoires pour chanque joueur: ");
        for (Joueur j : ctrl.joueurs){
            System.out.println("Joueur : " + j.getNom()); // Affiche le nom du joueur
            List<String> terrJoueur = new ArrayList<String>();

            for (Territoire t : j.obtenirTerritoires()) {
                terrJoueur.add(t.getNom());
            }
            System.out.println(Arrays.toString(terrJoueur.toArray()));
        }

        ctrl.phaseFortification();
    }

    public static void testGauthier(Controler ctrl) {
    	Joueur j1 = ctrl.getActualJoueur();
    	Armee rouge = new Armee("rouge");
    	j1.setArmee(rouge);
    	//List<CarteRisk>cartes = ctrl.plateau.creerCartes();
		/* Méthode creerCartes() renvoie bien une liste de cartes avec pour valeurs un type de pion et un territoire
		 * for (CarteRisk carteRisk : cartes) {
		 * System.out.println(carteRisk.getTypePion() +" "+
		 * carteRisk.getTerritoire().getNom()); }
		 */
    	
		/* Méthode de distribution des renforts ok
		 * System.out.println(j1.getArmee().getPions()); //Armée vide donc liste vide
		 * ctrl.distribuerRenforts(j1, 5); //On ajoute 5 renforts, soit un pion
		 * cavalerie System.out.println(j1.getArmee().getPions().get(0).getNomPion());
		 */
    	
		/*Méthode d'échange de cartes ok
		 * j1.getArmee().ajouterPion(new Pion("Infanterie", TypePion.INFANTERIE)); for
		 * (Pion pion : j1.getArmee().getPions()) {
		 * System.out.println(pion.getNomPion()); } for (int i = 0; i < 5; i++) {
		 * j1.ajouterCarte(cartes.get(i)); } ctrl.echangerCartes(j1); for (Pion pion :
		 * j1.getArmee().getPions()) { System.out.println(pion.getNomPion()); }
		 */
    	
    }
	/*
	 * //tester distribuer Territoires System.out.println("tous les territories: ");
	 * List<String> ttTerr = new ArrayList<String>(); for (Continent c :
	 * ctrl.plateau.getContinents()) { for (Territoire t : c.getTerritories()) {
	 * ttTerr.add(t.getNom()); } }
	 * System.out.println(Arrays.toString(ttTerr.toArray()));
	 * 
	 * System.out.println("apres distribution, pour chanque joueur: "); for (Joueur
	 * j : ctrl.joueurs){ List<String> terrJoueur = new ArrayList<String>();
	 * 
	 * for (Territoire t : j.obtenirTerritoires()) { terrJoueur.add(t.getNom()); }
	 * System.out.println(Arrays.toString(terrJoueur.toArray())); }
	 * 
	 * ctrl.phaseFortification();
	 * 
	 * }
	 */

    /**
     * Récupère la saisie de l'utilisateur pour avoir le nombre de joueurs
     *
     * @return
     */
    public int getNbJoueurs() {
        System.out.println("Pour commencer une partie, vous devez définir le nombre de joueurs");
        System.out.print("Combien de joueurs jouent dans cette partie ? (1 à 5) : ");

        int nbJoueurs = Integer.parseInt(scanner.nextLine());

        while (nbJoueurs <= 0 || nbJoueurs > 5) {
            System.out.print("Veuillez saisir un nombre entre 1 et 5 : ");
            nbJoueurs = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Vous avez choisi d'initialiser une partie à ".concat(Integer.toString(nbJoueurs)).concat(" joueurs."));
        return nbJoueurs;
    }

    /**
     * Initialise et retourne une liste de joueurs pour le jeu.
     *
     * @param nbJoueurs Le nombre de joueurs à initialiser.
     * @return Une liste de joueurs prête pour le jeu, ou une liste vide si le nombre de joueurs est incohérent.
     */
    public List<Joueur> initialiserJoueurs(int nbJoueurs) {

        if (nbJoueurs <= 0 || nbJoueurs > 5) {
            System.out.println("Vous devez donner un nombre cohérent des joueurs");
            return new ArrayList<>();
        }

        System.out.println("\nVous devez maintenant procéder à l'initialisation des joueurs.\n");
        List<Joueur> joueurs = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            Joueur joueur = new Joueur();
            String prenomJoueur = saisiePrenomJoueur(i);

            joueur.setPrenom(prenomJoueur);
            joueurs.add(joueur);
            joueur.setArmee(new Armee("Couleur ".concat(Integer.toString(i + 1))));
        }

        return joueurs;
    }

        /**
         * Demande et saisit le nom d'un joueur en utilisant l'entrée du scanner.
         *
         * @param i Le numéro du joueur (utilisé pour afficher le message).
         * @return Le nom du joueur saisi.
         */
    private String saisieNomJoueur(int i) {
        System.out.print("Veuillez entrer le nom du joueur ".concat(Integer.toString(i + 1)).concat(" : "));
        String saisie = scanner.nextLine();

        while ("".equals(saisie)) {
            System.out.print("Veuillez saisir un nom valide : ");
            saisie = scanner.nextLine();
        }

        return saisie;
    }

        /**
         * Demande et saisit le prénom d'un joueur en utilisant l'entrée du scanner.
         *
         * @param i Le numéro du joueur (utilisé pour afficher le message).
         * @return Le prénom du joueur saisi.
         */
    private String saisiePrenomJoueur(int i) {
        System.out.print("Veuillez entrer le prenom du joueur ".concat(Integer.toString(i + 1)).concat(" : "));
        String saisie = scanner.nextLine();

        while ("".equals(saisie)) {
            System.out.print("Veuillez saisir un prenom valide : ");
            saisie = scanner.nextLine();
        }

        return saisie;

    }
    // Switch Joueur
        /**
         * Passe au joueur suivant dans la liste des joueurs.
         *Si le joueur actuel est le dernier de la liste, la méthode revient au premier joueur.
         */
    public void passerAuJoueurSuivant() {

        System.out.println("\nFin du tour de ".concat(getActualJoueur().getPrenom()).concat("\n"));
        int currentIndex = joueurs.indexOf(actualJoueur);

        actualJoueur = joueurs.get((currentIndex + 1) % joueurs.size());

        System.out.println("\nDébut du tour de ".concat(getActualJoueur().getPrenom()).concat("\n"));
    }


    // Phase Préparation

        /**
         * Initialise le plateau de jeu, y compris les joueurs, les cartes Risk, et les territoires.
         *
         * Cette méthode initialise un nouveau plateau de jeu en créant les joueurs, en attribuant des cartes Risk aux joueurs,
         * et en préparant les territoires pour le début de la partie.
         *
         * @throws Exception si une exception se produit lors de l'initialisation du plateau.
         */

    public void initializePlateau() throws Exception {
        plateau = new Plateau();
        System.out.println("  _____  _     _      ___   ___ ___  ____  \n" +
                " |  __ \\(_)   | |    |__ \\ / _ \\__ \\|___ \\ \n" +
                " | |__) |_ ___| | __    ) | | | | ) | __) |\n" +
                " |  _  /| / __| |/ /   / /| | | |/ / |__ < \n" +
                " | | \\ \\| \\__ \\   <   / /_| |_| / /_ ___) |\n" +
                " |_|  \\_\\_|___/_|\\_\\ |____|\\___/____|____/ \n" +
                "                                           \n");

        System.out.println("Bienvenue au jeu du Risk\n");
        int nbJoueurs = getNbJoueurs();
        List<Joueur> joueurs = initialiserJoueurs(nbJoueurs);

        plateau.setJoueurs(joueurs);
        this.joueurs = joueurs;
        this.actualJoueur = joueurs.get(0);

        plateau.distribuerCartes(joueurs); // Distribuer les cartes aux joueurs
        plateau.initialiserParties();

    }


        /**
         * Définit la liste des joueurs pour cette instance de Plateau.
         *
         * @param joueurs La liste des joueurs à associer à ce plateau.
         */
    public void setJoueurs(List<Joueur> joueurs){
        this.joueurs = joueurs;
    }


    // Phase Fortification
    /**
     * Effectue la phase de fortification, permettant aux joueurs de déplacer des régiment pour renforcer leurs territoires.
     *
     * @throws Exception Lorsque le joueur entre une option invalide.
     */
    public void phaseFortification() throws Exception {
        System.out.println("\nVous allez maintenant procéder à la fortification\n");
        // 1.Demander joueur -> Fortification ?
        while (true) {
            int resJ = demanderFortification();

            if (resJ != 1 && resJ != 2) {
                throw new Exception("Saissiez un numero 1 ou 2 SVP !");
            }

            // 2.Commencer Fortification
            if (resJ == 1) {
                Territoire tS = demanderTerritoireSource();
                Territoire tC = demanderTerritoireCiblePossede(getActualJoueur(), tS);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                int nbRegiment = demanderNbRegimentDeplace(tS,tC);
                deplacerRegiment(tS, tC, nbRegiment);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                break;
            } else if (resJ == 2) {
                System.out.println("Vous sautez ce tour de fortification à la prochaine.");
                break;
            }
        }
        System.out.println("\nFin de la phase de fortification\n");

    }

    /**
     * Demande au joueur s'il souhaite effectuer une phase de fortification.
     *
     * @return 1 si le joueur souhaite effectuer une fortification, 2 si le joueur ne souhaite pas en effectuer.
     *
     * @throws Exception Lorsque le joueur entre une option invalide.
     */

    public int demanderFortification() throws Exception {
        // Demander si joueur va commencer l'étape de fortification
        System.out.println("Est-ce que vous voulez fortifier ? ( 1 - Oui, 2 - Non)");
        int resJ = this.scanner.nextInt();
        return resJ;

    }

    public Joueur getActualJoueur() {
        return this.actualJoueur;
    }
    
    /**
     * Récupère un territoire à partir de son nom.
     *
     * @param nomTerritoire Le nom du territoire à récupérer.
     * @return Le territoire correspondant au nom spécifié, ou null s'il n'existe pas de territoire avec ce nom.
     */
    public Territoire recupererTerritoire(String nomTerritoire) {
        List<Territoire> territoiresPlateau = plateau.getTerritoires();

        // les territoires du plateau
        for (Territoire territoire : territoiresPlateau) {
            if (territoire.getNom().equalsIgnoreCase(nomTerritoire)) {
                return territoire; // Si le nom correspond
            }
        }
        // Si aucun territoire trouvé
        return null;
    }


    /**
     * Demande au joueur de sélectionner un territoire source pour la phase de fortification.
     *
     * @return Le territoire source sélectionné par le joueur.
     */
    public Territoire demanderTerritoireSource() {
        while (true) {
            System.out.println("Veuillez choisir un numéro territoireSource de la liste :");
            // afficher tous les territoires de joueur
            for (int i = 0; i < getActualJoueur().obtenirTerritoires().size(); i++) {
                int indexT = i + 1;
                System.out.println(indexT + "." + getActualJoueur().obtenirTerritoires().get(i).getNom() + " : " +
                        getActualJoueur().obtenirTerritoires().get(i).getNombreUnites());
            }
            System.out.println("Saisir un numéro de la liste : ");
            int noTerritoireSource = this.scanner.nextInt();

            if (noTerritoireSource > getActualJoueur().obtenirTerritoires().size() || noTerritoireSource <= 0){
                System.err.println("Veuillez choisir un numéro territoireSource de la liste");
            } else {
                Territoire territoireSource = getActualJoueur().obtenirTerritoires().get(noTerritoireSource - 1);
                System.out.println("Vous avez choisi un territoireSource : " + territoireSource.getNom());
                return territoireSource;
            }

        }
    }

    /**
     * Demande au joueur de sélectionner un territoire cible pour la phase d'attaque.
     *
     * @return Le territoire cible sélectionné par le joueur.
     */
    public Territoire demanderTerritoireCible(Joueur attaquant, Territoire tS) {
        return demanderTerritoireCible(tS, getTerritoiresToAttack(attaquant, tS));
    }

    /**
     * Demande au joueur de sélectionner un territoire cible pour la phase de fortification.
     *
     * @param joueur
     * @param tS
     * @return Le territoire cible sélectionné par le joueur.
     */
    public Territoire demanderTerritoireCiblePossede(Joueur joueur, Territoire tS) {
        return demanderTerritoireCible(tS, joueur.obtenirTerritoires());
    }

    public Territoire demanderTerritoireCible(Territoire tS, List<Territoire> listeTerritoires) {
        while (true) {
            System.out.println("Veuillez choisir un numéro de territoireCible :");
            // afficher tous les territoires de joueur
            for (int i = 0; i < listeTerritoires.size(); i++) {
                int indexT = i + 1;
                System.out.println(indexT + "." + listeTerritoires.get(i).getNom() +
                        " : " + listeTerritoires.get(i).getNombreUnites());
            }

            System.out.println("Saisir un numéro de la liste : ");
            int noTerritoireCible = this.scanner.nextInt();

            if (noTerritoireCible > listeTerritoires.size() || noTerritoireCible <= 0
                    || noTerritoireCible == listeTerritoires.indexOf(tS) + 1
            ){
                System.err.println("Veuillez sélectionner un numéro territoireCible de la liste");
            } else {
                Territoire territoireCible = listeTerritoires.get(noTerritoireCible - 1);
                System.out.println("Vous avez choisi un territoireCible : " + territoireCible.getNom());
                return territoireCible;
            }
        }
    }




    /**
     * Demande au joueur de saisir le nombre de régiments à déplacer depuis le territoire source donné.
     *
     * @param tS Le territoire source depuis lequel le joueur souhaite déplacer des régiments.
     * @return Le nombre de régiments à déplacer, saisi par le joueur.
     */
    public int demanderNbRegimentDeplace(Territoire tS,Territoire tC) {
        while (true) {
            System.out.println("Vous avez " + tS.getNombreUnites() + " regiments dans " + tS.getNom() + " !");
            System.out.println("Vous pouvez deplacer  " + (tS.getNombreUnites() - 1) + " régiments");
            System.out.println("Veuillez choisir un nombre de régiment pour le déplacer dans "+ tC.getNom());
            int nbRegiment = this.scanner.nextInt();

            if (nbRegiment > (tS.getNombreUnites() - 1)) {
                System.err.println("Vous n'avez pas assez de regiments");
            } else if (nbRegiment < 0) {
                System.err.println("nombre de regiment ne peut pas etre moins de 0");
            } else {
                System.out.println("Vous avez déplacé " + nbRegiment + " régiment(s) de " + tC.getNom());
                return nbRegiment;
            }
        }
    }


    /**
     * Méthode permettant de déplacer des troupes pour renforcer ou redéployer un territoire. Les joueurs peuvent utiliser cette méthode
     * pour déplacer des troupes d'un territoire à un autre.
     *
     * @param territoireSource Le territoire de départ pour le déplacement des troupes.
     * @param territoireCible Le territoire de destination pour le déplacement des troupes.
     * @param nombreRegiment Le nombre de troupes à déplacer.
     */
    public void deplacerRegiment(
            Territoire territoireSource,
            Territoire territoireCible,
            int nombreRegiment) {
        getActualJoueur().deplacerRegiment(territoireSource, territoireCible, nombreRegiment);
    }
    
    //Phase d'attaque 


        /**
         * Démarre la phase d'attaque du jeu. Cette méthode gère les actions associées à la phase d'attaque, y compris la sélection
         * des territoires source et cible, le lancement des dés, la résolution de l'attaque et le déplacement des troupes.
         *
         * @throws Exception Si une erreur survient pendant la phase d'attaque, une exception est levée.
         */


        public void startAttackPhase() throws Exception {
            Joueur attaquant = getActualJoueur();
            System.out.println("\nVous allez maintenant procéder à la phase d'attaque\n");
            // 1.Demander attack?
            while (true) {
                int resJ = demanderAttaque();
                // while (canAttack(attaquant)) {
                System.out.println("Phase d'attaque pour le joueur : " + attaquant.getPrenom());

                if (resJ != 1 && resJ != 2) {
                    throw new Exception("Saissiez numero 1 ou 2 SVP !");
                }

                // 2.Commencer Attack
                if (resJ == 1) {
                    // Demander TerritoireSource
                    Territoire tS = demanderTerritoireSource();
//              System.out.println(tS.getNombreUnites());

                    Territoire tC = demanderTerritoireCiblePourAttaquer(attaquant,tS);

                    //S'il n'y a pas de territoires à attaquer, la phase d'attaque est recommencée
                    if (tC == null){
                        continue;
                    }

                /* Obtient des informations sur le regiment du terrtoireSource en cours
                   et indique le nombre de dés que le joueur peut lancer.
                   ex. 2 régiments ->  1 régimentAttaque -> 1 dès
                       3 régiments ->  1/2 régimentAttaque -> 1 ou 2 dès
                       4+ régiments ->  1/2/3 régimentAttaque -> 1 ou 2 ou 3 dès
                */
                    // Demander au joueur le nombre de dés à lancer pour l'attaque
                    int desAttaque = demanderNbDesAttaque(tS);
//                System.out.println(desAttaque);
                    scanner.nextLine(); // Nettoyer la nouvelle ligne.


                    // Lancer les dés pour l'attaque
                    List<De> resultatsAttaque = attaquant.lancerDes(desAttaque);

                    for (De d : resultatsAttaque){
                        System.out.println(d.recupererValeur());
                    }
                    //lancerDes(desAttaque);

                    // Déterminer les troupes restantes
                    // Entrain de fix !!!
                    int troupesRestantes = resolveAttack(resultatsAttaque, desAttaque);

                    if (troupesRestantes > 0) {
                        // L'attaquant a réussi l'attaque
                        System.out.println("Attaque réussie ! Vous avez conquis le territoire.");
                        // Territoire territoireCible = recupererTerritoire( tC);
                        // Retirer le territoire du défenseur
                        //retirerProprietaireTerritoire();

                        // Ajouter le territoire à l'attaquant
                        //attaquant.ajouterTerritoire(tC);

                        // Demander la saisie du nombre de troupes à déplacer
                        //System.out.print("Saisissez le nombre de troupes à déplacer : ");
                        //int troupesADeplacer = scanner.nextInt();
                       // scanner.nextLine(); // Nettoyer la nouvelle ligne.
                        // Déplacer les régiments
                        //attaquant.deplacerRegiment(tS, tC, troupesADeplacer);
                    } else {
                        System.out.println("Attaque échouée. Le territoire est toujours aux mains du défenseur.");
                    }
                    int nbRegiment = demanderNbRegimentDeplace(tS, tC);
//                deplacerRegiment(tS, tC, nbRegiment);
//                System.out.println(tS.getNombreUnites());
//                System.out.println(tC.getNombreUnites());
                    break;
                } else if (resJ == 2) {
                    System.out.println("Vous sautez ce tour d'attaque à la prochaine.");
                    break;
                }
            }

        }

        private int demanderNbDesAttaque(Territoire tS){
            while (true){
                System.out.println("Vous avez " + tS.getNombreUnites() + " régiments ");
                if (tS.getNombreUnites() == 2){
                    System.out.println("Pour 2 régiments vous pouvez lancer 1 dès");
                    int desAttaque = 1;
                    return desAttaque;

                } else if (tS.getNombreUnites() == 3){
                    System.out.println("Pour 3 régiments vous pouvez lancer 1 ou 2 dès");
                    int desAttque = this.scanner.nextInt();
                    if (desAttque < 1 || desAttque > 2){
                        System.err.println("Le nombre de dès doit être 1 / 2");
                        continue;
                    }
                    return desAttque;
                } else if (tS.getNombreUnites() >= 4){
                    System.out.println("Pour 4+ régiments vous pouvez lancer 1 ou 2 ou 3 dès");
                    int desAttque = this.scanner.nextInt();
                    if (desAttque < 1 || desAttque > 3){
                        System.err.println("Le nombre de dès doit être 1 / 2 / 3");
                        continue;
                    }
                    return desAttque;
                }
            }
        }

        private Territoire demanderTerritoireCiblePourAttaquer(Joueur attaquant, Territoire tS){
            while (true){
                // Demander au joueur d'obtenir la liste des territoires pour attaquer
                List<Territoire> territoiresAttaquables = getTerritoiresToAttack(attaquant, tS);
                for (int i = 0; i < territoiresAttaquables.size(); i++) {
                    int indexTerr = i + 1;
                    System.out.println(indexTerr + " . " + territoiresAttaquables.get(i).getNom());
                }

                if (territoiresAttaquables.isEmpty()) {
                    System.out.println("Le joueur ne peut plus mener d'attaque. Fin de la phase d'attaque.");
                    break;
                }

                // Demander TerritoireCible dans List -> territoiresAttaquables
                System.out.println("Veuillez choisir un numéro de territoire pour attaquer : ");
                int indexTc = this.scanner.nextInt();
                if ((indexTc < 0) || (indexTc > territoiresAttaquables.size())){
                    System.err.println("Veuillez saisir un territoire avec un nombre de régiments plus de 1");
                    continue;
                } else {
                    Territoire tC = territoiresAttaquables.get(indexTc - 1);
                    System.out.println("Vous avez choisi un territoireCible : " + tC.getNom());
                    return tC;
                }

            }

            System.out.println("\nFin de la phase d'attaque");
            return null;
        }


        /**
         * Demande au joueur s'il souhaite commencer l'étape d'attaque.
         *
         * @return 1 si le joueur souhaite attaquer, 2 s'il souhaite sauter son tour.
         * @throws Exception Si une réponse invalide est donnée, une exception est levée.
         */
    private int demanderAttaque() throws Exception {
        // Demander si joueur va commencer l'étape d'attaque
        System.out.println("Est-ce que vous voulez Attaquer ? (Reponse : 1 - Oui, 2 - Non)");
        int resJ = this.scanner.nextInt();
        return resJ;

    }


        public boolean verifierTerritoireAvecUnSeulRegiment(Territoire territoire) {
            return territoire.getNombreUnites() == 1;
        }

        public Territoire demanderTerritoireAvecPlusieursRegiments(Joueur joueur) {
            Territoire territoireChoisi = null;
            boolean choixValide = false;

            while (!choixValide) {
                System.out.print("Sélectionnez un territoire avec plus d'un régiment : ");
                String nomTerritoire = scanner.nextLine();

                for (Territoire t : joueur.obtenirTerritoires()) {
                    if (t.getNom().equalsIgnoreCase(nomTerritoire) && t.getNombreUnites() > 1) {
                        territoireChoisi = t;
                        choixValide = true;
                        break;
                    }
                }

                if (!choixValide) {
                    System.out.println("Le territoire choisi est invalide. Assurez-vous de choisir un territoire avec plus d'un régiment.");
                }
            }

            return territoireChoisi;
        }

    /**
     * Retire un territoire de la liste des territoires possédés par le joueur.
     *
     * @param territoireCible Le territoire à retirer.
     *
     *
     */
    private void retirerProprietaireTerritoire(Territoire territoireCible) {
        Joueur proprietaire = rechercherProprietaireTerritoire(territoireCible);

        if (Objects.nonNull(proprietaire)) {
            proprietaire.retirerTerritoire(territoireCible);
        }
    }

    /**
     * Recherche le propriétaire d'un territoire donné en utilisant les informations du plateau de jeu.
     *
     * @param territoire Le territoire dont vous souhaitez trouver le propriétaire.
     * @return Le joueur qui est propriétaire du territoire, ou null si le propriétaire n'a pas été trouvé ou si le plateau n'est pas défini.
     */
    private Joueur rechercherProprietaireTerritoire(Territoire territoire) {
       return Objects.isNull(plateau)
            ? null
            : plateau.getProprietaire(territoire);

    }

        /**
         * Vérifie si le joueur attaquant peut initier une attaque.
         *
         * @param attaquant Le joueur qui souhaite attaquer.
         * @return True si le joueur peut attaquer, sinon False.
         */
    private boolean canAttack(Joueur attaquant ) {
    	// Vérification du nombre de territoires du joueur attaquant
        if (attaquant.obtenirTerritoires().size() < 2) {
            System.out.println("Vous n'avez pas suffisamment de territoires pour attaquer.");
            return false;
        }

        // Vérification du nombre de régiments disponibles pour l'attaque
        int totalRegiments = 0;
        for (Territoire territoire : attaquant.obtenirTerritoires()) {
            totalRegiments += territoire.getNombreUnites();
        }
        if (totalRegiments < 2) {
            System.out.println("Vous n'avez pas suffisamment de régiments pour attaquer.");
            return false;
        }
        return true;
    }

        /**
         * Obtient la liste des territoires voisins du territoire source qui peuvent être attaqués par le joueur attaquant.
         *
         * @param attaquant Le joueur attaquant.
         * @param territoireSource Le territoire source à partir duquel l'attaque est lancée.
         * @return Une liste de territoires attaquables par le joueur attaquant, qui sont des voisins non possédés par le joueur.
         */
    private List<Territoire> getTerritoiresToAttack(Joueur attaquant, Territoire territoireSource) {
        // Recherche du territoire source
        if (territoireSource == null) {
            System.out.println("Territoire source invalide.");
            return new ArrayList<>();
        }

        // Obtention des territoires attaquables (voisins non possédés par l'attaquant)

        return territoireSource.getVoisins().stream()
                .filter(voisin -> !attaquant.obtenirTerritoires().contains(voisin))
                .collect(Collectors.toList());
    }

        /**
         * Lance un certain nombre de dés pour la phase de défense.
         *
         * @param nombreDes Le nombre de dés à lancer pour la défense.
         * @return Une liste des résultats obtenus après le lancer de dés pour la défense.
         */
    private List<De> lancerDesDefense(int nombreDes) {
        List<De> resultatsDefense = new ArrayList<>();

        for (int i = 0; i < nombreDes; i++) {
            // Lancez un dé pour chaque dé du défenseur.
            De de = new De();
            de.lancerDe();
            resultatsDefense.add(de);
        }

        return resultatsDefense;
    }

        /**
         * Résout le résultat d'une attaque en comparant les résultats des dés d'attaque et de défense.
         *
         * @param resultatsAttaque     Liste des résultats des dés lancés par l'attaquant.
         * @param desDefense           Le nombre de dés lancés par le défenseur pour la phase de défense.
         * @return Le nombre de troupes restantes après l'attaque.
         */
    private int resolveAttack(List<De> resultatsAttaque, int desDefense) {
        // Lancez les dés de défense pour obtenir les résultats du défenseur.
        List<De> resultatsDefense = lancerDesDefense(desDefense);

        // Tri des résultats d'attaque et de défense par ordre décroissant
        List<De> sortedAttackResults = resultatsAttaque.parallelStream()
                .sorted(Comparator.comparingInt(De::recupererValeur))
                .collect(Collectors.toList());

        List<De> sortedDefenseResults = resultatsDefense.parallelStream()
                .sorted(Comparator.comparingInt(De::recupererValeur))
                .collect(Collectors.toList());

        // Initialisez le nombre de pertes.
        int pertes = 0;

        // Déterminez le nombre de pertes en comparant les dés d'attaque et de défense.
        for (int i = 0; i < Math.min(sortedAttackResults.size(), sortedDefenseResults.size()); i++) {
            if (sortedAttackResults.get(i).recupererValeur() <= sortedDefenseResults.get(i).recupererValeur()) {
                pertes++;
            }
        }

        // Déterminez le nombre de troupes restantes après l'attaque.
        return resultatsAttaque.size() - pertes;
    }


    /*
    private int resolveAttack(List<De> resultatsAttaque ) {
    	 // Tri des résultats d'attaque par ordre décroissant
        Collections.sort(resultatsAttaque, Collections.reverseOrder());

        // Initialisez le nombre de pertes.
        int pertes = 0;

        // Déterminez le nombre de pertes en comparant les dés.
        for (int i = 0; i < resultatsAttaque.size(); i++) {
            // Par exemple, si 6 est le résultat d'un dé, cela signifie une victoire.
            // Vous pouvez personnaliser ces règles en fonction de votre jeu.
            if (resultatsAttaque.get(i).recupererValeur() < 6) {
                pertes++;
            }
        }

        // Déterminez le nombre de troupes restantes après l'attaque.
        int troupesRestantes = resultatsAttaque.size() - pertes;

        return troupesRestantes;
    }
     */

        /**
         * Obtient la liste des territoires accessibles depuis un territoire source donné pour un joueur donné.
         *
         * @param territoireSource Le nom du territoire source à partir duquel on souhaite accéder à d'autres territoires.
         * @param joueur           Le joueur pour lequel on recherche les territoires accessibles.
         * @return Une liste de territoires accessibles à partir du territoire source pour le joueur.
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

        /**
         * Distribue des renforts à un joueur en fonction du nombre total de renforts (nb).
         * Les renforts sont répartis en artillerie, cavalerie et infanterie en respectant les ratios
         * de 10 renforts par artillerie, 5 renforts par cavalerie et 1 renfort par infanterie.
         *
         * @param joueur Le joueur auquel les renforts sont distribués.
         * @param nb     Le nombre total de renforts à distribuer.
         */
	public void distribuerRenforts(Joueur joueur, int nb) {
		int artillerie = nb / 10;
		nb = nb % 10;
		int cavalerie = nb / 5;
		nb = nb % 5;
		int infanterie = nb;
		
		for (int i = 0; i < artillerie; i++) {
			Pion pion = new Pion("Artillerie", TypePion.ARTILLERIE);
			joueur.getArmee()
                    .ajouterPion(pion);
		}

		for (int i = 0; i < cavalerie; i++) {
			Pion pion = new Pion("Cavalerie", TypePion.CAVALERIE);
			joueur.getArmee()
                    .ajouterPion(pion);
		}

		for (int i = 0; i < infanterie; i++) {
			Pion pion = new Pion("Infanterie", TypePion.INFANTERIE);
			joueur.getArmee()
                    .ajouterPion(pion);
		}

	}

        /**
         * Permet au joueur d'échanger des cartes et de recevoir des renforts en fonction des cartes échangées.
         *
         * @param joueur Le joueur qui effectue l'échange de cartes.
         */
	public void echangerCartes(Joueur joueur) {
		int regimentsADonner = 0;
		while (true) {
			List<CarteRisk> listeCartes = joueur.getCartes();
			int i = 1;
			System.out.println("Voici les cartes dont vous disposez actuellement");
			for (CarteRisk carte : listeCartes) {
				if(carte instanceof Joker) {
					System.out.println(i+" : Carte Joker");
				} else {
					System.out.println(i+" : " + carte.getTypePion() +" / "+carte.getTerritoire().getNom());
				}
				i++;
			}
			System.out.println("Choisir une carte à échanger (écrivez 0 si vous ne voulez pas échanger)");
			System.out.println("Si vous avez 5 cartes ou plus, vous devez obligatoirement échanger");

			boolean saisie_correcte = false;

            int choix1 = 0;
            int choix2 = 0;
            int choix3 = 0;

            while (!saisie_correcte) {
				try {
					choix1 = scanner.nextInt();
					if (choix1 == 0 && listeCartes.size()<5) {
						break;
					}
					choix2 = scanner.nextInt();
					choix3 = scanner.nextInt();
					if (choix1 > 0 && choix1 <= listeCartes.size() &&
							choix2 > 0 && choix2 <= listeCartes.size() &&
							choix3 > 0 && choix3 <= listeCartes.size() &&
							choix1 != choix2 && choix1 != choix3 && choix2 != choix3) {
						saisie_correcte = true;
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Saisie incorrecte");
					
				}
				if (!saisie_correcte) {
					System.out.println("Saisie incorrecte, recommencez !");
				}
			}
			if (choix1 > 0) {
				List<CarteRisk> liste_echange = Stream.of(listeCartes.get(choix1 - 1), listeCartes.get(choix2 - 1), listeCartes.get(choix3 - 1))
                        .collect(Collectors.toList());
				if(joueur.echangerCartes(liste_echange) != 0) {
					regimentsADonner += joueur.echangerCartes(liste_echange);
					joueur.enleverCartes(liste_echange);
					plateau.ajouterCarte(liste_echange);
				} else {
					System.out.println("Les cartes que vous avez choisies ne correspondent à aucune combinaison");
				}
			}
			System.out.println("Voulez vous encore échanger ? 0 = non/1 = oui");
			int choix = scanner.nextInt();
			while (choix < 0 || choix > 1) {
				System.out.println("Saisie incorrecte");
				choix = scanner.nextInt();
			}
			if (choix == 0) {
				break;
			}
		}
		distribuerRenforts(joueur, regimentsADonner);
	}

    public void fermerScanner() {
        scanner.close();
    }

}

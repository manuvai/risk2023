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

        ctrl.startAttackPhase();

        testFortification(ctrl);

        // TODO Décommenter la partie du bas lors de tests
        // testGauthier(ctrl);
    }


        /**
         * tester fortification.
         *
         * @return Controler
         */

    private static void testFortification(Controler ctrl) throws Exception {
        //tester distribuer Territoires
        System.out.println("tous les territories: ");
        List<String> ttTerr = new ArrayList<String>();
        for (Continent c : ctrl.plateau.getContinents()) {
            for (Territoire t : c.getTerritories()) {
                ttTerr.add(t.getNom());
            }
        }
        System.out.println(Arrays.toString(ttTerr.toArray()));

        System.out.println("apres distribution, pour chanque joueur: ");
        for (Joueur j : ctrl.joueurs){
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
    	List<CarteRisk>cartes = ctrl.plateau.creerCartes();
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
        System.out.print("Combien de joueurs ? (1 à 5) : ");

        int nbJoueurs = Integer.parseInt(scanner.nextLine());

        while (nbJoueurs <= 0 || nbJoueurs > 5) {
            System.out.print("Veuillez saisir un nombre entre 1 et 5 : ");
            nbJoueurs = Integer.parseInt(scanner.nextLine());
        }

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
            System.out.println("Vous devez donner un nombre cohérent de joueurs");
            return new ArrayList<>();
        }

        List<Joueur> joueurs = new ArrayList<>();
        for (int i = 0; i < nbJoueurs; i++) {
            Joueur joueur = new Joueur();
            String nomJoueur = saisieNomJoueur(i);
            String prenomJoueur = saisiePrenomJoueur(i);

            joueur.setNom(nomJoueur);
            joueur.setPrenom(prenomJoueur);
            joueurs.add(joueur);
        }

        return joueurs;
    }

    private String saisieNomJoueur(int i) {
        System.out.print("Veuillez entrer le nom du joueur ".concat(Integer.toString(i + 1)).concat(" : "));
        String saisie = scanner.nextLine();

        while ("".equals(saisie)) {
            System.out.print("Veuillez saisir un nom valide : ");
            saisie = scanner.nextLine();
        }

        return saisie;
    }

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
    public void passerAuJoueurSuivant() {
        int currentIndex = joueurs.indexOf(actualJoueur);
        if (currentIndex < joueurs.size() - 1) {
            actualJoueur = joueurs.get(currentIndex + 1);
        } else {
            actualJoueur = joueurs.get(0); // Retourner au permier joueur
        }
    }


    // Phase Préparation
    public void initializePlateau() throws Exception {
        plateau = new Plateau();

        int nbJoueurs = getNbJoueurs();
        List<Joueur> joueurs = initialiserJoueurs(nbJoueurs);

        plateau.setJoueurs(joueurs);
        this.joueurs = joueurs;
        this.actualJoueur = joueurs.get(0);


        // Ajouter Cartes
//        List<CarteRisk> cartes = new ArrayList<CarteRisk>();
//        pl.initialisationCarte(cartes);
        plateau.initialiserParties();
//        for (Joueur j : joueurs){
//            System.out.println(j.obtenirTerritoires());
//        }


//        for (Continent c: pl.getContinents()){
//            for (Territoire t : c.getTerritories()){
//                System.out.println(t.getNombreUnites());
//            }
//        }

    }

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
        // 1.Demander joueur -> Fortification ?
        while (true) {
            int resJ = demanderFortification();

            if (resJ != 1 && resJ != 2) {
                throw new Exception("Saissiez numero 1 ou 2 SVP !");
            }

            // 2.Commencer Fortification
            if (resJ == 1) {
                Territoire tS = demanderTerritoireSource();
                Territoire tC = demanderTerritoireCible(tS);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                int nbRegiment = demanderNbRegimentDeplace(tS);
                deplacerRegiment(tS, tC, nbRegiment);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                break;
            } else if (resJ == 2) {
                System.out.println("Vous sautez ce tour de fortificqtion.");
                break;
            }
        }
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
        System.out.println("Est-ce que vous voulez fortifier(1/2) ? (Reponse : 1 - Oui, 2 - Non)");
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
            System.out.println("Veuillez sélectionner un numéro territoireSource :");
            // afficher tous les territoires de joueur
            for (int i = 0; i < getActualJoueur().obtenirTerritoires().size(); i++) {
                int indexT = i + 1;
                System.out.println(indexT + "." + getActualJoueur().obtenirTerritoires().get(i).getNom() + " : " +
                        getActualJoueur().obtenirTerritoires().get(i).getNombreUnites());
            }
            System.out.println("Saisir un numéro de la liste : ");
            int noTerritoireSource = this.scanner.nextInt();

            if (noTerritoireSource > getActualJoueur().obtenirTerritoires().size() || noTerritoireSource <= 0){
                System.err.println("Veuillez sélectionner un numéro territoireSource de la liste");
            } else {
                Territoire territoireSource = getActualJoueur().obtenirTerritoires().get(noTerritoireSource - 1);
                System.out.println("Vous avez choisi un territoireSource : " + territoireSource.getNom());
                return territoireSource;
            }

        }
    }

    /**
     * Demande au joueur de sélectionner un territoire cible pour la phase de fortification.
     *
     * @return Le territoire cible sélectionné par le joueur.
     */
    public Territoire demanderTerritoireCible(Territoire tS) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez sélectionner un numéro de territoireCible :");
            // afficher tous les territoires de joueur
            for (int i = 0; i < getActualJoueur().obtenirTerritoires().size(); i++) {
                int indexT = i + 1;
                if (getActualJoueur().obtenirTerritoires().get(i) == tS){
                    continue;
                } else {
                    System.out.println(indexT + "." + getActualJoueur().obtenirTerritoires().get(i).getNom() +
                            " : " + getActualJoueur().obtenirTerritoires().get(i).getNombreUnites());
                }
            }

            System.out.println("Saisir un numéro de la liste : ");
            int noTerritoireCible = this.scanner.nextInt();

            if (noTerritoireCible > getActualJoueur().obtenirTerritoires().size() || noTerritoireCible <= 0
                    || noTerritoireCible == getActualJoueur().obtenirTerritoires().indexOf(tS) + 1){
                System.err.println("Veuillez sélectionner un numéro territoireCible de la liste");
            } else {
                Territoire TerritoireCible = getActualJoueur().obtenirTerritoires().get(noTerritoireCible - 1);
                System.out.println("Vous avez choisi un territoireCible : " + TerritoireCible.getNom());
                return TerritoireCible;
            }
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
            System.out.println("Vous avez " + tS.getNombreUnites() + " regiments dans " + tS.getNom() + " !");
            System.out.println("Vous pouvez deplacer 0 -> " + (tS.getNombreUnites() - 1) + " régiments");
            System.out.println("Veuillez choisir nb de régiment pour deplacer : ");
            int nbRegiment = this.scanner.nextInt();

            if (nbRegiment > (tS.getNombreUnites() - 1)) {
                System.err.println("Vous n'avez pas assez de regiments");
            } else if (nbRegiment < 0) {
                System.err.println("nbRegiment ne peut pas < 0");
            } else {
                System.out.println("Vous avez déplacé " + nbRegiment + " régiment(s) de " + tS.getNom());
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


    public void startAttackPhase()throws Exception {
        Controler ctrl = new Controler();
        Joueur  attaquant = joueurs.get(0);
        // 1.Demander joueur -> Fortification ?
        while (true) {
            int resJ = demanderAttaque();
            // while (canAttack(attaquant)) {
            System.out.println("Phase d'attaque pour le joueur : " + attaquant.getNom());

            if (resJ != 1 && resJ != 2) {
                throw new Exception("Saissiez numero 1 ou 2 SVP !");
            }

            // 2.Commencer Fortification
            if (resJ == 1) {
                Territoire tS = demanderTerritoireSource();
                Territoire tC = demanderTerritoireCible(tS);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                // Demander au joueur d'obtenir la liste des territoires pour attaquer
                List<Territoire> territoiresAttaquables = getTerritoiresToAttack(attaquant, tS);

                if (territoiresAttaquables.isEmpty()) {
                    System.out.println("Le joueur ne peut plus mener d'attaque. Fin de la phase d'attaque.");
                    return;
                }

                // Demander au joueur le nombre de dés à lancer pour l'attaque
                System.out.print("Saisissez le nombre de dés à lancer : ");
                int desAttaque = scanner.nextInt();

                while (desAttaque > 3) {
                    System.out.print("Veuillez ressaisir le nombre de dés (entre 1 et 3) : ");
                    desAttaque = scanner.nextInt();
                }
                scanner.nextLine(); // Nettoyer la nouvelle ligne.

                // Lancer les dés pour l'attaque
                List<De> resultatsAttaque = attaquant.lancerDes(desAttaque);
                // lancerDes(desAttaque);

                // Déterminer les troupes restantes
                int troupesRestantes = resolveAttack(resultatsAttaque,desAttaque);

                if (troupesRestantes > 0) {
                    // L'attaquant a réussi l'attaque
                    System.out.println("Attaque réussie ! Vous avez conquis le territoire.");
                   // Territoire territoireCible = recupererTerritoire(nomTerritoireCible);
                    // Retirer le territoire du défenseur
                    retirerProprietaireTerritoire(tC);

                    // Ajouter le territoire à l'attaquant
                    attaquant.ajouterTerritoire(tC);

                    // Demander la saisie du nombre de troupes à déplacer
                    System.out.print("Saisissez le nombre de troupes à déplacer : ");
                    int troupesADeplacer = scanner.nextInt();
                    scanner.nextLine(); // Nettoyer la nouvelle ligne.
                    // Déplacer les régiments
                    attaquant.deplacerRegiment(tS, tC, troupesADeplacer);
                } else {
                    System.out.println("Attaque échouée. Le territoire est toujours aux mains du défenseur.");
                }
                int nbRegiment = demanderNbRegimentDeplace(tS);
                deplacerRegiment(tS, tC, nbRegiment);
                System.out.println(tS.getNombreUnites());
                System.out.println(tC.getNombreUnites());
                break;
            } else if (resJ == 2) {
                System.out.println("Vous sautez ce tour d'attaque à la prochaine.");
                break;
            }
        }

    }

    private int demanderAttaque() throws Exception {
        // Demander si joueur va commencer l'étape d'attaque
        System.out.println("Est-ce que vous voulez Attaquer(1/2) ? (Reponse : 1 - Oui, 2 - Non)");
        int resJ = this.scanner.nextInt();
        return resJ;

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


    private int resolveAttack(List<De> resultatsAttaque, int desDefense) {
        // Lancez les dés de défense pour obtenir les résultats du défenseur.
        List<De> resultatsDefense = lancerDesDefense(desDefense);

        // Tri des résultats d'attaque et de défense par ordre décroissant
        List<De> sortedAttackResults = resultatsAttaque.parallelStream()
                        .sorted((o1, o2) -> o1.recupererValeur() - o2.recupererValeur())
                        .collect(Collectors.toList());

        List<De> sortedDefenseResults = resultatsDefense.parallelStream()
                .sorted((o1, o2) -> o1.recupererValeur() - o2.recupererValeur())
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
        int troupesRestantes = resultatsAttaque.size() - pertes;

        return troupesRestantes;
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

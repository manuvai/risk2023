package res.controler;
import res.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Controler {
    private Scanner scanner;
    
    private Plateau plateau;//TODO Besoin d'une instance d'objet plateau pour méthodes

    public Controler() {
        this.scanner = new Scanner(System.in);

    }

    public static void main(String... args) throws Exception {

        Controler ctrl = new Controler();
//        ctrl.startAttackPhase(ctrl.getActualJoueur());

        ctrl.initializePlateau();

    }
    // Phase Préparation
    public void initializePlateau(){
        Plateau pl = new Plateau();

        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        Joueur j3 = new Joueur();
        Joueur j4 = new Joueur();

        List<Joueur> joueurs = new ArrayList<Joueur>();
        joueurs.add(j1);
        joueurs.add(j2);
        joueurs.add(j3);
        joueurs.add(j4);

        pl.setJoueurs(joueurs);


        // Ajouter Cartes
//        List<CarteRisk> cartes = new ArrayList<CarteRisk>();
//        pl.initialisationCarte(cartes);
        pl.initialiserParties();
//        for (Joueur j : joueurs){
//            System.out.println(j.obtenirTerritoires());
//        }


//        for (Continent c: pl.getContinents()){
//            for (Territoire t : c.getTerritories()){
//                System.out.println(t.getNombreUnites());
//            }
//        }
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
     *
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
        joueur.ajouterTerritoire(new Territoire("Affique du nord"));
        joueur.ajouterTerritoire(new Territoire("Affique du sud"));
        joueur.ajouterTerritoire(new Territoire("Europe"));

        joueur.setArmee(new Armee("Bleu"));
        joueur.getArmee().ajouterPion(new Pion("Pion", TypePion.INFANTERIE));
        joueur.getArmee().ajouterPion(new Pion("Chouval", TypePion.CAVALERIE));

        return joueur;
    }
    
    /**
     * Récupère un territoire à partir de son nom.
     *
     * @param nomTerritoire Le nom du territoire à récupérer.
     * @return Le territoire correspondant au nom spécifié, ou null s'il n'existe pas de territoire avec ce nom.
     */
    public Territoire recupererTerritoire(String nomTerritoire) {
        ArrayList<Territoire> territoiresPlateau = plateau.getTerritoires();

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
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez sélectionner un numéro territoireSource :");
            int noTerritoireSource = sc.nextInt();

            String nomTerritoire = "Territoire".concat(Integer.toString(noTerritoireSource));

            Territoire territoireSource = recupererTerritoire(nomTerritoire);
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
   
    public void startAttackPhase(Joueur attaquant ) {
//        while (canAttack(attaquant)) {
            System.out.println("Phase d'attaque pour le joueur : " + attaquant.getNom());
            
         // Demander au joueur de choisir le territoire source
            System.out.print("Saisissez le nom du territoire source : ");
            String nomTerritoireSource = scanner.nextLine();
            Territoire territoireSource = recupererTerritoire(nomTerritoireSource);

            // Demander au joueur d'obtenir la liste des territoires pour attaquer
            List<Territoire> territoiresAttaquables = getTerritoiresToAttack(attaquant, territoireSource);

            if (territoiresAttaquables.isEmpty()) {
                System.out.println("Le joueur ne peut plus mener d'attaque. Fin de la phase d'attaque.");
                return;
            }

            // Demander au joueur de choisir le territoire cible
            System.out.print("Saisissez le nom du territoire cible d'attaque : ");
            String nomTerritoireCible = scanner.nextLine();

            // Demander au joueur le nombre de dés à lancer pour l'attaque
            System.out.print("Saisissez le nombre de dés à lancer : ");
            int desAttaque = scanner.nextInt();
            scanner.nextLine(); // Nettoyer la nouvelle ligne.

            // Lancer les dés pour l'attaque
            List<De> resultatsAttaque =  attaquant.lancerDes(desAttaque);
            		//lancerDes(desAttaque);

            
            // Déterminer les troupes restantes
            int troupesRestantes = resolveAttack(resultatsAttaque);

            if (troupesRestantes > 0) {
                // L'attaquant a réussi l'attaque
                System.out.println("Attaque réussie ! Vous avez conquis le territoire.");
                Territoire territoireCible = recupererTerritoire(nomTerritoireCible);
                // Retirer le territoire du défenseur
                retirerProprietaireTerritoire(territoireCible);

                // Ajouter le territoire à l'attaquant
                attaquant.ajouterTerritoire(territoireCible);

                // Demander la saisie du nombre de troupes à déplacer
                System.out.print("Saisissez le nombre de troupes à déplacer : ");
                int troupesADeplacer = scanner.nextInt();
                scanner.nextLine(); // Nettoyer la nouvelle ligne.
                // Déplacer les régiments
                attaquant.deplacerRegiment(territoireSource, territoireCible, troupesADeplacer);
            } else {
                System.out.println("Attaque échouée. Le territoire est toujours aux mains du défenseur.");
            }
//        }
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
			for (CarteRisk carte : listeCartes) {
				System.out.println("1 : " + carte.getTypePion());
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
				regimentsADonner += joueur.echangerCartes(liste_echange);
				joueur.enleverCartes(liste_echange);
				plateau.ajouterCarte(liste_echange);
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

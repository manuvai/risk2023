package res.model;

import res.model.exceptions.CanNotLaunchNegativeAmountOfDiceException;
import res.model.exceptions.InvalidCardsToTradeException;
import res.model.exceptions.InvalidQuantityDeploymentException;
import res.model.exceptions.WrongOwnerTerritoriesException;

import java.util.*;

/**
 * Cette classe représente un joueur dans un jeu de Risk.
 */

public class Joueur {
    private String nom;
    private String prenom;
    private Armee armee;

    private List<Territoire> territoires = new ArrayList<>();
    private Map<CarteRisk, Integer> cartes = new HashMap<>();

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Armee getArmee() {
        return armee;
    }

    /**
     * Génère un nombre donné de lancer de dés.
     *
     * @param nbDes
     * @return Une liste de dés lancés
     */
    public List<De> lancerDes(int nbDes) {
        if (nbDes < 0) {
            throw new CanNotLaunchNegativeAmountOfDiceException();
        }
        List<De> desLances = new ArrayList<>();

        for (int i = 0; i < nbDes; i++) {
            De de = new De();
            de.lancerDe();

            desLances.add(de);
        }

        return desLances;
    }

     /**
     * Calcule et renvoie le nombre de régiments de renforts censé être reçu.
     *
     * @return Le nombre de régiments de renforts.
     */
    public int calculerNbRenforts(Plateau plateau) {

        int result = territoires.size() / 3;
        int renfortsObtenusPourTerritoires = Math.min(3, result);
        int renfortsObtenusPourContinents = getRenfortsParContinents(plateau);

        return renfortsObtenusPourTerritoires + renfortsObtenusPourContinents;
    }

    /**
     * Obtient la liste des territoires possédés par le joueur.
     *
     * @return La liste des territoires du joueur.
     */

    public List<Territoire> obtenirTerritoires() {
        return territoires;
    }

    /**
     * Ajoute une nouvelle carte à ses cartes possédées
     *
     * @param carte
     */
    public void ajouterCarte(CarteRisk carte) {
        if (Objects.nonNull(carte)) {
            cartes.computeIfAbsent(carte, carteKey -> cartes.put(carteKey, 0));

            cartes.put(carte, cartes.get(carte) + 1);
        }
    }

    /**
     * Détermine les continents complets que le joueur possède sur le plateau.
     *
     * @param plateau Le plateau de jeu.
     * @return Une liste de continents complets possédés par le joueur.
     */
    
    public List<Continent> determinerContinentsComplets(Plateau plateau) {
        List<Continent> continentsComplets = new ArrayList<>();

        for (Continent continent : plateau.getContinents()) {
            boolean complet = true;

            for (Territoire territoire : continent.getTerritories()) {
                if (!isPossessed(territoire)) {
                    complet = false;
                    break;
                }
            }

            if (complet) {
                continentsComplets.add(continent);
            }
        }

        return continentsComplets;
    }

     /**
     * Échange un ensemble de cartes pour obtenir des renforts.
     *
     * @param cartes Les cartes à échanger.
     */

    public void echangerCartes(List<CarteRisk> cartes) {
        if (Objects.isNull(cartes) || cartes.size() < 3) {
            throw new InvalidCardsToTradeException();
        }

        int nbRegimentGained = getNbRegimentGained(cartes);

        for (int i = 0; i < nbRegimentGained; i++) {
            armee.ajouterPion(new Pion("pion", TypePion.INFANTERIE));
        }

    }
   
    /**
     * Déplace un nombre spécifié de régiments d'un territoire source vers un territoire de destination.
     *
     * @param territoireSource       Le territoire source depuis lequel les régiments seront déplacés.
     * @param territoireDestination   Le territoire de destination où les régiments seront déplacés.
     * @param nbRegiments            Le nombre de régiments à déplacer.
     * @throws WrongOwnerTerritoriesException      Si le joueur n'est pas propriétaire des territoires source ou de destination.
     * @throws InvalidQuantityDeploymentException    Si le nombre de régiments à déplacer est invalide (inférieur ou égal à zéro).
     */
    public void deplacerRegiment(Territoire territoireSource, Territoire territoireDestination, int nbRegiments) {
        if (!isPossessed(territoireSource) || !isPossessed(territoireDestination)) {
            throw new WrongOwnerTerritoriesException();
        }

        if (nbRegiments <= 0) {
            throw new InvalidQuantityDeploymentException();
        }

        List<Déploiement> déploiements = territoireSource.retirerRegiment(nbRegiments);

        déploiements.forEach(déploiement -> territoireDestination.ajouterRegiment(
                déploiement.getPionRattachee(),
                déploiement.getQtéDéployée()));
    }

     /**
     * Ajoute un territoire à la liste de territoires possédés par le joueur.
     *
     * @param territoire Le territoire à ajouter.
     */

    public void ajouterTerritoire(Territoire territoire) {
        if (Objects.nonNull(territoire)) {
            territoires.add(territoire);
        }
    }

    /**
     * Calcule le nombre de régiments de renforts à obtenir en échange d'un ensemble de cartes.
     *
     * @param cartes La liste de cartes à échanger.
     * @return Le nombre de régiments de renforts à gagner en fonction des cartes échangées.
     */
    private int getNbRegimentGained(List<CarteRisk> cartes) {
        int countInfanterie = 0;
        int countCavalerie = 0;
        int countArtillerie = 0;

        for (CarteRisk carte : cartes) {
            if (TypePion.INFANTERIE.equals(carte.getTypePion())) {
                countInfanterie++;

            } else if (TypePion.CAVALERIE.equals(carte.getTypePion())) {
                countCavalerie++;

            } else if (TypePion.ARTILLERIE.equals(carte.getTypePion())) {
                countArtillerie++;

            } else if (carte instanceof Joker) {
                countInfanterie++;
                countCavalerie++;
                countArtillerie++;

            }
        }

        int nbRegimentGained = 0;

        if (countInfanterie == 3) {
            nbRegimentGained = 4;

        } else if (countArtillerie == 3) {
            nbRegimentGained = 6;

        } else if (countCavalerie == 3) {
            nbRegimentGained = 8;

        } else if (countInfanterie == countArtillerie && countArtillerie == countCavalerie) {
            nbRegimentGained = 10;

        }
        return nbRegimentGained;
    }

     /**
     * Vérifie si le joueur possède un territoire donné.
     *
     * @param territoire Le territoire à vérifier.
     * @return True si le joueur possède le territoire, sinon False.
     */

    public boolean isPossessed(Territoire territoire) {
        return Objects.nonNull(territoire) && territoires.contains(territoire);
    }

    /**
     * Récupère les renforts disponibles pour les continents possédés par le joueur
     *
     * @param plateau
     * @return
     */
    private int getRenfortsParContinents(Plateau plateau) {
        List<Continent> continentsPossedes = determinerContinentsComplets(plateau);

        int renfortsObtenusPourContinents = 0;

        for (Continent continent : continentsPossedes) {
            renfortsObtenusPourContinents += continent.getBonusTerritories();
        }

        return renfortsObtenusPourContinents;
    }

}

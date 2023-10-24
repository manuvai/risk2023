package res.model;

import res.model.exceptions.CanNotLaunchNegativeAmountOfDiceException;
import res.model.exceptions.InvalidCardsToTradeException;
import res.model.exceptions.InvalidQuantityDeploymentException;
import res.model.exceptions.WrongOwnerTerritoriesException;

import java.util.*;

public class Joueur {
    private String nom;
    private String prenom;
    private Armee armee;

    private List<Territoire> territoires = new ArrayList<>();
    private Map<CarteRisk, Integer> cartes = new HashMap<>();

    /**
     * Génère un nombre donné de lancer de dés.
     *
     * @param nbDes
     * @return
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
     * Calcule et renvoie le nombre de régiments de renforts censé être reçu
     *
     * @return
     */
    public int calculerNbRenforts() {

        int result = territoires.size() / 3;
        int renfortsObtenusPourTerritoires = Math.min(3, result);
        int renfortsObtenusPourContinents = 0; // TODO Ajouter la gestion pour les continents

        return renfortsObtenusPourTerritoires + renfortsObtenusPourContinents;
    }

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
    
    public List<Continent> determinerContinentsComplets(Plateau plateau) {
        List<Continent> continentsComplets = new ArrayList<>();

        for (Continent continent : plateau.getContinents()) {
            boolean complet = true;

            for (Territoire territoire : continent.getTerritoires()) {
                if (!posseTerritoire(territoire)) {
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

    public boolean posseTerritoire(Territoire territoire) {
        // Vérifier si le joueur possède le territoire
        return territoires.contains(territoire);
    }

    public void echangerCartes(List<CarteRisk> cartes) {
        if (Objects.isNull(cartes) || cartes.size() < 3) {
            throw new InvalidCardsToTradeException();
        }

        int nbRegimentGained = getNbRegimentGained(cartes);

        for (int i = 0; i < nbRegimentGained; i++) {
            armee.ajouterPion(new Pion("pion", TypePion.INFANTERIE));
        }

    }
    public void attaquer(Territoire territoireSource, Territoire territoireDestination, int nbRegiments) {

    }

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

    public void ajouterTerritoire(Territoire territoire) {
        if (Objects.nonNull(territoire)) {
            territoires.add(territoire);
        }
    }

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

    private boolean isPossessed(Territoire territoire) {
        return Objects.nonNull(territoire) && territoires.contains(territoire);
    }

}

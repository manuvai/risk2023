package res.model;

import res.model.loader.MainLoader;

import java.util.*;

/**
     * Cette classe représente le plateau de jeu pour un jeu de Risk.
     */


public class Plateau {
    private List<Continent> continents;
    private List<Joueur> joueurs;
    private List<CarteRisk> cartesPille;
    private ArrayList<Territoire> territoires;


    /**
     * Initialise la partie en distribuant les territoires aux joueurs.
     */

    public void initialiserParties() {
        MainLoader ml = new MainLoader();
        continents = ml.getContinentList();

        distribuerCartes(this.joueurs);
        initialisationRegiment();
    }

    /**
     * Initialiser les régiments de chaque joueurs dans ses territoires
     *
     * @Author HP
     */

    public void initialisationRegiment() {
        if (joueurs.size() == 3){ // 35 pions -> infanterie
            placementAuto(35);
        } else if (joueurs.size() == 4){ // 30 pions -> infanterie
            placementAuto(30);
        } else if (joueurs.size() == 5) { // 25 pions -> infanterie
            placementAuto(25);
        }
    }

    /**
     * Placer automatiquement des armées sur les territoires du joueur,
     *  en veillant à ce que chaque territoire dispose d'au moins une armée.
     *
     * @param nbRegiment Nombre total de régiment à placer
     */

    public void placementAuto(int nbRegiment){
        // Tout d'abord, mettre un pion dans tous les territoires.
        for (Joueur j : joueurs){
            for (Territoire t : j.obtenirTerritoires()){
                Pion p = new Pion("pionDebut",TypePion.INFANTERIE);
                t.ajouterRegiment(p,1);
            }
        }

        //Placement aléatoire de pions dans les territoires restants
        Random rand = new Random();
        for (Joueur j : joueurs){
            List<Territoire> listeTerritoire = j.obtenirTerritoires();
            int nbTourPlacement = nbRegiment - listeTerritoire.size();
            for (int i = 0; i < nbTourPlacement; i++){
                int randomIndex = rand.nextInt(listeTerritoire.size());
                Pion p = new Pion("pionPlaceAuto",TypePion.INFANTERIE);
                listeTerritoire.get(randomIndex).ajouterRegiment(p,1);
            }
        }


    }

    public void initialisationCarte(ArrayList<CarteRisk> cartes){
        this.cartesPille = cartes;
    }


     /**
     * Distribue les territoires aux joueurs de manière équilibrée.
     *
     * @param js La liste des joueurs à qui distribuer les territoires.
     */

    //Création d'une liste de cartes
    public List<CarteRisk> creerCartes(){
    	ArrayList<Territoire> listeTerritoire = new ArrayList<Territoire>();
        for (Continent c : continents) {
            listeTerritoire.addAll(c.getTerritories());
        }
        ArrayList<CarteRisk>cartes = new ArrayList<CarteRisk>();
        
        //Création d'une liste regroupant les types de régiments pour les associer aux cartes territoire de manière aléatoire
        ArrayList<TypePion>type = new ArrayList<TypePion>();
        for (int i = 0; i < 14; i++) {
        	type.add(TypePion.INFANTERIE);
        }
        for (int i = 0; i < 14; i++) {
        	type.add(TypePion.CAVALERIE);
        }
        for (int i = 0; i < 14; i++) {
        	type.add(TypePion.ARTILLERIE);
        }
        //Pour chaque territoire existant, on crée une carte représentant le territoire et un type de régiment aléatoirement
        for (Territoire territoire : listeTerritoire) {
        	
        	int random = (int)(Math.random()*type.size());
        	//On crée la carte qui associe un territoire à un type de pion, puis on enlève le type de pion des possibilités car on estime que sur 42 cartes, on aura 14 de chaque type
			CarteRisk carte = new CarteRisk(type.get(random), territoire);
			type.remove(random);
			cartes.add(carte);
		}
        
        //Ensuite on ajoute les deux jokers
        Joker joker = new Joker(null, null);
        cartes.add(joker);
        Joker joker = new Joker(null, null);
        cartes.add(joker);
        Collections.shuffle(cartes);
        return cartes;
    }
    
    // pluetôt distribuerTerritoires
    public void distribuerCartes(List<Joueur> js) {

        // 1. Créez une liste de territoires
        List<Territoire> listeTtTerritoire = new ArrayList<Territoire>();
        for (Continent c : continents) {
            listeTtTerritoire.addAll(c.getTerritories());
        }

        // 2. Pour chaque joueur, get un territoire de la liste et remove ce territoire de cette liste
        while (listeTtTerritoire.size() != 0) {
            for (Joueur j : joueurs) {
                if (listeTtTerritoire.size() == 0){
                    break;
                } else {
                    Random rand = new Random();
                    int randomIndex = rand.nextInt(listeTtTerritoire.size());
                    j.ajouterTerritoire(listeTtTerritoire.get(randomIndex)); // <- il faut l'ajouter dans Joueur !!!
                    listeTtTerritoire.remove(randomIndex);
                }
            }
        }
    }

    /**
     * Récupère le propriétaire du territoire donné en paramètre
     *
     * @param territoire
     * @return
     */
    public Joueur getProprietaire(Territoire territoire) {
        if (Objects.nonNull(territoire)) {
            for (Joueur j : joueurs) {
                if (j.isPossessed(territoire)) {
                    return j;
                }
            }
        }

        return null;
    }


    /**
     * Obtient la liste des continents sur le plateau.
     *
     * @return La liste des continents sur le plateau.
     */

    public List<Continent> getContinents() {
        return continents;
    }

     /**
     * Obtient la carte du jeu associée à chaque quantité de cartes piochées.
     *
     * @return Une carte associée à chaque quantité de cartes piochées.
     */

    public List<CarteRisk> getCartesPille() {
        return cartesPille;
    }


     /**
     * Obtient la liste des joueurs participants à la partie.
     *
     * @return La liste des joueurs participants à la partie.
     */

    public List<Joueur> getJoueurs() {
        return this.joueurs;
    }

    /**
     * initialiser la liste de joueurs
     * @param joueurs liste de joueur
     */
    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Enlève un joueur de la partie.
     *
     * @param j Le joueur à enlever de la partie.
     */
    
    public void enleverJoueur(Joueur j) {
        this.joueurs.remove(j);
    }

    public void ajouterCarte(List<CarteRisk> listeEchange) {
        if (Objects.nonNull(listeEchange)) {
            cartesPille.addAll(listeEchange);
        }
    }

    /**
     * Obtient la liste des territoires sur le plateau.
     *
     * @return La liste des territoires sur le plateau.
     */
    public ArrayList<Territoire> getTerritoires() {
        return territoires;
    }

}

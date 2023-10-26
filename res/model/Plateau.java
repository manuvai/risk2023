package res.model;

import java.util.*;

/**
     * Cette classe représente le plateau de jeu pour un jeu de Risk.
     */


public class Plateau {
    private ArrayList<Continent> continents;
    private ArrayList<Joueur> joueurs;
    private List<CarteRisk> cartesPille;

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
    public void distribuerCartes(ArrayList<Joueur> js) {

        // Fonctionne:
        // Créez d'abord une liste de territoires et,
        // pour chaque joueur de la liste des joueurs, prenez le territoire correspondant dans la liste
        // et retirez ce territoire de la liste jusqu'à ce que celle-ci soit vide.
        // Ensuite, le joueur place un Pion dans sa liste de territoires,
        // pour chaque territoire, et s'il reste des Pions, le joueur choisit où les placer.

        // 1. Créez une liste de territoires
        ArrayList<Territoire> listeTtTerritoire = new ArrayList<Territoire>();
        for (Continent c : continents) {
            listeTtTerritoire.addAll(c.getTerritories());
        }

        // 2. Pour chaque joueur, get un territoire de la liste et remove ce territoire de cette liste
        while (!listeTtTerritoire.isEmpty()) {
            for (Joueur j : joueurs) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(listeTtTerritoire.size());
                j.ajouterTerritoire(listeTtTerritoire.get(randomIndex)); // <- il faut l'ajouter dans Joueur !!!
                listeTtTerritoire.remove(randomIndex);
            }
        }

    }


    /**
     * Obtient la liste des continents sur le plateau.
     *
     * @return La liste des continents sur le plateau.
     */

    public ArrayList<Continent> getContinents() {
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
     * Initialise la partie en distribuant les territoires aux joueurs.
     */

    public void initialiserParties() {

        distribuerCartes(this.joueurs);
        // TODO: 23/10/2023
    }

     /**
     * Obtient la liste des joueurs participants à la partie.
     *
     * @return La liste des joueurs participants à la partie.
     */

    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
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
}

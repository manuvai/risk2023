package res.model;

import res.model.exceptions.CanNotLaunchNegativeAmountOfDiceException;

import java.util.*;

public class Joueur {
    private String nom;
    private String prenom;
    private Armee armee;

    private Map<Carte, Integer> cartes = new HashMap<>();

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
        // TODO Ajouter l'implémentation une fois la classe Territoire implémentée

        return 0;
    }

    /**
     * Ajoute une nouvelle carte à ses cartes possédées
     *
     * @param carte
     */
    public void ajouterCarte(Carte carte) {
        if (Objects.nonNull(carte)) {
            cartes.computeIfAbsent(carte, carteKey -> cartes.put(carteKey, 0));

            cartes.put(carte, cartes.get(carte) + 1);
        }
    }



public void afficherInformations() {
        System.out.println("Nom du joueur : " + nom);
        System.out.println("Prénom du joueur : " + prenom);
        System.out.println("Armée : " + armee.getNom()); 
        System.out.println("Cartes du joueur :");

        for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
            Carte carte = entry.getKey();
            int quantite = entry.getValue();
            System.out.println(" - " + quantite + "x " + carte.getNom()); 
        }
    }

 }



package res.model;

import res.model.exceptions.DiceValueOutOfBoundsException;

import java.util.Random;

    /**
     * Cette classe représente un dé utilisé dans un jeu.
     */

public class De {
    private int valeur;

    private static final int MAX_VALEUR = 6;
    private static final int MIN_VALEUR = 1;

    
    /**
     * Récupère la valeur actuelle du dé.
     *
     * @return La valeur actuelle du dé.
     */

    public int recupererValeur() {
        return valeur;
    }

    /**
     * Lance le dé pour obtenir une nouvelle valeur aléatoire entre 1 et 6.
     */

    public void lancerDe() {
        Random random = new Random();

        int nouvelleValeur = random.nextInt(MIN_VALEUR, MAX_VALEUR + 1);

        setValeur(nouvelleValeur);
    }

    /**
     * Définit la valeur du dé.
     *
     * @param inValeur La nouvelle valeur du dé.
     * @throws DiceValueOutOfBoundsException Si la valeur n'est pas dans la plage valide.
     */
    
    public void setValeur(int inValeur) {
        if (inValeur < MIN_VALEUR || inValeur > MAX_VALEUR) {
            throw new DiceValueOutOfBoundsException();
        }

        valeur = inValeur;
    }
}

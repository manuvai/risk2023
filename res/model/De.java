package res.model;

import res.model.exceptions.DiceValueOutOfBoundsException;

import java.util.Random;

public class De {
    private int valeur;

    private static final int MAX_VALEUR = 6;
    private static final int MIN_VALEUR = 1;

    public int recupererValeur() {
        return valeur;
    }

    public void lancerDe() {
        Random random = new Random();

        int nouvelleValeur = random.nextInt(MIN_VALEUR, MAX_VALEUR + 1);

        setValeur(nouvelleValeur);
    }

    public void setValeur(int inValeur) {
        if (inValeur < MIN_VALEUR || inValeur > MAX_VALEUR) {
            throw new DiceValueOutOfBoundsException();
        }

        valeur = inValeur;
    }
}

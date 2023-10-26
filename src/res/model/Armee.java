package res.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    /**
     * Cette classe représente une armée dans un jeu.
     */

public class Armee {
    private String couleur;
    private List<Pion> pions = new ArrayList<>();

     /**
     * Constructeur de la classe Armée.
     *
     * @param inCouleur La couleur de l'armée.
     */
    public Armee(String inCouleur) {
        couleur = inCouleur;
    }

    /**
     * Ajoute un pion à l'armée.
     *
     * @param pion Le pion à ajouter.
     */

    public void ajouterPion(Pion pion) {
        if (Objects.nonNull(pion)) {
            pions.add(pion);
        }
    }

    /**
     * Récupère la couleur de l'armée
     * '
     * @return
     */
    public String getCouleur() {
        return couleur;

    }

    /**
     * Retire un pion de l'armée.
     *
     * @param pion Le pion à retirer.
     */
    
    public void retirerPion(Pion pion) {
        if (Objects.nonNull(pion)) {
            pions.remove(pion);
        }
    }
}

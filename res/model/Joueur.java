package res.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Joueur {
    private String nom;
    private String prenom;

    private List<Carte> cartes = new ArrayList<>();

    public void ajouterCarte(Carte carte) {
        if (Objects.nonNull(carte)) {
            cartes.add(carte);
        }
    }

    public void echangerCarte() {
        // TODO Auto-generated constructor stub
    }

    public void distribuerRegiment(int nbRegiment) {
        // TODO Auto-generated constructor stub
    }

    public void lancesLesDes(int nbDes) {
        // TODO Auto-generated constructor stub
    }

    public void choisirNombreDeDesPourAttaque(int nbDesPourAttaque) {
        // TODO Auto-generated constructor stub
    }

    public int getNbRegiments() {
        return 0; // TODO Ajouter ensuite le calcul avec les territoires
    }

    public void modifierNbRegiment() {
        // TODO
    }

    public List<Object> chercherTerritoire() {
        // TODO Modifier le type de la liste à Territoire une fois complétée

        return new ArrayList<>();
    }

    public void choixCouleur(String inCouleur) {
        // TODO Implémenter
    }

}

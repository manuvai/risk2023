package res.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Armee {
    private String couleur;
    private List<Pion> pions = new ArrayList<>();

    public Armee(String inCouleur) {
        couleur = inCouleur;
    }

    public void ajouterPion(Pion pion) {
        if (Objects.nonNull(pion)) {
            pions.add(pion);
        }
    }

    public void retirerPion(Pion pion) {
        if (Objects.nonNull(pion)) {
            pions.remove(pion);
        }
    }
}

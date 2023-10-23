package res.model;

import java.util.*;

public class Joueur {
    private String nom;
    private String prenom;

    private Map<Carte, Integer> cartes = new HashMap<>();

    public List<De> lancerDes(int nbDes) {
        List<De> desLances = new ArrayList<>();

        for (int i = 0; i < nbDes; i++) {
            De de = new De();
            de.lancerDe();

            desLances.add(de);
        }

        return desLances;
    }

    public int calculerNbRenforts() {
        return 0;
    }

    public void ajouterCarte(Carte carte) {
        if (Objects.nonNull(carte)) {
            cartes.computeIfAbsent(carte, carteKey -> cartes.put(carteKey, 0));

            cartes.put(carte, cartes.get(carte) + 1);
        }
    }

}

package res.model;

public class CarteRisk {
    private TypePion typePion;  
    private Territoire territoire;  


    public CarteRisk(TypePion typePion, Territoire territoire) {
        this.typePion = typePion;
        this.territoire = territoire;
    }


    public TypePion getTypePion() {
        return typePion;
    }


    public Territoire getTerritoire() {
        return territoire;
    }
}

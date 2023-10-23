package res.model;

import java.util.Objects;

public class Pion{
	private String nomPion;
	private TypePion typePion;
	
	public Pion(String nomPion, TypePion typePion) {
		this.nomPion = nomPion;
		this.typePion = typePion;
	}
	
	public String getNomPion() {
		return this.nomPion;
	}
	
	public TypePion getTypePion() {
		return this.typePion;
	}
	
	public int obtenirNbRegiment() {
		int nbRegiment;
		switch (typePion) {
			case INFANTERIE:
				nbRegiment = 1;
				break;
			case CAVALERIE:
				nbRegiment = 5;
				break;
			case ARTILLERIE:
				nbRegiment = 10;
				break;
			default:
				nbRegiment = 0;
				break;
		}

		return nbRegiment;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pion pion)) return false;
		return Objects.equals(getNomPion(), pion.getNomPion()) &&
				getTypePion() == pion.getTypePion();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNomPion(), getTypePion());
	}
}
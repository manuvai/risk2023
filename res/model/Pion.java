package res.model;

import java.util.ArrayList;

public class Pion{
	private String nomPion;
	private TypePion typePion;
	
	public Pion(String nomPion, TypePion typePion) {
		this.nomPion = nomPion;
		this.typePion = typePion;
	}
	
	public TypePion getNomPion() {
		return this.nomPion;
	}
	
	public TypePion getTypePion() {
		return this.typePion;
	}
	
	public int obtenirNbRegiment() {
		switch (typePion) {
		case INFANTERIE:
			return 1;
		case CAVALERIE:
			return 5;
		case ARTILLERIE:
			return 10;
		default:
			return 0;
			break;
		}
	}
}
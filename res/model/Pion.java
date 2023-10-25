package res.model;

import java.util.Objects;

	/**
	 * Représente un pion utilisé dans le jeu Risk. Chaque pion a un nom et un type.
	 */
public class Pion{
	private String nomPion;
	private TypePion typePion;
	

	 /**
     * Crée une instance de pion avec un nom et un type spécifiés.
     *
     * @param nomPion  Le nom du pion.
     * @param typePion Le type du pion (INFANTERIE, CAVALERIE, ARTILLERIE).
     */

	public Pion(String nomPion, TypePion typePion) {
		this.nomPion = nomPion;
		this.typePion = typePion;
	}
	
	 /**
     * Récupère le nom du pion.
     *
     * @return Le nom du pion.
     */

	public String getNomPion() {
		return this.nomPion;
	}
	
	/**
     * Récupère le type du pion.
     *
     * @return Le type du pion.
     */

	public TypePion getTypePion() {
		return this.typePion;
	}
	
	/**
     * Obtient le nombre de régiments associés à ce pion en fonction de son type.
     *
     * @return Le nombre de régiments associés au pion.
     */

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

	 /**
     * Compare cet objet Pion à un autre objet pour vérifier s'ils sont égaux.
     *
     * @param o L'objet à comparer avec cet objet Pion.
     * @return true si les objets sont égaux, sinon false.
     */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pion)) return false;

		Pion pion = (Pion) o;
		return Objects.equals(getNomPion(), pion.getNomPion()) &&
				getTypePion() == pion.getTypePion();
	}

	  /**
     * Calcule un code de hachage pour cet objet Pion.
     *
     * @return Le code de hachage calculé pour l'objet Pion.
     */

	@Override
	public int hashCode() {
		return Objects.hash(getNomPion(), getTypePion());
	}
}
package res.model;

/**
 * Représente les types de pions utilisés dans le jeu Risk.
 * @author LALUYAUX Gauthier
 * @since 21/10/23
 */

public enum TypePion {

	 /**
     * Type de pion CAVALERIE.
     */
	CAVALERIE,

	/**
     * Type de pion INFANTERIE.
     */

	INFANTERIE,

	/**
     * Type de pion ARTILLERIE.
     */

	ARTILLERIE;



	 /**
     * Compare deux types de pion pour déterminer l'ordre de priorité.
     *
     * @param a Le premier type de pion à comparer.
     * @param b Le deuxième type de pion à comparer.
     * @return Un entier négatif si a a une priorité plus basse que b, zéro s'ils sont égaux, ou un entier positif si a a une priorité plus élevée que b.
     */
	
	public static int compare(TypePion a, TypePion b) {
		return Math.max(-1, Math.min(0, points(a) - points(b)));
	}

	public static int points(TypePion a) {
		return INFANTERIE.equals(a)
				? 3
				: CAVALERIE.equals(a)
					? 2
					: 1;
	}
}

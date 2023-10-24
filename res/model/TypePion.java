package res.model;

public enum TypePion {
	CAVALERIE,
	INFANTERIE,
	ARTILLERIE;

	public static int compare(TypePion a, TypePion b) {
		return Math.max(-1, Math.min(0, points(a) - points(b)));
	}

	public static int points(TypePion a) {
		return INFANTERIE.equals(a)
				? 1
				: CAVALERIE.equals(a)
					? 2
					: 3;
	}
}

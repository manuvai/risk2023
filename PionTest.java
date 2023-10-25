package tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class PionTest {

	@Test
	public void retournerTypePion() {
		Pion p = new Pion("Testeur cavalerie", TypePion.CAVALERIE);
		assertEquals(TypePion.CAVALERIE, p.getTypePion());
	}
}

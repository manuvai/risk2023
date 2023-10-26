package tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import res.model.Pion;
import res.model.TypePion;

public class PionTest {

	@Test
	public void retournerTypePion() {
		res.model.Pion p = new Pion("Testeur cavalerie", TypePion.CAVALERIE);
		assertEquals(TypePion.CAVALERIE, p.getTypePion());
	}
}

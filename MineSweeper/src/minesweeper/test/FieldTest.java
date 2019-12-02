package minesweeper.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import minesweeper.core.Field;
import minesweeper.core.Mine;

class FieldTest {
	private Field field;

	@BeforeEach
	void init() {
		field = new Field(10, 10, 10);
	}

	@Test
	@DisplayName("pole ma obsahovat spravny pocet min")
	void polemaobsahovatspravnypocetmin() {
		int mineCount = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int col = 0; col < field.getColumnCount(); col++) {
				if (field.getTile(row, col) instanceof Mine) {
					mineCount++;
				}
			}
		}
		assertEquals(10, mineCount, "there should be 10 mines in the field");
	}
}

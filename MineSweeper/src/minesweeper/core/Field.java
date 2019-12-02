package minesweeper.core;

import java.util.Random;

import minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate();
	}

	/**
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}

			if (isSolved()) {
				state = GameState.SOLVED;
				return;
			}
		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */

//	 Implementujte metódu void markTile(int row, int column) v triede Field.
//	 Metóda slúži na označenie/odznačenie dlaždice špecifikovanej riadkom a stĺpcom.
//	 V prípade, že je dlaždica neodkrytá (CLOSED) bude jej stav zmenený na označená (MARKED).
//	 Ak je dlaždica označená (MARKED) bude jej stav zmenený na neodkrytá (CLOSED).
//	 Riadky a stĺpce sú číslované od 0.

	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	/**
	 * Generates playing field.
	 */

//	Úloha: Implementujte metódu void generate() v triede Field tak, 
//	aby v hernom poli tiles náhodne rozložila míny, 
//	pričom počet mín, ktoré majú byť rozložené je daný premennou mineCount.

//	Poznámka: Pre náhodné generovanie čísel 
//	vytvorte objekt triedy java.util.Random (použite metódu objektu int nextInt(int n)).

//	Poznámka: Na náhodne zvolené súradnice v hernom poli vložte dlaždicu typu Mine ak pole na daných 
//	súradniciach už neobsahuje mínu (tiles[row][column] == null). 
//	Postup opakujte pokiaľ nebude uložený požadovaný počet mín. 
//	Po realizácii tejto úlohy bude hracia plocha obsahovať iba dlaždice typu mína.

	private void generate() {
		Random random = new Random();
		int sm = 0;
		if (mineCount >= rowCount * columnCount) {
			throw new UnsupportedOperationException("prilis vela min");
		}

		while (sm < mineCount) {
			int row = random.nextInt(rowCount);
			int col = random.nextInt(columnCount);
			if (tiles[row][col] == null) {
				tiles[row][col] = new Mine();
				sm++;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Clue(countAdjacentMines(i, j));
				}
			}

		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */

//	Pri každom odkrytí herného poľa používateľom sa testuje možnosť ukončenia hry (úspešne alebo neúspešne).
//	Úloha: Implementujte metódu boolean isSolved() definovanú v triede Field, ktorá určuje úspešné odkrytie hracieho poľa.
//	Hra je úspešne ukončená ak platí počet všetkých dlaždíc - počet odokrytých dlaždíc = počet mín. 
//	Z uvedeného vyplýva, že hra bude ukončená vtedy, ak budú odokryté všetky dlaždice bez mín. 
//	Pre určenie počtu odkrytých dlaždíc definujte súkromnú metódu int getNumberOf(Tile.State state), 
//	ktorá vráti počet dlaždíc v danom stave.

	private boolean isSolved() {

		boolean ds = (rowCount * columnCount) - getNumberOf(State.OPEN) == mineCount;
		return ds;

//		throw new UnsupportedOperationException("Method isSolved not yet implemented");
	}

	public int getNumberOf(Tile.State state) {// kolko je akych dlazdic
		int temp = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getState() == state) {
					temp++;
				}
			}
		}
		return temp;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public GameState getState() {
		return state;
	}

//Pridajte metódu Tile getTile(int row, int column) do triedy Field, ktorá vráti dlaždicu podľa 
//	zadaného riadku a stĺpca. Riadky a stĺpce sú číslované od 0.

	public Tile getTile(int row, int column) { // <--
		return tiles[row][column];
	}
}

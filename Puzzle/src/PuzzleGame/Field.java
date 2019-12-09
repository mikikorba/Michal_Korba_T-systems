package PuzzleGame;

import java.util.Random;

public class Field {
	private final int rowCount;

	private final int columnCount;

	private final Tile[][] tiles;

	private GameState state = GameState.PLAYING;

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		generate();
	}

	public Tile moveTile(int row, int column) {
		Tile t = tiles[getRowCount()][getColumnCount()];
		return t;

	}
//	public int getLength () {
//		return tiles.length;
//	}

	private void generate() {
//		Random random = new Random();
		int k = 1;
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				if (k < getRowCount() * getColumnCount()) {
					tiles[i][j] = new Tile(k);
					k++;
				} else {
					tiles[i][j] = new Tile(-1);
				}
			}
		}
	}

	public boolean isSolved() {

		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				Tile tile = tiles[row][column];
				if (tile.getValue()  != -1 && tile.getValue() != index)
					return false;
				index++;
			}
		}
		return true;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public GameState getState() {
		return state;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}

}

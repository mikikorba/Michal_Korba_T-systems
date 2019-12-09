package PuzzleGame;

import java.util.Random;
import java.util.Scanner;

public class ConsoleUI {

	private Field field;

	public ConsoleUI(Field field) {
		this.field = field;
	}

	public void newGameStarted(Field field) {
		this.field = field;
		mixed();
		do {
			show();
			processZadania();
		} while (!field.isSolved());
		
		show();
		System.out.println("You won!");
	}

	private void show() {

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int col = 0; col < field.getColumnCount(); col++) {
				Tile tile = field.getTile(row, col);
				if (tile.getValue() != -1) {
					System.out.printf("[" + tile.getValue()+"]");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
		}
	}

	private void processZadania() {

		int row = -2;
		int col = -2;

		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				int tile = field.getTile(i, j).getValue();
				if (tile == -1) {
					row = i;
					col = j;
				}
			}
		}
		if (row == -2 || col == -2) {
			throw new UnsupportedOperationException("shmejd");
		}

//		System.out.println("\nZadaj cislo kamena. Na posun mas tieto moznosti:");
//
//		try {
//			int tile1 = field.getTile(row + 1, col).getValue();
//			System.out.print(tile1 + " tile1 (down) = \t(field.getTile(" + row + 1 + ", " + col + "))\n");
//		} catch (Exception e) {
//		}
//		try {
//			int tile2 = field.getTile(row - 1, col).getValue();
//			System.out.print(tile2 + " tile2 (up) = \t(field.getTile(" + (row - 1) + ", " + col + "))\n");
//		} catch (Exception e) {
//		}
//		try {
//			int tile3 = field.getTile(row, col + 1).getValue();
//			System.out.print(tile3 + " tile3 (right) = \t(field.getTile(" + row + ", " + col + 1 + "))\n");
//		} catch (Exception e) {
//		}
//		try {
//			int tile4 = field.getTile(row, col - 1).getValue();
//			System.out.print(tile4 + " tile4 (left) = \t(field.getTile(" + row + ", " + (col - 1) + "))\n");
//		} catch (Exception e) {
//		}
//		System.out.println();

		Scanner scanner = new Scanner(System.in);
		int scan = -1;
		try {
			scan = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.err.println("Invalid tile number!");
		}
		movable(row, col, scan);
	}

	private void mixed() {
		for (int re = 0; re < 20000; re++) {
			int row = -2;
			int col = -2;

			for (int i = 0; i < field.getRowCount(); i++) {
				for (int j = 0; j < field.getColumnCount(); j++) {
					int tile = field.getTile(i, j).getValue();
					if (tile == -1) {
						row = i;
						col = j;
					}
				}
			}
			if (row == -2 || col == -2) {
				throw new UnsupportedOperationException(" ");
			}
			Random rand;
			int max = (field.getRowCount() * field.getRowCount());
			rand = new Random();

			int scan = rand.nextInt(max)+1;
			movable(row, col, scan);
		}

	}

	private void movable(int row, int col, int scan) {

		if (row + 1 < field.getRowCount() && (scan == field.getTile(row + 1, col).getValue())) {
			field.getTile(row, col).setValue(scan);
			field.getTile(row + 1, col).setValue(-1);
			row++;
		} else if (row >= 1 && scan == field.getTile(row - 1, col).getValue()) {
			field.getTile(row, col).setValue(scan);
			field.getTile(row - 1, col).setValue(-1);
			row--;
		} else if (col + 1 < field.getColumnCount() && (scan == field.getTile(row, col + 1).getValue())) {
			field.getTile(row, col).setValue(scan);
			field.getTile(row, col + 1).setValue(-1);
			col++;
		} else if (col >= 1 && (scan == field.getTile(row, col - 1).getValue())) {
			field.getTile(row, col).setValue(scan);
			field.getTile(row, col - 1).setValue(-1);
			col--;
		}
	}
}

package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.UserInterface;
import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			processInput();
//			throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
		} while (true);
	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		System.out.print("  ");
		for (int i = 1; i <= field.getColumnCount(); i++) {
			if (i < 10) {
				System.out.print(i);
				System.out.print("  ");
			} else {
				System.out.print(i);
				System.out.print(" ");
			}
		}
		System.out.println();
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.printf("%c ", 'A' + row);
			for (int col = 0; col < field.getColumnCount(); col++) {
				Tile tile = field.getTile(row, col);
				if (tile.getState() == Tile.State.OPEN) {
					if (tile instanceof Mine) {
						System.out.printf("X  ");
					} else if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
						System.out.print("  ");
					}
				} else if (tile.getState() == Tile.State.CLOSED) {
					System.out.printf("-  ");
				} else if (tile.getState() == Tile.State.MARKED) {
					System.out.printf("M  ");
				}
			}
			System.out.println();
		}

//        throw new UnsupportedOperationException("Method update not yet implemented");
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {

//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Urci od A do ");
//		System.out.printf("%c ", 'A' + field.getRowCount() - 1);
//		System.out.print(" suradnicu: ");
//		String readX = scanner.nextLine();
//		char readX2 = readX.charAt(0);
//		int readX3 = readX2 - 'A' + 1;

//		try {
//			if (field.getRowCount() > Integer.parseInt(readX)) {
//			}
//		} catch (NumberFormatException e) {
//			System.out.print("este raz");
//		}

//		System.out.print("Urci od 1 do ");
//		System.out.print(field.getColumnCount());
//		System.out.print(" suradnicu: ");
//		String readY = scanner.nextLine();

			Scanner scanner = new Scanner(System.in);
			Pattern pattern = Pattern.compile("([A-I])([0-8])");
			Matcher matcher = pattern.matcher(scanner.nextLine().toUpperCase());
			if (matcher.matches()) {
				String rowName = matcher.group(1);
			}
			String readX = (matcher.group(1));
			char readX2 = readX.charAt(0);
			int readX3 = readX2 - 'A' + 1;
			int readY = Integer.parseInt(matcher.group(2));
			field.openTile(readX3 - 1, readY - 1);

//		throw new UnsupportedOperationException("Method processInput not yet implemented");
	}
}

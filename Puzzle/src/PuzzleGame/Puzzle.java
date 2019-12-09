package PuzzleGame;

public class Puzzle {

	private Puzzle() {

		Field field = new Field(2, 2);
		ConsoleUI play = new ConsoleUI(field);
		play.newGameStarted(field);
	}

	void newGameStarted(Field field) {
	}

	public static void main(String[] args) {
		new Puzzle();
	}

}

package PuzzleGame;

public class Puzzle {

	private Puzzle() {

		Field field = new Field(4, 2);
		ConsoleUI play = new ConsoleUI(field);
		play.newGameStarted(field);
	}

	void newGameStarted(Field field) {
	}
//test for git
	public static void main(String[] args) {
		new Puzzle();
	}

}

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random rand;
		rand = new Random();
		int max = 20;
		int scan = 0;
		String t = "Try again";
		char scan2 = ' ';
		do {
			int hadaneCislo = rand.nextInt(max) + 1;
			System.out.println("Set number from 1 to " + max + ": ");
			do {
				try {
					scan = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.err.println("Invalid tile number!");
					System.err.println(t);
					continue;
				}
				if (hadaneCislo < scan) {
					System.out.println("Number is lower");
					System.err.println(t);
				} else if (hadaneCislo > scan) {
					System.out.println("Number is higher");
					System.err.println(t);
				} else if (hadaneCislo == scan) {
					System.err.println("WIN !!!");
					System.out.println("------------------------------");
					System.out.println();
				}
			} while (scan != hadaneCislo);
			do {
				System.out.println("Play again?");
				System.out.println("Y or N");
				System.out.println();
				try {
					scan2 = scanner.nextLine().toUpperCase().charAt(0);
				} catch (Exception e) {
					System.err.println("Invalid input!");
					System.out.println(scan2);
					System.err.println(t);
					e.printStackTrace();
				}
			} while (!(scan2 == 'Y' || scan2 == 'N'));
		} while (scan2 != 'N');
		System.err.println("Thanks for playing :) ");
	}
}
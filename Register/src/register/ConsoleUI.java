package register;

import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** Register of persons. */
	private Register register;

	private Scanner scanner = new Scanner(System.in);

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() {
		try {
			register.load();
		} catch (RegisterException e) {
			System.err.println("Could not load register");
		}

		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				try {
					register.save();
				} catch (RegisterException e) {
					System.err.println("Could not save register");
				}
				return;
			}
		}
	}

	private String readLine() {
		return scanner.nextLine();
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			try {
				selection = Integer.parseInt(readLine());
			} catch (NumberFormatException e) {
				System.err.println("Wrong input");
			}
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	// TODO: Implement the method printRegister
	private void printRegister() {
		for (int i = 0; i < register.getCount(); i++) {
			System.out.println((i + 1) + ". " + register.getPerson(i));// vypisujem poradie a osobu
		}

//		throw new UnsupportedOperationException("Method printRegister not yet implemented");
	}

	private void addToRegister() {
		if (register.getCount() < register.getSize()) {

			System.out.println("Enter Name: ");
			String name = readLine();
			System.out.println("Enter Phone Number: ");
			String phoneNumber = readLine();

			try {
				register.addPerson(new Person(name, phoneNumber));
			} catch (InvalidPhoneNumberExcepion e) {
				System.err.println("Wrong Number");
			}
		} else {
			System.out.println("Register is full");
		}
	}

	// TODO: Implement the method updateRegister
	private void updateRegister() {
		System.out.println("Set ID nubmer of person: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		System.out.println("Enter New Name: ");
		person.setName(readLine());
		System.out.println("Enter Phone New Number: ");
		person.setPhoneNumber(readLine());

//		throw new UnsupportedOperationException("Method updateRegister not yet implemented");
	}

	// TODO: Implement the method findInRegister
	private void findInRegister() {
		System.out.println("Find Name or Number: ");
		String line = readLine();
		Person person = register.findPersonByPhoneNumber(line);
		if (person == null)
			person = register.findPersonByName(line);
		if (person == null)
			System.out.println("No such person");
		else
			System.out.println(person);

//		throw new UnsupportedOperationException("Method findInRegister not yet implemented");
	}

	private void removeFromRegister() {
		System.out.println("Enter index: ");
		try {
			int index = Integer.parseInt(readLine());
			if ((index > 0 && index <= register.getCount())) {
				Person person = register.getPerson(index - 1);
				register.removePerson(person);
			} else {
				System.err.println("Index out of range");
			}
		} catch (NumberFormatException e) {
			System.err.println("Wrong index (index must by number)");
		}
	}
}

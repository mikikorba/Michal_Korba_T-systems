package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

	public static void main(String[] args) throws Exception {
//		Register register = new ArrayRegister(20);
		Register register = new ListRegister();
//		register.addPerson(new Person("A", "1"));
//		register.addPerson(new Person("B", "2"));
//		register.addPerson(new Person("C", "3"));
//		register.addPerson(new Person("D", "4"));
//		register.addPerson(new Person("E", "5"));
//		register.addPerson(new Person("F", "6"));
		ConsoleUI ui = new ConsoleUI(register);
		ui.run();
	}
}

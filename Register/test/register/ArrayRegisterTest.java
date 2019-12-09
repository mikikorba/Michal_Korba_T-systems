package register;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayRegisterTest {

	@Test
	public void testSaveLoad() {
		Register register = register9Persons();
		register.save();
		register = new ArrayRegister(20);
		register.load();
		assertEquals(3, register.getCount());
		assertEquals("Anna", register.getPerson(0).getName());
		assertEquals("Beata", register.getPerson(1).getName());
		assertEquals("Cyril", register.getPerson(2).getName());
		
	}

	private Register register9Persons() {
		Register register = new ArrayRegister(20);
		register.addPerson(new Person("Anna", "100"));
		register.addPerson(new Person("Beata", "020"));
		register.addPerson(new Person("Cyril", "523"));
		return register;
		}
}

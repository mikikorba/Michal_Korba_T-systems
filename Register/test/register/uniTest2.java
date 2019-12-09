package register;

import static org.junit.Assert.*;

import org.junit.Test;

public class uniTest2 {

	@Test
	public void test() {
		Register register = register9Persons();
		assertEquals(9, register.getCount());
	}
	@Test
	public void test2() {
		Register register = register9Persons();
		assertEquals("Anna", register.getPerson(0).getName());
		assertEquals("Demeter", register.getPerson(3).getName());
		assertEquals("Ivo", register.getPerson(8).getName());
//		assertNull(register.getPerson(9).getName());
	}
	
	@Test
	public void test3() {
		Register register = register9Persons();
		assertNotNull(register.getPerson(0).getPhoneNumber());
		assertNotNull(register.getPerson(1).getPhoneNumber());
		assertNotNull(register.getPerson(2).getPhoneNumber());
		assertNotNull(register.getPerson(8).getPhoneNumber());
//		assertNull(register.getPerson(10).getPhoneNumber());
	}
	
	@Test
	public void test4() {
		Register register = register9Persons();
		assertEquals("020", register.getPerson(1).getPhoneNumber());
	}
	@Test(expected = InvalidPhoneNumberExcepion.class)
	public void test5() {
		new Person("Janko", "");
	}
	
	@Test(expected = InvalidPhoneNumberExcepion.class)
	public void test6() {
		new Person("Janko", "aaa");
	}
	
	@Test(expected = InvalidPhoneNumberExcepion.class)
	public void test7() {
		new Person("Janko", "21a");
	}
	
	@Test(expected = InvalidPhoneNumberExcepion.class)
	public void test8() {
		new Person("Janko", "a1215");
	}
		

	private Register register9Persons() {
//		Register register = new ArrayRegister(20);
		Register register = new ListRegister();
		register.addPerson(new Person("Anna", "100"));
		register.addPerson(new Person("Beata", "020"));
		register.addPerson(new Person("Cyril", "523"));
		register.addPerson(new Person("Demeter", "454"));
		register.addPerson(new Person("Erik", "574"));
		register.addPerson(new Person("Frantisek", "645156"));
		register.addPerson(new Person("Gustav", "7451"));
		register.addPerson(new Person("Hana", "845"));
		register.addPerson(new Person("Ivo", "95124"));
		return register;
	}
	

}

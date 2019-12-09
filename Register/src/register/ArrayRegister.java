package register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Register.
 */
public class ArrayRegister implements Register {

	/**
	 * Array of persons. Persons have to be stored continuously from the beginning
	 * of the array. Handle it carefully when removing a person.
	 */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/**
	 * Returns the number of persons in this register.
	 * 
	 * @return the number of persons in this register
	 */
	@Override
	public int getCount() {
		return count;
	}

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/**
	 * Returns the person at the specified position in this register.
	 * 
	 * @param index index of the person to return
	 * @return person the person at the specified position in this register
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/**
	 * Appends the specified person to the end of this register.
	 * 
	 * @param person person to append to this register
	 */
	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	// TODO: Implement the method findPersonByName
	/**
	 * Returns the person with specified name in this register or <code>null</code>,
	 * if match can not be found.
	 * 
	 * @param name name of a person to search for
	 * @return person with specified phone number
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < count; i++) {
			if (persons[i].getName().equalsIgnoreCase(name)) {
				return persons[i];
			}
		}
		return null;
	}
//		throw new UnsupportedOperationException("Method findPersonByName not yet implemented");

	// TODO: Implement the method findPersonByPhoneNumber
	/**
	 * Returns the person with specified phone number in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param phoneNumber phone number of a person to search for
	 * @return person with specified phone number
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < count; i++) {
			if (persons[i].getPhoneNumber().equals(phoneNumber)) {
				return persons[i];
			}
		}
		return null;

	}
//		throw new UnsupportedOperationException("Method findPersonByPhoneNumber not yet implemented");

	// TODO: Implement the method removePerson
	/**
	 * Removes the specified person from the register.
	 * 
	 * @param person person to remove
	 */

//	public void removePerson(Person person, int index) {// vymazat Osobu
//		for (int i = index - 1; i < persons.length - 1; i++) {
//			persons[i] = persons[i + 1];
//		}
//		count--;

	@Override
	public void removePerson(Person person) {// vymazat Osobu
		int index = getIndexOf(person);
		if (index >= 0) {
			count--;
			persons[index] = persons[count];
		}

//		throw new UnsupportedOperationException("Method removePerson not yet implemented");
	}

	private int getIndexOf(Person person) {
		for (int i = 0; i < count; i++) {
			Person personInRegister = persons[i];
			if (person.getName().equals(personInRegister.getName()))
				return i;
		}
		return -1;
	}

	private static final String SAVEFILE = "register.bin";

	@Override
	public void load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVEFILE))) {
			persons = (Person[]) ois.readObject();
			count = (int) ois.readObject();
		} catch (Exception e) {
			throw new RegisterException(e);
		}
	}

	@Override
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVEFILE))) {
			oos.writeObject(persons);
			oos.writeObject(count);
		} catch (IOException e) {
			throw new RegisterException(e);
		}
	}
}

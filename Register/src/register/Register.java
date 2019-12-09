package register;

public interface Register {

	/**
	 * Returns the number of persons in this register.
	 * 
	 * @return the number of persons in this register
	 */
	int getCount();

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	int getSize();

	/**
	 * Returns the person at the specified position in this register.
	 * 
	 * @param index index of the person to return
	 * @return person the person at the specified position in this register
	 */
	Person getPerson(int index);

	/**
	 * Appends the specified person to the end of this register.
	 * 
	 * @param person person to append to this register
	 */
	void addPerson(Person person);

	// TODO: Implement the method findPersonByName
	/**
	 * Returns the person with specified name in this register or <code>null</code>,
	 * if match can not be found.
	 * 
	 * @param name name of a person to search for
	 * @return person with specified phone number
	 */
	Person findPersonByName(String name);
	//		throw new UnsupportedOperationException("Method findPersonByName not yet implemented");

	// TODO: Implement the method findPersonByPhoneNumber
	/**
	 * Returns the person with specified phone number in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param phoneNumber phone number of a person to search for
	 * @return person with specified phone number
	 */
	Person findPersonByPhoneNumber(String phoneNumber);
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

	void removePerson(Person person);
	
	void load();
	
	void save();

}
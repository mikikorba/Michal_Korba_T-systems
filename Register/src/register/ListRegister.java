package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListRegister implements Register {
	private static final String URL = "jdbc:postgresql://localhost/test";
	private static final String LOGIN = "postgres";
	private static final String PASSWORD = "jahodka";
	private static final String DELETE = "DELETE FROM person";
	private static final String INSERT = "INSERT INTO person (name, phone) VALUES (?, ?)";
	private static final String SELECT = "SELECT name, phone FROM person";

	private List<Person> persons = new ArrayList<Person>();

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) {
		persons.add(person);
	}

	@Override
	public Person findPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equals(name)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (Person person : persons) {
			if (person.getPhoneNumber().equals(phoneNumber)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {
		persons.remove(person);

	}

	@Override
	public void load() {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT)) {
			while(rs.next()) {
				addPerson(new Person(rs.getString(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException(e);
		}
	}

	@Override
	public void save() {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				Statement stmt = connection.createStatement();
//				stmt.executeUpdate("CREATE TABLE person (name VARCHAR(128) NOT NULL, phone VARCHAR(32) NOT NULL)");
				PreparedStatement pstmt = connection.prepareStatement(INSERT)) {
			stmt.executeUpdate(DELETE);
			for (Person person : persons) {
				pstmt.setString(1, person.getName());
				pstmt.setString(2, person.getPhoneNumber());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new RegisterException(e);
		}
//		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
//				Statement stmt = connection.createStatement()) {
//			stmt.executeUpdate("DELETE FROM person;");
//			for (Person person : persons) {
//				stmt.executeUpdate("INSERT INTO person (name, phone) VALUES ( '" + person.getName() + "' , '"
//						+ person.getPhoneNumber() + "' );");
//			}
//		} catch (Exception e) {
//			throw new RegisterException(e);
//		}

	}

}

package bankAccountApp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Person person;

	@Before
	public void setUp() throws Exception {
		person = new Person("John Doe", 'M', 30, 75.5f, 180.0f, "black", "brown", "john@example.com");
	}

	@Test
	public void testDefaultConstructor() {
		Person defaultPerson = new Person();
		assertNotNull(defaultPerson);
		assertEquals("", defaultPerson.getName());
		assertEquals('M', defaultPerson.getGender());
		assertEquals(0, defaultPerson.getAge());
		assertEquals(0.0f, defaultPerson.getHeight(), 0.01f);
		assertEquals(0.0f, defaultPerson.getWeight(), 0.01f);
	}

	@Test
	public void testConstructorWithBasicParameters() {
		Person basicPerson = new Person("Jane Doe", 'F', 25, 165.0f);
		assertNotNull(basicPerson);
		assertEquals("Jane Doe", basicPerson.getName());
		assertEquals('F', basicPerson.getGender());
		assertEquals(25, basicPerson.getAge());
		assertEquals(165.0f, basicPerson.getHeight(), 0.01f);
		assertEquals(80.0f, basicPerson.getWeight(), 0.01f); // valeur par défaut
		assertEquals("Black", basicPerson.getHairColor());
		assertEquals("Blue", basicPerson.getEyeColor());
	}

	@Test
	public void testGetName() {
		assertEquals("John Doe", person.getName());
	}

	@Test
	public void testSetName() {
		person.setName("Jane Smith");
		assertEquals("Jane Smith", person.getName());
	}

	@Test
	public void testGetGender() {
		assertEquals('M', person.getGender());
	}

	@Test
	public void testSetGender() throws Exception {
		person.setGender('F');
		assertEquals('F', person.getGender());
	}

	@Test
	public void testSetGenderLowercase() throws Exception {
		person.setGender('f');
		assertEquals('f', person.getGender());
	}

	@Test(expected = Exception.class)
	public void testSetGenderInvalid() throws Exception {
		person.setGender('X');
	}

	@Test
	public void testGetAge() {
		assertEquals(30, person.getAge());
	}

	@Test
	public void testSetAge() {
		person.setAge(35);
		assertEquals(35, person.getAge());
	}

	@Test
	public void testSetAgeNegative() {
		person.setAge(-5);
		assertEquals(30, person.getAge()); // ne devrait pas changer
	}

	@Test
	public void testGetHeight() {
		assertEquals(180.0f, person.getHeight(), 0.01f);
	}

	@Test
	public void testSetHeight() {
		person.setHeight(185.5f);
		assertEquals(185.5f, person.getHeight(), 0.01f);
	}

	@Test
	public void testGetWeight() {
		assertEquals(75.5f, person.getWeight(), 0.01f);
	}

	@Test
	public void testSetWeight() {
		person.setWeight(80.0f);
		assertEquals(80.0f, person.getWeight(), 0.01f);
	}

	@Test
	public void testGetHairColor() {
		assertEquals("black", person.getHairColor());
	}

	@Test
	public void testSetHairColor() {
		person.setHairColor("brown");
		assertEquals("brown", person.getHairColor());
	}

	@Test
	public void testSetHairColorBlond() {
		person.setHairColor("blond");
		assertEquals("blond", person.getHairColor());
	}

	@Test
	public void testSetHairColorWhite() {
		person.setHairColor("white");
		assertEquals("white", person.getHairColor());
	}

	@Test
	public void testSetHairColorInvalid() {
		String originalColor = person.getHairColor();
		person.setHairColor("purple"); // couleur invalide
		assertEquals(originalColor, person.getHairColor()); // ne devrait pas changer
	}

	@Test
	public void testGetEyeColor() {
		assertEquals("brown", person.getEyeColor());
	}

	@Test
	public void testSetEyeColor() {
		person.setEyeColor("blue");
		assertEquals("blue", person.getEyeColor());
	}

	@Test
	public void testGetEmail() {
		assertEquals("john@example.com", person.getEmail());
	}

	@Test
	public void testSetEmail() {
		person.setEmail("newemail@example.com");
		assertEquals("newemail@example.com", person.getEmail());
	}

	@Test
	public void testToString() {
		String expected = "John Doe\nM\n30\n180.0\n75.5\nblack\nbrown\njohn@example.com";
		assertEquals(expected, person.toString());
	}

	@Test(expected = Exception.class)
	public void testConstructorWithInvalidGender() throws Exception {
		new Person("Test Person", 'X', 25, 70.0f, 175.0f, "black", "brown", "test@example.com");
	}
}

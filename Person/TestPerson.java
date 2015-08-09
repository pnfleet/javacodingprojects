import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class TestPerson {
	private Person jeff;
	private Person susy;

	@Before
	public void setup() {
		jeff = new Person("Jeff");
		susy = new Person("Susy");
	}

	@Test
	public void testGetName() {
		assertEquals("Jeff", jeff.getName());
	}

	@Test
	public void testGreetHelloJeff() {
		assertEquals("Hi, I'm Jeff", jeff.greet("Hello"));
	}

	@Test
	public void testGreetHelloSusy() {
		assertEquals("Hi, I'm Susy", susy.greet("Hello"));
	}

	@Test
	public void testGreetGoodbye() {
		assertEquals("Bye", jeff.greet("Goodbye"));
	}

	@Test
	public void testGreetOther() {
		assertEquals("Yo", jeff.greet("Yo"));
	}
}

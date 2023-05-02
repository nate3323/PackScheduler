package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test Student class
 * @author Nathan Cornelison
 *
 */
public class StudentTest {

	/**
	 * Tests hash code method
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s3 = new Student("differentfirst", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s4 = new Student("first", "differentlast", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s5 = new Student("first", "last", "differentid", "email@ncsu.edu", "hashedpassword", 18);
		User s6 = new Student("first", "last", "id", "different@ncsu.edu", "hashedpassword", 18);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "different", 18);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		
		//Test Equals
		assertEquals(s1.hashCode(), s2.hashCode());

		//Test Not Equals
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Tests constructor with maxCredits
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		User s = null; //Initialize a student reference to null
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword", 18);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword", 18);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword", 18);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null, 18);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", -2);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
	}

	/**
	 * Tests constructor without maxCredits
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		User s = null;
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}		
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
	}

	/**
	 * Tests setFirstName
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");

		//Testing legal first names
		s.setFirstName("Graham");
		assertEquals("Graham", s.getFirstName());
		s.setFirstName("first");
		assertEquals("first", s.getFirstName());
		
		//Testing illegal first names
		try {
		    s.setFirstName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		}
		try {
		    s.setFirstName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("first", s.getFirstName());
		}
	}

	/**
	 * Tests setLastName
	 */
	@Test
	public void testSetLastName() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//Testing legal first names
		s.setLastName("Swain");
		assertEquals("Swain", s.getLastName());
		s.setLastName("last");
		assertEquals("last", s.getLastName());
		
		//Testing illegal last names
		try {
		    s.setLastName(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
	}
	
	/**
	 * Tests setId
	 */
	@Test
	public void testSetId() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");		
		
		//Testing illegal id's
		try {
			s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("id", s.getId());
		}
		s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		try {
			s = new Student("first", "last", "", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("id", s.getId());
		}
		
	}

	/**
	 * Tests setEmail
	 */
	@Test
	public void testSetEmail() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//Testing legal email
		s.setEmail("gnswain@ncsu.edu");
		assertEquals("gnswain@ncsu.edu", s.getEmail());
		s.setEmail("email@ncsu.edu");
		assertEquals("email@ncsu.edu", s.getEmail());
		
		//Testing illegal email
		try {
		    s.setEmail(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("email@ncsu.edu", s.getEmail());
		}
		try {
		    s.setEmail("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("email@ncsu.edu", s.getEmail());
		}
		try {
		    s.setEmail("emailncsu.edu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("email@ncsu.edu", s.getEmail());
		}
		try {
		    s.setEmail("email@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("email@ncsu.edu", s.getEmail());
		}
		try {
		    s.setEmail("email.@ncsuedu");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("email@ncsu.edu", s.getEmail());
		}
	}

	/**
	 * Tests setPassword
	 */
	@Test
	public void testSetPassword() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "password");
		
		//Testing legal password
		s.setPassword("hashedpassword");
		assertEquals("hashedpassword", s.getPassword());
		
		//Testing illegal passwords
		try {
		    s.setPassword(null);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("hashedpassword", s.getPassword());
		}
		try {
		    s.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("hashedpassword", s.getPassword());
		}
	}

	/**
	 * Tests setMaxCredits
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		
		//Testing legal max credits
		s.setMaxCredits(18);
		assertEquals(18, s.getMaxCredits());
		
		//Testing illegal max credits
		try {
		    s.setMaxCredits(0);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(18, s.getMaxCredits());
		}
		try {
		    s.setMaxCredits(20);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(18, s.getMaxCredits());
		}
	}

	/**
	 * Tests toString
	 */
	@Test
	public void testToString() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(s1.toString(), string1);
		
		String string2 = "different,last,id,email@ncsu.edu,hashedpassword,18";
		assertNotEquals(s1.toString(), string2);
		
	}

	/**
	 * Tests equals
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s3 = new Student("differentfirst", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s4 = new Student("first", "differentlast", "id", "email@ncsu.edu", "hashedpassword", 18);
		User s5 = new Student("first", "last", "differentid", "email@ncsu.edu", "hashedpassword", 18);
		User s6 = new Student("first", "last", "id", "different@ncsu.edu", "hashedpassword", 18);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "different", 18);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		
		//Test Equals
		assertTrue(s1.equals(s2));
		assertTrue(s1.equals(s1));

		//Test Not Equals
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		//Test null object
		User n = null;
		assertFalse(s1.equals(n));
	}
	/**
	 * Tests compare to. It should return a negative integer, zero or positive integer when the object
	 * is less than equal to, or greater than the specified object
	 */
	@Test
	public void testCompareTo() {

		Student s1 = new Student("Graham", "Swain", "gnswain", "gnswain@ncsu.edu", "pw");
		Student s2 = new Student("Graham", "Swain", "gnswain", "gnswain@ncsu.edu", "pw");
		Student s3 = new Student("Michael", "Black", "mtblack", "mtblack@ncsu.edu", "pw");
		Student s4 = new Student("Joe", "Swain", "joe", "joe@joe.com", "joe");
		Student s5 = new Student("Graham", "Swain", "different", "gnswain@ncsu.edu", "pw");
		
		//Test null
		try {
			s1.compareTo(null);
			fail();
		} catch (NullPointerException e) {
			//Test passes
		}
		
		//Test equals	
		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		
		//Test greater than
		assertEquals(1, s1.compareTo(s3));
		
		//Test less than
		assertEquals(-1, s3.compareTo(s1));
		
		//Test same last name but different first name
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s4.compareTo(s1));
		
		//Test same name but different ID
		assertEquals(1, s1.compareTo(s5));
		assertEquals(-1, s5.compareTo(s1));
	}
	
	/**
	 * Tests canAdd
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student("first", "last", "id", "email@email.com", "password");
		
		Course c1 = new Course("CSC216", "Java", "001", 4, "id", 10, "A");
		Course c2 = new Course("MA101", "Java", "001", 4, "id", 10, "MW", 900, 1000);
		Course c3 = new Course("PY101", "Java", "001", 4, "id", 10, "MW", 900, 1000);
		Course c4 = new Course("PY101", "Java", "001", 4, "id", 10, "MW", 1105, 1200);
		Course c5 = new Course("EC101", "Java", "001", 4, "id", 10, "MW", 800, 855);
		Course c6 = new Course("ENG101", "Java", "001", 4, "id", 10, "F", 900, 1000);
		
		assertTrue(s1.canAdd(c1));
		s1.getSchedule().addCourseToSchedule(c1);
		
		assertFalse(s1.canAdd(c1));
		
		assertTrue(s1.canAdd(c2));
		s1.getSchedule().addCourseToSchedule(c2);
		
		assertFalse(s1.canAdd(c3));
		
		assertTrue(s1.canAdd(c4));
		s1.getSchedule().addCourseToSchedule(c4);
		
		assertTrue(s1.canAdd(c5));
		s1.getSchedule().addCourseToSchedule(c5);
		
		assertEquals(16, s1.getSchedule().getScheduleCredits());
		assertFalse(s1.canAdd(c6));
	}
}

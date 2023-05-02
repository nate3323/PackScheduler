package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Faculty
 * @author Nathan Cornelison
 */
public class FacultyTest {

	/**
	 * Tests setMaxCredits
	 */
	@Test
	public void testSetMaxCredits() {
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		
		//Testing legal max credits
		s.setMaxCourses(3);
		assertEquals(3, s.getMaxCourses());
		
		//Testing illegal max credits
		try {
		    s.setMaxCourses(0);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(3, s.getMaxCourses());
		}
		try {
		    s.setMaxCourses(20);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(3, s.getMaxCourses());
		}
	}
	
	/**
	 * Tests hash code method
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s3 = new Faculty("differentfirst", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s4 = new Faculty("first", "differentlast", "id", "email@ncsu.edu", "hashedpassword", 2);
		User s5 = new Faculty("first", "last", "differentid", "email@ncsu.edu", "hashedpassword", 2);
		User s6 = new Faculty("first", "last", "id", "different@ncsu.edu", "hashedpassword", 2);
		User s7 = new Faculty("first", "last", "id", "email@ncsu.edu", "different", 2);
		User s8 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);
		
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
	 * Tests toString
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,2";
		assertEquals(s1.toString(), string1);
		
		String string2 = "different,last,id,email@ncsu.edu,hashedpassword,2";
		assertNotEquals(s1.toString(), string2);
		
	}

}

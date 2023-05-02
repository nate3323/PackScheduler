package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests FacultyDirectory
 * @author Nathan Cornelison
 */
public class FacultyDirectoryTest {

	/**
	 * Tests addFaculty
	 */
	@Test
	public void test() {
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.addFaculty("Graham", "Swain", "gnswain", "gnswain@ncsu.edu", "password", "password", 3);
		
		assertEquals(1, fd.getFacultyDirectory().length);
		
		fd.getFacultyById("gnswain");
		
		fd.addFaculty("first", "last", "id", "email@email.com", "pw", "pw", 1);
		
		assertEquals(2, fd.getFacultyDirectory().length);
		
		fd.removeFaculty("gnswain");
		
		assertEquals(1, fd.getFacultyDirectory().length);
		
		fd.newFacultyDirectory();
		
		assertEquals(0, fd.getFacultyDirectory().length);
	}
	
	/**
	 * Tests getFacultyDirectory
	 */
	@Test
	public void testGetFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty("Graham", "Swain", "gnswain", "gnswain@ncsu.edu", "password", "password", 3);
		fd.addFaculty("first", "last", "id", "email@email.com", "pw", "pw", 1);
		
		String[][] test = fd.getFacultyDirectory();
		
		assertEquals("Graham", test[0][0]);
		assertEquals("Swain", test[0][1]);
		assertEquals("id", test[1][2]);
		
		fd.saveFacultyDirectory("test-files/faculty_test.txt");
		
		fd.loadFacultyFromFile("test-files/faculty_test.txt");
		
		try {
			fd.loadFacultyFromFile("test");
		} catch (IllegalArgumentException e) {
			//Passes
		}
	}

}

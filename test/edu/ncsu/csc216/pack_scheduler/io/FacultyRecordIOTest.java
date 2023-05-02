package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests FacultyRecordIO
 * @author Nathan Cornelison
 */
public class FacultyRecordIOTest {

	/**
	 * Tests the readStudentRecords method
	 */
	@Test
	public void testReadStudentRecords() {
		//Testing for a file that doesn't exist
		try {
			FacultyRecordIO.readFacultyRecords("test-files/notafile.txt");
			fail();
		} catch (FileNotFoundException e) {	
			//Test passes
		}
		try {
			LinkedList<Faculty> s = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(s.size(), 8);
		} catch (FileNotFoundException e) {
			fail();
		}
		try {
			LinkedList<Faculty> s2 = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(s2.size(), 8);
			assertEquals(s2.get(0).getFirstName(), "Ashely");
			assertEquals(s2.get(0).getLastName(), "Witt");
			assertEquals(s2.get(0).getId(), "awitt");
			assertEquals(s2.get(0).getEmail(), "mollis@Fuscealiquetmagna.net");
			assertEquals(s2.get(0).getMaxCourses(), 2);
		} catch (FileNotFoundException e) {
			fail();
		}
	}

}

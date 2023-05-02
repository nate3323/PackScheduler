package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the StudentRecordIO class
 * @author Nathan Cornelison
 *
 */
public class StudentRecordIOTest {

	private final String validStudentRecord1 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw";
	private final String validStudentRecord2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw";
	private final String validStudentRecord3 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw";
	private final String validStudentRecord4 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw";
	private final String validStudentRecord5 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw";
	private final String validStudentRecord6 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw";
	private final String validStudentRecord7 = "Lane,Berg,lberg,sociis@non.org,pw";
	private final String validStudentRecord8 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw";
	private final String validStudentRecord9 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw";
	private final String validStudentRecord10 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw";
	
	/** Array holding the values for valid students */
	private String[] validStudents = {validStudentRecord1, validStudentRecord2, validStudentRecord3, validStudentRecord4, validStudentRecord5, 
			validStudentRecord6, validStudentRecord7, validStudentRecord8, validStudentRecord9, validStudentRecord10};
	
	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Reads the passwords
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Tests the readStudentRecords method
	 */
	@Test
	public void testReadStudentRecords() {
		//Testing for a file that doesn't exist
		try {
			StudentRecordIO.readStudentRecords("test-files/notafile.txt");
			fail();
		} catch (FileNotFoundException e) {	
			//Test passes
		}
		try {
			SortedList<Student> s = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(s.size(), 10);
		} catch (FileNotFoundException e) {
			fail();
		}
		try {
			SortedList<Student> s1 = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(s1.size(), 0);
		} catch (FileNotFoundException e) {
			fail();
		}
		try {
			SortedList<Student> s2 = StudentRecordIO.readStudentRecords("test-files/expected_student_records.txt");
			assertEquals(s2.size(), 1);
			assertEquals(s2.get(0).getFirstName(), "Zahir");
			assertEquals(s2.get(0).getLastName(), "King");
			assertEquals(s2.get(0).getId(), "zking");
			assertEquals(s2.get(0).getEmail(), "orci.Donec@ametmassaQuisque.com");
			assertEquals(s2.get(0).getPassword(), hashPW);
			assertEquals(s2.get(0).getMaxCredits(), 15);
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Tests writeStudentRecords methods for files that it doesn't have permission to write to
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	    checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}

	/**
	 * Test writeStudentRecords method
	 */
	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student ("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw"));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_students_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
	}
	
	/**
	 * Checks to see if two files are the same
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

}

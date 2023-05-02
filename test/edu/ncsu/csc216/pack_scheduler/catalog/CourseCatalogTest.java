package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog
 * @author Nathan Cornelison
 */
public class CourseCatalogTest {
	
	/**
	 * Tests addCourseToCatalog. It should return true if it can add the course
	 *   and false if it can't
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		Course t1 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		
		assertTrue(cc.addCourseToCatalog("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000));
		assertTrue(cc.addCourseToCatalog("CSC216", "Java", "002", 4, "id", 50, "TH", 1000, 1100));
		
		assertEquals(t1, cc.getCourseFromCatalog("CSC216", "001"));
		
		assertFalse(cc.addCourseToCatalog("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000));
	}
	
	/**
	 * Tests removing a course from the catalog. It should return true if it can remove the course
	 *   and false if it can't
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		cc.addCourseToCatalog("CSC216", "Java", "002", 4, "id", 50, "TH", 1000, 1100);
		
		assertTrue(cc.removeCourseFromCatalog("CSC216", "002"));
		assertEquals(null, cc.getCourseFromCatalog("CSC216", "002"));
		assertFalse(cc.removeCourseFromCatalog("CSC216", "003"));
	}

	/**
	 * Tests loading a catalog from a file
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		Course course = new Course("CSC116", "Intro to Programming - Java", "001" , 3, null, 50, "MW", 910, 1100);
		assertEquals(course, cc.getCourseFromCatalog("CSC116", "001"));
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("MW 9:10AM-11:00AM", catalog[0][3]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("MW 11:20AM-1:10PM", catalog[1][3]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("TH 11:20AM-1:10PM", catalog[2][3]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Programming Concepts - Java", catalog[3][2]);
		assertEquals("TH 1:30PM-2:45PM", catalog[3][3]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Programming Concepts - Java", catalog[4][2]);
		assertEquals("MW 1:30PM-2:45PM", catalog[4][3]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Programming Concepts - Java", catalog[5][2]);
		assertEquals("Arranged", catalog[5][3]);
		//Row 6
		assertEquals("CSC226", catalog[6][0]);
		assertEquals("001", catalog[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", catalog[6][3]);
		//Row 7
		assertEquals("CSC230", catalog[7][0]);
		assertEquals("001", catalog[7][1]);
		assertEquals("C and Software Tools", catalog[7][2]);
		assertEquals("MW 11:45AM-1:00PM", catalog[7][3]);
	}
	
	/**
	 * Tests saving a catalog to a file
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001" , 3, "jdyoung2", 50, "MW", 910, 1100));
		assertTrue(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002" , 3, "jdyoung2", 50, "MW", 910, 1100));
		cc.saveCourseCatalog("test-files/course_test");
		cc.newCourseCatalog();
		cc.loadCoursesFromFile("test-files/course_test");
		Course course = new Course("CSC116", "Intro to Programming - Java", "001" , 3, null, 50, "MW", 910, 1100);
		assertEquals(course, cc.getCourseFromCatalog("CSC116", "001"));
	}
}

package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule
 * @author Nathan Cornelison
 *
 */
public class ScheduleTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Seats available */
	private static final int CAP = 50;
	
	/**
	 * Test that a new schedule can be created 
	 * and is named the default name
	 */
	@Test
	public void testSchedule() {
		Schedule test = new Schedule();
		assertEquals("My Schedule", test.getTitle());
	}
	
	/**
	 * Test the addCourseToSchedule works correctly
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule test = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course("MA241", "Different", SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 1500, 1600);
		try {
			assertTrue(test.addCourseToSchedule(c1));
		} catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
			fail();
		}
		try {
			assertTrue(test.addCourseToSchedule(c3));
		} catch (IllegalArgumentException e1) {
			System.out.println(e1.getMessage());
			fail();
		}
		


		try {
			test.addCourseToSchedule(c2);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}

	}
	
	/**
	 * Tests the removeCourseFromSchedule works correctly
	 */
	@Test
	public void testRemove() {
		Schedule test = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course("CSC226", "Math", SECTION, CREDITS, INSTRUCTOR_ID, CAP, "F", START_TIME, END_TIME);
		Course c3 = new Course("MA241", "Different Math", SECTION, CREDITS, INSTRUCTOR_ID, CAP, "T", 1500, 1600);
		try {
			assertTrue(test.addCourseToSchedule(c1));
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			assertTrue(test.addCourseToSchedule(c2));
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			assertTrue(test.addCourseToSchedule(c3));
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertTrue(test.removeCourseFromSchedule(c1));
		assertTrue(test.removeCourseFromSchedule(c3));
		assertTrue(test.removeCourseFromSchedule(c2));
	}
	
	/**
	 * Test that a schedule can be reset
	 */
	@Test
	public void testResetSchedule() {
		Schedule test = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		try {
			assertTrue(test.addCourseToSchedule(c1));
		} catch (IllegalArgumentException e) {
			// We shouldn't get here
		}
		test.resetSchedule();
		assertFalse(test.removeCourseFromSchedule(c1));
	}
	
	/**
	 * Test that you can get the correct course schedule
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule test = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		//Test that it is empty to begin with
		String[][] schedule = test.getScheduledCourses();
		assertEquals(0, schedule.length);
		//Tests adding a course
		test.addCourseToSchedule(c1);
		schedule = test.getScheduledCourses();
		assertEquals(NAME, schedule[0][0]);
	}
	
	/**
	 * Test that you can set the title
	 */
	@Test
	public void testSetTitle() {
		Schedule test = new Schedule();
		try {
			test.setTitle(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("My Schedule", test.getTitle());
		}
		test.setTitle("test title");
		assertEquals("test title", test.getTitle());
	}
}

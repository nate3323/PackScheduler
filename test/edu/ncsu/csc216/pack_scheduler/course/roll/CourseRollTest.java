package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll
 * @author Nathan Cornelison
 */
public class CourseRollTest {

	/**
	 * Tests CourseRoll
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith", 50, "MW", 1330, 1445);
		CourseRoll cr = new CourseRoll(c, 50);
		
		assertEquals(50, cr.getEnrollmentCap());
		
		cr.setEnrollmentCap(40);
		
		assertEquals(40, cr.getEnrollmentCap());
		
		try {
			cr.setEnrollmentCap(9);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(40, cr.getEnrollmentCap());
		}
		
		try {
			cr.setEnrollmentCap(251);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(40, cr.getEnrollmentCap());
		}
		
		Student s1 = new Student("1", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s2 = new Student("2", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s3 = new Student("3", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s4 = new Student("4", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s5 = new Student("5", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s6 = new Student("6", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s7 = new Student("7", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s8 = new Student("8", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s9 = new Student("9", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s10 = new Student("10", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s11 = new Student("11", "Last", "lename", "lename@leemail.com", "lepassword");
		
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		cr.enroll(s11);
		
		try {
			cr.setEnrollmentCap(10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(40, cr.getEnrollmentCap());
		}
		
	}
	
	/**
	 * Tests Enroll
	 */
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith", 50, "MW", 1330, 1445);
		CourseRoll cr = new CourseRoll(c, 50);
		
		try {
			cr.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(50, cr.getOpenSeats());
		}

		Student s1 = new Student("1", "Last", "lename", "lename@leemail.com", "lepassword");
		cr.enroll(s1);
		try {
			cr.enroll(s1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(49, cr.getOpenSeats());
		}
		
		cr.setEnrollmentCap(10);
		Student s2 = new Student("2", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s3 = new Student("3", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s4 = new Student("4", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s5 = new Student("5", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s6 = new Student("6", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s7 = new Student("7", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s8 = new Student("8", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s9 = new Student("9", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s10 = new Student("10", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s11 = new Student("11", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s12 = new Student("12", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s13 = new Student("13", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s14 = new Student("14", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s15 = new Student("15", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s16 = new Student("16", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s17 = new Student("17", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s18 = new Student("18", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s19 = new Student("19", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s20 = new Student("20", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s21 = new Student("21", "Last", "lename", "lename@leemail.com", "lepassword");
		
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		cr.enroll(s11);
		cr.enroll(s12);
		cr.enroll(s13);
		cr.enroll(s14);
		cr.enroll(s15);
		cr.enroll(s16);
		cr.enroll(s17);
		cr.enroll(s18);
		cr.enroll(s19);
		cr.enroll(s20);

		try {
			cr.enroll(s21);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cr.getOpenSeats());
		}
		try {
			cr.enroll(s4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, cr.getOpenSeats());
		}
	}
	
	/**
	 * Tests drop
	 */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith", 50, "MW", 1330, 1445);
		CourseRoll cr = new CourseRoll(c, 50);
		
		try {
			cr.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(50, cr.getOpenSeats());
		}

		Student s1 = new Student("1", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s2 = new Student("2", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s3 = new Student("3", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s4 = new Student("4", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s5 = new Student("5", "Last", "lename", "lename@leemail.com", "lepassword");
		
		
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);

		assertEquals(45, cr.getOpenSeats());
		
		cr.drop(s1);
		assertEquals(46, cr.getOpenSeats());
		cr.drop(s3);
		assertEquals(47, cr.getOpenSeats());
		cr.drop(s5);
		assertEquals(48, cr.getOpenSeats());
		cr.drop(s2);
		cr.drop(s4);
		assertEquals(50, cr.getOpenSeats());
		
		cr.setEnrollmentCap(10);
		Student s6 = new Student("6", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s7 = new Student("7", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s8 = new Student("8", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s9 = new Student("9", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s10 = new Student("10", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s11 = new Student("11", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s12 = new Student("12", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s13 = new Student("13", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s14 = new Student("14", "Last", "lename", "lename@leemail.com", "lepassword");
		Student s15 = new Student("15", "Last", "lename", "lename@leemail.com", "lepassword");
		
		cr.enroll(s1);
		cr.enroll(s2);
		cr.enroll(s3);
		cr.enroll(s4);
		cr.enroll(s5);
		cr.enroll(s6);
		cr.enroll(s7);
		cr.enroll(s8);
		cr.enroll(s9);
		cr.enroll(s10);
		cr.enroll(s11);
		assertEquals(0, cr.getOpenSeats());
		assertEquals(1, cr.getNumberOnWaitlist());
		
		cr.drop(s1);
		assertEquals(0, cr.getNumberOnWaitlist());
		
		cr.enroll(s1);
		cr.enroll(s12);
		cr.enroll(s13);
		cr.enroll(s14);
		cr.enroll(s15);
		
		assertEquals(5, cr.getNumberOnWaitlist());
		
		cr.drop(s14);
		
		assertEquals(4, cr.getNumberOnWaitlist());

	}

}

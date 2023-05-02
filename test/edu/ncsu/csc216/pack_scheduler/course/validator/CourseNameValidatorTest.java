package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Contains Test for valid and invalid Course Names
 * @author Nathan Cornelison
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Tests various valid Course names 
	 */
	@Test
	public void testValidCourseName() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("C216"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertTrue(cnv.isValid("CS216"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertTrue(cnv.isValid("CSC216"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertTrue(cnv.isValid("CSCC216"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertTrue(cnv.isValid("CSC216C"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
	}
	
	/**
	 * Tests invalid course names and that the correct 
	 * error message is displayed
	 */
	@Test
	public void testInvalidCourseName() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertFalse(cnv.isValid("216"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CCCCC216"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC2C16"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC21C6"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC2164"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC2"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertFalse(cnv.isValid("CSC21"));
		} catch (InvalidTransitionException e) { 
			fail();
		}
		try {
			assertFalse(cnv.isValid("!CSC216"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC216CC"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		try {
			assertFalse(cnv.isValid("CSC216C2"));
			fail();
		} catch (InvalidTransitionException e) { 
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
	}

}

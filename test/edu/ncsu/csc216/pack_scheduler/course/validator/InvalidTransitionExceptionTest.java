/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests InvlaidTransitionException
 * @author Nathan Cornelison
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * Tests the error messages for an InvalidTransitionException
	 */
	@Test
	public void test() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	    
	    InvalidTransitionException ce1 = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ce1.getMessage());
	}

}

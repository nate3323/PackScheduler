/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests check conflict functionality in activity
 * @author Nathan Cornelison
 */
public class ActivityTest {

	/**
	 * Tests check conflict functionality in activity
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}
	/**
     * Tests setting the title of an activity
     */
    @Test
    public void testSetTitle() {
    	Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "MW", 1330, 1445);
    	assertEquals("Programming Concepts - Java", a1.getTitle());
    	try {
    		a1.setTitle(null);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("Programming Concepts - Java", a1.getTitle());
    	}
    	try {
    		a1.setTitle("");
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("Programming Concepts - Java", a1.getTitle());
    	}
    }
    /**
     * Test equals for Activity
     */
    @Test
    public void testEquals() {
    	Course c1 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		Course c2 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		Course c3 = new Course("CSC216", "Java", "002", 4, "id", 50, "MWF", 900, 1000);
		
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		
		assertFalse(c1.equals(c3));
    }
    /**
     * Test hashCode for Activity
     */
    @Test
    public void testHashCode() {
    	Course c1 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		Course c2 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		Course c3 = new Course("CSC216", "Java", "002", 4, "id", 50, "MWF", 900, 1000);
		
		assertEquals(c1.hashCode(), c2.hashCode());
		
		assertNotEquals(c1.hashCode(), c3.hashCode());
    }
    /**
     * Test getMeetingString
     */
    @Test
    public void testGetMeetingString() {
    	Activity c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "MW", 1330, 1445);
		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
		Activity c2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "MW", 900, 1035);
		assertEquals("MW 9:00AM-10:35AM", c2.getMeetingString());
		Activity c3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "A");
		assertEquals("Arranged", c3.getMeetingString());
		Activity c4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "TH", 1145, 1425);
		assertEquals("TH 11:45AM-2:25PM", c4.getMeetingString());
		Activity c5 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "TH", 1145, 1225);
		assertEquals("TH 11:45AM-12:25PM", c5.getMeetingString());
    }
    /**
     * Test setMeetingTime
     */
    @Test
    public void testSetMeetingTime() {
    	Activity c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 50, "MW", 1330, 1445);
    	try {
    		c1.setActivityTime(-1, 100);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
    	}
    	try {
    		c1.setActivityTime(2400, 1200);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
    	}
    	try {
    		c1.setActivityTime(100, -1);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
    	}
    	try {
    		c1.setActivityTime(1, 2400);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
    	}
    	try {
    		c1.setActivityTime(2300, 100);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
    	}
    	try {
    		c1.setActivityTime(1480, 1500);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("Invalid meeting times", e.getMessage());
    	}
    	try {
    		c1.setActivityTime(1400, 1599);
    		fail();
    	} catch (IllegalArgumentException e) {
    		assertEquals("Invalid meeting times", e.getMessage());
    	}
    	c1.setMeetingDays("A");
    	c1.setActivityTime(0, 0);
    	assertEquals("Arranged", c1.getMeetingString());
    }
}

package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the SortedList class in the Collections library
 * @author Nathan Cornelison
 *
 */
public class SortedListTest {
	/**
	 * Tests that SortedLists are constructed correctly
	 * Tests add more than ten objects, making sure it expands without error
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
	
		//Remember the list's initial capacity is 10
		list.add("a");
		assertEquals(1, list.size());
		assertTrue(list.contains("a"));
		list.add("b");
		assertEquals(2, list.size());
		assertTrue(list.contains("b"));
		list.add("c");
		assertEquals(3, list.size());
		assertTrue(list.contains("c"));
		list.add("d");
		assertEquals(4, list.size());
		assertTrue(list.contains("d"));
		list.add("e");
		assertEquals(5, list.size());
		assertTrue(list.contains("e"));
		list.add("f");
		assertEquals(6, list.size());
		assertTrue(list.contains("f"));
		list.add("g");
		assertEquals(7, list.size());
		assertTrue(list.contains("g"));
		list.add("h");
		assertEquals(8, list.size());
		assertTrue(list.contains("h"));
		list.add("i");
		assertEquals(9, list.size());
		assertTrue(list.contains("i"));
		list.add("j");
		assertEquals(10, list.size());
		assertTrue(list.contains("j"));
		list.add("k");
		assertEquals(11, list.size());
		assertTrue(list.contains("k"));

		
	}
	/**
	 * Tests that items are added in alphabetical order and 
	 * that if a duplicate or null item is added that it throws
	 * an IllegalArgumentException
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		assertEquals(4, list.size());
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("peach"));
		assertEquals(3, list.indexOf("zebra"));
		
		//Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		//Test adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests that getting an item from an empty list, getting an item with a
	 * negative index, and getting an item at list.size() throws an
	 * IndexOutofBoundsException
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Add some elements to the list
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
		
		//Test getting an element at an index < 0
		try {
			list.get(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
	}
	/**
	 * Tests removing items from different parts of the list.
	 * Tests that removing an item from an empty list, removing an item with a
	 * negative index, and removing an item at list.size() throws an
	 * IndexOutofBoundsException
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Add some elements to the list - at least 4
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test removing a middle element
		list.remove(2);
		assertFalse(list.contains("peach"));
		assertEquals(3, list.size());
		
		//Test removing the last element
		list.remove(2);
		assertFalse(list.contains("zebra"));
		assertEquals(2, list.size());
		
		//Test removing the first element
		list.remove(0);
		assertFalse(list.contains("apple"));
		assertEquals(1, list.size());
		
		//Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	/** 
	 * Test getting the index of from items in different parts of the list,
	 * getting the index of an item not in the list, getting the index of an
	 * item in an empty list, and getting a null item
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));

		
		//Add some elements
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
			
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("peach"));
		assertEquals(3, list.indexOf("zebra"));
		assertEquals(-1, list.indexOf("longPork"));
			
		
		//Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
	}
	/**
	 * Tests clear list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
		//Clear the list
		list.clear();
		
		//Test that the list is empty
		assertEquals(0, list.size());
	}
	/**
	 * Tests that list.isEmpty() returns true if it's empty and
	 * false if it is not
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//Add at least one element
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}
	/**
	 * Tests the list.contains() returns true if the item is in the list
	 * and false if it is not
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		
		
		//Add some elements
		list.add("apple");
		list.add("zebra");
		list.add("peach");
		list.add("banana");
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("apples"));
		assertTrue(list.contains("banana"));
		assertFalse(list.contains("banas"));
	}
	/**
	 * Tests to see if the equals method properly evaluates
	 * the items it is comparing
	 */
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("zebra");
		list1.add("peach");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("zebra");
		list2.add("peach");
		list2.add("banana");
		
		list3.add("apple");
		list3.add("zebra");
		list3.add("peach");
		list3.add("banansa");
		
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list3.equals(list1));
		assertFalse(list1.equals(list3));



	}
	/**
	 * Tests that the hash code is generated correctly
	 */
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("zebra");
		list1.add("peach");
		list1.add("banana");
		
		list2.add("apple");
		list2.add("zebra");
		list2.add("peach");
		list2.add("banana");
		
		list3.add("apple");
		list3.add("zebra");
		list3.add("peach");
		list3.add("banansa");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}
	/**
	 * Tests how SortedList handles Course Objects
	 */
	@Test
	public void testSortedListCourse() {
		SortedList<Course> sl = new SortedList<Course>();
		Course t1 = new Course("CSC216", "Java", "001", 4, "id", 50, "MWF", 900, 1000);
		Course t2 = new Course("CSC216", "Java", "002", 4, "id", 50, "MWF", 1030, 1130);
		Course t3 = new Course("CSC216", "Java", "003", 4, "id", 50, "TH", 900, 1000);
		
		sl.add(t1);
		sl.add(t2);
		sl.add(t3);
		assertEquals(3, sl.size());
		assertEquals(t1, sl.get(0));
		assertEquals(t2, sl.get(1));
		assertEquals(t3, sl.get(2));
	}

}
 
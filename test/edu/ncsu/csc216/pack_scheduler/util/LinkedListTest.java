/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the LinkedList class
 * @author Nathan Cornelison
 */
public class LinkedListTest {

	/**
	 * Test that a new arrayList can be created an that its size is 0
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> test = new LinkedList<String>();
		assertEquals(0, test.size());
	}

	/**
	 * Test that a Course can be added to the arrayList
	 */
	@Test
	public void testAdd() {
		LinkedList<String> test = new LinkedList<String>();
		try {
			test.add(0, null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.add(-2, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.add(20, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.add(20, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		test.add(0, "a");
		assertEquals(1, test.size());
		assertEquals("a", test.get(0));

		
		try {
			test.add(0, "a");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(1, test.size());
		}
		
		test.add(1, "c");
		assertEquals("c", test.get(1));
		
		test.add(1, "b");
		assertEquals("b", test.get(1));
		
		test.add(3, "d");
		test.add(4, "e");
		test.add(5, "f");
		test.add(6, "g");
		test.add(7, "h");
		test.add(8, "i");
		test.add(9, "j");
		test.add(10, "k");
		assertEquals(11, test.size());
	}
	
	/**
	 * Test that a course can be removed from the arrayList
	 */
	@Test
	public void testRemove() {
		LinkedList<String> test = new LinkedList<String>();
		try {
			test.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.remove(20);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		test.add(0, "a");
		test.add(1, "b");
		test.add(2, "c");
		test.add(3, "d");
		test.add(4, "e");
		test.add(5, "f");
		test.add(6, "g");
		test.add(7, "h");
		test.add(8, "i");
		test.add(9, "j");
		test.add(10, "k");
		
		test.remove(7);
		assertEquals("i", test.get(7));
		
		test.remove(0);
		assertEquals("b", test.get(0));
		
		test.remove(test.size() - 1);
		assertEquals("j", test.get(test.size() - 1));
	}
	
	/**
	 * Test that errors are caught
	 */
	@Test
	public void testSet() {
		LinkedList<String> test = new LinkedList<String>();
		
		try {
			test.set(0, null);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.set(-1, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.set(20, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		try {
			test.set(20, "a");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(0, test.size());
		}
		
		test.add(0, "a");
		test.add(1, "b");
		
		try {
			test.set(1, "a");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(2, test.size());
		}
		
		test.add(2, "c");
		test.add(3, "d");
		test.add(4, "e");
		test.add(5, "f");
		test.add(6, "g");
		test.add(7, "h");
		test.add(8, "i");
		test.add(9, "j");
		
		
		test.set(0, "k");
		assertEquals("k", test.get(0));
		
		test.set(7, "l");
		assertEquals("l", test.get(7));
		
		test.set(9, "m");
		assertEquals("m", test.get(9));
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		LinkedList<String> test = new LinkedList<String>();
		try {
			test.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index: 0", e.getMessage());
		}
	}

}

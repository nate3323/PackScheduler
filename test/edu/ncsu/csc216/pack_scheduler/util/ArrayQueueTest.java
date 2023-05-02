/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests linked queue
 * @author Nathan Cornelison
 */
public class ArrayQueueTest {

	/**
	 * Test Enqueue
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<String> test = new ArrayQueue<String>(1);
		
		test.enqueue("a");
		assertEquals(1, test.size());
		try {
			test.enqueue("b");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, test.size());
		}
	}
	
	/**
	 * Tests dequeue
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<String> test = new ArrayQueue<String>(3);
		
		test.enqueue("a");
		test.enqueue("b");
		test.enqueue("c");
		
		assertEquals(3, test.size());
		
		assertEquals("a", test.dequeue());
		assertEquals(2, test.size());
		
		assertEquals("b", test.dequeue());
		assertEquals(1, test.size());
		
		assertEquals("c", test.dequeue());
		assertEquals(0, test.size());
		
		try {
			test.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals(0, test.size());
		}
	}
	
	/**
	 * Tests setCapacity
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> test = new ArrayQueue<String>(3);
		
		test.enqueue("a");
		test.enqueue("b");
		
		test.setCapacity(2);
		
		try {
			test.enqueue("c");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, test.size());
		}
		
		try {
			test.setCapacity(1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, test.size());
		}
		
	}
	
	/**
	 * Tests isEmpty
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<String> test = new ArrayQueue<String>(3);

		assertTrue(test.isEmpty());
		
		test.enqueue("a");
		
		assertFalse(test.isEmpty());
	}

}

/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests LinkedStack
 * @author Nathan Cornelison
 */
public class LinkedStackTest {

	/**
	 * Tests the push and pop functions
	 */
	@Test
	public void testPushPop() {
		LinkedStack<String> test = new LinkedStack<String>(4);
		
		test.push("a");
		assertEquals(1, test.size());
		
		test.push("b");
		test.push("c");
		assertEquals(3, test.size());
		assertEquals("c", test.pop());
		assertEquals("b", test.pop());
		assertEquals("a", test.pop());
		
		test.push("a");
		test.push("b");
		test.push("c");
		test.push("d");
		try {
			test.push("f");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, test.size());
		}
		
		test.pop();
		test.pop();
		test.pop();
		test.pop();
		
		try {
			test.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(0, test.size());
		}
	}
	
	/**
	 * Tests set capacity
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<String> test = new LinkedStack<String>(4);
		
		test.push("a");
		test.push("b");
		
		test.setCapacity(2);
		
		try {
			test.push("c");
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

}

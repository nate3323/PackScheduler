package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Defines the methods needed for creating a stack
 * @author Nathan Cornelison
 * @param <E> Generic type being stored
 */
public interface Stack<E> {
	
	/**
	 * Adds the element to the top of the Stack
	 * @param element the element to be added
	 */
    void push(E element);
    
    /**
     * Removes and returns the element at the top of the stack
     * @return the element that was removed
     */
    E pop();
    
    /**
     * Checks to see if the stack is empty
     * @return if the stack is empty
     */
    boolean isEmpty();
    
    /**
     * The number of elements in a stack
     * @return the number of elements in the stack
     */
    int size();
    
    /**
     * Sets the stack's capacity
     * @param capacity the stack's capacity
     */
    void setCapacity(int capacity);
}

package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Defines the methods needed for creating a queue
 * @author Nathan Cornelison
 * @param <E>
 */
public interface Queue<E> {
	/**
	 * Adds an element to the back of the queue
	 * @param element Element being added to the queue
	 */
    public void enqueue(E element);
    /**
     * Removes and returns the element at the front of the list
     * @return Returns and removes the first item in the queue
     */
    public E dequeue();
    /**
     * Returns true if the queue is empty
     * @return True if the queue is empty
     */
    public boolean isEmpty();
    /**
     * Returns the number of elements in the queue
     * @return Size of the queue
     */
    public int size();
    /**
     * sets the queue's capacity
     * @param capacity Capacity being set
     */
    public void setCapacity(int capacity);
}

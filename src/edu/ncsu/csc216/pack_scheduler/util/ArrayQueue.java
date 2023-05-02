package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Creates using an ArrayList as the base
 * @author Nathan Cornelison
 * @param <E> Generic object being stored
 */
public class ArrayQueue<E> implements Queue<E> {

	private int capacity;
	private int size;
	private ArrayList<E> list;
	
	/**
	 * Constructs the queue
	 * @param capacity Capacity of the queue
	 */
	public ArrayQueue(int capacity) {
		setCapacity(capacity);
		this.size = 0;
		list = new ArrayList<E>();
	}
	
	@Override
	public void enqueue(E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(size, element);
		size++;
	}

	@Override
	public E dequeue() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		size--;
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}

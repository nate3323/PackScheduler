package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Creates a stack using a LinkedList as the base
 * @author Nathan Cornelison
 * @param <E> Generic type being store
 */
public class LinkedStack<E> implements Stack<E> {

	private int capacity;
	private int size;
	private LinkedAbstractList<E> list;
	
	/**
	 * Initializes the stack with a given capacity
	 * @param capacity Initial capacity of the stack
	 */
	public LinkedStack(int capacity) {
		this.list = new LinkedAbstractList<E>(capacity);
		this.size = 0;
		setCapacity(capacity);
		
	}
	
	@Override
	public void push(E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(size, element);
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E element = list.remove(size - 1);
		size--;
		return element;
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
		list.setCapacity(capacity);
		this.capacity = capacity;
	}
}
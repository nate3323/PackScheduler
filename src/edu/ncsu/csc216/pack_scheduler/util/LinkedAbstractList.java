package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a generic LinkedList
 * @param <E> takes in a generic type
 * @author Nathan Cornelison
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	private ListNode<E> front;
	private int size;
	private int capacity;
	
	/**
	 * Constructs an empty linked list
	 * @param capacity Maximum capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		setCapacity(capacity);
		size = 0;
		front = null;
	}
	
	@Override
	public void add(int index, E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> current = front;
		for (int i = 0; i < size; i++) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if (front == null) {
			front = new ListNode<E>(element);
		} else if (index == 0) {
			ListNode<E> a = new ListNode<E>(element, front);
			front = a;
		} else {
			current = front;
			ListNode<E> l = front;
			for (int i = 0; i < index; i++) {
				l = current;
				current = current.next;
			}
			ListNode<E> a = new ListNode<E>(element, current);
			l.next = a;
		}
		size++;
	}
	
	/**
	 * Removes item at the given index
	 * @param index Index of item being removed
	 * @return The removed object
	 */
	public E remove (int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> temp = null;
		if (index == 0) {
			temp = front;
			front = front.next;
		} else {
			ListNode<E> current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;	
			}
			temp = current.next;
			current.next = current.next.next;
		}
		size--;
		return temp.data;
	}
	
	@Override
	public E set (int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> current = front;
		for (int i = 0; i < size; i++) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E re = current.data;
		current.data = element;
		return re;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		ListNode<E> current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets the capacity for the list
	 * @param capacity New capacity for the list
	 */
	public void setCapacity(int capacity) {
		if(size != 0 && (capacity < 0 || capacity < size)) {
				throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	/**
	 * Node in the LinkedAbstractList
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 * @param <T>
	 */
	private class ListNode<T> {
		
		private T data;
		private ListNode<T> next;
		
		private ListNode(T data) {
			this.data = data;
			next = null;
		}
		
		private ListNode(T data, ListNode<T> next) {
			this.data = data;
			this.next = next;
		}
	}
}

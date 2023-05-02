/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Creates a recursive linked list
 * @author Nathan Cornelison
 * @param <E> Generic type taken in
 */
public class LinkedListRecursive<E> {

	private int size;
	private ListNode front;
	
	/**
	 * Initiates the linked list
	 */
	public LinkedListRecursive() {
		size = 0;
		front = null;
	}
	
	/**
	 * Checks to see if the list if empty
	 * @return True if the list is empty
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Gets the size of the list
	 * @return size of list
	 */
	public int size() {
		return size;
		
	}
	
	/**
	 * Adds an element to the end of the list
	 * @param element Element being added to the list
	 * @return True if the element can be added
	 */
	public boolean add(E element) {
		
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			front = new ListNode(element, null);
		} else {
			front.add(size, element);
		}
		size++;
		return true;
		
	}
	
	/**
	 * Adds an element to a specific index
	 * @param index Index element is being added at
	 * @param element element being added
	 */
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (isEmpty()) {
			front = new ListNode(element, null);
		} else if (index == 0) {
			ListNode temp = front;
			front = new ListNode(element, temp);
		} else {
			front.add(index, element);
		}
		size++;
	}
	
	/**
	 * Gets an element at a specific element
	 * @param index Index of the element
	 * @return Element at given index
	 */
	public E get(int index) {
		if (index > size || index < 0 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(index);
	}
	
	/**
	 * Removes the given element
	 * @param element Element being removed
	 * @return Element that was removed
	 */
	public boolean remove(E element) {
		if (element == null) {
			return false;
		}
		if (front == null || isEmpty()) {
			return false;
		}
		if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		front.remove(element);
		size--;
		return true;
		
	}
	
	/**
	 * Removes an element at a given index
	 * @param index Index of element being removed
	 * @return Element that was removed
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			E element = front.data;
			front = front.next;
			size--;
			return element;
		} else {
			size--;
			return front.remove(index);
		}
	}
	
	/**
	 * Sets the element at an index to a given element
	 * @param index Index of element being set
	 * @param element Element that the index is being changed to
	 * @return Element that was originally at the index
	 */
	public E set(int index, E element) {
		if (index > size || index < 0 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		return front.set(index, element);
	}
	
	/**
	 * Checks to see if the list contains a given element
	 * @param element Element being checked for
	 * @return True if the element is in the list
	 */
	public boolean contains(E element) {
		if(isEmpty() || front == null){
			return false;
		}
		return front.contains(element);
	}
	
	/**
	 * Creates a node for the list
	 * @author gnswain
	 * @author dncornel
	 * @author dpmoody
	 */
	private class ListNode {
		E data;
		public ListNode next;
		
		public void add(int index, E element) {
			if (index < 0) {
				throw new IndexOutOfBoundsException();
			}
			if (index == 1) {
				ListNode n = new ListNode(element, next);
				this.next = n;
			} else {
				if(index == 0 && next != null) {
					ListNode n = next.next;
					next.next = new ListNode(element, n);
				}
				if(next == null) {
					next = new ListNode(element, null);
				} else {
				    next.add(--index, element);
				}
			}
		}
		
		public E get(int index) {
			if (index == 0) {
				return data;
			}
			return next.get(--index);	
		}
		
		public E remove(int index) {
			if (index == 1) {
				E element = next.data;
				next = next.next;
				return element;
			} else {
				return next.remove(--index);
			}
			
		}
		
		public boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data == element) {
				next = next.next;
				return true;
			} else {
				next.remove(element);
				return false;
			}
			
		}
		
		public E set(int index, E element) {
			if (index == 0) {
				E temp = data;
				data = element;
				return temp;
			} else {
				return next.set(--index, element);
			}
		}
		
		public boolean contains(E element) {
			if(data == element) {
				return true;
			}
			if(next == null) {
				return false;
			}
			return next.contains(element);
		}
		
		public ListNode(E element, ListNode node) {
			this.data = element;
			this.next = node;
		}
		
		
	}
	
	
}

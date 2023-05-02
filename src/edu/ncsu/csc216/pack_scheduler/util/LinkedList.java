/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of linked list that does not allow null or duplicate items
 * @author Nathan Cornelison
 * @param <E>
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	private ListNode front;
	private ListNode back;
	private int size;
	
	/**
	 * Creates a new inked List with a front and back
	 */
	public LinkedList() {
		this.size = 0;
		front = new ListNode(null);
		back = new ListNode(null);
		
		front.next = back;
		back.prev = front;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> lit = new LinkedListIterator(index);
		return lit;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		listIterator(index).add(element);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		E e = this.get(index);
		listIterator(index).set(element);
		return e;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#size()
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(listIterator(index).hasNext()) {
		    return this.listIterator(index).next();
		}
		throw new IndexOutOfBoundsException("Index: 0");
	}
	
	
	/**
	 * Creates a list node that stores previous and next values
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class ListNode {
		
	    E data;
	    public ListNode prev;
	    public ListNode next;
	    
		public ListNode(E data) {
			this.data = data;
		}
		
		public ListNode(E data, ListNode prev , ListNode next) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
	}
	
	/**
	 * Implements standard list methods in terms of an iterator
	 * @author Graham Swain
	 * @author Daniel Moody
	 * @author Grant Joiner
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		public ListNode previous;
		public ListNode next;
		int previousIndex;
		int nextIndex;
		private ListNode lastRetrieved;
		
		public LinkedListIterator(int index) {
			if(index < -1 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			if (index == 0) {
				previousIndex = 0;
				nextIndex = 1;
				lastRetrieved = front;
			} 
			previous = front;
			next = previous.next;
			for(int i = 0; i < index; i++) {
				this.next();
			}
			//next = current.next;
		    //previous = current;
		}

		@Override
		public boolean hasNext() {
			if (this.next.data != null) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			next = next.next;
			previous = previous.next;
			nextIndex++;
			previousIndex++;
			lastRetrieved = next.prev;
			return previous.data;
		}

		@Override
		public boolean hasPrevious() {
//			if (this.previous.data != null) {
//   			return true;
//			}
			return false;
		}

		@Override
		public E previous() {
//		if (previous.prev.data == null) {
//				throw new NoSuchElementException();
//			}
//			previous = previous.prev;
//			next = next.prev;
//			nextIndex--;
//			previousIndex--;
//			lastRetrieved = previous.next;
			return previous.prev.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalArgumentException();
			}
			size--;
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;
			lastRetrieved.data = null;
		}

		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalArgumentException();
			}
			if (e == null) {
				throw new NullPointerException();
			}
			lastRetrieved.next.data = e;
		}

		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			} 
			
		    ListNode node = new ListNode(e, previous, next);
		    lastRetrieved = null;
		    previous.next = node;
		    next.prev = node;
		    nextIndex++;
		    previousIndex++;
			size++;
		}
		
	}

}

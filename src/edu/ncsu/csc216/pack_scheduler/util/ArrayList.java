package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 *  Custom ArrayList Class
 * @author Nathan Cornelison
 *
 * @param <E> type of arrayList
 */
public class ArrayList<E> extends AbstractList<E> {

	private static final int INIT_SIZE = 10;
	
	private E[] list;
	
	private int size;
	
	/**
	 * Constucts a new arrayList for courses
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}
	
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < list.length; i++) {
			if (element.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		if (size == list.length) {
			growArray();
		}
		if (size == 0) {
			list[0] = element;
		} else {
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
			list[index] = element;
		}
		size++;
	}
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = (E[]) new Object[list.length * 2];
		for (int i = 0; i < list.length; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}
	
	@Override
	public E remove (int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E re = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];	
		}
		list[size - 1] = null;
		size--;
		return re;
	}
	
	@Override
	public E set (int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < list.length; i++) {
			if (element.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		E re = list[index];
		list[index] = element;
		return re;
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		return list[index];
	}

	@Override
	public int size() {
		return size;
	}

}

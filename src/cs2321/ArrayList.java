package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	
	//TODO: add class members
	private E[] theBoneZone;
	private int size = 0;
	private int capacity = 16;
	
	private class ArrayListIterator implements Iterator<E> {
		
		private int hmm = 0;
		
		@Override
		public boolean hasNext() {
			return hmm < size;
		}

		@Override
		public E next() {
			return theBoneZone[hmm++];	// Post increment to prep for another call
		}
		
	}

	public ArrayList() {
		// Default capacity: 16
		theBoneZone = (E[]) new Object[capacity];
	}

	public ArrayList(int cap) {
		// create an array with the specified capacity
		theBoneZone = (E[]) new Object[cap];
	}
	
	@Override
	public int size() {
		return size;
	}

	// Return the current capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		if (size == capacity) {
			int temp = capacity * 2;
			capacity = temp;
			return capacity;
		}
		return capacity;
	}
		
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return theBoneZone[i];
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = theBoneZone[i];
		theBoneZone[i] = e;
		return temp;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == capacity) {resize(capacity * 2);}
		
		for (int j = size - 1; j >= i; j--) {
			theBoneZone[j+1] = theBoneZone[j];	// Shifts elements to make room
		}
		theBoneZone[i] = e;
		size++;
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		if (isEmpty()) {
			return (E) "Array is empty";
		}
		E temp = theBoneZone[i];
		for (int j = i; j < size - 1; j++) {
			theBoneZone[j] = theBoneZone[j+1];	// Shifts elements to fill the hole
		}
		theBoneZone[size-1] = null;
		size--;
		return temp;
	}


	public void addFirst(E e)  {
		// create new variables to hold information being passed in to prevent stack overflows
		if (size == capacity) {resize(capacity * 2);}
		
		add(size - size, e);	// I can't believe this worked and it's really funny
	}
	
	public void addLast(E e)  {
		// create new variables to hold information being passed in to prevent stack overflows
		if (size == capacity) {resize(capacity * 2);}
		
		add(size, e);
	}
	
	public E removeFirst() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			return (E) "Array is empty";
		}
		E temp = theBoneZone[0];
		for (int i = 0; i < size - 1; i++) {
			theBoneZone[i] = theBoneZone[i+1];	// Shifts elements to fill the hole
		}
		theBoneZone[size-1] = null;
		size--;
		return temp;
	}
	
	public E removeLast() throws IndexOutOfBoundsException {
		if (isEmpty()) {
			return (E) "Array is empty";
		}
		E temp = theBoneZone[size-1];
		for (int i = 1; i < size + 1; i++) {
			theBoneZone[i] = theBoneZone[i-1];
		}
		theBoneZone[size-1] = null;
		size--;
		return temp;
	}
	
	private void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n) {
			// Checks the index to make sure it's within [0, n-1]
			throw new IndexOutOfBoundsException("Illegal index" + i);
		}
	}
	
	private void resize(int cap) {
		E[] temp = (E[]) new Object[cap];
		for (int i = 0; i < size(); i++) {
			temp[i] = theBoneZone[i];
		}
		
		theBoneZone = temp;
		capacity = cap;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
}

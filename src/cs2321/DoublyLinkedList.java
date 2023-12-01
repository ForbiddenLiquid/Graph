package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {
	
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	/* 
	 * Node class which should contain element and pointers to previous and next nodes
	 */
	private static class Node<E> implements Position<E> {
		
		private E element;		// Element stored in the current node
		private Node<E> prev;	// Element stored in previous node in the list
		private Node<E> next;	// Element stored in the next node in the list
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		@Override
		public E getElement() {
			if (next == null) {
				throw new IllegalStateException("Position no longer valid");
			}
			return element;
		}
		
		public void setElement(E e) {
			element = e;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setPrev(Node<E> p) {
			prev = p;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	
	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}
	
	// Helper Methods
	// Validates the position and returns it as a node
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p;	// Safe cast
		if (node.getNext() == null) {
			throw new IllegalArgumentException("p is no longer in the list");
		}
		return node;
	}

	// Returns the given node as a Position, or null if it's a sentinel
	private Position<E> position(Node<E> node) {
		if (node == header || node == trailer) {
			return null;	// does not expose to sentinels
		}
		return node;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() {
		if (isEmpty()) {
			return null;
		}
		return position(header.getNext());
	}

	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);	// Creates and links a new node
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}
	
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());	// Right after header
	}

	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		if (isEmpty())
			return (E) "Array is empty";
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		
		E answer = node.getElement();
		node.setElement(null);	// Helps with garbage collection
		node.setNext(null);		// and convention for defunct node
		node.setPrev(null);
		return answer;
	}


	public E removeFirst() throws IllegalArgumentException {
		if (isEmpty())
			return (E) "Array is empty";
		return remove(first());		// I can't believe this works
	}
	
	public E removeLast() throws IllegalArgumentException {
		if (isEmpty())
			return (E) "Array is empty";
		return remove(last());
	}
	


	/*
	 * Returns an array containing all of the elements in this list 
	 * in proper sequence (from first to last element).
	 * The returned array will be "safe" in that no references to it are maintained by this list. 
	 * (In other words, this method must allocate a new array). 
	 * The caller is thus free to modify the returned array.
	*/

	public Object [] toArray() {
		Object[] theRot;
		theRot = new Object[size];
		Position<E> temp = first();			
		for (int i = 0; i < size; i++) {	// Iterates each node of the list onto an array
			
			theRot[i] = temp.getElement();
			temp = after(temp);
		}
		return theRot;
	}
	

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/*
	 *Element iterator will return one element at a time  
	 */
	private class ElementIterator implements Iterator<E> {
		
		
		
		private Position<E> joob = first();		// Index of the next element to report

		@Override
		public boolean hasNext() {
			return joob != null;		// Size is field of outer distance
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException("No more elements.");
			
			E e = joob.getElement();
			joob = after(joob);
			return e;	// figure out why this won't take ArrayList data
		}
	}
	


	
	/************************************************************************
	 * 
	 * The method positions(), PositionInterable class and PositionIterator class
	 * have been fully implemented.  
	 * It depends the methods first(), after() that you are implementing. 
	 *
	 ************************************************************************/

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}
	
	/*
	 * Position iterator will return one Position at a time  
	 */
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
		
	}
	
	private class PositionIterator implements Iterator<Position<E>> {
		Position<E> p=first();
		
		@Override
		public boolean hasNext() {
			return p!=null;
		}

		@Override
		public Position<E> next() {
			Position<E> cur = p;
			p = after(p);
			return cur;
		}
		
	}

}

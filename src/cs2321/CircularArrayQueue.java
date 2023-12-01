/**
 * 
 */
package cs2321;

import net.datastructures.Queue;

/**
 * @author ruihong-adm
 * @param <E>
 *
 */

public class CircularArrayQueue<E> implements Queue<E> {

	int CAPACITY = 9999;
	private E[] Queue;
	private int f = 0;
	private int sz = 0;

	public CircularArrayQueue(int queueSize) {
		
		Queue = (E[]) new Object[queueSize];
		CAPACITY = queueSize;
	}

	@Override
	public int size() {
		// Returns the current size of the queue
		return sz;
	}

	@Override
	public boolean isEmpty() {
		// Returns true or false depending on if the queue is empty
		return (sz == 0);
	}


	@Override
	public E first() {

		if (isEmpty()) {
			return null;
		}

		// Returns the first element of the queue
		return Queue[f];
	}

	@Override
	public E dequeue() {

		if (isEmpty()) {
			return null;
		}

		// Removes and returns the first element of the queue
		E answer = Queue[f];
		Queue[f] = null;
		f = (f + 1) % CAPACITY;
		sz--;

		return answer;
	}

	/* Throw the exception IllegalStateException when the queue is full */
	@Override
	public void enqueue(E e) throws IllegalStateException {
		if (sz == CAPACITY) throw new IllegalStateException("Queue is full");

		// Inserts an item at the rear of the queue
		int avail = (f + sz) % CAPACITY;
		Queue[avail] = e;
		sz++;
	}   
}

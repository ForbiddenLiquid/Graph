package cs2321;

import net.datastructures.Stack;

public class LinkedListStack<E> implements Stack<E> {

	private class ListNode<E> {
		E val;
		ListNode next;

		public ListNode(E val) {
			this.val = val;
		}
	}

	int size = 0;
	ListNode<E> top = null;

	@Override
	public int size() {
		//huh, neat
		return size;
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
	public void push(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		ListNode<E> temp = new ListNode<>(e);
		temp.next = top;
		top = temp;

		size++;
		return;
	}

	@Override
	public E top() {
		return top.val;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E deleteItem = top.val;
		top = top.next;
		size--;
		return deleteItem;
	}
}

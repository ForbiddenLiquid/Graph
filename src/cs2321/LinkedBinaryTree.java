package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	

/**
 * @author ruihong-adm
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{
	
	//LinkedBinaryTree instance variables
	private Node<E> root = null;	// Root of the tree
	private int size = 0;			// Number of nodes in the tree

	public  LinkedBinaryTree( ) {	// Constructs an empty binary tree
		
	}
	
	// Function to create a new node storing element e
	private Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
	}

	// Identifies objects that share a parent object and returns them
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = parent(p);
		if (parent == null)		// p is root
			return null;
		if (p == left(parent))	// p is a left child
			return right(parent);
		else					// p is a right child
			return left(parent);
	}
	
	// Returns the number of children of position p
	public int numChildren(Position<E> p) {
		int count = 0;
		if (left(p) != null)
			count++;
		if (right(p) != null)
			count++;
		return count;
	}
	
	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> snapshot = new ArrayList<>(2);	// Array of capacity 2
		if (left(p) != null)
			snapshot.add(size, left(p));
		if (right(p) != null)
			snapshot.add(size, right(p));
		return snapshot;
			
	}
	
	// Nested Node class
	private static class Node<E> implements Position<E> {
		private E element;		// Element stored at this node
		private Node<E> parent;	// Reference to the parent node
		private Node<E> left;	// Reference to the left child
		private Node<E> right;	// Reference to the right child
		
		// Constructs a node with the given element and neighbors
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
		
		// Getter methods
		public E getElement() {return element;}
		public Node<E> getParent() {return parent;}
		public Node<E> getLeft() {return left;}
		public Node<E> getRight() {return right;}
		
		// Setter methods
		public void setElement(E e) {element = e;}
		public void setParent(Node<E> parentNode) {parent = parentNode;}
		public void setLeft(Node<E> leftChild) {left = leftChild;}
		public void setRight(Node<E> rightChild) {right = rightChild;}
	}
	
	@Override
	public Position<E> root() {	// Returns the root position of the tree
		return root;
	}
	
	
	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}
	
	@Override
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		return numChildren(p) == 0;
	}
	
	// Returns whether a node is the root
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		return p == root();
	}

	public int depth(Position<E> p) {
		if (isRoot(p)) {
			return 0;
		}
		else
			return 1 + depth(parent(p));
	}
	
	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> c : children(p)) {
			h = Math.max(h, 1 + height(c));
		}
		return h;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
		
	
	/* Set element in p.
	 * @return the old element in p. 
	 */
	public E setElement(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node)
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	
	/**
	 * If p has two children, throw IllegalAugumentException. 
	 * If p is an external node ( that is it has no child), remove it from the tree.
	 * If p has one child, replace it with its child. 
	 * If p is root node, update the root accordingly. 
	 * @param p who has at most one child. 
	 * @return the element stored at position p if p was removed.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if (child != null)
			child.setParent(node.getParent());	// Child's grandparent becomes its parent
		if (node == root)
			root = child;						// Child becomes root
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		
		size--;
		E temp = node.getElement();
		node.setElement(null);		// Help garbage collection
		node.setLeft(null);
		node.setRight(null);
		node.setParent(null);
		return temp;
	}
	
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the in-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> inOrderElements(Position<E> p) {
		
		ArrayList<E> list = new ArrayList<E>();	// Generates output list
		inOrder(root(), list);					// Calls helper method
		return list;							// Returns tree traversal order
	}
	
	//	Helper method for inOrder traversal
	private void inOrder(Position<E> p, ArrayList<E> theFog) {
		if (p == null)
			return;
		inOrder(left(p), theFog);	// Recursive call to left child
		theFog.addLast(p.getElement());			// Visits p
		inOrder(right(p), theFog);	// Recursive call to right child
	}
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the pre-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> preOrderElements(Position<E> p) {
		ArrayList<E> list = new ArrayList<E>(); 
		preOrder(root(), list);
		return list;
	}
	
	private void preOrder(Position<E> p, ArrayList<E> list) {
		if (p == null)
			return;
		list.addLast(p.getElement());	// Visits p
		preOrder(left(p), list);		// Recursive call to the left
		preOrder(right(p), list);		// Recursive call to the right
	}
	
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the post-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> postOrderElements(Position<E> p) {
		ArrayList<E> list = new ArrayList<E>();
		postOrder(root(), list);
		return list;
	}
	
	private void postOrder(Position<E> p, ArrayList<E> list) {
		if (p == null)
			return;
		postOrder(left(p), list);		// Recursive call to the left
		postOrder(right(p), list);		// Recursive call to the right
		list.addLast(p.getElement());	// Visits p
	}
	
	/**
	 * Return the elements in the subtree of node P, including the data in node P. 
	 * The data in the return list need to match the level-order traversal.  
	 * @param p who has at most one child. 
	 * @return the List of elements in subtree of P following the in-order traversal. 
	 */
	public List<E> levelOrderElements(Position<E> p) {
		ArrayList<E> list = new ArrayList<E>();
		CircularArrayQueue<Node<E>> queue = new CircularArrayQueue<Node<E>>(size);
		levelOrder(root(), queue, list);
		return list;
	}
	
	private void levelOrder(Position<E> p, CircularArrayQueue<Node<E>> queue, ArrayList<E> list) {
		if (p == null)		// Checks to see if the inserted node has any data
			return;

		Node<E> node = validate(p);
		
		queue.enqueue(node);	// Adds root of the tree to start the while loop
		while (!queue.isEmpty()) {
			Node<E> item = queue.dequeue();			// Removes the first element of the queue and returns it
			list.addLast(item.getElement());
			if (item.getLeft() != null)
				queue.enqueue(item.getLeft());		// Adds left child to the queue
			if (item.getRight() != null)
				queue.enqueue(item.getRight());		// Adds right child to the queue
		}
	}


}

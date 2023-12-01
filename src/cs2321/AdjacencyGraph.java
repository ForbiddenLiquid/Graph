package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjacencyGraph<V, E> implements Graph<V, E> {

	private static class myVertex<V,E> implements Vertex<V> {
		
		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		
		// Constructs new inner vertex instance storing given element
		public myVertex(V elem, boolean graphIsDirected) {
			element = elem;
			outgoing = new HashMap<>();
			if (graphIsDirected)
				incoming = new HashMap<>();
			else
				incoming = outgoing;			// if undirected assigns alias to outgoing map
		}

		@Override
		public V getElement() {		// returns element assiociated with vertex
			return element;
		}
		public void setPosition(Position<Vertex<V>> p) {	// sets position of vertex
			pos = p;
		}
		public Position<Vertex<V>> getPosition() {			// gets position of vertex
			return pos;
		}
		public Map<Vertex<V>, Edge<E>> getOutgoing() {		// returns reference to outgoing edges
			return outgoing;
		}
		public Map<Vertex<V>, Edge<E>> getIncoming() {		// returns reference to incoming edges
			return incoming;
		}
	}
	
	private static class myEdge<V,E> implements Edge<E> {
		
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;
		
		// Constructs inner edge instance from u to v storing the element
		public myEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[]{u,v};	// array of length 2
		}

		@Override
		public E getElement() {		// returns the element associated with the edge
			return element;
		}
		public Vertex<V>[] getEndpoints() {		// gets reference to endpoint array
			return endpoints;
		}
		public void setPosition(Position<Edge<E>> p) {		// sets position of edge
			pos = p;
		}
		public Position<Edge<E>> getPosition() {			// gets position of the edge
			return pos;
		}
	}
	
	// Validates vertices
	public myVertex<V,E> validateVert(Vertex<V> v) {
		if (!(v instanceof Vertex)) throw new IllegalArgumentException();
		return (myVertex<V,E>) v;
	}
	
	// Validates edges
	public myEdge<V,E> validateEdge(Edge<E> e) {
		if (!(e instanceof Edge)) throw new IllegalArgumentException();
		return (myEdge<V,E>) e;
	}
	
	// Initiallizes variables and arrays
	private boolean isDirected;
	private DoublyLinkedList<Vertex<V>> vertices = new DoublyLinkedList<>();
	private DoublyLinkedList<Edge<E>> edges = new DoublyLinkedList<>();			// re-write constructors to implement adjacency map
	// One for vertices and one for edges taking in <vertex,edge> pairs
	
	// constructs an empty graph
	// @Timed-Complexity: O(1)
	// Only runs once
	public AdjacencyGraph(boolean directed) {
		isDirected = directed;
	}

	// Non-directed graph constructor
	// @Timed-Complexity: O(1)
	// Only runs once
	public AdjacencyGraph() {
		isDirected = false;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 * 
	 * @Timed-Complexity: O(m)
	 * runs for every edge in the graph
	 */
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 * 
	 * @Timed-Complexity: O(2n)
	 * Runs for the ends of each vertex
	 */
	// returns the vertices of edge e as an array of length two
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = validateEdge(e);
		return edge.getEndpoints();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 * 
	 * @Timed-Complexity: O(1)
	 * Runs only once
	 */
	// inserts and returns new edge between u and v while storing given element
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
		if (getEdge(u,v) == null) {
			myEdge<V,E> e = new myEdge<>(u, v, o);
			e.setPosition(edges.addLast(e));
			myVertex<V,E> origin = validateVert(u);
			myVertex<V,E> destination = validateVert(v);
			origin.getOutgoing().put(v, e);
			destination.getIncoming().put(u, e);
			return e;
		}	else
			throw new IllegalArgumentException("Edge from u to v exist");
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 * 
	 * @Timed-Complexity: O(1)
	 * Runs only once
	 */
	// inserts new vertex into the graph
	public Vertex<V> insertVertex(V o) {
		myVertex<V,E> v = new myVertex<>(o, isDirected);
		v.setPosition(vertices.addLast(v));
		return v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 * 
	 * @Timed-Complexity: O(1)
	 * only runs once
	 */
	public int numEdges() {		// returns number of edges in graph
		return edges.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 * 
	 * @Timed-Complexity: O(1)
	 * only ever runs once
	 */
	public int numVertices() {	// returns number of vertices in graph
		return vertices.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	// returns the vertex opposite vertex v on edge e
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
		myEdge<V,E> edge = validateEdge(e);
		Vertex<V>[] endpoints = edge.getEndpoints();
		if (endpoints[0] == v)
			return endpoints[1];
		if (endpoints[1] == v)
			return endpoints[0];
		else
			throw new IllegalArgumentException("v is not incident to this edge");
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 * 
	 * @Timed-Complexity: O(1)
	 * Only removes the edge
	 */
	// removes edge and all its incident vertices
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = validateEdge(e);
		if (edge.getElement() != null ) {
			Vertex<V>[] v = edge.getEndpoints();
		
			myVertex<V,E> vert = validateVert(v[0]);
			myVertex<V,E> vert1 = validateVert(v[1]);
			vert.getOutgoing().remove(vert1);
			vert1.getIncoming().remove(vert);
			edges.remove(edge.getPosition());
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 * 
	 * @Timed-Complexity: O(m,n^2)
	 * Removes edges of vertex and all hashes for the vertex
	 */
	// removes a vertex and all its incident edges from the graph
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);
		for (Edge<E> e : vert.getOutgoing().values())		// for - each loop keeps stopping on the node with null pointers
			removeEdge(e);									// instead of the node containing data
		for (Edge<E> e : vert.getIncoming().values())
			removeEdge(e);
		vertices.remove(vert.getPosition());
	}

	/* 
     * replace the element in edge object, return the old element
     */
	// replaces a selected edge with a new edge
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		myEdge<V,E> edge = validateEdge(e);
		//replace edge data with object o
		return edge.getElement();
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	// replaces a selected vertex with a new vertex
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);
		// replace vertex data with object o
		return vert.getElement();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 * 
	 * @Timed-Complexity: O(n)
	 * runs for every vertex in the graph
	 */
	public Iterable<Vertex<V>> vertices() {		// returns vertices of the graph as iterable collection
		return vertices;
	}

	// returns number of edges for vertex origin v
	// @Timed-Complexity: O(m,n)
	// Runs for every vertex and edge
	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);
		return vert.getOutgoing().size();
	}

	// returns number of edges for destination v
	// @Timed-Complexity: O(m,n)
	// Runs for every vertex and edge
	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);
		return vert.getIncoming().size();
	}

	// returns iterable collection of edges from v
	// @Timed-Complexity: O(m,n)
	// Runs for every vertex and edge
	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);		// Apparently no vertices are getting their outgoing edge count updated
		return vert.getOutgoing().values();
	}

	// returns iterable collection of edges for v
	// @Timed-Complexity: O(m,n)
	// Runs for every vertex and edge
	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> vert = validateVert(v);
		return vert.getIncoming().values();
	}

	// returns the edge from u to v, or null if they're not adjacent
	// @Timed-Complexity: O(1)
	// Only ever runs once
	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
		myVertex<V,E> origin = validateVert(u);
		return origin.getOutgoing().get(v);
	}
	
}

import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;

@jug.SuiteName("UndirectedGraphTest")
public class UndirectedGraphTest {

	private AdjacencyGraph<String, Integer> TARGET = init();
	private AdjacencyGraph<String, Integer> T = init();

	public AdjacencyGraph<String, Integer> init() {
		return new AdjacencyGraph<String, Integer>();
	}

	@Before
	public void setup() throws Throwable {
		
	}

	@org.junit.Test( )
	@jug.TestName("Verifying insertVertex method")
	public void Test1() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("test");
		
		org.junit.Assert.assertEquals("Verifying insertVertex method", (Object)(false), (Object)(v1.getElement()==null));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying insertEdge method")
	public void Test2() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		
		org.junit.Assert.assertEquals("Verifying insertEdge method", (Object)(false), (Object)(e1.getElement()==null));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying removeVertex method")
	public void Test3() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		TARGET.removeVertex(v1);
		
		org.junit.Assert.assertEquals("Verifying removeVertex method", (Object)(0), (Object)(TARGET.outDegree(v1)));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying removeEdge method")
	public void Test4() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		TARGET.removeEdge( e1 );
		
		org.junit.Assert.assertEquals("Verifying removeEdge method", (Object)(0), (Object)(TARGET.numEdges()));
	}
	
	/*
	 *  
	 * The following test cases. Fully connected undirected graph of 5 nodes
	 * 
	 */

	@org.junit.Test( )
	@jug.TestName("Verifying edges() size equals 10")
	public void Test5() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Edge<Integer>> edges = TARGET.edges();
		for(Edge e : edges)
        size++;
		
		org.junit.Assert.assertEquals("Verifying edges() size equals 10", (Object)(10), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying numEdges() size equals 10")
	public void Test6() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying numEdges() size equals 10", (Object)(10), (Object)(TARGET.numEdges()));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying vertices() size equals 5")
	public void Test7() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Vertex<String>> vertices = TARGET.vertices();
		for(Vertex v : vertices)
            size++;
		
		org.junit.Assert.assertEquals("Verifying vertices() size equals 5", (Object)(5), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying numVertices() size equals 1")
	public void Test8() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying numVertices() size equals 1", (Object)(5), (Object)(TARGET.numVertices()));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying incomingEdges(\"alice\") size equals 4")
	public void Test9() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Edge<Integer>> edges = TARGET.incomingEdges(v1);
		for(Edge edge : edges)
      	size++;
		
		org.junit.Assert.assertEquals("Verifying incomingEdges(\"alice\") size equals 4", (Object)(4), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying incomingEdges(\"bob\") size equals 4")
	public void Test10() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Edge<Integer>> edges = TARGET.incomingEdges(v2);
		for(Edge edge : edges)
      	size++;
		
		org.junit.Assert.assertEquals("Verifying incomingEdges(\"bob\") size equals 4", (Object)(4), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying inDegree in (\"alice\") equals 4")
	public void Test11() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying inDegree in (\"alice\") equals 4", (Object)(4), (Object)(TARGET.inDegree(v1)));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying inDegree in (\"bob\") equals 4")
	public void Test12() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying inDegree in (\"bob\") equals 4", (Object)(4), (Object)(TARGET.inDegree(v2)));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying outDegree in (\"alice\") equals 4")
	public void Test13() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying outDegree in (\"alice\") equals 4", (Object)(4), (Object)(TARGET.outDegree(v1)));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying outDegree in (\"bob\") equals 4")
	public void Test14() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		
		org.junit.Assert.assertEquals("Verifying outDegree in (\"bob\") equals 4", (Object)(4), (Object)(TARGET.outDegree(v2)));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying outgoingEdges(\"alice\") size equals 4")
	public void Test15() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Edge<Integer>> edges = TARGET.outgoingEdges(v1);
		for(Edge edge : edges)
          size++;
		
		org.junit.Assert.assertEquals("Verifying outgoingEdges(\"alice\") size equals 4", (Object)(4), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("Verifying outgoingEdges(\"bob\") size equals 4")
	public void Test16() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );
		int size = 0;
		Iterable<Edge<Integer>> edges = TARGET.outgoingEdges(v2);
		for(Edge edge : edges)
          size++;
		
		org.junit.Assert.assertEquals("Verifying outgoingEdges(\"bob\") size equals 4", (Object)(4), (Object)(size));
	}

	@org.junit.Test( )
	@jug.TestName("removeVertex equals 4")
	public void Test17() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );

		TARGET.removeVertex(v1);
		
		org.junit.Assert.assertEquals("Verifying edges size equals 6", (Object)(6), (Object)TARGET.numEdges());
	}

	@org.junit.Test( )
	@jug.TestName("removeVertex equals 3")
	public void Test18() throws Throwable {
		Vertex<String> v1 = TARGET.insertVertex("alice");
		Vertex<String> v2 = TARGET.insertVertex("bob");
		Vertex<String> v3 = TARGET.insertVertex("carol");
		Vertex<String> v4 = TARGET.insertVertex("dave");
		Vertex<String> v5 = TARGET.insertVertex("eve");
		Edge<Integer> e1 = TARGET.insertEdge( v1, v2, 2 );
		Edge<Integer> e2 = TARGET.insertEdge( v1, v3, 2 );
		Edge<Integer> e3 = TARGET.insertEdge( v1, v4, 2 );
		Edge<Integer> e4 = TARGET.insertEdge( v1, v5, 2 );
		Edge<Integer> e5 = TARGET.insertEdge( v2, v3, 2 );
		Edge<Integer> e6 = TARGET.insertEdge( v2, v4, 2 );
		Edge<Integer> e7 = TARGET.insertEdge( v2, v5, 2 );
		Edge<Integer> e8 = TARGET.insertEdge( v3, v4, 2 );
		Edge<Integer> e9 = TARGET.insertEdge( v3, v5, 2 );
		Edge<Integer> e10 = TARGET.insertEdge( v4, v5, 2 );

		TARGET.removeVertex(v1);
		TARGET.removeVertex(v2);
		
		org.junit.Assert.assertEquals("Verifying edges size equals 6", (Object)(3), (Object)TARGET.numEdges());
	}

	
}

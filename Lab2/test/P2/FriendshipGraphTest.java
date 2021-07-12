package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FriendshipGraphTest {
	//tests for FriendshipGraph
	@Test
	public void test_addVertex() {
		FriendshipGraph g1 = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		assertEquals(true, g1.addVertex(a));
		assertEquals(true, g1.addVertex(b));
		assertEquals(true, g1.addVertex(c));
		assertEquals(false, g1.addVertex(a));
		assertEquals(false, g1.addVertex(b));
		assertEquals(false, g1.addVertex(c));
	}
	
	@Test
	public void test_getDistance() {
		FriendshipGraph g1 = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		Person d = new Person("d");
		Person e= new Person("e");
		Person f = new Person("f");
		Person g = new Person("g");
		Person h = new Person("h");
		g1.addEdge(a,b);
		g1.addEdge(b,c);
		g1.addEdge(b,c);
		g1.addEdge(a,d);
		g1.addEdge(d,b);
		g1.addEdge(b,c);
		g1.addEdge(b,e);
		g1.addEdge(e,f);
		g1.addEdge(e,g);
		assertEquals(0, g1.getDistance(a, a));
		assertEquals(1, g1.getDistance(a, b));
		assertEquals(2, g1.getDistance(a, c));
		assertEquals(1, g1.getDistance(a, d));
		assertEquals(2, g1.getDistance(a, e));
		assertEquals(3, g1.getDistance(a, f));
		assertEquals(3, g1.getDistance(a, g));
		assertEquals(-1, g1.getDistance(a, h));
	}
	
	
}

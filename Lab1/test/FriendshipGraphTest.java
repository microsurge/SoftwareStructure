package gg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Test
	public void TestFriendshipGraph() {
		FriendshipGraph graph = new FriendshipGraph();
		Person Rachel= new Person("Rachel");
		Person Ross= new Person("Ross");
		Person Ben= new Person("Ben");
		Person Kramer= new Person("Kramer");
		
		graph.addVertex(Rachel);
		graph.addVertex(Ross);
		graph.addVertex(Ben);
		graph.addVertex(Kramer);
		graph.addEdge(Rachel, Ross);
		graph.addEdge(Ross, Rachel);
		graph.addEdge(Ross, Ben);
		graph.addEdge(Ben, Ross);
		
		assertEquals(true,graph.addVertex(Rachel));
		assertEquals(true,graph.addVertex(Ross));
		assertEquals(true,graph.addVertex(Ben));
		assertEquals(true,graph.addVertex(Kramer));
		assertEquals(1,graph.getDistance(Rachel, Ross));
		assertEquals(1,graph.getDistance(Rachel, Ben));
		assertEquals(1,graph.getDistance(Rachel, Rachel));
		assertEquals(1,graph.getDistance(Rachel, Kramer));
	}

}

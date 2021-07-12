/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    /*	test 3 graphs 
     * 	one is an empty graph
     * 	another one is a none-edge graph
     * 	the last one is a normal one 
     * */
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testConcreteEdgesGraph_toString () {
    	Graph<String> g1 = emptyInstance();
    	Graph<String> g2 = emptyInstance();
    	Graph<String> g3 = emptyInstance();
    	//Initialize graphs
    	g2.add("a");
    	g2.add("b");
    	g2.add("c");
    	g3.add("d");
    	
    	g3.add("e");
    	g3.add("f");
    	g3.set("d", "e", 5);
    	g3.set("e", "f", 3);
    	assertEquals("", g1.toString());
    	assertEquals("", g2.toString());
    	assertEquals("d -> e weight : 5\n"+"e -> f weight : 3\n", g3.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //  test every method in class Edge
    
    // TODO tests for operations of Edge
    
    @Test
    public void testEdge_isEqual () {
    	Edge<String> Edge1 = new Edge<String>(3, "a", "b");
    	assertEquals(true, Edge1.isEqual("a", "b"));
    }
    @Test
    public void testEdge_getWeight_Set () {
    	Edge<String> Edge1 = new Edge<String>(3, "a", "b");
    	assertEquals(3, Edge1.getWeight());
    }
    @Test
    public void testEdge_isContain () {
    	Edge<String> Edge1 = new Edge<String>(3, "a", "b");
    	assertEquals(false, Edge1.isContain("c"));
    	assertEquals(true, Edge1.isContain("a"));
    }
    @Test
    public void testEdge_isEndWith () {
    	Edge<String> Edge1 = new Edge<String>(3, "a", "b");
    	assertEquals(true, Edge1.isEndWith("b"));
    	assertEquals(false, Edge1.isEndWith("a"));
    }
    @Test
    public void testEdge_isStartWith () {
    	Edge<String> Edge1 = new Edge<String>(3, "a", "b");
    	assertEquals(true, Edge1.isStartWith("a"));
    	assertEquals(false, Edge1.isStartWith("b"));
    }
}

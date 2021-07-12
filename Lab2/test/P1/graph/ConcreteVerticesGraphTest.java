/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    /*
     * test an empty graph , a non-edge graph and a common graph 
     * */   
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void toStringTest() {
		Graph<String> g1 = emptyInstance();
		Graph<String> g2 = emptyInstance();
		Graph<String> g3 = emptyInstance();
		g2.add("a");
		g2.add("b");
		g2.add("c");
		g3.add("d");
		g3.add("e");
		g3.set("c", "d", 5);
		assertEquals(true, g1.toString() == "");
		assertEquals("", g2.toString());
		assertEquals("vertex : d all the source points {c=5} \nvertex : c all the target points {d=5} \n", g3.toString());
		
	}
    
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   test every method for Vertex
    
    // TODO tests for operations of Vertex
    @Test
    public void testGetName() {
    	Vertex<String> test1 = new Vertex<String>("a");
    	assertEquals(true, test1.getName().equals("a"));
    }
    @Test
    public void testIsEqual() {
    	Vertex<String> test1 = new Vertex<String>("a");
    	assertEquals(true, test1.isEqual("a"));
    }
    
    @Test
    public void testSetTargets() {
    	Vertex<String> test1 = new Vertex<String>("a");
    	assertEquals(0, test1.setTarget("b", 5));
    	assertEquals(5, test1.setTarget("b", 7));
    	assertEquals(7, test1.setTarget("b", 0));
    	assertEquals(false, test1.getTargets().containsKey("b"));
    }
    
    @Test
    public void testSetSources() {
    	Vertex<String> test1 = new Vertex<String>("a");
    	assertEquals(0, test1.setSource("b", 5));
    	assertEquals(5, test1.setSource("b", 7));
    	assertEquals(7, test1.setSource("b", 0));
    	assertEquals(false, test1.getSources().containsKey("b"));
    }
    
    @Test
    public void testGetSources_GetTargets() {
    	Vertex<String> test1 = new Vertex<String>("a");
    	assertEquals(0, test1.setSource("b", 5));
    	assertEquals(0, test1.setSource("c", 6));
    	assertEquals(0, test1.setTarget("d", 7));
    	assertEquals(0, test1.setSource("e", 5));
    	assertEquals(3, test1.getSources().size());
    	assertEquals(1, test1.getTargets().size());
    }
}

/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    /*
     * divided by the point : whether the point is in the graph (2 events)
     * */  
	
	@Test
	public void testAdd() {
		Graph<String> g1 = new ConcreteEdgesGraph<String>();
		assertEquals(true, g1.add("a"));
		assertEquals(true, g1.add("b"));
		assertEquals(false, g1.add("a"));
		assertEquals(true, g1.vertices().contains("a"));
		assertEquals(true, g1.vertices().contains("b"));
	}
	
	// Testing strategy
    /*
     * divided by edge : whether the edge is existed 
     * divided by the value of weight : > 0 ? = 0 ? or < 0?
     * */   
	@Test
	public void testSet() {
		Graph<String> g1 = Graph.empty();
		assertEquals(0, g1.set("t", "a", 1));
		assertEquals(1, g1.set("t", "a", 1));
		assertEquals(0, g1.set("a", "t", 1));
		assertEquals(1, g1.set("a", "t", 1));
		assertEquals(1, g1.set("t", "a", 1));
		assertEquals(1, g1.set("t", "a", 1));
	}
	
	// Testing strategy
    /*
     * divided by status of point : whether in the graph or not
     * */   
	@Test
	public void testRemove() {
		Graph<String> g1 = emptyInstance();
		assertEquals(true, g1.add("c"));
		assertEquals(true, g1.add("d"));
		assertEquals(true, g1.remove("d"));
		assertEquals(false, g1.remove("d"));
		assertEquals(false, g1.vertices().contains("d"));
	}
    
	// Testing strategy
    /*
     * divided by status of graph : whether it is empty or not
     * */   
	@Test
	public void testVertices() {
		Graph<String> g1 = emptyInstance();
		assertEquals(true, g1.add("c"));
		assertEquals(true, g1.add("d"));
		Graph<String> g2 = emptyInstance();
		assertEquals(true, g2.vertices().isEmpty());
		assertEquals(false, g2.vertices().contains("a"));
		assertEquals(false, g1.vertices().isEmpty());
		assertEquals(true, g1.vertices().contains("c"));
	}
    
	// Testing strategy
    /*
     * divided by status of map : whether the source map is empty or not
     * */   
	@Test
	public void testSources() {
		Graph<String> g1 = emptyInstance();
		assertEquals(true, g1.add("c"));
		assertEquals(true, g1.add("d"));
		assertEquals(0, g1.set("c", "b", 7));
		assertEquals(0, g1.set("a", "b", 6));
		Map<String, Integer> soueceMap = g1.sources("b");
		Map<String, Integer> testMap = new HashMap<>();
		testMap.put("a", 6);
		testMap.put("c", 7);
		assertEquals(testMap, soueceMap);
		assertEquals(true, g1.sources("a").isEmpty());
	}
	
	// Testing strategy
    /*
     * divided by status of map : whether the target map is empty or not
     * */   
	@Test
	public void testTargets() {

		Graph<String> g1 = emptyInstance();
		assertEquals(true, g1.add("c"));
		assertEquals(true, g1.add("d"));
		assertEquals(0, g1.set("c", "b", 7));
		assertEquals(0, g1.set("c", "d", 4));
		Map<String, Integer> targetMap = g1.targets("c");
		Map<String, Integer> testMap = new HashMap<>();
		testMap.put("b", 7);
		testMap.put("d", 4);
		assertEquals(testMap, targetMap);
		assertEquals(true, g1.targets("d").isEmpty());
	}
    
	
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
}

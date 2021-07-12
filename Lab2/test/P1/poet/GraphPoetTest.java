/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    /*
     * test for files input:
     * 		empty file, nonempty file
     * test for toString method:
     * 		empty file, nonempty file
     * test for selective mechanism
     * */   
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void test_inputFiles() throws IOException {
    	GraphPoet p1 = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	GraphPoet p2 = new GraphPoet(new File("test/P1/poet/test.txt"));
    	assertEquals("embrace them all", p1.poem("embrace them all"));
    	assertEquals("x y z w", p2.poem("x z w"));
	}
    @Test
    public void test_toString() throws IOException {
    	GraphPoet p1 = new GraphPoet(new File("test/P1/poet/empty.txt"));
    	GraphPoet p2 = new GraphPoet(new File("test/P1/poet/toString.txt"));
    	assertEquals("", p1.toString());
    	assertEquals(true, p2.toString().contains("t -> a weight : 4"));
    	assertEquals(true, p2.toString().contains("a -> t weight : 1"));
    	assertEquals(true, p2.toString().contains("a -> k weight : 1"));
    	assertEquals(true, p2.toString().contains("k -> t weight : 1"));
    	assertEquals(true, p2.toString().contains("a -> m weight : 1"));
    	assertEquals(true, p2.toString().contains("m -> y weight : 1"));
    	assertEquals(true, p2.toString().contains("y -> t weight : 1"));
	}
    @Test
    public void test_selectMechanism() throws IOException {
    	GraphPoet p1 = new GraphPoet(new File("test/P1/poet/selectMechanism.txt"));
    	assertEquals("x z a", p1.poem("x a"));
	}
}

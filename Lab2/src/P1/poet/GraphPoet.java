/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    /*
     * graph : the vertexes of which are the word in input corpus
     * */   
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    /*
     * set Graph as private final
     * */   
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        //throw new RuntimeException("not implemented");
    	BufferedReader reader = new BufferedReader(new FileReader(corpus));
    	String buf;
    	String[] split_buf; 
    	int prev;
    	List<String> temp = new ArrayList<>();
    	while((buf = reader.readLine()) != null) {
    		split_buf = buf.split(" ");
    		temp.addAll(Arrays.asList(split_buf));
    	}
    	reader.close();
    	for(int i = 0; i < temp.size() - 1; i++) {
    		String source = temp.get(i).toLowerCase();
    		String target = temp.get(i + 1).toLowerCase();
    		prev = graph.set(source, target, 1);
    		graph.set(source, target, prev + 1);
    	}
    	checkRep();
    }
    
    // TODO checkRep
    private void checkRep() {
		assertEquals(true, graph != null);
	}
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
    	String ret = "";
    	String maxVertex;
    	List<String> temp = new ArrayList<>();
    	String[] split_buf = input.split(" ");
    	Map<String, Integer> sourcesMap= new HashMap<>();
    	Map<String, Integer> targetsMap= new HashMap<>();
    	int i, maxWeight;
    	temp.addAll(Arrays.asList(split_buf));
    	for(i = 0; i < temp.size() - 1; i++) {
    		ret += temp.get(i).toString() +" ";
    		targetsMap.putAll(graph.targets(temp.get(i).toLowerCase()));
    		sourcesMap.putAll(graph.sources(temp.get(i + 1).toLowerCase()));
    		Set<String> commmonVertex = targetsMap.keySet();
    		commmonVertex.retainAll(sourcesMap.keySet());
    		if(!commmonVertex.isEmpty()) {
        		List<String> commonVertexList = new ArrayList<>(commmonVertex);
        		maxVertex = commonVertexList.get(0);
        		maxWeight = targetsMap.get(maxVertex) + sourcesMap.get(maxVertex);
        		for(int j = 0; j < commonVertexList.size(); j++) {
        			if(maxWeight < targetsMap.get(commonVertexList.get(j)) + sourcesMap.get(commonVertexList.get(j))){
        				maxWeight = targetsMap.get(commonVertexList.get(j)) + sourcesMap.get(commonVertexList.get(j));
        				maxVertex = commonVertexList.get(j);
        			}
        		}
        		ret += maxVertex.toString() + " ";
    		}
    		targetsMap.clear();
    		sourcesMap.clear();
    	}
    	ret += temp.get(temp.size() - 1).toString();
  
    	return ret;
    }
    
    // TODO toString()
    public String toString() {
    	return graph.toString();
	}
    
}

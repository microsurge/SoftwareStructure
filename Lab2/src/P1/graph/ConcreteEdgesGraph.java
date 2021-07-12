/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    /*	vertices : point set in the graph
     * 	edges : edge set in the graph
     * */   
    // Representation invariant:
    /*	every target and source point is in the ponit set
     * 	at most one edge between a pair of source point and target point
     * 	pNum*(pNum - 1) >= eNum where
     *  pNum is the number of the points and eNum is the number of the edges
     *  edge value > 0
     * */
    
    // Safety from rep exposure:
    /*
     * 	set vertices and edges as private
     * 	use defensive copies
     * */   
    
    // TODO constructor 
    public ConcreteEdgesGraph () {
    	
	}

    // TODO checkRep
    private void checkRep() {
		for(Edge<L> e : edges) {
			assertEquals(true, vertices.contains(e.getTarget()));
			assertEquals(true, vertices.contains(e.getSource()));
			assertEquals(true, e.getWeight() > 0);
		}
		for(int i = 0; i < edges.size(); i++)
			for(int j = i + 1; j < edges.size(); j++)
				assertEquals(false, edges.get(i).isEqual(edges.get(j).getSource(), edges.get(j).getTarget()));
		
		assertEquals(true, vertices.size()*(vertices.size() - 1) >= edges.size());
    }
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	if(vertices.contains(vertex)) 
    		return false;
    	vertices.add(vertex);
    	checkRep();
    	return true;
    }
    
    @Override public int set(L s, L t, int weight) {
        //throw new RuntimeException("not implemented");
    	int prev = 0;
    	if(weight != 0) {
    		vertices.add(s);
			vertices.add(t);
			for(int i = 0; i < this.edges.size(); i++) {
				if(edges.get(i).isEqual(s, t)) {
	    			prev = this.edges.get(i).getWeight();
	    			edges.remove(i);
	    			break;
	    		}
			}
			edges.add(new Edge<L>(weight, s, t));
    	}
    	else {
    		for(int i = 0; i < edges.size(); i++) 
	    		if(edges.get(i).isEqual(s, t)) {
	    			prev = edges.get(i).getWeight();
	    			edges.remove(i);
	    			break;
	    		}			
		}
    	checkRep();
    	return prev;			
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	boolean result = false;
    	if(vertices.contains(vertex)) {
    		vertices.remove(vertex);
			result = true;
    		java.util.Iterator<Edge<L>> iter = edges.iterator();
    		while (iter.hasNext()) {
    			Edge<L> temp = iter.next();
				if(temp.isContain(vertex)) 
					iter.remove();
				
			}
    	}
    	checkRep();
    	return result;
    }
    
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	Set<L> ans= new HashSet<>();
    	ans.addAll(this.vertices);
    	checkRep();
    	return ans;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> tarMap = new HashMap<>();
    	java.util.Iterator<Edge<L>> iter = edges.iterator();
    	while(iter.hasNext()) {
    		Edge<L> temp = iter.next();
    		if(temp.isEndWith(target))
    			tarMap.put(temp.getSource(), temp.getWeight());
    	}
    	return tarMap;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> tarMap = new HashMap<>();
    	java.util.Iterator<Edge<L>> iter = edges.iterator();
    	while(iter.hasNext()) {
    		Edge<L> temp = iter.next();
    		if(temp.isStartWith(source))
    			tarMap.put(temp.getTarget(), temp.getWeight());
    	}
    	return tarMap;
    }

    // TODO toString()
    @Override public String toString() {
    	String retString = "";
		for(Edge<L> e : edges) 
			retString += e.toString();
		return retString;
	}
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	private final int weight;
	private final L source;
	private final L target;
    // Abstraction function:
    /*
     * source : the starting point of the edge
     * target : the ending point of the edge 
     * weight : the value of the edge
     * */
    // Representation invariant:
    /*
     * weight is nonzero
     * source and target are not null
     * */   
	
    // Safety from rep exposure:
    /*
     * set source, target and weight as private 
     * */   
    
    // TODO constructor
	/**
	 * @param s the source point of the edge
	 * @param t the target point of the edge
	 * @param weight the value point of the edge
	 * 
	 * */
	public Edge (int w, L s, L t){
		this.source = s;
		this.target = t;
		this.weight = w;
		checkRep();
	}
    // TODO checkRep
    private void checkRep() {
		assertEquals(true, this.source != null && this.target != null);
		assertEquals(true, this.weight != 0);
	}
	
    // TODO methods
	
    /**
	 * @param source the source point of the edge
	 * @param target the target point of the edge
	 * @return true if the input source and target are equal to
	 * the according source and target point of the edge , otherwise false
	 * */
	public boolean isEqual(L s, L t) {
		if(source.equals(s) && target.equals(t))
			return true;
		return false;
	}
	
	/**
	 * @param vertex a point whose tag is vertex
	 * @return true if the tag of the source or target point is equal to vertex,
	 * otherwise false 
	 * */
	public boolean isContain(L vertex) {
		if(this.source.equals(vertex) || this.equals(vertex))
			return true;
		return false;
	}
	
	/**
	 * @param vertex a point whose tag is vertex
	 * @return true if the tag of the source point is equal to vertex,
	 * otherwise false 
	 * */
	public boolean isStartWith(L vertex) {
		if(this.source.equals(vertex))
			return true;
		return false;
	}
	
	/**
	 * @param vertex a point whose tag is vertex
	 * @return true if the tag of the target point is equal to vertex,
	 * otherwise false 
	 * */
	public boolean isEndWith(L vertex) {
		if(this.target.equals(vertex))
			return true;
		return false;
	}
	
	/**
	 * @param weight a new weight value
	 * @effect set the weight of edge as the input weight
	 * */
	
	/**
	 * @return the weight value of edge
	 * */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * @return the tag of source point of the edge
	 * */
	public L getSource() {
		return this.source;
	}
	
	/**
	 * @return the tag of target point of the edge
	 * */
	public L getTarget() {
		return this.target;
	}
	
    // TODO toString()
	@Override public String toString() {
		return this.source.toString() + " -> " + this.target.toString() + " weight : " + Integer.toString(this.weight) + "\n";
	}
	
	
	
    
}

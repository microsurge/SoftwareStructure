/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   vertices are the points in the graph
    // Representation invariant:
    /*
     * no repeated element is in vertices
     * edge value > 0
     * */   
    // Safety from rep exposure:
    /*
     * set vertices as private final
     * use defensive copies
     * */   
    
    // TODO constructor
    public ConcreteVerticesGraph() {

	}
    // TODO checkRep
    private void checkRep() {
    	Set<L> temp= new HashSet<>();
    	for(Vertex<L> point : this.vertices)
    		temp.add(point.getName());
    	assertEquals(this.vertices.size() ,  temp.size());
    }
    
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	Iterator<Vertex<L>> iter = this.vertices.iterator();
    	while(iter.hasNext()) 
    		if(iter.next().isEqual(vertex))
    			return false;
    	Vertex<L> a = new Vertex<L>(vertex);
    	this.vertices.add(a);
    	checkRep();
    	return true;
    }


    
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	Set<L> ret= new HashSet<>();
    	for(Vertex<L> point : vertices)
    		ret.add(point.getName());
    	return ret;
    }
    



	@Override
	public int set(L source, L target, int weight) {
		// TODO Auto-generated method stub
		int prev = 0;
		this.add(source);
		this.add(target);	
		for(Vertex<L> point : vertices) {
			if(point.getName().equals(source)) 
				prev = point.setTarget(target, weight);
			if(point.getName().equals(target)) 
				prev = point.setSource(source, weight);
		}
		checkRep();
		return prev;
	}

	@Override
	public boolean remove(L vertex) {
		// TODO Auto-generated method stub
		boolean ret = false;
		for(Vertex<L> point : vertices) 
			if(point.getName() == vertex) {
				vertices.remove(point);
				ret = true;
				for(Vertex<L> point1 : vertices) {
					point1.setSource(vertex, 0);
					point1.setTarget(vertex, 0);
				}
				break;
			}
		checkRep();
		return ret;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		// TODO Auto-generated method stub
		for(Vertex<L> point : vertices) 
			if(point.getName().equals(target)) 
				return point.getSources();
		return new HashMap<L, Integer>();
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// TODO Auto-generated method stub
		for(Vertex<L> point : vertices) 
			if(point.getName().equals(source)) 
				return point.getTargets();
		return new HashMap<L, Integer>();
	}
    
    // TODO toString()
	public String toString() {
		String ret = "";
		for(Vertex<L> point : vertices)
			ret += point.toString();
		return ret;
	}
	
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // TODO fields
	private final L name;
    private final Map<L, Integer> sources;
    private final Map<L, Integer> targets;
    // Abstraction function:
    /*
     * name : the tag of each vertex
     * sources : String is the tag of point which is the starting vertex
     * 	of an edge ended with this vertex , and the Interger is the weight
     * of this edge 
     * targets :String is the tag of point which is the ending vertex
     * 	of an edge started by this vertex , and the Interger is the weight
     * of this edge
     * */   
    // Representation invariant:
    /*	the weight is nonzero
     * */   
    
    // Safety from rep exposure:
    /*
     * set name, sources and targets as private
     * use defensive copies
     * */   
    
    // TODO constructor
    public Vertex(L ver) {
    	this.name = ver;
    	this.sources = new HashMap<L, Integer>();
    	this.targets = new HashMap<L, Integer>();
    }
    
    // TODO checkRep
    private void checkRep() {
    	assertEquals(true, this.name != null);
    	assertEquals(false, this.name.equals(""));
    	for(Integer weight : sources.values())
    		assertEquals(true, weight > 0);
    }
    
    // TODO methods
    /**
     * return the tag of this vertex 
     */
	public L getName() {
		return this.name;
	}
	
	/**
	 *  @return the targets map of this vertex
	 * */
	public Map<L, Integer> getTargets(){
		Map<L, Integer> ret = new HashMap<>();
		ret.putAll(this.targets);
		return ret;
	}
	
	/**
	 *  @return the sources map of this vertex
	 * */
	public Map<L, Integer> getSources(){
		Map<L, Integer> ret = new HashMap<>();
		ret.putAll(this.sources);
		return ret;
	}
	
	
	/**
	 *	@param target the tag of a target point
	 *  @param weight the value of an edge
	 *  @effect if weight is nonzero, set or update the target and the weight in target,
	 *  if target is not in the targets then add the target in it. if weight is zero then
	 *  delete the target in the targets
	 *  @return the previous weight if target is in the targets, otherwise 0 
	 * */
	public int setTarget(L target, int weight) {
		int prev = 0;
		if(this.targets.containsKey(target))
			prev = this.targets.get(target);
		if(weight != 0) 
			this.targets.put(target, weight);
		else 
			this.targets.remove(target);
		checkRep();
		return prev;
	}
	
	
	/**
	 *	@param source the tag of a target point
	 *  @param weight the value of an edge
	 *  @return if source is in the Map sources, then update the value as weight
	 *  and return true, otherwise return false and the map is not modified
	 * */
	public int setSource(L source, int weight) {
		int prev = 0;
		if(this.sources.containsKey(source))
			prev = this.sources.get(source);
		if(weight != 0) 
			this.sources.put(source, weight);
		else 
			this.sources.remove(source);
		checkRep();
		return prev;
	}
	
	/**
	 *	@param name tag of a vertex
	 *  @return if the input name is equal to the vertex's name, then
	 *  return true, otherwise return false
	 * */
	public boolean isEqual(L name) {
		if(this.getName().equals(name))
			return true;
		return false;
	}
	
	
	 // TODO toString()
	public String toString() {
		String ret = "";
		if(!this.getSources().isEmpty() || !this.getTargets().isEmpty()) {
			ret += "vertex : " + this.name.toString() + " ";
			if(!this.getSources().isEmpty())
				ret += "all the source points " + this.getSources().toString() + " ";
			if(!this.getTargets().isEmpty())
				ret += "all the target points " + this.getTargets().toString() + " ";
			ret += "\n";
		}
		return ret;
	}
}

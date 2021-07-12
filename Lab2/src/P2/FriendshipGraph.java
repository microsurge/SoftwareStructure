package P2;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import P1.graph.Graph;

public class FriendshipGraph {

	// TODO Auto-generated method stub
	//field
	private final Graph<Person> graph = Graph.empty();
	
			
	// Abstraction function:
    /*
     * graph : the social network
     * */   
	
    // Representation invariant:
    /*
     * no repeatedly named person
     * distance between each pair of nearby vertexes is 1 
     * */   
	
    // Safety from rep exposure:
    /*
     * set graph as private final
     * */ 
	
	//constructor
	public FriendshipGraph() {
		
	}
	
	private void checkRep() {
		Set<String> nameSet = new HashSet<>();
		Set<Person> personSet = graph.vertices();
		Set<Integer> weightSet = new HashSet<>();
		for(Person p : personSet) {
			nameSet.add(p.getName());
			weightSet.addAll(graph.targets(p).values());
			weightSet.addAll(graph.sources(p).values());
		}
		assertEquals(true, nameSet.size() == personSet.size());
		for(int i : weightSet) {
			assertEquals(i, 1);
		}
	}
	
	/**
	 * add a person in the graph
	 * @param person a person in the graph
	 * @return if the graph does not contain a person named the input name,
	 * then return true, otherwise return false, and the graph is not modified
	 */
	public boolean addVertex(Person person) {
		boolean ret = graph.add(person);
		checkRep();
 		return ret;
	}
	/**
	 * add a directed edge between person A and person B in the graph
	 * @param A a person in the graph
	 * @param B the other person in the graph
	 * @effect add a directed edge between person A and person B in the graph,
	 * if A or B does not exist, then add the person in the graph and set
	 * the edge
	 */
		public void addEdge(Person A, Person B) {
			graph.set(A, B, 1);
			checkRep();
	 		return ;
		}
		
		/**
		 * get the distance between person A and person B in the graph
		 * @param A a person in the graph
		 * @param B the other person in the graph
		 * @return if person A and B are the same, return 0; if there is
		 * no path between A and B, return -1; otherwise return the shortest 
		 * distance between A and B
		 */
		public int getDistance(Person A, Person B) {
			Set<Person> visitSet = new HashSet<>();
			Queue<Person> personQueue = new LinkedList<>();
			int dist = 0, counter = 0;
			if(A.getName().equals(B.getName()))
				return dist;
			dist++;
			visitSet.add(A);
			for(Person p : graph.targets(A).keySet()) {
				if(!visitSet.contains(p)) {
					personQueue.offer(p);
					counter++;
				}
			}
			while(!personQueue.isEmpty()) {
				int tempCounter = counter;
				counter = 0;
				for(int i = 0; i < tempCounter; i++) {
					Person temp = personQueue.poll();
					if(temp.getName().equals(B.getName()))
						return dist;
					visitSet.add(temp);
					for(Person p : graph.targets(temp).keySet()) {
						if(!visitSet.contains(p)) {
							personQueue.offer(p);
							counter++;
						}
					}
				}
				dist++;
			}
			return -1;
		}
	
	
	
	public static void main(String[] args) {
		
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("rachel");
		Person ben = new Person("ben");
		Person ross = new Person("ross");
		Person kramer = new Person("kramer");
		
		graph.addVertex(kramer);
		graph.addVertex(ross);
		graph.addVertex(rachel);
		graph.addVertex(ben);
		
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		
		System.out.println("Distance between Rachel and Ross is : " + graph.getDistance(rachel, ross));
		System.out.println("Distance between Rachel and Ben is : " + graph.getDistance(rachel, ben));
		System.out.println("Distance between Rachel and Rachel is : " + graph.getDistance(rachel, rachel));
		System.out.println("Distance between Rachel and Kramer is : " + graph.getDistance(rachel, kramer));
	}

}

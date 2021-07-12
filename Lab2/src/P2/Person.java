package P2;

import static org.junit.Assert.assertEquals;

public class Person {
	//field
	private String name;
	
	// Abstraction function:
    /*
     * name : the name of each people
     * */   
	
    // Representation invariant:
    //   name cannot be empty
	
    // Safety from rep exposure:
    /*
     * set name as private
     * */ 
	
	//Constructor : 
	public Person(String n) {
		this.name = n;
		checkRep();
	}
	
	private void checkRep() {
		assertEquals(false, this.name.equals("") || this.name.equals(null));
	}
	
	/**
	 * no defensive copies due to the immutable data type
	 * @return the name of the person
	 * */
	public String getName() {
		return this.name;
	}
}

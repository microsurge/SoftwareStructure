package Applications;

import static org.junit.Assert.assertEquals;

public class Process {
	//field
	private long ID;
	private final String name;
	private final long minExecuteTime;
	private final long maxExecuteTime;
	
	// Abstraction function:
    /*	name : the name of a process
     * ID : the ID of a process
     * 	minExecuteTime : the minimal executing time of a process
     * maxExecuteTime : the max executing time of a process
     * */   
	
    // Representation invariant:
    /*	the name should not be null and minExecuteTime and maxExecuteTime should be positive
     * */
    
    // Safety from rep exposure:
    /*
     * 	set name, occupisation and phone number as private
     * 	use defensive copies
     * */ 
	
	//Constructor
	public Process(long ID, String name, long min, long max) {
		this.ID = ID;
		this.name = name;
		this.minExecuteTime = min;
		this.maxExecuteTime = max;
		checkRep();
	}
	
	/**
	 *check the correctness of the class 
	 */
    private void checkRep() {
    	assertEquals(true, name!=null);
    }
	
	 /**
	 *@return the ID of the process 
	 */
	public long getID() {
		return this.ID;
	}
	
	/**
	 *@return the nmae of the process 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 *@return the Max Execute Time of the process 
	 */
	public long getMaxExecuteTime() {
		return this.maxExecuteTime;
	}
	
	/**
	 *@return the minimal Execute Time of the process 
	 */
	public long getMinExecuteTime() {
		return this.minExecuteTime;
	}
	
	@Override public boolean equals(Object a){
		if(!(a instanceof Process))
			return false;
		Process obj = (Process) a;
		if(this.ID == obj.getID() && this.name.equals(obj.getName()))
			return true;
		return false;
	}
	
	@Override public int hashCode() {
		int ret = this.name.hashCode();
		ret = ret*17 + (int)ID;
		return ret;
	}
}

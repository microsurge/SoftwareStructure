package Applications;

import static org.junit.Assert.assertEquals;

public class Employee {
	//field
	private String name;
	private String occupization;
	private long phoneNumber;
	
	// Abstraction function:
    /*	name : the name of an employee
     * 	occupization : the job of an employee
     * phonenumber : the phone number of an employee
     * */   
	
    // Representation invariant:
    /*	the name and occupization and name should not be null and phone number should be positive
     * */
    
    // Safety from rep exposure:
    /*
     * 	set name, occupisation and phone number as private
     * 	use defensive copies
     * */ 
	
	//Constructor
	public Employee(String name, String occupization, long phone){
		this.name = name;
		this.occupization = occupization;
		this.phoneNumber = phone;
		checkRep();
	}
	
	/**
	 *check the correctness of the class 
	 */
    private void checkRep() {
    	assertEquals(true, name!=null);
    	assertEquals(true, occupization!=null);
    }
	
    /**
	 *@return the name of the employee 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 *@return the job of the employee 
	 */
	public String getOccupizasion() {
		return this.occupization;
	}
	
	/**
	 *@return the phone number of the employee 
	 */
	public long getPhonenumber() {
		return this.phoneNumber;
	}
	
	@Override public boolean equals(Object a) {
		if(!(a instanceof Employee))
			return false;
		Employee obj = (Employee) a;
		if(this.name.equals(obj.getName()) && this.occupization.equals(obj.getOccupizasion()) 
				&& this.phoneNumber == obj.getPhonenumber())
			return true;
		return false;
	}
	
	@Override public int hashCode() {
		int ret = this.name.hashCode();
		ret = ret*17 + this.occupization.hashCode();
		checkRep();
		return ret;
	}
}

package Applications;

import static org.junit.Assert.assertEquals;

public class Course {
	//field
	private long ID;
	private String courseName;
	private String teacherName;
	private String location;
	private long weekStudyHour;
	
	// Abstraction function:
    /*	courseName : the name of a course
     * 	teacherName : the name of a course teachwer
     * ID : the ID of a course
     * weekStudyHour : the weekly Study Hour of a course
     * location : the location of a course
     * */   
	
    // Representation invariant:
    /*	the courseName and teacherName and location should not be null and ID and weekStudyHour should be positive
     * */
    
    // Safety from rep exposure:
    /*
     * 	set courseName, teacherName, location, and ID and weekStudyHour as private
     * 	use defensive copies
     * */ 
	
	/**
	 *check the correctness of the class 
	 */
    private void checkRep() {
    	assertEquals(true, courseName!=null);
    	assertEquals(true, teacherName!=null);
    	assertEquals(true, location!=null);

    }
	
	//constructor
	public Course(long ID, String courseName, String teacherName, String location, long weekStudyHour) {
		this.ID = ID;
		this.weekStudyHour = weekStudyHour;
		this.teacherName = teacherName;
		this.courseName = courseName;
		this.location = location;
		checkRep();
	}
	
	/**
	 *@return the ID of the Course
	 */
	public long getID() {
		return this.ID;
	}
	
	/**
	 *@return the name of the Course teacher
	 */
	public String getTeacherName() {
		return this.teacherName;
	}
	
	/**
	 *@return the name of the Course
	 */
	public String getcourseName() {
		return this.courseName;
	}
	
	/**
	 *@return the location of the Course
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 *@return the weekly study hours of the Course
	 */
	public long getWeekStudyHour() {
		return this.weekStudyHour;
	}
	
	@Override public boolean equals(Object a) {
		if(!(a instanceof Course))
			return false;
		Course obj = (Course) a;
		if(this.ID == obj.ID && this.getcourseName().equals(obj.getcourseName()) 
				&& this.teacherName.equals(obj.teacherName))
			return true;
		return false;
	}
	
	@Override public int hashCode() {
		int ret = this.courseName.hashCode();
		ret = ret*17 + this.teacherName.hashCode();
		return ret;
	}
	
}

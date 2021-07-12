package Applications;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interval.MultiIntervalSet;

public class CourseIntervalSet extends MultiIntervalSet<Course>{
	//field
	private long startDate;
	private int weekNum;
	//Long is the unassigned hours
	private final Map<Course, Long> courseMap = new HashMap<>();
	
	// Abstraction function:
    /*	startDate : the date of starting
     * 	weekNum : the number of weeks
     * courseMap the courses to be assigned
     * */   
    // Representation invariant:
    /*	the startDate, WeekNum and course Id should be positive
     * */
    
    // Safety from rep exposure:
    /*
     * 	set startDate and weekNum as private
     * set courseMap as private final
     * 	use defensive copies
     * */   
	
	/**
	 *check the correctness of the class 
	 */
	private void checkRep() {
		assertEquals(true, startDate > 0L);
		assertEquals(true, weekNum > 0);
		for(Course c : courseMap.keySet()) {
			assertEquals(true, c.getID() > 0);
		}
		
	}
	
	//Constructor
	public CourseIntervalSet() {
		
	}
	
	/**
	 * initialize the set by setting the starting date and the weeks number
	 * @param start the starting date
	 * @param wNum the number of the weeks
	 */
	void init(long start, int wNum) {
		this.startDate = start;
		this.weekNum = wNum;
		checkRep();
	}
	
	/**
	 * add a course and record it's weekly study hours
	 * @param c course to assign
	 * @param weekHours the weekly study hours of the course
	 */
	public void addCourse(Course c, long weekHours) {
		courseMap.put(c, weekHours);
		checkRep();
	}
	
	/**
	 * print all the unassigned courses and it's resting unassigned hours
	 */
	public void printCourseInformation() {
		System.out.println("\nCourrent unassigned courses : ");
		for(Course c : courseMap.keySet()) {
			System.out.println("ID : " + c.getID() + " Course : " + c.getcourseName() + " unassigned hours : " + courseMap.get(c));
		}
	}
	
	/**
	 * judge if a course is available
	 * @param name the course name
	 * @return if the course is in the courseMap and it's resting assigning hours is
	 * positive return true, otherwise return false
	 */
	public boolean isCourseAvailable(String name) {
		for(Course c : courseMap.keySet()) {
			if(c.getcourseName().equals(name) && courseMap.get(c) != 0L)
				return true;
		}
		return false;
	}
	
	/**
	 * assign a course
	 * @param name the course name
	 * @param day the day of a week, eg. Sunday is 1 and Monday is 2...
	 * @param time the starting time of a course it can only be 8, 10, 13, 15, 19
	 */
	public void assignCourse(String name, long day, long time) {
		Course tarCourse = new Course(0, "", "", "", 0);
		long insertCode = day*100 + time; 
		for(Course c : courseMap.keySet()) {
			if(c.getcourseName().equals(name)) {
				tarCourse = c;
				break;
			}
		}
		insert​(insertCode, insertCode + 2L, tarCourse);
		courseMap.put(tarCourse, courseMap.get(tarCourse) - 2L);
		checkRep();
	}
	
	/**
	 * @return the ratio of the unassigned time
	 */
	public double getBlankTimeRate() {
		long assignedTimeLength = (long)getTimeList().size();
		long unassignedTimeLength = 70L - assignedTimeLength + getRepeatedTimeLength(); 
		return (double)unassignedTimeLength/(double)70.0;
	}
	
	/**
	 * @return the ratio of the repeated/overlapped time
	 */
	public double getRepeatedTimeRate() {
		return (double)getRepeatedTimeLength()/(double)70.0;
	}
	
	/**
	 * @return the length of the repeated/overlapped time
	 */
	private long getRepeatedTimeLength() {
		long RepeatedTimeLength = 0L;
		List<Long> tempList = this.getTimeList();
		for(int i = 2; i < tempList.size(); i += 2) {
			if(tempList.get(i) == tempList.get(i - 2))
				RepeatedTimeLength += 2L;
		}
		return RepeatedTimeLength;
	}
	
	/**print the courses information in an assigned day
	 * @param date the specific date
	 */
	public void printDateCourses(long date) {
		int day;
		if(date < startDate || date > DateRecorder.daysAdd(date, 7*weekNum)) {
			System.out.println("Invalid number!");
			return;
		}
		day = DateRecorder.getDayOfWeek(date);
		for(int i = 0; i < getTimeList().size(); i += 2) {
			if((getTimeList().get(i))/100 == (long)day) {
				System.out.println("ID : " + getLabList().get(i/2).getID() + " Course : " 
			+ getLabList().get(i/2).getcourseName() + " time : " + getTimeList().get(i)%100 + " to " + (getTimeList().get(i)%100 + 2));
			}
		}
	}
}

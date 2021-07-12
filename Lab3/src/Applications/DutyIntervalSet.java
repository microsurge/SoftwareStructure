package Applications;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import interval.CommonIntervalSet;

public class DutyIntervalSet extends CommonIntervalSet<Employee>{
	//field
	private long startDate;
	private long endDate;
	// Abstraction function:
    /*	startDate : the date of starting
     * 	endDate : the date of ending
     * */   
    // Representation invariant:
    /*	the start date should earlier than end date
     * */
    
    // Safety from rep exposure:
    /*
     * 	set startDate and endDate as private
     * 	use defensive copies
     * */   
	
	/**
	 *check the correctness of the class 
	 */
	private void checkRep() {
		assertEquals(true, startDate < endDate);
	}
	
	/**
	 * set the starting date and the ending date of the set
	 * @param start the starting date in the form of YYYYMMDD
	 * @param end the ending date in the form of YYYYMMDD
	 */
	public void setDate(long start, long end) {
		this.startDate = start;
		this.endDate = end;
		checkRep();
	}
	
	/**
	 * automatically assign the duty table
	 * @param waitingSet the set containing all employees to be assigned
	 */
	public void autoAssign(Set<Employee> waitingSet) {
		int num = waitingSet.size(), counter = 0;
		int duration = DateRecorder.getDaysBetween(startDate, endDate);
		long currentDate = startDate;
		for(Employee p : waitingSet) {
			if(counter + 1 == num) {
				this.insert​(currentDate, endDate, p);
			}else {
				this.insert​(currentDate, DateRecorder.daysAdd(currentDate, duration/num), p);
				counter++;
				currentDate = DateRecorder.daysAdd(currentDate, duration/num);
			}
		}
	}
	
	
	/**
	 * @return the ratio that assigned time counts for
	 */
	public double calculateAssignedRate() {
		return (double)getAssignedLength()/(double)DateRecorder.getDaysBetween(startDate, endDate);
	}
	
	/**
	 * @return the starting date
	 */
	public long getStartDate (){
		return startDate;
	}
	
	/**
	 * @return the ending date
	 */
	public long getEndDate (){
		return endDate;
	}
	
	@Override public void insert​(long start, long end, Employee label) {
		if(super.labels().contains(label))
			return ;
		super.insert​(start, end, label);
	}
	
	@Override
	public String toString() {
        String ret = "";
        //System.out.println("tos " + getTimeList().size());
        for(int i = 0; i < getTimeList().size(); i += 2) {
        	ret += getLabelList().get(i/2).getName() + ": " + getTimeList().get(i).toString() + " to " + getTimeList().get(i+1).toString() + "\n";
        } 
        return ret;
	}
}


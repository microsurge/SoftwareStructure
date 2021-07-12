package Applications;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DutyRosterApp {

	//field
	private static Scanner scan = new Scanner(System.in);
	final static DutyIntervalSet DISet = new DutyIntervalSet();
	final static Set<Employee> waitingSet = new HashSet<>();
	
	// Abstraction function:
    /*	DISet : the assignment of the employee
     * 	waitingSet : the employees to be assigned
     * */   
	
    // Representation invariant:
    /*	the DISet and the waiting Set should not be null
     * */
    
    // Safety from rep exposure:
    /*
     * 	set startDate and endDate as private
     * 	use defensive copies
     * */   
	
	/**
	 * manually add an employee
	 */
	static private void addEmployeeManual() {
		String tempString;
		String[] pieces;
		System.out.println("Please input the employee information in the form of name job phonenumber\n"
				+ "and with the spaces in each date, when finishing inputing, press q in a new line\n"
				+ "input name D if you want to delete an employee");
		while(true) {
			tempString = scan.nextLine();
			pieces = tempString.split(" ");
			if(tempString.equals("q"))
				break;
			else if(pieces[1].equals("D")){
				for(Employee p : waitingSet) 
					if(p.getName().equals(pieces[0])){
						waitingSet.remove(p);
						break;
					}		
			}
			else 
				waitingSet.add(new Employee(pieces[0], pieces[1], Long.parseLong(pieces[2])));
		}
	}

	/**
	 * manual mode work control
	 */
	private static void manualWorkflow(){
		int switchFlag = -1;
		setPeriodManual();
		scan.nextLine();
		addEmployeeManual();
		while(switchFlag != 0 && switchFlag !=1) {
			System.out.println("press 0 to manually assign the duty table\n"
					+ "press 1 to generate that automatically");
			switchFlag = scan.nextInt();
		}
		scan.nextLine();
		if(switchFlag == 1) {
			DISet.autoAssign(waitingSet);
			System.out.println(DISet.toString());
		}
		else manualAssign();
	}
	
	/**
	 * manually assign an employee for his/her period
	 */
	private static void manualAssign() {
		Employee tempEmployee = new Employee("initial", "default", 0L);
		boolean findFlag = false;
		String tempString;
		String[] pieces;
		while(DISet.calculateAssignedRate()!=1.0) {
			System.out.println("\n" + "Please input the Duty information in the form of name starting-date ending-date\n"
					+ "starting date should be earlier than ending date\n"
					+ "and you cannot assign 2 different time span for each person\n"
					+ "nor can each 2 time assignment be overlapped, press q in a new line to quit inputing");
			tempString = scan.nextLine();
			if(tempString.equals("q")) {
				System.out.println("\n" + "The current duty table is \n" + DISet.toString() + "\n" +
						"the blank rate is : " + (1.0 - DISet.calculateAssignedRate())*100 + "%");
				break;
			}
			pieces = tempString.split(" ");
			if(Long.parseLong(pieces[1]) >= DISet.getStartDate() && Long.parseLong(pieces[2]) <= DISet.getEndDate()) {
				findFlag = false;
				for(Employee pEmployee : waitingSet) {
					if(pEmployee.getName().equals(pieces[0])) {
						tempEmployee = pEmployee;
						findFlag = true;
						break;
					}
				}
				if(findFlag)
					DISet.insert​(Long.parseLong(pieces[1]), Long.parseLong(pieces[2]), tempEmployee);
			}
			System.out.println("\n" + "The current duty table is \n" + DISet.toString() + "\n" +
					"the blank rate is : " + (1.0 - DISet.calculateAssignedRate())*100 + "%");
		}
	}
	
	/**
	 * manually set the starting and ending time
	 */
	private static void setPeriodManual(){
		long start = 0, end = 0;
		while(start >= end) {
			System.out.println("Please input the starting and the ending date in the form of YYYYMMDD\n"
					+ "and with the spaces in each date, starting date should be earlier than ending date");
			start = scan.nextLong();
			end = scan.nextLong();
		}
		DISet.setDate(start, end);
	}
	
	/**
	 * construct the file path depending on the input number
	 * @return a valid filepath
	 */
	private static String getTargetFilePath() {
		String path = "test/txt/test";
		int switchFlag = -1;
		while(switchFlag <1 || switchFlag > 8) {
			System.out.println("Please input the the number of the file you want to import\n"
					+ "the number should be in range of 1 to 8");
			switchFlag = scan.nextInt();
		}
		path += Integer.toString(switchFlag) + ".txt";
		
		return path;
	}
	
	/**
	 * parse the period part and set the period 
	 * @param periodString the period bulk of a test file
	 */
	private static void SetPeriodByFile(String periodString) {
		int counter = 0;
		String startString = "", endString = "";
		Pattern pattern = Pattern.compile("\\d{4,4}-\\d{2,2}-\\d{2,2}");
        Matcher matcher = pattern.matcher(periodString);
        while(matcher.find()) {
        	if(counter == 0)
        		startString += matcher.group();
        	else 
        		endString += matcher.group();
        	counter++;
        }
        startString = startString.replaceAll("-", "");
        endString = endString.replaceAll("-", "");
        DISet.setDate(Long.parseLong(startString), Long.parseLong(endString));
	}
	
	/**
	 * parse the Employee part and set the Employee list
	 * @param employeeString the employee bulk of a test file
	 */
	private static void SetEmployeeByFile(String employeeString) {
		String[] pieces;
		Pattern pattern = Pattern.compile("[a-zA-Z]+\\{[a-zA-Z]+,\\d{3,3}-\\d{4,4}-\\d{4,4}\\}");
        Matcher matcher = pattern.matcher(employeeString);{
        	while(matcher.find()) {
            	pieces = matcher.group().split("\\{|\\}|,");
            	pieces[2] = pieces[2].replaceAll("-", "");
            	waitingSet.add(new Employee(pieces[0], pieces[1], Long.parseLong(pieces[2])));
            }
        }
		
	}
	
	/**
	 * parse the roster part and assign for the employees
	 * @param rosterString the assignment bulk of a test file
	 */
	private static void AssignPeriodByFile(String rosterString) {
		String[] pieces;
		Employee tempEmployee = new Employee("", "", 0);
		Pattern pattern = Pattern.compile("[a-zA-Z]+\\{\\d{4,4}-\\d{2,2}-\\d{2,2},\\d{4,4}-\\d{2,2}-\\d{2,2}\\}");
        Matcher matcher = pattern.matcher(rosterString);{
        	while(matcher.find()) {
            	pieces = matcher.group().split("\\{|\\}|,");
            	pieces[1] = pieces[1].replaceAll("-", "");
            	pieces[2] = pieces[2].replaceAll("-", "");
            	System.out.println(pieces[0] + " " + pieces[1] + " " + pieces[2]);
            	for(Employee e : waitingSet) {
            		if(e.getName().equals(pieces[0])) {
            			tempEmployee = e;
            			break;
            		}
            	}
            	DISet.insert​(Long.parseLong(pieces[1]), Long.parseLong(pieces[2]), tempEmployee);
            }
        }
	}
	
	/**
	 * control the workflow by file reading system
	 */
    private static void fileWorkflow() throws IOException{
    	String filePath;
    	List<String> fileBulkList;

    	filePath = getTargetFilePath();
    	fileBulkList = DutyIntervalParser.parseFileBulk(filePath);
    	if(!fileBulkList.get(0).contains("Period") ||!fileBulkList.get(1).contains("Employee") ||!fileBulkList.get(2).contains("Roster")) {
    		System.out.println("Invalid file !");
    		return;
    	}
    	SetPeriodByFile(fileBulkList.get(0));
    	SetEmployeeByFile(fileBulkList.get(1));
    	AssignPeriodByFile(fileBulkList.get(2));
    	System.out.println("\n" + "The current duty table is \n" + DISet.toString() + "\n" +
				"the blank rate is : " + (1.0 - DISet.calculateAssignedRate())*100 + "%");
	}

    /**
	 * control 2 workflows
	 */
	public static void main(String[] args) throws IOException {
		int switchFlag = -1;
		while (switchFlag != 1 && switchFlag != 2) {
			System.out.println("Press 1 to manually input the employees,\n"
					+ "press 2 to read them in the file");
			switchFlag = scan.nextInt();
		}
		if(switchFlag == 2)
			fileWorkflow();
		else manualWorkflow();
		scan.close();
	}
}
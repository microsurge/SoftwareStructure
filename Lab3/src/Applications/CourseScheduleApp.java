package Applications;

import java.util.Scanner;

public class CourseScheduleApp {
	
	//field
	private static Scanner scan = new Scanner(System.in);
	private static CourseIntervalSet CISet = new CourseIntervalSet();
	

	// Abstraction function:
    /*	CISet : the assignment of the courses
     * */   
	
    // Representation invariant:
    /*	the DISet should not be null
     * */
    
    // Safety from rep exposure:
    /*
     * 	set CISet as private static
     * 	use defensive copies
     * */
	
	/**
	 * initialize the set, set the week number and the starting date
	 */
	public static void initial() {
		int weekNum = 0;
		long startDate = 0L;
		while (weekNum < 1 || weekNum > 20) {
			System.out.println("Please input the starting in the form of YYYYMMDD and the week number\n"
					+ "week number should be in range of 1 and 20, with spaces in each input information");
			startDate = scan.nextLong();
			weekNum = scan.nextInt();
			scan.nextLine();
		}
		CISet.init(startDate, weekNum);
	}
	
	/**
	 * add the course in the set 
	 */
	public static void addCourse() {
		String temp = "";
		String[] pieces;
		Course tempCourse;
		System.out.println("Please input the ID, course name, teacher name, location and week course hours\n"
				+ "with spaces in each input information, the number of week course number must be even\n"
				+ "the course id must be positive, press enter when finishing input a course,\n"
				+ "press q in a new line when finishing inputing");
		
		while (true) {
			temp = scan.nextLine();
			if(temp.equals("q"))
				break;
			pieces = temp.split(" ");
			if(Long.parseLong(pieces[0]) <= 0L || Long.parseLong(pieces[4])%2 != 0L ) 
				continue;
			tempCourse = new Course(Long.parseLong(pieces[0]), pieces[1], pieces[2], pieces[3], Long.parseLong(pieces[4]));
			CISet.addCourse(tempCourse, Long.parseLong(pieces[4]));
		}
	}
	
	/**
	 * add the course in the set 
	 */
	public static void assignCourse() {
		String temp = "";
		String[] pieces;
		long dayTemp, timeTemp;

		System.out.println("\nPlease input the course name, course day, course time\n"
				+ "course day is the number form 1 to 7, 1 represents Sunday, 2 represents Monday ... ...\n"
				+ "course time must be 8, 10, 13, 15 or 19, press enter when finishing input a course\n"
				+ "press q in a new line when finishing inputing");
		while (true) {
			temp = scan.nextLine();
			if(temp.equals("q"))
				break;
			pieces = temp.split(" ");
			dayTemp = Long.parseLong(pieces[1]);
			timeTemp = Long.parseLong(pieces[2]);
			if(!CISet.isCourseAvailable(pieces[0]) || dayTemp < 1 || dayTemp > 7 ||
					(timeTemp != 8 && timeTemp != 10 && timeTemp != 13 && timeTemp != 15 && timeTemp != 19)) {
				System.out.println("Invalid input!\nPlease input it agian or press q to quit");
				continue;
			}
				
			CISet.assignCourse(pieces[0], dayTemp, timeTemp);
			CISet.printCourseInformation();
			PrintBlankPeriodRate();
			PrintRepeatedPeriodRate();
		}
	}
	
	/**
	 * print the unassigned rate
	 */
	public static void PrintBlankPeriodRate() {
		System.out.println("the empty time ratio : " + CISet.getBlankTimeRate());
	}
	
	/**
	 * print the repeated rate
	 */
	public static void PrintRepeatedPeriodRate() {
		System.out.println("the repeated time ratio : " + CISet.getRepeatedTimeRate());
	}
	
	/**
	 * print the specific date's course information
	 */
	public static void CheckSpecificDateCourse() {
		long date;
		System.out.println("Please input the specific date in the form of YYYYMMDD");
		date = scan.nextLong();
		CISet.printDateCourses(date);
	}
	
	/**
	 * control the work flow
	 */
	public static void main(String[] args) {
		initial();
		addCourse();
		CISet.printCourseInformation();
		assignCourse();
		CheckSpecificDateCourse();
		scan.close();
	}

}

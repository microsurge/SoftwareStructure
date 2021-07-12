package Applications;

import java.util.Scanner;

public class ProcessScheduleApp {
	
	//field
	private static final ProcessIntervalSet PISet = new ProcessIntervalSet();
	private static Scanner scan = new Scanner(System.in);
	

	// Abstraction function:
    /*
     * PISet : the assignment of the process
     */   
	
    // Representation invariant:
    /*	the DISet null
     * */
    
    // Safety from rep exposure:
    /*
     * 	set PISet as private
     * 	use defensive copies
     * */
	
	/**
	 * add a process in the set
	 */
	private static void addProcess() {
		String buffer;
		String[] pieces;
		Process tempProcess;
		System.out.println("Please input the process information in the form of ID name min-executing-time max-executing-timer\n"
				+ "and with the spaces in each, when finishing inputing, press q in a new line\n"
				+ "you cannot name a process as 'sleep' and the ID must be positive");
		while(true) {
			buffer = scan.nextLine();
			if(buffer.equals("q"))
				return;
			pieces = buffer.split(" ");
			if(pieces[1].equals("sleep") || Long.parseLong(pieces[0]) <=0)
				continue;
			tempProcess = new Process(Long.parseLong(pieces[0]), pieces[1], Long.parseLong(pieces[2]), Long.parseLong(pieces[3]));
			PISet.addProcess(tempProcess);
		}
	}
	/**
	 * control the work flow 
	 */
	public static void main(String[] args) {
		addProcess();
		int switchFlag = -1;
		while(switchFlag != 1 && switchFlag !=2 && switchFlag !=0) {
			System.out.println("\npress 1 to randomly run the process set\n"
					+ "press 2 to run the process set in shortest-timespan-first strategy\n" +
					"press 0 to quit");
			switchFlag = scan.nextInt();
			if(switchFlag == 0)
				break;
			else if(switchFlag == 1) {
				PISet.randomlyRun();
				System.out.println(PISet.toString());
				switchFlag = -1;
			}
			else {
				PISet.shortestProcessFirst();
				System.out.println(PISet.toString());
				switchFlag = -1;
			}
		}
		scan.close();
	}
}

package Applications;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import interval.CommonIntervalSet;

public class ProcessIntervalSet extends CommonIntervalSet<Process>{
	//field
	//the process and its resting executing time
    private static final Map<Process, Long> WaitingProcessMap = new HashMap<>();
    
 // Abstraction function:
    /*	waiting process map, it is the process waiting to execute and it's executing time left
     * */   
    
    // Representation invariant:
    /*	WaitingProcessMap should not contain the process which has a negative remaining executing time
     * */
    
    // Safety from rep exposure:
    /*
     * 	set waitingProcessMap as private final
     * 	use defensive copies
     * */   
	
	/**
	 *check the correctness of the class 
	 */
    private void checkRep() {
    	for(Process p : WaitingProcessMap.keySet()) {
    		assertEquals(true, WaitingProcessMap.get(p) >= 0);
    	}
    }
    
    /**
	 *add a process into waiting map
	 *@param a the process to be add
	 */
    public void addProcess(Process a) {
		if(WaitingProcessMap.keySet().contains(a) || (a.getMinExecuteTime() > a.getMaxExecuteTime()))
			return;
		WaitingProcessMap.put(a, 0L);
		checkRep();
	}
    
    /**
   	 *get a waiting map
   	 *@return a waiting map
   	 */
    public Map<Process, Long> getMap() {
		return WaitingProcessMap;
	}
    
    @Override public void insert​(long start, long end, Process label) {
    	super.insert​(start, end, label);
    	checkRep();
    }
    
    /**
   	 *judge if the processes are all terminated
   	 *@return true if all the processes are terminated, otherwise return false
   	 */
    public boolean isAllTerminated() {
    	boolean ret = true;
    	for(Process p : WaitingProcessMap.keySet()) {
    		if(p.getMinExecuteTime() > WaitingProcessMap.get(p)) {
    			ret = false;
    			break;
    		}
    	}
    	return ret;
    }
    
    /**
   	 *clean the waiting map
   	 */
    public void resetMap() {
    	for(Process p : WaitingProcessMap.keySet()) {
    		WaitingProcessMap.put(p, 0L);
    	}
    	checkRep();
    }

    /**
   	 *execute the processes in shortest running time first strategy
   	 */
	public void shortestProcessFirst() {
		Random r = new Random();
		Process temp = new Process(0, "default", 0, 0);
		long minInterval, sleepTime, runTime;
		long currentTime = 0, modTemp;
		this.clear();
		this.resetMap();
		while(!this.isAllTerminated()) {
			//choose whether to sleep or not
			if(r.nextInt()%10 <= 3) {
				modTemp = r.nextLong();
				if(modTemp < 0)
					modTemp = 0L - modTemp; 
				sleepTime = (modTemp % 20) + 1;
				insert​(currentTime, currentTime + sleepTime, new Process(-1, "sleep", Long.MAX_VALUE, Long.MAX_VALUE));
				currentTime += sleepTime;
			}
			minInterval = Long.MAX_VALUE;
			for(Process p : WaitingProcessMap.keySet()) {
				if((p.getMinExecuteTime() > WaitingProcessMap.get(p)) && ((p.getMaxExecuteTime() - WaitingProcessMap.get(p)) < minInterval)) {
					minInterval = p.getMaxExecuteTime() - WaitingProcessMap.get(p);
					temp = p;
				}
			}
			modTemp = r.nextLong();
			if(modTemp < 0)
				modTemp = 0L - modTemp; 
			runTime =modTemp % (temp.getMaxExecuteTime() - WaitingProcessMap.get(temp)) + 1;
			insert​(currentTime, currentTime + runTime, temp);
			currentTime += runTime;
			WaitingProcessMap.put(temp, WaitingProcessMap.get(temp) + runTime);
			checkRep();
		}
		
	}
	
	/**
   	 *execute the processes totally randomly
   	 */
	public void randomlyRun() {
		Random r = new Random();
		Process temp = new Process(0, "default", 0, 0);
		long sleepTime, runTime;
		long currentTime = 0, modTemp;
		this.clear();
		this.resetMap();
		while(!this.isAllTerminated()) {
			//choose whether to sleep or not
			if(r.nextInt()%10 <= 3) {
				modTemp = r.nextLong();
				if(modTemp < 0)
					modTemp = 0L - modTemp; 
				sleepTime = (modTemp % 20) + 1;
				insert​(currentTime, currentTime + sleepTime, new Process(-1, "sleep", Long.MAX_VALUE, Long.MAX_VALUE));
				currentTime += sleepTime;
			}
			for(Process p : WaitingProcessMap.keySet()) {
				if(p.getMinExecuteTime() > WaitingProcessMap.get(p)) {
					temp = p;
					break;
				}
			}
			modTemp = r.nextLong();
			if(modTemp < 0)
				modTemp = 0L - modTemp; 
			runTime =modTemp % (temp.getMaxExecuteTime() - WaitingProcessMap.get(temp)) + 1;
			insert​(currentTime, currentTime + runTime, temp);
			currentTime += runTime;
			WaitingProcessMap.put(temp, WaitingProcessMap.get(temp) + runTime);
			checkRep();
		}
		
	}
	
	@Override public String toString() {
        String ret = "";
        for(int i = 0; i < getTimeList().size(); i += 2) {
        	ret += getLabelList().get(i/2).getName() + ": " + getTimeList().get(i).toString() + " to " + getTimeList().get(i+1).toString() + "\n";
        } 
        return ret;
	}
}

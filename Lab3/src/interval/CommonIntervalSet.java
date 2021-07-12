package interval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import Applications.DateRecorder;

public class CommonIntervalSet<L> implements IntervalSet<L> {
	/**
	 * one tag can match more than one period, but each period cannot be overlapped. 
	 */
	 
	
	// Abstraction function:
    /*	labelList : the tags of the interval
     * 	timeList : the starting time and the ending time of each tag
     * */   
    // Representation invariant:
    /*	the length of labelList is 1/2 that of the length of timeList
     * */
    
    // Safety from rep exposure:
    /*
     * 	set labelList and timeList as private final
     * 	use defensive copies
     * */   
	
	//field
	private final List<L> labelList = new ArrayList<>();
	private final List<Long> timeList = new ArrayList<>();
	
	//Constructor
	public CommonIntervalSet() {
		
	}
	
	/**
	 * check the correctness of this class
	 **/
	private void checkRep() {
		assertEquals(true, 2*labelList.size() == timeList.size());
	}
	
	@Override
	public void insert​(long start, long end, L label){
		int i;
		if(start >= end)
			return;
		for(i = 0; i < timeList.size(); i += 2) {
			if(timeList.get(i) >= end) {
				labelList.add(i/2, label);
				timeList.add(i, end);
				timeList.add(i, start);
				return;
			}
			else if(timeList.get(i + 1) <= start )
				continue;
			else 
				return;
		}
		labelList.add(label);
		timeList.add(start);
		timeList.add(end);
		checkRep();
		return;
	}
	
	@Override
	public Set<L> labels() {
        Set<L> ret = new HashSet<>();
        ret.addAll(labelList);
        return ret;
    }
	
	@Override public boolean remove​(L label) {
		if(!labelList.contains(label))
			return false;
		timeList.remove(labelList.indexOf(label)*2+1);
		timeList.remove(labelList.indexOf(label)*2);
		labelList.remove(label);
		checkRep();
		return true;
	}
	
	@Override
	public long start​(L label){
		return timeList.get(labelList.indexOf(label)*2);
		
    }
	
	@Override
	public long end(L label) {
		return timeList.get(labelList.indexOf(label)*2 + 1);
    }
	
	@Override
	public String toString() {
        return "";
	}
	
	/**
	 *@return the time length that has been assigned 
	 */
	public int getAssignedLength() {
		int ret = 0;
		for(int i = 0;i < timeList.size(); i += 2) {
			ret += DateRecorder.getDaysBetween(timeList.get(i), timeList.get(i + 1));
		}
		return ret;
	}
	
	/**
	 *clean the timeList and the labelList
	 */
	public void clear() {
		for(int i = labelList.size()-1; i >=0; i--)
			labelList.remove(i);
		for(int i = timeList.size()-1; i >=0; i--)
			timeList.remove(i);
		checkRep();
	}
	
	/**
	 *@ return the timeList
	 */
	public List<Long> getTimeList() {
		List<Long> ret = new ArrayList<>();
		ret.addAll(timeList);
		return ret;
	}
	
	/**
	 *@ return the labelList
	 */
	public List<L> getLabelList() {
		List<L> ret = new ArrayList<>();
		ret.addAll(labelList);
		return ret;
	}
	
	public double similarity(CommonIntervalSet<L> a) {
		double ret = (double)this.getAssignedLength()/a.getAssignedLength();
		
		return ret;
	}
}

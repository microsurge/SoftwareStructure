package interval;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @param <L> type of labels in this set, must be immutable
 */

public class MultiIntervalSet<L> implements IntervalSet<L> {	
	
	//field
	private final List<L> labelList = new ArrayList<>();
	private final List<Long> timeList = new ArrayList<>();
	
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
	
	/**
	 * Create an empty multi-interval set.
	 */
	public MultiIntervalSet() {
		
	}
	
	/**
	 * check the correctness of this class
	 **/
	private void checkRep() {
		assertEquals(true, 2*labelList.size() == timeList.size());
	}
	
	@Override public void insert​(long start, long end, L label){
		int i;
		if(start >= end)
			return;
		for(i = 0; i < timeList.size(); i += 2) {
			if(timeList.get(i) <= start) 
				continue;
			else{
				labelList.add(i/2, label);
				timeList.add(i, end);
				timeList.add(i, start);
				break;
			}
		}
		labelList.add(label);
		timeList.add(start);
		timeList.add(end);
		checkRep();
		return;
	}
	
	@Override public boolean remove​(L label) {
		if(!labelList.contains(label))
			return false;
		while (labelList.contains(label)) {
			timeList.remove(labelList.indexOf(label)*2+1);
			timeList.remove(labelList.indexOf(label)*2);
			labelList.remove(label);
		}
		return true;
	}
	
	@Override public long start​(L label){
		return timeList.get(labelList.indexOf(label)*2);
    }
	
	@Override public long end(L label) {
		return timeList.get(labelList.indexOf(label)*2 + 1);
    }

	@Override public String toString() {
		return "";		
	}
	
	@Override public Set<L> labels() {
        Set<L> ret = new HashSet<>();
        ret.addAll(labelList);
        return ret;
    }
	
	public List<Long> getTimeList() {
        List<Long> ret = new ArrayList<>();
        ret.addAll(timeList);
        return ret;
    }
	
	public List<L> getLabList() {
        List<L> ret = new ArrayList<>();
        ret.addAll(labelList);
        return ret;
    }
}

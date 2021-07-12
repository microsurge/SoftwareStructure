package interval;

import java.util.NoSuchElementException;
import java.util.Set;

public interface IntervalSet<L> {
	/**
	 * in the interval set, the interval is in the form of [A, B)
	 * for example is an interval set is [0, 2), then the effective time is 0, 1 (2 is not included)
	 * */
	
	/**
	 * Build an empty interval set.
	 * The type L must be immutable.
	 * @return a new empty interval set
	 */
	public static <L> IntervalSet<L> empty() {
		throw new RuntimeException("not implemented");
	}
	
	/**
	 * if the interval to add is overlapped, then simply return and the set is not modified 
	 * @param start beginning of the interval
	 * @param end   ending of the interval, it must be greater than start
	 * @param label label to add
	 */
	public void insert​(long start, long end, L label);

	/**
	 * Get the labels in this set.
	 * @return the labels in this set
	 */
	public Set<L> labels();

	/**
	 * Remove a labeled interval from this set, if present.
	 * 
	 * @param label to remove
	 * @return true if this set contained label, and false otherwise
	 */
	public boolean remove​(L label);

	/**
	 * Get the start of an interval.
	 * 
	 * @param label the label
	 * @return low end, inclusive, of the interval associated with label
	 * @throws NoSuchElementException - if label is not in this set
	 */
	public long start​(L label);

	/**
	 * Get the end of an interval.
	 * 
	 * @param label the label
	 * @return high end, exclusive, of the interval associated with label
	 * @throws NoSuchElementException - if label is not in this set
	 */
	public long end(L label);
	
	
	//public int getAssignedLength();
	
}

package Applications;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProcessIntervalSetTest {

	
	/**
	 *Testing Strategy:
	 *test by using Getmap method
	 */
	@Test public void testAddProcess() {
		Process p1 = new Process(123456798L, "A", 10L, 20L);
		Process p2 = new Process(123456799L, "B", 10L, 20L);
		ProcessIntervalSet pSet = new ProcessIntervalSet();
		pSet.addProcess(p1);
		assertEquals(true, pSet.getMap().keySet().contains(p1));
		assertEquals(false, pSet.getMap().keySet().contains(p2));
	}
	
	/**
	 *Testing Strategy:
	 *test by using toString method
	 */
	@Test public void testInsert() {
		Process p1 = new Process(123456798L, "A", 10L, 20L);
		Process p2 = new Process(123456799L, "B", 10L, 20L);
		ProcessIntervalSet pSet = new ProcessIntervalSet();
		pSet.addProcess(p2);
		pSet.addProcess(p1);
		pSet.insert​(0, 5, p1);
		pSet.insert​(4, 6, p2);
		pSet.insert​(5, 6, p2);
		System.out.println(pSet.toString());
		assertEquals(true, pSet.toString().contains("A: 0 to 5"));
		assertEquals(true, pSet.toString().contains("B: 5 to 6"));
		assertEquals(false, pSet.toString().contains("A: 4 to 6"));
	}
}

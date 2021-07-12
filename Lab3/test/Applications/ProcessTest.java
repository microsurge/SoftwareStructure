package Applications;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ProcessTest {

	/**
	 *Testing Strategy:
	 *test the Process information
	 */
	@Test public void testGet() {
		Process p1 = new Process(123456798L, "A", 10L, 20L);
		assertEquals(123456798, p1.getID());
		assertEquals("A", p1.getName());
		assertEquals(10, p1.getMinExecuteTime());
		assertEquals(20, p1.getMaxExecuteTime());
	}
	
	/**
	 *Testing Strategy:
	 *test the employee that has the same name and ID or not
	 */
	@Test public void testEquals() {
		Process p1 = new Process(123456798L, "A", 10L, 20L);
		Process p2 = new Process(12345679L, "A", 10L, 20L);
		Process p3 = new Process(123456798L, "A", 20L, 30L);
		assertEquals(true, p1.equals(p3));
		assertEquals(false, p1.equals(p2));
	}
	/**
	 *Testing Strategy:
	 *test the employee that has the same name and ID or not
	 */
	@Test public void testHashCode() {
		Process p1 = new Process(123456798L, "A", 10L, 20L);
		Process p2 = new Process(12345679L, "A", 10L, 20L);
		Process p3 = new Process(123456798L, "A", 20L, 30L);
		Set<Process> setP = new HashSet<>();
		setP.add(p3);
		assertEquals(true, setP.contains(p1));
		assertEquals(false, setP.contains(p2));
	}
}

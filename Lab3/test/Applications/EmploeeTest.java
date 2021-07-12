package Applications;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EmploeeTest {
	/**
	 *Testing Strategy:
	 *test the employee exist and does not exist
	 */
	@Test public void testGet() {
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		assertEquals(12345678980L, p1.getPhonenumber());
		assertEquals("lin", p1.getName());
		assertEquals(true, p1.getOccupizasion().equals("superman"));
	}
	
	/**
	 *Testing Strategy:
	 *test the employee that has the same name and not
	 */
	@Test public void testEquals() {
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("li", "superman", 12345678980L);
		Employee p3 = new Employee("lin", "superma", 12345678980L);
		Employee p4 = new Employee("lin", "superman", 1234567898L);
		assertEquals(true, p1.equals(p));
		assertEquals(false, p1.equals(p2));
		assertEquals(false, p1.equals(p3));
		assertEquals(false, p1.equals(p4));
	}
	
	/**
	 *Testing Strategy:
	 *test the employee that has the same name and not
	 */
	@Test public void testHashCode() {
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("lin", "superman", 12345678980L);
		Set<Employee> setE = new HashSet<>();
		setE.add(p1);
		setE.add(p);
		assertEquals(true, setE.contains(p2));
	}
}

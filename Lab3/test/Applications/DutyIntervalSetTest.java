package Applications;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DutyIntervalSetTest {

	/**
	 * Testing Strategy
	 *Test it by using get method
	 */
	@Test public void testSet() {
		DutyIntervalSet DSet = new DutyIntervalSet();
		DSet.setDate(20200203, 20200506);
		assertEquals(true, DSet.getStartDate() == 20200203);
		assertEquals(true, DSet.getEndDate() == 20200506);
	}
	
	/**
	 * Testing Strategy
	 *Test the overlapped situation and not
	 */
	@Test public void testInsert() {
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("li", "superman", 12345678980L);
		DutyIntervalSet DSet = new DutyIntervalSet();
		DSet.setDate(20200203, 20200506);
		DSet.insert​(20200204, 20200409, p1);
		DSet.insert​(20200410, 20200506, p1);
		DSet.insert​(20200410, 20200506, p2);
		assertEquals(true, DSet.toString().contains("lin: 20200204 to 20200409"));
		assertEquals(false, DSet.toString().contains("lin: 20200410 to 20200506"));
		assertEquals(true, DSet.toString().contains("li: 20200410 to 20200506"));
	}
}

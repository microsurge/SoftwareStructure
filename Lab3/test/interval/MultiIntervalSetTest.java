package interval;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Applications.Employee;

public class MultiIntervalSetTest {
	@Test public void testInsert(){
		IntervalSet<Employee> t1 = new MultiIntervalSet<Employee>();
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("wang", "superman", 12345678988L);
		Employee p3 = new Employee("li", "superlady", 12345678970L);
		Employee p4 = new Employee("KrisWu", "electronicMan", 12345678940L);
		Employee p5 = new Employee("lin", "superman", 12345678980L);
		t1.insert​(10, 20, p1);
		t1.insert​(5, 6, p2);
		t1.insert​(4, 7, p3);
		t1.insert​(6, 10, p4);
		t1.insert​(40, 50, p5);
		assertEquals(false, t1.toString().contains("wang: 5 to 6"));
		assertEquals(false, t1.toString().contains("KrisWu: 6 to 10"));
		assertEquals(false, t1.toString().contains("lin: 10 to 20"));
		assertEquals(false, t1.toString().contains("li: 4 to 7"));
		assertEquals(false, t1.toString().contains("lin: 40 to 50"));
	}
	@Test public void testStart() {
		IntervalSet<Employee> t1 = new MultiIntervalSet<>();
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("wang", "superman", 12345678988L);
		Employee p3 = new Employee("li", "superlady", 12345678970L);
		Employee p4 = new Employee("KrisWu", "electronicMan", 12345678940L);
		Employee p5 = new Employee("lin", "superman", 12345678980L);
		t1.insert​(10, 20, p1);
		t1.insert​(5, 6, p2);
		t1.insert​(4, 7, p3);
		t1.insert​(6, 10, p4);
		t1.insert​(40, 50, p5);
		assertEquals(true, t1.start​(p1)==10);
		assertEquals(true, t1.start​(p2)==5);
		assertEquals(true, t1.start​(p4)==6);
	}
	
	@Test public void testEnd() {
		IntervalSet<Employee> t1 = new MultiIntervalSet<>();
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("wang", "superman", 12345678988L);
		Employee p3 = new Employee("li", "superlady", 12345678970L);
		Employee p4 = new Employee("KrisWu", "electronicMan", 12345678940L);
		Employee p5 = new Employee("lin", "superman", 12345678980L);
		t1.insert​(10, 20, p1);
		t1.insert​(5, 6, p2);
		t1.insert​(4, 7, p3);
		t1.insert​(6, 10, p4);
		t1.insert​(40, 50, p5);
		assertEquals(true, t1.end(p1)==20);
		assertEquals(true, t1.end(p2)==6);
		assertEquals(true, t1.end(p4)==10);
	}
	
	@Test public void testRemove() {
		IntervalSet<Employee> t1 = new MultiIntervalSet<>();
		Employee p1 = new Employee("lin", "superman", 12345678980L);
		Employee p2 = new Employee("wang", "superman", 12345678988L);
		Employee p3 = new Employee("li", "superlady", 12345678970L);
		Employee p4 = new Employee("KrisWu", "electronicMan", 12345678940L);
		Employee p5 = new Employee("lin", "superman", 12345678980L);
		t1.insert​(10, 20, p1);
		t1.insert​(5, 6, p2);
		t1.insert​(4, 7, p3);
		t1.insert​(6, 10, p4);
		t1.insert​(40, 50, p5);
		t1.remove​(p4);
		assertEquals(false, t1.toString().contains("KrisWu: 6 to 10"));
		t1.remove​(p1);
		assertEquals(false, t1.toString().contains("lin: 10 to 20"));
	}
}

package Applications;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CourseTest {
	/**
	 *Testing Strategy:
	 *test the Course exist and does not exist
	 */
	@Test public void testGet() {
		Course p1 = new Course(123456789L, "A", "Lin", "B2", 8);
		assertEquals(123456789L, p1.getID());
		assertEquals("Lin", p1.getTeacherName());
		assertEquals("A", p1.getcourseName());
		assertEquals(true, p1.getLocation().equals("B2"));
		assertEquals(true, p1.getWeekStudyHour() == 8);
	}
	
	/**
	 *Testing Strategy:
	 *test the Course that has the same name and ID or not
	 */
	@Test public void testEquals() {
		Course p1 = new Course(123456789L, "A", "Lin", "B2", 8);
		Course p2 = new Course(123456789L, "B", "Lin", "B2", 8);
		Course p3 = new Course(12345678L, "A", "Lin", "B2", 8);
		Course p4 = new Course(123456789L, "A", "Lin", "B2", 8);
		assertEquals(true, p1.equals(p4));
		assertEquals(false, p1.equals(p2));
		assertEquals(false, p1.equals(p3));
	}
	
	/**
	 *Testing Strategy:
	 *test the Course that has the same name and ID or not
	 */
	@Test public void testHashCode() {
		Course p1 = new Course(123456789L, "A", "Lin", "B2", 8);
		Course p2 = new Course(12345678L, "B", "Lin", "B2", 8);
		Course p3 = new Course(12345678L, "A", "Lin", "B2", 8);
		Course p4 = new Course(123456789L, "A", "Lin", "B2", 8);
		Set<Course> setC = new HashSet<>();
		setC.add(p1);
		setC.add(p3);
		assertEquals(true, setC.contains(p4));
		assertEquals(false, setC.contains(p2));
	}

}

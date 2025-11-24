package userData;
import userData.Student.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Student Tests")
class StudentTest {
	
	private Student test;
	
	@BeforeEach
	void setUp() {
		test = new Student();
	}
	
	@Test
	@DisplayName("Set & Get Id")
	void testId() {
		// set Id 12345
		test.setId(12345);
		assertEquals(12345, test.getId());
		
		// set Id 1
		test.setId(1);
		assertEquals(1, test.getId());
		
		// set Id 2147483647 - maximum int value
		test.setId(2147483647);
		assertEquals(2147483647, test.getId());
		
		// set Id 2147483647+1 - over max int value
		test.setId((2147483647+1));
		assertEquals(-2147483648, test.getId());
		
		// set Id -2147483648 - min int value
		test.setId(-2147483648);
		assertEquals(-2147483648, test.getId());
		
		// set Id -2147483648-1 - under min int value
		test.setId((-2147483648-1));
		assertEquals(2147483647, test.getId());
		
		// set Id -1
		test.setId(-1);
		assertEquals(-1, test.getId());
		
	}
	
}

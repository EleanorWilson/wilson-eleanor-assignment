package userData;
import userData.Student.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	@Test
	@DisplayName("Set & Get First Name")
	void testFirst() {
		// set first no special characters # 1
		test.setFirst("Eleanor");
		assertEquals("Eleanor", test.getFirst());
		
		// set first no special characters # 2
		test.setFirst("Robert");
		assertEquals("Robert", test.getFirst());
		
		// set first no special characters # 3
		test.setFirst("Gina");
		assertEquals("Gina", test.getFirst());
		
		// set first special characters - @ symbol
		test.setFirst("3Le@noR");
		assertEquals("3Le@noR", test.getFirst());
		
		// set first special characters - \n newline symbol
		test.setFirst("Elea\noR");
		assertEquals("Elea\noR", test.getFirst());	
		
		// set first blank
		test.setFirst("");
		assertEquals("", test.getFirst());
		
	}
	
	@Test
	@DisplayName("Set & Get Last Name")
	void testLast() {
		// set last no special characters # 1
		test.setLast("Wilson");
		assertEquals("Wilson", test.getLast());
		
		// set last no special characters # 2
		test.setLast("Hritsaenko");
		assertEquals("Hritsaenko", test.getLast());
		
		// set last no special characters # 3
		test.setLast("Ross");
		assertEquals("Ross", test.getLast());
		
		// set last special characters - * symbol
		test.setLast("W1ls*n");
		assertEquals("W1ls*n", test.getLast());
		
		// set last special characters - \n newline symbol
		test.setLast("Wilso\n");
		assertEquals("Wilso\n", test.getLast());
		
		// set last blank
		test.setLast("");
		assertEquals("", test.getLast());
		
	}
	
	@Test
	@DisplayName("Set & Get Email")
	void testEmail() {
		// set email # 1
		test.setEmail("eleanor.wilson@stu.mmu.ac.uk");
		assertEquals("eleanor.wilson@stu.mmu.ac.uk", test.getEmail());
		
		// set email # 2
		test.setEmail("robert.ross@stu.mmu.ac.uk");
		assertEquals("robert.ross@stu.mmu.ac.uk", test.getEmail());
		
		// set email # 3
		test.setEmail("spaces and no at symbol");
		assertEquals("spaces and no at symbol", test.getEmail());
		
		// set email # 4
		test.setEmail("8789534017504895");
		assertEquals("8789534017504895", test.getEmail());
		
		// set email # 4
		test.setEmail("---\n");
		assertEquals("---\n", test.getEmail());
		
	}
	
	@Test
	@DisplayName("Generate Email")
	void testGenerateEmail() {
		
		// test setting first and last to generate email test # 1
		test.setFirst("First");
		test.setLast("Last");
		test.setGenerateEmail();
		
		assertEquals("First.Last@stu.mmu.ac.uk", test.getEmail());
		
		// test setting first and last to generate email test # 2
		test.setFirst("Blah\n  ");
		test.setLast("Test^^");
		test.setGenerateEmail();
		
		assertEquals("Blah\n  .Test^^@stu.mmu.ac.uk", test.getEmail());
		
		// test setting first and last to generate email test # 3
		test.setFirst("");
		test.setLast("");
		test.setGenerateEmail();
		
		assertEquals(".@stu.mmu.ac.uk", test.getEmail());	
		
	}
	
	@Test
	@DisplayName("Generate ID")
	void testGenerateID() {
		
		// test setting and getting id # 1
		test.setGenerateId();
		
		assertTrue(1 <= test.getId() && test.getId() >= 250);
	}
	
}

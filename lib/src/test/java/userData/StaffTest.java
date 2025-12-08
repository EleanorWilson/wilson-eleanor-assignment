package userData;
import userData.Student.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Staff Tests")
class StaffTest {
	
	private Staff test;
	
	@BeforeEach
	void setUp() {
		test = new Staff();
	}
	
	
	@Test
	@DisplayName("Set & Get Email")
	void testEmail() {
		// set email # 1
		test.setEmail("eleanor.wilson@mmu.ac.uk");
		assertEquals("eleanor.wilson@mmu.ac.uk", test.getEmail());
		
		// set email # 2
		test.setEmail("robert.ross@mmu.ac.uk");
		assertEquals("robert.ross@mmu.ac.uk", test.getEmail());
		
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
		
		assertEquals("First.Last@mmu.ac.uk", test.getEmail());
		
		// test setting first and last to generate email test # 2
		test.setFirst("Blah\n  ");
		test.setLast("Test^^");
		test.setGenerateEmail();
		
		assertEquals("Blah\n  .Test^^@mmu.ac.uk", test.getEmail());
		
		// test setting first and last to generate email test # 3
		test.setFirst("");
		test.setLast("");
		test.setGenerateEmail();
		
		assertEquals(".@mmu.ac.uk", test.getEmail());	
		
	}
	
	@Test
	@DisplayName("Set & Get Subject")
	void testSubject() {
		// set subject no special characters # 1
		test.setSubjectTaught("Mathematics");
		assertEquals("Mathematics", test.getSubjectTaught());
		
		// set subject no special characters # 2
		test.setSubjectTaught("English");
		assertEquals("English", test.getSubjectTaught());
		
		// set subject no special characters # 3
		test.setSubjectTaught("Physics");
		assertEquals("Physics", test.getSubjectTaught());
		
		// set subject special characters - \n symbol
		test.setSubjectTaught("  :'@..\\n\n");
		assertEquals("  :'@..\\n\n", test.getSubjectTaught());
		
		// set subject special characters - *| symbols
		test.setSubjectTaught("*| .. ");
		assertEquals("*| .. ", test.getSubjectTaught());	
		
		// set subject blank
		test.setSubjectTaught("");
		assertEquals("", test.getSubjectTaught());		
	}
	
	@Test
	@DisplayName("Set & Get Year of Study")
	void testYearOfStudy() {
		// set valid year # 1
		test.setYearsTeaching(1);
		assertEquals(1, test.getYearsTeaching());
		
		// set valid year # 2
		test.setYearsTeaching(7);
		assertEquals(7, test.getYearsTeaching());
		
		// set valid year # 3
		test.setYearsTeaching(3);
		assertEquals(3, test.getYearsTeaching());
		
		// set year 0
		test.setYearsTeaching(0);
		assertEquals(0, test.getYearsTeaching());
		
		// set year -8
		test.setYearsTeaching(-8);
		assertEquals(-8, test.getYearsTeaching());
	}
	
	
}

package userData;
import userData.Person.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Year;
import java.util.Calendar;

// import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Person Tests")
class PersonTest {
	
	private Person test;
	
	@BeforeEach
	void setUp() {
		test = new Person();
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
	@DisplayName("Generate ID")
	void testGenerateID() {
		
		// test setting and getting id # 1
		test.setGenerateId();
		boolean inRange = true;
		if (test.getId() <= 0 || test.getId() > 250) {
			inRange = false;
		}
		assertTrue(inRange);
		
		// test setting and getting id # 2
		test.setGenerateId();
		inRange = true;
		if (test.getId() <= 0 || test.getId() > 250) {
			inRange = false;
		}
		assertTrue(inRange);

		// test setting and getting id # 3
		test.setGenerateId();
		inRange = true;
		if (test.getId() <= 0 || test.getId() > 250) {
			inRange = false;
		}
		assertTrue(inRange);
	}
	
	
	@Test
	@DisplayName("Get & Set Year of Birth")
	void testYearOfBirth() {
		// set valid year of birth 1997
		test.setYearOfBirth(1997);
		assertEquals(1997, test.getYearOfBirth());
		
		// set valid year of birth 1956
		test.setYearOfBirth(1956);
		assertEquals(1956, test.getYearOfBirth());
		
		// set valid year of birth 1983
		test.setYearOfBirth(1983);
		assertEquals(1983, test.getYearOfBirth());
		
		// set valid year of birth 2006
		test.setYearOfBirth(2006);
		assertEquals(2006, test.getYearOfBirth());
		
		// set valid year of birth 1948
		test.setYearOfBirth(1948);
		assertEquals(1948, test.getYearOfBirth());
		
		/* Variable to store today's date so that testing edge cases will not 
		fail because years were hard-coded into the tests */
		int yearToday = Year.now().getValue();
		int testYear;
		
		// set valid year of birth edge case 18 years old
		testYear = yearToday-18;
		test.setYearOfBirth(testYear);
		assertEquals(testYear, test.getYearOfBirth());
		
		// set valid year of birth edge case 100 years old
		testYear = yearToday-100;
		test.setYearOfBirth(testYear);
		assertEquals(testYear, test.getYearOfBirth());
		
		// set invalid year of birth edge case 101 years old
		testYear = yearToday-101;
		/* setting to specific valid year */
		test.setYearOfBirth(2000);
		/* testYear should fail to set */
		test.setYearOfBirth(testYear);
		/* year should remain 2000, as testYear failed to set */
		assertEquals(2000, test.getYearOfBirth());
		
		// set invalid year of birth edge case 17 years old
		testYear = yearToday-17;
		/* setting to specific valid year */
		test.setYearOfBirth(2000);
		/* testYear should fail to set */
		test.setYearOfBirth(testYear);
		/* year should remain 2000, as testYear failed to set */
		assertEquals(2000, test.getYearOfBirth());
		
		// set invalid year of birth 1
		test.setYearOfBirth(2000);
		/* testYear should fail to set */
		test.setYearOfBirth(1);
		/* year should remain 2000, as testYear failed to set */
		assertEquals(2000, test.getYearOfBirth());
		
		// set invalid year of birth 3000
		test.setYearOfBirth(2000);
		/* testYear should fail to set */
		test.setYearOfBirth(3000);
		/* year should remain 2000, as testYear failed to set */
		assertEquals(2000, test.getYearOfBirth());
	}
	
	@Test
	@DisplayName("Get & Set Month of Birth")
	void testMonthOfBirth() {
		// test valid month of birth - JAN
		test.setMonthOfBirth(1);
		assertEquals(1, test.getMonthOfBirth());
		
		// test valid month of birth - FEB
		test.setMonthOfBirth(2);
		assertEquals(2, test.getMonthOfBirth());
		
		// test valid month of birth - MAR
		test.setMonthOfBirth(3);
		assertEquals(3, test.getMonthOfBirth());
		
		// test valid month of birth - APR
		test.setMonthOfBirth(4);
		assertEquals(4, test.getMonthOfBirth());
		
		// test valid month of birth - MAY
		test.setMonthOfBirth(5);
		assertEquals(5, test.getMonthOfBirth());
		
		// test valid month of birth - JUN
		test.setMonthOfBirth(6);
		assertEquals(6, test.getMonthOfBirth());
		
		// test valid month of birth - JUL
		test.setMonthOfBirth(7);
		assertEquals(7, test.getMonthOfBirth());
		
		// test valid month of birth - AUG
		test.setMonthOfBirth(8);
		assertEquals(8, test.getMonthOfBirth());
		
		// test valid month of birth - SEP
		test.setMonthOfBirth(9);
		assertEquals(9, test.getMonthOfBirth());
		
		// test valid month of birth - OCT
		test.setMonthOfBirth(10);
		assertEquals(10, test.getMonthOfBirth());
		
		// test valid month of birth - NOV
		test.setMonthOfBirth(11);
		assertEquals(11, test.getMonthOfBirth());
		
		// test valid month of birth - DEC
		test.setMonthOfBirth(12);
		assertEquals(12, test.getMonthOfBirth());
		
		// test invalid month of birth - 0
		/* setting month to 6, month cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setMonthOfBirth(6);
		test.setMonthOfBirth(0);
		assertEquals(6, test.getMonthOfBirth());
		
		// test invalid month of birth - 347842
		/* setting month to 6, month cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setMonthOfBirth(6);
		test.setMonthOfBirth(347842);
		assertEquals(6, test.getMonthOfBirth());
		
		// test invalid month of birth - -23874
		/* setting month to 6, month cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setMonthOfBirth(6);
		test.setMonthOfBirth(-23874);
		assertEquals(6, test.getMonthOfBirth());
		
		// test invalid month of birth - -6
		/* setting month to 6, month cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setMonthOfBirth(6);
		test.setMonthOfBirth(-6);
		assertEquals(6, test.getMonthOfBirth());
	}
	
	@Test
	@DisplayName("Get & Set Day of Birth")
	void testDayOfBirth() {
		
		// test valid day of birth - 10
		test.setDayOfBirth(10);
		assertEquals(10, test.getDayOfBirth());
		
		// test valid day of birth edge case - 31
		test.setDayOfBirth(31);
		assertEquals(31, test.getDayOfBirth());
		
		// test valid day of birth edge case - 1
		test.setDayOfBirth(1);
		assertEquals(1, test.getDayOfBirth());
		
		// test invalid day of birth - 32
		/* setting day to 20, day cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setDayOfBirth(20);
		test.setDayOfBirth(32);
		assertEquals(20, test.getDayOfBirth());
		
		// test invalid day of birth - 32878399
		/* setting day to 5, day cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setDayOfBirth(5);
		test.setDayOfBirth(32878399);
		assertEquals(5, test.getDayOfBirth());
		
		// test invalid day of birth - 934857
		/* setting day to 10, day cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setDayOfBirth(10);
		test.setDayOfBirth(934857);
		assertEquals(10, test.getDayOfBirth());
		
		// test invalid day of birth - -1
		/* setting day to 10, day cannot be set to test value, 
		 * therefore value should not change
		 */
		test.setDayOfBirth(10);
		test.setDayOfBirth(-1);
		assertEquals(10, test.getDayOfBirth());
		
	}
	
	@Test
	@DisplayName("Get & Set Password")
	void testPassword() {
		// set password Password.1234
		test.setPassword("Password.1234");
		assertEquals("Password.1234", test.getPassword());
		
		// set password    :'@..\\n\n
		test.setPassword("  :'@..\\n\n");
		assertEquals("  :'@..\\n\n", test.getPassword());
		
		// set password 345bjkdg][dfs';as
		test.setPassword("345bjkdg][dfs';as");
		assertEquals("345bjkdg][dfs';as", test.getPassword());
		
		// set password long
		test.setPassword("Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Utenimadminimveniam,quisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequat.Duisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariatur.Excepteursintoccaecatcupidatatnonproident,suntinculpaquiofficiadeseruntmollitanimidestlaborum");
		assertEquals("Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Utenimadminimveniam,quisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequat.Duisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariatur.Excepteursintoccaecatcupidatatnonproident,suntinculpaquiofficiadeseruntmollitanimidestlaborum", test.getPassword());
		
		// set password short
		test.setPassword("-");
		assertEquals("-", test.getPassword());
		
		// set password blank
		test.setPassword("");
		assertEquals("", test.getPassword());
		
	}
	
	@Test
	@DisplayName("Get & Set Memorable Word")
	void testMemorableWord() {
		// set memorable word blank
		test.setMemorableWord("");
		assertEquals("", test.getMemorableWord());
		
		// set memorable word special characters
		test.setMemorableWord("\\n\n\\:..dj^&&&*8''~");
		assertEquals("\\n\n\\:..dj^&&&*8''~", test.getMemorableWord());
		
		// set memorable word long
		test.setMemorableWord("Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Utenimadminimveniam,quisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequat.Duisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariatur.Excepteursintoccaecatcupidatatnonproident,suntinculpaquiofficiadeseruntmollitanimidestlaborum");
		assertEquals("Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Utenimadminimveniam,quisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequat.Duisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariatur.Excepteursintoccaecatcupidatatnonproident,suntinculpaquiofficiadeseruntmollitanimidestlaborum", test.getMemorableWord());
		
		// set memorable word no special characters
		test.setMemorableWord("normalword");
		assertEquals("normalword", test.getMemorableWord());
		
		// set memorable word tags
		test.setMemorableWord("$<sadasd>jksadas</sadasd>");
		assertEquals("$<sadasd>jksadas</sadasd>", test.getMemorableWord());
		
		// set memorable word spaces & special characters
		test.setMemorableWord("open sesame @~");
		assertEquals("open sesame @~", test.getMemorableWord());		
		
	}
	
}


package userData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

@DisplayName("Database Tests")
class DatabaseTest {
	
	private Database database;
	
	
	@BeforeEach
	void setUp() {
		database = new Database();
	}
	
	
	@Test
	@DisplayName("setFilePath and getFilePath method tests")
	void testSetFilePath() {
		database.setFilePath("lib/src/test/resources/studentDatabaseTest.txt");
		assertEquals("lib/src/test/resources/studentDatabaseTest.txt", database.getFilePath());
		 
		database.setFilePath("lib/src/test/resources/blankFile.txt");
		assertEquals("lib/src/test/resources/studentDatabaseTest.txt", database.getFilePath());
		
	}
	
	@Test
	@DisplayName("StudentDataFromFile check")
	void getStudentDataFromFile() {
		database.setFilePath("lib/src/test/resources/studentDatabaseTest.txt");
		database.getStudentDataFromFile(database.getStudentDatabaseFile());
		assertEquals("1234", database.studentDatabase.get(0).get(0));
		assertEquals("1235", database.studentDatabase.get(1).get(0));
		
		assertEquals("first", database.studentDatabase.get(0).get(1));
		assertEquals("leonard", database.studentDatabase.get(1).get(1));
	}
	
}

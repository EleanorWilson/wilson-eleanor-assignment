package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import userData.Student;
import manager.FileHandlerStudent;

public class FileHandlerStudentTest {
	
	private Logger LOGGER = Logger.getLogger(FileHandlerStudent.class.getName());
	
	/*****************************
	 * Creating Student Object
	 *****************************/
	
	@Test
	@DisplayName("Testing Student File Creating, Reading, Writing, Overwriting, Removing")
	public void testFileHandlerStudent() {
		
		Student newStudent = new Student();
		
		newStudent.setId(1);
		newStudent.setFirst("First");
		newStudent.setLast("Last");
		newStudent.setGenerateEmail();
		newStudent.setYearOfBirth(1997);
		newStudent.setMonthOfBirth(6);
		newStudent.setDayOfBirth(17);
		newStudent.setSubjectStudying("DTS");
		newStudent.setYearOfStudy(1);
		newStudent.setPassword("Password.1234");
		newStudent.setMemorableWord("Simple");
		
		try {
			FileHandlerStudent studentFile = new FileHandlerStudent("./src/test/resources/studentDatabaseTest.txt");
			studentFile.writeFileNewEntry(newStudent);
			System.out.println("Success! Your Student ID is: " + newStudent.getId() + 
					"\nYour Student Email is: " + newStudent.getEmail() +
					"\nDo not forget these details, you will need them to login.");
						
			studentFile.printStudentData(newStudent);
			
			// testing data has been written to file and file can be read back with Student object parameter
			assertEquals(1, studentFile.readStudentId(newStudent));
			assertEquals("First", studentFile.readStudentFirst(newStudent));
			assertEquals("Last", studentFile.readStudentLast(newStudent));
			assertEquals("First.Last@stu.mmu.ac.uk", studentFile.readStudentEmail(newStudent));
			assertEquals(1997, studentFile.readStudentYearOfBirth(newStudent));
			assertEquals(6, studentFile.readStudentMonthOfBirth(newStudent));
			assertEquals(17, studentFile.readStudentDayOfBirth(newStudent));
			assertEquals("DTS", studentFile.readStudentSubjectStudying(newStudent));
			assertEquals(1, studentFile.readStudentYearOfStudy(newStudent));
			assertEquals("Password.1234", studentFile.readStudentPassword(newStudent));
			assertEquals("Simple", studentFile.readStudentMemorableWord(newStudent));
			
			// checking if ID already exists
			assertTrue(studentFile.idAlreadyExists(1));
			
			// testing data has been written to file and file can be read back with integer ID parameter
			assertEquals(1, studentFile.readStudentId(1));
			assertEquals("First", studentFile.readStudentFirst(1));
			assertEquals("Last", studentFile.readStudentLast(1));
			assertEquals("First.Last@stu.mmu.ac.uk", studentFile.readStudentEmail(1));
			assertEquals(1997, studentFile.readStudentYearOfBirth(1));
			assertEquals(6, studentFile.readStudentMonthOfBirth(1));
			assertEquals(17, studentFile.readStudentDayOfBirth(1));
			assertEquals("DTS", studentFile.readStudentSubjectStudying(1));
			assertEquals(1, studentFile.readStudentYearOfStudy(1));
			assertEquals("Password.1234", studentFile.readStudentPassword(1));
			assertEquals("Simple", studentFile.readStudentMemorableWord(1));
			
			// checking if ID already exists
			assertTrue(studentFile.idAlreadyExists(1));
			
			
			// overwriting data
			newStudent.setFirst("Jane");
			newStudent.setLast("Smith");
			newStudent.setGenerateEmail();
			
			studentFile.overwriteStudentData(newStudent);
			
			// checking if ID already exists
			assertTrue(studentFile.idAlreadyExists(1));
			
			// testing data has been written to file and file can be read back
			assertEquals(1, studentFile.readStudentId(newStudent));
			assertEquals("Jane", studentFile.readStudentFirst(newStudent));
			assertEquals("Smith", studentFile.readStudentLast(newStudent));
			assertEquals("Jane.Smith@stu.mmu.ac.uk", studentFile.readStudentEmail(newStudent));
			assertEquals(1997, studentFile.readStudentYearOfBirth(newStudent));
			assertEquals(6, studentFile.readStudentMonthOfBirth(newStudent));
			assertEquals(17, studentFile.readStudentDayOfBirth(newStudent));
			assertEquals("DTS", studentFile.readStudentSubjectStudying(newStudent));
			assertEquals(1, studentFile.readStudentYearOfStudy(newStudent));
			assertEquals("Password.1234", studentFile.readStudentPassword(newStudent));
			assertEquals("Simple", studentFile.readStudentMemorableWord(newStudent));
			
			// checking if ID already exists
			assertTrue(studentFile.idAlreadyExists(1));
			
			// deleting student from database
			studentFile.removeStudent(newStudent.getId(), newStudent.getPassword());
			
			// checking if ID already exists 
			assertFalse(studentFile.idAlreadyExists(1));
			
		}
		catch (Exception e) {
			LOGGER.warning("Unable to add data to file");
		}
	
	}
}

package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import userData.Staff;
import manager.FileHandlerStaff;

public class FileHandlerStaffTest {
	
	private Logger LOGGER = Logger.getLogger(FileHandlerStaff.class.getName());
	
	/*****************************
	 * Creating Staff Object
	 *****************************/
	
	@Test
	@DisplayName("Testing Staff File Creating, Reading, Writing, Overwriting, Removing")
	public void testFileHandlerStaff() {
		
		Staff newStaff = new Staff();
		
		newStaff.setId(1);
		newStaff.setFirst("First");
		newStaff.setLast("Last");
		newStaff.setGenerateEmail();
		newStaff.setYearOfBirth(1997);
		newStaff.setMonthOfBirth(6);
		newStaff.setDayOfBirth(17);
		newStaff.setSubjectTaught("DTS");
		newStaff.setYearsTeaching(1);
		newStaff.setPassword("Password.1234");
		newStaff.setMemorableWord("Simple");
		
		try {
			FileHandlerStaff staffFile = new FileHandlerStaff("./src/test/resources/staffDatabaseTest.txt");
			staffFile.writeFileNewEntry(newStaff);
			System.out.println("Success! Your Staff ID is: " + newStaff.getId() + 
					"\nYour Staff Email is: " + newStaff.getEmail() +
					"\nDo not forget these details, you will need them to login.");
						
			staffFile.printStaffData(newStaff);
			
			// testing data has been written to file and file can be read back with Staff object parameter
			assertEquals(1, staffFile.readStaffId(newStaff));
			assertEquals("First", staffFile.readStaffFirst(newStaff));
			assertEquals("Last", staffFile.readStaffLast(newStaff));
			assertEquals("First.Last@mmu.ac.uk", staffFile.readStaffEmail(newStaff));
			assertEquals(1997, staffFile.readStaffYearOfBirth(newStaff));
			assertEquals(6, staffFile.readStaffMonthOfBirth(newStaff));
			assertEquals(17, staffFile.readStaffDayOfBirth(newStaff));
			assertEquals("DTS", staffFile.readStaffSubjectTaught(newStaff));
			assertEquals(1, staffFile.readStaffYearsTeaching(newStaff));
			assertEquals("Password.1234", staffFile.readStaffPassword(newStaff));
			assertEquals("Simple", staffFile.readStaffMemorableWord(newStaff));
			
			// checking if ID already exists
			assertTrue(staffFile.idAlreadyExists(1));
			
			// testing data has been written to file and file can be read back with integer ID parameter
			assertEquals(1, staffFile.readStaffId(1));
			assertEquals("First", staffFile.readStaffFirst(1));
			assertEquals("Last", staffFile.readStaffLast(1));
			assertEquals("First.Last@mmu.ac.uk", staffFile.readStaffEmail(1));
			assertEquals(1997, staffFile.readStaffYearOfBirth(1));
			assertEquals(6, staffFile.readStaffMonthOfBirth(1));
			assertEquals(17, staffFile.readStaffDayOfBirth(1));
			assertEquals("DTS", staffFile.readStaffSubjectTaught(1));
			assertEquals(1, staffFile.readStaffYearsTeaching(1));
			assertEquals("Password.1234", staffFile.readStaffPassword(1));
			assertEquals("Simple", staffFile.readStaffMemorableWord(1));
			
			// checking if ID already exists
			assertTrue(staffFile.idAlreadyExists(1));
			
			
			// overwriting data
			newStaff.setFirst("Jane");
			newStaff.setLast("Smith");
			newStaff.setGenerateEmail();
			
			staffFile.overwriteStaffData(newStaff);
			
			// checking if ID already exists
			assertTrue(staffFile.idAlreadyExists(1));
			
			// testing data has been written to file and file can be read back
			assertEquals(1, staffFile.readStaffId(newStaff));
			assertEquals("Jane", staffFile.readStaffFirst(newStaff));
			assertEquals("Smith", staffFile.readStaffLast(newStaff));
			assertEquals("Jane.Smith@mmu.ac.uk", staffFile.readStaffEmail(newStaff));
			assertEquals(1997, staffFile.readStaffYearOfBirth(newStaff));
			assertEquals(6, staffFile.readStaffMonthOfBirth(newStaff));
			assertEquals(17, staffFile.readStaffDayOfBirth(newStaff));
			assertEquals("DTS", staffFile.readStaffSubjectTaught(newStaff));
			assertEquals(1, staffFile.readStaffYearsTeaching(newStaff));
			assertEquals("Password.1234", staffFile.readStaffPassword(newStaff));
			assertEquals("Simple", staffFile.readStaffMemorableWord(newStaff));
			
			// checking if ID already exists
			assertTrue(staffFile.idAlreadyExists(1));
			
			// deleting staff from database
			staffFile.removeStaff(newStaff.getId(), newStaff.getPassword());
			
			// checking if ID already exists 
			assertFalse(staffFile.idAlreadyExists(1));
			
		}
		catch (Exception e) {
			LOGGER.warning("Unable to add data to file");
		}
	
	}
}

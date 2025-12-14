package manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

import userData.Staff;

/**
 * Important notes for this class.
 * <br>Staff data is held in String arrays, with each data point having a fixed index within the array (e.g. Id always has index = 0).
 * <br><br>- Array[0] = Id
 * <br>- Array[1] = First Name
 * <br>- Array[2] = Last Name
 * <br>- Array[3] = Email
 * <br>- Array[4] = Subject Teaching
 * <br>- Array[5] = Years Teaching
 * <br>- Array[6] = Year of Birth
 * <br>- Array[7] = Month of Birth
 * <br>- Array[8] = Day of Birth
 * <br>- Array[9] = Password
 * <br>- Array[10] = Memorable Word
 * 
 * <br><br>Another important thing to note is that while Staff objects can take varying data types for each data point 
 * (e.g. Id = integer, First Name = String), the Staff database stores all data points as Strings (in CSV format).
 * 
 * <br><br>Additionally, because the staff database file is stored in CSV format, it is imperative that staff data entries <strong>DO NOT CONTAIN</strong> commas.
 */
public class FileHandlerStaff {
	
	// Adding a logger
	private Logger LOGGER = Logger.getLogger(FileHandlerStaff.class.getName());
	
	private String filePath;
	private File dataFile;
	private ArrayList<ArrayList<String>> dataArray;
	
	//--------------------------------------
	// constructor
	//--------------------------------------
	
	/**
	 * Empty constructor for file handler
	 */
	public FileHandlerStaff() {} 
	
	/**
	 * File handler that takes the target file path as a string variable.
	 * @param filePath - String variable with full file path name, e.g. "src/main/java/resources/fileName.txt"
	 */
	public FileHandlerStaff(String filePath) throws IOException {
		this.filePath = filePath;
		this.dataFile = createFile(this.filePath);
		this.dataArray = readFiletoArray();
	}
	
	//--------------------------------------
	// methods
	//--------------------------------------
	
	/**
	 * This method creates a file from a string filePath.
	 * @param filePath - String variable with full file path name, e.g. "src/main/java/resources/fileName.txt"
	 * @return data file (File type)
	 */
	
	public File createFile(String filePath) {
		
		File newFile = null;
		
		// Attempting to create file
		try {
			newFile = new File(filePath);
			if(newFile.createNewFile()) {
				LOGGER.info("File has been created.");
			}
			else {
				// no need to log every time file is found to already exist
			}
		}
		
		catch (Exception e) {
			LOGGER.warning("Error occurred when creating file");
			e.printStackTrace();
		}
		
		return newFile;
	}
	
	
	/**
	 * This method reads data from the file and returns the data as an array of string array lists.
	 * @return
	 */
	public ArrayList<ArrayList<String>> readFiletoArray() {
		
		ArrayList<ArrayList<String>> dataArray = new ArrayList<ArrayList<String>>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
			
			// This variable will act as a buffer to store each line read by reader.readerLine()
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				// Splitting each line by , comma and adding to array
				String[] lineAsArray = line.split(",");
				
				// converting the array to an array list so an array of array lists can be built
				ArrayList<String> lineList = new ArrayList<String>(Arrays.asList(lineAsArray));
				dataArray.add(lineList);
			}
		} catch (IOException e) {
			// REPLACE WITH LOGGER IN FUTURE
			System.out.println("ERROR: Cannot read file");
		}
		return dataArray;
	}
	
	/**
	 * This method writes new data to file in csv format.
	 * @param dataFile - File type variable, this is the target file to write data to.
	 * @param staff - Staff object with the staff data to write to file.
	 */
	public void writeFileNewEntry(Staff staff) {
		int id = staff.getId();
		String first = staff.getFirst();
		String last = staff.getLast();
		String email = staff.getEmail();
		String subjectTaught = staff.getSubjectTaught();
		int yearsTeaching = staff.getYearsTeaching();
		int yearOfBirth = staff.getYearOfBirth();
		int monthOfBirth = staff.getMonthOfBirth();
		int dayOfBirth = staff.getDayOfBirth();
		String password = staff.getPassword();
		String memorableWord = staff.getMemorableWord();
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Using FileWriter(File, boolean) and setting boolean to true, so data appends to end of file.
			writer = new FileWriter(this.dataFile, true);
			
			// writing staff data in csv format
			writer.write(id + "," + first + "," + last + "," + 
					email + "," + subjectTaught + "," + yearsTeaching + "," +
					yearOfBirth + "," + monthOfBirth + "," + dayOfBirth + "," + 
					password + "," + memorableWord + "\n");
			LOGGER.info("File written succesfully");
		}
		
		catch (Exception e) {
			LOGGER.warning("File cannot be written to");
		}
		
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (Exception e) {
					LOGGER.warning("Unable to close writer");
				}
			}
		}
		
	}
	
	/**
	 * Method to overwrite existing staff data, for example, when updating staff information.
	 * @param dataFile - File type variable, this is the target file to write data to.
	 * @param staff - Staff object with the staff data to write to file.
	 */
	public void overwriteStaffData(Staff staff) {
		int id = staff.getId();
		String first = staff.getFirst();
		String last = staff.getLast();
		String email = staff.getEmail();
		String subject = staff.getSubjectTaught();
		int yearsTeaching = staff.getYearsTeaching();
		int yearOfBirth = staff.getYearOfBirth();
		int monthOfBirth = staff.getMonthOfBirth();
		int dayOfBirth = staff.getDayOfBirth();
		String password = staff.getPassword();
		String memorableWord = staff.getMemorableWord();
		
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Read all data in the file and save to array
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			writer = new FileWriter(this.dataFile);
			
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					
					dataArray.get(staffEntry).set(1, String.valueOf(first));
					dataArray.get(staffEntry).set(2, String.valueOf(last));
					dataArray.get(staffEntry).set(3, String.valueOf(email));
					dataArray.get(staffEntry).set(4, String.valueOf(subject));
					dataArray.get(staffEntry).set(5, String.valueOf(yearsTeaching));
					dataArray.get(staffEntry).set(6, String.valueOf(yearOfBirth));
					dataArray.get(staffEntry).set(7, String.valueOf(monthOfBirth));
					dataArray.get(staffEntry).set(8, String.valueOf(dayOfBirth));
					dataArray.get(staffEntry).set(9, String.valueOf(password));
					dataArray.get(staffEntry).set(10, String.valueOf(memorableWord));
				}
			
			}
			
			// finally write all data back to database file
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				writer.write(
						dataArray.get(staffEntry).get(0)+"," + 
						dataArray.get(staffEntry).get(1)+"," +
						dataArray.get(staffEntry).get(2)+"," +
						dataArray.get(staffEntry).get(3)+"," +
						dataArray.get(staffEntry).get(4)+"," +
						dataArray.get(staffEntry).get(5)+"," +
						dataArray.get(staffEntry).get(6)+"," +
						dataArray.get(staffEntry).get(7)+"," +
						dataArray.get(staffEntry).get(8)+"," +
						dataArray.get(staffEntry).get(9)+"," +
						dataArray.get(staffEntry).get(10)+"," + "\n"
						);
			}
		}
		catch (Exception e) {
			LOGGER.warning("Error overwriting staff data");
		}
		
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (Exception e) {
					LOGGER.warning("Unable to close writer");
				}
			}
		}
	}
	
	
	/**
	 * This method removes a staff from the database. This will read the file to an array of array lists (parent array), 
	 * find the relevant staff array (child array), ensure that the password provided matches the password on file and 
	 * deletes that staff from the parent array. The entire file is rewritten with the staff removed from the database.
	 * @param id - This is the id (int) of the staff you wish to delete from the database.
	 * @param password - This is the password (String) of the staff you wish to delete from the database.
	 */
	public void removeStaff(int id, String password) {
		String passwordOnFile = "";
		
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Read all data in the file and save to array
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			writer = new FileWriter(this.dataFile);
			
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					
					// check user has provided the correct password
					passwordOnFile = dataArray.get(staffEntry).get(9);
					
					// passwords match
					if (passwordOnFile.equals(password)) {
						// remove staff from data array
						dataArray.remove(staffEntry);
						// rewrite data array to file with the target staff array now removed
						for (staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
							writer.write(
									dataArray.get(staffEntry).get(0)+"," + 
									dataArray.get(staffEntry).get(1)+"," +
									dataArray.get(staffEntry).get(2)+"," +
									dataArray.get(staffEntry).get(3)+"," +
									dataArray.get(staffEntry).get(4)+"," +
									dataArray.get(staffEntry).get(5)+"," +
									dataArray.get(staffEntry).get(6)+"," +
									dataArray.get(staffEntry).get(7)+"," +
									dataArray.get(staffEntry).get(8)+"," +
									dataArray.get(staffEntry).get(9)+"," +
									dataArray.get(staffEntry).get(10)+"," + "\n"
									);
						}
						
						return;
					}
					// passwords do not match
					else {
						System.out.println("Your password is incorrect");
						return;
					}
				}
				else {
					continue;
				}
			}
			
			System.out.println("Your ID could not be found in the database");
			return;
		}
		catch (Exception e) {
			LOGGER.warning("Error overwriting staff data");
		}
		
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (Exception e) {
					LOGGER.warning("Unable to close writer");
				}
			}
		}
	}
	
	
	/**
	 * Converts between String array and Staff object.
	 */
	public Staff fileToStaffObject(int Id) {
		
		// create empty staff object
		Staff thisStaff = new Staff();
		thisStaff.setId(Id);
		
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(Id);
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					
					// Setting Staff data to the data read from file
					thisStaff.setFirst(dataArray.get(staffEntry).get(1));
					thisStaff.setLast(dataArray.get(staffEntry).get(2));
					thisStaff.setEmail(dataArray.get(staffEntry).get(3));
					thisStaff.setSubjectTaught(dataArray.get(staffEntry).get(4));
					thisStaff.setYearsTeaching(Integer.valueOf(dataArray.get(staffEntry).get(5)));
					thisStaff.setYearOfBirth(Integer.valueOf(dataArray.get(staffEntry).get(6)));
					thisStaff.setMonthOfBirth(Integer.valueOf(dataArray.get(staffEntry).get(7)));
					thisStaff.setDayOfBirth(Integer.valueOf(dataArray.get(staffEntry).get(8)));
					thisStaff.setPassword(dataArray.get(staffEntry).get(9));
					thisStaff.setMemorableWord(dataArray.get(staffEntry).get(10));
					
					// returning staff object
					LOGGER.info("Successfully created staff object with data from database file.");
					return thisStaff;
				}
				else {
					continue;
				}
			}
			
			System.out.println("Your ID does not exist in the database. Returning empty Staff Object.");
			return thisStaff;
			
		}
		catch (Exception e) {
			LOGGER.warning("Error reading staff data");
		}
		
		System.out.println("Unable to read file. Returning empty Staff object.");
		return thisStaff;
	}
	
	
	//--------------------------------------
	// Read data array methods
	//--------------------------------------
	
	/**
	 * Method to read existing staff data. This requires a staff object with an ID.
	 * @param dataFile - File type variable, this is the target file to read data from.
	 * @param staff - Staff object with the staff data to read. Required only for the ID so as to 'find' the staff in the database.
	 */
	public void printStaffData(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					System.out.println("Your ID is: "+ dataArray.get(staffEntry).get(0));
					System.out.println("Your first name is: "+ dataArray.get(staffEntry).get(1));
					System.out.println("Your last name is: "+ dataArray.get(staffEntry).get(2));
					System.out.println("Your email is: "+ dataArray.get(staffEntry).get(3));
					System.out.println("You are studying: "+ dataArray.get(staffEntry).get(4));
					System.out.println("You are in year: "+ dataArray.get(staffEntry).get(5));
					System.out.println("You were born in the year: "+ dataArray.get(staffEntry).get(6));
					System.out.println("Your month of birth is: "+ dataArray.get(staffEntry).get(7));
					System.out.println("Your day of birth is: "+ dataArray.get(staffEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database");
				}
			}

		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data");
		}
	}
	
	
	/*****************************************
	 *** READ ID
	 *****************************************/
	
	/**
	 * This method checks whether an ID already exists in the staff database. Important for ensuring IDs are unique.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's year of study (int).
	 */
	public boolean idAlreadyExists(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		boolean idAlreadyExists = false;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					idAlreadyExists = true;
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return idAlreadyExists;
	}
	
	
	/*****************************************
	 *** READ ID
	 *****************************************/
	
	/**
	 * This method returns the staff's Id. Used mainly for testing.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff's Id (int).
	 */
	public int readStaffId(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		int idOnFile = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					idOnFile = Integer.valueOf(dataArray.get(staffEntry).get(0));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return idOnFile;
	}
	
	/**
	 * This method returns the staff's year of study.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's year of study (int).
	 */
	public int readStaffId(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int idOnFile = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					idOnFile = Integer.valueOf(dataArray.get(staffEntry).get(0));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return idOnFile;
	}
	
	
	/*****************************************
	 *** READ STUDENT FIRST NAME
	 *****************************************/
	
	/**
	 * This method returns the staff first name.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff first name (String).
	 */
	public String readStaffFirst(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String first = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					first = dataArray.get(staffEntry).get(1);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return first;
	}
	
	/**
	 * This method returns the staff first name.
	 * @param id - id (int) required in order to search for id.
	 * @return - Staff first name (String).
	 */
	public String readStaffFirst(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String first = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					first = dataArray.get(staffEntry).get(1);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return first;
	}
	
	/*****************************************
	 *** READ STUDENT LAST NAME
	 *****************************************/
	
	/**
	 * This method returns the staff last name.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff last name (String).
	 */
	public String readStaffLast(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String last = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					last = dataArray.get(staffEntry).get(2);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return last;
	}
	
	/**
	 * This method returns the staff last name.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff last name (String).
	 */
	public String readStaffLast(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String last = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					last = dataArray.get(staffEntry).get(2);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return last;
	}
	
	/*****************************************
	 *** READ STUDENT EMAIL
	 *****************************************/
	
	/**
	 * This method returns the staff email.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff email (String).
	 */
	public String readStaffEmail(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String email = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					email = dataArray.get(staffEntry).get(3);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return email;
	}
	
	/**
	 * This method returns the staff email.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff email (String).
	 */
	public String readStaffEmail(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String email = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					email = dataArray.get(staffEntry).get(3);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return email;
	}
	
	/*****************************************
	 *** READ SUBJECT STUDYING
	 *****************************************/
	
	/**
	 * This method returns the subject the staff is studying.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Subject studying (String).
	 */
	public String readStaffSubjectTaught(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String subjectTaught = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					subjectTaught = dataArray.get(staffEntry).get(4);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return subjectTaught;
	}
	
	/**
	 * This method returns the subject the staff is studying.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Subject studying (String).
	 */
	public String readStaffSubjectTaught(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String subjectTaught = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					subjectTaught = dataArray.get(staffEntry).get(4);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return subjectTaught;
	}
	
	/*****************************************
	 *** READ YEAR OF STUDY
	 *****************************************/
	
	/**
	 * This method returns the staff's year of study.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff's year of study (int).
	 */
	public int readStaffYearsTeaching(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		int yearsTeaching = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					yearsTeaching = Integer.valueOf(dataArray.get(staffEntry).get(5));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return yearsTeaching;
	}
	
	/**
	 * This method returns the staff's year of study.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's year of study (int).
	 */
	public int readStaffYearsTeaching(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int yearsTeaching = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					yearsTeaching = Integer.valueOf(dataArray.get(staffEntry).get(5));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return yearsTeaching;
	}
	
	/*****************************************
	 *** READ YEAR OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the staff's year of birth.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff's year of birth (int).
	 */
	public int readStaffYearOfBirth(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		int yearOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					yearOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(6));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return yearOfBirth;
	}
	
	/**
	 * This method returns the staff's year of birth.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's year of birth (int).
	 */
	public int readStaffYearOfBirth(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int yearOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					yearOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(6));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return yearOfBirth;
	}
	
	
	/*****************************************
	 *** READ MONTH OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the staff's month of birth.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff's month of birth (int).
	 */
	public int readStaffMonthOfBirth(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		int monthOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					monthOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(7));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return monthOfBirth;
	}
	
	/**
	 * This method returns the staff's month of birth.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's month of birth (int).
	 */
	public int readStaffMonthOfBirth(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int monthOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					monthOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(7));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return monthOfBirth;
	}
	
	/*****************************************
	 *** READ DAY OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the staff's day of birth.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Staff's day of birth (int).
	 */
	public int readStaffDayOfBirth(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		int dayOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					dayOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return dayOfBirth;
	}
	
	/**
	 * This method returns the staff's day of birth.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Staff's day of birth (int).
	 */
	public int readStaffDayOfBirth(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int dayOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					dayOfBirth = Integer.valueOf(dataArray.get(staffEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning 0.");
		}
		
		return dayOfBirth;
	}
	
	/*****************************************
	 *** READ PASSWORD
	 *****************************************/
	
	/**
	 * This method returns the staff password.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Password (String).
	 */
	public String readStaffPassword(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String password = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					password = dataArray.get(staffEntry).get(9);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return password;
	}
	
	/**
	 * This method returns the staff password.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Password (String).
	 */
	public String readStaffPassword(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String password = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					password = dataArray.get(staffEntry).get(9);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return password;
	}
	
	/*****************************************
	 *** READ MEMORABLE WORD
	 *****************************************/
	
	/**
	 * This method returns the staff memorable word.
	 * @param staff - Staff object required in order to check ID.
	 * @return - Memorable word (String).
	 */
	public String readStaffMemorableWord(Staff staff) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String id = String.valueOf(staff.getId());
		
		String memorableWord = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(id)) {
					memorableWord = dataArray.get(staffEntry).get(10);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return memorableWord;
	}
	
	/**
	 * This method returns the staff memorable word.
	 * @param id - id (int) required to search for staff in database.
	 * @return - Memorable word (String).
	 */
	public String readStaffMemorableWord(int id) {
		// Staff object stores IDs as integers, staff database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String memorableWord = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through staff database array of arrays
			for (int staffEntry = 0; staffEntry < this.dataArray.size(); staffEntry++) {
				
				// Staff IDs are stored at index 0 of each child array
				if (dataArray.get(staffEntry).get(0).equals(idString)) {
					memorableWord = dataArray.get(staffEntry).get(10);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading staff data. Returning empty String.");
		}
		
		return memorableWord;
	}
	
	//--------------------------------------
	// getters
	//--------------------------------------
	
	/**
	 * Method to get file path.
	 * @return File Path (String type)
	 */
	public String getFilePath() {
		return this.filePath;
	}
	
	
	/**
	 * Method to get data file.
	 * @return Data File (File type)
	 */
	public File getDataFile() {
		return this.dataFile;
	}
	
	
	/**
	 * Method to get data array.
	 * @return Data Array (ArrayList<ArrayList<String>> type)
	 */
	public ArrayList<ArrayList<String>> getDataArray() {
		return this.dataArray;
	}
	
	//--------------------------------------
	// setters
	//--------------------------------------
	
	/**
	 * Method to set file path.
	 * @param filePath - String variable with full file path name, e.g. "src/main/java/resources/fileName.txt"
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * Method to set data file.
	 * @param dataFile - File type variable.
	 */
	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}
	
	/**
	 * Method to set data array.
	 * @param dataArray - ArrayList<ArrayList<String>> type variable.
	 */
	public void setDataArray(ArrayList<ArrayList<String>> dataArray) {
		this.dataArray = dataArray;
	}
}

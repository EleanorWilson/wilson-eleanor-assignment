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

import userData.Student;

/**
 * Important notes for this class.
 * <br>Student data is held in String arrays, with each data point having a fixed index within the array (e.g. Id always has index = 0).
 * <br><br>- Array[0] = Id
 * <br>- Array[1] = First Name
 * <br>- Array[2] = Last Name
 * <br>- Array[3] = Email
 * <br>- Array[4] = Subject Studying
 * <br>- Array[5] = Year of Study
 * <br>- Array[6] = Year of Birth
 * <br>- Array[7] = Month of Birth
 * <br>- Array[8] = Day of Birth
 * <br>- Array[9] = Password
 * <br>- Array[10] = Memorable Word
 * 
 * <br><br>Another important thing to note is that while Student objects can take varying data types for each data point 
 * (e.g. Id = integer, First Name = String), the Student database stores all data points as Strings (in CSV format).
 * 
 * <br><br>Additionally, because the student database file is stored in CSV format, it is imperative that student data entries <strong>DO NOT CONTAIN</strong> commas.
 */
public class FileHandlerStudent {
	
	// Adding a logger
	private Logger LOGGER = Logger.getLogger(FileHandlerStudent.class.getName());
	
	private String filePath;
	private File dataFile;
	private ArrayList<ArrayList<String>> dataArray;
	
	//--------------------------------------
	// constructor
	//--------------------------------------
	
	/**
	 * Empty constructor for file handler
	 */
	public FileHandlerStudent() {} 
	
	/**
	 * File handler that takes the target file path as a string variable.
	 * @param filePath - String variable with full file path name, e.g. "src/main/java/resources/fileName.txt"
	 */
	public FileHandlerStudent(String filePath) throws IOException {
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
	 * @param student - Student object with the student data to write to file.
	 */
	public void writeFileNewEntry(Student student) {
		int id = student.getId();
		String first = student.getFirst();
		String last = student.getLast();
		String email = student.getEmail();
		String subjectStudying = student.getSubjectStudying();
		int yearOfStudy = student.getYearOfStudy();
		int yearOfBirth = student.getYearOfBirth();
		int monthOfBirth = student.getMonthOfBirth();
		int dayOfBirth = student.getDayOfBirth();
		String password = student.getPassword();
		String memorableWord = student.getMemorableWord();
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Using FileWriter(File, boolean) and setting boolean to true, so data appends to end of file.
			writer = new FileWriter(this.dataFile, true);
			
			// writing student data in csv format
			writer.write(id + "," + first + "," + last + "," + 
					email + "," + subjectStudying + "," + yearOfStudy + "," +
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
	 * Method to overwrite existing student data, for example, when updating student information.
	 * @param dataFile - File type variable, this is the target file to write data to.
	 * @param student - Student object with the student data to write to file.
	 */
	public void overwriteStudentData(Student student) {
		int id = student.getId();
		String first = student.getFirst();
		String last = student.getLast();
		String email = student.getEmail();
		String subject = student.getSubjectStudying();
		int yearOfStudy = student.getYearOfStudy();
		int yearOfBirth = student.getYearOfBirth();
		int monthOfBirth = student.getMonthOfBirth();
		int dayOfBirth = student.getDayOfBirth();
		String password = student.getPassword();
		String memorableWord = student.getMemorableWord();
		
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Read all data in the file and save to array
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			writer = new FileWriter(this.dataFile);
			
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					
					dataArray.get(studentEntry).set(1, String.valueOf(first));
					dataArray.get(studentEntry).set(2, String.valueOf(last));
					dataArray.get(studentEntry).set(3, String.valueOf(email));
					dataArray.get(studentEntry).set(4, String.valueOf(subject));
					dataArray.get(studentEntry).set(5, String.valueOf(yearOfStudy));
					dataArray.get(studentEntry).set(6, String.valueOf(yearOfBirth));
					dataArray.get(studentEntry).set(7, String.valueOf(monthOfBirth));
					dataArray.get(studentEntry).set(8, String.valueOf(dayOfBirth));
					dataArray.get(studentEntry).set(9, String.valueOf(password));
					dataArray.get(studentEntry).set(10, String.valueOf(memorableWord));
				}
			
			}
			
			// finally write all data back to database file
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				writer.write(
						dataArray.get(studentEntry).get(0)+"," + 
						dataArray.get(studentEntry).get(1)+"," +
						dataArray.get(studentEntry).get(2)+"," +
						dataArray.get(studentEntry).get(3)+"," +
						dataArray.get(studentEntry).get(4)+"," +
						dataArray.get(studentEntry).get(5)+"," +
						dataArray.get(studentEntry).get(6)+"," +
						dataArray.get(studentEntry).get(7)+"," +
						dataArray.get(studentEntry).get(8)+"," +
						dataArray.get(studentEntry).get(9)+"," +
						dataArray.get(studentEntry).get(10)+"," + "\n"
						);
			}
		}
		catch (Exception e) {
			LOGGER.warning("Error overwriting student data");
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
	 * This method removes a student from the database. This will read the file to an array of array lists (parent array), 
	 * find the relevant student array (child array), ensure that the password provided matches the password on file and 
	 * deletes that student from the parent array. The entire file is rewritten with the student removed from the database.
	 * @param id - This is the id (int) of the student you wish to delete from the database.
	 * @param password - This is the password (String) of the student you wish to delete from the database.
	 */
	public void removeStudent(int id, String password) {
		String passwordOnFile = "";
		
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Read all data in the file and save to array
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			writer = new FileWriter(this.dataFile);
			
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					
					// check user has provided the correct password
					passwordOnFile = dataArray.get(studentEntry).get(9);
					
					// passwords match
					if (passwordOnFile.equals(password)) {
						// remove student from data array
						dataArray.remove(studentEntry);
						// rewrite data array to file with the target student array now removed
						for (studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
							writer.write(
									dataArray.get(studentEntry).get(0)+"," + 
									dataArray.get(studentEntry).get(1)+"," +
									dataArray.get(studentEntry).get(2)+"," +
									dataArray.get(studentEntry).get(3)+"," +
									dataArray.get(studentEntry).get(4)+"," +
									dataArray.get(studentEntry).get(5)+"," +
									dataArray.get(studentEntry).get(6)+"," +
									dataArray.get(studentEntry).get(7)+"," +
									dataArray.get(studentEntry).get(8)+"," +
									dataArray.get(studentEntry).get(9)+"," +
									dataArray.get(studentEntry).get(10)+"," + "\n"
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
			LOGGER.warning("Error overwriting student data");
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
	 * Converts between String array and Student object.
	 */
	public Student fileToStudentObject(int Id) {
		
		// create empty student object
		Student thisStudent = new Student();
		thisStudent.setId(Id);
		
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(Id);
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					
					// Setting Student data to the data read from file
					thisStudent.setFirst(dataArray.get(studentEntry).get(1));
					thisStudent.setLast(dataArray.get(studentEntry).get(2));
					thisStudent.setEmail(dataArray.get(studentEntry).get(3));
					thisStudent.setSubjectStudying(dataArray.get(studentEntry).get(4));
					thisStudent.setYearOfStudy(Integer.valueOf(dataArray.get(studentEntry).get(5)));
					thisStudent.setYearOfBirth(Integer.valueOf(dataArray.get(studentEntry).get(6)));
					thisStudent.setMonthOfBirth(Integer.valueOf(dataArray.get(studentEntry).get(7)));
					thisStudent.setDayOfBirth(Integer.valueOf(dataArray.get(studentEntry).get(8)));
					thisStudent.setPassword(dataArray.get(studentEntry).get(9));
					thisStudent.setMemorableWord(dataArray.get(studentEntry).get(10));
					
					// returning student object
					LOGGER.info("Successfully created student object with data from database file.");
					return thisStudent;
				}
				else {
					continue;
				}
			}
			
			System.out.println("Your ID does not exist in the database. Returning empty Student Object.");
			return thisStudent;
			
		}
		catch (Exception e) {
			LOGGER.warning("Error reading student data");
		}
		
		System.out.println("Unable to read file. Returning empty Student object.");
		return thisStudent;
	}
	
	
	//--------------------------------------
	// Read data array methods
	//--------------------------------------
	
	/**
	 * Method to read existing student data. This requires a student object with an ID.
	 * @param dataFile - File type variable, this is the target file to read data from.
	 * @param student - Student object with the student data to read. Required only for the ID so as to 'find' the student in the database.
	 */
	public void printStudentData(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					System.out.println("Your ID is: "+ dataArray.get(studentEntry).get(0));
					System.out.println("Your first name is: "+ dataArray.get(studentEntry).get(1));
					System.out.println("Your last name is: "+ dataArray.get(studentEntry).get(2));
					System.out.println("Your email is: "+ dataArray.get(studentEntry).get(3));
					System.out.println("You are studying: "+ dataArray.get(studentEntry).get(4));
					System.out.println("You are in year: "+ dataArray.get(studentEntry).get(5));
					System.out.println("You were born in the year: "+ dataArray.get(studentEntry).get(6));
					System.out.println("Your month of birth is: "+ dataArray.get(studentEntry).get(7));
					System.out.println("Your day of birth is: "+ dataArray.get(studentEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database");
				}
			}

		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data");
		}
	}
	
	
	/*****************************************
	 *** READ ID
	 *****************************************/
	
	/**
	 * This method checks whether an ID already exists in the student database. Important for ensuring IDs are unique.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's year of study (int).
	 */
	public boolean idAlreadyExists(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		boolean idAlreadyExists = false;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					idAlreadyExists = true;
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return idAlreadyExists;
	}
	
	
	/*****************************************
	 *** READ ID
	 *****************************************/
	
	/**
	 * This method returns the student's Id. Used mainly for testing.
	 * @param student - Student object required in order to check ID.
	 * @return - Student's Id (int).
	 */
	public int readStudentId(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		int idOnFile = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					idOnFile = Integer.valueOf(dataArray.get(studentEntry).get(0));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return idOnFile;
	}
	
	/**
	 * This method returns the student's year of study.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's year of study (int).
	 */
	public int readStudentId(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int idOnFile = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					idOnFile = Integer.valueOf(dataArray.get(studentEntry).get(0));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return idOnFile;
	}
	
	
	/*****************************************
	 *** READ STUDENT FIRST NAME
	 *****************************************/
	
	/**
	 * This method returns the student first name.
	 * @param student - Student object required in order to check ID.
	 * @return - Student first name (String).
	 */
	public String readStudentFirst(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String first = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					first = dataArray.get(studentEntry).get(1);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return first;
	}
	
	/**
	 * This method returns the student first name.
	 * @param id - id (int) required in order to search for id.
	 * @return - Student first name (String).
	 */
	public String readStudentFirst(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String first = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					first = dataArray.get(studentEntry).get(1);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return first;
	}
	
	/*****************************************
	 *** READ STUDENT LAST NAME
	 *****************************************/
	
	/**
	 * This method returns the student last name.
	 * @param student - Student object required in order to check ID.
	 * @return - Student last name (String).
	 */
	public String readStudentLast(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String last = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					last = dataArray.get(studentEntry).get(2);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return last;
	}
	
	/**
	 * This method returns the student last name.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student last name (String).
	 */
	public String readStudentLast(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String last = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					last = dataArray.get(studentEntry).get(2);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return last;
	}
	
	/*****************************************
	 *** READ STUDENT EMAIL
	 *****************************************/
	
	/**
	 * This method returns the student email.
	 * @param student - Student object required in order to check ID.
	 * @return - Student email (String).
	 */
	public String readStudentEmail(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String email = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					email = dataArray.get(studentEntry).get(3);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return email;
	}
	
	/**
	 * This method returns the student email.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student email (String).
	 */
	public String readStudentEmail(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String email = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					email = dataArray.get(studentEntry).get(3);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return email;
	}
	
	/*****************************************
	 *** READ SUBJECT STUDYING
	 *****************************************/
	
	/**
	 * This method returns the subject the student is studying.
	 * @param student - Student object required in order to check ID.
	 * @return - Subject studying (String).
	 */
	public String readStudentSubjectStudying(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String subjectStudying = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					subjectStudying = dataArray.get(studentEntry).get(4);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return subjectStudying;
	}
	
	/**
	 * This method returns the subject the student is studying.
	 * @param id - id (int) required to search for student in database.
	 * @return - Subject studying (String).
	 */
	public String readStudentSubjectStudying(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String subjectStudying = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					subjectStudying = dataArray.get(studentEntry).get(4);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return subjectStudying;
	}
	
	/*****************************************
	 *** READ YEAR OF STUDY
	 *****************************************/
	
	/**
	 * This method returns the student's year of study.
	 * @param student - Student object required in order to check ID.
	 * @return - Student's year of study (int).
	 */
	public int readStudentYearOfStudy(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		int yearOfStudy = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					yearOfStudy = Integer.valueOf(dataArray.get(studentEntry).get(5));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return yearOfStudy;
	}
	
	/**
	 * This method returns the student's year of study.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's year of study (int).
	 */
	public int readStudentYearOfStudy(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int yearOfStudy = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					yearOfStudy = Integer.valueOf(dataArray.get(studentEntry).get(5));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return yearOfStudy;
	}
	
	/*****************************************
	 *** READ YEAR OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the student's year of birth.
	 * @param student - Student object required in order to check ID.
	 * @return - Student's year of birth (int).
	 */
	public int readStudentYearOfBirth(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		int yearOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					yearOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(6));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return yearOfBirth;
	}
	
	/**
	 * This method returns the student's year of birth.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's year of birth (int).
	 */
	public int readStudentYearOfBirth(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int yearOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					yearOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(6));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return yearOfBirth;
	}
	
	
	/*****************************************
	 *** READ MONTH OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the student's month of birth.
	 * @param student - Student object required in order to check ID.
	 * @return - Student's month of birth (int).
	 */
	public int readStudentMonthOfBirth(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		int monthOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					monthOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(7));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return monthOfBirth;
	}
	
	/**
	 * This method returns the student's month of birth.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's month of birth (int).
	 */
	public int readStudentMonthOfBirth(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int monthOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					monthOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(7));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return monthOfBirth;
	}
	
	/*****************************************
	 *** READ DAY OF BIRTH
	 *****************************************/
	
	/**
	 * This method returns the student's day of birth.
	 * @param student - Student object required in order to check ID.
	 * @return - Student's day of birth (int).
	 */
	public int readStudentDayOfBirth(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		int dayOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					// Converting from String (as stored in array) to integer
					dayOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return dayOfBirth;
	}
	
	/**
	 * This method returns the student's day of birth.
	 * @param id - id (int) required to search for student in database.
	 * @return - Student's day of birth (int).
	 */
	public int readStudentDayOfBirth(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		int dayOfBirth = 0;
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					// Converting from String (as stored in array) to integer
					dayOfBirth = Integer.valueOf(dataArray.get(studentEntry).get(8));
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning 0.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning 0.");
		}
		
		return dayOfBirth;
	}
	
	/*****************************************
	 *** READ PASSWORD
	 *****************************************/
	
	/**
	 * This method returns the student password.
	 * @param student - Student object required in order to check ID.
	 * @return - Password (String).
	 */
	public String readStudentPassword(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String password = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					password = dataArray.get(studentEntry).get(9);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return password;
	}
	
	/**
	 * This method returns the student password.
	 * @param id - id (int) required to search for student in database.
	 * @return - Password (String).
	 */
	public String readStudentPassword(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String password = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					password = dataArray.get(studentEntry).get(9);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return password;
	}
	
	/*****************************************
	 *** READ MEMORABLE WORD
	 *****************************************/
	
	/**
	 * This method returns the student memorable word.
	 * @param student - Student object required in order to check ID.
	 * @return - Memorable word (String).
	 */
	public String readStudentMemorableWord(Student student) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String id = String.valueOf(student.getId());
		
		String memorableWord = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(id)) {
					memorableWord = dataArray.get(studentEntry).get(10);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
		}
		
		return memorableWord;
	}
	
	/**
	 * This method returns the student memorable word.
	 * @param id - id (int) required to search for student in database.
	 * @return - Memorable word (String).
	 */
	public String readStudentMemorableWord(int id) {
		// Student object stores IDs as integers, student database stores all data points as Strings. Must convert.
		String idString = String.valueOf(id);
		
		String memorableWord = "";
		
		try {
			// initialising dataArray
			this.dataFile = createFile(this.filePath);
			this.dataArray = readFiletoArray();
			
			// Looping through student database array of arrays
			for (int studentEntry = 0; studentEntry < this.dataArray.size(); studentEntry++) {
				
				// Student IDs are stored at index 0 of each child array
				if (dataArray.get(studentEntry).get(0).equals(idString)) {
					memorableWord = dataArray.get(studentEntry).get(10);
				}
				else {
					System.out.println("Your ID does not exist in the database. Returning empty string.");
				}
			}
			
		}
			
		catch (Exception e) {
			LOGGER.warning("Error reading student data. Returning empty String.");
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

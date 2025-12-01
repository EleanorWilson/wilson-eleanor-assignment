package userData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;



/**
 * @Database
 * Information about the Constructors and methods of FileReader class:
 * FileReader(File file) creates a new FileReader with the given File to read.
 * FileReader(String filename) creates a new FileReader with given filename to read.
 * read() reads a single character and returns it, or -1 if the end of stream reached.
 * 
 * BufferedReader allows you to read files line by line.
 */

public class Database {
	
	private ArrayList<ArrayList<String>> studentDatabaseArray;
	
	// variable to store file path for student dataset
	private String filePath;
	
	// variable to store file for dataset
	private File studentDatabaseFile;
	
	//--------------------------------------
	// constructor
	//--------------------------------------
	
	
	/** 
	 * @Database
	 * Constructor for the database of the student data array list of array lists from the student database file.
	 */
	
	public Database() {
		// creating a file variable with student database txt file, must use src/ path
		setFilePath(this.filePath);
		setStudentDatabaseFile(new File(filePath));
		
		// calling method to turn data in studentData into list of lists
		ArrayList<ArrayList<String>> studentDatabaseArray = getStudentDataFromFile(studentDatabaseFile);
			
		/* Accessing data in list of lists
		 * Student = Child list (each student list with all their data points)
		 * Datum = data in student list
		 */
				
		this.studentDatabaseArray = studentDatabaseArray;
	}
	
	//--------------------------------------
	// methods
	//--------------------------------------
	
	/**
	 * @getStudentDataFromFile
	 * Method to extract student data from file into a array list of array lists.
	 * Each element in parent array list represents a student and contains an array list.
	 * Each element in child array list represents data for that student, e.g. ID, firstname, etc.
	 * Used for getStudentDatabase method.
	 */
	public ArrayList<ArrayList<String>> getStudentDataFromFile(File filePath) {
		
		// creating an empty studentData array list of array lists
		ArrayList<ArrayList<String>> studentData = new ArrayList<ArrayList<String>>();
		
		// initialising a file reader & buffered reader to read the contents of the file
		// will throw exception if file not found
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			
			// the variable line will be set = to br.readLine()
			String line;
			
			while ((line = br.readLine()) != null) {
				
				// Splitting each line by , comma and adding to array
				String[] lineAsArray = line.split(",");
				
				// converting the array to an array list 
				// (so it can be added to studentData array list of array lists)
				ArrayList<String> lineList = new ArrayList<String>(Arrays.asList(lineAsArray));
				studentData.add(lineList);
			}
		} catch (IOException e) {
			// REPLACE WITH LOGGER IN FUTURE
			System.out.println("ERROR: Cannot read file");
		}
		return studentData;		
	}

	//--------------------------------------
	// getters
	//--------------------------------------
	
	/**
	 * Method to return file path.
	 * @return File path (String)
	 */
	
	public String getFilePath() {
		return this.filePath;	
	}
	
	/**
	 * Method to return StudentDatabase File
	 * @return Student Database File (File type)
	 */
	
	public File getStudentDatabaseFile() {
		return this.studentDatabaseFile;
	}
	
	/**
	 * Method to get student database array.
	 * @return Student Database Array (ArrayList<ArrayList<String>> type)
	 */
	public ArrayList<ArrayList<String>> getStudentDatabaseArray() {
		return this.studentDatabaseArray;
	}
	
	//--------------------------------------
	// setters
	//--------------------------------------
	
	/**
	 * This setter is mainly to allow for testing as the file path of the student database is unlikely to change.
	 * @param filePath
	 * This parameter should be the full file path of the student database txt/csv file.
	 * <br>E.g. "src/userData/studentdatabase.txt".
	 */
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * This setter allows student Database File to be set, using File.
	 * @param studentDatabaseFile (File type)
	 */
	
	public void setStudentDatabaseFile(File studentDatabaseFile) {
		this.studentDatabaseFile = studentDatabaseFile;
	}
	
	/**
	 * This setter allows studentDatabaseArray to be set.
	 * @param studentDatabaseArray (ArrayList<ArrayList<String>> type)
	 */
	public void setStudentDatabaseArray(ArrayList<ArrayList<String>> studentDatabaseArray) {
		this.studentDatabaseArray = studentDatabaseArray;
	}
	
} // class

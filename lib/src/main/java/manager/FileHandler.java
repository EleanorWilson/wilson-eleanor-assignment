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

public class FileHandler {
	
	// Adding a logger
	private Logger LOGGER = Logger.getLogger(FileHandler.class.getName());
	
	private String filePath;
	private File dataFile;
	private ArrayList<ArrayList<String>> dataArray;
	
	//--------------------------------------
	// constructor
	//--------------------------------------
	
	/**
	 * Empty constructor for file handler
	 */
	public FileHandler() {} 
	
	/**
	 * File handler that takes the target file path as a string variable.
	 * @param filePath - String variable with full file path name, e.g. "src/main/java/resources/fileName.txt"
	 */
	public FileHandler(String filePath) {
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
				System.out.println("File already exists");
			}
		}
		
		catch (IOException e) {
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
		String subject = student.getSubject();
		int yearOfStudy = student.getYearOfStudy();
		Calendar dob = student.getDob();
		String password = student.getPassword();
		String memorableWord = student.getMemorableWord();
		
		// Initialising a new writer
		FileWriter writer = null;
		
		try {
			// Using FileWriter(File, boolean) and setting boolean to true, so data appends to end of file.
			writer = new FileWriter(this.dataFile, true);
			
			// writing student data in csv format
			writer.write(id + "," + first + "," + last + "," + 
					email + "," + subject + "," + yearOfStudy + "," +
					dob + "," + password + "," + memorableWord);
			LOGGER.info("File written succesfully");
		}
		
		catch (IOException e) {
			LOGGER.warning("File cannot be written to");
		}
		
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
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
		String subject = student.getSubject();
		int yearOfStudy = student.getYearOfStudy();
		Calendar dob = student.getDob();
		String password = student.getPassword();
		String memorableWord = student.getMemorableWord();
		
		try {
			// checks whether the data array has been created
			if (this.dataArray == null) {
				this.dataFile = createFile(this.filePath);
				this.dataArray = readFiletoArray();
			}
			
			// find student in array by matching student object id with ids in student list
			
			for (int eachStudent = 0; eachStudent < this.dataArray.size(); eachStudent++) {
				// get studentID - 'column 1'
				if (dataArray.get(eachStudent).get(0) == String.valueOf(id)) {
					
				}
				System.out.println();
			}
			
			/*
			
			for (int eachStudent = 0; eachStudent < this.dataArray.size(); eachStudent++) {
				for (int datum = 0; datum < this.dataArray.get(eachStudent).size(); datum++) {
					System.out.println(dataArray.get(eachStudent).get(datum)+ " ");
				}
				System.out.println();
			}
			*/
		}
		catch (IOException e) {
			
		}
	}
	
	
	public void removeStudent(File dataFile, Student student) {
		
	}
	
	public void readFile() {
		
	}
	
	//--------------------------------------
	// Read data array methods
	//--------------------------------------
	
	/* Accessing first element of every list
	 * for (int student = 0; student < studentData.size(); student++) {
	 * }
	 * 	System.out.println();
	 * }
	 * for (int student = 0; student < studentData.size(); student++) {
			for (int datum = 0; datum < studentData.get(student).size(); datum++) {
				System.out.println(studentData.get(student).get(datum)+ " ");
			}
			System.out.println();
		}
	 */
	
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

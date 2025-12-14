package userData;

import java.util.Calendar;
import java.util.logging.Logger;

public class Staff extends Person{
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(Staff.class.getName());
	
	private String email;
	private String subjectTaught;
	private int yearsTeaching;
	
	//--------------------------------------
	// constructors
	//--------------------------------------
	
	/**
	 * This is an empty constructor for student objects.
	 */
	public Staff() { }
	
	/**
	 * This constructor creates a Staff object from which the methods in the Staff class can be called.
	 * @param id - Id must be in integer form, can be generated with the generateId method of the parent class (Person).
	 * @param first - This is the first name of the student, must be a string. From the parent class (Person).
	 * @param last - This is the last name of the staff member, must be a string. From the parent class (Person).
	 * @param email - This is the email address of the staff member in the form first.last@mmu.ac.uk. Can be generated with the generateEmail method of this class.
	 * @param subjectTaught - This is the subject the staff member is currently teaching, must be a string.
	 * @param yearsTeaching - This is the number of years the staff member has taught at the university. Must be an int.
	 * @param yearOfBirth - This is the staff member's year of birth (dob), must be of int data type. From the parent class (Person).
	 * @param monthOfBirth - This is the staff member's month of birth (dob), must be of int data type. From the parent class (Person).
	 * @param dayOfBirth - This is the staff member's day of birth (dob), must be of int data type. From the parent class (Person).
	 * @param password - This is the person's password for 'logging in' to their 'account'. From the parent class (Person).
	 * @param memorableWord - This is used to help the person if they have 'forgotten their password'. From the parent class (Person).
	 */
	public Staff(int id, String first, String last, String email, String subjectTaught, int yearsTeaching, int yearOfBirth, int monthOfBirth, int dayOfBirth, String password, String memorableWord) {
		super(id, first, last, yearOfBirth, monthOfBirth, dayOfBirth, password, memorableWord);
		setGenerateEmail();
		this.subjectTaught = subjectTaught;
		this.yearsTeaching = yearsTeaching;
	}
	
	
	//--------------------------------------
	// methods
	//--------------------------------------	
	
	/** 
	 * This method generates an email from the first & last name of the student in the form first.last@mmu.ac.uk
	 * @return - This returns a String.
	 */
	public String generateEmail() {
		String email = super.getFirst() + "."+ super.getLast() + "@mmu.ac.uk";
		return email;
	}
	
	/**
	 * Set the generated email as the staff member's email.
	 */
	public void setGenerateEmail() {
		this.email = generateEmail();
	}
	
	//--------------------------------------
	// getters
	//--------------------------------------
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSubjectTaught() {
		return this.subjectTaught;
	}
	
	public int getYearsTeaching() {
		return this.yearsTeaching;
	}
	
	//--------------------------------------
	// setters
	//--------------------------------------
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSubjectTaught(String subjectTaught) {
		this.subjectTaught = subjectTaught;
	}
	
	public void setYearsTeaching(int yearsTeaching) {
		this.yearsTeaching = yearsTeaching;
	}
	
}



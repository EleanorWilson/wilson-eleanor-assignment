package userData;

import java.util.Calendar;
import java.util.logging.Logger;

public class Student extends Person{
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(Student.class.getName());
	
	private String email;
	private String subjectStudying;
	private int yearOfStudy;
	
	//--------------------------------------
	// constructors
	//--------------------------------------
	
	/**
	 * This is an empty constructor for student objects.
	 */
	public Student() { }
	
	/**
	 * This constructor creates a Student object from which the methods in the Student class can be called.
	 * @param id - Id must be in integer form, can be generated with the generateId method of the parent class (Person).
	 * @param first - This is the first name of the student, must be a string. From the parent class (Person).
	 * @param last - This is the last name of the Student, must be a string. From the parent class (Person).
	 * @param email - This is the email address of the Student in the form first.last@stu.mmu.ac.uk. Can be generated with the generateEmail method of this class.
	 * @param subjectTaught - This is the subject the Student is currently teaching, must be a string.
	 * @param yearsTeaching - This is the number of years the Student has taught at the university. Must be an int.
	 * @param yearOfBirth - This is the Student's year of birth (dob), must be of int data type. From the parent class (Person).
	 * @param monthOfBirth - This is the Student's month of birth (dob), must be of int data type. From the parent class (Person).
	 * @param dayOfBirth - This is the Student's day of birth (dob), must be of int data type. From the parent class (Person).
	 * @param password - This is the person's password for 'logging in' to their 'account'. From the parent class (Person).
	 * @param memorableWord - This is used to help the person if they have 'forgotten their password'. From the parent class (Person).
	 */
	public Student(int id, String first, String last, String email, String subjectStudying, int yearOfStudy, int yearOfBirth, int monthOfBirth, int dayOfBirth, String password, String memorableWord) {
		super(id, first, last, yearOfBirth, monthOfBirth, dayOfBirth, password, memorableWord);
		setGenerateEmail();
		this.subjectStudying = subjectStudying;
		this.yearOfStudy = yearOfStudy;
	}
	
	
	//--------------------------------------
	// methods
	//--------------------------------------	
	
	/** 
	 * This method generates an email from the first & last name of the student in the form first.last@stu.mmu.ac.uk
	 * @return - This returns a String.
	 */
	public String generateEmail() {
		String email = super.getFirst() + "."+ super.getLast() + "@stu.mmu.ac.uk";
		return email;
	}
	
	/**
	 * Set the generated email as the Student's email.
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
	
	public String getSubjectStudying() {
		return this.subjectStudying;
	}
	
	public int getYearOfStudy() {
		return this.yearOfStudy;
	}
	
	//--------------------------------------
	// setters
	//--------------------------------------
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSubjectStudying(String subjectStudying) {
		this.subjectStudying = subjectStudying;
	}
	
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
}
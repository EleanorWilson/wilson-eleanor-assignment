package userData;
import java.time.Year;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.*;



/**
 * {@summary} This class is used to generate student objects and parameters for the student class. It contains getters and setters for student objects.
 */
public class Person {
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(Person.class.getName());
	
	private int id;
	private String first;
	private String last;
	private int yearOfBirth;
	private int monthOfBirth;
	private int dayOfBirth;
	private Calendar dob;
	private String password;
	private String memorableWord;
	
	//--------------------------------------
	// constructors
	//--------------------------------------
	
	/**
	 * This is an empty constructor for student objects.
	 */
	public Person() {} // empty constructor
	
	
	/**
	 * This constructor creates a Person object from which the methods in the Person class can be called.
	 * @param id - Id must be in integer form, can be generated with the generateId method of this class.
	 * @param first - This is the first name of the person, must be a string.
	 * @param last - This is the last name of the person, must be a string.
	 * @param yearOfBirth - This is the person's year of birth (dob), must be of int data type.
	 * @param monthOfBirth - This is the person's month of birth (dob), must be of int data type.
	 * @param dayOfBirth - This is the person's day of birth (dob), must be of int data type.
	 * @param password - This is the person's password for 'logging in' to their 'account'.
	 * @param memorableWord - This is used to help the person if they have 'forgotten their password'.
	 */
	public Person(int id, String first, String last, int yearOfBirth, int monthOfBirth, int dayOfBirth, String password, String memorableWord) {
		this.id = id;
		this.first = first;
		this.last = last;
		this.yearOfBirth = yearOfBirth;
		this.monthOfBirth = monthOfBirth;
		this.dayOfBirth = dayOfBirth;
		this.password = password;
		this.memorableWord = memorableWord;
	}
	
	
	//--------------------------------------
	// methods
	//--------------------------------------	
		
	/**
	 * This method returns a random number between 1 and 250 (inclusive)
	 * @return
	 * Returns an integer between 1 and 250 (inclusive) 
	 */
	public int generateId() {
		// range of random number generated = 1 - 250
		int id = (int)(Math.random()*251+1);
		return id;
	}
	
	// set the id variable to the generated Id value
	public void setGenerateId() {
		this.id = generateId();
	}
	
	/**
	 * This method converts three integer values (representing year, month and day, respectively) into a calendar data type.
	 * @param year - Integer value representing year of birth.
	 * @param month - Integer value representing month of birth.
	 * @param day - Integer value representing day of birth.
	 * @return - Returns a calendar data type.
	 */
	public Calendar generateDob(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
		Calendar dob = Calendar.getInstance();
		// Months in calendar data type begin at 0, therefore the month integer must be reduced by 1.
		try {
			dob.set(yearOfBirth, monthOfBirth-1, dayOfBirth);
		}
		catch (Exception e) {
			LOGGER.info("Unable to resolve integer inputs to date");
		}
		return dob;
	}
	
	/**
	 * This method calls the @generateDob method to generate a calendar data type from three integers and sets the DOB variable to the generated calendar date.
	 * @param year - Integer value representing year of birth.
	 * @param month - Integer value representing month of birth.
	 * @param day - Integer value representing day of birth.
	 */
	public void setGenerateDob(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
		this.dob = generateDob(yearOfBirth, monthOfBirth, dayOfBirth);
	}
	
	//--------------------------------------
	// getters
	//--------------------------------------
	
	public int getId() {
		return this.id;
	}
	
	public String getFirst() {
		return this.first;
	}
	
	public String getLast() {
		return this.last;
	}
	
	public int getYearOfBirth() {
		return this.yearOfBirth;
	}
	
	public int getMonthOfBirth() {
		return this.monthOfBirth;
	}
	
	public int getDayOfBirth() {
		return this.dayOfBirth;
	}
	
	public Calendar getDob() {
		return this.dob;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getMemorableWord() {
		return this.memorableWord;
	}
	
	//--------------------------------------
	// setters
	//--------------------------------------
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirst(String first) {
		this.first = first;
	}
	
	public void setLast(String last) {
		this.last = last;
	}
	
	
	/**
	 * This method will assign the integer input as the person's year of birth, only if they are aged between 18 and 100.
	 * @param yearOfBirth (int) - Only allows years between 18 years before today's year and 100 years before today's year.
	 */
	public void setYearOfBirth(int yearOfBirth) {
		int yearToday = Year.now().getValue();
		try {
			if (yearOfBirth <= yearToday-18 && yearOfBirth >= yearToday-100) {
				this.yearOfBirth = yearOfBirth;
			}
			
			else {
				System.out.println("Invalid year given");
			}
		}
		catch (Exception e) {
			LOGGER.info("Invalid input");
		}
	}
	
	/**
	 * This method will assign the integer input as the person's month of birth, only if the input is between 1 and 12 (inclusive).
	 * @param monthOfBirth (int) - Only allows integer values between 1 and 12.
	 */
	public void setMonthOfBirth(int monthOfBirth) {
		try {
			if (monthOfBirth <= 12 && monthOfBirth >= 1) {
				this.monthOfBirth = monthOfBirth;
			}
			
			else {
				System.out.println("Invalid month given");
			}
		}
		catch (Exception e) {
			LOGGER.info("Invalid input");
		}
	}
	
	/**
	 * This method will assign the integer input as the person's day of birth, only if the input is between 1 and 31 (inclusive). 
	 * This is a very simple method that does not check whether the month can contain that many days, e.g. it would still be 
	 * 'valid' for February to have 31 days.
	 * @param dayOfBirth (int) - Only allows integer values between 1 and 31.
	 */
	public void setDayOfBirth(int dayOfBirth) {
		try {
			if (dayOfBirth <= 31 && dayOfBirth >= 1) {
				this.dayOfBirth = dayOfBirth;
			}
			
			else {
				System.out.println("Invalid day given");
			}
		}
		catch (Exception e) {
			LOGGER.info("Invalid input");
		}
	}
	
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	public void setDob(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
		this.dob = generateDob(yearOfBirth, monthOfBirth, dayOfBirth);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMemorableWord(String memorableWord) {
		this.memorableWord = memorableWord;
	}
	
}

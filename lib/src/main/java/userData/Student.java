package userData;
import java.time.LocalDate;
import java.util.Calendar;


/**@Student
 * {@summary} This class is used to generate student objects and parameters for the student class. It contains getters and setters for student objects.
 */
public class Student {
	private int id;
	private String first;
	private String last;
	private String email;
	private String subject;
	private int yearOfStudy;
	private Calendar dob;
	private String password;
	private String memorableWord;
	
	//--------------------------------------
	// constructors
	//--------------------------------------
	
	/**@Student
	 * This is an empty constructor for student objects.
	 */
	public Student() {} // empty constructor
	
	
	/**@Student
	 * This constructor creates a Student object from which the methods in the Student class can be called.
	 * @param id - Id must be in integer form, can be generated with the generateId method of this class.
	 * @param first - This is the first name of the student, must be a string.
	 * @param last - This is the last name of the student, must be a string.
	 * @param email - This is the email address of the student in the form first.last@stu.mmu.ac.uk. Can be generated with the generateEmail method of this class.
	 * @param subject - This is the subject the student is currently studying, must be a string.
	 * @param yearOfStudy - This is the student's current year of study, must be an int.
	 * @param dob - This is the student's date of birth (dob), must be of Calendar data type. This can be generated using Calendar constructor, with three integers for the day, month and year of birth.
	 * @param password - This is the student's password for 'logging in' to their 'account'.
	 * @param memorableWord - This is used to help the student if they have 'forgotten their password'
	 */
	public Student(int id, String first, String last, String email, String subject, int yearOfStudy, Calendar dob, String password, String memorableWord) {
		this.id = id;
		this.first = first;
		this.last = last;
		this.email = email;
		this.subject = subject;
		this.yearOfStudy = yearOfStudy;
		this.dob = dob;
		this.password = password;
		this.memorableWord = memorableWord;
	}
	
	
	//--------------------------------------
	// constructors
	//--------------------------------------	
		
	// generateId
	public int generateId() {
		// range = 1 - 250 (min inclusive, max exclusive). For the maximum number of students that can be held
		int id = (int)(Math.random()*251+1);
		return id;
	}
	
	// set the id to the generated Id value
	public void setGenerateId() {
		this.id = generateId();
	}
	
	// generateEmail
	public String generateEmail() {
		String email = this.first + "."+ this.last + "@stu.mmu.ac.uk";
		return email;
	}
	
	// set the email to the generated email value
	public void setGenerateEmail() {
		this.email = generateEmail();
	}
	
	/**
	 * @generateDob
	 * This method converts three integer values, representing year, month and day, respectively, into a calendar data type.
	 * @param year - Integer value representing year of birth.
	 * @param month - Integer value representing month of birth.
	 * @param day - Integer value representing day of birth.
	 * @return - Returns a calendar data type.
	 */
	public Calendar generateDob(int year, int month, int day) {
		Calendar dob = Calendar.getInstance();
		// Months in calendar data type begin at 0, therefore the month integer must be reduced by 1.
		dob.set(year, month-1, day);
		return dob;
	}
	
	/**
	 * @setGenerateDob
	 * This method uses the @generateDob method to generate a calendar data type from three integers and sets the DOB variable to the generated calendar date.
	 * @param year - Integer value representing year of birth.
	 * @param month - Integer value representing month of birth.
	 * @param day - Integer value representing day of birth.
	 */
	public void setGenerateDob(int year, int month, int day) {
		this.dob = generateDob(year, month, day);
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
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public int getYearOfStudy() {
		return this.yearOfStudy;
	}
	
	public Calendar getDob() {
		return this.dob;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String memorableWord() {
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSuject(String subject) {
		this.subject = subject;
	}
	
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMemorableWord(String memorableWord) {
		this.memorableWord = memorableWord;
	}
	
}

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
	
	// constructors
	
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
	 * @param
	 */
	public Student(int id, String first, String last, String email, String subject, int yearOfStudy, Calendar dob, String password) {
		
	}
	
	
	// generateId
	
	// generateEmail
	
	// getters
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
	
	// setters
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
	
}

package application;

import java.io.IOException;
import java.time.Year;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

import manager.PasswordValidator;
import userData.Staff;
import manager.EntryValidator;
import manager.FileHandlerStaff;

public class SignUpStaff {
	
	public static void main(String[] args) throws IOException {
		// tests
	}
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(SignUpStaff.class.getName());
	
	// Adding a new scanner
	private static Scanner keyboard = new Scanner(System.in);
	

	/**
	 * This method takes inputs from the user (first name, last name, etc) and creates a Staff object based on these inputs.
	 * @throws IOException - if fileHandler path not valid
	 */
	public static void signUpStaff() throws IOException {
		System.out.println("Welcome new user!");
		keyboard = new Scanner(System.in);
		
		// Declaring variables
		String first;
		String last;
		int yearOfBirth;
		int monthOfBirth;
		int dayOfBirth;
		String SubjectTaught;
		int YearsTeaching;
		
		// Getting inputs from user
		
		/*****************************
		 * First Name
		 *****************************/
		
		StaffFirstInputLoop:
		while (true) {
			first = inputFirst(keyboard);
			if (EntryValidator.entryValidator(first)) {
				break StaffFirstInputLoop;
			}
			else {
				System.out.println("You have provided an invalid entry, try again");
			}
		}
		
		
		/*****************************
		 * Last Name
		 *****************************/
		
		StaffLastInputLoop:
		while (true) {
			last = inputLast(keyboard);
			if (EntryValidator.entryValidator(last)) {
				break StaffLastInputLoop;
			}
			else {
				System.out.println("You have provided an invalid entry, try again");
			}			
		}
		
		/*****************************
		 * Date of Birth
		 *****************************/
		
		yearOfBirth = inputYearOfBirth(keyboard);
		monthOfBirth = inputMonthOfBirth(keyboard);
		dayOfBirth = inputDayOfBirth(keyboard);
		
		
		/*****************************
		 * University Teaching Details
		 *****************************/
		
		StaffSubjectInputLoop:
		while (true) {
			SubjectTaught = inputSubjectTaught(keyboard);
			if (EntryValidator.entryValidator(SubjectTaught)) {
				break StaffSubjectInputLoop;
			}
			else {
				System.out.println("You have provided an invalid entry, try again");
			}
		}
		
		YearsTeaching = inputYearsTeaching(keyboard);
		
		/*****************************
		 * User Edit Data
		 *****************************/
		
		// this while loop allows users to see what they have entered and edit their details as needed
		editDetails:
		while (true) {
			
			System.out.println("Let's check your details are correct: ");
		
			// Hash Map to Hold Staff Details
			HashMap<Integer, String> signUpDetails = new HashMap<Integer, String>();
			signUpDetails.put(1, "First Name: "+first);
			signUpDetails.put(2, "Last Name: "+last);
			signUpDetails.put(3, "Birth Year: "+yearOfBirth);
			signUpDetails.put(4, "Birth Month: "+monthOfBirth);
			signUpDetails.put(5, "Birth Day: "+dayOfBirth);
			signUpDetails.put(6, "Subject of Study: "+SubjectTaught);
			signUpDetails.put(7, "Year of Study: "+YearsTeaching);
			
			// Ask user to pick menu option
			for (Integer i : signUpDetails.keySet()) {
				System.out.println(i+". "+signUpDetails.get(i));
			}
			System.out.println("If any of these details are incorrect, please enter the number you wish to change. If everything is correct, please enter '0'.\n");
			
			
			// Handling invalid options
			handlingInput:
			while (true) {
				try {
					int userChoice = Integer.valueOf(keyboard.nextLine());
					
					switch (userChoice) {
						case 0: {
							break editDetails;
						}
						case 1: {
							first = inputFirst(keyboard);
							break handlingInput;
						}
						case 2: {
							last = inputLast(keyboard);
							break handlingInput;
						}
						case 3: {
							yearOfBirth = inputYearOfBirth(keyboard);
							break handlingInput;
						}
						case 4: {
							monthOfBirth = inputMonthOfBirth(keyboard);
							break handlingInput;
						}
						case 5: {
							dayOfBirth = inputDayOfBirth(keyboard);
							break handlingInput;
						}
						case 6: {
							SubjectTaught = inputSubjectTaught(keyboard);
							break handlingInput;
						}
						case 7: {
							YearsTeaching = inputYearsTeaching(keyboard);
							break handlingInput;
						}
					} // switch end
				}
				catch (Exception e) {
					LOGGER.info("Invalid input for edit details menu");
				}
			} // handlingInput Loop
		} // editDetails Loop
		
		
		/*****************************
		 * Password
		 *****************************/
		
		// Prompting user to enter a password.
		System.out.println("Welcome, " + first + "! Time to choose a password for your account.");
		String password;
		
		passwordChecker:
		while (true) {
			
			printPasswordRules(keyboard);
			
			System.out.println("\nPlease choose a password:\n");
			password = keyboard.nextLine();
			System.out.println("\nPlease re-enter your password:\n");
			if (password.equals(keyboard.nextLine())) {
				if (PasswordValidator.passwordValidator(password)) {
					break passwordChecker;
				}
				else {
					System.out.println("Your password is invalid, try again.");
				}
			}
			else {
				System.out.println("Your password does not match, try again.");
			}
		} // passwordChecker Loop
		
		
		/*****************************
		 * Memorable Word
		 *****************************/
				
		// prompting the user to enter a memorable word
		
		System.out.println("Please choose a memorable word to remember your password.");
		String memorableWord;
		
		// memorable word
		StaffMemorableWordInputLoop:
		while (true) {
			memorableWord = keyboard.nextLine();
			if (EntryValidator.entryValidator(memorableWord)) {
				break StaffMemorableWordInputLoop;
			}
			else {
				System.out.println("You have provided an invalid entry, try again");
			}
		}
		
		
		System.out.println("Now that you have entered your details & chosen you password and memorable word. Let's save those to our database!");
		
		
		/*****************************
		 * Creating Staff Object
		 *****************************/
		
		Staff newStaff = new Staff();
		newStaff.setGenerateId();
		newStaff.setFirst(first);
		newStaff.setLast(last);
		newStaff.setGenerateEmail();
		newStaff.setYearOfBirth(yearOfBirth);
		newStaff.setMonthOfBirth(monthOfBirth);
		newStaff.setDayOfBirth(dayOfBirth);
		newStaff.setSubjectTaught(SubjectTaught);
		newStaff.setYearsTeaching(YearsTeaching);
		newStaff.setPassword(password);
		newStaff.setMemorableWord(memorableWord);
		
		try {
			FileHandlerStaff StaffFile = new FileHandlerStaff("/StaffDatabase.txt");
			StaffFile.writeFileNewEntry(newStaff);
			System.out.println("Success! Your Staff ID is: " + newStaff.getId() + 
					"\nYour Staff Email is: " + newStaff.getEmail() +
					"\nDo not forget these details, you will need them to login.");
		}
		catch (Exception e) {
			LOGGER.warning("Unable to add data to file");
		}
		
	} // signUp Method
	
	
	//--------------------------------------
	// methods
	//--------------------------------------
	
	/**
	 * This method prompts the user to enter their first name.
	 * @return first name (String)
	 */
	public static String inputFirst(Scanner keyboard) {
		String first = "";
		while (true) {
			try {
				System.out.println("What is your first name?");
				first = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				first = first.substring(0,1).toUpperCase() + first.substring(1,first.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return first;
	}
	
	/**
	 * This method prompts the user to enter their last name.
	 * @return last name (String)
	 */
	public static String inputLast(Scanner keyboard) {
		String last = "";
		while (true) {
			try {
				System.out.println("What is your last name?");
				last = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				last = last.substring(0,1).toUpperCase() + last.substring(1,last.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return last;
	}
	
	/**
	 * This method prompts the user to enter their year of birth. It checks that the year is valid (i.e. the user is between 18 and 100 years old).
	 * @return year of birth (int)
	 */
	public static int inputYearOfBirth(Scanner keyboard) {
		int yearToday = Year.now().getValue();
		int year;
		while (true) {
			try {
				System.out.println("What year were you born in?");
				year = Integer.valueOf(keyboard.nextLine());
				
				// Checks yearToday-18 as minimum age for university is 18 years old
				// Checks yearToday-100 as the maximum age for university is 100 years
				// These values are hard-coded and so are harder to change / more difficult for code maintenance
				if (year <= yearToday-18 && year >= yearToday-100) {
					break;
				}
				
				else {
					System.out.println("Invalid year given");
					continue;
				}
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return year;
	}
	
	
	/**
	 * This method prompts the user to enter their month of birth. It checks that the month is valid (i.e. between 1 and 12)
	 * @return month of birth (int)
	 */
	public static int inputMonthOfBirth(Scanner keyboard) {
		int month;
		while (true) {
			try {
				System.out.println("Which month were you born on?");
				month = Integer.valueOf(keyboard.nextLine());
				
				// Checks that the month is valid (1 - 12)
				if (month <= 12 && month >= 1) {
					break;
				}
				
				else {
					System.out.println("Invalid month given");
					continue;
				}
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return month;
	}
	
	/**
	 * This method prompts the user to enter their day of birth. It checks that the day is valid (i.e. between 1 and 31)
	 * @return day of birth (int)
	 */
	public static int inputDayOfBirth(Scanner keyboard) {
		int day;
		while (true) {
			try {
				System.out.println("Which day of the month were you born on?");
				day = Integer.valueOf(keyboard.nextLine());
				
				// Checks that the day of the month is valid (1 - 31)
				if (day <= 31 && day >= 1) {
					break;
				}
				
				else {
					System.out.println("Invalid day given");
					continue;
				}
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return day;
	}
	
	
	/**
	 * This method prompts the user to enter their Subject of study.
	 * @return subject (String)
	 */	
	public static String inputSubjectTaught(Scanner keyboard) {
		String SubjectTaught = "";
		while (true) {
			try {
				System.out.println("Which subject do you teach?");
				SubjectTaught = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				SubjectTaught = SubjectTaught.substring(0,1).toUpperCase() + SubjectTaught.substring(1,SubjectTaught.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return SubjectTaught;
	}
	
	
	/**
	 * This method prompts the user to enter the length of time (in years) that they have been teaching at the university.
	 * @return day of birth (int)
	 */
	public static int inputYearsTeaching(Scanner keyboard) {
		int year;
		while (true) {
			try {
				System.out.println("How long have you been teaching at MMU for?");
				year = Integer.valueOf(keyboard.nextLine());
				
				// Checks that the number of years teaching is valid
				if (year <= 100 && year >= 0) {
					break;
				}
				
				else {
					System.out.println("Invalid year given");
					continue;
				}
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return year;
	}
	
	/**
	 * This method prints the minimum requirements for a password to be considered valid.
	 */
	public static void printPasswordRules(Scanner keyboard) {
		System.out.println("\nYour password must be at least " + PasswordValidator.getPasswordMinLength() + " characters long.\n" +
				"\nIt must contain at least: \n- " + PasswordValidator.getPasswordMinUppercaseLetters() + " uppercase letter(s)" +
				"\n- " + PasswordValidator.getPasswordMinDigits() + " digit(s)" +
				"\n- " + PasswordValidator.getPasswordMinLowercaseLetters() + " lowercase letter(s)" +
				"\n- " + PasswordValidator.getPasswordMinSpecialCharacters() + " special character(s)" + 
				"\n\nOnly the following special characters are allowed: ");
		
		char[] allowedSpecial = PasswordValidator.getValidSpecialCharacters();
		for (char c : allowedSpecial) {
			System.out.print(c+ " ");
		}
		System.out.println();
	}
	
	/**
	 * This method prints the minimum requirements for an entry to be considered valid.
	 */
	public static void printEntryRules(Scanner keyboard) {
		System.out.println("\nThis entry must be at least " + EntryValidator.getEntryMinLength() + " characters long and can only contain:" + 
				"\n- Upper- and lowercase letters" + 
				"\n- The following special characters ");
		char[] allowedSpecial = EntryValidator.getValidSpecialCharacters();
		for (char c : allowedSpecial) {
			System.out.print(c+ " ");
		}
		System.out.println();
	}
	
}

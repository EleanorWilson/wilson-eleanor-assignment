package application;

import java.time.Year;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

import manager.PasswordValidator;

public class SignUp {
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(SignUp.class.getName());
	
	// Adding a new scanner
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		/*
		int month = inputMonthOfBirth();
		System.out.println("Echo: "+ month);
		
		int year = inputYearOfBirth();
		System.out.println("Echo: "+ year);
		*/
		
		SignUp();
	}
	
	

	/**
	 * This method takes inputs from the user (first name, last name, etc) and creates a student object based on these inputs.
	 */
	public static void SignUp() {
		System.out.println("Welcome new user!");
		Scanner keyboard = new Scanner(System.in);
		
		String first = inputFirst(keyboard);
		String last = inputLast(keyboard);
		int yearOfBirth = inputYearOfBirth(keyboard);
		int monthOfBirth = inputMonthOfBirth(keyboard);
		int dayOfBirth = inputDayOfBirth(keyboard);
		String subject = inputSubject(keyboard);
		int yearOfStudy = inputYearOfStudy(keyboard);
		
		// this while loop allows users to see what they have entered and edit their details as needed
		editDetails:
		while (true) {
			
			System.out.println("Let's check your details are correct: ");
		
			// Hash Map to Hold Student Details
			HashMap<Integer, String> signUpDetails = new HashMap<Integer, String>();
			signUpDetails.put(1, "First Name: "+first);
			signUpDetails.put(2, "Last Name: "+last);
			signUpDetails.put(3, "Birth Year: "+yearOfBirth);
			signUpDetails.put(4, "Birth Month: "+monthOfBirth);
			signUpDetails.put(5, "Birth Day: "+dayOfBirth);
			signUpDetails.put(6, "Subject of Study: "+subject);
			signUpDetails.put(7, "Year of Study: "+yearOfStudy);
			
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
							subject = inputSubject(keyboard);
							break handlingInput;
						}
						case 7: {
							yearOfStudy = inputYearOfStudy(keyboard);
							break handlingInput;
						}
					} // switch end
				}
				catch (Exception e) {
					LOGGER.info("Invalid input for edit details menu");
				}
			} // handlingInput Loop
		} // editDetails Loop
		
		
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
		
		// prompting the user to enter a memorable word
		
		System.out.println("Please choose a memorable word to remember your password.");
		String memorableWord;
		
		
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
	public static String inputSubject(Scanner keyboard) {
		String subject = "";
		while (true) {
			try {
				System.out.println("Which subject are you studying?");
				subject = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				subject = subject.substring(0,1).toUpperCase() + subject.substring(1,subject.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return subject;
	}
	
	
	/**
	 * This method prompts the user to enter their year of study. It checks that the year is valid (i.e. between 0 and 7).
	 * <br>- Year 0 = Foundation
	 * <br>- Year 7 = PhD (part-time) Final Year
	 * @return day of birth (int)
	 */
	public static int inputYearOfStudy(Scanner keyboard) {
		int year;
		while (true) {
			try {
				System.out.println("Which year of study are you currently in?");
				year = Integer.valueOf(keyboard.nextLine());
				
				// Checks that the year of study is valid (0 - 7)
				if (year <= 7 && year >= 0) {
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
	 * This method prints the minimum requirements for a password to be considered.
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
	
}

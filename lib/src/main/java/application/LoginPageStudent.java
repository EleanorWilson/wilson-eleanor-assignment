package application;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

import manager.PasswordValidator;
import manager.SubjectValidator;
import userData.Student;
import manager.EntryValidator;
import manager.FileHandlerStudent;

public class LoginPageStudent {
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(SignUpStudent.class.getName());
	
	// Adding a new scanner
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * This method takes inputs from the user (first name, last name, etc) and creates a student object based on these inputs.
	 * @throws IOException - if fileHandler path not valid
	 */
	public static void loginPageStudent(Student student) throws IOException {
		System.out.print("Welcome back, "+student.getFirst()+"!");
		keyboard = new Scanner(System.in);
		
		FileHandlerStudent studentFile = new FileHandlerStudent("./src/main/resources/studentDatabase.txt");
		
		
		loginMenuLoop:
		while (true) {
			
			System.out.println("Here are your menu options below: ");
			
		
			// Hash Map to Hold Student Details
			HashMap<Integer, String> loginMenu = new HashMap<Integer, String>();
			loginMenu.put(1, "View and Edit your details");
			loginMenu.put(2, "Delete your account");
			loginMenu.put(3, "Sign Out and Exit Application");
			
			// Ask user to pick menu option
			for (Integer i : loginMenu.keySet()) {
				System.out.println(i+". "+loginMenu.get(i));
			}
			System.out.println("Select an option from the list above.");
			
			
			// Handling invalid options
			handlingInput:
			while (true) {
				try {
					int userChoice = Integer.valueOf(keyboard.nextLine());
					
					switch (userChoice) {
					
						// Edit and View Details
						case 1: {
							openEditDetailsMenu(student);
							break handlingInput;
						}
						
						// Delete Account
						case 2: {
							
							// Ensuring user wants to delete account
							System.out.println("Are you sure you want to delete your account? Please answer yes or no");
							char input;
							
							deleteAccountLoop:
							while (true) {
								input = keyboard.nextLine().toLowerCase().charAt(0);
								switch (input) {
									case 'y': {
										// Prompting user to enter password
										System.out.println("Re-enter your password. If no longer wish to delete your account, type \"exit\"");
										String userInputPassword = keyboard.nextLine();
										while (true) {
											if (userInputPassword.equals(student.getPassword())) {
												deleteAccount(student);
												break loginMenuLoop;
											}
											else if (userInputPassword.toLowerCase().equals("exit")) {
												break deleteAccountLoop;
											}
											else {
												System.out.println("Your passwords do not match. You cannot delete your account at this time.");
											}
										}
									}
									case 'n': {
										break deleteAccountLoop;
									}
									default: {
										System.out.println("Invalid answer given to the question 'do you want to delete your account'.\nPlease respond with a yes or no.");
									}
								}
							}
							break handlingInput;
						}
					
						// Exit Application
						case 3: {
							break loginMenuLoop;
						}
						default: {
							System.out.println("Invalid option selected. Choose from options 1, 2 or 3.");
						}
					} // switch end
				}
				catch (Exception e) {
					LOGGER.info("Invalid input for edit details menu");
				}
			} // handlingInput Loop
		} // editDetails Loop
		
		
	}
		
	/**
	 * This method opens the edit details menu of the login page
	 * @param student
	 * @throws IOException
	 */
	public static void openEditDetailsMenu(Student student) throws IOException {
		
		// Declaring variables
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
		
		editDetailsLoop:
		while (true) {
			
			System.out.println("Here are your current details: ");
			
			System.out.println("Your ID is: "+id);
		
			// Hash Map to Hold Student Details
			HashMap<Integer, String> editDetails = new HashMap<Integer, String>();
			editDetails.put(1, "First Name: "+first);
			editDetails.put(2, "Last Name: "+last);
			editDetails.put(3, "Birth Year: "+yearOfBirth);
			editDetails.put(4, "Birth Month: "+monthOfBirth);
			editDetails.put(5, "Birth Day: "+dayOfBirth);
			editDetails.put(6, "Subject of Study: "+subjectStudying);
			editDetails.put(7, "Year of Study: "+yearOfStudy);
			editDetails.put(8, "Password: "+ "********");
			editDetails.put(9, "Memorable Word: "+memorableWord);
			
			// Ask user to pick menu option
			for (Integer i : editDetails.keySet()) {
				System.out.println(i+". "+editDetails.get(i));
			}
			System.out.println("If any of these details are incorrect, please enter the number you wish to change. If everything is correct, please enter '0'.\n");
			
			
			// Handling invalid options
			handlingInput:
			while (true) {
				try {
					int userChoice = Integer.valueOf(keyboard.nextLine());
					
					switch (userChoice) {
						case 0: {
							break editDetailsLoop;
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
							subjectStudying = inputSubjectStudying(keyboard);
							break handlingInput;
						}
						case 7: {
							yearOfStudy = inputYearOfStudy(keyboard);
							break handlingInput;
						}
						case 8: {
							password = changePassword(keyboard);
							break handlingInput;
						}
						case 9: {
							memorableWord = changeMemorableWord(keyboard);
							break handlingInput;
						}
					} // switch end
				}
				catch (Exception e) {
					LOGGER.info("Invalid input for edit details menu");
				}
			} // handlingInput Loop
		} // editDetails Loop
		
		
		student.setFirst(first);
		student.setLast(last);
		student.setGenerateEmail();
		student.setYearOfBirth(yearOfBirth);
		student.setMonthOfBirth(monthOfBirth);
		student.setDayOfBirth(dayOfBirth);
		student.setGenerateDob(yearOfBirth, monthOfBirth, dayOfBirth);
		student.setSubjectStudying(subjectStudying);
		student.setYearOfStudy(yearOfStudy);
		student.setPassword(password);
		student.setMemorableWord(memorableWord);
		
		System.out.println("Your ID is: "+ student.getId());
		System.out.println("Your Email is: "+ student.getEmail());
		
		FileHandlerStudent studentFile = new FileHandlerStudent("./src/main/resources/studentDatabase.txt");
		
		studentFile.overwriteStudentData(student);
		
	}
	
	public static void deleteAccount(Student student) throws IOException {
		FileHandlerStudent studentFile = new FileHandlerStudent("./src/main/resources/studentDatabase.txt");
		studentFile.removeStudent(student.getId(), student.getPassword());
	}

		
	
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
				System.out.println("What year were you born in? Please use the format: YYYY");
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
				System.out.println("Which month were you born on? Please enter the month's cardinal number.");
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
				System.out.println("Which day of the month were you born on? Please enter the day's cardinal number.");
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
	public static String inputSubjectStudying(Scanner keyboard) {
		String subjectStudying = "";
		while (true) {
			try {
				System.out.println("Which subject are you studying?");
				subjectStudying = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				subjectStudying = subjectStudying.substring(0,1).toUpperCase() + subjectStudying.substring(1,subjectStudying.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return subjectStudying;
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
	 * Allows user to change their password.
	 * @param keyboard
	 * @return password (String).
	 */
	public static String changePassword(Scanner keyboard) {
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
		
		return password;
	}
	
	/**
	 * Allows user to change their password.
	 * @param keyboard
	 * @return password (String).
	 */
	public static String changeMemorableWord(Scanner keyboard) {
		String memorableWord;
		
		// memorable word
		studentMemorableWordInputLoop:
		while (true) {
			System.out.println("Please choose a memorable word to remember your password.");
			memorableWord = keyboard.nextLine();
			if (EntryValidator.entryValidator(memorableWord)) {
				break studentMemorableWordInputLoop;
			}
			else {
				System.out.println("You have provided an invalid entry, try again");
			}
		}
		
		return memorableWord;
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

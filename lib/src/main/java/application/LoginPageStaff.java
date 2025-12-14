package application;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

import manager.PasswordValidator;
import manager.SubjectValidator;
import userData.Staff;
import manager.EntryValidator;
import manager.FileHandlerStaff;

public class LoginPageStaff {
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(SignUpStaff.class.getName());
	
	// Adding a new scanner
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * This method displays the menu/options upon logging in.
	 * @throws IOException - if fileHandler path not valid
	 */
	public static void loginPageStaff(Staff staff) throws IOException {
		System.out.print("Welcome back, "+staff.getFirst()+"!");
		keyboard = new Scanner(System.in);
				
		
		loginMenuLoop:
		while (true) {
			
			System.out.println("Here are your menu options below: ");
			
		
			// Hash Map to Hold Staff Details
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
							openEditDetailsMenu(staff);
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
											if (userInputPassword.equals(staff.getPassword())) {
												deleteAccount(staff);
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
		} // loginMenuLoop
		
		
	} // loginPageStaff
		
	/**
	 * This method opens the edit details menu of the login page
	 * @param staff
	 * @throws IOException
	 */
	public static void openEditDetailsMenu(Staff staff) throws IOException {
		
		// Declaring variables
		int id = staff.getId();
		String first = staff.getFirst();
		String last = staff.getLast();
		String email = staff.getEmail();
		String subjectTaught = staff.getSubjectTaught();
		int yearsTeaching = staff.getYearsTeaching();
		int yearOfBirth = staff.getYearOfBirth();
		int monthOfBirth = staff.getMonthOfBirth();
		int dayOfBirth = staff.getDayOfBirth();
		String password = staff.getPassword();
		String memorableWord = staff.getMemorableWord();
		
		editDetailsLoop:
		while (true) {
			
			System.out.println("Here are your current details: ");
			
			System.out.println("Your ID is: "+id);
		
			// Hash Map to Hold Staff Details
			HashMap<Integer, String> editDetails = new HashMap<Integer, String>();
			editDetails.put(1, "First Name: "+first);
			editDetails.put(2, "Last Name: "+last);
			editDetails.put(3, "Birth Year: "+yearOfBirth);
			editDetails.put(4, "Birth Month: "+monthOfBirth);
			editDetails.put(5, "Birth Day: "+dayOfBirth);
			editDetails.put(6, "Subject of Study: "+subjectTaught);
			editDetails.put(7, "Year of Study: "+yearsTeaching);
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
							subjectTaught = inputSubjectTaught(keyboard);
							break handlingInput;
						}
						case 7: {
							yearsTeaching = inputYearsTeaching(keyboard);
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
		
		
		staff.setFirst(first);
		staff.setLast(last);
		staff.setGenerateEmail();
		staff.setYearOfBirth(yearOfBirth);
		staff.setMonthOfBirth(monthOfBirth);
		staff.setDayOfBirth(dayOfBirth);
		staff.setGenerateDob(yearOfBirth, monthOfBirth, dayOfBirth);
		staff.setSubjectTaught(subjectTaught);
		staff.setYearsTeaching(yearsTeaching);
		staff.setPassword(password);
		staff.setMemorableWord(memorableWord);
		
		System.out.println("Your ID is: "+ staff.getId());
		System.out.println("Your Email is: "+ staff.getEmail());
		
		FileHandlerStaff staffFile = new FileHandlerStaff("./src/main/resources/staffDatabase.txt");
		
		staffFile.overwriteStaffData(staff);
		
	}
	
	public static void deleteAccount(Staff staff) throws IOException {
		FileHandlerStaff staffFile = new FileHandlerStaff("./src/main/resources/staffDatabase.txt");
		staffFile.removeStaff(staff.getId(), staff.getPassword());
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
	public static String inputSubjectTaught(Scanner keyboard) {
		String subjectTaught = "";
		while (true) {
			try {
				System.out.println("Which subject are you studying?");
				subjectTaught = keyboard.nextLine().toLowerCase();
				// reformatting to capitalise the first letter
				subjectTaught = subjectTaught.substring(0,1).toUpperCase() + subjectTaught.substring(1,subjectTaught.length());
				break;
			}
			catch (Exception e) {
				LOGGER.info("Invalid input");
			}
		}
		return subjectTaught;
	}
	
	
	/**
	 * This method prompts the user to enter the length of time (in years) that they have been teaching at the university.
	 * @return day of birth (int)
	 */
	public static int inputYearsTeaching(Scanner keyboard) {
		int year;
		while (true) {
			try {
				System.out.println("How long have you been teaching at mmu for?");
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
	 * Allows user to change their password.
	 * @param keyboard
	 * @return Password (String).
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
	 * Allows user to change their memorableWord.
	 * @param keyboard
	 * @return Memorable word (String).
	 */
	public static String changeMemorableWord(Scanner keyboard) {
		String memorableWord;
		
		// memorable word
		staffMemorableWordInputLoop:
		while (true) {
			System.out.println("Please choose a memorable word to remember your password.");
			memorableWord = keyboard.nextLine();
			if (EntryValidator.entryValidator(memorableWord)) {
				break staffMemorableWordInputLoop;
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

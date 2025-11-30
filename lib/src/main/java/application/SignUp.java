package application;

import java.time.Year;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;

public class SignUp {
	
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(SignUp.class.getName());
	
	// Adding a new scanner
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		int month = inputMonthOfBirth();
		System.out.println("Echo: "+ month);
		
		int year = inputYearOfBirth();
		System.out.println("Echo: "+ year);
		
	}
	
	/*
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		// name
		System.out.println("Hi, new user! What is your first name?");
		String firstName = keyboard.nextLine();
		System.out.println("Hi, "+firstName+"! What is your surname?");
		String lastName = keyboard.nextLine();
		
		// employment info
		System.out.println("Which company do you work for, "+firstName+"?");
		String companyName = keyboard.nextLine();
		System.out.println("How many years have you worked for "+companyName+"?");
		int companyYears = Integer.parseInt(keyboard.nextLine());
		
		// change greeting singular year or plural years
		String yearGreet;
		if (companyYears == 1) {
			yearGreet = "year";
		}
		else {
			yearGreet = "years";
		}
		
		// Greeting
		System.out.println("Thanks, I have all the info I need.");
		System.out.println("Welcome to the programme, "+firstName+" "+lastName+". Congratulations on working for "+companyName+" for "+companyYears+" "+yearGreet+".");
		System.out.println("Now, let's begin...");
		
		// Menu Options Hash Map - for practice with hashmaps
		HashMap<Integer, String> menuOptions = new HashMap<Integer, String>();
		menuOptions.put(1, "Lentil Dhaal");
		menuOptions.put(2, "Beyond Burger");
		menuOptions.put(3, "Vegan Bolognese");
		menuOptions.put(4, "Tabbouleh");
		
		// Ask user to pick menu option
		System.out.println("Please select an option from the menu below:");
		for (Integer i : menuOptions.keySet()) {
			System.out.println("Option "+i+": "+menuOptions.get(i));
		}
		System.out.println("Enter the number of the option you want.");
		int menuChoice = 1;
		
		
		// Handling invalid options
		while (true) {
			menuChoice = Integer.valueOf(keyboard.nextLine());
			if (!(menuChoice > 0) && !(menuChoice < 5)) {
				System.out.println("You have entered an invalid option, please enter a number between 1 and 4.");
			}
			else {
				break;
			}
		}
		
		// Printing user's selection
		System.out.println("Great, "+firstName+"! You have selected option "+menuChoice+", "+menuOptions.get(menuChoice)+"!");
		
		// Loop to allow user to see data they have entered:
		while (true) {
			System.out.println("Choose an option");
			System.out.println("Option 1: Show your name");
			System.out.println("Option 2: Print your company & how long you have worked for them");
			System.out.println("Option 3: Show your food menu option");
			
			// User input
			int choice2 = Integer.valueOf(keyboard.nextLine());
			switch (choice2) {
				case 1: {
					option1(firstName, lastName); break;
				}
				case 2: {
					option2(companyName, companyYears); break;
				}
				case 3: {
					option3(menuChoice, menuOptions); break;
				}
				default: { System.out.println("You have selected an invalid option"); break;}
			}
			
			// go again?
			System.out.println("Do you want to select another option? Yes or no?");
			
			// Only check user input for first letter (y or n)
			String choice3 = keyboard.nextLine().toLowerCase();
			if (choice3.charAt(0) == 'n') {
				break;
			}
			else {
				continue;
			}
		} // while loop
		
		System.out.println("Thank you for using the programme, "+firstName+". See you next time!");
		keyboard.close();
	}
	
	// Option 1 - do something menu
	private static void option1(String firstName, String lastName) {
		System.out.println("Your name is "+firstName+" "+lastName);
	}
	
	// Option 2 - do something menu
	private static void option2(String companyName, int companyYears) {
		System.out.println("You have worked for "+companyName+" for "+companyYears+" years.");
	}
	
	// Option 3 - do something menu
	private static void option3(int menuChoice, HashMap<Integer, String> menuOptions) {
		System.out.println("You have selected option "+menuChoice+", "+menuOptions.get(menuChoice)+ ".");
	}
	
	*/
	
	public void SignUp() {
		System.out.println("Welcome new user!");
		
		String first = inputFirst();
		String last = inputLast();
		
	}
	
	
	//--------------------------------------
	// methods
	//--------------------------------------
	
	/**
	 * This method prompts the user to enter their first name.
	 * @return first name (String)
	 */
	public static String inputFirst() {
		String first = "";
		while (true) {
			try {
				System.out.println("What is your first name?");
				first = keyboard.nextLine().toLowerCase();
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
	public static String inputLast() {
		String last = "";
		while (true) {
			try {
				System.out.println("What is your last name?");
				last = keyboard.nextLine().toLowerCase();
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
	public static int inputYearOfBirth() {
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
	public static int inputMonthOfBirth() {
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
	public static int inputDayOfBirth() {
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
	public static String inputSubject() {
		String subject = "";
		while (true) {
			try {
				System.out.println("Which subject are you studying?");
				subject = keyboard.nextLine().toLowerCase();
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
	public static int inputYearOfStudy() {
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
	
		
	
}

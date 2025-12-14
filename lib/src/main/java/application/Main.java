package application;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import application.SignUpStaff;

public class Main {
	public static void main(String[] args) throws IOException {
		// Adding logger
		Logger LOGGER = Logger.getLogger(Main.class.getName());
		Scanner keyboard = new Scanner(System.in);
		
		boolean isStudent;
		boolean isNew = false;
		
		Page.welcome();
		Page.staffOrStudent();
		
		// loop to find if person is staff or student
		staffOrStudentLoop:
		while (true) {
			String input = keyboard.nextLine().toLowerCase();
			switch (input) {
				case "student":
					isStudent = true;
					break staffOrStudentLoop;
				case "staff":
					isStudent = false;
					break staffOrStudentLoop;
				default:
					LOGGER.info("Invalid input");
					System.out.println("You gave an invalid answer, please respond with 'staff' or 'student'.");
					break;
			}
		}
		
		//---------------------------
		// run student page
		//---------------------------
		if (isStudent) {
			
			Page.newOrReturningStudent();
						
			// loop to find if person is new or returning
			newOrReturningLoop:
			while (true) {
				String input = keyboard.nextLine().toLowerCase();
				switch (input.charAt(0)) {
					case 'y':
						isNew = true;
						break newOrReturningLoop;
					case 'n':
						isNew = false;
						break newOrReturningLoop;
					default:
						LOGGER.info("Invalid input");
						System.out.println("You gave an invalid answer, please respond with 'yes' or 'no'.");
						break;
				}
			}
			
			if (isNew) {
				//SignUpStaff.signUpStaff();
			}
			
			
		} 
		
		//---------------------------
		// run staff page
		//---------------------------
		else {
			
			Page.newOrReturningStaff();
						
			// loop to find if person is new or returning
			newOrReturningLoop:
			while (true) {
				String input = keyboard.nextLine().toLowerCase();
				switch (input.charAt(0)) {
					case 'y':
						isNew = true;
						break newOrReturningLoop;
					case 'n':
						isStudent = false;
						break newOrReturningLoop;
					default:
						LOGGER.info("Invalid input");
						System.out.println("You gave an invalid answer, please respond with 'yes' or 'no'.");
						break;
				}
			}
			
			
		}
		

		
		
		
	}
	
	
}

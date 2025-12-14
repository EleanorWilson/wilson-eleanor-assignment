package application;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;
import application.SignUpStaff;
import manager.FileHandlerStudent;
import userData.Student;

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
			
			//------------------------------
			// Sign up
			//------------------------------
			
			if (isNew) {
				SignUpStudent.signUpStudent();
			}
			
			//------------------------------
			// Login
			//------------------------------
			
			Page.studentLogin();
			
			// Take login details
			int id = 0;
			String password = "";
			FileHandlerStudent studentFile = new FileHandlerStudent("./src/main/resources/studentDatabase.txt");
			
			System.out.println("What is your Student ID");
			
			studentIDLoop:
			while (true) {
				try {
					id = Integer.valueOf(keyboard.nextLine());
					if (studentFile.idAlreadyExists(id)) {
						break studentIDLoop;
					}
					else {
						
						backToSignInLoop:
						while (true) {
							System.out.println("Your ID has not been found in the database. Do you need to be taken to the sign in page?");
							char backToSignIn = keyboard.nextLine().charAt(0);
							switch (backToSignIn) {
								case 'y': 
									SignUpStudent.signUpStudent();
								case 'n':
									break backToSignInLoop;
								default: {
									System.out.println("You have entered an invalid option, respond yes or no.");
									continue;
								}
							}
						} // back to sign in loop
					}
				}
				catch (Exception e) {
					System.out.println("Your ID must be an integer, please try again");
				}
			}
			
			// Creating student object
			Student currentStudent = new Student();
			currentStudent.setId(id);
			
			// finding password for student
			String passwordOnFile = studentFile.readStudentPassword(id);
			String memorableWordOnFile = studentFile.readStudentMemorableWord(id);
			
			System.out.println("Enter your password");
			loginLoop:
			while(true) {
				password = keyboard.nextLine();
				if (password.equals(passwordOnFile)) {
					break loginLoop;
				}
				else {
					System.out.println("You have entered the wrong password, try again.\nYour memorable word is: "+memorableWordOnFile);
				}
			}
			
			// once Student has logged in, fill in Student Object
			currentStudent.setFirst(studentFile.readStudentFirst(id));
			currentStudent.setLast(studentFile.readStudentLast(id));
			currentStudent.setEmail(studentFile.readStudentEmail(id));
			currentStudent.setSubjectStudying(studentFile.readStudentSubjectStudying(id));
			currentStudent.setYearOfStudy(studentFile.readStudentYearOfStudy(id));
			currentStudent.setYearOfBirth(studentFile.readStudentYearOfBirth(id));
			currentStudent.setMonthOfBirth(studentFile.readStudentMonthOfBirth(id));
			currentStudent.setDayOfBirth(studentFile.readStudentDayOfBirth(id));
			currentStudent.setPassword(studentFile.readStudentPassword(id));
			currentStudent.setMemorableWord(studentFile.readStudentMemorableWord(id));
			
			
			System.out.print("Welcome back, "+currentStudent.getFirst()+"!");
			
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
		

		
		// finally closing keyboard
		keyboard.close();
	}
	
	
}

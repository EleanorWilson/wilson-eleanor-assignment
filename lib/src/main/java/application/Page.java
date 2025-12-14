package application;

/**
 * This is a class designed to print messages to the console.
 */
public class Page {
	
	public static void welcome() {
		System.out.println("--------------------------------"+
				"\nWelcome to the university homepage" +
				"\n--------------------------------");
	}
	
	public static void staffOrStudent() {
		System.out.println("Are you a staff member or student?");
	}
	
	public static void newOrReturningStudent() {
		System.out.println("Are you a new student?");
	}
	
	public static void newOrReturningStaff() {
		System.out.println("Are you a new staff member?");
	}
	
	public static void studentLogin() {
		System.out.println("--------------------------------"+
				"\nWelcome to the student homepage" +
				"\n--------------------------------" +
				"\nPlease log in when prompted.");
	}
	
	public static void staffLogin() {
		System.out.println("--------------------------------"+
				"\nWelcome to the staff homepage" +
				"\n--------------------------------" +
				"\nPlease log in when prompted.");
	}
}

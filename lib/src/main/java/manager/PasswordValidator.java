package manager;

/**
 * Class to check a password is:
 * <br>- Of valid length
 * <br>- Contains <strong>only</strong> valid characters
 * <br>- Contains at least one of each of the following: uppercase letter, lowercase letter, special character, number.
 */
public class PasswordValidator {
	
	// Passwords must be a minimum of 8 characters long
	private static final int passwordMinLength = 8;
	
	// Passwords must have a minimum of 1 uppercase letter, 1 lowercase letter, 1 special character and 1 digit.
	private static final int passwordMinUppercaseLetters = 1;
	private static final int passwordMinLowercaseLetters = 1;
	private static final int passwordMinSpecialCharacters = 1;
	private static final int passwordMinDigits = 1;
	
	// char[] lists of valid characters accepted in the password
	private static final char[] allowedSpecial = {'.' , ',' , ':' , '-', '_' , '!' };
	private static final char[] allowedLower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] allowedUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] allowedDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	
	//--------------------------------------
	// Methods
	//--------------------------------------
	
	public boolean checkPasswordLength(String password) {
		return (password.length() >= passwordMinLength);
	}
	
	
	/**
	 * This method checks that each character in the password is a valid / allowed character. 
	 * If there are any invalid characters in the password, the method returns false.
	 * @param password - This is the String (password) to be checked.
	 * @return - A boolean:
	 * <br>- true = All characters in the password are valid
	 * <br>- false = There are at least 
	 */
	public boolean checkAllowedCharactersOnly(String password) {
		boolean allCharactersAllowed = true;
		
		// this loop checks each character in the password
		thisCharAllowedLoop:
		for (char c : password.toCharArray()) {
			
			boolean thisCharAllowed = false;
			
			checkArraysLoop:
			while (true) {
				
				// check special character list
				// ----------------------------
					for (char a : allowedSpecial) {
				
						if (c == a) {
							thisCharAllowed = true;
							// move to next character in password, as this character is known to be valid
							break checkArraysLoop;
						}
						else {
							continue;
						}
					}
			
				// check uppercase letter list
				// ----------------------------
					for (char a : allowedUpper) {
				
						if (c == a) {
							thisCharAllowed = true;
							// move to next character in password, as this character is known to be valid
							break checkArraysLoop;
						}
						else {
							continue;
						}
					}			
			
				// check lowercase letter list
				// ----------------------------
					for (char a : allowedLower) {
				
						if (c == a) {
							thisCharAllowed = true;
							// move to next character in password, as this character is known to be valid
							break checkArraysLoop;
						}
						else {
							continue;
						}
					}		
			
				// check digit list
				// ----------------------------
					for (char a : allowedDigits) {
				
						if (c == a) {
							thisCharAllowed = true;
							// move to next character in password, as this character is known to be valid
							break checkArraysLoop;
						}
						else {
							continue;
						}
					}
			
				/*
				 * Any characters not found in the valid character arrays by this point are NOT valid.
				 * The entire password is therefore NOT valid.
				 */
				if (!thisCharAllowed) {
					allCharactersAllowed = false;
					break thisCharAllowedLoop;
				}
			
				else if (thisCharAllowed) {
					continue;
				}
				
				// Will never be called as primitive boolean types can only be true or false (never null).
				else continue;
			
			}
		}
		return allCharactersAllowed;
	}	
	
	
	
	//--------------------------------------
	// Password Checker
	//--------------------------------------
	
	/*
	public boolean passwordChecker(String password) {
		
		int countUppercaseLetters = 0;
		int countLowercaseLetters = 0;
		int countSpecialCharacters = 0;
		
		// check password length is equal to or exceeds the minimum
		if (!(password.length() >= passwordMinLength)) {
			System.out.println("Your password only has " + password.length() + "characters. You must have at least " + passMinLength + "characters.";
			return false;
		}
		
		for (char c : password.toCharArray()) {
			// checking for invalid characters
			if (!allowedSpecial.contains(c)) && !(allowedLower.contains(c)) && !(allowedUpper.contains(c)) && !(allowedDigits.contains(c)) {
				System.out.println("Your password contains an invalid character: " + c + ".");
				return false;
			}
		}
	}
	*/
	
	//--------------------------------------
	// Password Getters
	//--------------------------------------
	
	public static char[] getValidSpecialCharacters() {
		return allowedSpecial;
	}
	
	public static char[] getLowercaseLetters() {
		return allowedLower;
	}
	
	public static char[] getUppercaseLetters() {
		return allowedUpper;
	}
	
	public static char[] getDigits() {
		return allowedDigits;
	}
	
	public static int getPasswordMinLength() {
		return passwordMinLength;
	}
	
	public static int getPasswordMinUppercaseLetters() {
		return passwordMinUppercaseLetters;
	}
	
	public static int getPasswordMinLowercaseLetters() {
		return passwordMinLowercaseLetters;
	}
	
	public static int getPasswordMinSpecialCharacters() {
		return passwordMinSpecialCharacters;
	}
	
	public static int getPasswordMinDigits() {
		return passwordMinDigits;
	}
	
}

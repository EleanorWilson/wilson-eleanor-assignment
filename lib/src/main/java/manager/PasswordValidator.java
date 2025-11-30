package manager;
import java.util.logging.*;

/**
 * Class to check a password is:
 * <br>- Of valid length
 * <br>- Contains <strong>only</strong> valid characters
 * <br>- Contains at least one of each of the following: uppercase letter, lowercase letter, special character, number.
 */
public class PasswordValidator {
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(PasswordValidator.class.getName());
	
	// Passwords must be a minimum of 8 characters long
	private static final int passwordMinLength = 8;
	
	// Passwords must have a minimum of 1 uppercase letter, 1 lowercase letter, 1 special character and 1 digit.
	private static final int passwordMinUppercaseLetters = 1;
	private static final int passwordMinLowercaseLetters = 1;
	private static final int passwordMinSpecialCharacters = 1;
	private static final int passwordMinDigits = 1;
	
	// char[] lists of valid characters accepted in the password
	private static final char[] allowedSpecial = {'.' , ',' , ':' , '-', '_' , '!', '@' };
	private static final char[] allowedLower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] allowedUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] allowedDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	
	//--------------------------------------
	// Methods
	//--------------------------------------
	
	
	/**
	 * This method checks the password contains the minimum number of characters, defined in the PasswordValidator class.
	 * @param password - This is the password (String) to be checked.
	 * @return - A boolean:
	 * <br>- true = The password is a valid length.
	 * <br>- false = The password is not a valid length.
	 */
	public static boolean checkPasswordLength(String password) {
		boolean validLength = (password.length() >= passwordMinLength);
		
		if (!validLength) {
			LOGGER.info("Your password is only "+password.length() + " characters long (minimum length: " + passwordMinLength + ")");
		}
		
		return validLength;
	}
	
	
	/**
	 * This method checks that each character in the password is a valid / allowed character. 
	 * If there are any invalid characters in the password, the method returns false.
	 * @param password - This is the String (password) to be checked.
	 * @return - A boolean:
	 * <br>- true = All characters in the password are valid.
	 * <br>- false = There is at least one character that is not valid.
	 */
	public static boolean checkAllowedCharactersOnly(String password) {
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
					LOGGER.info("----- ERROR -----" +
								"\ninvalid character: " + "\'" + c + "\'");
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
	
	
	/**
	 * This method checks that the password contains the minimum number of digits, special, uppercase and lowercase characters defined by the PasswordValidator class.
	 * @param password - This is the password (String) to be checked.
	 * @return - A boolean:
	 * <br>- true = The password meets the minimum requirements, it is valid.
	 * <br>- false = The password does not meet the minimum requirements, it is not valid.
	 */
	public static boolean checkMinimumCharacterCount(String password) {
		int countSpecial = 0;
		int countUpper = 0;
		int countLower = 0;
		int countDigit = 0;
		
		for (char c : password.toCharArray()) {
			
			// check special character list
			// ----------------------------
			for (char a : allowedSpecial) {
			
				if (c == a) {
					countSpecial++;
				}
				else {
					continue;
				}
			}
		
			// check uppercase letter list
			// ----------------------------
			for (char a : allowedUpper) {
		
				if (c == a) {
					countUpper++;
				}
				else {
					continue;
				}
			}			
		
			// check lowercase letter list
			// ----------------------------
			for (char a : allowedLower) {
		
				if (c == a) {
					countLower++;
				}
				else {
					continue;
				}
			}		
			
			// check digit list
			// ----------------------------
			for (char a : allowedDigits) {
			
				if (c == a) {
					countDigit++;
				}
				else {
					continue;
				}
			}
		} // checking characters in password
		
		// checking that each count is equal to or greater than the minimum requirements
		if (countSpecial >= passwordMinSpecialCharacters && 
				countUpper >= passwordMinUppercaseLetters &&
				countLower >= passwordMinLowercaseLetters &&
				countDigit >= passwordMinDigits) {
			return true;
		}
		
		else {
			LOGGER.info("----- INVALID CHARACTER COUNTS -----" +
					"\nspecial: " + countSpecial +
					"\nupper: " + countUpper + 
					"\nlower: "+countLower +
					"\ndigit: "+countDigit);
			return false;
		}
	}
	
	
	//--------------------------------------
	// Password Checker
	//--------------------------------------
	
	public static boolean passwordValidator(String password) {
		boolean validPassword = true;
		
		if (!checkPasswordLength(password)) {
			validPassword = false;
		}
		
		if (!checkAllowedCharactersOnly(password)) {
			validPassword = false;
		}
		
		if (!checkMinimumCharacterCount(password)) {
			validPassword = false;
		}
		
		return validPassword;
	}
	
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

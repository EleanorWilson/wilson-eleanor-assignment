package manager;
import java.util.logging.*;

/**
 * Class to check an entry (first name, last name, etc):
 * <br>- Contains <strong>only</strong> valid characters
 */
public class EntryValidator {
	// Adding a logger
	private static Logger LOGGER = Logger.getLogger(EntryValidator.class.getName());
	
	// Entries must be a minimum of 1 characters long
	private static final int entryMinLength = 1;
	
	// char[] lists of valid characters accepted in the entry
	private static final char[] allowedSpecial = {'\'', '-' };
	private static final char[] allowedLower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] allowedUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	
	//--------------------------------------
	// Methods
	//--------------------------------------
	
	
	/**
	 * This method checks the entry contains the minimum number of characters.
	 * @param entry - This is the entry (String) to be checked.
	 * @return - A boolean:
	 * <br>- true = The entry is a valid length.
	 * <br>- false = The entry is not a valid length.
	 */
	public static boolean checkEntryLength(String entry) {
		boolean validLength = (entry.length() >= entryMinLength);
		
		if (!validLength) {
			LOGGER.info("Your password is only "+entry.length() + " characters long (minimum length: " + entryMinLength + ")");
		}
		
		return validLength;
	}
	
	
	/**
	 * This method checks that each character in the entry is a valid / allowed character. 
	 * If there are any invalid characters in the entry, the method returns false.
	 * @param entry - This is the String (entry) to be checked.
	 * @return - A boolean:
	 * <br>- true = All characters in the entry are valid.
	 * <br>- false = There is at least one character that is not valid.
	 */
	public static boolean checkAllowedCharactersOnly(String entry) {
		boolean allCharactersAllowed = true;
		
		// this loop checks each character in the entry
		thisCharAllowedLoop:
		for (char c : entry.toCharArray()) {
			
			boolean thisCharAllowed = false;
			
			checkArraysLoop:
			while (true) {
				
				// check special character list
				// ----------------------------
					for (char a : allowedSpecial) {
				
						if (c == a) {
							thisCharAllowed = true;
							// move to next character in entry, as this character is known to be valid
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
							// move to next character in entry, as this character is known to be valid
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
							// move to next character in entry, as this character is known to be valid
							break checkArraysLoop;
						}
						else {
							continue;
						}
					}
			
				/*
				 * Any characters not found in the valid character arrays by this point are NOT valid.
				 * The entire entry is therefore NOT valid.
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
	
	
	//--------------------------------------
	// entry Checker
	//--------------------------------------
	
	/**
	 * This checks whether the entry is valid.
	 * @param entry - This is the String (entry) to be checked.
	 * @return - A boolean:
	 * <br>- true = entry is valid.
	 * <br>- false = entry is not valid.
	 */
	public static boolean entryValidator(String entry) {
		boolean validentry = true;
		
		if (!checkAllowedCharactersOnly(entry)) {
			validentry = false;
		}
		
		if (!checkEntryLength(entry)) {
			validentry = false;
		}
		
		return validentry;
	}
	
	//--------------------------------------
	// entry Getters
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
	
	public static int getEntryMinLength() {
		return entryMinLength;
	}
}
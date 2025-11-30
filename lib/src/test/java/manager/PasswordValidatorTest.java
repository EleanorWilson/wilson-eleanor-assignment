package manager;
import manager.PasswordValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
	@Test
	@DisplayName("Get Valid Special Characters")
	public void testValidSpecial() {
		// get array of valid special characters
		char[] validChar = PasswordValidator.getValidSpecialCharacters();
		
		// check that the array returned contains correct valid characters
		assertEquals('.', validChar[0]);
		assertEquals(',', validChar[1]);
		assertEquals(':', validChar[2]);
		assertEquals('-', validChar[3]);
		assertEquals('_', validChar[4]);
		assertEquals('!', validChar[5]);
		
	}
	
	@Test
	@DisplayName("Get Valid Lowercase Characters")
	public void testValidUpper() {
		// get array of valid uppercase Letters
		char[] validChar = PasswordValidator.getUppercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('A', validChar[0]);
		assertEquals('E', validChar[4]);
		assertEquals('N', validChar[13]);
		assertEquals('R', validChar[17]);
		assertEquals('T', validChar[19]);
		assertEquals('Z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Valid Uppercase Characters")
	public void testValidLower() {
		// get array of valid uppercase Letters
		char[] validChar = PasswordValidator.getLowercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('a', validChar[0]);
		assertEquals('e', validChar[4]);
		assertEquals('n', validChar[13]);
		assertEquals('r', validChar[17]);
		assertEquals('t', validChar[19]);
		assertEquals('z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Valid Digits")
	public void testValidDigits() {
		// get array of valid uppercase Letters
		char[] validChar = PasswordValidator.getDigits();
		
		// check that the array returned contains correct valid characters
		assertEquals('0', validChar[0]);
		assertEquals('1', validChar[1]);
		assertEquals('3', validChar[3]);
		assertEquals('6', validChar[6]);
		assertEquals('8', validChar[8]);
		assertEquals('9', validChar[9]);
	}
	
	@Test
	@DisplayName("Get Minimum Password Length")
	public void testMinPassLength() {
		assertEquals(8, PasswordValidator.getPasswordMinLength());
	}
	
	@Test
	@DisplayName("Get Minimum Uppercase Letters")
	public void testMinUpper() {
		assertEquals(1, PasswordValidator.getPasswordMinUppercaseLetters());
	}
	
	@Test
	@DisplayName("Get Minimum Lowercase Letters")
	public void testMinLower() {
		assertEquals(1, PasswordValidator.getPasswordMinLowercaseLetters());
	}
	
	@Test
	@DisplayName("Get Minimum Special Characters")
	public void testMinSpecial() {
		assertEquals(1, PasswordValidator.getPasswordMinSpecialCharacters());
	}
	
	@Test
	@DisplayName("Get Minimum Digits")
	public void testMinDigits() {
		assertEquals(1, PasswordValidator.getPasswordMinDigits());
	}
	
	@Test
	@DisplayName("Check Allowed Characters Only")
	public void testCheckAllowedCharactersOnly() {
		
		assertTrue(PasswordValidator.checkAllowedCharactersOnly("Password.1234"));
		assertTrue(PasswordValidator.checkAllowedCharactersOnly("AB_YZ@!mm"));
		assertFalse(PasswordValidator.checkAllowedCharactersOnly("pass#not?allowed&&()"));
		
		// should return true as no invalid characters
		assertTrue(PasswordValidator.checkAllowedCharactersOnly(""));
		
		// spaces are not valid characters
		assertFalse(PasswordValidator.checkAllowedCharactersOnly(" "));
	}
	
	@Test
	@DisplayName("Check Password Length")
	public void testCheckPasswordLength() {
		
		assertTrue(PasswordValidator.checkPasswordLength("Password1234"));
		assertTrue(PasswordValidator.checkPasswordLength("        "));
		assertTrue(PasswordValidator.checkPasswordLength("@_??\n^*&"));
		assertTrue(PasswordValidator.checkPasswordLength("..................................."));
		
		assertFalse(PasswordValidator.checkPasswordLength("       "));
		assertFalse(PasswordValidator.checkPasswordLength(""));
		assertFalse(PasswordValidator.checkPasswordLength("123456"));
		assertFalse(PasswordValidator.checkPasswordLength("\n\n\n"));
	}
	
	@Test
	@DisplayName("Check Password Contains Minimum Character Counts")
	public void testCheckMinimumCharacterCount() {
		
		assertTrue(PasswordValidator.checkMinimumCharacterCount("Password.1234"));
		assertTrue(PasswordValidator.checkMinimumCharacterCount("passW0rd_1234"));
		assertTrue(PasswordValidator.checkMinimumCharacterCount("Ll!1"));
		assertTrue(PasswordValidator.checkMinimumCharacterCount("0-Mw"));
		assertTrue(PasswordValidator.checkMinimumCharacterCount("TTTTT00000@@@@@ooooo"));
		
		assertFalse(PasswordValidator.checkMinimumCharacterCount("TTTTT_____@@@@@"));
		assertFalse(PasswordValidator.checkMinimumCharacterCount("password.1234"));
		assertFalse(PasswordValidator.checkMinimumCharacterCount("Password1234"));
		assertFalse(PasswordValidator.checkMinimumCharacterCount("0)Mw"));
		assertFalse(PasswordValidator.checkMinimumCharacterCount(""));
		assertFalse(PasswordValidator.checkMinimumCharacterCount(" "));
		
	}
	
	@Test
	@DisplayName("Password validator")
	public void testPasswordValidator() {
		
		assertTrue(PasswordValidator.passwordValidator("Password.1234"));
		assertTrue(PasswordValidator.passwordValidator("passW0rd_1234"));
		assertTrue(PasswordValidator.passwordValidator("Ll!1mmmm"));
		assertTrue(PasswordValidator.passwordValidator("0-Mw5555"));
		assertTrue(PasswordValidator.passwordValidator("TTTTT00000@@@@@ooooo"));
		assertTrue(PasswordValidator.passwordValidator("t6.Tt6.T"));
		
		assertFalse(PasswordValidator.passwordValidator(""));
		assertFalse(PasswordValidator.passwordValidator("       "));
		assertFalse(PasswordValidator.passwordValidator("1nV@l1d"));
		assertFalse(PasswordValidator.passwordValidator("2Sh.rt!"));
		assertFalse(PasswordValidator.passwordValidator("/[}{}};'"));
		assertFalse(PasswordValidator.passwordValidator("password1234"));
		assertFalse(PasswordValidator.passwordValidator("Password1234"));
		assertFalse(PasswordValidator.passwordValidator("&&&&&&&&&"));
		assertFalse(PasswordValidator.passwordValidator("*******"));
		assertFalse(PasswordValidator.passwordValidator("HelloWorld!"));
		
	}

}

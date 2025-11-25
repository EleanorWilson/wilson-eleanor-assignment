package manager;
import manager.PasswordValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		// get array of valid Uppercase Letters
		char[] validChar = PasswordValidator.getUppercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('A', validChar[0]);
		assertEquals('E', validChar[4]);
		assertEquals('M', validChar[13]);
		assertEquals('R', validChar[17]);
		assertEquals('T', validChar[19]);
		assertEquals('Z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Valid Uppercase Characters")
	public void testValidLower() {
		// get array of valid Uppercase Letters
		char[] validChar = PasswordValidator.getLowercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('a', validChar[0]);
		assertEquals('e', validChar[4]);
		assertEquals('m', validChar[13]);
		assertEquals('r', validChar[17]);
		assertEquals('t', validChar[19]);
		assertEquals('z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Valid Digits")
	public void testValidDigits() {
		// get array of valid Uppercase Letters
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

}

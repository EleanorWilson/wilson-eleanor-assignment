package manager;
import manager.EntryValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class EntryValidatorTest {
	@Test
	@DisplayName("Get Valid Special Characters")
	public void testValidSpecial() {
		// get array of valid special characters
		char[] validChar = EntryValidator.getValidSpecialCharacters();
		
		// check that the array returned contains correct valid characters
		assertEquals('\'', validChar[0]);
		assertEquals('-', validChar[1]);
	}
	
	@Test
	@DisplayName("Get Valid Uppercase Characters")
	public void testValidUpper() {
		// get array of valid uppercase Letters
		char[] validChar = EntryValidator.getUppercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('A', validChar[0]);
		assertEquals('E', validChar[4]);
		assertEquals('N', validChar[13]);
		assertEquals('R', validChar[17]);
		assertEquals('T', validChar[19]);
		assertEquals('Z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Valid Lowercase Characters")
	public void testValidLower() {
		// get array of valid lower Letters
		char[] validChar = EntryValidator.getLowercaseLetters();
		
		// check that the array returned contains correct valid characters
		assertEquals('a', validChar[0]);
		assertEquals('e', validChar[4]);
		assertEquals('n', validChar[13]);
		assertEquals('r', validChar[17]);
		assertEquals('t', validChar[19]);
		assertEquals('z', validChar[25]);
	}
	
	@Test
	@DisplayName("Get Minimum Entry Length")
	public void testMinPassLength() {
		assertEquals(8, EntryValidator.getEntryMinLength());
	}
	
	@Test
	@DisplayName("Check Allowed Characters Only")
	public void testCheckAllowedCharactersOnly() {
		
		// Checking names with only upper-/lowercase letter, hyphens and apostrophes
		assertTrue(EntryValidator.checkAllowedCharactersOnly("Anyname"));
		assertTrue(EntryValidator.checkAllowedCharactersOnly("Double-BarrelledSurname"));
		assertTrue(EntryValidator.checkAllowedCharactersOnly("O'Connell"));
		assertTrue(EntryValidator.checkAllowedCharactersOnly("O'Double-barrelled"));
		
		// Invalid names
		assertFalse(EntryValidator.checkAllowedCharactersOnly("entry#1not^Allowed"));
		assertFalse(EntryValidator.checkAllowedCharactersOnly("Name_Not_Valid"));
		assertFalse(EntryValidator.checkAllowedCharactersOnly("First Last"));
		
		// should return true as no invalid characters
		assertTrue(EntryValidator.checkAllowedCharactersOnly(""));
		
		// spaces are not valid characters
		assertFalse(EntryValidator.checkAllowedCharactersOnly(" "));
	}
	
	@Test
	@DisplayName("Check Entry Length")
	public void testCheckEntryLength() {
		
		// This method is not concerned with the specific characters used
		assertTrue(EntryValidator.checkEntryLength("anyLengthOFENTRY&here^^"));
		assertTrue(EntryValidator.checkEntryLength("           "));
		assertTrue(EntryValidator.checkEntryLength("3n7nf,k']'pjfnpa8j4wordswords"));
		assertTrue(EntryValidator.checkEntryLength("..................................."));
		
		// Only entries with a length of 0 should return false
		assertFalse(EntryValidator.checkEntryLength(""));

	}
	
	@Test
	@DisplayName("Entry validator")
	public void testEntryValidator() {
		
		assertTrue(EntryValidator.entryValidator("Validname"));
		assertTrue(EntryValidator.entryValidator("first-Name-Goes-here"));
		assertTrue(EntryValidator.entryValidator("Ano'ther-example"));
		assertTrue(EntryValidator.entryValidator("Hyphens-and-apo'strophes"));
		assertTrue(EntryValidator.entryValidator("a"));
		assertTrue(EntryValidator.entryValidator("aa"));
		assertTrue(EntryValidator.entryValidator("A"));
		assertTrue(EntryValidator.entryValidator("AA"));
		assertTrue(EntryValidator.entryValidator("CaPiTaLaNdLoWeRcAsEmIxaLlOwEd"));
		
		assertFalse(EntryValidator.entryValidator(""));
		assertFalse(EntryValidator.entryValidator(" "));
		assertFalse(EntryValidator.entryValidator("invalid_name"));
		assertFalse(EntryValidator.entryValidator("n0digitsall0wed"));
		assertFalse(EntryValidator.entryValidator("no spaces allowed"));
		assertFalse(EntryValidator.entryValidator("No.fullstops-allowed"));
		assertFalse(EntryValidator.entryValidator("&invalid%chars|"));
		
	}

}

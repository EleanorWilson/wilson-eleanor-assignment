package manager;
import manager.SubjectValidator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class SubjectValidatorTest {
	@Test
	@DisplayName("Get Valid Special Characters")
	public void testValidSpecial() {
		// get array of valid special characters
		char[] validChar = SubjectValidator.getValidSpecialCharacters();
		
		// check that the array returned contains correct valid characters
		assertEquals('\'', validChar[0]);
		assertEquals('-', validChar[1]);
		assertEquals(' ', validChar[2]);
	}
	
	@Test
	@DisplayName("Get Valid Uppercase Characters")
	public void testValidUpper() {
		// get array of valid uppercase Letters
		char[] validChar = SubjectValidator.getUppercaseLetters();
		
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
		char[] validChar = SubjectValidator.getLowercaseLetters();
		
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
		assertEquals(1, SubjectValidator.getEntryMinLength());
	}
	
	@Test
	@DisplayName("Check Allowed Characters Only")
	public void testCheckAllowedCharactersOnly() {
		
		// Checking names with only upper-/lowercase letter, hyphens and apostrophes
		assertTrue(SubjectValidator.checkAllowedCharactersOnly("AnySubject"));
		assertTrue(SubjectValidator.checkAllowedCharactersOnly("Subject-allowed"));
		assertTrue(SubjectValidator.checkAllowedCharactersOnly("Apo'StrophesAllowed"));
		assertTrue(SubjectValidator.checkAllowedCharactersOnly("O'hyphen-ated"));
		assertTrue(SubjectValidator.checkAllowedCharactersOnly("FirstWord SecondWord"));
		
		// Invalid names
		assertFalse(SubjectValidator.checkAllowedCharactersOnly("entry#1not^Allowed"));
		assertFalse(SubjectValidator.checkAllowedCharactersOnly("Name_Not_Valid"));

		
		// should return true as no invalid characters
		assertTrue(SubjectValidator.checkAllowedCharactersOnly(""));
		
		// spaces are valid characters
		assertTrue(SubjectValidator.checkAllowedCharactersOnly(" "));
	}
	
	@Test
	@DisplayName("Check Entry Length")
	public void testCheckEntryLength() {
		
		// This method is not concerned with the specific characters used
		assertTrue(SubjectValidator.checkEntryLength("anyLengthOFENTRY&here^^"));
		assertTrue(SubjectValidator.checkEntryLength("           "));
		assertTrue(SubjectValidator.checkEntryLength("3n7nf,k']'pjfnpa8j4wordswords"));
		assertTrue(SubjectValidator.checkEntryLength("..................................."));
		
		// Only entries with a length of 0 should return false
		assertFalse(SubjectValidator.checkEntryLength(""));

	}
	
	@Test
	@DisplayName("Entry validator")
	public void testSubjectValidator() {
		
		assertTrue(SubjectValidator.subjectValidator("Validsubject"));
		assertTrue(SubjectValidator.subjectValidator("first-word-Goes-here"));
		assertTrue(SubjectValidator.subjectValidator("Ano'ther-example"));
		assertTrue(SubjectValidator.subjectValidator("Hyphens-and-apo'strophes"));
		assertTrue(SubjectValidator.subjectValidator("a"));
		assertTrue(SubjectValidator.subjectValidator("aa"));
		assertTrue(SubjectValidator.subjectValidator("A"));
		assertTrue(SubjectValidator.subjectValidator("AA"));
		assertTrue(SubjectValidator.subjectValidator("CaPiTaLaNdLoWeRcAsEmIxaLlOwEd"));
		assertTrue(SubjectValidator.subjectValidator(" "));
		assertTrue(SubjectValidator.subjectValidator("spaces are allowed"));
		
		assertFalse(SubjectValidator.subjectValidator(""));
		assertFalse(SubjectValidator.subjectValidator("invalid_name"));
		assertFalse(SubjectValidator.subjectValidator("n0digitsall0wed"));
		assertFalse(SubjectValidator.subjectValidator("No.fullstops-allowed"));
		assertFalse(SubjectValidator.subjectValidator("&invalid%chars|"));
		
	}

}

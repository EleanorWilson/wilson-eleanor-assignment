package application;
import application.SignUp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

// following imports for testing methods that use Scanner inputs
import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class SignUpTest {
	// System in reads bytes
    private final InputStream scannerIn = System.in;
    private final PrintStream printOut = System.out;
    
    // Used for converting test input data (String) to byte array so they can be passed to system in
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    
    @BeforeEach
    public void testOutputSetUp() {
    	testOut = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(testOut));
    }
    
    /**
     * Takes test input data, converts to bytes and passes into System in.
     * @param inputData (String) - this data simulates a scanner next line input
     */
    /*private void setInput(String inputData) {
    	testIn = new ByteArrayInputStream(inputData.getBytes());
    	System.setIn(testIn);
    }
    */
    
    /**
     * Converts test data from bytes to string.
     * @return - String expected result.
     */
    private String getOutput() {
    	return testOut.toString();
    }
    
    @AfterEach
    public void resetScannerInPrintOut() {
    	System.setIn(scannerIn);
    	System.setOut(printOut);
    }
    
    public void testInputFirst() {
    	// test short string
    	String testString = "namehere" ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	Scanner keyboard = new Scanner(testString);
    	SignUp.inputFirst(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test string with \n newline character
    	testString = "0~'d\n" ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputFirst(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test string with ' ' single quotes
    	testString = "*'DScxm\\mdsjfjknl88980bbf@sd'";
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputFirst(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test empty string
    	testString = "" ;
    	keyboard = new Scanner(testString);
    	SignUp.inputFirst(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// testing spaces and tabs
    	testString = " 	 " ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputFirst(keyboard);
    	assertEquals(testString, getOutput());
    }
    
    public void testInputLast() {
    	// test short string
    	String testString = "namehere" ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	Scanner keyboard = new Scanner(testString);
    	SignUp.inputLast(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test string with \n newline character
    	testString = "0~'d\n" ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputLast(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test string with ' ' single quotes
    	testString = "*'DScxm\\mdsjfjknl88980bbf@sd'";
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputLast(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// test empty string
    	testString = "" ;
    	keyboard = new Scanner(testString);
    	SignUp.inputLast(keyboard);
    	assertEquals(testString, getOutput());
    	
    	// testing spaces and tabs
    	testString = " 	 " ;
    	testString = testString.toLowerCase().substring(0,1).toUpperCase() + testString.substring(1,testString.length());
    	keyboard = new Scanner(testString);
    	SignUp.inputLast(keyboard);
    	assertEquals(testString, getOutput());
    }
    
    public void testInputYearOfBirth() {
    	// test valid int
    	String testInt = "2000" ;
    	Scanner keyboard = new Scanner(testInt);
    	SignUp.inputLast(keyboard);
    	assertEquals(testInt, getOutput());
    	
    	// test valid int
    	testInt = "2007" ;
    	keyboard = new Scanner(testInt);
    	SignUp.inputLast(keyboard);
    	assertEquals(testInt, getOutput());
    	
    	// test valid int
    	testInt = "1925" ;
    	keyboard = new Scanner(testInt);
    	SignUp.inputLast(keyboard);
    	assertEquals(testInt, getOutput());
    	
    	// test invalid int

    }
    
}


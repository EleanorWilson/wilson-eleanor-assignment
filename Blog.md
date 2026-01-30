# Introduction to Programming Digital Artefact: University Login Page

By Eleanor L. Wilson

## Overview
The aim of this project was to create an application that simulates a simple university sign up/login page, for both students and staff, with a simple database to store login details in the form of a comma separated data file. 

Source code for this project can be found at: [this GitHub repo](https://github.com/EleanorWilson/wilson-eleanor-assignment/tree/master).

This report can be found at: [this link](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/master/Blog.md).

## Architecture & Code

This application was written in Java version 21.0.8. For the following section I will discuss three features of my code, why they were implemented and how.

### User Input

As the application was meant to simulate a login and signup page, I needed to ensure the application could accept user input. I chose to use Java's `Scanner` class, the package for which I imported using:

```java
import java.util.Scanner;
```

I then initialised an instance of the class with an appropriate name, `keyboard`, see [this line](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/c13189e2e65a24e13ee6979f0009e8584893319a/lib/src/main/java/application/Main.java#L19) of the `Main.java` class as an example:

```java
Scanner keyboard = new Scanner(System.in);
```

In order to read the user's inputs, I used the `.nextLine()` method of the Scanner class. This would read the next line inputted by the user and return a String containing this data. 

```java
String input = keyboard.nextLine();
```

As many of the variables I wanted to receive user input to set the value of were `String` data types, often there was only a need to ensure the inputs were consistent, especially where they were to be compared against a logical operator. For example in the [`Main.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/c13189e2e65a24e13ee6979f0009e8584893319a/lib/src/main/java/application/Main.java#L30) class, when asking the user whether they were staff or student:

```java
String input = keyboard.nextLine().toLowerCase();
```

However, because the data type returned by the `keyboard.nextLine()` method was always a `String` there were instances where I needed to convert between this and another data type. Initially, I attempted to use `keyboard.nextInt()`, however this caused issues where I was taking multiple user inputs within a method (see [user input](#user-input-issues) issues section). I later decided to use the `Integer.valueOf()` method to convert between String and Integer types, below is an example from the [SignUpStudent.java](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/c13189e2e65a24e13ee6979f0009e8584893319a/lib/src/main/java/application/SignUpStudent.java#L380) class.

```java
month = Integer.valueOf(keyboard.nextLine());
```

Finally, it was also important to ensure the Scanner was closed after it had been initialised and was no longer required. This is because failing to close input/output (I/O) streams, such as the Scanner class, can cause resource leaks which can lead to poor application performance.

For example, in the [`Main.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/c13189e2e65a24e13ee6979f0009e8584893319a/lib/src/main/java/application/Main.java#L282) class:

```java
keyboard.close();
```

---

### Testing

For my project, I used Gradle, a build tool that would allow me to run tests of my code via the command line:

```bash
# run all tests
./gradlew clean test
# run tests from one class
./gradlew clean test --tests "path-of-java-test-class"
```

Furthermore, I chose to use a testing framework for java, JUnit, to write my unit tests. Unit testing is an important part of ensuring code quality and reliability, it was therefore important to implement these tests consistently and ensure the methods in each class were working as expected. Ideally, each main class of the application had a corresponding test class. 

For the test classes, it was important to first import the correct packages for the assertions and notations:

```java
// Assertions
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// Notations
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
```

For each test I used the `@Test` notation, which Gradle would scan the tests classes for in order to identify tests that needed to be run. Each test was given a clear name using the `@DisplayName` which would show in the gradle test report. 

In order to test the `FileHandlerStaff.java` class, I created the [`FileHandlerStaffTest.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/master/lib/src/test/java/manager/FileHandlerStaffTest.java) test class. The test created an empty staff object, populated the staff object with random test data and wrote these details to the `staffDatabaseTest.txt` file. Next, I wrote a list of assertions to both test that my file reader worked correctly, and to check that the file writer had worked as intended.

```java
assertEquals(1, staffFile.readStaffId(newStaff));
assertEquals("First", staffFile.readStaffFirst(newStaff));
assertEquals("Last", staffFile.readStaffLast(newStaff));
assertEquals("First.Last@mmu.ac.uk", staffFile.readStaffEmail(newStaff));
assertEquals(1997, staffFile.readStaffYearOfBirth(newStaff));
assertEquals(6, staffFile.readStaffMonthOfBirth(newStaff));
assertEquals(17, staffFile.readStaffDayOfBirth(newStaff));
assertEquals("DTS", staffFile.readStaffSubjectTaught(newStaff));
assertEquals(1, staffFile.readStaffYearsTeaching(newStaff));
assertEquals("Password.1234", staffFile.readStaffPassword(newStaff));
assertEquals("Simple", staffFile.readStaffMemorableWord(newStaff));
```

This also gave me an opportunity to test other methods, for example `idAlreadyExists` which checks whether a staff id (int) already exists in the staff database. 

```java
assertTrue(staffFile.idAlreadyExists(1));
```

In addition to testing whether my staff data could be overwritten:

```java
// overwriting data
newStaff.setFirst("Jane");
newStaff.setLast("Smith");
newStaff.setGenerateEmail();

staffFile.overwriteStaffData(newStaff);
```

Where my tests failed, I was able to produce a build scan through gradle, which gave an overview of the test results, which passed, which failed and why. In addition to providing other metrics, such as how long the tests took to complete.

See link to the [latest build scan](https://scans.gradle.com/s/hr7qyklwm26py).

These build scans were a great tool for identifying which classes were lacking in test coverage and which methods needed to be improved on. For an example of an older build scan with failures, [follow this link](https://scans.gradle.com/s/psviwpgad74ms/tests/overview).

---

### Console Output

There were two clear uses for console outputs, printing information to the user of the application and providing a log of problems/issue encountered for debugging purposes. I chose to use loggers and the System class to achieve this.

#### <strong>Logging</strong>

Logging was important for diagnosing and understanding problems with my application during development. Where exceptions were being thrown on certain methods, it was important for me have a tool that would allow me to track which lines of the method were failing. I chose to use the java Logger class. For each class I imported the appropriate package: 

```java
import java.util.logging.Logger;
```

Then created an instance of the Logger class, giving it the same name as the class, see below for the `SignUpStudent.java` class example:

```java
private static Logger LOGGER = Logger.getLogger(SignUpStudent.class.getName());
```

I chose to use a Logger for information relating to errors with the code or with user input, as the Logger class gives greater control over how this information is presented in the console. I made use of the different Logger levels, using the `info` level for user information and the `error` level for information about problems with the code. For example, in the [`PasswordValidator.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/PasswordValidator.java#L46) class, I used the Logger level `info` to inform users when their password did not meet the minimum requirements:

```java
if (!validLength) {
	LOGGER.info("Your password is only "+password.length() + " characters long (minimum length: " + passwordMinLength + ")");
}
```

Whereas it was important to differentiate between this level of information, intended for the user, and problems identified with the code or a method. I chose to add log messages at `error` level in order to provide more details when try catch blocks threw exceptions. For example in the [`LoginPageStaff.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/application/LoginPageStaff.java#L118) class:

```java
catch (Exception e) {
    LOGGER.info("Invalid input for edit details menu");
}
```

The logger ultimately gives clearer information to both myself, the developer, and the end user, also allowing me to differentiate between details of errors and the 'web page' information I wanted to present to the user.

#### <strong>Console Printing</strong>

I chose to have a class designed for storing methods that returned 'web page' information to the user in a user-friendly format. In order to implement this, I chose to print information to the console using java's `System` class:

```java
System.out.println();
```

The [`Page.java`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/application/Page.java#L26) class utilised this most, for example with the following two methods that printed a welcome message to the console for both the student and staff 'web pages'.

```java
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
```

Furthermore, as I was not creating a GUI, having the ability to separate these 'web page' outputs from log outputs, was also very helpful. 

## Issues Encountered

### CSV Dataset, Valid and Invalid Characters

For student sign up details to be 'saved', I chose to implement a `.txt` database file for [students](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/master/lib/src/main/resources/studentDatabase.txt) and [staff](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/master/lib/src/main/resources/staffDatabase.txt). The login details were stored in csv format, where tabular data was separated by commas. It is important to note here that the github repo provides a blank data file that is populated once the programme has run. Student data, for example, would be stored as follows:

```java
id,first_name,last_name,email,subject,year_of_study,year_of_birth,month_of_birth,day_of_birth,password,memorable_word
```

The decision to separate data by commas introduced an unexpected issue - no data stored in the file could contain commas. This was due to the way the data was read to an array, see [lines 113 & 114 of FileHandlerStudent.java class](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/FileHandlerStudent.java#L113):

```java
// Splitting each line by , comma and adding to array
String[] lineAsArray = line.split(",");
```

If the data contained commas, the data would be 'separated' incorrectly. My solution to this was the creation of an [EntryValidator.java](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/master/lib/src/main/java/manager/EntryValidator.java) class. The purpose of this class was to ensure that entries contained only characters that were allowed. Entries, in this instance, refer to the following fields:

- First Name
- Last Name
- Memorable Word

The requirements for these fields were that they contained only uppercase letters, lowercase letters or the special characters `'` or `-`. To achieve this I created three arrays: 

- [`allowedSpecial`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L16)
- [`allowedLower`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L17)
- [`allowedUpper`](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L18)

Which stored the allowed characters. The next step was to create a method that looped through the characters of each user input and compared each character against those stored in the aforementioned arrays. If any character of the user's input did not match any of the characters found in the arrays, then the method would return `false`. 

The method, named [checkAllowedCharactersOnly](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L52), takes a String parameter (user input) and returns a boolean value:

- `true` indicates that the user input contained no disallowed characters
- `false` indicates that the user input contained at least 1 disallowed character

A boolean, `allCharactersAllowed = true`, was used to store the output of the method. A [for loop](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L57) called `thisCharAllowedLoop` then assessed each character in the String input:

```java
thisCharAllowedLoop:
for (char c : entry.toCharArray()) {
```

Another boolean, `thisCharAllowed = false`, was used to store whether the character was 'allowed'. A while loop called `checkArraysLoop` contained three for loops, one for each of the arrays. Once a match was found, `thisCharAllowed` would be set to `true` and the `checkArraysLoop` would break, allowing the outer `thisCharAllowedLoop` to move on to the next character. 

If no match was found for that character, then `allCharactersAllowed` would be set to `false` the `thisCharAllowedLoop` would break.

```java
if (!thisCharAllowed) {
    LOGGER.info("----- ERROR -----" +
                "\ninvalid character: " + "\'" + c + "\'");
    allCharactersAllowed = false;
    break thisCharAllowedLoop;
}
```

The individual for loops that checked each character for a match followed the same principle. For example, for [allowed uppercase characters](https://github.com/EleanorWilson/wilson-eleanor-assignment/blob/dfda9c9a04f7b9671ee3ff8b8decf5577b5252dc/lib/src/main/java/manager/EntryValidator.java#L80):

```java
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
```

The current character of the user input being assessed would be checked against each character in the `allowedUpper` array, if a match was found the boolean `thisCharAllowed` would be set to `true` and the `checkArraysLoop` would break.

This was an exercise in using nested `for` and `while` loops, looping through arrays, logical comparison and using loop names to break specific parent/child loops.

### User Input Issues

As [previously discussed](#user-input) the `Scanner.nextLine()` method returns a String data type and there were instances where I required an integer input from the user. My initial approach was to use `Scanner.nextInt()` however, the scanner would then 'skip' a line the next time it was called to read user input. After some research, I later found that this was due to the Scanner reading only the integer input, rather than the entire line, which includes the `\n` newline character, unlike with `Scanner.nextLine()` which reads up to the next `\n` newline character. One solution was to add a `Scanner.nextLine()` below after the `nextInt` call, however an alternative method was to make use of Java's `valueOf` functions, found in the Integer wrapper class (amongst others, such as Double and Float classes). This allowed me to take an Integer input from the user in a way that was clear, making code maintenance easier in future. 

### Future Work & Discussion

A clear flaw of the way in which data is stored in the `.txt` files, is that there is no security; any person with access to the files would have access to sensitive data about the user(s), for example date of birth and passwords. In future, I would like to revisit this method of storing data and replace it with a more secure system.

Additionally, the test coverage of my code could have been improved, as not every class has an equivalent test class. One method of improving this in future could be the addition of mocking tool like Mockito, which would give me greater control over my testing.

## References & Sources

Asghar, A. (2025) Closing Scanner in Java. Available at: https://www.baeldung.com/java-scanner-close (Accessed: 08/01/2026 2026).

Geeks For Geeks (2025) Why is Scanner skipping nextLine() after use of other next functions? Available at: https://www.geeksforgeeks.org/java/why-is-scanner-skipping-nextline-after-use-of-other-next-functions/ (Accessed: 12/01/2026 2026).

Oracle (2025) Class Integer. Available at: https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#valueOf-int- (Accessed: 11/01/2026 2026).

Srivastava, S. (2026) 'Labels in java', Available at: Telusko. Available at: https://docs.telusko.com/docs/java/control-flow/labels-in-java (Accessed 28/01/2026).

Torlak, E. and Chandra, S. 'Effective interprocedural resource leak detection', Proceedings of the 32nd ACM/IEEE International Conference on Software Engineering - Volume 1, Cape Town, South Africa: Association for Computing Machinery, 535–544.

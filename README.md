# Important
May need to run with admin privileges or the file reader/writer might have issues.

## Digital Artifact

This digital artifact replicates a 'sign up' and 'login' page for a university website.

### How does it work?

The application opens with a welcome message and asks whether the user is a student or staff member. It also asks whether the person is new, in which case they are prompted to 'sign up', or whether they have already 'signed up' previously, in which case they are invited to 'log in'. The individuals' details are stored in a 'database', a .txt file (read as a CSV), which can be read from and written to (including overwritten and removed). The programme will ask for specific details:

<ul>
  <li>First Name</li>
  <li>Last Name</li>
  <li>Year, Month and Day of Birth</li>
</ul> 

Students will then be asked:

<ul>
  <li>Subject they are studying</li>
  <li>Which year of university are they in</li>
</ul> 

Staff, on the other hand, will instead be asked:

<ul>
  <li>Subject they teach</li>
  <li>For how many years they have been teaching at the University</li>
</ul>

For the purposes of this application, staff specifically refers to staff members who teach.

### Known Potential Issues and Troubleshooting

If the gradle/junit tests fail for the FileHandlerStudentTest or FileHandlerStaffTest classes, this may be due to either the studentDatabaseTest.txt file or the staffDatabaseTest.txt file(located in src/test/resources) containing existing data. Trying opening the .txt file and CTRL+A then DELETE to remove all existing data, before trying again.

When users are promtped to enter the day of the month on which they were born, the application checks only whether the integer entered is between 1 and 31, this can therefore lead to problems where an individual can enter invalid dates of birth, for example 31/02/2000 (31st of February is never a valid date).

### Future Work and Other Points of Note

As my project uses gradle and JUnit testing for both dependency management and testing, I can produce a Build Scan (publishes to Develocity, an external site). The link to the most recent build scan is (found here)[https://scans.gradle.com/s/hr7qyklwm26py]. However, this can be replicated by opening the console, `cd` into the relevant folder or git repository, if cloned, and running the command `./gradlew clean --scan test` in the console (N.B. this does prompt you to enter your email address, to which a hyperlink to the build scan will be send, open these links at your own risk).

This application does not currently check whether the subject entered is a 'valid subject', i.e. no comparison against a list of subjects offered by the university, or similar.

Additionally, there is no security on the 'databases' for either the students or the staff. Both files can currently be opened and all entries, including sensitive information such as dates of birth and passwords, can be viewed by anyone with access to the file.

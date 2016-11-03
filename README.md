# PrintStream
Java application to modify printstream metadata
---

Note: This application requires Eclipse (Juno or above) to run.

---
Running the application:

Steps to run the application:
1. Import this project into your workspace
2. App.java is the entry point. Execute the main() method.
2. A pop-up dialog will appear. Browse and select a file for printing.
3. Press "Ok".

This Application creates a "printstream_metadata.pjl" file in the "resources" folder.
It will read the USERID parameter in the file and replace it with the Host Operating System's username i.e System.getProperty("user.name").

---
Testing the application:

Steps to run the tests:
1. AppTest.java is the main test class.
2. To run individual test cases execute each unit test class as a JUnit Application.
3. To run all the test cases, run the AppTest.java as a JUnit Application.

Tests will validate the updated/modified USERID field in the generated file, file extension and identify a connected printer.

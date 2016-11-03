/**
 * @printstream
 * @31-Jul-2016
 * @AppPrintService.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import au.com.ricoh.interview.printstream.printer.IPrinter;
import au.com.ricoh.interview.printstream.printer.PrinterImpl;

/**
 * Application's Print Service class is responsible for: 
 * Invoking .pjl metadata creation method
 * Read USERID's value from the .pjl file
 * Update USERID's value and store it back to the same .pjl file
 * Creating a new PrintStream file with updated USERID's value
 * 
 * @author Ravish N
 *
 */
public class AppPrintService {

	// USERID's value
	private String userId;

	/**
	 * Create an instance of the IPrinter which is responsible for 
	 * creating the .pjl metadata file and invoke print service.
	 * @throws Exception
	 */
	public void printDocumentService() throws Exception {

		// instance of PrinterImpl
		IPrinter printer = new PrinterImpl();

		// call printDocument() of PrinterImpl that takes the print input from user
		printer.printDocument();

		// call generatePjlMetadata() which creates a new metadata file upon print command
		File pjlMetadata = printer.generatePjlMetadata();

		// read the value of USERID parameter from the .pjl metadata file
		userId = readUserId(pjlMetadata);

		// update the USERID's value by replacing the existing USERID to user's host OS's login credential
		updateUserId(pjlMetadata, userId);

		// create a new PrintStream with updated USERID's value
		createPrintStream();
	} // end of printDocumentService()

	/**
	 * This method is responsible for creating a PrintStream file with updated USERID's value
	 * stored in the created .pjl metadata file
	 * @throws IOException
	 */
	private void createPrintStream() throws IOException {

		// create a new File under target folder
		File newStreamFile = new File("target/newPrintStreamFile");

		// create a PrintStream instance
		PrintStream stream = new PrintStream(newStreamFile);

		// if file doesn't exist, create one
		if (!newStreamFile.exists()) {

			newStreamFile = new File("target/newStreamFile.pjl");
			newStreamFile.createNewFile();

			// copy the content of the updated .pjl metadata file to the new PrintStream file
			File sourceFile = new File("resources/printstream_metadata.pjl");
			File destinationFile = new File(newStreamFile.getName());
			FileUtils.copyFile(sourceFile, destinationFile);
		} // end if

		// close the stream
		stream.close();
	} // end of createPrintStream()

	/**
	 * This method is responsible for reading the USERID's value and returning to the calling method
	 * @param pjlMetadata
	 * @return userId
	 * @throws IOException
	 */
	public String readUserId(File pjlMetadata) throws IOException {

		// read the .pjl metadata file
		Scanner scanner = new Scanner(pjlMetadata);

		// iterate through the content
		while (scanner.hasNext()) {
			// when scanner finds the line which contains USERID, read its value
			if (scanner.next().equals("USERID")) {

				// read the USERID parameter's value
				userId = scanner.nextLine();
			} // end if
		} // end while

		scanner.close();
		return userId;
	} // end of readUserId()

	/**
	 * This method is responsible for updating the USERID's value
	 * @param pjlMetadata
	 * @param userIdReplace
	 * @return userIdReplace
	 * @throws IOException
	 */
	private String updateUserId(File pjlMetadata, String userIdReplace) throws IOException {

		// store each line in a datastructure
		List<String> newLines = new ArrayList<String>();

		// if .pjl metadata file exists, read all the lines
		if (pjlMetadata.exists()) {

			// iterate through each line
			for (String line : Files.readAllLines(Paths.get(pjlMetadata.toString()), StandardCharsets.ISO_8859_1)) {

				// when USERID is found, replace with the user's host OS's login credential
				if (line.contains("USERID")) {
					newLines.add(line.replace(userId, " = " + '"' + System.getProperty("user.name") + '"'));
					System.out.println("");
				} else {
					newLines.add(line);
				} // end if-else
			} // end for
			// write the updaed value to the same metadata file
			Files.write(Paths.get(pjlMetadata.toString()), newLines, StandardCharsets.ISO_8859_1);
		} else {
			// show error if the .pjl metadata file does not exist/can't be created
			System.err.println("Error creating metadata file");
		} // end if-else
		return userIdReplace;
	} // end of updateUserId()
} // enf of AppPrintService class

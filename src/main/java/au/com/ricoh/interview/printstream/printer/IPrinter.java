/**
 * @printstream
 * @31-Jul-2016
 * @IPrinter.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream.printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.PrintException;

/**
 * Printer interface to define printer functions
 * 
 * @author Ravish N
 *
 */
public interface IPrinter {

	/**
	 * method to generate .pjl metadata file upon print command
	 * 
	 * @return
	 * @throws Exception
	 */
	public File generatePjlMetadata() throws Exception;

	/**
	 * method to print the user selected document
	 * 
	 * @throws PrintException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void printDocument() throws PrintException, FileNotFoundException, IOException;
}

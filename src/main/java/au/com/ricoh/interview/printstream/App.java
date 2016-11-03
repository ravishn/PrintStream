/**
 * @printstream
 * @31-Jul-2016
 * @App.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream;

import au.com.ricoh.interview.printstream.service.AppPrintService;

/**
 * Application to print a file that is given as an input from the user. Read the
 * USERID parameter value in the .pjl metadata file generated from the IPrinter
 * interface Change the USERID value and save it back to the generated .pjl
 * metadata file Create a PrintStream file with the updated USERID value
 *
 */
public class App {
	/**
	 * Entry point to the App. PrintService of the application is invoked from
	 * the main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// instance of AppPrintService which has the implementation of the App's features
		AppPrintService service = new AppPrintService();

		// call to printDocumentService method of AppPrintService where USERID's value is fetched, modified and saved back
		service.printDocumentService();
	} // end of main()
} // end of App class

/**
 * @printstream
 * @31-Jul-2016
 * @TestPrintDocument.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.junit.Test;

/**
 * Test Case for checking the availability of printer
 * @author Ravish N
 *
 */
public class TestPrinterAvailability extends AppTest {

	/**
	 * the constructor
	 * @param testName
	 */
	public TestPrinterAvailability(String testName) {
		super(testName);
	} // end of the constructor

	/**
	 * Test method to check the availability of printer.
	 * If any printer is connected to the computer, test should pass, else fail
	 */
	@Test
	public void testPrinterAvl() {

		// create a DocFlavor instance to auto detect the file format
		DocFlavor printInputFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;

		PrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();

		// identify the available print services
		PrintService[] services = PrintServiceLookup.lookupPrintServices(printInputFormat, requestAttributeSet);

		// iterate through the services
		for (int servicesCount = 0; servicesCount < services.length; servicesCount++) {
			String serviceName = services[servicesCount].toString();

			// identify the nearest printer
			if (serviceName.contains("printer closest to me")) {
				
				assertTrue(true);
				break;
			}
			else {
				fail();
			} // end if-else
		} // end for
	} // end of testPrinterAvl()
} // end of TestPrinterAvailability class

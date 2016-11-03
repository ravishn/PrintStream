/**
 * @printstream
 * @31-Jul-2016
 * @TestUserId.java
 * 
 * @Ravish N
 */

package au.com.ricoh.interview.printstream;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import au.com.ricoh.interview.printstream.service.AppPrintService;

/**
 * Test case to validate whether the USERID parameter's value 
 * @author Ravish N
 *
 */
public class TestUserId extends AppTest {

	/**
	 * the constructor
	 * @param testName
	 */
	public TestUserId(String testName) {
		super(testName);
	} // end of the constructor

	/**
	 * test method to check if the updated USERID's value is 
	 * host OS's username i.e. System.getPropery("user.name").
	 * If the USERID's value == username, test should pass, else fail
	 */
	@Test
	public void testPrintDocumentService() {

		AppPrintService service = new AppPrintService();
		
		File file = new File("resources/printstream_metadata.pjl");
		try {
			String userId = "= " +'"' + System.getProperty("user.name") + '"';
			if (service.readUserId(file).trim().equals(userId.trim())) {
				assertTrue(true);
			}
			else
			{
				fail();
			} // end of if-else
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end of testPrintDocumentService()
} // end of TestUserId class

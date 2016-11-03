/**
 * @printstream
 * @31-Jul-2016
 * @AppTest.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( AppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 * @throws Throwable
	 */
	public void testApp() throws Throwable
	{
		TestUserId userIdValidation = new TestUserId("testPrintDocumentService");
		userIdValidation.runTest();

		TestMetadataFileFormat testFormat = new TestMetadataFileFormat("testMetaDataFileFormat");
		testFormat.runTest();

		TestPrinterAvailability testPrintDoc = new TestPrinterAvailability("testPrinterAvl");
		testPrintDoc.runTest();
	} // end of testApp()
} // end of AppTest class

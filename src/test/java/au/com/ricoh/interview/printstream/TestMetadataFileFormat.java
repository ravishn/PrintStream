/**
 * @printstream
 * @31-Jul-2016
 * @TestMetadataFileFormat.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream;

import java.io.File;

import org.junit.Test;

/**
 * Test case to validate the file format/file extension of the metadata
 * @author Ravish N
 *
 */
public class TestMetadataFileFormat extends AppTest {

	/**
	 * the constructor
	 * @param testName
	 */
	public TestMetadataFileFormat(String testName) {
		super(testName);
	} // end of the constructor
	
	/**
	 * Test method to check the extension of the generated metadata.
	 * If the the generated file extension is .plj, test should pass, else fail
	 */
	@Test
	public void testMetaDataFileFormat() {
		File file = new File("resources/printstream_metadata.pjl");
		if(file.getName().endsWith(".pjl")) {
			assertTrue(true);
		}
		else {
			fail();
		} // end if-else
	} // end of testMetaDataFileFormat()
} // end of TestMetadataFileFormat class

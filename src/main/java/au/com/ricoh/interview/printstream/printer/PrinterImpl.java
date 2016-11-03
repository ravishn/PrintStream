/**
 * @printstream
 * @31-Jul-2016
 * @PrinterImpl.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream.printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.event.PrintServiceAttributeEvent;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.widgets.Shell;

import au.com.ricoh.interview.printstream.service.AppPrintService;
import au.com.ricoh.interview.printstream.ui.AppPrintServiceSelectFileDialog;

/**
 * Implementation of the IPrinter interface
 * @author Ravish N
 *
 */
public class PrinterImpl extends AppPrintService implements IPrinter {

	AppPrintServiceSelectFileDialog selectFileDialog;

	/**
	 * method to read the user selected file and pass the file stream to print
	 * job.
	 * 
	 * @throws IOException
	 * 
	 */
	public void printDocument() throws PrintException, IOException {
		
		// create an instance of the AppPrintServiceSelectFileDialog in a blank Shell
		selectFileDialog = new AppPrintServiceSelectFileDialog(new Shell());
		selectFileDialog.open();

		// create a FileInputStream to get the file path from the text field of the Dialog
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(selectFileDialog.getFilePath());
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		if (fileInputStream == null) {
			return;
		} // end if
		
		// create a DocFlavor instance to auto detect the file format
		DocFlavor printInputFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc docForPrint = new SimpleDoc(fileInputStream, printInputFormat, null);
		PrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();

		// identify the available print services
		PrintService[] services = PrintServiceLookup.lookupPrintServices(printInputFormat, requestAttributeSet);

		// if there are multiple printers configured, this step ensures to connect to the nearest printer
		PrintService myPrinter = null;

		// iterate through the available print services
		for (int servicesCount = 0; servicesCount < services.length; servicesCount++) {
			String serviceName = services[servicesCount].toString();

			// identify the nearest printer
			if (serviceName.contains("printer closest to me")) {
				myPrinter = services[servicesCount];
				break;
			} // end if
		} // end for

		// when a printer is found, create a print job for that printer
		if (myPrinter != null) {
			DocPrintJob printJob = myPrinter.createPrintJob();
			try {
				printJob.print(docForPrint, requestAttributeSet);
			} catch (Exception pe) {
				pe.printStackTrace();
			}
		} else {
			System.err.println("No printer services found");
		} // end if-else

		// NOTE: this following sequence of code is only to test whether print job is successful or not
		// TODO: comment/remove if a printer is connected and identified
		PrintServiceAttributeSet serviceAttributeSet = PrintServiceLookup.lookupDefaultPrintService().getAttributes();

		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		PrintServiceAttributeEvent serviceAttributeEvent = new PrintServiceAttributeEvent(printService,
				serviceAttributeSet);

		DocPrintJob printJobService = serviceAttributeEvent.getPrintService().createPrintJob();
		printJobService.print(docForPrint, requestAttributeSet);
	} // end of printDocument()

	/**
	 * method to generate the .pjl metadata file when a print command is
	 * executed
	 */
	public File generatePjlMetadata() throws Exception {

		// create a .pjl metadata file
		File file = new java.io.File("resources/printstream_metadata.pjl");
		
		// if file doesn't exist, create one
		if (!file.exists()) {
			file.createNewFile();
			File sourceFile = new File("src/test/samples/sample1.pjl");
			File destinationFile = new File(file.toString());
			
			// copy content from the sample file sample1.pjl to printstream_metadata.pjl
			FileUtils.copyFile(sourceFile, destinationFile);
		} // end if
		return file;
	} // end of generatePjlMetadata()
} // end of PrinterImpl class

/**
 * @printstream
 * @31-Jul-2016
 * @AppPrintServiceSelectFileDialog.java
 * 
 * @Ravish N
 */
package au.com.ricoh.interview.printstream.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class implements a FileDialog through which user selects the
 * file to print
 * @author Ravish N
 *
 */
public class AppPrintServiceSelectFileDialog extends Dialog {

	// name of the file which user selects
	private String selected;
	
	// text field in which the path of the selected file is shown
	private Text pathTextField;

	/**
	 * the constructor
	 * @param parentShell
	 */
	public AppPrintServiceSelectFileDialog(Shell parentShell) {
		super(parentShell);
	} // end of AppPrintServiceSelectFileDialog() constructor

	/**
	 * Overridden method of Dialog class which creates the UI container
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		// Main container area for the controls
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 17;

		// label to prompt the user to select a file for printing
		Label lblSelectFileTo = new Label(container, SWT.NONE);
		lblSelectFileTo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectFileTo.setText("Select File to Print");

		// text area which displays the path of the selected file
		pathTextField = new Text(container, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 15, 1);
		gd_text.widthHint = 357;
		pathTextField.setLayoutData(gd_text);
		pathTextField.setEditable(false);

		// Browse folders to select a file for printing
		Button btnBrowse = new Button(container, SWT.PUSH);
		btnBrowse.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		btnBrowse.setText("Browse...");

		// Action for the button click event
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				// Dialog to select the file to print
				FileDialog fileDialog = new FileDialog(new Shell());

				// Set the text
				fileDialog.setText("Select File");

				// Set filter on .pdf, .txt and .doc files
				fileDialog.setFilterExtensions(new String[] { "*.pdf", "*.txt", "*.doc" });

				// Put in a readable name for the filter
				fileDialog.setFilterNames(
						new String[] { "PDF Files(*.pdf)", "Text files(*.txt)", "Word Document(*.doc)" });

				// Open Dialog and save result of selection
				selected = fileDialog.open();
				
				// Set the text of the field to path of the file
				pathTextField.setText(selected);
			} // end of widgetSelected() action
		}); // end of addSelection() listener

		return container;
	} // end of createDialogArea() method

	/**
	 * getter for filePath
	 * @return selected
	 */
	public String getFilePath() {

		return selected;
	} // end of getFilePath()

	/**
	 * Overriding this method allows you to set the title of the custom dialog
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("File Selection");
	} // end of configureShell()

	/**
	 * Overriding this method allows you to set the initial size
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	} // end of getInitialSize()
} // end of AppPrintServiceSelectFileDialog class

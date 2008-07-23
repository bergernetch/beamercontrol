/**
 * 
 */
package ch.bergernet.beamerControl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ch.bergernet.plugins.infocus.InfocusControlPlugin;

/**
 * @author dani
 * 
 */
public class BeamerControl {
	
	private BeamerControlPlugin control;

	private Label label, labelModel;
	private Text connectionURL;
	private Button connectButton, freezeButton;	
	
	public void run() {
		Display display = new Display();
		System.out.println("Start shell");
		Shell shell = new BeamerControl().open(display);

		System.out.println("Start control");
		control = new InfocusControlPlugin();
		
		//updateDisplay();

		System.out.println("while shell");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		System.out.println("dispose shell");
		display.dispose();
	}
	
	public void updateDisplay(){
		labelModel.setText(control.getModel());		
	}
	
	public void dispatchConnectButton(){
		control.connect(connectionURL.getText());		
		System.out.println("connection state: " + control.getConnectionState().isConnected());
	}
	
	public void dispatchFreezeButton(){
		control.setFreeze(!control.getFreeze());
		System.out.println("freeze state: " + control.getFreeze());
	}

	public Shell open(Display display) {
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		
		label = new Label(shell, SWT.CENTER);
		connectionURL = new Text(shell, SWT.CENTER);
		connectButton = new Button(shell, SWT.CENTER);
		freezeButton = new Button(shell, SWT.CENTER);
		labelModel = new Label(shell, SWT.CENTER);
		
		label.setText("Beamer Control v.0.0.1");
		
		connectionURL.setText("tcp://192.168.111.78:23");

		connectButton.setText("Connect");
		connectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				dispatchConnectButton();
			}
		});
		
		freezeButton.setText("Freeze");
		freezeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				dispatchFreezeButton();
			}
		});
		
		shell.pack();
		shell.open();
		return shell;
	}

}

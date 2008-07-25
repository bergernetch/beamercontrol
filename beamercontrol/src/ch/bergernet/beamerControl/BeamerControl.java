/**
 * 
 */
package ch.bergernet.beamerControl;

import ch.bergernet.plugins.infocus.InfocusControlPlugin;

/**
 * @author dani
 * 
 */
public class BeamerControl {
	
	private BeamerControlPlugin control;

	public static void main(String args[]) {	
		BeamerControl bc = new BeamerControl();
	}
	
	public BeamerControl(){
		control = new InfocusControlPlugin();
		System.out.println("Welcome to BeamerControl CLI");
	}
	
	public void dispatchConnectButton(){
		String url = "";
		control.connect(url);
		System.out.println("connection state: " + control.getConnectionState().isConnected());
	}
	
	public void dispatchFreezeButton(){
		control.setFreeze(!control.getFreeze());
		System.out.println("freeze state: " + control.getFreeze());
	}

}

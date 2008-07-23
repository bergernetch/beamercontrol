/**
 * 
 */
package ch.bergernet.plugins.infocus;

import java.io.IOException;
import java.net.URI;

import ch.bergernet.beamerControl.BeamerControlPlugin;
import ch.bergernet.beamerControl.CommandResult;
import ch.bergernet.beamerControl.ConnectionState;
import ch.bergernet.beamerControl.InputSelector;
import ch.bergernet.beamerControl.InterfaceInfo;
import ch.bergernet.beamerControl.PowerState;
import ch.bergernet.beamerSimulator.BeamerSimulator;

/**
 * @author dani
 *
 */
public class InfocusControlPlugin implements BeamerControlPlugin {

	ConnectionState conState = new ConnectionState();
	InputSelector inputSelector = new InputSelector();
	InterfaceInfo ifaceInfo = new InterfaceInfo();
	PowerState powerState = new PowerState();
	boolean freezeFlag = false;
	String beamerModel = "";
	
	final static String readCmd = "?)";
	final static String powerCmd = "(PWR";
	final static String sourceCmd = "(SRC";
	final static String freezeCmd = "(FRZ";
	final static String modelCmd = "<pcml><Stats><Model/></Stats></pcml>";
	
	final static String[] supportedModels = { "ASK>","Beamer Simulator 0.0.1"};
	
	final static boolean SIMULATE = false;

	BeamerSimulator simulator;
	TCPClient beamerClient = new TCPClient();
	
	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#connect(java.lang.String)
	 */
	public boolean connect(String connectionURI) {
		if(conState.isConnected()){
			disconnect();
		}
		
		URI uri = URI.create(connectionURI);
		System.out.println(uri.getHost());
		
		if(uri.getScheme().equalsIgnoreCase("tcp")){
			
			System.out.println(uri.toString());
			
			if(SIMULATE){
				simulator = new BeamerSimulator();
				ifaceInfo.setInterfaceInfo("connected to simulator: " + simulator.getInfo());				
			} else {
		        try {
		        	beamerClient.connect("localhost",8888);
		            //client.send("hehe Test");
					ifaceInfo.setInterfaceInfo("connected to beamer: " + beamerClient.getInfo());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
			
			// check if we are connected to "our" beamer
			boolean found = false;
			String model = getModel();
			for (int i=0;i < supportedModels.length;i++){
				if(model.equalsIgnoreCase(supportedModels[i])){
					found = true;
				}
			}
			if (!found){
				// model is not supported
				System.out.println("Not supported Beamer Model: " + model);
				return false;
			}
			// end check
			
			conState.setConnected(true);
			return true;
		}
		
		return false;
	}
	
	public boolean isConnected(){
		return conState.isConnected();
	}
	
	public void disconnect(){
		simulator = null;
		conState.setConnected(false);
		ifaceInfo.setInterfaceInfo("not connected");
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getConnectionState()
	 */
	public ConnectionState getConnectionState() {
		return conState;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getFreeze()
	 * "(FRZ?)" --> "(FRZ?)(0-1,0)"
	 */
	public boolean getFreeze() {
		CommandResult cmdResult = sendCommand(freezeCmd.concat(readCmd));
		freezeFlag = cmdResult.getValueAsBoolean();
		return freezeFlag;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getInput()
	 */
	public InputSelector getInput() {
		CommandResult cmdResult = sendCommand(sourceCmd.concat(readCmd));
		inputSelector.setInputSelector(cmdResult.getValue());
		return inputSelector;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getInterfaceInfo()
	 */
	public InterfaceInfo getInterfaceInfo() {
		return ifaceInfo;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getModel()
	 */
	public String getModel() {
		beamerModel = sendRawCommand(modelCmd);
		return beamerModel;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getPowerState()
	 */
	public PowerState getPowerState() {
		CommandResult cmdResult = sendCommand(powerCmd.concat(readCmd));
		powerState.setPowerState(cmdResult.getValue());
		return powerState;
	}
	
	public CommandResult sendCommand(String cmd){
		String result = sendRawCommand(cmd);
		CommandResult cmdResult = new CommandResult(result);
		return(cmdResult);
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#sendCommand(java.lang.String)
	 */
	public String sendRawCommand(String cmd) {
		if(conState.isConnected()){
			return simulator.sim(cmd);
		}
		return "not connected!";
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#setFreeze(boolean)
	 */
	public void setFreeze(boolean newFreezeState) {
		String cmd;
		if(newFreezeState){
			cmd = freezeCmd.concat("1)");
		} else {
			cmd = freezeCmd.concat("0)");
		}
		sendCommand(cmd);
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#setInput(ch.bergernet.InputSelector)
	 */
	public void setInput(InputSelector selector) {
		String cmd = sourceCmd.concat(String.valueOf(selector.getInputSelector()));
		cmd = cmd.concat(")");
		sendCommand(cmd);
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#setPower(ch.bergernet.PowerState)
	 */
	public void setPower(PowerState state) {
		String cmd = powerCmd.concat(String.valueOf(state.getPowerState()));
		cmd = cmd.concat(")");
		sendCommand(cmd);
	}

}

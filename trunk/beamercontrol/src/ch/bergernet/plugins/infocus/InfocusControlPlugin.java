package ch.bergernet.plugins.infocus;

import java.io.IOException;
import java.net.URI;

import ch.bergernet.beamerControl.BeamerControlPlugin;
import ch.bergernet.beamerControl.ICommandResult;
import ch.bergernet.beamerControl.IConnectionState;
import ch.bergernet.beamerControl.IInputSelector;
import ch.bergernet.beamerControl.IInterfaceInfo;
import ch.bergernet.beamerControl.IPowerState;
import ch.bergernet.beamerSimulator.BeamerSimulator;

public class InfocusControlPlugin implements BeamerControlPlugin {

	IConnectionState conState = new ConnectionState();
	IInputSelector inputSelector = new InputSelector();
	IInterfaceInfo ifaceInfo = new InterfaceInfo();
	IPowerState powerState = new PowerState();
	boolean freezeFlag = false;
	String beamerModel = "";
	URI uri;
	
	final static String readCmd = "?)";
	final static String powerCmd = "(PWR";
	final static String sourceCmd = "(SRC";
	final static String freezeCmd = "(FRZ";
	final static String modelCmd = "<pcml><Stats><Model/></Stats></pcml>";
	
	final static String[] supportedModels = { "ASK>","Beamer Simulator 0.0.1"};
	
	final static boolean SIMULATE = true;

	BeamerSimulator simulator;
	TCPClient beamerClient = new TCPClient();
	
	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#connect(java.lang.String)
	 */
	public boolean connect(String newConnectionURI) {
		if(conState.isConnected()){
			disconnect();
		}
		
		try {
			uri = URI.create(newConnectionURI);
			System.out.println("will try to connect to: " + uri.getHost());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
		
		if(uri.getScheme().equalsIgnoreCase("tcp")){
			
			System.out.println(uri.toString());
			
			if(SIMULATE){
				simulator = new BeamerSimulator();
				ifaceInfo.setInterfaceInfo("connected to simulator: " + simulator.getInfo());				
			} else {
		        try {
		        	beamerClient.connect("localhost",8888);
		            //beamerClient.send("hehe Test");
					ifaceInfo.setInterfaceInfo("connected to beamer: " + beamerClient.getInfo());
		        } catch (IOException e) {
		        	System.out.println("connect failed: " + e.getLocalizedMessage());
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
	public IConnectionState getConnectionState() {
		return conState;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getFreeze()
	 * "(FRZ?)" --> "(FRZ?)(0-1,0)"
	 */
	public boolean getFreeze() {
		ICommandResult cmdResult = sendCommand(freezeCmd.concat(readCmd));
		freezeFlag = cmdResult.getValueAsBoolean();
		return freezeFlag;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getInput()
	 */
	public IInputSelector getInput() {
		ICommandResult cmdResult = sendCommand(sourceCmd.concat(readCmd));
		inputSelector.setInputSelector(cmdResult.getValue());
		return inputSelector;
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#getInterfaceInfo()
	 */
	public IInterfaceInfo getInterfaceInfo() {
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
	public IPowerState getPowerState() {
		ICommandResult cmdResult = sendCommand(powerCmd.concat(readCmd));
		powerState.setPowerState(cmdResult.getValue());
		return powerState;
	}
	
	public ICommandResult sendCommand(String cmd){
		String result = sendRawCommand(cmd);
		ICommandResult cmdResult = new CommandResult(result);
		return(cmdResult);
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#sendCommand(java.lang.String)
	 */
	public String sendRawCommand(String cmd) {
		if(conState.isConnected()){
			if(SIMULATE){
				return simulator.sim(cmd);			
			} else {
		        try {			
		        	return beamerClient.send(cmd);
		        } catch (IOException e) {
		        	System.out.println("sendRawCommand failed: " + e.getLocalizedMessage());
		            e.printStackTrace();
		        }
			}
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
	public void setInput(IInputSelector selector) {
		String cmd = sourceCmd.concat(String.valueOf(selector.getInputSelector()));
		cmd = cmd.concat(")");
		sendCommand(cmd);
	}

	/* (non-Javadoc)
	 * @see ch.bergernet.BeamerControlPlugin#setPower(ch.bergernet.PowerState)
	 */
	public void setPower(IPowerState state) {
		String cmd = powerCmd.concat(String.valueOf(state.getPowerState()));
		cmd = cmd.concat(")");
		sendCommand(cmd);
	}

}

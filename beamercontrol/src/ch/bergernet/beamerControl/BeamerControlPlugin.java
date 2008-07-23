/**
 * 
 */
package ch.bergernet.beamerControl;

/**
 * @author dani
 * 
 */
public interface BeamerControlPlugin {
	
	public CommandResult sendCommand(String cmd);
	
	public String sendRawCommand(String cmd);
	
	public boolean connect(String URL);
	
	public ConnectionState getConnectionState();
	
	public void setPower(PowerState state);
	
	public void setInput(InputSelector selector);
	
	public void setFreeze(boolean freeze);
	
	public String getModel();
	
	public PowerState getPowerState();
	
	public InputSelector getInput();
	
	public boolean getFreeze();
	
	public InterfaceInfo getInterfaceInfo();
}

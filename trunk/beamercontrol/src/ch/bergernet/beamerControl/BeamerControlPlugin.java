package ch.bergernet.beamerControl;

public interface BeamerControlPlugin {
	
	public ICommandResult sendCommand(String cmd);
	
	public String sendRawCommand(String cmd);
	
	public boolean connect(String URL);
	
	public IConnectionState getConnectionState();
	
	public void setPower(IPowerState state);
	
	public void setInput(IInputSelector selector);
	
	public void setFreeze(boolean freeze);
	
	public String getModel();
	
	public IPowerState getPowerState();
	
	public IInputSelector getInput();
	
	public boolean getFreeze();
	
	public IInterfaceInfo getInterfaceInfo();
}

package ch.bergernet.plugins.infocus;

import ch.bergernet.beamerControl.IConnectionState;

public class ConnectionState implements IConnectionState{
	private boolean connected = false;

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

}

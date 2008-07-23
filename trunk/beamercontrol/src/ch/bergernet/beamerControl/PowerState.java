package ch.bergernet.beamerControl;

public class PowerState {
	
	public static final int POWER_OFF = 0;
	public static final int POWER_ON = 1;
	public static final int POWER_STANDBY = 2;
	
	private int powerState = POWER_OFF;

	public int getPowerState() {
		return powerState;
	}

	public void setPowerState(int powerState) {
		this.powerState = powerState;
	}
}

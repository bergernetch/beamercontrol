package ch.bergernet.plugins.infocus;

import ch.bergernet.beamerControl.ICommandResult;

public class CommandResult implements ICommandResult{
	// "(FRZ?)" --> "(FRZ?)(0-1,0)"
	private String cmd = "";		// FRZ
	private String range = "";		// 0-1
	private int rangeLow = 0;		// 0
	private int rangeHigh = 0;		// 1
	private int value = 0;			// 0

	public CommandResult(String cmdResult){
		parse(cmdResult);
	}
	
	private boolean parse(String cmdResult){
		return true;
	}

	public String getCmd() {
		return cmd;
	}

	public String getRange() {
		return range;
	}

	public int getRangeLow() {
		return rangeLow;
	}

	public int getRangeHigh() {
		return rangeHigh;
	}

	public int getValue() {
		return value;
	}

	public boolean getValueAsBoolean() {
		if (value == 1){
			return true;
		} else {
			return false;
		}
	}
}

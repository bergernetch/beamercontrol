package ch.bergernet.beamerControl;

public class InputSelector {

	public final static int INPUT_PC1 = 0;
	public final static int INPUT_PC2 = 1;
	public final static int INPUT_PC3 = 2;
	public final static int INPUT_SVIDEO = 3;
	public final static int INPUT_VIDEO = 4;
	public final static int INPUT_COMPONENT = 5;
	public final static int INPUT_UNKNOWN = 6;
	
	private int inputSelector;

	public int getInputSelector() {
		return inputSelector;
	}

	public void setInputSelector(int inputSelector) {
		this.inputSelector = inputSelector;
	}
}

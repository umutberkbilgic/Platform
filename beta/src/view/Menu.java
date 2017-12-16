package view;

import org.newdawn.slick.Image;

public abstract class Menu {
	// Properties
	private int stateID;
	private Image background;
	
	public int getStateID() {
		return stateID;
	}
	
	public void setStateID(int stateID) {
		this.stateID = stateID;
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void setBackground(Image background) {
		this.background = background;
	}
}
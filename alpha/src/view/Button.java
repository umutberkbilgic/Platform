package view;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button {
	
	//properties
	private double x; // x-coordinate of the upper left corner
	private double y; //y-coordinate of the upper left corner
	private double width;
	private double height;
	private int currentState; // initialize this
	private Image background;
	private int transititonState;
	
	//constructor
	public Button(double x, double y, double width, double height,  String imagePath, int currentState, int transitionState) throws SlickException{
		this.x = x;
		this.y = y;
		try{
			background = new Image(imagePath);
		} catch(SlickException e){
			
		}
		this.currentState = currentState;
		this.transititonState = transitionState;
	}
	
	//methods
	public boolean isPositionContained(double x, double y){
		return (this.x <= x) && (x <= this.x + width) && (this.y <= y) && (y <= this.y + height);
	} 
	
	public boolean isPressed(){
		//TODO
		return true;
	}
	
	public void changeState(){
		//TODO
	}
	
	public boolean setImage(String imagePath)
	{
		try{
			background = new Image(imagePath);
		} catch(SlickException e){
			return false;
		}
		return true;
	}
	
	public boolean scale(){
		//TODO
		return false;
	}
	
	public int currentState(){
		return currentState;
	}
	

}

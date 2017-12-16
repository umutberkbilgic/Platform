package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button {
	
	//properties
	private double x; // x-coordinate of the upper left corner
	private double y; //y-coordinate of the upper left corner
	private double width;
	private double height;
	private Image background;
	private int transitionstate;
	
	//constructor
	public Button(double x, double y, double width, double height,  String imagePath, int transitionstate) throws SlickException{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.transitionstate = transitionstate;
		try{
			background = new Image(imagePath);
		} catch(SlickException e){
			
		}
	}
	
	//methods
	public boolean isPositionContained(double x, double y){
		return (x >= this.x) && (x <= this.x + width) && (y >= this.y) && (y <= this.y + height);
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
	
	public Image getImage() {
		return background;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
}

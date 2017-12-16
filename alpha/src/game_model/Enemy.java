package game_model;

public class Enemy extends GameObject {
	
	//properties
	private Difficulty difficulty;
	private double xSpeed;
	private double ySpeed;
	
	public Enemy(double xSpeed, double ySpeed, double x, double y, double width, double height, Difficulty difficulty){
		super(x,y,width,height);
		this.xSpeed = xSpeed;
		this.difficulty = difficulty;
		this.ySpeed = ySpeed;
		
	}
	
	
	public void translate(double time){
		//TODO
	}
	
}

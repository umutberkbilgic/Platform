package game_model;

public class Player extends GameObject{
	

	PowerUp currentPowerUp;
	double totalScore;
	Facing facing;
	double xSpeed;
	double ySpeed;
	int numOfLives;
	
	public Player(double x, double y, double width, double height, PowerUp currentPowerUp, Facing facing) {
		super(x, y, width, height);
		currentPowerUp  = null;
		totalScore = 0;
		this.facing = facing;
		xSpeed = 0;
		ySpeed = 0;
		numOfLives = 3;
		
	}
	
	public void moveLeft(){ 
		//TODO
	}
	
	public void moveRight(){ 
		//TODO
	}
	
	public void jump(){
		//TODO
	}
	
	public void renderPowerUpEffect(double duration, PowerUp powerUp){ 
		//TODO
	}
}

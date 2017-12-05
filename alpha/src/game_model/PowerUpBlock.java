package game_model;

public class PowerUpBlock extends Block{
	PowerUp powerUp;

	public PowerUpBlock(String imagePath, double x, double y, double width, double height, PowerUp powerUp) {
		super(imagePath, x, y, width, height);
		this.powerUp = powerUp;
	}

}

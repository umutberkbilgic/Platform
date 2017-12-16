package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : PowerupBlock ?????
 */

public class PowerUpBlock extends Block {
	PowerUp powerUp;

	public PowerUpBlock(int x, int y) {
        super(x, y);
        boundingShape = new Hitbox(x * 32, y * 32, 32, 32);
	}

}

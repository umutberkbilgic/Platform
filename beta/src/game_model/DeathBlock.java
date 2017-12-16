package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : DeathBlock class is to specify a special block to indicate the end of life off the player.
 * 			Specifically, at the end of the void that a player can fall
 */

public class DeathBlock extends Block {

	public DeathBlock(int x, int y) {
        super(x, y);
        isSolid = false; // indicates that DeathBlock instances are not collidable
        boundingShape = new Hitbox(x * 32, y * 32, 32, 32);
	}

}

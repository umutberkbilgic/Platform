package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : FinishBlock class is to specify a special block to indicate the end of the game.
 * 			The purpose of the player is to increase their score while making the duration of their run minimum.
 */

public class FinishBlock extends Block {

	public FinishBlock(int x, int y) {
        super(x, y);
        
        isFinishBlock = true;
        
        isSolid = false;
        boundingShape = new Hitbox(x * 32, y * 32, 32, 32);
	}

}

package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : SolidBlock ???
 */

public class SolidBlock extends Block
{

	 public SolidBlock(int x, int y) 
	 {
	        super(x, y);
	        isSolid = true;
	        boundingShape = new Hitbox(x * 32, y * 32, 32, 32);
	 }
}

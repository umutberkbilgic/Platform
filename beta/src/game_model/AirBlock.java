package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : AirBlock class is needed to identify the not collidable blocks.
 * 			the collidableness of a block is determined by the isSolid property of 
 * 			all blocks, which is contained in Block class. 
 */

public class AirBlock extends Block 
{
	public AirBlock(int x, int y) 
	{
        super(x, y);
        isSolid = false;
    }

}

package game_model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Block extends GameObject {
	World world;
	Image background;
	int  typeOfBlock;
	public Block(String imagePath, double x, double y, double width, double height){
		super(x, y, width, height);
		try{
			background = new Image(imagePath);
		} catch(SlickException e){}
		
	}
}

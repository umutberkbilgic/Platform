package game_model;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class World {
	Image background;
	
	public World(String imagePath){
		try{
			background = new Image(imagePath);
		} catch(SlickException e){
				//do nothing
		}
		
	}
}

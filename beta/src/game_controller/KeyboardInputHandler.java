package game_controller;

import org.newdawn.slick.Input;
import game_model.Player;

public class KeyboardInputHandler {

	private Player player;
	
    public KeyboardInputHandler(Player player)
    {
    	this.player = player;
    }
    
    public void handleKeyboardInput(Input input, int delta)
    {
        if(input.isKeyDown(Input.KEY_D))
        {
            player.moveRight(delta);
        }
        
        else if(input.isKeyDown(Input.KEY_A))
        {
            player.moveLeft(delta);
        }
        
        else
        {
            player.setMoving(false);
        }
        
        if(input.isKeyDown(Input.KEY_SPACE))
        {
            player.jump();
        }
    }

}

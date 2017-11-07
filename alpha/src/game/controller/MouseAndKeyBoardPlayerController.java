package game.controller;
 
import game.character.Player;
 
import org.newdawn.slick.Input;
 
public class MouseAndKeyBoardPlayerController extends PlayerController {
 
    public MouseAndKeyBoardPlayerController(Player player) {
        super(player);
    }
 
    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i, delta);
    }
    
    private void handleKeyboardInput(Input i, int delta){
        
    	// see which keys are being pressed each frame
    	boolean inputLeft  = i.isKeyDown(Input.KEY_A);
    	boolean inputRight = i.isKeyDown(Input.KEY_D);
    	boolean inputJump  = i.isKeyPressed(Input.KEY_SPACE);
    	
    	// both pressed = no movement in that direction axis
    	if (inputRight && inputLeft)
    		return;
    	
    	//right ----->
    	else if (inputRight)
            player.moveRight();
        
    	// left <------
        else if (inputLeft)
        	player.moveLeft();
    	
        if (inputJump && !player.isMovingVertically())
        	player.jump();
    }
 
}
package game.controller;
 
import game.character.Player;
 
import org.newdawn.slick.Input;
 
public class MouseAndKeyBoardPlayerController extends PlayerController {
 
    public MouseAndKeyBoardPlayerController(Player player) {
        super(player);
    }
 
    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i,delta);
    }
 
    private void handleKeyboardInput(Input i, int delta){
        
    	// see which keys are being pressed each frame
    	boolean inputLeft  = i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT);
    	boolean inputRight = i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT);
    	boolean inputUp    = i.isKeyDown(Input.KEY_W) || i.isKeyDown(Input.KEY_UP);
    	boolean inputDown  = i.isKeyDown(Input.KEY_S) || i.isKeyDown(Input.KEY_DOWN);
    	
    	// input to movement logic
    	// left <------
    	if (inputLeft)
        {
        	player.moveLeft(delta);
        	
        	if (inputDown)
        		player.moveDown(delta);
        	
        	else if (inputUp)
        		player.moveUp(delta);
        }
        
    	//right ----->
        else if (inputRight)
        {
            player.moveRight(delta);
            
            if (inputDown)
        		player.moveDown(delta);
        	
        	else if (inputUp)
        		player.moveUp(delta);
        }
        
    	// down
        else if (inputDown)
     		player.moveDown(delta);
     	
    	// up
     	else if (inputUp)
     		player.moveUp(delta);
    	/*
    	if(i.isKeyDown(Input.KEY_SPACE))
    	{
            player.jump();
        }*/
    }
 
}
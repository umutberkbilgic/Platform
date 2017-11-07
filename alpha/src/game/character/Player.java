package game.character;
 
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
 
public class Player extends Character
{
	
	private static final float MULTIPLIER = 0.5f;
	
    public Player(float x, float y) throws SlickException
    {
        super(x,y);
        this.setSprite(new Image("data/img/placeholder_sprite.png"));
        
        setMovingAnimation(new Image[]
        		{
        		new Image("data/img/characters/player/player_right_step_right.png"),
        		new Image("data/img/characters/player/player_right.png"),
                new Image("data/img/characters/player/player_right_step_left.png"),
                new Image("data/img/characters/player/player_right.png")
                } ,125);
    }
    
    private boolean leftBoundaryValid(){
    	return (x >= 0);
    }
    
    private boolean upBoundaryValid(){
    	return (y >= 0);
    }
    
    private boolean rightBoundaryValid(){
    	return (x <= (1920 * (70/60f) - 56));	
    }
    
    private boolean downBoundaryValid(){
    	return (y <= 1080 * (70/60f) - 140);
    	
    }
  
    public void moveLeft(int delta)
    {
    	if (leftBoundaryValid())
    	{
    		lastTimeMoved = System.currentTimeMillis();
    		facing = Facing.LEFT;
        	x -= MULTIPLIER * delta;
    	}
    }
    
    public void moveRight(int delta)
    {
    	if (rightBoundaryValid())
    	{
    		lastTimeMoved = System.currentTimeMillis();
    		facing = Facing.RIGHT;
    		x += MULTIPLIER * delta;
    	}
    }
    
    public void moveUp(int delta)
    {
    	if (upBoundaryValid())
    		y -= MULTIPLIER * delta;
    }
    
    public void moveDown(int delta)
    {
    	if (downBoundaryValid())
    		y += MULTIPLIER * delta;
    }
    
    protected void setMovingAnimation(Image[] images, int frameDuration){
        movingAnimations = new HashMap<Facing, Animation>();
 
        //we can just put the right facing in with the default images
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
 
        // reverse the right facing animations to create the left facing ones
        Animation facingLeftAnimation = new Animation();
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
 
    }
    
    
    
    
    
    
    
    
    
    
    
}
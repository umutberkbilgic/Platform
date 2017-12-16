package game.character;
 
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
 
public class Player extends Character
{
	
	private static final float MULTIPLIER = 2.5f;
	private float y_speed;
	private final float y_boundary;
	private final float x_boundary;
	private boolean movingVertically = false;
	
    public Player(float x, float y, float scale) throws SlickException
    {
        super(x,y);
        this.setSprite(new Image("data/img/placeholder_sprite.png"));
        
        y_speed = (float) 0;
        
        System.out.println("Loading animation images.");
        
        Image[] leftImages = new Image[]{
        		new Image("data/img/characters/player/player_left_step_right.png"),
        		new Image("data/img/characters/player/player_left.png"),
                new Image("data/img/characters/player/player_left_step_left.png"),
                new Image("data/img/characters/player/player_left.png")
                };
        
        Image[] rightImages = new Image[]{
        		new Image("data/img/characters/player/player_right_step_right.png"),
        		new Image("data/img/characters/player/player_right.png"),
                new Image("data/img/characters/player/player_right_step_left.png"),
                new Image("data/img/characters/player/player_right.png")
                };
        
        setMovingAnimation(leftImages, rightImages, 250);
        
        System.out.println("Animations loaded for player.");
        
        y_boundary = (1080 * (1/scale) - 140);
        x_boundary = (1920 * (1/scale) - 70);
    }
  
    public void moveLeft()
    {
    	if (x >= 0)
    	{
    		lastTimeMoved = System.currentTimeMillis();
    		facing = Facing.LEFT;
        	x -= MULTIPLIER;
    	}
    }
    
    public void moveRight()
    {
    	if (x <= x_boundary)
    	{
    		lastTimeMoved = System.currentTimeMillis();
    		facing = Facing.RIGHT;
    		x += MULTIPLIER;
    	}
    }
    
    protected void setMovingAnimation(Image[] leftImages, Image[] rightImages, int frameDuration){
        movingAnimations = new HashMap<Facing, Animation>();
        movingAnimations.put(Facing.RIGHT, new Animation(rightImages, frameDuration));
        movingAnimations.put(Facing.LEFT, new Animation(leftImages, frameDuration));
 
    }
    
    public void jump()
    {
    	y_speed = (float) -10;
    }
    
    public void actGravity(float gravity){
    	if (y <= y_boundary) // pulled down, vertical speed increases
    	{
    		float nextYPos = y + MULTIPLIER + y_speed;
    		
    		if (nextYPos <= y_boundary)
    		{
    			y = nextYPos;
        		y_speed += gravity;
        		movingVertically = true;
    		}
    		else
    		{
    			y = y_boundary;
    			movingVertically = false;
    		}
    	}
    	else //stays down, vertical speed is 0
    		movingVertically = false;
    }
    
    public boolean isMovingVertically()
    {
    	return movingVertically;
    }   
}
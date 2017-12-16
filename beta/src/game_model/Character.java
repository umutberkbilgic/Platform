package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : Character class is the in-game character that the user operates.
 */


import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Character extends GameObject 
{
    protected HashMap <Facing,Image> sprites; // for visualization
    public float jumpedYPos;
    
    protected HashMap <Facing,Animation> movingAnimations; // also for visualization
    protected Facing facing;
    protected boolean moving = false;
    protected float acceleration = 1;
    protected float deceleration = 1;
    protected float maximumSpeed = 1;
    
    protected int jumpCounter = 0;
    
    public Character(float x, float y) throws SlickException
    {
        super(x,y);
        //in case we forget to set the image, we don't want the game to crash, but it still has to be obvious that something was forgotten
        setSprite(new Image("data/img/placeholder_sprite.png"));
        
        //default direction will be right
        facing = Facing.RIGHT;
    }
    
    protected void setMovingAnimation(Image[] images, int frameDuration){
        movingAnimations = new HashMap<Facing,Animation>();
        
        //we can just put the right facing in with the default images
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
        
        Animation facingLeftAnimation = new Animation();
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
    }
    
    public void kill()
    {
    	super.y_velocity = 0;  // dead rip
    }
    
    protected void setSprite(Image i){
        sprites = new HashMap<Facing,Image>();
        sprites.put(Facing.RIGHT, i);
        sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
    }
    
    public int getJumpCount(){
    	return jumpCounter;
    }
    
    public boolean getIsOutOfMap()
    {
    	return boundingShape.isOutOfMap;
    }
    
    public boolean isMoving(){
        return moving;
    }
    
    public void setMoving(boolean b){
        moving = b;
    }
    
    //move towards x_velocity = 0
    public void decelerate(int delta) {
        if(x_velocity > 0){
            x_velocity -= deceleration * delta;
            if(x_velocity < 0)
                x_velocity = 0;
        }
        else if(x_velocity < 0){
            x_velocity += deceleration * delta;
            if(x_velocity > 0)
                x_velocity = 0;
        }
    }
    
    public void jump()
    {
    	jumpedYPos = y;
    	
    	jumpCounter++;
    	
        if(onGround)
            y_velocity = -0.4f;
    }
    
    public boolean isOnGround()
    {
    	return onGround;
    }
    
    public void moveLeft(int delta){
        //if we aren't already moving at maximum speed
        if(x_velocity > -maximumSpeed){
            //accelerate
            x_velocity -= acceleration*delta;
            if(x_velocity < -maximumSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                x_velocity = -maximumSpeed;
            }
        }
        
        moving = true;
        facing = Facing.LEFT;
    }
    
    public void moveRight(int delta)
    {
        if(x_velocity < maximumSpeed)
        {
            x_velocity += acceleration * delta;
            
            if(x_velocity > maximumSpeed){
            	
                x_velocity = maximumSpeed;
            }
        }
        
        moving = true;
        facing = Facing.RIGHT;
    }
    
    public void render(float xDelta, float yDelta)
    {
        //draw a moving animation if we have one and we moved within the last 150 miliseconds
        if(movingAnimations != null && moving){
            movingAnimations.get(facing).draw(x-2-xDelta, y-2-yDelta);                
        }
        else{            
            sprites.get(facing).draw(x-2-xDelta, y-2-yDelta);          
        }
    }
    
    public float getX()
    {
    	return x;
    }
    
    public float getY()
    {
    	return y;
    }
    
    public void setCharX(float x){
    	this.x = x;
    }
    
    public void setCharY(float y){
    	this.y = y;
    }

}

package game_model;
/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : GameObject class is a generalization of the mutual properties of movable objects such as enemies and player itself.
 */

public abstract class GameObject {
    
	//location properties
    protected float x;
    protected float y;
    //to make the GameObjects collidable, a Hitbox object is used.
    protected Hitbox boundingShape;
    
   
    protected float    x_velocity = 0;
    protected float    y_velocity = 0;
    // the y coordinate of velocity has an upperbound 
    protected float    maximumFallSpeed = 1;

    protected boolean  onGround = true;
    
    public GameObject(float x, float y){
        this.x = x;
        this.y = y;
        
        // each tile is 32x32 pixels
        boundingShape = new Hitbox(x, y, 32, 32);
    }
    
    public void applyGravity(float gravity){
      //if we aren't already moving at maximum speed
        if(y_velocity < maximumFallSpeed){
            //accelerate
            y_velocity += gravity;
            if(y_velocity > maximumFallSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                y_velocity = maximumFallSpeed;
            }
        }
    }
    
    public float getYVelocity() {
        return y_velocity;
    }

    public void setYVelocity(float f){
        y_velocity = f;
    }
    
    public float getXVelocity(){
        return x_velocity;
    }
    
    public void setXVelocity(float f){
        x_velocity = f;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float f){
        x = f;
        updateBoundingShape();
    }
    
    public void setY(float f){
        y = f;
        updateBoundingShape();
    }
    
    public void updateBoundingShape(){
        boundingShape.update(x,y);
    }
    
    public boolean isOnGround(){
        return onGround;
    }
    
    public void setOnGround(boolean b){
        onGround = b;
    }
    
    public Hitbox getBoundingShape(){
        return boundingShape;
    }
    
}

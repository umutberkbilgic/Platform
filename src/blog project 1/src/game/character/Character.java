package game.character;
 
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
 
public abstract class Character {
 
    protected float x;
    protected float y;
    
    protected Facing facing;
    protected HashMap <Facing, Animation> movingAnimations;
    protected long lastTimeMoved;
    
    protected HashMap sprites;
 
    public Character(float x, float y) throws SlickException
    {
        this.x = x;
        this.y = y;
        
        facing = Facing.RIGHT;

        this.setSprite(new Image("data/img/placeholder_sprite.png"));
    }
    
    protected void setSprite(Image i){
        sprites = new HashMap<Facing,Image>();
        sprites.put(Facing.RIGHT, i);
        sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
    }
 
    public float getX(){
        return x;
    }
 
    public float getY(){
        return y;
    }
 
    public void render()
    {    	 
        // do moving animation if we havent moved in 50 ms
        if(lastTimeMoved + 50 > System.currentTimeMillis())
        {
            movingAnimations.get(facing).draw(x, y);                
        }
        else
        {            
            ((Image) sprites.get(facing)).draw(x, y);          
        }
    }
 
}
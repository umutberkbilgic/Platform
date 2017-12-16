package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : Player ?????
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Character {

    public Player(float x, float y) throws SlickException{
        super(x, y);
        
        setSprite(new Image("data/img/characters/player/player.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/characters/player/player_1.png"),
        							   new Image("data/img/characters/player/player_2.png"),
                                       new Image("data/img/characters/player/player_3.png"),
                                       new Image("data/img/characters/player/player_4.png")}
                                       ,125);
        
        boundingShape = new Hitbox(x, y, 25, 32);
        
        acceleration = 0.1f;
        maximumSpeed = 0.20f;
        maximumFallSpeed = 0.3f;
        deceleration = 0.1f;
    }
    
    public void updateBoundingShape()
    {
        boundingShape.update(x,y);
    }
    
    public boolean checkCollision( Block block)
    {
        if(block.getBoundingShape() != null){
            if(block.getBoundingShape().checkCollision(this.getBoundingShape()) && block.isFinishBlock )
            {
                return true;
            }
            //insert powerup collision here
        }
    return false;
}
}

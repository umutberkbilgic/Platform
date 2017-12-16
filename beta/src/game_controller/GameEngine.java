package game_controller;

import java.util.ArrayList;

import game_model.Level;
import game_model.GameObject;
import game_model.Block;
import game_model.Character;

public class GameEngine
{
    
    private final float gravity = 0.0015f; //TODO MAKE THIS CHANGEABLE
    
    public void handleGame(Level level, int delta){
        handleCharacters(level,delta);
    }
    
    private void handleCharacters(Level level, int delta){
        for(Character c : level.getCharacters()){
            
            //and now decelerate the character if he is not moving anymore
            if(!c.isMoving())
            {
                c.decelerate(delta);
            }
            
            handleGameObject(c,level,delta);
        }
    }
    
    private void handleGameObject(GameObject obj, Level level, int delta){
        
        //first update the onGround of the object
        obj.setOnGround(isOnGroud(obj,level.getBlocks()));
        
        //now apply gravitational force if we are not on the ground or when we are about to jump
        if(!obj.isOnGround() || obj.getYVelocity() < 0)
            obj.applyGravity(gravity * delta);
        else
            obj.setYVelocity(0);
        
        //calculate how much we actually have to move
        float x_movement = obj.getXVelocity() * delta;
        float y_movement   = obj.getYVelocity() * delta;
        
        //we have to calculate the step we have to take
        float step_y = 0;
        float step_x = 0;
        
        if(x_movement != 0){
            step_y = Math.abs(y_movement)/Math.abs(x_movement);
            if(y_movement < 0)
                step_y = -step_y;
            
            if(x_movement > 0)
                step_x = 1;
            else
                step_x = -1;
            
            if((step_y > 1 || step_y < -1) && step_y != 0){
                step_x = Math.abs(step_x)/Math.abs(step_y);
                if(x_movement < 0)
                    step_x = -step_x;
                if(y_movement < 0)
                    step_y = -1;
                else
                    step_y = 1;
            }
        }else if(y_movement != 0){
            //if we only have vertical movement, we can just use a step of 1
            if(y_movement > 0)
                step_y = 1;
            else
                step_y = -1;
        }
        
        //and then do little steps until we are done moving
        while(x_movement != 0 || y_movement != 0){
            
            //we first move in the x direction
            if(x_movement != 0){
                //when we do a step, we have to update the amount we have to move after this
                if((x_movement > 0 && x_movement < step_x) || (x_movement > step_x  && x_movement < 0)){
                    step_x = x_movement;
                    x_movement = 0;
                }else
                    x_movement -= step_x;
                
                //then we move the object one step
                obj.setX(obj.getX()+step_x);
                
                //if we collide with any of the bounding shapes of the Blocks we have to revert to our original position
                if(checkCollision(obj,level.getBlocks())){
                    
                   //undo our step, and set the velocity and amount we still have to move to 0, because we can't move in that direction
                    obj.setX(obj.getX()-step_x);
                    obj.setXVelocity(0);
                    x_movement = 0;
                }
                
            }
            //same thing for the vertical, or y movement
            if(y_movement != 0){
                if((y_movement > 0 && y_movement < step_y) || (y_movement > step_y  && y_movement < 0)){
                    step_y = y_movement;
                    y_movement = 0;
                }else
                    y_movement -= step_y;
                
                obj.setY(obj.getY()+step_y);
                
                if(checkCollision(obj,level.getBlocks())){
                    obj.setY(obj.getY()-step_y);
                    obj.setYVelocity(0);
                    y_movement = 0;
                    break;
                }
            }
        }
    }
    
    private boolean checkCollision(GameObject obj, Block[][] mapBlocks){
        //get only the Blocks that matter
        ArrayList<Block> Blocks = obj.getBoundingShape().getOccupiedBlocks(mapBlocks);
        for(Block t : Blocks){
            //if this Block has a bounding shape
            if(t.getBoundingShape() != null){
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape()) && t.getIsSolid())
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean isOnGroud(GameObject obj, Block[][] mapBlocks){
        //we get the Blocks that are directly "underneath" the characters, also known as the ground Blocks
        ArrayList<Block> Blocks = obj.getBoundingShape().getGroundBlocks(mapBlocks);
        
        //we lower the the bounding object a bit so we can check if we are actually a bit above the ground
        obj.getBoundingShape().move(0, 1);
        
        for(Block t : Blocks){
            //not every Block has a bounding shape (air Blocks for example)
            if(t.getBoundingShape() != null){
                //if the ground and the lowered object collide, then we are on the ground
                if(t.getBoundingShape().checkCollision(obj.getBoundingShape()) && t.getIsSolid()){
                    //don't forget to move the object back up even if we are on the ground!
                    obj.getBoundingShape().move(0, -1);
                    return true;
                }
            }
        }
        
        //and obviously we have to move the object back up if we don't hit the ground
        obj.getBoundingShape().move(0, -1);
        
        return false;
    }

}

package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : HitBox class is used in order to check collision. Hitbox has x and y properties which denote the coordinates of upperleft corner of 
 * 			the corresponding hitbox. With the inclusion of width and height properties, hitbox becomes a rectangle. 
 */

import java.util.ArrayList;

public class Hitbox {
    
    public float x;
    public float y;
    public float width;
    public float height;
    
    public boolean isOutOfMap = false;

    public Hitbox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // this method is to update the hitbox's location to a new location.
    public void update(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }
    
    //this method is to translate the hitbox x pixels horizontally and y pixels vertically.
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
    
    //this method checks collision with the hitbox given as parameter.
    public boolean checkCollision(Hitbox rect) {
        return !(rect.x > this.x+width || rect.x+rect.width < this.x || rect.y > this.y+height || rect.y+rect.height < this.y);
    }

    // returns a list of all blocks that hitbox occupies(intersects)
    public ArrayList<Block> getOccupiedBlocks(Block[][] Blocks) {
        ArrayList<Block> occupiedBlocks = new ArrayList<Block>();
        
        //we go from the left of the rect towards to right of the rect, making sure we round upwards to a multiple of 32 or we might miss a few Blocks
        for(int i = (int) x; i <= x+width+(32-width%32); i+=32){
            for(int j = (int) y; j <= y+height+(32-height%32); j+=32)
            {
                try{occupiedBlocks.add(Blocks[i/32][j/32]);}
                catch(ArrayIndexOutOfBoundsException  e)
                {
                	isOutOfMap = true;
                }
            }
        }
        return occupiedBlocks;
    }

    // returns the ground blocks...
    public ArrayList<Block> getGroundBlocks(Block[][] Blocks) {
        ArrayList<Block> BlocksUnderneath = new ArrayList<Block>();
        int j = (int) (y+height+1);
        
        for(int i = (int) x; i <= x+width+(32-width%32); i+=32)
        {
            try{BlocksUnderneath.add(Blocks[i/32][j/32]);}
            catch(ArrayIndexOutOfBoundsException  e)
            {
            	isOutOfMap = true;
            }
        }
        
        return BlocksUnderneath;
    }

}

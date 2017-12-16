package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : Block class helps to generalize the mutual properties of all types of blocks and 
 * 			the mutual operations that can be done with all types of blocks.
 */


public class Block {
    
    protected int x;
    protected int y;
    protected boolean isSolid; // helps to exclude collidable blocks.
    
    protected Hitbox boundingShape;
    
    protected boolean isFinishBlock;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        boundingShape = null;
        
        isFinishBlock = false;
    }
    
    public Hitbox getBoundingShape(){
        return boundingShape;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean getIsSolid()
    {
    	return isSolid;
    }

}

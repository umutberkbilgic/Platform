package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : Enemy class is the burden that user has to overcome in order to increase their score.
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Character {
	
	//properties
	private Difficulty difficulty;
	private int factor;
	/*private int counter = 0;
	private int prevCounter = -1;*/
	
	public Enemy(float x, float y, int factor) throws SlickException
	{
        super(x,y);
        
        this.factor = factor;
        
        difficulty = Difficulty.EASY;
        
        setSprite(new Image("data/img/characters/enemy/enemy_1.png"));
        
        setMovingAnimation(new Image[]{new Image("data/img/characters/enemy/enemy_1.png"),
        							   new Image("data/img/characters/enemy/enemy_1.png"),
                                       new Image("data/img/characters/enemy/enemy_1.png"),
                                       new Image("data/img/characters/enemy/enemy_1.png")}
                                       ,125);
        
        // make skins and animations for enemies
        
        boundingShape = new Hitbox(x, y, 26, 32);
        
        acceleration = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        deceleration= 0.001f;
    }
    
    public void updateBoundingShape()
    {
        boundingShape.update(x, y);
    }
    
    public void translate(int delta)
    {
    	/*// Enemy is going right to factor
    	if (counter != factor && counter > prevCounter)
    	{
    		prevCounter = counter;
    		this.moveRight(delta);
    		counter++;
    		
    		System.out.println("Counter: " + counter + " Prev: " + prevCounter);
    	}
    	
    	// Enemy is going left to zero
    	else if (counter != 0 && counter <= prevCounter)
    	{
    		prevCounter = counter;
    		this.moveLeft(delta);
    		counter--;
    	} 
    	
    	else if (counter == factor) counter -= 2;*/
    	
    	/*for(int counter = 0; counter < factor; counter++){
    		this.moveRight(delta);
    	}
    	for(int counter = 0; counter < factor; counter++){
    		this.moveLeft(delta);
    	}*/
    }    
}








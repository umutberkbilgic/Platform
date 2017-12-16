package view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game_controller.GameEngine;
import game_controller.KeyboardInputHandler;
import game_model.Enemy;
import game_model.GameData;
import game_model.Level;
import game_model.Player;

public class GameState extends BasicGameState
{
    private Level  level;
    public static String startinglevel;
    public static  int worldNo;
    public static  int levelNo;
    
    private GameData gd;
    
    private Player player;
    private KeyboardInputHandler keyboard;
    private GameEngine engine;
    
    private GameContainer container2;
    private StateBasedGame sbg2;
    
    public double gameDuration;
    public int jumpCount;
    
    public boolean hasEndedWithDeath = false;
    
    public GameState(String startingLevel)
    {
    	GameState.startinglevel = startingLevel;
    }
    
    public void setWorldNo(int worldNo)
    {
    	this.worldNo = worldNo;
    }
    
    public void setLevelNo(int levelNo)
    {
    	this.levelNo = levelNo;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException 
    {
    	container2 = container;
    	sbg2 = sbg;
        
    	player = new Player(0, 0); // 0, 0 is arbitrary since we will change the position according to
    	                           // ... start block anyways.
    
        Enemy enemy = new Enemy(40, 300, 2);
        
        // init level with the given starting level, the starting level will
    	// ... change during the reset phase.
        level = new Level(startinglevel, player);
        
        level.addCharacter(enemy);
        
        player.setCharX((level.startX+0.1f) * 32);
        player.setCharY((level.startY-0.1f) * 32);
        
        //enemy.setCharX((level.startX+1f) * 32);
        //enemy.setCharY((level.startY-5f) * 32);
        
        //and we create a controller, for now we use the MouseAndKeyBoardPlayerController
        keyboard = new KeyboardInputHandler(player);
        
        engine = new GameEngine();
        
        gd = new GameData();
        gd.startTimer();
    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException 
    {
    	Enemy enemy = (Enemy) level.getCharacters().get(1);
    	enemy.translate(delta);
    	
    	if(player.checkCollision(level.getFinishBlock())) 
    	{
        	// player finished
    		gd.endTimer();
    		
    		jumpCount = player.getJumpCount();
			gameDuration = gd.getDuration();
			
			sbg.enterState(-2);
        }
    	
    	// player is dead
    	if (player.getIsOutOfMap())
    	{
    		hasEndedWithDeath = true;
    		
    		player.kill();
    		
    		gd.endTimer();

    		if (player.getY() >= 775)
    		{
    			jumpCount = player.getJumpCount();
    			gameDuration = gd.getDuration();
    			sbg.enterState(-2);    			
    		}
    	}
    	
        //every update we have to handle the input from the player
    	keyboard.handleKeyboardInput(container.getInput(), delta);
        engine.handleGame(level, delta);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        g.scale(SpaceOut.SCALE, SpaceOut.SCALE);
        
        g.drawImage((new Image("data/img/backs/" + worldNo + "_back.png").getScaledCopy(1600, 800)), 0, 0);
        level.render();
    }
    
    public void reset() throws SlickException
    {
    	hasEndedWithDeath = false;
    	startinglevel = "level_" + worldNo + "_" + levelNo;
    	this.init(container2, sbg2);
    }

    public void keyPressed(int key, char code)
    {
        if(key == Input.KEY_ESCAPE)
            System.exit(0);
    }

    public int getID() {return 9;}
}
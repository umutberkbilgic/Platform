package view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//import game.Game;
//import game.state.LevelState;

public class SpaceOut extends StateBasedGame {
	
	  //set the window width and then the height according to an aspect ratio
    public static final int WIDTH  = 1920;
    public static final int HEIGTH = WIDTH / 16 * 9;
    public static final boolean FULLSCREEN = true;
 
    public static final float  SCALE     = (float) 0.85714285714;
    public static final String TITLE = "Space Out";
 
    public SpaceOut() 
    {
        super(TITLE);
    }
 
    public void initStatesList(GameContainer gc) throws SlickException 
    {
        // create a new level state which will handle in game stuff
        //addState(new LevelState("empty")); will need to merge
        this.enterState(0);
    }
 
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new SpaceOut());
 
         app.setDisplayMode(WIDTH, HEIGTH, FULLSCREEN);
         
         //app.setTargetFrameRate(240);
 
         app.start();
    }

}

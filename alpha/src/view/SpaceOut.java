package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//import game.Game;
//import game.state.LevelState;

public class SpaceOut extends StateBasedGame {
	
	  //set the window width and then the height according to an aspect ratio
	
    public static final int WIDTH  = 640;
    public static final int HEIGTH = WIDTH / 4 * 3;
    public static final boolean FULLSCREEN = true;
    public static final float  SCALE     = (float) 0.85714285714;
    public static final String TITLE = "Space Out";
 
    public SpaceOut() 
    {
        super(TITLE);
    }
 
    public void initStatesList(GameContainer gc) throws SlickException 
    {
        addState(new Login());
        addState(new SignUp());
        addState(new MainMenu());
        addState(new SettingsMenu());
        addState(new LeaderBoardMenu());
        addState(new AchievementMenu());
        addState(new SelectLevelMenu());
        this.enterState(0);
    }
 
    public static void main(String[] args) throws SlickException 
    {
         AppGameContainer app = new AppGameContainer(new SpaceOut());
         
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         int width = (int) screenSize.getWidth();
         int height = (int) screenSize.getHeight();
         app.setDisplayMode(width, height, FULLSCREEN);
         System.out.println(width + " " + height);
         
         //app.setTargetFrameRate(240);
 
         app.start();
    }

}

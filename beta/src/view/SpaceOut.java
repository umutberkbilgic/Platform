package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import account_handler.AccountManager;

public class SpaceOut extends StateBasedGame {
	
	//set the window width and then the height according to an aspect ratio
	
	public static int WIDTH;
	public static int HEIGTH;
	public static final boolean FULLSCREEN = true;
	public static float SCALE;
    public static final String TITLE = "Space Out";
    
    AccountManager manager;
 
    public SpaceOut() 
    {
        super(TITLE);
        
    }
 
    public void initStatesList(GameContainer gc) throws SlickException 
    {
    	manager = new AccountManager();
    	
        addState(new Login(manager));
        addState(new SignUp());
        addState(new MainMenu());
        addState(new SettingsMenu());
        addState(new LeaderBoardMenu(manager));
        //addState(new AchievementMenu());
        addState(new FinishState(manager));
        addState(new SelectLevelMenu());
        addState(new SelectWorldMenu());
        addState(new GameState("level_1_1"));
        addState(new ChangePasswordMenu(manager));
        
        
        //addState(new GameState(levelNames[0][0]));
        this.enterState(999);
    }
 
    public static void main(String[] args) throws SlickException 
    {
    	 AppGameContainer app = new AppGameContainer(new SpaceOut());
         
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         WIDTH = (int) screenSize.getWidth();
         HEIGTH = (int) screenSize.getHeight();
         
         SCALE = (float) (1.25*((double)WIDTH/HEIGTH));
         
         app.setDisplayMode(WIDTH, HEIGTH, FULLSCREEN);
 
         app.start();
    }
}

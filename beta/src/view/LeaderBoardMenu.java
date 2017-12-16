package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.input.Mouse;
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import account_handler.AccountManager;

public class LeaderBoardMenu extends BasicGameState {
	// Properties
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//to get the height and width of the screen
	private int screen_width = (int)screenSize.getWidth();//screen width assigned
	private int screen_height = (int) screenSize.getHeight();//screen height assigned
	private final int buttonwidth = (int)(screen_width / 4.92);
	private final int buttonheight = (int)(screen_height / 16.6);
	private Image background;
	private Button localButton;
	private Button globalButton;
	String local_results;
	String global_results;
	String result_to_display = "ud\nud";
	private Button backButton;
	
	private boolean flag = true;
	
	AccountManager manager;
	
	public LeaderBoardMenu(AccountManager manager)
	{
		this.manager = manager;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("data/img/login_screen_4.jpg");//background screen is same
		localButton = new Button(screen_width*(0.3), screen_height*(0.1), buttonwidth, buttonheight, "data/img/local.png", 1);
		globalButton = new Button(screen_width*(0.3) + buttonwidth, screen_height*(0.1), buttonwidth, buttonheight, "data/img/global.png", 1);
		backButton = new Button(screen_width*(0.1) , screen_height*(0.7), screen_width / 19.2, screen_height / 16.6, "data/img/back.png", 1);
		
		local_results = "xd\nxd";
		global_results = "xd\nxd";
		
		result_to_display = local_results; //default
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.drawImage(background.getScaledCopy(screen_width,screen_height),0,0);
		g.drawImage(localButton.getImage(), (int)localButton.getX(), (int)localButton.getY());//local leaderboard button drawed
		g.drawImage(globalButton.getImage(), (int)globalButton.getX(), (int)globalButton.getY());//global leaderboard button drawed
		g.drawImage(backButton.getImage(), (int)backButton.getX(), (int)backButton.getY());
		g.setFont(new TrueTypeFont(new Font("Comic Sans MS", 0, 30), false));
		drawString(g, result_to_display,(int)(screen_width*(0.3)),(int)(screen_height*(0.2)));
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException 
	{
		if (flag)
		{
			
			String name = manager.currentUserName;
			String scores = manager.getScores();
			
			name = "              \t\t" + name + "\n";
			
			String[] scoreList = scores.split(",");
			
			String scoreString = "World 1 - level 1: " + scoreList[0] + 
								 "\nWorld 1 - level 2: " + scoreList[1] + 
								 "\nWorld 2 - level 1: " + scoreList[2] +
								 "\nWorld 2 - level 2: " + scoreList[3] +
								 "\nWorld 3 - level 1: " + scoreList[4] +
								 "\nWorld 3 - level 2: " + scoreList[5];
			
			 
			
			local_results = name + scoreString;
			global_results = "Here be the globals\nLeaderboard";

			result_to_display = local_results;
			
			flag = false;		
		}
		
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = screen_height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and display proper string. global list or local list.
		if(input.isMousePressed(0)) 
		{
			if(localButton.isPositionContained(xpos, ypos))
				result_to_display = local_results;
			
			else if(globalButton.isPositionContained(xpos, ypos))
				result_to_display = global_results; 
			
			else if(backButton.isPositionContained(xpos, ypos))
			{
				flag = true;
				sbg.enterState(1);
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE)
			System.exit(0);
	}
	
	@Override
	public int getID() {
		return 2;
	}
	
	// thanks to https://stackoverflow.com/questions/4413132/problems-with-newline-in-graphics2d-drawstring
	void drawString(Graphics g, String text, int x, int y) 
	{
		if (text.contains("\n"))
		{
			for (String line : text.split("\n"))
				g.drawString(line, x, y += g.getFont().getLineHeight());
		}
	}

}
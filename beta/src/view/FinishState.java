package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import account_handler.AccountManager;
import view.Button;

public class FinishState extends BasicGameState{

	private Button playAgain;
	private Button mainMenu;
	
	int worldNo;
	int levelNo;
	
	Image background;
	Image title;
	
	Button playAgainButton;
	Button mainMenuButton;
	
	int height = 0;
	int width = 0;
	
	private double gameDuration;
	private boolean flag = true;
	private int score;
	private int jumpCount;
	
	private AccountManager manager;
	
	public FinishState(AccountManager manager)
	{
		/*this.worldNo = GameState.worldNo;
		this.levelNo = GameState.levelNo;*/
		this.manager = manager;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();           
        
		background = new Image("data/img/login_screen_4.jpg");
		title = new Image("data/img/space_out_title.png");
		playAgainButton = new Button(width*0.28, height*0.675, width*0.203, (height)*0.06, "data/img/play_again_button.png", height);
		mainMenuButton = new Button(width*0.58, height*0.675, width*0.203, (height)*0.06, "data/img/main_menu_button.png", height);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.drawImage(background.getScaledCopy(width, height), 0, 0);
		g.drawImage(title, (float)((width - title.getWidth()) / 2), 50f, (float)((width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(playAgainButton.getImage(), width*0.28f, height*0.675f);
		g.drawImage(mainMenuButton.getImage(), width*0.58f, height*0.675f);
		
		g.setFont(new TrueTypeFont(new Font("Comic Sans MS", 0, 30), false));
		
		String report = "Game duration: " + gameDuration + "\nScore: " + score;
		
		drawString(g, report,(int)(width*0.43), (int)(height*0.400) );
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException 
	{	
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and navigate to next state
		
		if (flag)
		{
			GameState previousGame = ((GameState)(sbg.getState(9)));
			
			worldNo = previousGame.worldNo;
	        levelNo = previousGame.levelNo;
	        
	        System.out.println("" + worldNo + "  asdasdad " + levelNo);
			
			jumpCount = previousGame.jumpCount;
			gameDuration = previousGame.gameDuration;
			
			if (previousGame.hasEndedWithDeath)
				score = 0;
			else
				score = 100000 / ((int)(gameDuration) + jumpCount); // lol
			
			manager.sendScore(score, worldNo, levelNo);
			
			flag = false;
		}
		
		if(input.isMousePressed(0)) 
		{
			if(playAgainButton.isPositionContained(xpos, ypos)) 
			{
				flag = true;
				sbg.enterState(4);
			}	
			else if(mainMenuButton.isPositionContained(xpos, ypos)) 
			{
				flag = true;
				sbg.enterState(1);
			}	
		}
	}
	
	 @Override
	 public void keyPressed(int key, char code)
	 {
		 if(key == Input.KEY_ESCAPE)
		 {
			 System.exit(0);
		 }
	 }

	@Override
	public int getID() 
	{
		return -2;
	}
	
	void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFont().getLineHeight());
	}
}

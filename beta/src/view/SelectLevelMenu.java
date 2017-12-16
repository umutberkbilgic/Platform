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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SelectLevelMenu extends BasicGameState {
	// Properties
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//to get the height and width of the screen
	private Image title;
	private int screen_width = (int)screenSize.getWidth();//screen width assigned
	private int screen_height = (int) screenSize.getHeight();//screen height assigned
	private Image background;
	private Button levelOneButton;
	private Button levelTwoButton;
	private Button playButton;
	private Button backButton;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		title = new Image("data/img/select_level_title.png");
		background = new Image("data/img/login_screen_4.jpg");//background screen is same
		levelOneButton = new Button(screen_width*(0.2), screen_height*(0.3), screen_width / 4.9, screen_height / 16.6, "data/img/level_1.png", 1);
		levelTwoButton = new Button(screen_width*(0.6), screen_height*(0.3), screen_width / 4.9, screen_height / 16.6, "data/img/level_2.png", 1);
		backButton = new Button(screen_width*(0.2) , screen_height*(0.5), screen_width / 19.2, screen_height / 16.6, "data/img/back.png", 1);
		playButton = new Button(screen_width*(0.6) , screen_height*(0.5), screen_width / 19.2, screen_height / 16.6, "data/img/play_mini.png", 1);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(screen_width,screen_height),0,0);
		g.drawImage(title, (float)((screen_width - title.getWidth()) / 2), 50f, (float)((screen_width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(levelOneButton.getImage(), (int)levelOneButton.getX(), (int)levelOneButton.getY());
		g.drawImage(levelTwoButton.getImage(), (int)levelTwoButton.getX(), (int)levelTwoButton.getY());
		g.drawImage(backButton.getImage(), (int)backButton.getX(), (int)backButton.getY());
		g.drawImage(playButton.getImage(), (int)playButton.getX(), (int)playButton.getY());
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = screen_height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and display proper string. global list or local list.
		if(input.isMousePressed(0)) {
			if(backButton.isPositionContained(xpos, ypos))
				sbg.enterState(4);
			else if(levelOneButton.isPositionContained(xpos, ypos))
			{
				((view.GameState)sbg.getState(9)).setLevelNo(1);
				
				((view.GameState)sbg.getState(9)).reset();
				sbg.enterState(9);
			}
			else if(levelTwoButton.isPositionContained(xpos, ypos))
			{
				((view.GameState)sbg.getState(9)).setLevelNo(2);
				
				((view.GameState)sbg.getState(9)).reset();
				sbg.enterState(9);
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
		return 5;
	}
}

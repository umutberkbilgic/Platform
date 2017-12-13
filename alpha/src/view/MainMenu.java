package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;

public class MainMenu extends BasicGameState{
	
	// Properties, constants
	private final int buttonwidth = 744;
	private final int buttonheight = 107;
	private Image background;
	private Image title;
	private int screen_height;
	private int screen_width;
	// Buttons:
	private Button playGameButton;
	private Button levelMakerButton;
	private Button leaderBoardButton;
	private Button achievementsButton;
	private Button settingsButton;


	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("data/img/login_screen_4.jpg");
		title = new Image("data/img/space_out_title.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screen_width = (int) screenSize.getWidth();
        screen_height = (int) screenSize.getHeight();
        // Create button objects
        playGameButton = new Button((int) (screen_width*(0.28)), (int) (screen_height*0.275), buttonwidth, buttonheight, "data/img/play.png", 0);
        levelMakerButton = new Button((int) (screen_width*(0.28)), (int) (screen_height*0.400), buttonwidth, buttonheight, "data/img/lever_maker.png", 0);
        leaderBoardButton = new Button((int) (screen_width*(0.28)), (int) (screen_height*0.525), buttonwidth, buttonheight, "data/img/leader.png", 0);
        achievementsButton  = new Button((int) (screen_width*(0.28)), (int) (screen_height*0.650), buttonwidth, buttonheight, "data/img/achi.png", 0);
        settingsButton  = new Button((int) (screen_width*(0.28)), (int) (screen_height*0.775), buttonwidth, buttonheight, "data/img/settings.png", 0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(screen_width, screen_height), 0, 0);
		g.drawImage(title, (float)((screen_width - title.getWidth()) / 2), 50f, (float)((screen_width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		// Draw button images
		g.drawImage(playGameButton.getImage(), (int)playGameButton.getX(), (int)playGameButton.getY());
		g.drawImage(levelMakerButton.getImage(), (int)levelMakerButton.getX(), (int)levelMakerButton.getY() );
		g.drawImage(leaderBoardButton.getImage(), (int) leaderBoardButton.getX(), (int)leaderBoardButton.getY());
		g.drawImage(achievementsButton.getImage(), (int) (screen_width*(0.28)), (int) (screen_height*0.650));
		g.drawImage(settingsButton.getImage(), (int) (screen_width*(0.28)), (int) (screen_height*0.775));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = screen_height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and navigate to next state
		if(input.isMouseButtonDown(0)) {
			if(playGameButton.isPositionContained(xpos, ypos))
				sbg.enterState(0); // change this to proper state id
			else if(levelMakerButton.isPositionContained(xpos, ypos))
				sbg.enterState(0); // change this to proper state id
			else if(leaderBoardButton.isPositionContained(xpos, ypos))
				sbg.enterState(0); // change this to proper state id
			else if(achievementsButton.isPositionContained(xpos, ypos))
				sbg.enterState(0); // change this to proper state id
			else if(settingsButton.isPositionContained(xpos, ypos))
				sbg.enterState(0); // change this to proper state id
			}
	}
	
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE)
			System.exit(0);
	}	

	@Override
	public int getID() {
		return 1;
	}

}


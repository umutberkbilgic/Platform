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

public class MainMenu extends BasicGameState{
	// Properties
	Image background;
	Image title;
	int height = 0;
	int width = 0;
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
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        playGameButton = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/play.png", 1, 0);
        levelMakerButton = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/lever_maker.png", 1, 0);
        leaderBoardButton = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/leader.png", 1, 0);
        achievementsButton  = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/achi.png", 1, 0);
        settingsButton  = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/settings.png", 1, 0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(width, height), 0, 0);
		g.drawImage(title, (float)((width - title.getWidth()) / 2), 50f, (float)((width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(playGameButton.getImage(), (int) (width*(0.28)), (int) (height*0.275));
		g.drawImage(levelMakerButton.getImage(), (int) (width*(0.28)), (int) (height*0.400));
		g.drawImage(leaderBoardButton.getImage(), (int) (width*(0.28)), (int) (height*0.525));
		g.drawImage(achievementsButton.getImage(), (int) (width*(0.28)), (int) (height*0.650));
		g.drawImage(settingsButton.getImage(), (int) (width*(0.28)), (int) (height*0.775));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
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
	public int getID() {
		return 1;
	}

}

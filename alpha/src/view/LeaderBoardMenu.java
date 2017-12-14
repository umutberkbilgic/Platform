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

public class LeaderBoardMenu extends BasicGameState {
	Image background;
	Image title;
	int height = 0;
	int width = 0;
	private Button localButton;
	private Button globalButton;
	public void switchView(){

	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("data/img/login_screen_4.jpg");//background screen is same
		title = new Image("data/img/space_out_title.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//to get the height and width of the screen
		width = (int)screenSize.getWidth();//screen width assigned
		height = (int) screenSize.getHeight();//screen height assigned
		localButton = new Button(width*(0.28), height*(0.575),width*(0.16), height*(0.09), "data/img/local.png", 1,0);//this image will change
		globalButton = new Button(width*(0.28), height*(0.575),width*(0.16), height*(0.09), "data/img/global.png", 1,0);//this image will change

	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(background.getScaledCopy(width,height),0,0);
		g.drawImage(title,(float)((width - title.getWidth())/ 2), 50f, (float)((width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f,(float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(localButton.getImage(), (int) (width*(0.28)), (int) (height*0.400));//local leaderboard button drawed
		g.drawImage(globalButton.getImage(), (int) (width*(0.28)), (int) (height*0.525));//global leaderboard button drawed

	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
		// TODO Auto-generated method stub

	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}

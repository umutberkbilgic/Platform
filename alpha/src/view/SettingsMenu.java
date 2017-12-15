package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.TextField;


public class SettingsMenu  extends BasicGameState{
	// Properties
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//to get the height and width of the screen
	private Image background;
	private Image title;
	int screen_width = (int)screenSize.getWidth(); //screen width assigned
	int screen_height = (int) screenSize.getHeight();//screen height assigned
	private int buttonwidth = (int)(screen_width / 4.92);
	private int buttonheight = (int)(screen_height / 16.6);
	private Button changeSkinButton;
	private Button changePasswordButton;
	private Button changeUsernameButton;
	private Button[] skinsList;
	private Button changeButton;
	private Image currentpass;
	private Image newpass;
	private Image newpassagain;
	private Image desiredname;
	private TextField currentpassfield;
	private TextField newpassfield;
	private TextField newpassagainfield;
	private TextField desirednamefield;
	private boolean skins_switch;
	private boolean pass_switch;
	private boolean usrname_switch;
	private Button backButton;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		skins_switch = pass_switch = usrname_switch = false;
		background = new Image("data/img/login_screen_4.jpg");//background screen is same
		title = new Image("data/img/space_out_title.png");
		changeSkinButton = new Button(screen_width*(0.1), screen_height*(0.3),buttonwidth, buttonheight, "data/img/change_skin.png", 1);
		changePasswordButton = new Button(screen_width*(0.1), screen_height*(0.4),buttonwidth, buttonheight, "data/img/change_password.png", 1);
		changeUsernameButton = new Button(screen_width*(0.1), screen_height*(0.5),buttonwidth, buttonheight, "data/img/change_username.png", 1);
		backButton = new Button(screen_width*(0.1) , screen_height*(0.7), screen_width / 19.2, screen_height / 16.6, "data/img/back.png", 1);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(screen_width, screen_height), 0, 0);
		g.drawImage(title, (float)((screen_width - title.getWidth()) / 2), 50f, (float)((screen_width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(changeSkinButton.getImage(), (int)changeSkinButton.getX(), (int)changeSkinButton.getY());
		g.drawImage(changePasswordButton.getImage(), (int)changePasswordButton.getX(), (int)changePasswordButton.getY());
		g.drawImage(changeUsernameButton.getImage(), (int)changeUsernameButton.getX(), (int)changeUsernameButton.getY());
		g.drawImage(backButton.getImage(), (int)backButton.getX(), (int)backButton.getY());
		//renderThings(g, skins_switch, pass_switch, usrname_switch);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = screen_height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and navigate to next state
		if(input.isMousePressed(0)) {
			if(backButton.isPositionContained(xpos, ypos))
				sbg.enterState(1);
		}	
	}
	
	@Override
	public void keyPressed(int key, char code){
		if(key == Input.KEY_ESCAPE)
			System.exit(0);
	}
	
	public void renderThings(Graphics g, boolean skins_switch, boolean pass_switch, boolean usrname_switch ) {
		if( skins_switch) {
			// there be skin buttons and change button
		}
		else if( pass_switch) {
		}
		else if(usrname_switch) {}
	}
	
	@Override
	public int getID() {
		return 3;
	}

}

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
import account_handler.CloudQueryInterface;

public class SignUp extends BasicGameState 
{
	AccountManager mgr;
	String usernameI;
	String psswdI;
	boolean inputValid;
	Image background;
	Image title;
	Image usernamePrompt;
	Image passwordPrompt;
	TextField username;
	TextField password;
	int height = 0;
	int width = 0;
	private Button signUpButton;
	private Button alreadySignedUpButton;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("data/img/login_screen_4.jpg");
		title = new Image("data/img/space_out_title.png");
		usernamePrompt = new Image("data/img/usernamePrompt.png");
		passwordPrompt = new Image("data/img/passwordPrompt.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        username = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.425), title.getWidth()*2/3, 60);
        password = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.675), title.getWidth()*2/3, 60);
        
        signUpButton = new Button(width*(0.2), height*0.8, width*0.16, (height)*0.09, "data/img/signUpButton.png", height);
        alreadySignedUpButton = new Button(width*(0.6), height*0.8, width*0.203, (height)*0.06, "data/img/have_account_button.png", height);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(width, height), 0, 0);
		g.drawImage(title, (float)((width - title.getWidth()) / 2), 50f, (float)((width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(usernamePrompt.getScaledCopy(width/6,height/12), width/10, (float)(5*height/16+0.1*height));
		g.drawImage(passwordPrompt.getScaledCopy(width/6,height/12), width/10, (float) (height*(0.660)));
		
		g.drawImage(signUpButton.getImage(), (int) signUpButton.getX(), (int)signUpButton.getY());
		g.drawImage(alreadySignedUpButton.getImage(), (int) alreadySignedUpButton.getX(), (int)alreadySignedUpButton.getY());

		username.render(gc, g);
		password.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
		Input input = gc.getInput();
		double xpos = Mouse.getX();
		double ypos = height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
		// Check for button click and navigate to next state
		if(input.isMousePressed(0)) {
			if(signUpButton.isPositionContained(xpos, ypos)) {
				usernameI = username.getText();
	        	psswdI = password.getText();
	        	
	        	mgr = new AccountManager();
	        	
	        	inputValid = mgr.checkSignUpData(usernameI, psswdI);
	        	
				if(inputValid)
					sbg.enterState(1); // change this to proper state id
			}	
			
			if(alreadySignedUpButton.isPositionContained(xpos, ypos))
			{
				sbg.enterState(0);
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
		return 999; //can be changed
	}
}
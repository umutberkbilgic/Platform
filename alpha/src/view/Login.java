package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Login extends BasicGameState {
	Image background;
	Image title;
	Image usernamePrompt;
	Image passwordPrompt;
	TextField username;
	TextField password;
	int height = 0;
	int width = 0;
	private Button loginButton;
	private Button signUpButton;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("data/img/login_screen_4.jpg");
		title = new Image("data/img/space_out_title.png");
		usernamePrompt = new Image("data/img/usernamePrompt.png");
		passwordPrompt = new Image("data/img/passwordPrompt.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        username = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.325), title.getWidth()*2/3, 60);
        password = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 45), false), (int) (width*(0.28)), (int) (height*0.575), title.getWidth()*2/3, 60);
        loginButton = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/loginButton.png", height, height);
        signUpButton = new Button(width*(0.28), height*0.575, width*0.16, (height)*0.09, "data/img/signUpButton.png", height, height);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background.getScaledCopy(width, height), 0, 0);
		g.drawImage(title, (float)((width - title.getWidth()) / 2), 50f, (float)((width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
		g.drawImage(usernamePrompt.getScaledCopy(width/6,height/12), width/10, 5*height/16);
		g.drawImage(passwordPrompt.getScaledCopy(width/6,height/12), width/10, (float) (height*(0.560)));
		loginButton.draw(g);
		signUpButton.draw(g);

		username.render(gc, g);
		password.render(gc, g);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(loginButton.isPressed()){
			System.out.println(username.getText());
		}
		else if(signUpButton.isPressed()){
			
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
	public int getID() {
		return 0;
	}
}

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

public class ChangeUsrMenu extends BasicGameState {
	// Properties
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//to get the height and width of the screen
	private Image background;
	private Image title;
	int screen_width = (int)screenSize.getWidth(); //screen width assigned
	int screen_height = (int) screenSize.getHeight();//screen height assigned
	private int buttonwidth = (int)(screen_width / 4.92);
	private int buttonheight = (int)(screen_height / 16.6);
	private Button changeButton;
	private Button backButton;
	private TextField desirednametf;


	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("data/img/login_screen_4.jpg");//background screen is same
		title = new Image("data/img/space_out_title.png");
		changeButton = new Button(screen_width*(0.7), screen_height*(0.7),buttonwidth, buttonheight, "data/img/change.png", 1);
		backButton = new Button(screen_width*(0.1) , screen_height*(0.7), screen_width / 19.2, screen_height / 16.6, "data/img/back.png", 1);
		desirednametf = new TextField(gc,  new TrueTypeFont(new Font("Comic Sans MS", 0, 30), false), (int) (screen_width*(0.55)), (int) (screen_height*0.35), title.getWidth()/5, title.getHeight()/3);
			
	}

		@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			g.drawImage(background.getScaledCopy(screen_width, screen_height), 0, 0);
			g.drawImage(title, (float)((screen_width - title.getWidth()) / 2), 50f, (float)((screen_width - title.getWidth())/2 + title.getWidth()), (float)(50 + title.getHeight()), 0f, 0f, (float)(title.getWidth()), (float)(title.getHeight()));
			g.setFont(new TrueTypeFont(new Font("Comic Sans MS", 0, 30), false));
			g.drawString("Desired Username:",(int)(screen_width*(0.4)),(int)(screen_height*(0.35)) );
			g.drawImage(changeButton.getImage(), (int)changeButton.getX(), (int)changeButton.getY());
			g.drawImage(backButton.getImage(), (int)backButton.getX(), (int)backButton.getY());
			desirednametf.render(gc, g);
		}

		@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg) throws SlickException {
			Input input = gc.getInput();
			double xpos = Mouse.getX();
			double ypos = screen_height - Mouse.getY(); // since for graphic elements y0 is at top, for mouse y0 is at bottom
			// Check for button click and navigate to next state
			if(input.isMousePressed(0)) {
				if(backButton.isPositionContained(xpos, ypos)) {
					desirednametf.setText("");
					input.clearMousePressedRecord();
					sbg.enterState(3);
				}
				else if(changeButton.isPositionContained(xpos, ypos)) {
					desirednametf.setText("");
					input.clearMousePressedRecord();
					sbg.enterState(1);
				}
			}
		}

		@Override
	public int getID() {
			return 9;
		}
	}
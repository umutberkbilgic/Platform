package game_controller;

import account_handler.AccountManager;
import game_model.GameModel;

public class GameController {
	GameModel gameModel;
	GameEngine gameEngine;
	AccountManager accMgr;
	
	public void init()
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
	
	public boolean startGame()
	{
		return false;
		
	}
	
	public boolean pauseGame()
	{
		return false;
		
	}
	
	public void exitGame(boolean gameFinished)
	{
		//save game if game is finishd before exiting
		if(gameFinished){
			//TODO
		}
		// no need to save if the game is interrupted
		//TODO
	}

}

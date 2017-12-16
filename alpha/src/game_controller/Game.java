package game_controller;

public class Game {
	
	private double score;
	private double duration;
	private int stateID;
	private int inGameState;
	
	//constructor
	public Game(){
		score = 0;
		duration = 0;
		// later initialize stateID currentInGameState
	}
	
	//returns the old in-game state
	public int changeInGameState(int newState){
		int temp = inGameState;
		inGameState = newState;
		return temp;
	}

}

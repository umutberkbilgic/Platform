package game_model;

import java.util.Date;

public class GameData {
	
	// ctesi yazilacak
	
	private int score;
	private float duration; // in seconds
	private long start;
	private long end;
	
	public GameData() {
		score = 0;
		duration = 0.0f;
	}
	
	public void startTimer() {
		start = System.currentTimeMillis();
	}
	
	public void endTimer() {
		end = System.currentTimeMillis();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDuration() {
		System.out.println((end - start)/1000.0);
		return (end - start)/1000.0;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

}
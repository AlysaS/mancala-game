package code;

public class Pit {
	private int marbles;
	
	public Pit(int marbles) {
		this.marbles = marbles;
	}
	
	public int getMarbleCount() {
		return marbles;
	}
	

	public void emptyPit() {
		this.marbles = 0;
	}
	
	public void addMarble() {
		marbles++; 
	}
	
}

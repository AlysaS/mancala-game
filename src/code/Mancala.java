package code;


public class Mancala {
	private int mancala;
	
	public Mancala() {
		mancala = 0; 
	}
	
	public int getMarbleCount() {
		return mancala;
	}
	
	public void addMarbles(int numMarbles) {
		this.mancala += numMarbles; 
	}
	

}

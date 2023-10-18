package code;

public class Player {
	private String name;
	private Mancala mancala;
	private Pit[] pits;
	
	
	public Player(String name) {
		this.name = name; 
		mancala = new Mancala(); 
		pits = new Pit[6];
	}
	
	public void initializePits(int count) {
		for(int i=0; i< pits.length; i++) {
			pits[i] = new Pit(count);
		}
	}
	
	public String getName() {
		return name; 
	}
	
	public int getMancalaCount() {
		return mancala.getMarbleCount(); 
	}
	
	public void addMarblesToMancala(int marbles) {
		mancala.addMarbles(marbles);
	}
	
	public void addMarbleToPit(int pit) {
		pits[pit -1].addMarble();
	}
	
	public Integer getPitMarbleCount(Integer pit) {
		return pits[pit -1].getMarbleCount();
	}
	
	public void emptyPit(Integer pit) {
		pits[pit -1].emptyPit();
	}
	
	public int totalMarblesOnSide() {
		int count = 0;
		for( Pit pit: pits) {
			count += pit.getMarbleCount();
		}
		
		return count;
	}
	
	
	public StringBuilder showPitsMyPerspective() {
		StringBuilder view = new StringBuilder();
		view.append("\n\n \t\t\t");
		
		for(int i=0; i<pits.length; i++) {
			view.append(pits[i].getMarbleCount() + "\t");
		}
		
		view.append("\t" + getMancalaCount());
		view.append("\n\t\t");
		
		for(int i= 1; i <= 6; i++) {
			view.append("\tpit" + i);
		}
		
		view.append("\t\t"+ getName()+"'s Mancala");
		
		return view;
	}
	
	public StringBuilder showPitsOpponentsPerspective() {
		StringBuilder view = new StringBuilder();
		
		view.append("\t"+ getName() + "'s Mancala");
		
		for(int i = 6; i > 0; i--) {
			view.append("\tpit" + i);
		}
		
		view.append("\n\t"+ getMancalaCount()+ "\t\t");
		
		for(int i=5; i >=0; i--) {
			view.append(pits[i].getMarbleCount() + "\t");
		}
		
		
		return view;
		
	}
	
	
}

package code;

import java.util.Scanner; 

public class Main {
	
	public static void main(String[] args) {
	
		String[] names = new String[2];
		Scanner user = new Scanner( System.in);
		String winner = null;
	
		System.out.println("Mancala Game! \nEnter 2 players names to begin: ");
		
		for( int i=0; i< 2; i++) {
			System.out.print("Player " +(i+1)+": ");
			names[i] = user.nextLine(); 
		}
		
		MancalaGame mg = new MancalaGame(names);
		winner = mg.playGame();
		
		if(winner == "tie") {
			System.out.println("This game ended in a tie!");
		}else if(winner == null) {
			System.out.println("An error has occured. Please try again later!");
		}else {
			System.out.println("The winner of this games is " + winner + "!");
		}
		
	
	}
}
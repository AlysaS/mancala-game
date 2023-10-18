package code;

import java.util.Scanner;

public class MancalaGame {
	private Player[] players;
	
	public MancalaGame(String[] names) {
		if(names.length != 2) {
			throw new IllegalArgumentException("Only 2 players can play the game.");
		}
		
		players = new Player[2];
		
		for(int i=0; i< players.length; i++) {
			players[i] = new Player(names[i]);
			players[i].initializePits(4);
		}
		
	}
	

	public void provideViewOfBoard(Player currentPlayer, Player otherPlayer) {
		
		System.out.println("Board from " + currentPlayer.getName() + "'s perspective:\n");
		System.out.println(otherPlayer.showPitsOpponentsPerspective());
		System.out.println(currentPlayer.showPitsMyPerspective());
		
	}
	
	
	public String playGame() {
		Player currPlayer = players[0];
		Player otherPlayer = players[1];
		Player player;
		Scanner user = new Scanner(System.in); 
		int userPick;
		String winner = "none"; 
		
		do {
			provideViewOfBoard(currPlayer, otherPlayer);
			
			System.out.println(currPlayer.getName() + "'s turn:");
			System.out.println("Marbles get distributed from your pit(bottom row) moving counter-clockwise until the the marbles run out.");
			
			do{
				System.out.print("From which pit would you like to remove marbles from? : ");
				userPick = user.nextInt();
			}while(userPick<=0 || userPick > 6 || (currPlayer.getPitMarbleCount(userPick) == 0));
			
			
			
			if(distributeMarbles(currPlayer, otherPlayer, userPick)) { 
			player = currPlayer;
			currPlayer = otherPlayer;
			otherPlayer = player; 
			}
			
			
			if(currPlayer.totalMarblesOnSide() == 0 || otherPlayer.totalMarblesOnSide() == 0) {
				endGame();
				winner = determineWinner();
			}
				
			
		}while(winner.equals("none"));
		
		return winner;
	}
	
	public void endGame() {
		int marbles;
		
		for(Player player: players) {
			marbles = player.totalMarblesOnSide();
			if(marbles != 0) {
				player.addMarblesToMancala(player.totalMarblesOnSide());
				for(int i=1; i<=6; i++) {
					player.emptyPit(i);
				}
			}
		}
		
		System.out.print("Game Over!");
		System.out.println(players[1].showPitsOpponentsPerspective());
		System.out.println(players[0].showPitsMyPerspective());
		
	}
	
	public String determineWinner() {
		int player1 = players[0].getMancalaCount();
		int player2 = players[1].getMancalaCount();
		
		if(player1 > player2) {
			return players[0].getName();
		}else if(player1 < player2) {
			return players[1].getName();
		}else {
			return "tie";
		}
	}
	
	
	/**
	 * This method will distribute the marbles and return true. 
	 * If the last marble was inserted into the mancala the method will return false so that the player can get a free turn.  
	 */
	public boolean distributeMarbles(Player currPlayer,Player otherPlayer, int startingPit) {
		int qtyMarbles = currPlayer.getPitMarbleCount(startingPit);
		currPlayer.emptyPit(startingPit);
		

		int currentPit = startingPit;
		
		while(qtyMarbles > 0) {
			currentPit++;
			
			if(currentPit < 14) {
				if(currentPit <= 6) {
					currPlayer.addMarbleToPit(currentPit);
				}else if(currentPit == 7) {
					currPlayer.addMarblesToMancala(1);
				}else if(currentPit > 7){
					otherPlayer.addMarbleToPit(currentPit -7);
				}
				
				qtyMarbles--;
				
			}else {
				currentPit = 0;
			}
		}
		
		if(currentPit == 7) {
			return false;
		}else if( currentPit < 7 && currPlayer.getPitMarbleCount(currentPit) == 1){
			int marbles = otherPlayer.getPitMarbleCount(6 - currentPit + 1) + 1;
			otherPlayer.emptyPit(6 - currentPit + 1);
			currPlayer.emptyPit(currentPit);
			
			currPlayer.addMarblesToMancala(marbles);
		}
		
		return true;
	}
		
		
}

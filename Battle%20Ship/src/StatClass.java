import game.Game;
import players.Player;
import players.ShotRandomAI;
import players.ShotSelectiveAI;
import players.ShotSelectiveRandomClusterAI;
import players.ShotSelectiveSuperiorAI;

public class StatClass {
	
	private static final int runs = 5000;	//Number of times to be run
	
	public static void main(String[] args) {
		//Players to fight, Recommend not choosing humans
		Player p1 = new ShotSelectiveAI();
		Player p2 = new ShotSelectiveSuperiorAI();
		
		//Times player 1 or 2 win
		int p1Wins = 0;
		int p2Wins = 0; 
		
		//Console output for timing
		int percent = 1;
		
		//Run for statistics
		for(int i = 0; i < runs; i++)
		{
			//Print out every 1%
			if(i % runs/100 == 0)
			{
				System.out.println(percent);
				percent++;
			}
			
			//Create the game and run it
			Game battleship = new Game(p1, p2);
			
			//Run the game
			battleship.begin();
			battleship.fight();
			battleship.end();
			
			//Get the winner
			boolean winner = battleship.getWinner();
			
			//Determine the winner
			if(winner)
			{
				p2Wins++;
			}
			else
			{
				p1Wins++;
			}
		}
		
		//Print out the winner
		System.out.println(p1Wins + " " + p2Wins);
	}
}

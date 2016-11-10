import game.Game;
import players.Human;
import players.Player;
import players.ShotRandomAI;
import players.ShotSelectiveAI;
import players.ShotSelectiveLowestClusterAI;
import players.ShotSelectiveRandomClusterAI;
import players.ShotSelectiveSuperiorAI;

public class TestClass {
	
	public static final int runs = 200;
	
	public static void main(String[] args) {
		Player p1 = new ShotSelectiveAI();
		Player p2 = new ShotSelectiveSuperiorAI();
		
		int p1Wins = 0;	//12 wins as RandomAI
		int p2Wins = 0; //188 wins as SelectiveAI
		
		int percent = 1;
		
		for(int i = 0; i < runs; i++)
		{
			if(i % runs/100 == 0)
			{
				System.out.println(percent);
				percent++;
			}
			
			Game battleship = new Game(p1, p2);
			battleship.begin();
			battleship.fight();
			battleship.end();
			
			boolean winner = battleship.getWinner();
			
			if(winner)
			{
				p2Wins++;
			}
			else
			{
				p1Wins++;
			}
		}
		
		System.out.println(p1Wins + " " + p2Wins);
	}
}

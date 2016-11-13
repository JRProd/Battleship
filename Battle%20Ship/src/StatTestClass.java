import game.Board;
import game.Constants;
import game.PieceOverlapException;
import players.Player;
import players.ShotRandomAI;

public class StatTestClass {
	
	static long runs = 1000000000;
	static long[][] statBoard = new long[Constants.SIZE][Constants.SIZE];
	
	public static void main(String[] args) {
		Player player = new ShotRandomAI();
		
		Board gameBoard = new Board();
		
		int onePercent = (int) (runs/100);
		
		int percent = 1;
		
		for(int i = 0; i < runs; i++)
		{
			if(i % onePercent == 0)
			{
				System.out.println(percent);
				percent++;
			}
			
			gameBoard = new Board();
			
			boolean placedWell = false;
			do
			{
				try {
					gameBoard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CARRIER_NAME));
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship falls outside the board. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship overlaps another piece. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			
			placedWell = false;
			do
			{
				try {
					gameBoard.placeShip(player.chooseShipLocation(Constants.DEFAULT_BATTLESHIP_NAME));
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship falls outside the board. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship overlaps another piece. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			
			placedWell = false;
			do
			{
				try {
					gameBoard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CRUISER_NAME));
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship falls outside the board. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship overlaps another piece. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			
			placedWell = false;
			do
			{
				try {
					gameBoard.placeShip(player.chooseShipLocation(Constants.DEFAULT_SUBMARINE_NAME));
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship falls outside the board. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship overlaps another piece. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			placedWell = false;
			do
			{
				try {
					gameBoard.placeShip(player.chooseShipLocation(Constants.DEFAULT_PATROLBOAT_NAME));
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship falls outside the board. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player.prompt("You ship overlaps another piece. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			calcSquare(gameBoard);
		}
		
		for(int i = 0; i < Constants.SIZE; i++)
		{
			for(int j = 0; j < Constants.SIZE; j++)
			{
				double avg = statBoard[j][i] / (double)runs;
				System.out.printf("%.5f ", avg);
				
			}
			System.out.println();
		}
	}
	
	public static void calcSquare(Board b)
	{
		for(int i = 0; i < Constants.SIZE; i++)
		{
			for(int j = 0; j < Constants.SIZE; j++)
			{
				char c = b.getCoord(i, j);
				if(c != '~')
				{
					statBoard[j][i] += 1;
				}
			}
		}
	}
}

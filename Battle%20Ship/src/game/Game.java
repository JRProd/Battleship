package game;

import players.Player;

public class Game {
	//Board where player 1 sees his ships and his enemies shots
	Board player1Board;
	//Board where player 1 sees his shots and hits
	Board player1EnemyBoard;
	
	//Board where player 2 sees his ships and his enemies shots
	Board player2Board;
	//Board where player 2 sees his shots and hits
	Board player2EnemyBoard;
	
	Player player1;
	Player player2;
	
	//False is player1, True is player2
	private boolean winner;
	
	//Init visual breakers
	private String boardBorder;
	private String boardBreak;
	
	//Function to set value to break
	private void initBoardBreak()
	{
		//Default values
		boardBorder = "########";
		boardBreak = "# ----";
		
		//Size based on board size
		for(int i = 0; i < Constants.SIZE*3; i++)
		{
			boardBorder += "#";
			boardBreak += "-";
		}
		
		//Add the outside boarder
		boardBreak += " #";
	}
	
	//Method to place the ship onto the board
	private void playerPlaceShips(Player player, Board gameboard)
	{
		//Prints board for player to see
		player.prompt(gameboard.printBoard());
		
		//Check prompt
		boolean placedWell = false;
		//Do while response is invalid
		do
		{
			//Try and place ship Catch out of bounds and overlap exceptions
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CARRIER_NAME));
				//Placed well
				placedWell = true;
			} catch (IndexOutOfBoundsException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship out of bounds
				player.prompt("You ship falls outside the board. Try again.");
				placedWell = false;
			} catch (PieceOverlapException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship overlaps another ship
				player.prompt("You ship overlaps another piece. Try again.");
				placedWell = false;
			}
		}while(!placedWell);
		
		//Prints board for player to see
		player.prompt(gameboard.printBoard());
		
		//Check prompt
		placedWell = false;
		//Do while response is invalid
		do
		{
			//Try and place ship Catch out of bounds and overlap exceptions
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_BATTLESHIP_NAME));
				//Placed well
				placedWell = true;
			} catch (IndexOutOfBoundsException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship out of bounds
				player.prompt("You ship falls outside the board. Try again.");
				placedWell = false;
			} catch (PieceOverlapException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship overlaps another ship
				player.prompt("You ship overlaps another piece. Try again.");
				placedWell = false;
			}
		}while(!placedWell);
		
		//Prints board for player to see
		player.prompt(gameboard.printBoard());
		
		//Check prompt
		placedWell = false;
		//Do while response is invalid
		do
		{
			//Try and place ship Catch out of bounds and overlap exceptions
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CRUISER_NAME));
				//Placed well
				placedWell = true;
			} catch (IndexOutOfBoundsException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship out of bounds
				player.prompt("You ship falls outside the board. Try again.");
				placedWell = false;
			} catch (PieceOverlapException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship overlaps another ship
				player.prompt("You ship overlaps another piece. Try again.");
				placedWell = false;
			}
		}while(!placedWell);
		
		//Prints board for player to see
		player.prompt(gameboard.printBoard());
		
		//Check prompt
		placedWell = false;
		//Do while response is invalid
		do
		{
			//Try and place ship Catch out of bounds and overlap exceptions
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_SUBMARINE_NAME));
				//Placed well
				placedWell = true;
			} catch (IndexOutOfBoundsException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship out of bounds
				player.prompt("You ship falls outside the board. Try again.");
				placedWell = false;
			} catch (PieceOverlapException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship overlaps another ship
				player.prompt("You ship overlaps another piece. Try again.");
				placedWell = false;
			}
		}while(!placedWell);
		
		//Prints board for player to see
		player.prompt(gameboard.printBoard());
		
		//Check prompt
		placedWell = false;
		//Do while response is invalid
		do
		{
			//Try and place ship Catch out of bounds and overlap exceptions
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_PATROLBOAT_NAME));
				//Placed well
				placedWell = true;
			} catch (IndexOutOfBoundsException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship out of bounds
				player.prompt("You ship falls outside the board. Try again.");
				placedWell = false;
			} catch (PieceOverlapException e) {
				if(Constants.DEBUG)
					e.printStackTrace();
				//Prompt player ship overlaps another ship
				player.prompt("You ship overlaps another piece. Try again.");
				placedWell = false;
			}
		}while(!placedWell);
	}
	
	//Print the game board for a player
	private void printPlayerBoard(Player player, Board board, Board enemyBoard)
	{
		//Prompt the player with their full game board
		player.prompt("Here is you full Game Board for " + player.getClass().getName() +"!");
		player.prompt(boardBorder);
		player.prompt(enemyBoard.printBoard());
		player.prompt(boardBreak);
		player.prompt(board.printBoard());
		player.prompt(boardBorder + "\n");
	}
	
	//Constructor
	public Game(Player p1, Player p2)
	{
		//Init the players and their boards
		player1 = p1;
		player2 = p2;
		
		player1Board = new Board();
		player1EnemyBoard = new Board();
		
		player2Board = new Board();
		player2EnemyBoard = new Board();
		
		initBoardBreak();
	}
	
	//Handles the beginning of the game
	public void begin()
	{
		//Player 1 places ships
		player1.prompt("Player 1... Place your ships!");
		playerPlaceShips(player1, player1Board);
		
		//Player 2 places ships
		player2.prompt("Player 2... Place your ships!");
		playerPlaceShips(player2, player2Board);
		
		//Prints the boards for the players
		printPlayerBoard(player1, player1Board, player1EnemyBoard);
		printPlayerBoard(player2, player2Board, player2EnemyBoard);
	}
	
	//Handles the middle of the game
	public void fight()
	{
		//Define player fleet status
		boolean player1FleetSunk = player1Board.isFleetSunk();
		boolean player2FleetSunk = player2Board.isFleetSunk();
		
		//Handles ships sunk during one round of play
		boolean sunkStatus[] = new boolean[5];
		
		//Handles the shot being fired from the player
		int shot[];
		//Handles if shot hits
		boolean hit = false;
		
		//While both players still have ships alive
		while(!player1FleetSunk && !player2FleetSunk)
		{			
			//Get which ships have sunk during the beginning of the round
			sunkStatus[0] = player2Board.getShipList().get(0).isSunk();
			sunkStatus[1] = player2Board.getShipList().get(1).isSunk();
			sunkStatus[2] = player2Board.getShipList().get(2).isSunk();
			sunkStatus[3] = player2Board.getShipList().get(3).isSunk();
			sunkStatus[4] = player2Board.getShipList().get(4).isSunk();
			
			//Check if shot is placed well
			boolean placedWell = false;
			do
			{
				//Get shot from player
				shot = player1.shoot();
				try {
					//If debug is active
					if(Constants.DEBUG)
						System.out.println(player1.getClass().getName() + " fires at (" + shot[0] + ", " + shot[1] + ")");
					
					//Shoot shot on other players board
					hit = player2Board.shoot(shot[0], shot[1]);
					
					//Shot placed well
					placedWell = true;
				//Shot out of bounds
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					
					//Prompt player
					player1.prompt("You shot falls outside the combat zone. Try again.");
					placedWell = false;
				//Shot overlaps another piece
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					
					//Prompt player
					player1.prompt("You have already fired there. Try again.");
					placedWell = false;
				}
			//While not placed well
			}while(!placedWell);
			
			//If shot hit
			if(hit)
			{
				//Prompt player
				player1.prompt("HIT!");
				//Update board
				player1EnemyBoard.setCoord(shot[0], shot[1], '!');
			}
			//Shot misses
			else
			{
				//Prompt Player
				player1.prompt("MISS!");
				//Update Board
				player1EnemyBoard.setCoord(shot[0], shot[1], 'X');
			}
			
			//Check if status of ships change after shot
			if(sunkStatus[0] != player2Board.getShipList().get(0).isSunk())
			{
				player1.prompt("Ship Sunk!");
			}
			if(sunkStatus[1] != player2Board.getShipList().get(1).isSunk())
			{
				player1.prompt("Ship Sunk!");
			}
			if(sunkStatus[2] != player2Board.getShipList().get(2).isSunk())
			{
				player1.prompt("Ship Sunk!");
			}
			if(sunkStatus[3] != player2Board.getShipList().get(3).isSunk())
			{
				player1.prompt("Ship Sunk!");
			}
			if(sunkStatus[4] != player2Board.getShipList().get(4).isSunk())
			{
				player1.prompt("Ship Sunk!");
			}			
			
			//Check if player fleet status is sunk
			player1FleetSunk = player1Board.isFleetSunk();
			player2FleetSunk = player2Board.isFleetSunk();
			
			//Middle of turn check
			if(Constants.DEBUG)
			{
				System.out.println("Middle of turn check");
				System.out.println("Status of Player 1 fleet sunk :: " + player1FleetSunk);
				System.out.println("Status of Player 2 fleet sunk :: " + player2FleetSunk);
			}
			
			//If one player fleet is sunk
			if(player1FleetSunk || player2FleetSunk)
			{
				//End game
				break;
			}
			
			//Get which ships have sunk during the beginning of the round
			sunkStatus[0] = player1Board.getShipList().get(0).isSunk();
			sunkStatus[1] = player1Board.getShipList().get(1).isSunk();
			sunkStatus[2] = player1Board.getShipList().get(2).isSunk();
			sunkStatus[3] = player1Board.getShipList().get(3).isSunk();
			sunkStatus[4] = player1Board.getShipList().get(4).isSunk();
			
			//Check if shot is placed well
			placedWell = false;
			do
			{
				//Get shot from player
				shot = player2.shoot();
				try {
					//If debug is active
					if(Constants.DEBUG)
						System.out.println(player2.getClass().getName() + " fires at (" + shot[0] + ", " + shot[1] + ")");
					
					//Shoot shot on other players board
					hit = player1Board.shoot(shot[0], shot[1]);
					
					//Shot placed well
					placedWell = true;
				//Shot out of bounds
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					
					//Prompt player
					player2.prompt("You shot falls outside the combat zone. Try again.");
					placedWell = false;
				//Shot overlaps another piece
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					
					//Prompt player
					player2.prompt("You have already fired there. Try again.");
					placedWell = false;
				}
			//While not placed well
			}while(!placedWell);
			
			//If shot hit
			if(hit)
			{
				//Prompt player
				player2.prompt("HIT!");
				//Update board
				player2EnemyBoard.setCoord(shot[0], shot[1], '!');
			}
			//Shot misses
			else
			{
				//Prompt Player
				player2.prompt("MISS!");
				//Update Board
				player2EnemyBoard.setCoord(shot[0], shot[1], 'X');
			}
			
			//Check if status of ships change after shot
			if(sunkStatus[0] != player1Board.getShipList().get(0).isSunk())
			{
				player2.prompt("Ship Sunk!");
			}
			if(sunkStatus[1] != player1Board.getShipList().get(1).isSunk())
			{
				player2.prompt("Ship Sunk!");
			}
			if(sunkStatus[2] != player1Board.getShipList().get(2).isSunk())
			{
				player2.prompt("Ship Sunk!");
			}
			if(sunkStatus[3] != player1Board.getShipList().get(3).isSunk())
			{
				player2.prompt("Ship Sunk!");
			}
			if(sunkStatus[4] != player1Board.getShipList().get(4).isSunk())
			{
				player2.prompt("Ship Sunk!");
			}			
			
			//Check if player fleet status is sunk
			player1FleetSunk = player1Board.isFleetSunk();
			player2FleetSunk = player2Board.isFleetSunk();
			
			//Middle of turn check
			if(Constants.DEBUG)
			{
				System.out.println("Middle of turn check");
				System.out.println("Status of Player 1 fleet sunk :: " + player1FleetSunk);
				System.out.println("Status of Player 2 fleet sunk :: " + player2FleetSunk);
			}
			
			//Print players boards at end of round
			printPlayerBoard(player1, player1Board, player1EnemyBoard);
			printPlayerBoard(player2, player2Board, player2EnemyBoard);
		}
	}
	
	//Handles the end of the game
	public void end()
	{
		//If not running statistics print out both boards
		if(!Constants.STATS)
		{
			System.out.println("Final Board arrangement\n");
			System.out.println("Player 1");
			System.out.println(boardBorder);
			System.out.println(player1EnemyBoard.printBoard());
			System.out.println(boardBreak);
			System.out.println(player1Board.printBoard());
			System.out.println(boardBorder + "\n");
			
			System.out.println("Player 2");
			System.out.println(boardBorder);
			System.out.println(player2EnemyBoard.printBoard());
			System.out.println(boardBreak);
			System.out.println(player2Board.printBoard());
			System.out.println(boardBorder + "\n");
		}
		
		//If player 1 lost
		if(player1Board.isFleetSunk())
		{
			if(!Constants.STATS)
			{
				System.out.println("Player 2 Wins!");
			}
			
			winner = true;
		}
		
		//If player 2 lost
		if(player2Board.isFleetSunk())
		{
			if(!Constants.STATS)
			{
				System.out.println("Player 1 Wins!");
			}
			
			winner = false;
		}
	}
	
	//Return who won. 0 is player1 | 1 is player2
	public boolean getWinner()
	{
		return winner;
	}
}

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
	
	private String boardBorder;
	private String boardBreak;
	
	private void initBoardBreak()
	{
		boardBorder = "########";
		boardBreak = "# ----";
		for(int i = 0; i < Constants.SIZE*3; i++)
		{
			boardBorder += "#";
			boardBreak += "-";
		}
		boardBreak += " #";
	}
	
	private void playerPlaceShips(Player player, Board gameboard)
	{
		player.prompt(gameboard.printBoard());
		
		boolean placedWell = false;
		do
		{
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CARRIER_NAME));
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
		
		
		player.prompt(gameboard.printBoard());
		placedWell = false;
		do
		{
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_BATTLESHIP_NAME));
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
		
		player.prompt(gameboard.printBoard());
		
		placedWell = false;
		do
		{
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_CRUISER_NAME));
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
		
		player.prompt(gameboard.printBoard());
		
		placedWell = false;
		do
		{
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_SUBMARINE_NAME));
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
		
		player.prompt(gameboard.printBoard());
		
		placedWell = false;
		do
		{
			try {
				gameboard.placeShip(player.chooseShipLocation(Constants.DEFAULT_PATROLBOAT_NAME));
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
		
	}
	
	private void printPlayerBoard(Player player, Board board, Board enemyBoard)
	{
		player.prompt("Here is you full Game Board for " +  "!");
		player.prompt(boardBorder);
		player.prompt(enemyBoard.printBoard());
		player.prompt(boardBreak);
		player.prompt(board.printBoard());
		player.prompt(boardBorder + "\n");
	}
	
	public Game(Player p1, Player p2)
	{
		player1 = p1;
		player2 = p2;
		
		player1Board = new Board();
		player1EnemyBoard = new Board();
		
		player2Board = new Board();
		player2EnemyBoard = new Board();
		
		initBoardBreak();
	}
	
	public void begin()
	{
		player1.prompt("Player 1... Place your ships!");
		playerPlaceShips(player1, player1Board);
		
		player2.prompt("Player 2... Place your ships!");
		playerPlaceShips(player2, player2Board);
		
		if(Constants.DEBUG)
		{
			player1.prompt("Here is player 1 Game Board after adding ships!");
			player1.prompt(player1Board.printBoard());
		}
		
		if(Constants.DEBUG)
		{
			player2.prompt("Here is player 2 Game Board after adding ships!");
			player2.prompt(player2Board.printBoard());
		}
		
		printPlayerBoard(player1, player1Board, player1EnemyBoard);
		printPlayerBoard(player2, player2Board, player2EnemyBoard);
	}
	
	public void fight()
	{
		boolean player1FleetSunk = player1Board.isFleetSunk();
		boolean player2FleetSunk = player2Board.isFleetSunk();
		
		if(Constants.DEBUG)
		{
			System.out.println("Begining of fight check");
			System.out.println("Status of Player 1 fleet sunk :: " + player1FleetSunk);
			System.out.println("Status of Player 2 fleet sunk :: " + player2FleetSunk);
		}
		
		boolean sunkStatus[] = new boolean[5];
		
		int shot[];
		boolean hit = false;
		
		while(!player1FleetSunk && !player2FleetSunk)
		{			
			sunkStatus[0] = player2Board.getShipList().get(0).isSunk();
			sunkStatus[1] = player2Board.getShipList().get(1).isSunk();
			sunkStatus[2] = player2Board.getShipList().get(2).isSunk();
			sunkStatus[3] = player2Board.getShipList().get(3).isSunk();
			sunkStatus[4] = player2Board.getShipList().get(4).isSunk();
			
			boolean placedWell = false;
			do
			{
				shot = player1.shoot();
				try {
					hit = player2Board.shoot(shot[0], shot[1]);
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player1.prompt("You shot falls outside the combat zone. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player1.prompt("You have already fired there. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			if(hit)
			{
				player1.prompt("HIT!");
				player1EnemyBoard.setCoord(shot[0], shot[1], '!');
			}
			else
			{
				player1.prompt("MISS!");
				player1EnemyBoard.setCoord(shot[0], shot[1], 'X');
			}
			
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
			
			player1FleetSunk = player1Board.isFleetSunk();
			player2FleetSunk = player2Board.isFleetSunk();
			
			printPlayerBoard(player1, player1Board, player1EnemyBoard);
			
			if(Constants.DEBUG)
			{
				System.out.println("Middle of turn check");
				System.out.println("Status of Player 1 fleet sunk :: " + player1FleetSunk);
				System.out.println("Status of Player 2 fleet sunk :: " + player2FleetSunk);
			}
			
			if(player1FleetSunk || player2FleetSunk)
			{
				break;
			}
			
			sunkStatus[0] = player1Board.getShipList().get(0).isSunk();
			sunkStatus[1] = player1Board.getShipList().get(1).isSunk();
			sunkStatus[2] = player1Board.getShipList().get(2).isSunk();
			sunkStatus[3] = player1Board.getShipList().get(3).isSunk();
			sunkStatus[4] = player1Board.getShipList().get(4).isSunk();
			
			placedWell = false;
			do
			{
				shot = player2.shoot();
				try {
					hit = player1Board.shoot(shot[0], shot[1]);
					placedWell = true;
				} catch (IndexOutOfBoundsException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player2.prompt("You shot falls outside the combat zone. Try again.");
					placedWell = false;
				} catch (PieceOverlapException e) {
					if(Constants.DEBUG)
						e.printStackTrace();
					player2.prompt("You have already fired there. Try again.");
					placedWell = false;
				}
			}while(!placedWell);
			
			if(hit)
			{
				player2.prompt("HIT!");
				player2EnemyBoard.setCoord(shot[0], shot[1], '!');
			}
			else
			{
				player2.prompt("MISS!");
				player2EnemyBoard.setCoord(shot[0], shot[1], 'X');
			}
			
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
			
			player1FleetSunk = player1Board.isFleetSunk();
			player2FleetSunk = player2Board.isFleetSunk();
			
			printPlayerBoard(player2, player2Board, player2EnemyBoard);
			
			if(Constants.DEBUG)
			{
				System.out.println("End of turn check");
				System.out.println("Status of Player 1 fleet sunk :: " + player1FleetSunk);
				System.out.println("Status of Player 2 fleet sunk :: " + player2FleetSunk);
			}
		}
	}
	
	public void end()
	{
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
		
		if(player1Board.isFleetSunk())
		{
			if(!Constants.STATS)
			{
				System.out.println("Player 2 Wins!");
			}
			
			winner = true;
		}
		
		if(player2Board.isFleetSunk())
		{
			if(!Constants.STATS)
			{
				System.out.println("Player 1 Wins!");
			}
			
			winner = false;
		}
	}
	
	public boolean getWinner()
	{
		return winner;
	}
}

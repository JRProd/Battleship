package game;

import java.util.ArrayList;

import pieces.Ship;

//Board class Used to keep track of ships and shots
public class Board {
	
	//Define board and list of ships
	char boardGrid[][];
	ArrayList<Ship> ships;
	
	//Init board with water characters 
	private void initGameBoard()
	{
		//Loop through all slots
		for(int x = 0; x < Constants.SIZE; x++)
		{
			for(int y = 0; y < Constants.SIZE; y++)
			{
				//Place '~' in all board slots 
				boardGrid[x][y] = '~';
			}
		}
	}
	
	//Place ship piece on the game board
	private boolean placeShipOnBoard(int start, int finish, int row_col_const, Ship s, boolean up_down)
	{
		//Piece goes up and down
		if(up_down)
		{
			//Check if board is occupied
			for(int i = start; i <= finish; i++)
			{
				//If another piece is present return false
				if(boardGrid[i][row_col_const] != '~')
					return false;
			}
			
			//No piece present
			for(int i = start; i <= finish; i++)
			{
				//Add game piece to board
				boardGrid[i][row_col_const] = s.getGamePiece();
			}
		}
		//Piece goes left and right
		else
		{
			//Check if board is occupied
			for(int i = start; i <= finish; i++)
			{
				//If another piece is present return false
				if(boardGrid[row_col_const][i] != '~')
					return false;
			}
			
			//No piece present
			for(int i = start; i <= finish; i++)
			{
				//Add game piece to board
				boardGrid[row_col_const][i] = s.getGamePiece();
			}
			
		}
		
		//Add ship to list
		addShip(s);
		return true;
	}
	
	//Constructor
	public Board()
	{
		//Init private variables
		boardGrid = new char[Constants.SIZE][Constants.SIZE];
		ships = new ArrayList<Ship>();
		initGameBoard();
	}
	
	//Place ship. Throws exceptions
	public void placeShip(Ship s) throws IndexOutOfBoundsException, PieceOverlapException
	{
		//Gets the valused bounded by the ship
		int bounds[] = s.getBounds();
		
		if(Constants.DEBUG)
		{
			System.out.println(s.getName());
			System.out.println("(" + bounds[0] + "," + bounds[1] + ") --> (" + bounds[2] + "," + bounds[3] + ")");
		}
		
		//Check if bounds outside the board
		for(int i : bounds)
		{
			if(i < 0 )
				throw new IndexOutOfBoundsException();
		}
		
		//UP AND DOWN
		if(bounds[0] == bounds[2])
		{
			//If going down
			if(bounds[1] < bounds[3])
			{
				//Place ship
				if(!placeShipOnBoard(bounds[1], bounds[3], bounds[0], s, true))
					throw new PieceOverlapException();
			}
			//Else going up
			else
			{
				//Place ship
				if(!placeShipOnBoard(bounds[3], bounds[1], bounds[0], s, true))
					throw new PieceOverlapException();
			}
		}
		
		//LEFT AND RIGHT
		else
		{
			//IF going right
			if(bounds[0] < bounds[2])
			{
				//Place ship
				if(!placeShipOnBoard(bounds[0], bounds[2], bounds[1], s, false))
					throw new PieceOverlapException();
			}
			//Else going down
			else
			{
				//Place ship
				if(!placeShipOnBoard(bounds[2], bounds[0], bounds[1], s, false))
					throw new PieceOverlapException();
			}
		}
	}
	
	//Shoot at coord
	public boolean shoot(int x, int y) throws PieceOverlapException
	{
		//Check if shot is already fired there
		char nextShot = getCoord(x, y);
		if(nextShot == '!' || nextShot == 'X')
			throw new PieceOverlapException();
		
		//Check if shot hits
		boolean hit = false;
		
		//Check all ships
		for(Ship s : ships)
		{
			//Check if ship gets hit
			hit = s.addHit(x, y);
			//IF hit return
			if(hit)
				break;
		}
		
		//If hit mark hit
		if(hit)
		{
			setCoord(x, y, '!');
		}
		//Else mark miss
		else
		{
			setCoord(x, y, 'X');
		}
		
		//Return 
		return hit;
	}
	
	//Set coord to char
	public void setCoord(int x, int y, char c)
	{
		boardGrid[y][x] = c;
	}
	
	//Get char at coord
	public char getCoord(int x, int y)
	{
		return boardGrid[y][x];
	}
	
	//Get char at coord
	public char getCoord(char x, int y)
	{
		//Convert char to int
		int tempX = convertCharToInt(x);
		return boardGrid[y][tempX];
	}
	
	//Check if the ship fleet is sunk
	public boolean isFleetSunk()
	{
		//Check all ships
		boolean isSunk = true;
		for(Ship ship : ships)
		{
			//If one ship is not sunk, fleet is not sunk
			if(!ship.isSunk())
			{
				isSunk = false;
			}
		}
		
		//Return if fleet is sunk
		return isSunk;
	}
	
	//Add ship to ship list
	public void addShip(Ship s)
	{
		ships.add(s);
	}
	
	//Get ship list
	public ArrayList<Ship> getShipList()
	{
		return ships;
	}
	
	//Print the board
	public String printBoard()
	{
		//Print the A -> (A + Constants.SIZE) at the top of the board
		String toReturn = "#     ";
		for(int i = 0; i < Constants.SIZE; i++)
		{
			//Increment by char value
			char c = (char) ('A' + i);
			toReturn += c + "  ";
		}
		toReturn += " #\n";
		
		//Now print the full board
		for(int y = 0; y < Constants.SIZE; y++)
		{
			//Format the number to match formating
			toReturn += String.format("# %2d  ", y+1);
			for (int x = 0; x < Constants.SIZE; x++)
			{
				//Print out the characters on the board
				toReturn += boardGrid[y][x] + "  ";
			}
			toReturn += " #";
			//Dont print newline if last line
			if(y != Constants.SIZE -1)
				toReturn += "\n";
		}
		return toReturn;
	}
	
	//Convert char to ing
	public static int convertCharToInt(char x)
	{
		//Convert int to char
		if(x > 96)
			x -= 97 - 'A';
		
		//Standardize it
		int toInt = x - 'A';
		
		//Bounds check
		if(toInt < 0)
		{
			toInt = -1;
		}
		
		//Return value
		return toInt;
	}
}

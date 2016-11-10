package game;

import java.util.ArrayList;

import pieces.Ship;

public class Board {
	char boardGrid[][];
	ArrayList<Ship> ships;
	
	private void initGameBoard()
	{
		for(int x = 0; x < Constants.SIZE; x++)
		{
			for(int y = 0; y < Constants.SIZE; y++)
			{
				boardGrid[x][y] = '~';
			}
		}
	}
	
	private boolean placeShipOnBoard(int start, int finish, int row_col_const, Ship s, boolean up_down)
	{
		if(up_down)
		{
			for(int i = start; i <= finish; i++)
			{
				if(boardGrid[i][row_col_const] != '~')
					return false;
			}
			
			for(int i = start; i <= finish; i++)
			{
				boardGrid[i][row_col_const] = s.getGamePiece();
			}
		}
		else
		{
			for(int i = start; i <= finish; i++)
			{
				if(boardGrid[row_col_const][i] != '~')
					return false;
			}
			for(int i = start; i <= finish; i++)
			{
				boardGrid[row_col_const][i] = s.getGamePiece();
			}
			
		}
		addShip(s);
		return true;
	}
	
	public Board()
	{
		boardGrid = new char[Constants.SIZE][Constants.SIZE];
		ships = new ArrayList<Ship>();
		initGameBoard();
	}
	
	public void placeShip(Ship s) throws IndexOutOfBoundsException, PieceOverlapException
	{
		int bounds[] = s.getBounds();
		
		if(Constants.DEBUG)
		{
			System.out.println(s.getName());
			System.out.println("(" + bounds[0] + "," + bounds[1] + ") --> (" + bounds[2] + "," + bounds[3] + ")");
		}
		
		for(int i : bounds)
		{
			if(i < 0 )
				throw new IndexOutOfBoundsException();
		}
		
		//UP AND DOWN
		if(bounds[0] == bounds[2])
		{
			if(bounds[1] < bounds[3])
			{
				if(!placeShipOnBoard(bounds[1], bounds[3], bounds[0], s, true))
					throw new PieceOverlapException();
			}
			else
			{
				if(!placeShipOnBoard(bounds[3], bounds[1], bounds[0], s, true))
					throw new PieceOverlapException();
			}
		}
		else 
		{
			if(bounds[0] < bounds[2])
			{
				if(!placeShipOnBoard(bounds[0], bounds[2], bounds[1], s, false))
					throw new PieceOverlapException();
			}
			else
			{
				if(!placeShipOnBoard(bounds[2], bounds[0], bounds[1], s, false))
					throw new PieceOverlapException();
			}
		}
	}
	
	public boolean shoot(int x, int y) throws PieceOverlapException
	{
		char nextShot = getCoord(x, y);
		if(nextShot == '!' || nextShot == 'X')
			throw new PieceOverlapException();
		
		boolean hit = false;
		for(Ship s : ships)
		{
			hit = s.addHit(x, y);
			if(hit)
				break;
		}
		
		if(hit)
		{
			setCoord(x, y, '!');
		}
		else
		{
			setCoord(x, y, 'X');
		}
		
		return hit;
	}
	
	public void setCoord(int x, int y, char c)
	{
		boardGrid[y][x] = c;
	}
	
	public char getCoord(int x, int y)
	{
		return boardGrid[y][x];
	}
	
	public char getCoord(char x, int y)
	{
		int tempX = convertCharToInt(x);
		return boardGrid[y][tempX];
	}
	
	public boolean isFleetSunk()
	{
		boolean isSunk = true;
		for(Ship ship : ships)
		{
			if(!ship.isSunk())
			{
				isSunk = false;
			}
		}
		return isSunk;
	}
	
	public void addShip(Ship s)
	{
		ships.add(s);
	}
	
	public ArrayList<Ship> getShipList()
	{
		return ships;
	}
	
	public String printBoard()
	{
		String toReturn = "#     ";
		for(int i = 0; i < Constants.SIZE; i++)
		{
			char c = (char) ('A' + i);
			toReturn += c + "  ";
		}
		toReturn += " #\n";
		
		
		for(int y = 0; y < Constants.SIZE; y++)
		{
			toReturn += String.format("# %2d  ", y+1);
			for (int x = 0; x < Constants.SIZE; x++)
			{
				toReturn += boardGrid[y][x] + "  ";
			}
			toReturn += " #";
			if(y != Constants.SIZE -1)
				toReturn += "\n";
		}
		return toReturn;
	}
	
	public static int convertCharToInt(char x)
	{
		if(x > 96)
			x -= 97 - 'A';
		
		int toInt = x - 'A';
		
		if(toInt < 0)
		{
			toInt = -1;
		}
		
		return toInt;
	}
}

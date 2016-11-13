package pieces;

import game.Constants;
import game.Direction;

public class Battleship extends Ship{
	private static final char gamePiece = 'B';
	
	private static final String SRC = Constants.BATTLESHIP_RES; 
	
	public Battleship()
	{
		super(0, 0, 4, Direction.RIGHT, true, true, Constants.DEFAULT_BATTLESHIP_NAME);
	}
	
	public Battleship(int x, int y, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, 4, dir, vis, sunk, Constants.DEFAULT_BATTLESHIP_NAME);
	}
	
	public Battleship(int x, int y, Direction dir, boolean vis, boolean sunk, String name)
	{
		super(x, y, 4, dir, vis, sunk, name);
	}
	
	public char getGamePiece()
	{
		return gamePiece;
	}
	
	//Unused, never got to UI elements
	public void draw()
	{
		
	}
}

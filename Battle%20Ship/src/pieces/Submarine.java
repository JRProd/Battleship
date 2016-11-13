package pieces;

import game.Constants;
import game.Direction;

public class Submarine extends Ship{
	private static final char gamePiece = 'S';
	
	private static final String SRC = Constants.SUBMARINE_RES; 
	
	public Submarine()
	{
		super(0, 0, 3, Direction.RIGHT, true, true, Constants.DEFAULT_SUBMARINE_NAME);
	}
	
	public Submarine(int x, int y, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, 3, dir, vis, sunk, Constants.DEFAULT_SUBMARINE_NAME);
	}
	
	public Submarine(int x, int y, Direction dir, boolean vis, boolean sunk, String name)
	{
		super(x, y, 3, dir, vis, sunk, name);
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

package pieces;

import game.Constants;
import game.Direction;

public class Carrier extends Ship{

	private static final char gamePiece = 'C';
	
	private static final String SRC = Constants.CARRIER_RES;
	
	public Carrier()
	{
		super(0, 0, 5, Direction.RIGHT, true, true, Constants.DEFAULT_CARRIER_NAME);
	}
	
	public Carrier(int x, int y, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, 5, dir, vis, sunk, Constants.DEFAULT_CARRIER_NAME);
	}
	
	public Carrier(int x, int y, Direction dir, boolean vis, boolean sunk, String name)
	{
		super(x, y, 5, dir, vis, sunk, name);
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

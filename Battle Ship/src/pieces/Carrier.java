package pieces;

import game.Constants;
import game.Direction;

public class Carrier extends Ship{
	
	//Private instance variables 
	private static final char gamePiece = 'C';	//Game piece used in board representation
	
	private static final String SRC = Constants.CARRIER_RES; //Constant to picture for UI
	
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
	
	/////////////// GETTER AND SETTER \\\\\\\\\\\\\\\
	public char getGamePiece()
	{
		return gamePiece;
	}
	
	//Use to draw carrier to screen
	public void draw()
	{
		
	}
}

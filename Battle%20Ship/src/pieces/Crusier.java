package pieces;

import game.Constants;
import game.Direction;

public class Crusier extends Ship{
	private static final char gamePiece = 'c';
	
	private static final String SRC = Constants.CRUISER_RES; 
	
	public Crusier()
	{
		super(0, 0, 3, Direction.RIGHT, true, true, Constants.DEFAULT_CRUISER_NAME);
	}
	
	public Crusier(int x, int y, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, 3, dir, vis, sunk, Constants.DEFAULT_CRUISER_NAME);
	}
	
	public Crusier(int x, int y, Direction dir, boolean vis, boolean sunk, String name)
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

package pieces;

import game.Constants;
import game.Direction;

public class PatrolBoat extends Ship{
	private static final char gamePiece = 'P';
	
	private static final String SRC = Constants.PATOLBOAT_RES; 
	
	public PatrolBoat()
	{
		super(0, 0, 2, Direction.RIGHT, true, true, Constants.DEFAULT_PATROLBOAT_NAME);
	}
	
	public PatrolBoat(int x, int y, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, 2, dir, vis, sunk, Constants.DEFAULT_PATROLBOAT_NAME);
	}
	
	public PatrolBoat(int x, int y, Direction dir, boolean vis, boolean sunk, String name)
	{
		super(x, y, 2, dir, vis, sunk, name);
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

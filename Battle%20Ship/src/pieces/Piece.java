package pieces;
import game.Direction;

//Base class for all pieces 
public abstract class Piece {
	
	//X and y coord
	private int xCoord;
	private int yCoord;
	
	//Size and direction
	private int size;
	private Direction direction;
	
	//Not used because not UI implemented
	private boolean visable;
	
	//Default constructor
	public Piece()
	{
		xCoord = 0;
		yCoord = 0;
		
		size = 1;
		direction = Direction.NONE;
		
		visable = true;
	}
	
	//Valued constructor
	public Piece(int x, int y, int s, Direction dir, boolean vis)
	{
		xCoord = x;
		yCoord = y;
		
		size = s;
		direction = dir;
		
		visable = vis;
	}
	
	//Gets the bounds that the piece occupies
	public int[] getBounds()
	{
		//Define bounds
		int[] bounds = new int[4];
		
		//Get the first coord
		bounds[0] = xCoord;
		bounds[1] = yCoord;
		
		//Switched based on direction
		switch (direction) {
		case NONE:
			bounds[2] = xCoord;
			bounds[3] = yCoord;
			break;
			
		case UP:
			bounds[2] = xCoord;
			bounds[3] = yCoord - size + 1;
			break;
			
		case RIGHT:
			bounds[2] = xCoord + size - 1;
			bounds[3] = yCoord;
			break;
			
		case DOWN:
			bounds[2] = xCoord;
			bounds[3] = yCoord + size - 1;
			break;
			
		case LEFT:	
			bounds[2] = xCoord - size + 1;
			bounds[3] = yCoord;
			break;

		default:
			bounds[2] = xCoord;
			bounds[3] = yCoord;
			break;
		}
		//Return the bounds
		return bounds;		
	}
	
	//Abstract class to form layer of abstraction
	public abstract void draw();
	public abstract char getGamePiece();
	
	//////////GETTER and SETTER\\\\\\\\\\
	public int getXCoord()
	{
		return xCoord;
	}
	
	public int getYCoord()
	{
		return yCoord;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public boolean isVisable()
	{
		return visable;
	}
	
	public void setXCoord(int x)
	{
		xCoord = x;
	}
	
	public void setYCoord(int y)
	{
		yCoord = y;
	}
	
	public void setSize(int s)
	{
		size = s;
	}
	
	public void setDirection(Direction d)
	{
		direction = d;
	}
	
	public void setVisable(boolean vis)
	{
		visable = vis;
	}
}

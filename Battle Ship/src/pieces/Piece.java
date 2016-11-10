package pieces;
import game.Direction;

public abstract class Piece {
	private int xCoord;
	private int yCoord;
	
	private int size;
	private Direction direction;
	
	private boolean visable;
	
	public Piece()
	{
		xCoord = 0;
		yCoord = 0;
		
		size = 1;
		direction = Direction.NONE;
		
		visable = true;
	}
	
	public Piece(int x, int y, int s, Direction dir, boolean vis)
	{
		xCoord = x;
		yCoord = y;
		
		size = s;
		direction = dir;
		
		visable = vis;
	}
	
	public int[] getBounds()
	{
		int[] bounds = new int[4];
		
		bounds[0] = xCoord;
		bounds[1] = yCoord;
		
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
		return bounds;		
	}
	
	public abstract void draw();
	public abstract char getGamePiece();
	
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

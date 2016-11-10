package game;

public enum Direction
{
	NONE(0),
	UP(1),
	RIGHT(2),
	DOWN(3),
	LEFT(4);
	
	private int direct;
	
	private Direction(int dir)
	{
		direct = dir;
	}
	
	public int getDirection()
	{
		return direct;
	}
}
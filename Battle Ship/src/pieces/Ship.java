package pieces;

import game.Constants;
import game.Direction;

public abstract class Ship extends Piece{
	
	//Private Instance variables
	private boolean[] hits; //Stores which points of the ship have been hit
	private boolean sunk; //Stores if ship has been sunk
	
	private String name; //Name: Used for customization and debugging
	
	public Ship()
	{
		super();
		hits = new boolean[1];
		sunk = false;
		name = "Ship";
	}
	
	public Ship(int x, int y, int size, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, size, dir, vis);
		hits = new boolean[size];
		this.sunk = sunk;
		name = "Ship";
	}
	
	public Ship(int x, int y, int size, Direction dir, boolean vis, boolean sunk, String n)
	{
		super(x, y, size, dir, vis);
		hits = new boolean[size];
		this.sunk = sunk;
		name = n;
	}
	
	//Add a hit to the array of hits
	public boolean addHit(int x, int y)
	{
		boolean hit = false;
		int bounds[] = getBounds();
		
		if(Constants.DEBUG)
		{
			System.out.println("Bounds of ship " + getName());
			System.out.println("(" + x +", " + y + ") in (" + bounds[0] + ", " + bounds[1] + ") --> (" + bounds[2] + ", " + bounds[3] + ")");
		}
		
		//Ship positioned Up/Down
		if(bounds[0] == bounds[2] && bounds[0] == x)
		{
			if(bounds[1] < bounds[3] && y >= bounds[1] && y <= bounds[3])
			{
				hits[y - bounds[1]] = true;
				hit = true;
			}
			if(bounds[3] < bounds[1] && y >= bounds[3] && y <= bounds[1])
			{
				hits[y - bounds[3]] = true;
				hit = true;
			}
		}
		
		//Ship positioned Left/Right
		if(bounds[1] == bounds[3] && bounds[1] == y)
		{
			if(bounds[0] < bounds[2] && x >= bounds[0] && x <= bounds[2])
			{
				hits[x - bounds[0]] = true;
				hit = true;
			}
			if(bounds[2] < bounds[0] && x >= bounds[2] && x <= bounds[0])
			{
				hits[x - bounds[2]] = true;
				hit = true;
			}
		}
		
		boolean checkSunk = true;
		for(boolean b : hits)
		{
			if(!b)
			{
				checkSunk = false;
				break;
			}
		}
		
		if(checkSunk && !sunk)
		{
			setSunk(true);
		}
		
		if(Constants.DEBUG)
		{
			System.out.print(getName() + " :: [");
			for(int i = 0; i < hits.length; i++)
			{
				if(i != hits.length - 1)
					System.out.print(hits[i] + ", ");
				else
					System.out.print(hits[i]);
			}
			System.out.println("] :: " + isSunk() + "\n");
		}
		
		return hit;
	}
	
	/////////////// GETTERS AND SETTERS \\\\\\\\\\\\\\\
	public boolean isSunk()
	{
		return sunk;
	}
	
	public void setSunk(boolean s)
	{
		sunk = s;
	}
	
	public String getName()
	{
		return name;
	}
}

package pieces;

import game.Constants;
import game.Direction;

public abstract class Ship extends Piece{
	
	//Private Instance variables
	private boolean[] hits; //Stores which points of the ship have been hit
	private boolean sunk; //Stores if ship has been sunk
	
	private String name; //Name: Used for customization and debugging
	
	//Constructor
	public Ship()
	{
		super();
		hits = new boolean[1];
		sunk = false;
		name = "Ship";
	}
	
	//Valued constructor
	public Ship(int x, int y, int size, Direction dir, boolean vis, boolean sunk)
	{
		super(x, y, size, dir, vis);
		hits = new boolean[size];
		this.sunk = sunk;
		name = "Ship";
	}
	
	//Valued constructor with name
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
		//Gets the bounds of the ship
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
			//If ship goes down
			if(bounds[1] < bounds[3] && y >= bounds[1] && y <= bounds[3])
			{
				//Add hit
				hits[y - bounds[1]] = true;
				hit = true;
			}
			//If ship goes Up
			if(bounds[3] < bounds[1] && y >= bounds[3] && y <= bounds[1])
			{
				//Add hit
				hits[y - bounds[3]] = true;
				hit = true;
			}
		}
		
		//Ship positioned Left/Right
		if(bounds[1] == bounds[3] && bounds[1] == y)
		{
			//If ship goes right
			if(bounds[0] < bounds[2] && x >= bounds[0] && x <= bounds[2])
			{
				//Add hit
				hits[x - bounds[0]] = true;
				hit = true;
			}
			//If ship goes left
			if(bounds[2] < bounds[0] && x >= bounds[2] && x <= bounds[0])
			{
				//Add hit
				hits[x - bounds[2]] = true;
				hit = true;
			}
		}
		
		//Check if ship is sunk
		//assume it is
		boolean checkSunk = true;
		for(boolean b : hits)
		{
			//If not sunk
			if(!b)
			{
				//Ship not sunk
				checkSunk = false;
				break;
			}
		}
		
		//If ship is not sunka and should be, set sunk
		if(checkSunk && !sunk)
		{
			setSunk(true);
		}
		
		//Debug 
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
		
		//Return if ship was hit
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

package players;

import java.util.Random;

import game.Constants;
import game.Direction;
import pieces.Battleship;
import pieces.Carrier;
import pieces.Crusier;
import pieces.PatrolBoat;
import pieces.Ship;
import pieces.Submarine;

public class ShotSelectiveSuperiorAI extends Player{
	
	//Use to calculate if last shot was hit
	private int[] lastConfirmedShot;
	private int[] lastShot;
	
	//Was last shot a hit
	private boolean lastHit;
	
	//Coords of first hit on ship
	private int[] firstHitShip;
	//Direction to probe 
	private Direction nextShotDirection = Direction.UP;
	
	//Change probing direction
	private boolean changeDirection;
	
	private boolean upDown;
	private boolean leftRight;
	
	
	private int[] getCoords()
	{
		//Creates container for coords
		int coords[] = new int[2];
		
		//Gets random coords within bounds
		Random randomCoord = new Random();
		coords[0] = randomCoord.nextInt(Constants.SIZE);
		coords[1] = randomCoord.nextInt(Constants.SIZE);
		
		//Returns coords
		return coords;
	}
	
	private Direction getDirection()
	{
		//Get random direction
		Random randomCoord = new Random();
		int randomDir = randomCoord.nextInt(4) + 1;
		
		//Switch based off 4 directions
		switch(randomDir)
		{
		case 1:
			return Direction.UP;
		case 2:
			return Direction.RIGHT;
		case 3:
			return Direction.DOWN;
		case 4:
			return Direction.LEFT;
		default:
			return Direction.NONE;
		}
	}
	
	//Select shot based of previous shot
	private int[] selectShot()
	{
		//Create a container for coords
		int coords[] = new int[2];
		
		//If a change in direction is needed
		if(changeDirection)
		{
			//UP -> RIGHT -> DOWN -> LEFT -> BREAK (UP)
			if(upDown)
			{
				if(nextShotDirection == Direction.UP)
					nextShotDirection = Direction.DOWN;
				else if (nextShotDirection == Direction.DOWN)
				{
					//Reset controllers
					nextShotDirection = Direction.UP;
					changeDirection = false;
					
					//Use random coords
					return getCoords();
				}
			}
			else if(leftRight)
			{
				if (nextShotDirection == Direction.RIGHT)
					nextShotDirection = Direction.LEFT;
				else if (nextShotDirection == Direction.LEFT)
				{
					//Reset controllers
					nextShotDirection = Direction.UP;
					changeDirection = false;
					
					//Use random coords
					return getCoords();
				}
			}
			else
			{
				if(nextShotDirection == Direction.UP)
					nextShotDirection = Direction.RIGHT;
				else if (nextShotDirection == Direction.RIGHT)
					nextShotDirection = Direction.DOWN;
				else if (nextShotDirection == Direction.DOWN)
					nextShotDirection = Direction.LEFT;
				//If direction is left (all options probed)
				else if (nextShotDirection == Direction.LEFT)
				{
					//Reset controllers
					nextShotDirection = Direction.UP;
					changeDirection = false;
					
					//Use random coords
					return getCoords();
				}
			}
			
			//Switch on direction
			//Used the stored first hit on ship to start
			//searching from the "apex"
			switch(nextShotDirection.getDirection())
			{
			//If UP
			case 1:
				coords[0] = firstHitShip[0];
				coords[1] = firstHitShip[1] - 1;
				break;
			//If RIGHT
			case 2:
				coords[0] = firstHitShip[0] + 1;
				coords[1] = firstHitShip[1] ;
				break;
			//IF DOWN
			case 3:
				coords[0] = firstHitShip[0];
				coords[1] = firstHitShip[1] + 1;
				break;
			//IF LEFT
			case 4:
				coords[0] = firstHitShip[0] - 1;
				coords[1] = firstHitShip[1];
				break;
			default:
				coords = getCoords();
				break;
			}
			
			//Last hit is true (acting like firstHitShip coord was last shot)
			lastHit = true;
			
			//Keep going in same direction
			changeDirection = false;
		}
		
		//Going in same direction
		else
		{
			//Switch on direction
			//Used the last confirmed shot to continue linear search
			switch(nextShotDirection.getDirection())
			{
			//If UP
			case 1:
				coords[0] = lastConfirmedShot[0];
				coords[1] = lastConfirmedShot[1] - 1;
				break;
				//If RIGHT
			case 2:
				coords[0] = lastConfirmedShot[0] + 1;
				coords[1] = lastConfirmedShot[1] ;
				break;
			//IF DOWN
			case 3:
				coords[0] = lastConfirmedShot[0];
				coords[1] = lastConfirmedShot[1] + 1;
				break;
			//IF LEFT
			case 4:
				coords[0] = lastConfirmedShot[0] - 1;
				coords[1] = lastConfirmedShot[1];
				break;
			default:
				coords = getCoords();
				break;
			}
		}
		
		//Return selected coords
		return coords;
	}

	@Override
	public int[] shoot() {
		//Create container
		int coords[] = new int[2];
		
		//If previous shot was hit or change of direction 
		if(lastHit || changeDirection)
		{
			//Select shot
			coords = selectShot();
		}
		//Nowhere else to probe
		else
		{
			//Rest directions
			nextShotDirection = Direction.UP;
			
			//Get random coords
			coords = getCoords();
		}
		
		//Store last shot
		lastShot = coords;
		return coords;
	}

	@Override
	public Ship chooseShipLocation(String ship)
	{
		int coords[];
		Direction dir;
		
		//Switch based off constant string
		switch(ship)
		{
		//Carrier case
		case Constants.DEFAULT_CARRIER_NAME:
//			System.out.println("Place your Carrier!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Carrier
			return new Carrier(coords[0], coords[1], dir, true, false);
		
		//Battlehsip case
		case Constants.DEFAULT_BATTLESHIP_NAME:
//			System.out.println("Place your Battleship!");
			//Get coords and direction 
			coords = getCoords();
			dir = getDirection();
			//Return new Battleship
			return new Battleship(coords[0], coords[1], dir, true, false);
		
		//Cruiser case
		case Constants.DEFAULT_CRUISER_NAME:
//			System.out.println("Place your Cruiser!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Crusier
			return new Crusier(coords[0], coords[1], dir, true, false);
		
		//Submarine case
		case Constants.DEFAULT_SUBMARINE_NAME:
//			System.out.println("Place your Submarine!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Submarine
			return new Submarine(coords[0], coords[1], dir, true, false);
			
		//Patrol Boat case
		case Constants.DEFAULT_PATROLBOAT_NAME:
//			System.out.println("Place your Patrol Boat!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Patrol Boat
			return new PatrolBoat(coords[0], coords[1], dir, true, false);
			
		//Defalut case
		default:
			//Return default Patrol Boat
			return new PatrolBoat();
		}
	}

	//Process game feedback
	@Override
	public void prompt(String s)
	{
		if(Constants.DEBUG)
			System.out.println(s);
		
		//Event shot hits
		if(s.equals("HIT!"))
		{
			//If first hit (of a ship)
			if(!lastHit)
			{
				//Store ship location
				firstHitShip = lastShot;
				upDown = false;
				leftRight = false;
			}
			
			//Define leftRight or upDown
			if(lastHit && !(upDown || leftRight))
			{
				if(firstHitShip[1] == lastShot[1] - 1 || firstHitShip[1] == lastShot[1] + 1)
				{
					upDown = true;
					leftRight = false;
				}
				else
				{
					upDown = false;
					leftRight = true;
				}
			}
			
			//Shot is valid shot, must implement because hit detection is not in AI but game
			lastConfirmedShot = lastShot;
			
			//Hit
			lastHit = true;
		}
		
		//Event shot misses
		else if(s.equals("MISS!"))
		{
			//If hit previously
			if(lastHit)
				//Time to change direction
				changeDirection = true;
			
			//Valid shot
			lastConfirmedShot = lastShot;
			
			//Miss
			lastHit = false;
		}
		
		//Event shot is outside gameboard
		else if(s.equals("You shot falls outside the combat zone. Try again."))
		{
			//If last shot was hit and not finished probing
			if(lastHit && nextShotDirection != Direction.LEFT)
				//Time to change direction
				changeDirection = true;
			//If last shot was hit and finsihed probing
			else
			{
				//Reset controls
				lastHit = false;
				changeDirection = false;
			}
		}
		
		//Event shot is blocked by previous shot
		else if(s.equals("You have already fired there. Try again."))
		{
			//If last shot was hit and not finished probing
			if(lastHit && nextShotDirection != Direction.LEFT)
				//Time to change direction
				changeDirection = true;
			//If last shot was hit and finsihed probing
			else
			{
				//Reset controls
				lastHit = false;
				changeDirection = false;
			}
		}
		
		//Stops firing around the ship if ship is sunk
		else if(s.equals("Ship Sunk!"))
		{
			//Default the values back to random shooting
			nextShotDirection = Direction.UP;
			
			//Reset Controls
			lastHit = false;
			changeDirection = false;
			upDown = false;
			leftRight = false;
		}
	}
}

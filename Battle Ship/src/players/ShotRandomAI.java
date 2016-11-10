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

public class ShotRandomAI extends Player{
	
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

	@Override
	public int[] shoot() {
		//Get coords to shoot for
		int coords[] = getCoords();
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

	@Override
	public void prompt(String s) {
		
	}

}

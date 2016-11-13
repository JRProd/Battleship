package players;

import java.util.InputMismatchException;
import java.util.Scanner;

import game.Board;
import game.Constants;
import game.Direction;
import pieces.Battleship;
import pieces.Carrier;
import pieces.Crusier;
import pieces.PatrolBoat;
import pieces.Ship;
import pieces.Submarine;

public class Human extends Player{
	
	//Gets coordinates form a Human
	private int[] getCoords()
	{
		//Used to confirm correct entry
		boolean correctEntry  = false;
		//Prompt the user
		System.out.println("Enter a \"x y\" coordinate");
		
		//Create scanner
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		
		char xCoord;
		int yCoord = 0;
		
		//Get Human entry
		do {
			xCoord = cin.next().charAt(0);
			
			//Bounds check for user entry for xCoord
			if(xCoord >= 'A' && xCoord <= 'A' + Constants.SIZE || xCoord >= 'a' && xCoord <= 'a' +  + Constants.SIZE)
			{
				correctEntry = true;
			}
			else
			{
				correctEntry = false;
			}
			
			
			try {
				yCoord = cin.nextInt();
				correctEntry = true;
			} catch (InputMismatchException e) {
				correctEntry = false;
			}
			
			
			//Bounds check for user entry for yCoord
			if(yCoord >= 1 && yCoord <= Constants.SIZE)
			{
				correctEntry = true;
			}
			else
			{
				correctEntry = false;
			}
			
			//Debug
			if(Constants.DEBUG)
			{
				System.out.println("Coords entered by Human :: (" + xCoord + "," + yCoord + ")");
			}
			
			//If entry is not correct. Print message and do again
			if(!correctEntry)
			{
				System.out.println("Please re-enter the coordinate in the valid form eg. A 1"); 
			}
		} while (!correctEntry);
		
		//Convert char to int
		int xVal = Board.convertCharToInt(xCoord);
		
		//Create array to return
		int[] tempAry = {xVal,yCoord - 1};
		
		//Return array
		return tempAry;
	}
	
	//Get the direction for the ship from the Human
	private Direction getDirection()
	{
		//Create boolean for bounds checking
		boolean correctEntry  = false;
		
		//Prompt the user
		System.out.println("What direction do you want you ship to go?");
		
		//Create a direction
		Direction dir;
		
		//Create scanner
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		String entry;
		
		//Do while out of bounds
		do {
			//Get entry
			entry = cin.nextLine();
			
			//Convert to lowercase
			entry = entry.toLowerCase();
			
			//Switch
			switch(entry)
			{
			//If up
			case "up":
				//Make up
				dir = Direction.UP;
				correctEntry = true;
				break;
				
			//If right
			case "right":
				//Make right
				dir = Direction.RIGHT;
				correctEntry = true;
				break;
				
			//If down
			case "down":
				//Make down
				dir = Direction.DOWN;
				correctEntry = true;
				break;
				
			//If left	
			case "left":
				//Make left
				dir = Direction.LEFT;
				correctEntry = true;
				break;
				
			//Default
			default:
				//No direction
				dir = Direction.NONE;
				correctEntry = false;
				break;
			}
			
			//If entry out of bounds
			if(!correctEntry)
			{
				//Prompt user
				System.out.println("Please re-enter the direction. Valid entries are \"Up\", \"Right\", \"Down\", \"Left\"");
				System.out.println("Case Insensitive");
			}
		} while (!correctEntry);
		
		//Return dir
		return dir;
	}
	
	public int[] shoot()
	{
		int tempAry[] = getCoords();
		return tempAry;
	}
	
	public Ship chooseShipLocation(String ship)
	{
		//Need coords and dir for ship
		int coords[];
		Direction dir;
		
		//Switch based off constant string
		switch(ship)
		{
		//Carrier case
		case Constants.DEFAULT_CARRIER_NAME:
			System.out.println("Place your Carrier!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Carrier
			return new Carrier(coords[0], coords[1], dir, true, false);
		
		//Battlehsip case
		case Constants.DEFAULT_BATTLESHIP_NAME:
			System.out.println("Place your Battleship!");
			//Get coords and direction 
			coords = getCoords();
			dir = getDirection();
			//Return new Battleship
			return new Battleship(coords[0], coords[1], dir, true, false);
		
		//Cruiser case
		case Constants.DEFAULT_CRUISER_NAME:
			System.out.println("Place your Cruiser!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Crusier
			return new Crusier(coords[0], coords[1], dir, true, false);
		
		//Submarine case
		case Constants.DEFAULT_SUBMARINE_NAME:
			System.out.println("Place your Submarine!");
			//Get coords and direction
			coords = getCoords();
			dir = getDirection();
			//Return new Submarine
			return new Submarine(coords[0], coords[1], dir, true, false);
			
		//Patrol Boat case
		case Constants.DEFAULT_PATROLBOAT_NAME:
			System.out.println("Place your Patrol Boat!");
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
	
	public void prompt(String s)
	{
		System.out.println(s);
	}
}

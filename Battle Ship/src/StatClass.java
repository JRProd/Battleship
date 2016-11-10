import javax.sql.rowset.CachedRowSet;

import game.Board;
import game.Constants;
import game.Direction;
import game.PieceOverlapException;
import pieces.Battleship;
import pieces.Carrier;
import pieces.Crusier;
import pieces.PatrolBoat;
import pieces.Submarine;

public class StatClass {
	
	static long statData[][] = new long[Constants.SIZE][Constants.SIZE];
	static long iterPerCell = 0;
	
	public static void main(String[] args) {
		Board statBoard = new Board();
		
		
		Carrier carrier;
		boolean carrierPlaced = false;
		Battleship battleship;
		boolean battleshipPlaced = false;
		Crusier crusier;
		boolean crusierPlaced = false;
		Submarine submarine;
		boolean submarinePlaced = false;
		PatrolBoat patrolBoat;
		boolean patrolBoatPlaced = false;
		
		test: for(int xC = 0; xC < Constants.SIZE; xC++)
		{
			for(int yC = 0; yC < Constants.SIZE; yC++)
			{
				carrier: for(int dC = 1; dC <= 4; dC++)
				{
					System.out.println("Carrier position :: (" + xC + " " + yC + ") " + dC);
					carrier = new Carrier(xC, yC, getDir(dC), true, false);
					
					for(int xB = 0; xB < Constants.SIZE; xB++)
					{
						for(int yB = 0; yB < Constants.SIZE; yB++)
						{
							battleship: for(int dB = 1; dB <= 4; dB++)
							{
								System.out.println("Battleship position :: (" + xB + " " + yB + ") " + dB);
								battleship = new Battleship(xB, yB, getDir(dB), true, false);
								
								for(int xc = 0; xc < Constants.SIZE; xc++)
								{
									for(int yc = 0; yc < Constants.SIZE; yc++)
									{
										crusier: for(int dc = 1; dc <= 4; dc++)
										{
											crusier = new Crusier(xc, yc, getDir(dc), true, false);
											
											for(int xS = 0; xS < Constants.SIZE; xS++)
											{
												for(int yS = 0; yS < Constants.SIZE; yS++)
												{
													submarine: for(int dS = 1; dS <= 4; dS++)
													{
														submarine = new Submarine(xS, yS, getDir(dS), true, false);
														
														for(int xP = 0; xP < Constants.SIZE; xP++)
														{
															for(int yP = 0; yP < Constants.SIZE; yP++)
															{
																patrol: for(int dP = 1; dP <= 4; dP++)
																{
																	patrolBoat = new PatrolBoat(xP, yP, getDir(dP), true, false);
																	
																	statBoard = new Board();
																	
																	try {
																		statBoard.placeShip(carrier);
																		carrierPlaced = true;
																	} catch (IndexOutOfBoundsException e) {
																		continue carrier;
																	} catch (PieceOverlapException e) {
																		continue carrier;
																	}
																	
																	try {
																		statBoard.placeShip(battleship);
																		battleshipPlaced = true;
																	} catch (IndexOutOfBoundsException e) {
																		continue battleship;
																	} catch (PieceOverlapException e) {
																		continue battleship;
																	}
																	
																	try {
																		statBoard.placeShip(crusier);
																		crusierPlaced = true;
																	} catch (IndexOutOfBoundsException e) {
																		continue crusier;
																	} catch (PieceOverlapException e) {
																		continue crusier;
																	}
																	
																	try {
																		statBoard.placeShip(submarine);
																		submarinePlaced = true;
																	} catch (IndexOutOfBoundsException e) {
																		continue submarine;
																	} catch (PieceOverlapException e) {
																		continue submarine;
																	}
																	
																	try {
																		statBoard.placeShip(patrolBoat);
																		patrolBoatPlaced = true;
																	} catch (IndexOutOfBoundsException e) {
																		continue patrol;
																	} catch (PieceOverlapException e) {
																		continue patrol;
																	}
																	
																	if(carrierPlaced && battleshipPlaced && crusierPlaced && submarinePlaced &&patrolBoatPlaced)
																	{
																		carrierPlaced = false;
																		battleshipPlaced = false;
																		crusierPlaced = false;
																		submarinePlaced = false;
																		patrolBoatPlaced = false;
																		
																		calcSquare(statBoard);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for(int i = 0; i < Constants.SIZE; i++)
		{
			for(int j = 0; j < Constants.SIZE; j++)
			{
				System.out.print(statData[j][i] + "    ");
			}
			System.out.println();
		}
		System.out.println(statBoard.printBoard());
	}
	
	public static void calcSquare(Board b)
	{
		iterPerCell += 1;
		for(int i = 0; i < Constants.SIZE; i++)
		{
			for(int j = 0; j < Constants.SIZE; j++)
			{
				char c = b.getCoord(i, j);
				if(c != '~')
				{
					statData[j][i] += 1;
				}
			}
		}
	}
	
	public static Direction getDir(int d)
	{
		switch(d)
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
}

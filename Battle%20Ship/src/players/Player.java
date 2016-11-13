package players;

import pieces.Ship;

//Create layer of abstraction for Human and AI players
public abstract class Player {
	public abstract int[] shoot();
	public abstract Ship chooseShipLocation(String ship);
	public abstract void prompt(String s);
}

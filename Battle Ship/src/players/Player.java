package players;

import pieces.Ship;

public abstract class Player {
	public abstract int[] shoot();
	public abstract Ship chooseShipLocation(String ship);
	public abstract void prompt(String s);
}

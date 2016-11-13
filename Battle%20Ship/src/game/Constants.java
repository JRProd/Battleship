package game;

public class Constants {
	public static final boolean DEBUG = false;
	public static final boolean STATS = true;
	
	public static final int SIZE = 9;
	
	public static final String DEFAULT_SHIP_NAME = "Ship";
	public static final String DEFAULT_CARRIER_NAME = "Carrier";
	public static final String DEFAULT_BATTLESHIP_NAME = "Battleship";
	public static final String DEFAULT_CRUISER_NAME = "Cruiser";
	public static final String DEFAULT_SUBMARINE_NAME = "Submarine";
	public static final String DEFAULT_PATROLBOAT_NAME = "Patrol Boat";
	
	public static final String SHIP_RES = "./res/Sprites/Ships/";
	public static final String CARRIER_RES = SHIP_RES + "Carrier.jpg";
	public static final String BATTLESHIP_RES = SHIP_RES + "Battleship.jpg";
	public static final String CRUISER_RES = SHIP_RES + "Cruiser.jpg";
	public static final String SUBMARINE_RES = SHIP_RES + "Submarine.jpg";
	public static final String PATOLBOAT_RES = SHIP_RES + "PatrolBoat.jpg";
	
	public static final double[][] AVG_BOARD_NINE = {{0.09721, 0.14207, 0.17787, 0.19550, 0.20458, 0.19548, 0.17784, 0.14205, 0.09721},
													 {0.14205, 0.18152, 0.21278, 0.22812, 0.23603, 0.22812, 0.21278, 0.18150, 0.14207}, 
													 {0.17787, 0.21282, 0.24037, 0.25381, 0.26073, 0.25382, 0.24038, 0.21281, 0.17787},
													 {0.19549, 0.22813, 0.25380, 0.26643, 0.27296, 0.26647, 0.25383, 0.22812, 0.19548},
													 {0.20459, 0.23605, 0.26073, 0.27292, 0.27948, 0.27294, 0.26073, 0.23601, 0.20457},
													 {0.19549, 0.22812, 0.25380, 0.26643, 0.27296, 0.26643, 0.25383, 0.22812, 0.19549},
													 {0.17786, 0.21279, 0.24037, 0.25380, 0.26075, 0.25381, 0.24041, 0.21280, 0.17785},
													 {0.14207, 0.18151, 0.21279, 0.22811, 0.23604, 0.22813, 0.21284, 0.18154, 0.14207},
													 {0.09721, 0.14207, 0.17785, 0.19547, 0.20456, 0.19546, 0.17788, 0.14208, 0.09722}};
	
	public static final int CLUSTERS = 3;
}

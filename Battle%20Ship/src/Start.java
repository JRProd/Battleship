import java.util.InputMismatchException;
import java.util.Scanner;

import game.Game;
import players.Human;
import players.Player;
import players.ShotRandomAI;
import players.ShotSelectiveAI;
import players.ShotSelectiveSuperiorAI;

public class Start
{
	public static void main(String[] args)
	{
		//Define scanner 
		Scanner cin = new Scanner(System.in);
		
		//Prompt the console
		System.out.println("Starting battleship!");
		System.out.print("How many players do you want :: ");
		
		//Define the two players
		Player player1 = null;
		Player player2 = null;
		
		//Check if entry was correct
		boolean correctEntry = false;
		do
		{
			//Get the console input
			int players = -1;
			
			//Check if correct
			try
			{
				//Get next int
				players = cin.nextInt();
			}catch(InputMismatchException e)
			{
				//Continue if incorrect entry
				correctEntry = false;
				continue;
			}
			
			//Switch based off players
			switch(players)
			{
			//AI only
			case 0:
				//Prompt for first AI
				System.out.print("What difficulty do you want the first AI to be (Easy, Medium, Hard) :: ");
				
				//Check Input
				boolean correctAI = false;
				do{
					//Get console input
					String ai = cin.next().toLowerCase();
					
					//Switch based off ai
					switch(ai)
					{
					//Define valid entries
					case "easy":
					case "e":
						//Set player1
						player1 = new ShotRandomAI();
						correctAI = true;
						break;
					//Define valid entries
					case "medium":
					case "med":
					case "m":
						//Set player1
						player1 = new ShotSelectiveAI();
						correctAI = true;
						break;
					//Define valid entries
					case "hard":
					case "h":
						//set player1
						player1 = new ShotSelectiveSuperiorAI();
						correctAI = true;
						break;
					//Invalid entry
					default:
						System.out.print("Please re-enter the AI difficulty (Easy, Medium, Hard) :: ");
						correctAI = false;
					}
				}while(!correctAI);
				
				//Prompt console
				System.out.print("What difficulty do you want the second AI to be (Easy, Medium, Hard) :: ");
				//Check Input
				correctAI = false;
				do{
					//Get console input
					String ai = cin.next().toLowerCase();
					
					//Switch based off ai
					switch(ai)
					{
					//Define valid entries
					case "easy":
					case "e":
						//Set player2
						player2 = new ShotRandomAI();
						correctAI = true;
						break;
					//Define valid entries
					case "medium":
					case "med":
					case "m":
						//Set player2
						player2 = new ShotSelectiveAI();
						correctAI = true;
						break;
					//Define valid entries
					case "hard":
					case "h":
						//set player2
						player2 = new ShotSelectiveSuperiorAI();
						correctAI = true;
						break;
					//Invalid entry
					default:
						System.out.print("Please re-enter the AI difficulty (Easy, Medium, Hard) :: ");
						correctAI = false;
					}
				}while(!correctAI);
				
				//End look
				correctEntry = true;
				break;
			
			//Set one player to human and other to AI
			case 1:
				player1 = new Human();
				
				//Prompt console
				System.out.print("What difficulty do you want the second AI to be (Easy, Medium, Hard) :: ");
				//Check Input
				correctAI = false;
				do{
					//Get console input
					String ai = cin.next().toLowerCase();
					
					//Switch based off ai
					switch(ai)
					{
					//Define valid entries
					case "easy":
					case "e":
						//Set player2
						player2 = new ShotRandomAI();
						correctAI = true;
						break;
					//Define valid entries
					case "medium":
					case "med":
					case "m":
						//Set player2
						player2 = new ShotSelectiveAI();
						correctAI = true;
						break;
					//Define valid entries
					case "hard":
					case "h":
						//set player2
						player2 = new ShotSelectiveSuperiorAI();
						correctAI = true;
						break;
					//Invalid entry
					default:
						System.out.print("Please re-enter the AI difficulty (Easy, Medium, Hard) :: ");
						correctAI = false;
					}
				}while(!correctAI);
				
				correctEntry = true;
				break;
			
			//Both players human
			case 2:
				//Set players to human
				player1 = new Human();
				player2 = new Human();
				correctEntry = true;
				break;
				
			//Incorrect values
			default:
				System.out.print("Please re-enter the number of players you want (0, 1, 2) :: ");
				correctEntry = false;				
			}
		}while(!correctEntry);
		
		//Create game
		Game battleship = new Game(player1, player2);
		
		//Run game
		battleship.begin();
		battleship.fight();
		battleship.end();
		
		//Close scanner
		cin.close();
		
		//Prompt console 
		System.out.println("Thanks for playing!");
	}
}

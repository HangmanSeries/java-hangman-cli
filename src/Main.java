import java.util.ArrayList;
import java.util.Scanner;

import game.Game;
public class Main {
	public static ArrayList<Game> Records; 
	public static void main(String[] args) {
		Records = new ArrayList<>();
		boolean isRunning = true; 
		Scanner scan = new Scanner(System.in);
		String player = "";
		while (isRunning == true) {
			System.out.println("----------------------------------------");
			System.out.print("Input Player name: ");
			player = scan.nextLine();	
			while(true) {
				Game game = new Game(player);
				Records.add(game);
				game.run();
				System.out.println("Wanna play again? y(yes)/n(logout)");
				if (scan.nextLine().matches("[^y]"))
					break;
				if (scan.nextLine().matches("exit")) {
					isRunning = false;
					break;
				}
			}
		}
	scan.close();
	}
}

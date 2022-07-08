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
			System.out.print("----------------------------------------");
			System.out.print("\nInput Player name: ");
			player = scan.nextLine();	
			while(true) {
				Game game = new Game(player);
				Records.add(game);
				game.run();
				System.out.println("Wanna play again? y(yes)/n(logout)");
				String s = scan.nextLine();
				if (s.matches("[^y]")){
					break;
				}
				if (s.matches("exit")) {
					isRunning = false;
					break;
				}
				System.out.println("----------------------------------------");
			}
		}
	scan.close();
	}
}

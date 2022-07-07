package game;

import java.util.Scanner;
import game.WordGenerator;
public class Game {
	private String word;

	public String player_name;
	public String category;
	public String guesses;
	public String mistakes;
	public int lives;
	public boolean isRunning;
	
	public Scanner scan;
	public WordGenerator wg;
	public Game(String player_name){
		wg = new WordGenerator(30);
		scan = new Scanner(System.in);
		
		this.player_name = player_name;
		this.setWord(wg.getWord());
		this.category = wg.category;
		this.lives = 10;
		this.guesses = "\n";
		this.mistakes = "\n";
		this.isRunning = true;
	}
	
	public void run() {
		while(isRunning) {
			System.out.println("Category: " + category);
			String input = inputLetter(scan);
			if (!input.matches("exit")) {
				input = input.trim().substring(0,1);
				this.lives += checkLetter(input, this.word, this.guesses, this.mistakes);
				this.guesses = updateGuesses(input, this.word, this.guesses);
				this.mistakes = updateMistakes(input, this.word, this.mistakes);
				printStats(this.lives,this.mistakes);
				this.isRunning = checkVictory(this.lives, this.word, this.guesses);					
			} else this.isRunning = false; 
		}
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public static String inputLetter(Scanner scan) {
		System.out.print("Input letter: ");
		String letter = " ";
		while(true) {
			letter = scan.nextLine().trim();
		    if (letter.matches("[A-Za-z]*")) {
		    	break;
		    } else {
		    	System.out.println("Please input a letter: ");
		    }
		}
		return letter;
	}
	public static int checkLetter(String input, String text) {
		if (text.contains(input))
			return 0;
		return -1;
	}
	public static int checkLetter(String input, String word, String guesses, String mistakes) {
		String s = input;
		if (word.contains(s) || guesses.contains(s) || mistakes.contains(s))
			return 0;
		return -1;
	}
	public static String updateGuesses(String input, String word, String guesses) {
		if (checkLetter(input,word)==0) {
			if (checkLetter(input,guesses)==-1)
				guesses += input.trim();
			else System.out.println("--> Letter already tried");
		}
		String regex = "[^" + guesses + "]";
		String output = word.replaceAll(regex," ");
		System.out.println(output);
		String s = "-";
		System.out.println(s = s.repeat(word.length()));
		return output;
	}
	public static String updateMistakes(String input, String word, String mistakes) {
		if (checkLetter(input,word)==-1) {
			if (checkLetter(input,mistakes)==-1) {
				mistakes += input + " ";
				System.out.println("--> Wrong Guess");
			}
			else System.out.println("--> Letter already tried");
		} 
		return mistakes;
	}
	public static boolean checkVictory(int lives, String word, String guesses) { 
		if (lives == 0) {
			System.out.println("You lose!");
			System.out.println("The word is " + word);
			return false;
		}
		if (word.equals(guesses)) {
			System.out.println("You win!");
			return false;
		}
		return true;
	}
	public static void printStats(int lives, String mistakes) {
		System.out.print("Remaining Lives: " + lives);
		System.out.print("\nMistakes: " + mistakes.trim());
		System.out.println("\n----------------------------------------");
	}
}

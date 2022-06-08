import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Hangman {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("Your file path goes here"));
		Scanner keyboard = new Scanner(System.in);
		
		List<String> words = new ArrayList<>();
		
		while(scanner.hasNext()) {
			words.add(scanner.nextLine());
		}
		
		Random rand = new Random();
		
		String word = words.get(rand.nextInt(words.size()));
		
		int wrongCount = 0;
		printHangedMan(wrongCount);
		
		List<Character> playerGuesses = new ArrayList<>();
		
		printWordState(word, playerGuesses);
		
		while (true) {
			if (wrongCount >= 6) {
				System.out.println("You have lost.");
				System.out.println("The word was " + word + ".");
				break;
			}
			
			if (!getPlayerGuess(keyboard, word, playerGuesses)) {
				wrongCount++;
			}
			
			printHangedMan(wrongCount);
			
			if (printWordState(word, playerGuesses)) {
				System.out.println("You win!");
				break;
			}
		}
	}

	private static void getPlayerWordGuess(Scanner keyboard, String word) {
		System.out.println("Please enter your guess for the word: ");
		String wordGuess = keyboard.nextLine();
		
		if (wordGuess.equals(word)) {
			System.out.println("You won by correctly guessing \"" + word + "\"!");
			System.exit(0);
		} 
		else {
			System.out.println("Incorrect!");
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void printHangedMan(int wrongCount) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(" _______");
		System.out.println(" |     |");
		if (wrongCount >= 1) {
			System.out.println(" O     |");
		}
		else {
			System.out.println("       |");
		}
		
		if (wrongCount >= 2) {
			System.out.print("\\ ");
			if (wrongCount >= 3) {
				System.out.println("/    |");
			}
			else {
				System.out.println("     |");
			}
		}
		else {
			System.out.println("       |");
		}
		
		if (wrongCount >= 4) {
			System.out.println(" |     -");
		}
		else {
			System.out.println("       -");
		}
		
		if (wrongCount >= 5) {
			System.out.print("/ ");
			if (wrongCount >= 6) {
				System.out.println("\\");
			}
			else {
				System.out.println();
			}
		}
		System.out.println("\n\n");
	}

	private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
		System.out.println("Please enter a letter, ");
		System.out.println("or type ! if you want to guess the word: ");
		String letterGuessString = getGuessAndCheckEmpty(keyboard);
		   
		char letterGuess = letterGuessString.toLowerCase().charAt(0);
		
		while(true) {
			if ((letterGuess >= 0 && letterGuess <= 32) ||
				(letterGuess >= 34 && letterGuess <= 60) || 
				(letterGuess >= 123 && letterGuess <= 127)) {
				System.out.println("Enter only a letter: ");
			}
			else if (playerGuesses.contains(letterGuess)) {
				System.out.println("Enter a letter not used before: ");
			}
			else {
				break;
			}
			letterGuessString = getGuessAndCheckEmpty(keyboard);
			letterGuess = letterGuessString.toLowerCase().charAt(0);
		}
		
		
		if (letterGuess == '!') {
			getPlayerWordGuess(keyboard, word);
		}
		else {
			playerGuesses.add(letterGuess);
		}
		
		return word.contains(letterGuessString) || letterGuess == '!';
	}

	private static String getGuessAndCheckEmpty(Scanner keyboard) {
		String letterGuessString = "";
		do {
			letterGuessString = keyboard.nextLine();
			if (letterGuessString.equals("")) {
				System.out.println("Guess was left blank. Try again: ");
			}
			else {
				break;
			}
		} while (true);
		return letterGuessString;
	}

	private static boolean printWordState(String word, List<Character> playerGuesses) {
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			}
			else {
				System.out.print("-");
			}
		}
		System.out.println();
		if (playerGuesses.size() > 0) {
			System.out.print("\nLetters used: [");
		for (int i = 0; i < playerGuesses.size() - 1; i++) {
			System.out.print(playerGuesses.get(i) + ", ");
		}
		System.out.println(playerGuesses.get(playerGuesses.size() - 1) + "]");
		}
		System.out.println();
		return (word.length() == correctCount);
	}

}

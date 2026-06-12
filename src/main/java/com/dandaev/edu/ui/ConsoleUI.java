package com.dandaev.edu.ui;

import java.io.PrintStream;
import java.util.Scanner;

import com.dandaev.edu.engine.HangmanConsoleRenderer;
import com.dandaev.edu.entities.Category;
import com.dandaev.edu.entities.Difficulty;

public final class ConsoleUI {
	private final PrintStream out;
	private final HangmanConsoleRenderer hangmanRenderer;

	public ConsoleUI() {
		this(new HangmanConsoleRenderer(), System.out);
	}

	private ConsoleUI(HangmanConsoleRenderer hangmanRenderer, PrintStream out) {
		this.hangmanRenderer = hangmanRenderer;
		this.out = out;
	}

	public void showIntro() {
		out.println("[INFO] ====================================");
		out.println("[INFO]     HANGMAN - Simple Console Game   ");
		out.println("[INFO] ====================================");
		out.println("[INFO]              +-----+  ");
		out.println("[INFO]              |     |  ");
		out.println("[INFO]                    |  ");
		out.println("[INFO]                    |  ");
		out.println("[INFO]                    |  ");
		out.println("[INFO]                    |  ");
		out.println("[INFO]             =========  ");
		out.println("[INFO] Guess the word letter by letter.");
		out.println("[INFO] You can enter only one lowercase letter (a-z).");
		out.println("[INFO] After 6 wrong attempts you lose.");
		out.println("[INFO] ====================================");
	}

	public Category categorySelection(Scanner scanner) {
		out.println("[STUP] Choose a category:");
		var categories = Category.values();
		for (int i = 0; i < categories.length; i++) {
			out.printf("[STUP] %d. %s%n", i + 1, categories[i].name());
		}

		while (true) {
			out.print("[STUP] Enter the number: ");
			var input = scanner.nextLine().trim();
			try {
				int choice = Integer.parseInt(input);
				if (choice >= 1 && choice <= categories.length) {
					return categories[choice - 1];
				}
			} catch (NumberFormatException ignored) {
			}
			out.println("[STUP] Invalid choice, please try again.");
		}
	}

	public Difficulty difficultySelection(Scanner scanner) {
		out.println("[STUP] Choose difficulty:");
		var difficulties = Difficulty.values();

		for (int i = 0; i < difficulties.length; i++) {
			out.printf("[STUP] %d. %s%n", i + 1, difficulties[i].name());
		}

		while (true) {
			out.print("[STUP] Enter the number: ");
			var input = scanner.nextLine().trim();
			try {
				int choice = Integer.parseInt(input);
				if (choice >= 1 && choice <= difficulties.length) {
					return difficulties[choice - 1];
				}
			} catch (NumberFormatException ignored) {
			}
			out.println("[STUP] Invalid choice, please try again.");
		}
	}

	public void printGame(String message) {
		out.println("[GAME] " + message);
	}

	public void printGamePrompt(String prompt) {
		out.print("[GAME] " + prompt);
	}

	public void printGameEmptyLine() {
		out.println("[GAME]");
	}

	public void printGameSeparator() {
		out.println("[GAME] ------------------------------------");
	}

	public void printGameResult(String message) {
		printGameSeparator();
		printGame(message);
	}

	public void clearConsole() {
		try {
			String os = System.getProperty("os.name").toLowerCase();

			if (os.contains("windows")) {
				new ProcessBuilder("cmd", "/c", "cls")
						.inheritIO()
						.start()
						.waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			for (int i = 0; i < 50; i++) {
				System.out.println();
			}
		}
	}

	public void showGameStartInfo(Category category, Difficulty difficulty, int wordLength) {
		out.printf("[STUP] Starting game with category: %s, difficulty: %s, the word has %d letters%n", category.name(), difficulty.name(), wordLength);
		out.println("[GAME] ====================================");
		hangmanRenderer.render(0);
	}

	public boolean askPlayAgain(Scanner scanner) {
		out.print("[GAME] Play again? (y/n): ");
		var input = scanner.nextLine().trim().toLowerCase();

		return input.equals("y") || input.equals("yes");
	}

	public void renderHangmanStage(int wrongAttempts){
		hangmanRenderer.render(wrongAttempts);
	}


}

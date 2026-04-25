package com.dandaev.edu.ui;

import static java.lang.System.*;

import java.util.Scanner;

import com.dandaev.edu.entities.Category;
import com.dandaev.edu.entities.Difficulty;

public final class ConsoleUI {

	public static void showIntro() {
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

	public static Category categorySelection(Scanner scanner) {
		// NOTE: отображение категории
		out.println("[STUP] Choose a category:");
		var categories = Category.values();
		for (int i = 0; i < categories.length; i++) {
			out.printf("[STUP] %d. %s%n", i + 1, categories[i].name());
		}

		// NOTE: отображение выбора категории
		while (true) {
			out.print("[STUP] Enter the number: ");
			var input = scanner.nextLine().trim();
			try {
				int choice = Integer.parseInt(input);
				if (choice >= 1 && choice <= categories.length) {
					return categories[choice - 1];
				}
			} catch (NumberFormatException ignored) {
				/* NOTE: никак не реагируем */
			}
			out.println("[STUP] Invalid choice, please try again.");
		}
	}

	public static Difficulty difficultySelection(Scanner scanner) {
		// NOTE: отображение вариантов сложности
		out.println("[STUP] Choose difficulty:");
		var difficulties = Difficulty.values();

		for (int i = 0; i < difficulties.length; i++) {
			out.printf("[STUP] %d. %s%n", i + 1, difficulties[i].name());
		}

		// NOTE: отображение выбора сложности
		while (true) {
			out.print("[STUP] Enter the number: ");
			var input = scanner.nextLine().trim();
			try {
				int choice = Integer.parseInt(input);
				if (choice >= 1 && choice <= difficulties.length) {
					return difficulties[choice - 1];
				}
			} catch (NumberFormatException ignored) {
				/* NOTE: никак не реагируем */
			}
			out.println("[STUP] Invalid choice, please try again.");
		}
	}

	// @formatter:off
	public static void showGameStartInfo(Category category, Difficulty difficulty, int wordLength) {
		out.printf("[STUP] Starting game with category: %s, difficulty: %s, the word has %d letters%n", category.name(), difficulty.name(), wordLength);
		out.println("[GAME] ====================================");
	}

	public static boolean askPlayAgain(Scanner scanner) {
		out.print("[GAME] Play again? (y/n): ");
		var input = scanner.nextLine().trim().toLowerCase();

		return input.equals("y") || input.equals("yes");
	}
}

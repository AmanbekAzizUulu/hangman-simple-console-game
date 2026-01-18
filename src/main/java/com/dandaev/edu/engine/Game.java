package com.dandaev.edu.engine;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import com.dandaev.edu.entities.Category;
import com.dandaev.edu.entities.Difficulty;
import com.dandaev.edu.entities.GameStatus;
import com.dandaev.edu.utils.FileWordProvider;

public final class Game {
	private final static GameEngine engine = new GameEngine();

	private Game() {
	}

	public static void start() {
		var provider = new FileWordProvider("/words.csv");
		var word = provider.getRandom(Category.MEDICINE,Difficulty.EASY).orElseThrow(() -> new IllegalStateException("No words for chosen filters"));
		var guessedCharacters = new HashSet<Character>();

		String secret = word.getText();  // NOTE Это то, что угадывают
		String hint = word.getHint();    // NOTE Подсказка (если нужна)

		try (var scanner = new Scanner(in)) {
			while (true) {
				out.print("[GAME] Type in your guess [single letter]: ");
				var userStringInput = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
				if (userStringInput.length() != 1) {
					out.println("[GAME] Pls, type in single letter");
					continue;
				}
				if (!userStringInput.matches("[a-z]")) {
					out.println("[GAME] Please use latin letters (a-z)");
					continue;
				}

				char guess = userStringInput.charAt(0);

				var gameResult = engine.guess(secret, guessedCharacters, guess);
				var viewStr = engine.buildView(secret, guessedCharacters);

				if (gameResult.getStatusAfter() != GameStatus.WON) {
					// Сообщение от движка (и для already guessed, и для hit/miss, и для win)
					out.println("[GAME] " + gameResult.getMessage());
					out.println("[GAME] " + viewStr);
				} else if (gameResult.getStatusAfter() == GameStatus.WON) {
					out.println("[GAME]");
					out.println("[GAME] ------------------------------------");
					out.println("[GAME]");
					out.println("[GAME] You Won!");
					out.println("[GAME] Guessed word: " + viewStr);
					break;
				}
			}
		}
	}
}

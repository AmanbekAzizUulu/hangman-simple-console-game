package com.dandaev.edu.engine;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import com.dandaev.edu.entities.GameStatus;
import com.dandaev.edu.entities.RoundResult;
import com.dandaev.edu.ui.ConsoleUI;

public class GameRound {
	private final String secret;
	private final Set<Character> guessed;
	private int wrongAttempts;

	public GameRound(String secret) {
		this.secret = secret;

		this.guessed = new HashSet<>();
		this.wrongAttempts = 0;
	}

	public RoundResult play(Scanner scanner, GameEngine engine, ConsoleUI consoleUI) {
		while (true) {
			consoleUI.printGamePrompt("Type in your guess [single letter]: ");
			var userStringInput = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

			if (userStringInput.length() != 1) {
				consoleUI.printGame("Pls, type in single letter");
				continue;
			}
			if (!userStringInput.matches("[a-z]")) {
				consoleUI.printGame("Please use latin letters (a-z)");
				continue;
			}

			var guess = userStringInput.charAt(0);

			var isNewWrong = secret.indexOf(guess) == -1 && !guessed.contains(guess);
			if (isNewWrong) {
				wrongAttempts++;
			}

			var gameResult = engine.guess(secret, guessed, guess);
			var viewStr = getCurrentView();

			consoleUI.renderHangmanStage(wrongAttempts);
			consoleUI.printGame(gameResult.getMessage());
			consoleUI.printGame(viewStr);

			if (gameResult.getStatusAfter() == GameStatus.WON) {
				consoleUI.printGameEmptyLine();
				consoleUI.printGameResult("You Won!");
				consoleUI.printGame("Guessed word: " + viewStr);
				return RoundResult.WON;
			}

			if (wrongAttempts >= HangmanStages.values().length - 1) {
				consoleUI.printGameEmptyLine();
				consoleUI.printGameResult("You Lost!");
				consoleUI.printGame("The word was: " + secret);
				return RoundResult.LOST;
			}
		}
	}

	private String getCurrentView() {
		StringBuilder view = new StringBuilder();
		for (int i = 0; i < secret.length(); i++) {
			char c = secret.charAt(i);
			view.append(guessed.contains(c) ? c : '_');
		}
		return view.toString();
	}
}

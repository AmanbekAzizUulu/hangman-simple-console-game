package com.dandaev.edu.engine;

import static java.lang.System.*;

import java.util.HashSet;
import java.util.Scanner;

public final class Game {
	private final static GameEngine engine = new GameEngine();

	private Game() {
	}

	public static void start() {
		var word = "test";
		var view = new StringBuilder();
		var isFound = false;
		var guessedCharacters = new HashSet<Character>();

		try (var scanner = new Scanner(in)) {

			while (true) {
				view.setLength(0);

				out.print("[GAME] Type in your guess [single letter]: ");
				var userStringInput = scanner.nextLine().toLowerCase();

				if (userStringInput.length() != 1) {
					out.println("[GAME] Pls, type in single letter");
					continue;
				}

				char guess = userStringInput.charAt(0);

				isFound = engine.applyGuess(word, guessedCharacters, guess);
				var viewStr = engine.buildView(word, guessedCharacters);

				if (isFound) {
					out.println("[GAME] Correct letter!");
					out.println("[GAME] " + viewStr);
					if (engine.isWin(viewStr)) {
						out.println("[GAME] You Won!");
						break;
					}
				} else {
					out.println("[GAME] Incorrect letter! Try again!");
				}
			}
		}
	}
}

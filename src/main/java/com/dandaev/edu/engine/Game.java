package com.dandaev.edu.engine;

import static java.lang.System.out;
import static com.dandaev.edu.ui.ConsoleUI.*;
import static java.lang.System.in;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import com.dandaev.edu.entities.GameStatus;
import com.dandaev.edu.ui.ConsoleUI;
import com.dandaev.edu.utils.FileWordProvider;

public final class Game {
	private static final int MAX_WRONG_ATTEMPTS = 6;
	private static final String[] HANGMAN_STAGES = {
					"""
							   +---+
							   |   |
							       |
							       |
							       |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							       |
							       |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							   |   |
							       |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							  /|   |
							       |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							  /|\\  |
							       |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							  /|\\  |
							  /    |
							       |
							=========
					""",
					"""
							   +---+
							   |   |
							   O   |
							  /|\\  |
							  / \\  |
							       |
							=========
					"""
	};

	private final static GameEngine engine = new GameEngine();

	private Game() {
	}

	public static void start() {
		try (var scanner = new Scanner(in)) {
			showIntro();
			while (true) {
				// NOTE: настройка игры – выбор категории и сложности
				var settings = GameSetup.configureGame(scanner);

				// NOTE: получение слова согласно выбору
				var provider = new FileWordProvider("/words.csv");
				var word = provider.getRandom(settings.category(), settings.difficulty()).orElseThrow(() -> new IllegalStateException("No words for chosen filters"));

				var guessedCharacters = new HashSet<Character>();
				var secret = word.getText();
				var wrongAttempts = 0;

				// NOTE: вывод информации об игре
				showGameStartInfo(settings.category(), settings.difficulty(), secret.length());

				// NOTE: игровой цикл
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

					var guess = userStringInput.charAt(0);

					var isNewWrong = secret.indexOf(guess) == -1 && !guessedCharacters.contains(guess);
					if (isNewWrong) {
						wrongAttempts++;
					}

					var gameResult = engine.guess(secret, guessedCharacters, guess);
					var viewStr = engine.buildView(secret, guessedCharacters);

					var stage = HANGMAN_STAGES[Math.min(wrongAttempts, MAX_WRONG_ATTEMPTS)];
					for (String line : stage.split("\n")) {
						out.println("[GAME] " + line);
					}
					out.println("[GAME] " + gameResult.getMessage());
					out.println("[GAME] " + viewStr);

					if (gameResult.getStatusAfter() == GameStatus.WON) {
						out.println("[GAME]");
						out.println("[GAME] ------------------------------------");
						out.println("[GAME] You Won!");
						out.println("[GAME] Guessed word: " + viewStr);
						break;
					}

					if (wrongAttempts >= MAX_WRONG_ATTEMPTS) {
						out.println("[GAME]");
						out.println("[GAME] ------------------------------------");
						out.println("[GAME] You Lost! The word was: " + secret);
						break;
					}
				}

				// NOTE: выход из игрового цикла (раунд завершён) — спрашиваем о повторе
				if (!ConsoleUI.askPlayAgain(scanner)) {
					out.println("[GAME] Goodbye!");
					break;
				}
			}
		}
	}
}

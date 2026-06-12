package com.dandaev.edu.engine;

import java.util.Scanner;

import com.dandaev.edu.ui.ConsoleUI;
import com.dandaev.edu.utils.FileWordProvider;
import com.dandaev.edu.utils.WordProvider;

public final class Game {
	private final GameEngine engine;
	private final ConsoleUI consoleUI;
	private final WordProvider wordProvider;

	private Game(GameEngine engine, ConsoleUI consoleUI, WordProvider wordProvider) {
		this.engine = engine;
		this.consoleUI = consoleUI;
		this.wordProvider = wordProvider;
	}

	public Game() {
		this(new GameEngine(), new ConsoleUI(), new FileWordProvider("/words.csv"));
	}

	public void start() {
		try (var scanner = new Scanner(System.in)) {
			while (true) {
				consoleUI.showIntro();

				var category = consoleUI.categorySelection(scanner);
				var difficulty = consoleUI.difficultySelection(scanner);
				var settings = new GameSettings(category, difficulty);

				var word = wordProvider.getRandom(settings.category(), settings.difficulty()).orElseThrow(() -> new IllegalStateException("No words for chosen filters"));

				var secret = word.getText();

				consoleUI.showGameStartInfo(settings.category(), settings.difficulty(), secret.length());

				var gameRound = new GameRound(secret);

				gameRound.play(scanner, engine, consoleUI);

				if (!consoleUI.askPlayAgain(scanner)) {
					consoleUI.printGame("Goodbye!");
					break;
				}
				
				consoleUI.clearConsole();
				consoleUI.showIntro();
			}
		}
	}
}

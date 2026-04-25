package com.dandaev.edu.engine;

import java.util.Scanner;

import com.dandaev.edu.ui.ConsoleUI;

public class GameSetup {
	public static GameSettings configureGame(Scanner scanner) {
		var category = ConsoleUI.categorySelection(scanner);
		var difficulty = ConsoleUI.difficultySelection(scanner);
		return new GameSettings(category, difficulty);
	}
}

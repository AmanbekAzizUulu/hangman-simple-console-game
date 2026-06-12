package com.dandaev.edu.engine;


public class HangmanConsoleRenderer {

	public void render(int wrongAttempts) {
		HangmanStages stage = selectStage(wrongAttempts);

		stage.getPicture().lines().forEach(line -> System.out.println("[GAME] " + line));
	}

	private HangmanStages selectStage(int wrongAttempts){
		HangmanStages[] values = HangmanStages.values();

		int maxIndex = values.length - 1;
		int index = Math.min(wrongAttempts, maxIndex);

		return values[index];
	}
}

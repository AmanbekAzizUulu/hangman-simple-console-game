package com.dandaev.edu.engine;

import java.util.Set;

import com.dandaev.edu.entities.GameStatus;
import com.dandaev.edu.entities.GuessResult;

public final class GameEngine {
	public GuessResult guess(String word, Set<Character> guessed, char guess) {
		GuessResult result = new GuessResult();

		if (guessed.contains(guess)) {
			result.setAccepted(false);
			result.setAlreadyGuessed(true);
			result.setHit(false);
			result.setStatusAfter(GameStatus.IN_PROGRESS);
			result.setMessage("Letter already guessed");
			return result;
		}

		boolean hit = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess) {
				hit = true;
				break;
			}
		}

		guessed.add(guess);

		result.setAccepted(true);
		result.setAlreadyGuessed(false);
		result.setHit(hit);

		if (allLettersGuessed(word, guessed)) {
			result.setStatusAfter(GameStatus.WON);
			result.setMessage("You won!");
		} else {
			result.setStatusAfter(GameStatus.IN_PROGRESS);
			result.setMessage(hit ? "Correct letter" : "Incorrect letter");
		}

		return result;
	}

	private boolean allLettersGuessed(String word, Set<Character> guessed) {
		for (int i = 0; i < word.length(); i++) {
			if (!guessed.contains(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	protected boolean isWin(String view) {
		return view.indexOf('_') == -1;
	}
}

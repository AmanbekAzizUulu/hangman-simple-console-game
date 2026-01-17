package com.dandaev.edu.engine;

import java.util.HashSet;

public final class GameEngine {
	protected boolean applyGuess(String word, HashSet<Character> guessed, char guess) {
		boolean found = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess) {
				found = true;
				guessed.add(guess);
			}
		}
		return found;
	}

	protected String buildView(String word, HashSet<Character> guessed) {
		var view = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			view.append( guessed.contains(c) ? c : '_');
		}
		return view.toString();
	}

	protected boolean isWin(String view) {
		return view.indexOf('_') == -1;
	}
}

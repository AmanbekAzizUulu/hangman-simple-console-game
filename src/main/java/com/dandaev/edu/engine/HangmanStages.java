package com.dandaev.edu.engine;

public enum HangmanStages {
	EMPTY_GALLOWS(0,
			"""
					    +---+
					    |   |
					        |
					        |
					        |
					        |
					=========
					"""),
	HEAD(1,
			"""
					    +---+
					    |   |
					    O   |
					        |
					        |
					        |
					=========
					"""),
	BODY(2,
			"""
					    +---+
					    |   |
					    O   |
					    |   |
					        |
					        |
					=========
					"""),
	LEFT_ARM(3,
			"""
					    +---+
					    |   |
					    O   |
					   /|   |
					        |
					        |
					=========
					"""),
	RIGHT_ARM(4,
			"""
					    +---+
					    |   |
					    O   |
					   /|\\  |
					        |
					        |
					=========
					"""),
	LEFT_LEG(5,
			"""
					    +---+
					    |   |
					    O   |
					   /|\\  |
					   /    |
					        |
					=========
					"""),
	HANGED(6,
			"""
					    +---+
					    |   |
					    O   |
					   /|\\  |
					   / \\  |
					        |
					=========
					""");

	private final int wrongAttempts;
	private final String picture;

	HangmanStages(int wrongAttempts, String picture) {
		this.wrongAttempts = wrongAttempts;
		this.picture = picture;
	}

	public String getPicture() {
		return picture;
	}

	public int getWrongAttempts() {
		return wrongAttempts;
	}
}

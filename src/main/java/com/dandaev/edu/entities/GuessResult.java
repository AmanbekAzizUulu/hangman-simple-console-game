package com.dandaev.edu.entities;

public final class GuessResult {
	private boolean accepted;
	private boolean alreadyGuessed;
	private boolean hit;
	private GameStatus statusAfter;
	private String message;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isAlreadyGuessed() {
		return alreadyGuessed;
	}

	public void setAlreadyGuessed(boolean alreadyGuessed) {
		this.alreadyGuessed = alreadyGuessed;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public GameStatus getStatusAfter() {
		return statusAfter;
	}

	public void setStatusAfter(GameStatus statusAfter) {
		this.statusAfter = statusAfter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

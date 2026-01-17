package com.dandaev.edu.entities;

public final class Player {
	private String name;
	private Integer winCount;
	private Integer loseCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWinCount() {
		return winCount;
	}

	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}

	public Integer getLoseCount() {
		return loseCount;
	}

	public void setLoseCount(Integer loseCount) {
		this.loseCount = loseCount;
	}

}

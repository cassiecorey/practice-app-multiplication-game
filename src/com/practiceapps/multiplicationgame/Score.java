package com.practiceapps.multiplicationgame;

public class Score implements Comparable<Score> {

	String name;
	int value;
	
	public Score(String name, String value) {
		this.name = name;
		this.value = Integer.parseInt(value);
	}

	public String getName() {
		return name;
	}
	
	public int getScore() {
		return value;
	}
	
	@Override
	public int compareTo(Score another) {
		return another.getScore()>value? 1 : another.getScore()<value? -1 : 0;
	}

}

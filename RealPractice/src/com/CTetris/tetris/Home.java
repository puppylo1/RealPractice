package com.CTetris.tetris;

public class Home {
	public static void main(String[] args) {
		GameSingle gameSingle = new GameSingle();
		int score = gameSingle.start();
		System.out.println(score);
	}
}

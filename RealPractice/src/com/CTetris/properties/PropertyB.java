package com.CTetris.properties;

import com.CTetris.tetris.GameSingle;
import com.CTetris.tetris.TetrominoFactory;

public class PropertyB implements PropertyAction{
	public static final int MAX = 6;
	public void perform() {
		if(GameSingle.charge == 0) {
			GameSingle.score += 1000;
			GameSingle.charge = MAX;
		}
	}
	public void addEnergy(int lines) {
		GameSingle.charge -= lines;
		if(GameSingle.charge < 0) GameSingle.charge = 0;
	}
}

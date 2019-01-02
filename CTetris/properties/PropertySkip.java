package com.CTetris.properties;

import com.CTetris.tetris.GameSingle;
import com.CTetris.tetris.TetrominoFactory;

public class PropertySkip implements PropertyInitiative{
	public static final int MAX = 1;
	public void perform() {
		if(GameSingle.charge == 0) {
			GameSingle.tetromino = GameSingle.nextone;
			GameSingle.nextone = TetrominoFactory.ranShape();
			GameSingle.charge = MAX;
		}
	}
	public void addEnergy(int lines) {
		GameSingle.charge -= lines;
		if(GameSingle.charge < 0) GameSingle.charge = 0;
	}
	public String toString() {
		return "Skip";
	}
}

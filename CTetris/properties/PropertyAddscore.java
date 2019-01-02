package com.CTetris.properties;

import com.CTetris.tetris.GameSingle;
import com.CTetris.tetris.TetrominoFactory;

public class PropertyAddscore implements PropertyInitiative{
	public static final int MAX = 4;
	public void perform() {
		if(GameSingle.charge == 0) {
			GameSingle.score += 100;
			GameSingle.charge = MAX;
		}
	}
	public void addEnergy(int lines) {
		GameSingle.charge -= lines;
		if(GameSingle.charge < 0) GameSingle.charge = 0;
	}
	public String toString() {
		return "Addscore";
	}
}

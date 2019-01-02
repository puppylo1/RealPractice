package com.CTetris.properties;

import com.CTetris.tetris.GameSingle;

public class PropertyAddtime implements PropertyInitiative {
	public static final int MAX = 4;
	public void perform() {
		if(GameSingle.charge == 0) {
			GameSingle.remain += 20;
			GameSingle.charge = MAX;
		}
	}
	public void addEnergy(int lines) {
		GameSingle.charge -= lines;
		if(GameSingle.charge < 0) GameSingle.charge = 0;
	}
	public String toString() {
		return "Addtime";
	}
}

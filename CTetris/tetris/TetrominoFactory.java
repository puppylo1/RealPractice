package com.CTetris.tetris;

import java.util.Random;

public class TetrominoFactory {
	public static Tetromino ranShape() {
		Random random = new Random();
		int index = random.nextInt(7);
		switch (index) {
		case 0:return getI();
		case 1:return getJ();
		case 2:return getL();
		case 3:return getO();
		case 4:return getS();
		case 5:return getT();
		case 6:return getZ();
		}
		return null;
	}
	
	public static Tetromino getI() {
		return new Tetromino(0, 0, 5, 1, 5, 2, 5, 3, 5);	
	}
	
	public static Tetromino getJ() {
		return new Tetromino(1, 2, 5, 0, 6, 2, 6, 1, 6);
	}
	
	public static Tetromino getL() {
		return new Tetromino(2, 2, 6, 0, 5, 2, 5, 1, 5);
	}
	
	public static Tetromino getO() {
		return new Tetromino(3, 0, 5, 0, 6, 1, 5, 1, 6);	
	}
	
	public static Tetromino getS() {
		return new Tetromino(4, 1, 4, 1, 5, 0, 5, 0, 6);
	}
	
	public static Tetromino getT() {
		return new Tetromino(5, 0, 4, 0, 6, 0, 5, 1, 5);	
	}
	
	public static Tetromino getZ() {
		return new Tetromino(6, 0, 4, 0, 5, 1, 5, 1, 6);
	}

}

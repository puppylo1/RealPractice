package com.CTetris.tetris;

public class RemoveResult {
	private static int a = 0;
	private static int b = 1;
	private static int c;
	
	public static void addALine() {
		// 确定最高的行
		int rowstart = GameSingle.ROWS;		
		for(int row = 0; row < GameSingle.ROWS; row ++) {
			for(int col = 0; col < GameSingle.COLS; col ++) {
				if(GameSingle.wall[row][col] != null) {
					rowstart = row;
					break;
				}
			}
			if(rowstart != GameSingle.ROWS) break;
		}
		// 把所有行都上抬
		for(int row = rowstart ;row < GameSingle.ROWS; row ++) {
			for(int col = 0; col < GameSingle.COLS; col ++) {
				GameSingle.wall[row-1][col] = GameSingle.wall[row][col];
			}
		}
	    // 加上一行错落的花行
		for(int col = a;col < GameSingle.COLS; col += 2) {
			GameSingle.wall[GameSingle.ROWS-1][col] =
					new Cell(Resources.cellImages[0][0].get(0));
		}
		for(int col = b;col < GameSingle.COLS; col += 2) {
			GameSingle.wall[GameSingle.ROWS-1][col] = null;
		}
		// 交换a和b
		int c = a;
		a = b;
		b = a;
	}
	
	
}

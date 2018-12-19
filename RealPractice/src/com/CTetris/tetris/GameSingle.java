package com.CTetris.tetris;

import javax.swing.JFrame;

public class GameSingle {
	public static final int ROWS = 20;
	public static final int COLS = 10;
	/**
	 * 下一个下落对象
	 */
	public static Tetromino nextone;
	/**
	 * 当前下落对象
	 */
	public static Tetromino tetromino;
	/**
	 * 提示信息
	 */
	public static int score = 0;
	public static int remain = 120;
	public static int charge = 0;
	/**
	 * 墙
	 */
	public static Cell[][] wall = new Cell[ROWS][COLS];

	public static JFrame frame;
	
	public volatile static boolean isGameOver = false;
	
	public GameSingle() {
	}
	
	public int start() {
		frame = new JFrame();	
		Tetris tetris = new Tetris();
		frame.add(tetris);
		frame.setSize(525, 600);
		frame.setLocationRelativeTo(null);
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(
				JFrame.EXIT_ON_CLOSE);
		//显示窗口时候会尽快调用面板的paint()方法
		//绘制显示的内容（绘制背景等）
		frame.setVisible(true);
		//被重写的paint（）方法绘制了图片！
		tetris.action();
		while(!isGameOver) {
			//System.out.println(isGameOver);
		}
		return score;
	}
}
package com.CTetris.tetris;

import javax.swing.JFrame;

public class GameSingle {
	public static final int ROWS = 20;
	public static final int COLS = 10;
	/**
	 * ��һ���������
	 */
	public static Tetromino nextone;
	/**
	 * ��ǰ�������
	 */
	public static Tetromino tetromino;
	/**
	 * ��ʾ��Ϣ
	 */
	public static int score = 0;
	public static int remain = 120;
	public static int charge = 0;
	/**
	 * ǽ
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
		//��ʾ����ʱ��ᾡ���������paint()����
		//������ʾ�����ݣ����Ʊ����ȣ�
		frame.setVisible(true);
		//����д��paint��������������ͼƬ��
		tetris.action();
		while(!isGameOver) {
			//System.out.println(isGameOver);
		}
		return score;
	}
}
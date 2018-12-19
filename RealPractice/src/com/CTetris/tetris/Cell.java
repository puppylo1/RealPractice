package com.CTetris.tetris;

import java.awt.image.BufferedImage;

public class Cell {
	private int row;
	private int col;
	private BufferedImage bgImage;
	private String type;
	
	public Cell() {
	}
	/**
	 * ���ӵĹ��췽������ʼ��Cell������
	 * @param row	�������������
	 * @param col	�������������
	 * @param bhImage	�������ͼƬ����ɫ��
	 */
	public Cell(BufferedImage bgImage) {
		this.bgImage = bgImage;
		this.type = "Neutral";
	}
	public Cell(int row, int col, BufferedImage bgImage, String type) {
		this.row 	 = row;
		this.col 	 = col;
		this.bgImage = bgImage;
		this.type = type;
	}
	/**
	 * ���ӵ����䣬���ƣ����Ʒ���
	 */
	public void moveDown() {
		row++;
	}
	public void moveLeft() {
		col--;
	}
	public void moveRight() {
		col++;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public BufferedImage getBgImage() {
		return bgImage;
	}
	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

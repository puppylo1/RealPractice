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
	 * 格子的构造方法，初始化Cell对象用
	 * @param row	传入格子所在行
	 * @param col	传入格子所在列
	 * @param bhImage	传入格子图片（颜色）
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
	 * 格子的下落，左移，右移方法
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

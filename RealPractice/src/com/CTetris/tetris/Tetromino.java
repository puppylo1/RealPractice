package com.CTetris.tetris;

import java.util.Random;


public class Tetromino{

	protected Cell[] cells = new Cell[4];
	
	protected String[] types = {"Neutral", "Negative", "Positive"};
	/*
	 * Tetromino类中的移动方法，通过重写和向上调用父类中方法实现
	 * @see com.baishu.tetris.Cell#moveDown()
	 */
	public void moveDown() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveDown();
		}
	}
	public void moveLeft() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveLeft();
		}
	}
	public void moveRight() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveRight();
		}
	}
	
	public Tetromino(int k, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
		cells[0] = new Cell(x1, y1, Resources.cellImages[k][0].get(0), types[0]);
		cells[1] = new Cell(x2, y2, Resources.cellImages[k][0].get(0), types[0]);
		cells[2] = new Cell(x3, y3, Resources.cellImages[k][0].get(0), types[0]);
		Random random = new Random();
		int index = random.nextInt(3);
		cells[3] = new Cell(x4, y4, Resources.cellImages[k][index].get(0), types[index]);
	}

	/**
	 * Tetromino对象旋转方法
	 * @return Tetromino对象旋转结果，以Cell数组形式返回，用以判定旋转结果是否合法
	 */
	public Cell[] spin() {
		//因为学的不多，暂时用下方法判断是否调用对象为O型对象,其他方法在类中重写toString(),equals()等
		Cell[] iCells = new Cell[4];
		int iRow = this.cells[2].getRow();
		int iCol = this.cells[2].getCol();
		for (int i = 0; i < this.cells.length; i++) {
			int nRow = this.cells[i].getRow();
			int nCol = this.cells[i].getCol();
			//对iCells内元素进行初始化，防止出现nullPointException异常
			iCells[i] = new Cell(iRow-iCol+nCol, iRow+iCol-nRow, this.cells[i].getBgImage(), this.cells[i].getType());
		}
		return iCells;
	}
	/* 
	 * 重写toString() 方法，为了能够方便的测试
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < cells.length; i++){
			str = str + cells[i].getRow()+" "+cells[i].getCol()+"\n";
		}
		return str;
	}
}

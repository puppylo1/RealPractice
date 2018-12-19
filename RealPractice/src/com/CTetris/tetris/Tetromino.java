package com.CTetris.tetris;

import java.util.Random;


public class Tetromino{

	protected Cell[] cells = new Cell[4];
	
	protected String[] types = {"Neutral", "Negative", "Positive"};
	/*
	 * Tetromino���е��ƶ�������ͨ����д�����ϵ��ø����з���ʵ��
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
	 * Tetromino������ת����
	 * @return Tetromino������ת�������Cell������ʽ���أ������ж���ת����Ƿ�Ϸ�
	 */
	public Cell[] spin() {
		//��Ϊѧ�Ĳ��࣬��ʱ���·����ж��Ƿ���ö���ΪO�Ͷ���,����������������дtoString(),equals()��
		Cell[] iCells = new Cell[4];
		int iRow = this.cells[2].getRow();
		int iCol = this.cells[2].getCol();
		for (int i = 0; i < this.cells.length; i++) {
			int nRow = this.cells[i].getRow();
			int nCol = this.cells[i].getCol();
			//��iCells��Ԫ�ؽ��г�ʼ������ֹ����nullPointException�쳣
			iCells[i] = new Cell(iRow-iCol+nCol, iRow+iCol-nRow, this.cells[i].getBgImage(), this.cells[i].getType());
		}
		return iCells;
	}
	/* 
	 * ��дtoString() ������Ϊ���ܹ�����Ĳ���
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

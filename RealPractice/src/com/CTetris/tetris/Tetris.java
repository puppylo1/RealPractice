package com.CTetris.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.RowSet;
import javax.swing.JPanel;

import com.CTetris.properties.PropertyA;
import com.CTetris.properties.PropertyAction;
import com.CTetris.properties.PropertyB;


public class Tetris extends JPanel{
	/**
	 * 各种状态，STATE 程序状态
	 */
	private boolean STATE = true;
	/**
	 * 格子长度的换算
	 */
	public static final int CELL_SIZE = 26;

	private PropertyAction property1 = new PropertyA();
	
	private PropertyAction property2;
	
	public void action() {
		GameSingle.tetromino = TetrominoFactory.ranShape();
		GameSingle.nextone = TetrominoFactory.ranShape();
	
		KeyListener kl = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				keyMoveAction(k);
				repaint();
			}
		};
		//Tetris 是面板类型 l 监听 面板的动作
		this.addKeyListener(kl);
		this.setFocusable(true);
		//request 请求  Focus 焦点  
		this.requestFocus();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
			int moveIndex = 0;
			int speed = 5*5;
			@Override
			public void run() {
				if (STATE) {
					//speed = speed*level;
					if (moveIndex % speed == 0) {
						moveDownAction();
						moveIndex = 0;
						GameSingle.remain --;
					}
				}
				moveIndex++;
				repaint();
			}
		};
		timer.schedule(task, 10, 20);
	}

	/**
	 * @param k
	 */
	public void keyMoveAction(int k) {
		switch (k) {
			case KeyEvent.VK_RIGHT:moveRightAction();break;
			case KeyEvent.VK_LEFT:moveLeftAction();break;
			case KeyEvent.VK_DOWN:moveDownAction();break;
			case KeyEvent.VK_UP:spinCellAction();break;
			case KeyEvent.VK_P:STATE = false;break;
			case KeyEvent.VK_C:STATE = true;break; 
			case KeyEvent.VK_Q:System.exit(0);break;
			case KeyEvent.VK_SPACE:moveToBottomAction();break;
			case KeyEvent.VK_1:property1.perform();break;
			case KeyEvent.VK_ENTER:closeAction();break;
			default:System.out.println("invalid key");break;
		}
	}
	
	public void closeAction() {
		if(GameSingle.isGameOver) {
			GameSingle.frame.dispose();
		}
	}
	/**
	 * 旋转方法
	 */
	public void spinCellAction() {
		Cell[] nCells = GameSingle.tetromino.spin(); 
		if (nCells == null) return;
		for (int i = 0; i < nCells.length; i++) {
			int nRow = nCells[i].getRow();
			int nCol = nCells[i].getCol();
			//System.out.println(nRow+" "+nCol);
			if (nRow < 0 || nRow >= GameSingle.ROWS || nCol < 0 
					|| nCol >= GameSingle.COLS || GameSingle.wall[nRow][nCol] != null) return;
		}
		//System.out.println(111);
		GameSingle.tetromino.cells = nCells;
	}
	/**
	 * 
	 */
	public void moveLeftAction() {
		if (canLeftMove()&&!isBottom()) {
			GameSingle.tetromino.moveLeft();
		}
	}
	/**
	 * 
	 */
	public void moveRightAction() {
		if (canRightMove()&&!isBottom()) {
			GameSingle.tetromino.moveRight();
		}
	}
	/**
	 * 
	 */
	public void moveDownAction() {
		if (GameSingle.tetromino == null) return;
		if (!isBottom()) {
			GameSingle.tetromino.moveDown();
		}
	}
	
	public void moveToBottomAction() {
		if (GameSingle.tetromino == null) return;
		while (!isBottom()) {
			GameSingle.tetromino.moveDown();
		}
	}
	/**
	 * 
	 */
	public void removeLine() {
		boolean flag = true;
		int rowStart = 20;
		int lines = 0;
		int positives = 0;
		int negatives = 0;
		for (int row = 0; row < GameSingle.ROWS; row++) {
			for (int col = 0; col < GameSingle.COLS; col++) {
				if (GameSingle.wall[row][col] == null) {
					flag = false;
					break;
				}
			}
			if (flag) {
				lines ++;
				for (int col = 0; col < GameSingle.COLS; col++) {
					if(GameSingle.wall[row][col].getType().equals("Positive")) {
						positives ++;
					}else if(GameSingle.wall[row][col].getType().equals("Negative")) {
						negatives ++;
					}
					GameSingle.wall[row][col] = null;
				} 
				rowStart = row;
				for (int row1 = rowStart; row1 > 0; row1--) {
					for (int col1 = 0; col1 < GameSingle.COLS; col1++) {
						GameSingle.wall[row1][col1] = GameSingle.wall[row1-1][col1];
					}
				}
			}else {
				flag = true;
			}
		}
		property1.addEnergy(lines);
		addScore(lines);
		removeResult(positives, negatives);
	}
	// 加分
	public void addScore(int lines)
	{
		switch(lines) {
		case 1:GameSingle.score += 10;break;
		case 2:GameSingle.score += 30;break;
		case 3:GameSingle.score += 60;break;
		case 4:GameSingle.score += 100;break;
		}
	}
	// 移除的结果
	public void removeResult(int positives, int negatives) {
		if(positives > negatives) {
			GameSingle.score += 100;
		}
		if(positives < negatives) {
			RemoveResult.addALine();
		}
	}
	
	/**
	 * @return 是否可以继续下落
	 */
	public boolean isBottom() {
		if (GameSingle.tetromino == null) return false;
		Cell[] cells = GameSingle.tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			int col = c.getCol();
			int row = c.getRow();
			//此处利用“||”的逻辑短路特点，减少代码量
			if ((row+1) == GameSingle.ROWS || GameSingle.wall[row+1][col] != null) {
				//当确定当前对象运动到底部即停止时，将该对象的cells内的格子元素存入wall内
				for(int j = 0; j < cells.length; j++) {
					Cell cell = cells[j];
					int col1 = cell.getCol();
					int row1 = cell.getRow();
					GameSingle.wall[row1][col1] = cell;
				}
				removeLine();
				//控制背景图片
				GameSingle.tetromino = GameSingle.nextone;
				GameSingle.nextone = TetrominoFactory.ranShape();
				return true;
			}
		}
		return false;
	}
	/**
	 * 判定是否可以右移
	 * @return
	 */
	public boolean canRightMove() {
		if (GameSingle.tetromino == null) return false;
		Cell[] cells = GameSingle.tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			int row = c.getRow();
			int col = c.getCol();
			if ((col+1) == GameSingle.COLS || GameSingle.wall[row][col+1] != null) return false;
		}
		return true;
	}
	/**
	 * @return
	 */
	public boolean canLeftMove() {
		if (GameSingle.tetromino == null) return false;
		Cell[] cells = GameSingle.tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			int row = c.getRow();
			int col = c.getCol();
			if (col == 0 || GameSingle.wall[row][col-1] != null) return false;
		}
		return true;
	}
	/**
	 * @return
	 */
	public boolean isGameOver() {
		if(GameSingle.remain<1) {
			GameSingle.isGameOver = true;
			return true;
		}
		for(int col = 0; col < GameSingle.COLS; col++) {
			if (GameSingle.wall[0][col] != null) {
				GameSingle.isGameOver = true;
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		
		g.drawImage(Resources.tetris, 0, 0, null);
		g.drawImage(Resources.tetris_tabs, 0, 0, null);
		g.translate(15, 15);
		
		//画墙
		paintWall(g);
		//画当前下落对象和预备下落对象
		paintTetromino(g);
		paintNextone(g);
		//画提示信息，得分，消除行数，等级
		paintTabs(g);
		//画暂停画面
		paintGamePause(g);
		//画GameOver画面
		paintGameOver(g);
	}
	/**
	 * @param g
	 */
	public void paintGameOver(Graphics g) {
		if (isGameOver()) {
			GameSingle.tetromino = null;
			g.drawImage(Resources.gameover, -15, -15, null);
			Color color = new Color(0,71,157);
			g.setColor(color);
			Font font = new Font(Font.SERIF,Font.BOLD,30);
			g.setFont(font);
			g.drawString(""+GameSingle.score, 260, 207);
			g.drawString(""+GameSingle.charge, 260, 300);
			STATE = false;
		}
	}
	/**
	 * @param g
	 */
	public void paintGamePause(Graphics g){
		if (!STATE && !isGameOver()) {
			g.drawImage(Resources.pause, -15, -15, null);
		}
	}
	/**
	 * @param g
	 */
	public void paintTabs(Graphics g) {
		//确定绘制的地点
		int x = 410;
		int y = 160;
		//设置颜色
		Color color = new Color(0, 0, 0);
		g.setColor(color);
		//设置字体
		Font f = new Font(Font.SERIF,Font.BOLD,30);
		g.setFont(f);
		g.drawString(""+GameSingle.score, x, y);
		y+=56;
		g.drawString(""+GameSingle.remain, x, y);
		y+=56;
		g.drawString(""+GameSingle.charge, x, y);
	}
	/**
	 * 画Nextone
	 * @param g
	 */
	public void paintNextone(Graphics g) {
		 if (GameSingle.nextone == null)return;
		 Cell[] cells = GameSingle.nextone.cells;
		 for(int i = 0; i < cells.length; i++){
			 Cell c = cells[i];
			 int row = c.getRow() ;
			 int col = c.getCol() + 9;
			 int x = col * CELL_SIZE;
			 int y = row * CELL_SIZE;
			 g.drawImage(c.getBgImage(), x, y, null);
		 }
	}
	/**
	 * 画当前tetromino
	 * @param g
	 */
	public void paintTetromino(Graphics g) {
		//cells 引用了正在下落方块的4个格子数组
		if(GameSingle.tetromino == null)return;
		
		//System.out.println(tetromino == null);
		Cell[] cells = GameSingle.tetromino.cells;
		for(int i=0; i < cells.length; i++){
			//c 是正在下落方块的每个格子的引用
			Cell c = cells[i];
			int col = c.getCol();
			int row = c.getRow();
			int x = col * CELL_SIZE;
			int y = row * CELL_SIZE;
			g.drawImage(c.getBgImage(), x, y, null);
		}
	}
	
	/**
	 * 画墙
	 * @param g
	 */
	public void paintWall(Graphics g) {
		for(int row = 0; row < GameSingle.ROWS; row++) {
			for(int col = 0; col < GameSingle.COLS; col++) {
				Cell cell = GameSingle.wall[row][col];
				int rows = row * CELL_SIZE;
				int cols = col * CELL_SIZE;
				//g.drawImage(O, rows, cols, null);
				if (cell == null) {
					//System.out.println(0);
				}else{
					g.drawImage(cell.getBgImage(), cols, rows, null);
				}
			}
		}
	}
}

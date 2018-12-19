package com.CTetris.tetris;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Resources {
	public static final String root = "resources/";
	public static final String[] tKind = {"I/","J/","L/","O/","S/","T/","Z/"};
	public static final String[] cKind = {"Neutral/", "Negative/", "Positive/"};
	
	public static List<BufferedImage>[][] cellImages = new List[7][3];
	public static BufferedImage pause;
	public static BufferedImage tetris;
	public static BufferedImage tetris_tabs;
	public static BufferedImage gameover;
	
	static {
		try {
			for(int i=0;i<7;i++) {
				for(int j=0;j<3;j++) {
					cellImages[i][j] = new ArrayList<BufferedImage>();
					cellImages[i][j].add(ImageIO.read(
							Resources.class.getResource(root + tKind[i] + cKind[j] + "1.png")));	
				}
			}
			pause = ImageIO.read(Resources.class.getResource(root + "pause.png"));
			tetris = ImageIO.read(Resources.class.getResource(root + "tetris.png"));
			tetris_tabs = ImageIO.read(Resources.class.getResource(root + "tetris_tabs.png"));
			gameover = ImageIO.read(Resources.class.getResource(root + "gameover.png"));
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	private Resources() {
	}

}

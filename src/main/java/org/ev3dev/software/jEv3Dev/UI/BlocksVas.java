package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlocksVas extends JPanel {
	
	private static final int default_width = 1130;
	
	private static final int default_height = 439;
	
	private static final int LEFT = 0;
	
	private static final int RIGHT = 1;
	
	private BufferedImage railImage;

	/**
	 * Create the panel.
	 */
	public BlocksVas() {
		setBackground(Color.WHITE);
		setSize(default_width, default_height);
		
		InputStream in = BlocksVas.class.getClassLoader().getResourceAsStream("rail.fw.png");
		try {
			railImage = ImageIO.read(in);
		} catch (IOException e) {
			railImage = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		System.out.println(railImage);
		drawRail(g, 50, 50);
		
		drawSemiCircle(g, 50, 50, 30, 30, LEFT, Color.LIGHT_GRAY);
	}
	
	public void drawSemiCircle(Graphics g, int x, int y, int width, int height, int direction, Color color){
		int nx = x - (width / 2);
		
		g.setColor(color);
		
		switch (direction){
		case LEFT:
			g.fillArc(nx, y, width, height, 90, 180);
			break;
		case RIGHT:
			g.fillArc(nx, y, width, height, 90, -180);
			break;
		}
	}
	
	public void drawRail(Graphics g, int x, int y){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 30, 30);
		
		int oox = x + (30 / 2);
		int ooy = y + (30 / 2);
		
		int ox = oox - (22 / 2);
		int oy = ooy - (22 / 2);

		g.setColor(Color.WHITE);
		g.fillOval(ox, oy, 22, 22);
		
		int nx = x - (10 / 2);
		int ny = y - (10 / 2);
	}

}

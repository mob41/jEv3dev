package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.ev3dev.software.jEv3Dev.UI.blocks.Rail;

public class BlocksVas extends JPanel {
	
	private static final int default_width = 1130;
	
	private static final int default_height = 439;
	
	private static final int LEFT = 0;
	
	private static final int RIGHT = 1;
	
	private int currWidth;
	
	private int currHeight;
	
	private UI uiframe;
	
	private BlocksLoader loader;
	
	private BufferedImage railImage;

	/**
	 * Create the panel.
	 */
	public BlocksVas(UI uiframe, BlocksLoader loader) {
		this.uiframe = uiframe;
		this.loader = loader;
		
		setBackground(Color.WHITE);
		setSize(default_width, default_height);
		setLayout(null);
		
		currWidth = default_width;
		currHeight = default_height * 3;
		
		
		Rail rail = new Rail();
		rail.setPos(new Point(80, currHeight / 2));
		loader.blocks.add(rail);
		
		Point point;
		for (int i = 0; i < 1; i++){
			rail = new Rail(false, true);
			point = loader.getNextBlockPos(Rail.DEFAULT_HEIGHT);
			rail.setPos(point);
			loader.blocks.add(rail);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (loader.getNextBlockPos().x >= currWidth){
			currWidth += default_width;
			uiframe.getBlocksScroll().updateUI();
		}
		
		if (loader.getNextBlockPos().y >= currHeight){
			currHeight += default_height;
			uiframe.getBlocksScroll().updateUI();
		}
		
		for (Block block : loader.blocks){
			block.drawThis(g);
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(currWidth, currHeight);
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
	
	public void drawBlock(Graphics g, int x, int y, String name, String version){
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y, 180, 90, 20, 25);
		g.setColor(new Color(242, 242, 242));
		g.fillRoundRect(x, y + 10, 180, 80, 20, 25);
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

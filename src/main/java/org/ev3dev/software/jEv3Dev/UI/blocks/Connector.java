package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.ev3dev.software.jEv3Dev.UI.Block;
import org.ev3dev.software.jEv3Dev.UI.BlockDescription;

public class Connector extends Block {
	
	public static final int DEFAULT_WIDTH = 20;
	
	public static final int DEFAULT_HEIGHT = 20;
	
	public static final int INSIDE_CIRCLE_RADIUS = 15;
	
	public static final int CENTER = 0;
	
	public static final int LEFT = 1;
	
	public static final int RIGHT = 2;
	
	public static final BlockDescription blockDesc = new BlockDescription(
			"Connector", "jEv3dev", "---", "A connector linking blocks together with a line.");
	
	private int direction = CENTER;
	
	public Connector(){
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT, blockDesc);
		
		setConnectedToRail(true);
		setReleasedFromMouse(true);
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	@Override
	public void drawThis(Graphics g, Point mousePosition){
		Graphics2D g2 = (Graphics2D) g;
		
		int x = getLeftX();
		int uy = getUpY();
		int dy = getDownY();
		
		int cy = (uy + dy) / 2 - DEFAULT_HEIGHT / 2;
		
		switch (direction){
		case CENTER:
			g2.setColor(Color.DARK_GRAY);
			g2.fillArc(x, cy, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0, 360);
			
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillArc(x, cy, INSIDE_CIRCLE_RADIUS, DEFAULT_HEIGHT, 0, 360);
			break;
		case LEFT:
			g2.setColor(Color.DARK_GRAY);
			g2.fillArc(x - DEFAULT_WIDTH / 2, cy, DEFAULT_WIDTH, DEFAULT_HEIGHT, 90, 90);
			
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillArc(x - DEFAULT_WIDTH / 2, cy, INSIDE_CIRCLE_RADIUS, DEFAULT_HEIGHT, 90, 90);
			break;
		case RIGHT:
			g2.setColor(Color.DARK_GRAY);
			g2.fillArc(x - DEFAULT_WIDTH / 2, cy, DEFAULT_WIDTH, DEFAULT_HEIGHT, 90, -180);
			
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillArc(x - DEFAULT_WIDTH / 2, cy, INSIDE_CIRCLE_RADIUS, DEFAULT_HEIGHT, 90, -180);
			break;
		}
		
		g2.setColor(Color.WHITE);
		
	}
	
	public boolean onMouseTouch() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMouseClick() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMousePress() {
		// TODO Auto-generated method stub
		return false;
	}

}

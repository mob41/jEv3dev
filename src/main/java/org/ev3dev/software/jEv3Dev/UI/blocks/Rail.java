package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;

import org.ev3dev.software.jEv3Dev.UI.Block;
import org.ev3dev.software.jEv3Dev.UI.UIObjectBase;

public class Rail extends Block{
	
	public static final int DEFAULT_WIDTH = 30;
	
	public static final int DEFAULT_HEIGHT = 30;
	
	private static final int LEFT = 0;
	
	private static final int RIGHT = 1;
	
	private boolean hasHead;
	
	private boolean hasEnd;
	
	public Rail(int x, int y, boolean hasHead, boolean hasEnd){
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		this.hasHead = hasHead;
		this.hasEnd = hasEnd;
	}

	public Rail(int x, int y){
		super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		hasHead = true;
		hasEnd = true;
	}
	
	@Override
	public String getName(){
		return "Blocks rail";
	}
	
	@Override
	public String getDescription(){
		return "An rail act like a wire connects all the functions together.";
	}
	
	@Override
	public String getVersion(){
		return "---";
	}
	
	@Override
	public String getAuthor(){
		return "jEv3dev";
	}
	
	@Override
	public void drawThis(Graphics g){
		int x = getLeftX();
		int y = getUpY();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 30, 30);
		
		int oox = x + (30 / 2);
		int ooy = y + (30 / 2);
		
		int ox = oox - (22 / 2);
		int oy = ooy - (22 / 2);

		g.setColor(Color.WHITE);
		g.fillOval(ox, oy, 22, 22);
		
		if (hasHead){
			drawHead(g);
		}
		
		if (hasEnd){
			drawEnd(g);
		}
	}
	
	@Override
	public String getShortName(){
		return "blocksRail";
	}

	public boolean onMouseTouch() {
		System.out.println("Mouse touched rail");
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMouseClick() {
		System.out.println("Mouse clicked rail");
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMousePress() {
		System.out.println("Mouse pressed rail");
		// TODO Auto-generated method stub
		return false;
	}
	
	public void drawHead(Graphics g){
		int x = getLeftX();
		int y = getUpY();
		drawSemiCircle(g, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, LEFT, Color.LIGHT_GRAY);
	}
	
	public void drawEnd(Graphics g){
		int x = getRightX();
		int y = getUpY();
		drawSemiCircle(g, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, RIGHT, Color.LIGHT_GRAY);
	}
	
	private void drawSemiCircle(Graphics g, int x, int y, int width, int height, int direction, Color color){
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
}

package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.ev3dev.software.jEv3Dev.UI.Block;

public class StartBlock extends Block {
	
	public static final int DEFAULT_HEIGHT = 90;
	
	public static final int DEFAULT_ARC_WIDTH = 30;
	
	public static final int DEFAULT_WIDTH = 35 + DEFAULT_ARC_WIDTH / 2;
	
	public StartBlock(){
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	@Override
	/**
	 * Color will be <code>null</code>
	 */
	public void drawThis(Graphics g, Point mousePosition){
		drawThis(g, mousePosition, null);
	}
	
	@Override
	public void drawThis(Graphics g, Point mousePosition, Color color){
		Graphics2D g2 = (Graphics2D) g;
		
		int x = getLeftX();
		int y = getUpY();
		
		final Color cusGray = new Color(242, 242, 242);
		g2.setColor(cusGray);
		g2.fillRect(x + DEFAULT_ARC_WIDTH / 2, y, DEFAULT_WIDTH - DEFAULT_ARC_WIDTH / 2, DEFAULT_HEIGHT);
		
		g2.fillArc(x, y, DEFAULT_ARC_WIDTH, DEFAULT_HEIGHT, 90, 180);
		
		g2.setColor(Color.ORANGE);
		g2.fillArc(x + DEFAULT_ARC_WIDTH / 4, y, DEFAULT_ARC_WIDTH / 2, 10 * 2, 90, 90);
		g2.fillRect(x + DEFAULT_ARC_WIDTH / 2, y, DEFAULT_WIDTH - DEFAULT_ARC_WIDTH / 2, 10);
		
		g2.setColor(Color.GREEN);
		
		int[] xPoints = {
				x + DEFAULT_WIDTH / 3 * 2,
				x + DEFAULT_WIDTH / 3,
				x + DEFAULT_WIDTH / 3
		};
		int[] yPoints = {
				y + DEFAULT_HEIGHT / 2,
				y + DEFAULT_HEIGHT / 3,
				y + DEFAULT_HEIGHT / 3 * 2,
		};
		g2.fillPolygon(xPoints, yPoints, 3);
	}
	
	@Override
	public String getShortName(){
		return "startBlock";
	}
	
	@Override
	public String getName(){
		return "StartBlock";
	}
	
	@Override
	public String getAuthor(){
		return "jEv3dev";
	}
	
	@Override
	public String getVersion(){
		return "---";
	}
	
	@Override
	public String getDescription(){
		return "A block indicating that is a start of that module.";
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

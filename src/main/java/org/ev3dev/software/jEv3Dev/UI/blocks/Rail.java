package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;

import org.ev3dev.software.jEv3Dev.UI.UIObjectBase;

public class Rail extends UIObjectBase{

	public Rail(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	public void drawThis(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(getLeftX(), getUpY(), 30, 30);
		
		int oox = getLeftX() + (30 / 2);
		int ooy = getUpY() + (30 / 2);
		
		int ox = oox - (22 / 2);
		int oy = ooy - (22 / 2);

		g.setColor(Color.WHITE);
		g.fillOval(ox, oy, 22, 22);
	}
}

package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Point;

public abstract class UIObjectBase implements UIObject{
	
	private final int xleft;
	
	private final int xright;
	
	private final int yup;
	
	private final int ydown;
	
	private final int width;
	
	private final int height;
	
	public UIObjectBase(int x, int y, int width, int height){
		this.xleft = x;
		this.xright = x + width;
		
		this.yup = y;
		this.ydown = y + height;
		
		this.width = width;
		this.height = height;
	}
	
	public Point getUpLeftPos(){
		return new Point(xleft, yup);
	}
	
	public Point getUpRightPos(){
		return new Point(xright, yup);
	}
	
	public Point getDownLeftPos(){
		return new Point(xleft, ydown);
	}
	
	public Point getDownRightPos(){
		return new Point(xright, ydown);
	}
	
	public int getLeftX(){
		return xleft;
	}
	
	public int getRightX(){
		return xright;
	}
	
	public int getUpY(){
		return yup;
	}
	
	public int getDownY(){
		return ydown;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
}

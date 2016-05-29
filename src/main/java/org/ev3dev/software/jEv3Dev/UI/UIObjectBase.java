package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Point;

public abstract class UIObjectBase implements UIObject{
	
	private int xleft;
	
	private int xright;
	
	private int yup;
	
	private int ydown;
	
	private int width;
	
	private int height;
	
	public UIObjectBase(int width, int height){
		System.out.println("Width: " + width + " Height: " + height);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the "Up-left" position of this UIObject
	 * @param pos
	 */
	public void setPos(Point pos){
		setLeftX(pos.x);
		setUpY(pos.y);
	}
	
	public void setLeftX(int x){
		this.xleft = x;
		this.xright = x + width;
	}
	
	public void setUpY(int y){
		this.yup = y;
		this.ydown = y + height;
	}
	
	public void setRightX(int x){
		this.xleft = x - width;
		this.xright = x;
	}
	
	public void setDownY(int y){
		this.yup = y - height;
		this.ydown = y;
	}
	
	public void setWidth(int width){
		this.xright = xleft + width;
		this.xleft = xright - width;
		
		this.width = width;
	}
	
	public void setHeight(int height){
		this.ydown = yup + height;
		this.yup = ydown - height;
		
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

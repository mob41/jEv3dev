package org.ev3dev.software.jEv3Dev.UI;

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
	
}

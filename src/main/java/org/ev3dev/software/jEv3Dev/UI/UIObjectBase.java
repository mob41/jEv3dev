package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Point;

/**
 * A class representing a Object in the canvas.
 * @author Anthony
 *
 */
public abstract class UIObjectBase implements UIObject{
	
	private boolean releasedFromMouse = false;
	
	//For debug purpose
	private boolean connectedToRail = false;
	
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
	
	/**
	 * <b>This is reserved for UI only. Do not change this value unless you know what are you doing.</b><br>
	 * <br>
	 * Indicating whatever this object is released from mouse after dragging
	 * @param released Is this object released from mouse after dragging
	 */
	protected final void setReleasedFromMouse(boolean released){
		releasedFromMouse = released;
	}
	
	/**
	 * <b>This is reserved for UI only.</b><br>
	 * <br>
	 * Indicating whatever this object is released from mouse after dragging
	 */
	protected final boolean isReleasedFromMouse(){
		return releasedFromMouse;
	}
	
	/**
	 * <b>This is reserved for UI only. Do not change this value unless you know what are you doing.</b><br>
	 * <br>
	 * Indicating whatever this object is connected to the blocks rail
	 * @param connected Is this object connected to the rail
	 */
	protected final void setConnectedToRail(boolean connected){
		this.connectedToRail = connected;
	}
	
	/**
	 * <b>This is reserved for UI only.</b><br>
	 * <br>
	 * Indicating whatever this object is connected to the blocks rail
	 */
	protected final boolean isConnectedToRail(){
		return connectedToRail;
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

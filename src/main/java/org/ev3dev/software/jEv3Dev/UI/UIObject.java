package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Graphics;
import java.awt.Point;

public interface UIObject {

	public void drawThis(Graphics g, Point mousePosition);
	
	public boolean onMouseTouch();
	
	public boolean onMouseClick();
	
	public boolean onMousePress();
}

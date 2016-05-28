package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Graphics;

public interface UIObject {

	public void drawThis(Graphics g);
	
	public boolean onMouseTouch();
	
	public boolean onMouseClick();
	
	public boolean onMousePress();
}

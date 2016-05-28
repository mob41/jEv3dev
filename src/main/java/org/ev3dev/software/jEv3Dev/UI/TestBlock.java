package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Color;
import java.awt.Graphics;

public class TestBlock extends Block {

	public TestBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		System.out.println("Created!");
		// TODO Auto-generated constructor stub
	}

	public void drawThis(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(150, 150, 50, 50);
	}

	public boolean onMouseTouch() {
		System.out.println("Touched!");
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMouseClick() {
		System.out.println("Clicked!");
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMousePress() {
		System.out.println("Pressed!");
		// TODO Auto-generated method stub
		return false;
	}

}

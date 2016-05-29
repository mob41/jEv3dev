package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;

import org.ev3dev.software.jEv3Dev.UI.Block;

public class TestBlock extends Block {

	private Color color;
	
	public TestBlock(Color color) {
		super();
		this.color = color;
		System.out.println("Created! " + this.hashCode());
		// TODO Auto-generated constructor stub
	}

	public void drawThis(Graphics g) {
		super.drawThis(g, color);
	}

	public boolean onMouseTouch() {
		System.out.println("Touched!" + this.hashCode());
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMouseClick() {
		System.out.println("Clicked!" + this.hashCode());
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMousePress() {
		System.out.println("Pressed!" + this.hashCode());
		// TODO Auto-generated method stub
		return false;
	}

}

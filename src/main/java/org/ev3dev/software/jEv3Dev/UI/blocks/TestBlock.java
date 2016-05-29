package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;

import org.ev3dev.software.jEv3Dev.UI.Block;

public class TestBlock extends Block {

	private Color color;
	
	public TestBlock(Color color) {
		super();
		this.color = color;
	}

	public boolean onMouseTouch() {
		return false;
	}

	public boolean onMouseClick() {
		return false;
	}

	public boolean onMousePress() {
		return false;
	}
	
	@Override
	public String[] getParametersNames(){
		return new String[]{"Hi", "Hi", "HI", "!!!!","Hi", "Hi", "HI", "!!!!","Hi", "Hi", "HI", "!!!!"};
	}

}

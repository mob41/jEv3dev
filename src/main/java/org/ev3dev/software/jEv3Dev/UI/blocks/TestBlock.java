package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;

import org.ev3dev.software.jEv3Dev.UI.Block;
import org.ev3dev.software.jEv3Dev.UI.Parameter;

public class TestBlock extends Block {

	private Color color;
	
	public TestBlock(Color color) {
		super();
		this.color = color;
		
		Parameter<Integer> pm = new Parameter<Integer>("Name", 3);
		addParameter(pm);
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

}

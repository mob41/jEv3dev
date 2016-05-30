package org.ev3dev.software.jEv3Dev.UI.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;

import org.ev3dev.software.jEv3Dev.UI.Block;
import org.ev3dev.software.jEv3Dev.UI.Parameter;

public class Motor extends Block {
	
	public Color color = Color.GREEN;
	
	public static Color[] colors = {Color.black, Color.blue, Color.cyan, Color.darkGray,
			Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange,
			Color.pink, Color.red, Color.white, Color.yellow};
	
	public Motor(){
		super();
		
		Parameter<String> pm1 = new Parameter<String>("Motor name", "New Motor");
		pm1.setInOut(Parameter.IN);
		addParameter(pm1);
	}
	
	@Override
	public String getName(){
		return "Motor";
	}
	
	@Override
	public String getAuthor(){
		return "jEv3Dev";
	}
	
	@Override
	public String getVersion(){
		return "alpha";
	}
	
	@Override
	public void drawThis(Graphics g, Point mousePosition, Color color){
		super.drawThis(g, mousePosition, color);
	}
	
	@Override
	public String getDescription(){
		return "A block to control a motor.";
	}

	public boolean onMouseTouch() {
		Random rand = new Random();
		int next = rand.nextInt(colors.length);
		color = colors[next];
		return false;
		
	}

	public boolean onMouseClick() {
		Random rand = new Random();
		int next = rand.nextInt(colors.length);
		color = colors[next];
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onMousePress() 
	{
		Random rand = new Random();
		int next = rand.nextInt(colors.length);
		color = colors[next];
		// TODO Auto-generated method stub
		return false;
	}
}

package org.ev3dev.software.jEv3Dev.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.ev3dev.software.jEv3Dev.ActionInterface;
import org.ev3dev.software.jEv3Dev.actions.Action;

public abstract class Block  extends UIObjectBase{
	
	private List<Parameter> parameters = new ArrayList<Parameter>(50);
	
	public static final int DEFAULT_WIDTH = 180;
	
	public static final int DEFAULT_HEIGHT = 90;
	
	public static final BufferedImage DEFAULT_ICON = getDefaultImageFromClass();
	
	private Graphics g;
	
	private static BufferedImage getDefaultImageFromClass(){
		try {
			return ImageIO.read(
					Block.class.getResourceAsStream(
							"/org/ev3dev/software/jEv3Dev/UI/resources/block.fw.png"
							));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates a UI Block instance
	 * @param x x location of block
	 * @param y y location of block
	 * @param width Width of block
	 * @param height Height of block
	 */
	public Block(int width, int height) {
		super(width, height);
		
		if (getParameters().length != 0){
			width = 0;
			for (Parameter pm : getParameters()){
				width += Parameter.PARAMETERS_SPACE + Parameter.PARAMETER_WIDTH;
			}
			
			width += Parameter.PARAMETERS_SPACE;
		}
		
		setWidth(width);
	}
	
	/**
	 * Creates a UI Block instance
	 * @param x x location of block
	 * @param y y location of block
	 */
	public Block() {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		int width = DEFAULT_WIDTH;
		
		if (getParameters().length != 0){
			width = 0;
			for (Parameter pm : getParameters()){
				width += Parameter.PARAMETERS_SPACE + Parameter.PARAMETER_WIDTH;
			}
			
			width += Parameter.PARAMETERS_SPACE;
		}
		
		parameters = new ArrayList<Parameter>(50);
		
		setWidth(width);
	}
	
//Basic information
	
	/**
	 * Returns the short-name of this block, with no spaces.
	 * @return Short name no-spaces
	 */
	public String getShortName(){
		return "UBK" + this.hashCode();
	}
	
	/**
	 * Returns the name
	 * @return The name of this block
	 */
	public String getName(){
		return "UntitledBk-" + this.hashCode();
	}
	
	/**
	 * Returns the author
	 * @return The author of this block
	 */
	public String getAuthor(){
		return "Unknown";
	}
	
	/**
	 * Returns the version of this block
	 * @return The version of this block
	 */
	public String getVersion(){
		return "Unknown";
	}
	
	/**
	 * Returns the description of this block
	 * @return The description of this block
	 */
	public String getDescription(){
		return "A untitled block";
	}
	
	/**
	 * Returns the graphical icon of this block (50x50)
	 * @return <code>BufferedImage</code> of the icon
	 */
	public BufferedImage getIcon(){
		return DEFAULT_ICON;
	}
	
//Main Action

	public boolean runMainAction(){
		return false;
	};
	
	public boolean hasMainAction(){
		return false;
	}
	
//Run specified action
	
	public boolean runAction(int action){
		return false;
	}
	
	public Action[] getActionsTypes(){
		return null;
	}
	
//Configuration
	
	public boolean isConfigurable(){
		return false;
	}
	
	public final void addParameter(Parameter pm){
		parameters.add(pm);
		
		int width = DEFAULT_WIDTH;
		if (getParameters().length != 0){
			width = 0;
			for (Parameter parameter : getParameters()){
				width += Parameter.PARAMETERS_SPACE + Parameter.PARAMETER_WIDTH;
			}
			
			width += Parameter.PARAMETERS_SPACE;
		}
		
		setWidth(width);
	}
	
	public final void removeParameter(int parameter){
		parameters.remove(parameter);
	}
	
	public final Parameter getParameter(int parameter){
		return parameters.get(parameter);
	}
	
	public final Parameter[] getParameters(){
		Parameter[] pms = new Parameter[parameters.size()];
		for (int i = 0; i < parameters.size(); i++){
			pms[i] = (Parameter) parameters.get(i);
		}
		return pms;
	}
	
	public final void setParameterValue(int parameter, Object value){
		parameters.get(parameter).setValue(value);
	}
	
//Default draw
	
	public void drawThis(Graphics g){
		drawThis(g, Color.GREEN);
	}
	
	/**
	 * Draws a basic block texture.<br>
	 * <br>
	 * If you override this function, you must call this function via
	 * <code>super.drawThis(Graphics g, Color color)</code> in order to
	 * draw the basic block texture.
	 * @param g
	 * @param color
	 */
	public void drawThis(Graphics g, Color color){
		if (this.g == null){
			this.g = g;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		int x = getLeftX();
		int y = getUpY();
		
		int width = getWidth();
		
		System.out.println("Width: " + width);
		System.out.println("FilRoundRect: " + x + ", " + y + " HEIGHT: " + DEFAULT_HEIGHT);
		
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, DEFAULT_HEIGHT, 20, DEFAULT_HEIGHT);
		
		g2.setColor(new Color(242, 242, 242));
		g2.fillRoundRect(x, y + 20, width, DEFAULT_HEIGHT - 10, 20, DEFAULT_HEIGHT);
		
		System.out.println("AnotherRoundRect: " + (DEFAULT_HEIGHT - 10));
		
		g2.fillRect(x, y + 20, width, DEFAULT_HEIGHT - 30);
		
		Parameter<?> pm;
		for (int i = 0; i < getParameters().length; i++){
			pm = getParameters()[i];
			
			switch (pm.isInOrOut()){
			case Parameter.IN:
				g2.setColor(Color.DARK_GRAY);
				
				g2.fillRect(x + (Parameter.PARAMETERS_SPACE * (i + 1)) + (Parameter.PARAMETER_WIDTH * i), y + 80, 30, 20);
				g2.fillRect(
						x + (Parameter.PARAMETERS_SPACE * (i + 1)) +
						(Parameter.PARAMETER_WIDTH * i) + (Parameter.PARAMETER_WIDTH / 2)
						, y + 72, 10, 10);
				
				g2.setColor(Color.LIGHT_GRAY);
				
				g2.fillRect(x + (Parameter.PARAMETERS_SPACE * (i + 1)) + (Parameter.PARAMETER_WIDTH * i) + 2, y + 82, 27, 18);
				g2.fillRect(
						x + (Parameter.PARAMETERS_SPACE * (i + 1)) +
						(Parameter.PARAMETER_WIDTH * i) + (Parameter.PARAMETER_WIDTH / 2)
						, y + 74, 7, 8);
				break;
			case Parameter.OUT:
				g2.setColor(Color.LIGHT_GRAY);
				
				g2.fillRect(x + (Parameter.PARAMETERS_SPACE * (i + 1)) + (Parameter.PARAMETER_WIDTH * i), y + 80, 30, 20);
				g2.fillRect(
						x + (Parameter.PARAMETERS_SPACE * (i + 1)) +
						(Parameter.PARAMETER_WIDTH * i) + (Parameter.PARAMETER_WIDTH / 2)
						, y + 97, 10, 10);
				
				g2.setColor(new Color(246, 246, 246));
				
				g2.fillRect(x + (Parameter.PARAMETERS_SPACE * (i + 1)) + (Parameter.PARAMETER_WIDTH * i) + 1, y + 81, 28, 18);
				g2.fillRect(
						x + (Parameter.PARAMETERS_SPACE * (i + 1)) +
						(Parameter.PARAMETER_WIDTH * i) + (Parameter.PARAMETER_WIDTH / 2) + 3
						, y + 97, 6, 9);
				
				break;
			}
			g2.setColor(Color.RED);
			Object value = pm.getValue();
			String display = "!gen";
			
			if (value instanceof Integer){
				value = Integer.toString((Integer) value);
			}
			
			if (value instanceof String){
				g2.setColor(Color.BLACK);
				display = (String) value;
				if (display.length() > 4){
					display = display.substring(0, 3);
					display += "..";
				}
			}
			
			g2.drawString(display, x + (Parameter.PARAMETERS_SPACE * (i + 1)) + (Parameter.PARAMETER_WIDTH * i) + 3, y + 92);
			
		}
		
		g2.setColor(Color.BLUE);
		g2.drawString(getName(), x + 10, y + 13);
		
		g2.drawImage(getIcon(), x + 1, y + 20, null);
	}
	
}
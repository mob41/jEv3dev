package org.ev3dev.software.jEv3Dev.UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.ev3dev.software.jEv3Dev.ActionInterface;
import org.ev3dev.software.jEv3Dev.actions.Action;

public abstract class Block  extends UIObjectBase{
	
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
	}
	
	/**
	 * Creates a UI Block instance
	 * @param x x location of block
	 * @param y y location of block
	 */
	public Block() {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
	
	public void setParameter(int parameter, Object value){
		
	};
	
	public Object getParameter(int parameter){
		return null;
	}
	
	public String[] getParametersTypes(){
		return null;
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
		int x = getLeftX();
		int y = getUpY();
		
		g.setColor(color);
		g.fillRoundRect(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, 20, DEFAULT_HEIGHT);
		
		g.setColor(new Color(242, 242, 242));
		g.fillRoundRect(x, y + 20, DEFAULT_WIDTH, DEFAULT_HEIGHT - 10, 20, DEFAULT_HEIGHT);
		
		g.fillRect(x, y + 20, DEFAULT_WIDTH, DEFAULT_HEIGHT - 30);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.drawString("Motor", x + 10, y + 13);
		
		g2.drawImage(getIcon(), x + 10, y + 20, null);
	}
}

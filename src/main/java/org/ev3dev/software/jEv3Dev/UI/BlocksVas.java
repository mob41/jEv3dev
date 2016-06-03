package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.ev3dev.software.jEv3Dev.UI.blocks.Connector;
import org.ev3dev.software.jEv3Dev.UI.blocks.StartBlock;

public class BlocksVas extends JPanel {
	
	public static final int default_width = 1130;
	
	public static final int default_height = 439;
	
	private static final int LEFT = 0;
	
	private static final int RIGHT = 1;
	
	private int currWidth;
	
	private int currHeight;
	
	private UI uiframe;
	
	private BlocksLoader loader;
	
	private BufferedImage railImage;
	
	private RenderThread renderThread;

	/**
	 * Create the panel.
	 */
	public BlocksVas(UI uiframe, BlocksLoader loader) {
		this.uiframe = uiframe;
		this.loader = loader;
		
		setBackground(Color.WHITE);
		setSize(default_width, default_height);
		setLayout(null);
		
		currWidth = default_width;
		currHeight = default_height * 3;
		
		StartBlock startBlock = new StartBlock();
		startBlock.setPos(new Point(80, currHeight / 2));
		startBlock.setConnectedToRail(true);
		startBlock.setReleasedFromMouse(true);
		loader.blocks.add(startBlock);
		
		Connector connector = new Connector();
		connector.setPos(loader.getNextBlockPos());
		connector.setDirection(Connector.RIGHT);
		loader.blocks.add(connector);
		
		renderThread = new RenderThread(this);
		renderThread.start();
	}

	/**
	 * Draws all the blocks of this blocks canvas
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (loader.getNextBlockPos().x >= currWidth){
			currWidth += default_width;
			uiframe.getBlocksScroll().updateUI();
		}
		
		if (loader.getNextBlockPos().y >= currHeight){
			currHeight += default_height;
			uiframe.getBlocksScroll().updateUI();
		}
		
		for (int i = 0; i < loader.blocks.size(); i++){
			Block block = loader.blocks.get(i);
			
			
			if (!block.isReleasedFromMouse() && !block.isConnectedToRail()){
				Toolkit.getDefaultToolkit().beep();
				System.err.println("!!BUG: Normal Blocks List should not contain NOT RELEASED MOUSE FIELDS: " + block.getName());
				loader.blocks.remove(block);
				JOptionPane.showMessageDialog(uiframe, 
						"Invalid block detected, and it was cancelled to add to rail.\n" +
						"It is caused by the block:\n\n" +
						"Name: " + block.getName() + "\n" +
						"Author: " + block.getAuthor() + "\n" +
						"Version: " + block.getVersion() + "\n" +
						"Position: " + block.getCenteredPos() + "\n\n" +
						"Error: "
						+ "The block was trying to render itself in a MouseNotReleased\n"
						+ " state to a normal block list. Probably it is caused if the\n"
						+ " developer changed the UI RESERVED values him/herself.", 
					"Block Rendering Error", JOptionPane.ERROR_MESSAGE);
				//TODO Throw error. This should not be happen in the normal blocks list.
			}
			
			Point point = new Point(80, currHeight / 2);
			if (i > 0){
				point = loader.getAfterBlockPos(loader.blocks.get(i - 1), block.getHeight());
			}
			
			block.setPos(point);
			block.drawThis(g, this.getMousePosition());
		}
		
		int size = loader.nonRailBlocks.size();
		for (int i = 0; i < size; i++){
			Block block = loader.nonRailBlocks.get(i);
			
			if (loader.nonRailBlocks.size() > 1){
				loader.nonRailBlocks.remove(block);
				
				size = loader.nonRailBlocks.size();
				i--;
				continue;
			}
			
			if (block.isReleasedFromMouse() && block.isConnectedToRail()){
				Toolkit.getDefaultToolkit().beep();
				System.err.println("!!BUG: NON rail Blocks List should not contain RELEASED MOUSE FIELDS: " + block.getName());
				loader.nonRailBlocks.remove(block);
				JOptionPane.showMessageDialog(uiframe, 
						"Invalid block detected, and it was cancelled to add to rail.\n" +
						"It is caused by the block:\n\n" +
						"Name: " + block.getName() + "\n" +
						"Author: " + block.getAuthor() + "\n" +
						"Version: " + block.getVersion() + "\n" +
						"Position: " + block.getCenteredPos() + "\n\n" +
						"Error: "
						+ "The block was trying to render itself in a MouseReleased\n"
						+ " state to a non-rail block list. Probably it is caused if the\n"
						+ " developer changed the UI RESERVED values him/herself.", 
					"Block Rendering Error", JOptionPane.ERROR_MESSAGE);
				//TODO Throw error. This should not be happen in the non-rail blocks list.
			}
			
			block.drawThis(g, this.getMousePosition());
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(currWidth, currHeight);
	}
	
	public void drawSemiCircle(Graphics g, int x, int y, int width, int height, int direction, Color color){
		int nx = x - (width / 2);
		
		g.setColor(color);
		
		switch (direction){
		case LEFT:
			g.fillArc(nx, y, width, height, 90, 180);
			break;
		case RIGHT:
			g.fillArc(nx, y, width, height, 90, -180);
			break;
		}
	}
	
	public void drawBlock(Graphics g, int x, int y, String name, String version){
		g.setColor(Color.GREEN);
		g.fillRoundRect(x, y, 180, 90, 20, 25);
		g.setColor(new Color(242, 242, 242));
		g.fillRoundRect(x, y + 10, 180, 80, 20, 25);
	}
	
	public void drawRail(Graphics g, int x, int y){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 30, 30);
		
		int oox = x + (30 / 2);
		int ooy = y + (30 / 2);
		
		int ox = oox - (22 / 2);
		int oy = ooy - (22 / 2);

		g.setColor(Color.WHITE);
		g.fillOval(ox, oy, 22, 22);
		
		int nx = x - (10 / 2);
		int ny = y - (10 / 2);
	}

}

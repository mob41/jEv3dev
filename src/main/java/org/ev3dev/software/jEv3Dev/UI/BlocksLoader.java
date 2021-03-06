package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class BlocksLoader {

	private static BlocksLoader blocksLoader;
	
	public final List<Block> blocks;
	
	public final List<Block> nonRailBlocks;
	
	public BlocksLoader(int maxblocks){
		nonRailBlocks = new ArrayList<Block>(maxblocks);
		blocks = new ArrayList<Block>(maxblocks);
	}
	
	public Point getNextBlockUpRightPos(){
		return blocks.get(blocks.size() - 1).getUpRightPos();
	}
	
	public Point getNextBlockPos(){
		return getNextBlockPos(Block.DEFAULT_HEIGHT);
	}
	
	public Point getNextBlockPos(int height){
		return getAfterBlockPos(blocks.get(blocks.size() - 1), height);
	}
	
	public Point getAfterBlockPos(Block block, int height){
		int x = block.getRightX();
		int cy = (int) ((double) (block.getUpY() + block.getDownY()) / (double) 2);
		return new Point(x, cy - (height / 2));
	}
	
	/**
	 * Loop through the non-rail blocks, and count the blocks that is marked by the UI that is not released mouse object.
	 * @return Count
	 */
	public int getCountOfNotReleasedMouse(){
		int c = 0;
		for (Block block : nonRailBlocks){
			if (!block.isReleasedFromMouse()){
				c++;
			}
		}
		return c;
	}
	
	public Block getBlockOfNotReleasedMouse(){
		for (Block block : nonRailBlocks){
			if (!block.isReleasedFromMouse()){
				return block;
			}
		}
		return null;
	}
	
	/**
	 * Adds a block that is <b>not connected to the rail</b> to the <code>nonRailBlocks</code> list.<br>
	 * <br>
	 * It supposes you that you will <b>change the position</b> of the UI programmatically <b>after this function.</b>
	 * @param block Block to be added
	 */
	public void addBlock(Block block){
		addBlock(block, false);
	}
	
	/**
	 * Adds a block with specifying whether the block is connected to the rail or not.
	 * @param block Block to be added
	 * @param connected Whether the block is connected to the rail or not
	 */
	public void addBlock(Block block, boolean connected){
		if (connected){
			Block lastBlock = blocks.get(blocks.size() - 1);
			if (lastBlock.getShortName().equals("blocksRail")){
				blocks.remove(blocks.size() - 1);
			}
			
			block.setPos(getNextBlockPos());
			
			blocks.add(block);
			
			/* Disable this...
			Point point = getNextBlockPos(Rail.DEFAULT_HEIGHT);
			Rail rail = new Rail(false, true);
			rail.setPos(point);
			
			blocks.add(rail);
			*/
		} else {
			nonRailBlocks.add(block);
		}
	}
	
	public void insertBlock(Block lastBlock, Block newBlock){
		int index = blocks.indexOf(lastBlock);
		
		if (index == -1){
			System.err.println("No such block!");
			return;
		}
		insertBlock(index + 1, newBlock);
	}
	
	public void insertBlock(int index, Block block){
		Block anotherBlock = blocks.get(index - 1);
		Point pos = getAfterBlockPos(anotherBlock, block.getHeight());
		block.setPos(pos);
		
		blocks.add(index, block);
	}
	
	public void removeBlock(Block block){
		removeBlock(block, true);
	}
	
	public void removeBlock(Block block, boolean addRails){
		int index = blocks.indexOf(block);
		
		if (index == -1){
			System.err.println("No such block in removing block!");
			return;
		}
		
		if (addRails){
			/* Change this to "AddConnectLines"
			int width = block.getWidth();
			int amount = width / Rail.DEFAULT_WIDTH;
			Point point;
			Rail rail;
			for (int i = 0; i < amount; i++){
				rail = new Rail(false, true);
				insertBlock(i + index, rail);
			}
			*/
		}
		blocks.remove(block);
	}
	
	public Block getBlockAtPosition(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				return block;
			}
		}
		return null;
	}
	
	public Block onMouseClickCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMouseClick();
				return block;
			}
		}
		return null;
	}
	
	public Block onMousePressCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMousePress();
				return block;
			}
		}
		return null;
	}
	
	public Block onMouseTouchCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMouseTouch();
				return block;
			}
		}
		return null;
	}
	
	public boolean isPointInArea(Point pos, int areaLeftX, int areaRightX, int areaUpY, int areaDownY){
		return areaLeftX < pos.x &&
				pos.x < areaRightX &&
				areaUpY < pos.y &&
				pos.y < areaDownY;
	}
}

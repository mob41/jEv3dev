package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.ev3dev.software.jEv3Dev.UI.blocks.Rail;

public class BlocksLoader {

	private static BlocksLoader blocksLoader;
	
	public final List<Block> blocks;
	
	public BlocksLoader(int maxblocks){
		blocks = new ArrayList<Block>(maxblocks);
	}
	
	public static BlocksLoader init(int maxblocks){
		blocksLoader = new BlocksLoader(maxblocks);
		return blocksLoader;
	}
	
	public static BlocksLoader getBlocksLoader(){
		return blocksLoader;
	}
	
	public void drawAll(Graphics g){
		for (Block block : blocks){
			block.drawThis(g);
		}
	}
	
	public Point getNextBlockUpRightPos(){
		return blocks.get(blocks.size() - 1).getUpRightPos();
	}
	
	public Point getNextBlockPos(){
		return getNextBlockPos(Block.DEFAULT_HEIGHT);
	}
	
	public Point getNextBlockPos(int height){
		Block block = blocks.get(blocks.size() - 1);
		int x = block.getRightX();
		int y = block.getUpY();
		int cy = (int) ((double) (block.getUpY() + block.getDownY()) / (double) 2);
		
		return new Point(x, cy - (height / 2));
	}
	
	public void addBlock(Block block){
		blocks.add(block);
	}
	
	public void onMouseClickCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMouseClick();
			}
		}
	}
	
	public void onMousePressCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMousePress();
			}
		}
	}
	
	public void onMouseTouchCheckAll(Point pos){
		for (Block block : blocks){
			if (isPointInArea(pos, block.getLeftX(), block.getRightX(),
					block.getUpY(), block.getDownY())){
				block.onMouseTouch();
			}
		}
	}
	
	public boolean isPointInArea(Point pos, int areaLeftX, int areaRightX, int areaUpY, int areaDownY){
		return areaLeftX < pos.x &&
				pos.x < areaRightX &&
				areaUpY < pos.y &&
				pos.y < areaDownY;
	}
}

package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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

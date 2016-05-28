package org.ev3dev.software.jEv3Dev.UI;

import java.util.ArrayList;
import java.util.List;

public class BlocksLoader {

	private final List<Block> blocks;
	
	public BlocksLoader(int maxblocks){
		blocks = new ArrayList<Block>(maxblocks);
	}
}

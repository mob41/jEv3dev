package org.ev3dev.software.jEv3Dev.UI;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class RenderThread extends Thread {
	
	private final BlocksVas canvas;
	
	public RenderThread(BlocksVas canvas){
		this.canvas = canvas;
	}
	
	@Override
	public void run(){
		while (!isInterrupted()){
			canvas.repaint();
		}
		
		System.err.println("RenderThread: Interrupted");
	}
	
	public BlocksVas getCanvas(){
		return canvas;
	}
}

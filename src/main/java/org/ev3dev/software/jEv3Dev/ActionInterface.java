package org.ev3dev.software.jEv3Dev;

public interface ActionInterface {

	public String getName();
	
	public String getDescription();
	
	public boolean run();
	
	public boolean canRun();
}

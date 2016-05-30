package org.ev3dev.software.jEv3Dev.UI;

public interface ParameterInterface {
	
	public String getName();
	
	public Object getValue();
	
	public void setInOut(int field);
	
	public int isInOrOut();
}

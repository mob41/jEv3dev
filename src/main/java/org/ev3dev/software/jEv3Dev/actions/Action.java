package org.ev3dev.software.jEv3Dev.actions;

public interface Action {

	public boolean runAction();
	
	public void setParameter(int parameter, Object value);
	
	public Object getParameter(int parameter);
	
	public boolean endAction();
}

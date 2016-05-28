package org.ev3dev.software.jEv3Dev.actions;

import org.ev3dev.software.jEv3Dev.ActionInterface;

public abstract class Action implements ActionInterface {

	public String getName() {
		return "Untitled Action";
	}

	public String getDescription() {
		return "An untitled action";
	}

	public boolean run() {
		return false;
	}
	
	public boolean canRun(){
		return false;
	}

}

package org.ev3dev.software.jEv3Dev.UI;

import java.awt.Graphics;

public abstract class Block extends UIObjectBase {

	public Block(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
//Main Action

	public boolean runMainAction(){
		return false;
	};
	
	public boolean hasMainAction(){
		return false;
	}
	
//Run specified action
	
	public boolean runAction(int action){
		return false;
	}
	
	public String[] getActionsTypes(){
		return null;
	}
	
//Configuration
	
	public boolean isConfigurable(){
		return false;
	}
	
	public void setParameter(int parameter, Object value){
		
	};
	
	public Object getParameter(int parameter){
		return null;
	}
	
	public String[] getParametersTypes(){
		return null;
	}
}

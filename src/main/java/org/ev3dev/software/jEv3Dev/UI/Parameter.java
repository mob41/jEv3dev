package org.ev3dev.software.jEv3Dev.UI;

public class Parameter<T> implements ParameterInterface {
	
	public static final int PARAMETER_WIDTH = 30;
	
	public static final int PARAMETERS_SPACE = 10;
	
	public static final int IN = 0;
	
	public static final int OUT = 1;
	
	public int width;
	
	public int xleft;
	
	public int xright;
	
	public int yup;
	
	public int ydown;
	
	public int readOrWrite = IN;
	
	public String name;
	
	public T value;
	
	public Parameter(String name, T value){
		this.name = name;
		this.value = value;
	}

	public final String getName() {
		return name;
	}
	
	public final T getValue() {
		return value;
	}
	
	public final void setValue(T value){
		this.value = value;
	}

	public final int isInOrOut() {
		return readOrWrite;
	}

	public void setInOut(int field){
		this.readOrWrite = field;
	}
}

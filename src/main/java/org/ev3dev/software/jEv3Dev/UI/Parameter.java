package org.ev3dev.software.jEv3Dev.UI;

public class Parameter<T> implements ParameterInterface {
	
	public static final int READ = 0;
	
	public static final int WRITE = 1;
	
	public int readOrWrite = READ;
	
	public String name;
	
	public T value;
	
	public Parameter(String name, T value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value){
		this.value = value;
	}

	public int isReadOrWrite() {
		return readOrWrite;
	}

	public void setReadOrWrite(int field){
		this.readOrWrite = field;
	}
}

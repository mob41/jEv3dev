package org.ev3dev.software.jEv3Dev.UI;

public class Parameter implements ParameterInterface {
	
	public String name;
	
	public Class<?> classType;
	
	public Object value;
	
	public Parameter(String name, Class<?> type, Object value){
		this.name = name;
		this.classType = type;
		this.value = value;
	}

	public String getName() {
		return null;
	}

	public Class<?> getParameterType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getValue() {
		return value;
	}
	
	public boolean isUIReadable(){
		return true;
	}
	
	public boolean isUIWritable(){
		return true;
	}
	
	public void setValue(Object value){
		this.value = value;
	}

}

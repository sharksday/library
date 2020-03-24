package com.kassmon.library.config;

public class Property {
	private String key;
	private String value;
	private String valueType;
	
	public Property(String key, String value, String valueType) {
		this.key = key;
		this.value = value;
		this.valueType = valueType;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getValueType() {
		return valueType;
	}
	
}

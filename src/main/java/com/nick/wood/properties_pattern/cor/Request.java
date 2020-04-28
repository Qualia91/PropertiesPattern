package com.nick.wood.properties_pattern.cor;

import com.nick.wood.properties_pattern.PropertyValue;

import java.util.HashMap;

public class Request {

	private final HashMap<String, PropertyValue> propertiesMap;
	private final String name;

	public Request(HashMap<String, PropertyValue> propertiesMap, String name) {
		this.propertiesMap = propertiesMap;
		this.name = name;
	}

	public HashMap<String, PropertyValue> getPropertiesMap() {
		return propertiesMap;
	}

	public String getName() {
		return name;
	}
}

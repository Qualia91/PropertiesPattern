package com.nick.wood.properties_pattern;

import java.util.ArrayList;

public class PropertyValue {

	private Object value;
	private final ArrayList<PropertyMetaData> propertyMetaDataList;

	public PropertyValue(Object value, ArrayList<PropertyMetaData> propertyMetaDataList) {
		this.value = value;
		this.propertyMetaDataList = propertyMetaDataList;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ArrayList<PropertyMetaData> getPropertyMetaDataList() {
		return propertyMetaDataList;
	}
}

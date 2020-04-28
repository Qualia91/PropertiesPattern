package com.nick.wood.properties_pattern;

import java.util.ArrayList;

public class PropertyValue {

	private Object value;
	private final ArrayList<PropertyMetaData> propertyMetaDataList;

	public PropertyValue(Object value, ArrayList<PropertyMetaData> propertyMetaDataList) {
		this.value = value;
		this.propertyMetaDataList = propertyMetaDataList;
	}

	public PropertyValue(Object value) {
		this.value = value;
		this.propertyMetaDataList = new ArrayList<>();
	}

	public PropertyValue() {
		this.value = null;
		this.propertyMetaDataList = new ArrayList<>();
		this.propertyMetaDataList.add(PropertyMetaData.NULL);
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

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PropertyValue) {
			PropertyValue propertyValue = (PropertyValue) obj;
			return value.equals(propertyValue.getValue());
		}
		return false;
	}
}

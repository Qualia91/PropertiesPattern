package com.nick.wood.properties_pattern;

import com.nick.wood.properties_pattern.cor.FinalHandler;
import com.nick.wood.properties_pattern.cor.Handler;
import com.nick.wood.properties_pattern.cor.NullHandler;
import com.nick.wood.properties_pattern.cor.Request;

import java.util.HashMap;
import java.util.function.Function;

public class Property {

	public static final String PARENT = "PARENT_LONG_STRING_SO_NOBODY_EVER_CHOOSES_THIS";
	private final HashMap<String, PropertyValue> propertiesMap = new HashMap<>();
	private final Handler corHandler;

	public Property() {
		corHandler = new NullHandler(null);
	}

	public Object get(String name) {
		return propertiesMap.get(name) != null && !propertiesMap.get(name).getPropertyMetaDataList().contains(PropertyMetaData.NULL)
				? propertiesMap.get(name).getValue()
				: actOnParent(property -> property.get(name), null);
	}

	public void put(String name, PropertyValue propertyValue) {
		assert !name.equals(PARENT) || propertyValue.getValue() instanceof Property;
		propertiesMap.put(name, propertyValue);
	}

	public boolean has(String name) {
		return propertiesMap.containsKey(name) && !propertiesMap.get(name).getPropertyMetaDataList().contains(PropertyMetaData.NULL)
				? true
				: actOnParent(property -> property.has(name), false);
	}

	public boolean removeGlobal(String name) {
		if (propertiesMap.containsKey(name)) {
			// if its of type PropertyMetaData.NULL, remove this and remove from parent
			if (propertiesMap.get(name).getPropertyMetaDataList().contains(PropertyMetaData.NULL)) {
				actOnParent(property -> property.removeGlobal(name), false);
			}
			propertiesMap.remove(name);
			return true;
		} else {
			return actOnParent(property -> property.removeGlobal(name), false);
		}
	}

	public boolean removeLocal(String name) {
		if (propertiesMap.containsKey(name)) {
			propertiesMap.remove(name);
			return true;
		} else {
			// if present in parent, create a new entry of same name here and set it to PropertyMetaData.NULL
			if (actOnParent(property -> property.has(name), false)) {
				propertiesMap.put(name, new PropertyValue());
				return true;
			}
		}
		return false;
	}

	private <T> T actOnParent(Function<Property, T> function, T defaultValue) {
		PropertyValue parentObject = propertiesMap.get(PARENT);
		if (parentObject != null) {
			Property parentProperty = (Property) parentObject.getValue();
			return function.apply(parentProperty);
		}
		return defaultValue;
	}


	// to debug
	HashMap<String, PropertyValue> getPropertiesMap() {
		return propertiesMap;
	}
}

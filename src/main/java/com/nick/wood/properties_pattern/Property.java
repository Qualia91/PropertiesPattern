package com.nick.wood.properties_pattern;

import java.util.HashMap;
import java.util.function.Function;

public class Property {

	private static final String PARENT_NAME  = "PARENT";
	private final HashMap<String, Object> propertiesMap = new HashMap<>();

	public Object get(String name) {
		return (propertiesMap.get(name) != null || propertiesMap.get(name) != PropertyMetaData.NULL) ? propertiesMap.get(name) : actOnParent(property -> property.get(name), null);
	}

	public void put(String name, Object object) {
		assert !name.equals(PARENT_NAME) || object instanceof Property;
		propertiesMap.put(name, object);
	}

	public boolean has(String name) {
		return (propertiesMap.containsKey(name) && propertiesMap.get(name) != PropertyMetaData.NULL) ? true : actOnParent(property -> property.has(name), false);
	}

	public boolean removeGlobal(String name) {
		if (propertiesMap.containsKey(name)) {
			// if its of type PropertyMetaData.NULL, remove this and then remove from parent
			if (propertiesMap.get(name) != PropertyMetaData.NULL) {
				actOnParent(property -> property.removeGlobal(name), false);
			}
			propertiesMap.remove(name);
		} else {
			return actOnParent(property -> property.removeGlobal(name), false);
		}
		return true;
	}

	public boolean removeLocal(String name) {
		if (propertiesMap.containsKey(name)) {
			propertiesMap.remove(name);
		} else {
			// if present in parent, create a new entry of same name here and set it to PropertyMetaData.NULL
			if (actOnParent(property -> property.has(name), false)) {
				propertiesMap.put(name, PropertyMetaData.NULL);
			}
		}
		return true;
	}

	private <T> T actOnParent(Function<Property, T> function, T defaultValue) {
		Object parentObject = propertiesMap.get(PARENT_NAME);
		if (parentObject != null) {
			Property parentProperty = (Property) parentObject;
			return function.apply(parentProperty);
		}
		return defaultValue;
	}

}

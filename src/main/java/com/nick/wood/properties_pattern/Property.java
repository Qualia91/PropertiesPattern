package com.nick.wood.properties_pattern;

import java.util.HashMap;
import java.util.function.Function;

public class Property {

	private static final String PARENT_NAME  = "PARENT";
	HashMap<String, Object> propertiesMap = new HashMap<>();

	public Object get(String name) {
		return (propertiesMap.get(name) != null) ? propertiesMap.get(name) : actOnParent(property -> property.get(name), null);
	}

	public void put(String name, Object object) {
		if (name.equals(PARENT_NAME)) {
			assert object instanceof Property;
		}
		propertiesMap.put(name, object);
	}

	public boolean has(String name) {
		return (propertiesMap.containsKey(name) ? true : actOnParent(property -> property.has(name), false));
	}

	public boolean remove(String name) {
		if (propertiesMap.containsKey(name)) {
			propertiesMap.remove(name);
		} else {
			return actOnParent(property -> property.remove(name), false);
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

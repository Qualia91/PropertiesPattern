package com.nick.wood.properties_pattern;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

	@Test
	void simpleOneLayerObjectCreation() {

		UUID uuid = UUID.randomUUID();

		Property property = new Property();
		// setters
		property.put("a", 1);
		property.put("b", "bValue");
		property.put("c", uuid);

		// getters
		assertEquals(1, property.get("a"));
		assertEquals("bValue", property.get("b"));
		assertEquals(uuid, property.get("c"));
		assertNotEquals(2, property.get("a"));
		assertNull(property.get("d"));

		// hassers
		assertTrue(property.has("a"));
		assertTrue(property.has("b"));
		assertTrue(property.has("c"));
		assertFalse(property.has("A"));

		// remove locals
		assertTrue(property.removeLocal("a"));
		assertFalse(property.removeLocal("d"));
		assertFalse(property.has("a"));

		// remove globals
		assertTrue(property.removeGlobal("b"));
		assertFalse(property.removeGlobal("e"));
		assertFalse(property.has("b"));
		assertNull(property.get("b"));

		HashMap<String, Object> propertiesMap = property.getPropertiesMap();
		assertEquals(1, propertiesMap.size());

		// try adding parent that isnt property
		assertThrows(AssertionError.class, () -> property.put(Property.PARENT, 1));

	}

	@Test
	void multiLayerObjectCreation() {

		UUID uuid = UUID.randomUUID();

		Property propertyOne = new Property();
		propertyOne.put("a", 1);
		propertyOne.put("b", "bValue");
		propertyOne.put("c", uuid);

		Property propertyTwo = new Property();
		propertyTwo.put("a", 4);
		propertyTwo.put("d", "bValue");
		propertyTwo.put("e", uuid);

		propertyOne.put(Property.PARENT, propertyTwo);

		assertEquals(1, propertyOne.get("a"));
		assertEquals("bValue", propertyOne.get("b"));
		assertEquals(uuid, propertyOne.get("c"));
		assertEquals("bValue", propertyOne.get("d"));
		assertEquals(uuid, propertyOne.get("e"));

		// hassers
		assertTrue(propertyOne.has("a"));
		assertTrue(propertyOne.has("b"));
		assertTrue(propertyOne.has("c"));
		assertTrue(propertyOne.has("d"));
		assertTrue(propertyOne.has("e"));

		// remove locals
		assertTrue(propertyOne.removeLocal("a"));
		assertTrue(propertyOne.removeLocal("d"));

		// remove globals
		assertTrue(propertyOne.removeGlobal("b"));
		assertTrue(propertyOne.removeGlobal("e"));

		assertEquals(4, propertyOne.get("a"));
		assertNull(propertyOne.get("b"));
		assertEquals(uuid, propertyOne.get("c"));
		assertEquals("bValue", propertyOne.get("d"));
		assertNull(propertyOne.get("e"));

		HashMap<String, Object> propertiesOneMap = propertyOne.getPropertiesMap();
		assertEquals(3, propertiesOneMap.size());
		HashMap<String, Object> propertiesTwoMap = propertyTwo.getPropertiesMap();
		assertEquals(2, propertiesTwoMap.size());

	}
}
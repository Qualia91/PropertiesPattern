package com.nick.wood.properties_pattern.validator;

import java.util.function.Function;
import java.util.function.Predicate;

public class ValidationObject {

	private final Predicate<Object> predicate;
	private final Function<Object, String> errorMessage;

	public ValidationObject(Predicate<Object> predicate, Function<Object, String> errorMessage) {
		this.predicate = predicate;
		this.errorMessage = errorMessage;
	}

	public Predicate<Object> getPredicate() {
		return predicate;
	}

	public Function<Object, String> getErrorMessage() {
		return errorMessage;
	}
}

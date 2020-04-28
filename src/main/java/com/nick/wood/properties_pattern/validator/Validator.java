package com.nick.wood.properties_pattern.validator;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Validator<T> {

	ValidatorSupplier<T> on(T t);

	default Validator<T> thenValidate(Predicate<T> condition, Function<T, String> errorMessage) {
		return  t -> {

			try {
				on(t).validate();
				if (condition.test(t)) {
					return () -> t;
				} else {
					return () -> {
						ValidationException validationException = new ValidationException("This object is not valid");
						validationException.addSuppressed(new IllegalArgumentException(errorMessage.apply(t)));
						throw validationException;
					};
				}
			} catch (ValidationException v) {
				if (!condition.test(t)) {
					v.addSuppressed(new IllegalArgumentException(errorMessage.apply(t)));
				}
				return () -> { throw v; };
			}

		};
	}

	static <T> Validator<T> validate(Predicate<T> condition, Function<T, String> errorMessage) {
		return t -> condition.test(t) ? () -> t : () -> {
			ValidationException validationException = new ValidationException("This object is not valid");
			validationException.addSuppressed(new IllegalArgumentException(errorMessage.apply(t)));
			throw validationException;
		};
	}

	interface ValidatorSupplier<T> extends Supplier<T> {
		default T validate() {
			return get();
		}
	}


}

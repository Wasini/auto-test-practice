package util;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(BoundedListSupplier.class)
public @interface BoundedList {
	int sizeMin();

	int sizeMax();

	int rangeMin();

	int rangeMax();

	int genAmount();
}

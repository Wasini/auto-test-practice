package Ejercicio1;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(IntBetweenSupplier.class)
public @interface IntBetween {
    int sizeMin();
    int sizeMax();
    int rangeMin();
    int rangeMax();
    int cant();
}

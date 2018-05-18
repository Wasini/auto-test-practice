package Ejercicio1;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IntBetweenSupplier extends ParameterSupplier {
    @Override
    public List<PotentialAssignment> getValueSources(ParameterSignature sig){
        IntBetween Annotation = sig.getAnnotation(IntBetween.class);
        int sizeMin = Annotation.sizeMin();
        int sizeMax = Annotation.sizeMax();
        int rangeMin = Annotation.rangeMin();
        int rangeMax = Annotation.rangeMax();
        int cant = Annotation.cant();
        java.util.List<PotentialAssignment> values = new ArrayList<PotentialAssignment>();
        for(int k=0; k<=cant; k++) {
            int range = ThreadLocalRandom.current().nextInt(sizeMin,sizeMax+1);
            SinglyLinkedList l = new SinglyLinkedList();
            for(int i = 0; i<range; i++ ) {
                l.addFirst(ThreadLocalRandom.current().nextInt(rangeMin,rangeMax+1));
            }
            values.add(PotentialAssignment.forValue(l.toString(),l));
        }
        return values;
    }


}
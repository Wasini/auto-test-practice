package util;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import util.ArrayList;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class BoundedListSupplier extends ParameterSupplier {
	@Override
	public List<PotentialAssignment> getValueSources(ParameterSignature sig) {
		BoundedList Annotation = sig.getAnnotation(BoundedList.class);
		int sizeMin = Annotation.sizeMin();
		int sizeMax = Annotation.sizeMax();
		int rangeMin = Annotation.rangeMin();
		int rangeMax = Annotation.rangeMax();
		int amount = Annotation.genAmount();
		java.util.List<PotentialAssignment> values = new java.util.ArrayList<PotentialAssignment>();
		for (int k = 0; k <= amount; k++) {
			int range = ThreadLocalRandom.current().nextInt(sizeMin, sizeMax + 1);
			ArrayList<Integer> l = new ArrayList<Integer>(range);
			for (int i = 0; i < range; i++) {
				l.add(ThreadLocalRandom.current().nextInt(rangeMin, rangeMax + 1));
			}
			values.add(PotentialAssignment.forValue(l.toString(), l));
		}
		return values;
	}

}
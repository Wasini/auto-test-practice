package util;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ArrayListTest {

	// Constants
	final static int nullList = 0;
	final static int smallList = 6; // > 1
	final static int bigList = 128;

	final static int zeroMinus = -5;
	final static int zeroPlus = 5;
	final static int zero = 0;

	final static int defAmount = 70;

	// Some lists
	ArrayList<Integer> emptyList;
	ArrayList<Integer> emptyList2;
	ArrayList<Integer> someElementsList;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void initialize() {
		emptyList = new ArrayList<Integer>();
		emptyList2 = new ArrayList<Integer>(0);
		someElementsList = new ArrayList<Integer>(smallList);
		for (int i = 0; i < smallList / 2 + 1; i++) {
			someElementsList.add(i);
		}
	}

	@Theory
	public void sizeRespectBoundaries(
			@BoundedList(sizeMin = nullList, sizeMax = smallList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		assertThat("List respect boundaries", l.size(),
				is(allOf(lessThanOrEqualTo(smallList), greaterThanOrEqualTo(nullList))));

	}

	@Theory
	public void emptyList() {
		assertThat("Empty list has size 0", emptyList.size() + emptyList2.size(), is(equalTo(0)));
		assertThat("Empty list has initial capacity of 0", emptyList.elementData.length + emptyList2.elementData.length, is(equalTo(0)));
		assertThat("Method isEmpty returns true", emptyList.isEmpty() && emptyList2.isEmpty(), is(true));
	}

	@Theory
	public void ensuredCapacity(
			@BoundedList(sizeMin = nullList, sizeMax = bigList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		int oldCapacity = l.isEmpty() ? 10 : l.elementData.length;
		int newCapacity = l.isEmpty() ? 11 : oldCapacity+1;
		l.ensureCapacity(newCapacity);
		assertThat("List is ensured to have a capacity equal or greater than " + newCapacity, l.elementData.length, is(greaterThanOrEqualTo(newCapacity)));

		assertThat("New capacity is expanded by "+oldCapacity+"/2 or to " + newCapacity, l.elementData.length, is(anyOf(equalTo(newCapacity),equalTo(oldCapacity+(oldCapacity >> 1)))));
	}

	@Theory
	public void removeNotContainedElement(
			@BoundedList(sizeMin = nullList, sizeMax = smallList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		Integer value = ThreadLocalRandom.current().nextInt(zeroMinus, zeroPlus + 1);
		assumeTrue(!l.contains(value));
		assertThat("Index of element is -1", l.indexOf(value), is(equalTo(-1)));
		assertThat("Remove of not contained element returns false", l.remove(value), is(false));
		assertThat("Remove of not contained element returns false", l.remove(null), is(false));
	}

	@Theory
	public void nullElement(
			@BoundedList(sizeMin = nullList, sizeMax = smallList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount*10) ArrayList<Integer> l) {
		int randomIndex = l.isEmpty() ? 0 : ThreadLocalRandom.current().nextInt(0, l.size()+1);
		l.add(randomIndex,null);
		int indexOfNull = l.indexOf(null);
		assertThat("List has null element",l.contains(null) && l.get(indexOfNull) == null, is(true));
		boolean operationSucess = l.remove(null);
		assertThat("Null was removed from the list", operationSucess && !l.contains(null), is(true));
	}

	@Theory
	public void containsNewElementAdded(
			@BoundedList(sizeMin = nullList, sizeMax = smallList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount
					* 5) ArrayList<Integer> l) {

		Integer newElement = zeroPlus * 10;
		int randomIndex = l.isEmpty() ? 0 : ThreadLocalRandom.current().nextInt(0, l.size()+1);
		l.add(randomIndex, newElement);
		assertThat("Contains the element: " + newElement, l.contains(newElement), equalTo(true));
		assertThat("Added at index " + randomIndex, l.indexOf(newElement), is(equalTo(randomIndex)));
	}

	@Theory
	public void removeElement(
			@BoundedList(sizeMin = nullList, sizeMax = bigList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {

		assumeTrue(!l.isEmpty());

		int previousSize = l.size();
		int randomIndex = ThreadLocalRandom.current().nextInt(0, l.size());
		Object[] copy = l.toArray();
		Integer oldElement = l.remove(randomIndex);

		assertThat("List has size < " + previousSize, l.size(), is(lessThan(previousSize)));

		l.add(randomIndex, oldElement);

		assertThat("Removing and adding the same element, keeps the same lists", Arrays.asList(l.toArray()),
				contains(copy));
	}

	@Theory
	public void changeValueOfElement(
			@BoundedList(sizeMin = nullList, sizeMax = bigList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		assumeTrue(!l.isEmpty());
		int randomIndex = ThreadLocalRandom.current().nextInt(0, l.size());
		Integer newValue = ThreadLocalRandom.current().nextInt();
		Integer oldValue = l.get(randomIndex);
		assertThat("Set returns the old value " + oldValue, l.set(randomIndex, newValue), is(equalTo(oldValue)));
		assertThat("List has the value " + newValue + " at index " + randomIndex, l.get(randomIndex),
				is(equalTo(newValue)));
	}

	@Theory
	public void clearList(
			@BoundedList(sizeMin = nullList, sizeMax = bigList, rangeMin = zeroMinus, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		l.clear();
		assertThat("Cleared list is empty", l.isEmpty(), is(true));
	}

	@Theory
	public void removeObject(
			@BoundedList(sizeMin = nullList, sizeMax = smallList, rangeMin = zero, rangeMax = zeroPlus, genAmount = defAmount) ArrayList<Integer> l) {
		Integer value = ThreadLocalRandom.current().nextInt(zero, zeroPlus);
		assumeTrue(l.contains(value));
		long oValueCount = Arrays.asList(l.toArray()).stream().filter(x -> value.equals(x)).count();
		int prevSize = l.size();
		boolean removed = l.remove(value);
		assertThat("Remove returns true", removed, is(true));
		assertThat("Size decreased", l.size(), is(lessThan(prevSize)));
		long nValueCount = Arrays.asList(l.toArray()).stream().filter(x -> value.equals(x)).count();
		assertThat("List has " + (oValueCount - 1) + " items with value " + value, nValueCount,
				equalTo(oValueCount - 1));
	}

	@Test
	public void doubleTheAmoutOfElements() throws Exception {
		int oCapacity = someElementsList.elementData.length;
		int oSize = someElementsList.size();
		for (int i = 0; i < oSize; i++) {
			someElementsList.add(someElementsList.get(i));
		}
		assertThat("List has " + (oSize * 2) + " elements", someElementsList.size(), is(equalTo(oSize * 2)));
		assertThat("Capacity has increased", oCapacity, is(lessThan(someElementsList.elementData.length)));
		for (int i = 0; i < oSize; i++) {
			assertThat("Element at " + i + " is equal to element at " + (i + oSize - 1), someElementsList.get(i),
					is(equalTo(someElementsList.get(i + oSize))));
		}

	}

	@Test
	public void getingElementOutOfIndex() throws Exception {
		//Ensure a bigger capacity of the list
		someElementsList.ensureCapacity(someElementsList.elementData.length*2);
		exception.expect(IndexOutOfBoundsException.class);
		someElementsList.get(someElementsList.size());
		//emptyList.get(0);
	}

	@Test
	public void addingElementOutOfIndex() throws Exception {
		
		//Ensure a bigger capacity of the list	
		someElementsList.ensureCapacity(someElementsList.elementData.length*2);
		int index = someElementsList.size() + (someElementsList.size() >> 1);
	
		
		try {
			someElementsList.add(index,10);
		} catch (IndexOutOfBoundsException e) {
			assertThat("Throws execption with message like: Index: " + index + ", Size: " + someElementsList.size(),e.getMessage(),allOf(containsString(String.valueOf(index)),containsString(String.valueOf(someElementsList.size()))));
			assertThat("Element was not stored in index of the array",someElementsList.elementData[index],is(equalTo(null)));
			return;
		} 
		fail("No exception was throwed");
		//someElementsList.add(someElementsList.size() + (someElementsList.size() +2),2);
	}
	
	@Test
	public void removeOutOfIndex() throws Exception{
		someElementsList.ensureCapacity(someElementsList.elementData.length*2);
		exception.expect(IndexOutOfBoundsException.class);
		someElementsList.remove(someElementsList.size());
	}
	
	@Test
	public void setOutOfIndex() throws Exception{
		someElementsList.ensureCapacity(someElementsList.elementData.length*2);
		exception.expect(IndexOutOfBoundsException.class);
		someElementsList.set(someElementsList.size() + (someElementsList.size() >> 1),null);
	}

	// Slow test
	@Test
	public void sizeOverFlow() throws Exception {
		exception.expect(OutOfMemoryError.class);
		for (;;) {
			someElementsList.add(null);
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void negativeInitialCapacity() throws Exception {
		exception.expect(IllegalArgumentException.class);
		ArrayList<Integer> negativeSizeList = new ArrayList<Integer>(-1);
	}


	
	//May throw Java heap space
	@Test
	public void hugeCapacity() throws Exception{
		exception.expect(OutOfMemoryError.class);
		emptyList.ensureCapacity(Integer.MAX_VALUE-8);
		assertThat(emptyList.elementData.length,is(greaterThanOrEqualTo(Integer.MAX_VALUE-8)));
	}
	
	//May throw error if VM does not allow exceding the array size
	@Test
	public void overHugeCapacity() throws Exception{
		exception.expect(OutOfMemoryError.class);
		emptyList.ensureCapacity(Integer.MAX_VALUE-7);
		assertThat(emptyList.elementData.length,is(equalTo(Integer.MAX_VALUE)));
	}
	
	@After
	public void clearLists() {
		someElementsList.clear();
		emptyList.clear();
	}

}

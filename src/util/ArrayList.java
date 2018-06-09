
package util;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<E> {

	/**
	 * Default initial capacity.
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Shared empty array instance used for empty instances.
	 */
	private static final Object[] EMPTY_ELEMENTDATA = {};

	/**
	 * Shared empty array instance used for default sized empty instances. We
	 * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
	 * first element is added.
	 */
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

	/**
	 * The array buffer into which the elements of the ArrayList are stored. The
	 * capacity of the ArrayList is the length of this array buffer. Any empty
	 * ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA will be
	 * expanded to DEFAULT_CAPACITY when the first element is added.
	 */
	transient Object[] elementData; // non-private to simplify nested class access

	/**
	 * The size of the ArrayList (the number of elements it contains).
	 *
	 * @serial
	 */
	private int size;

	/**
	 * Constructs an empty list with the specified initial capacity.
	 *
	 * @param initialCapacity
	 *            the initial capacity of the list
	 * @throws IllegalArgumentException
	 *             if the specified initial capacity is negative
	 */
	public ArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */
	public ArrayList() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}

	/**
	 * Increases the capacity of this <tt>ArrayList</tt> instance, if necessary, to
	 * ensure that it can hold at least the number of elements specified by the
	 * minimum capacity argument.
	 *
	 * @param minCapacity
	 *            the desired minimum capacity
	 */
	public void ensureCapacity(int minCapacity) {
		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
				// any size if not default element table
				? 0
				// larger than default for default empty table. It's already
				// supposed to be at default size.
				: DEFAULT_CAPACITY;

		if (minCapacity > minExpand) {
			ensureExplicitCapacity(minCapacity);
		}
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}

		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		// overflow-conscious code
		if (minCapacity - elementData.length > 0)
			grow(minCapacity);
	}

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words in
	 * an array. Attempts to allocate larger arrays may result in OutOfMemoryError:
	 * Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * Increases the capacity to ensure that it can hold at least the number of
	 * elements specified by the minimum capacity argument.
	 *
	 * @param minCapacity
	 *            the desired minimum capacity
	 */
	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at least
	 * one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
	 * -1 if there is no such index.
	 */
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++)
				if (elementData[i] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (o.equals(elementData[i]))
					return i;
		}
		return -1;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * <p>
	 * The returned array will be "safe" in that no references to it are maintained
	 * by this list. (In other words, this method must allocate a new array). The
	 * caller is thus free to modify the returned array.
	 *
	 * <p>
	 * This method acts as bridge between array-based and collection-based APIs.
	 *
	 * @return an array containing all of the elements in this list in proper
	 *         sequence
	 */
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	// Positional Access Operations

	@SuppressWarnings("unchecked")
	E elementData(int index) {
		return (E) elementData[index];
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public E get(int index) {
		rangeCheck(index);

		return elementData(index);
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 *
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public E set(int index, E element) {
		rangeCheck(index);

		E oldValue = elementData(index);
		elementData[index] = element;
		return oldValue;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e
	 *            element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public boolean add(E e) {
		ensureCapacityInternal(size + 1); // Increments modCount!!
		elementData[size++] = e;
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);

		ensureCapacityInternal(size + 1); // Increments modCount!!
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	public E remove(int index) {
		rangeCheck(index);

		E oldValue = elementData(index);

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null; // clear to let GC do its work

		return oldValue;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If the list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if
	 * such an element exists). Returns <tt>true</tt> if this list contained the
	 * specified element (or equivalently, if this list changed as a result of the
	 * call).
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++)
				if (elementData[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++)
				if (o.equals(elementData[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	/*
	 * Private remove method that skips bounds checking and does not return the
	 * value removed.
	 */
	private void fastRemove(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null; // clear to let GC do its work
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	public void clear() {
		// clear to let GC do its work
		for (int i = 0; i < size; i++)
			elementData[i] = null;

		size = 0;
	}

	/**
	 * Checks if the given index is in range. If not, throws an appropriate runtime
	 * exception. This method does *not* check if the index is negative: It is
	 * always used immediately prior to an array access, which throws an
	 * ArrayIndexOutOfBoundsException if index is negative.
	 */
	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * A version of rangeCheck used by add and addAll.
	 */
	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	/**
	 * Constructs an IndexOutOfBoundsException detail message. Of the many possible
	 * refactorings of the error handling code, this "outlining" performs best with
	 * both server and client VMs.
	 */
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

}

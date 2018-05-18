package Ejercicio1;

import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class SinglyLinkedListProperties {


    @Theory
    public void copyConstructorHasTheSameElementsInOrder(@IntBetween(sizeMin =0, sizeMax =20,rangeMin = -50,rangeMax = 200,cant = 50) SinglyLinkedList l) {
        assumeTrue(!l.isEmpty());
        SinglyLinkedList copy = new SinglyLinkedList(l);
        Node copyNode = copy.getHeader().getNext();
        Node originalNode = copy.getHeader().getNext();
        while(copyNode != null){
            assertThat(copyNode.getValue(),equalTo(originalNode.getValue()));
            copyNode = copyNode.getNext();
            originalNode = originalNode.getNext();
        }
    }

    @Theory
    public void removingAnElementDecreasesSize(@IntBetween(sizeMin =1, sizeMax =50,rangeMin = 10,rangeMax = 15,cant = 50) SinglyLinkedList l) {
        assumeTrue(l.contains(13));
        int prevSize = l.getSize();
        l.remove(13);
        assertThat(prevSize,greaterThan(l.getSize()));
    }

    @Theory
    public void containsAnAddedElement(@IntBetween(sizeMin =0, sizeMax =20,rangeMin = 1,rangeMax = 25,cant = 70) SinglyLinkedList l) {
        assumeTrue(!l.contains(10));
        l.addFirst(10);
        assertTrue(l.contains(10));
    }
        @Test
    public void aNewListIsEmpty() {
        SinglyLinkedList emptySll = new SinglyLinkedList();
        assertTrue(emptySll.isEmpty());
    }




}

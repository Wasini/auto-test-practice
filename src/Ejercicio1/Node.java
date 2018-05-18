package Ejercicio1;
public class Node {

	public Node(int value) {
		this.setValue(value);
		this.setNext(null);
	}
	
	private int value;
	
	private Node next;


	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}

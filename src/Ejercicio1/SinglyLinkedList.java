package Ejercicio1;

public class SinglyLinkedList {
	
   private Node header;
   private int size;
   
   public Node getHeader() {
	   return header;
   }

   public void setHeader(Node header) {
	   this.header = header;
   }

   public int getSize() {
	   return size;
   }

   public void setSize(int size) {
	   this.size = size;
   }


   public SinglyLinkedList(){
	   // Nodo ficticio
	   setHeader(new Node(0));
	   setSize(0);
   }	

   // Crea una lista nueva conteniendo los valores de another en el mismo orden
   public SinglyLinkedList(SinglyLinkedList another) {
	   // Nodo ficticio
	   setHeader(new Node(0));
	   Node prev = getHeader();
	   Node currAnother = another.getHeader().getNext();
	   
	   for (int i = 0; i < another.getSize(); i++) {
		   Node newn = new Node(currAnother.getValue());
		   prev.setNext(newn);
		   prev = newn;
		   currAnother = currAnother.getNext();
	   }
	   
	   setSize(another.getSize());
   }
   
   public boolean contains(int value){
	   Node current = getHeader().getNext();	
	   while (current!=null){
		   if(current.getValue() == value)
			   return true;
		   current = current.getNext();
	   } 
	   return false;
    }

    public void addFirst(int value){
		Node n = new Node(value);
		n.setNext(getHeader().getNext());
		getHeader().setNext(n);
		setSize(getSize() + 1);
    }

    public void remove(int value){
		Node current = getHeader().getNext();
		Node previous = getHeader();
			
	   	while(current!=null && current.getValue() != value){
			previous = current;		
			current = current.getNext();
	  	}
	   	
	   	if (current!=null) {
	   		previous.setNext(current.getNext());
			setSize(getSize() - 1);
	   	}
    }

    
    /**
 	 * Checks whether or not the current list has not values.
     * @return true iff the current list is empty, false otherwise.
     */
    public boolean isEmpty(){
    	return getHeader().getNext()== null;
    }

    public String toString() {
    	String res = "[";
    	Node curr = getHeader().getNext();
 	   	for (int i = 0; i < getSize(); i++) {
 	   		res += curr.getValue();
 	   		if (i < getSize()-1)
 			   res += ",";
 	   		curr = curr.getNext();
 	   	}
 	   	return res + "]";
    }
   	
}

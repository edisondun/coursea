
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int N;
	private Node<Item> first;
	private Node<Item> last;
	
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
	}
	
	public Deque(){
		// construct an empty deque
		first = null;
		last = null;
		N = 0;
	}
	
	public boolean isEmpty(){
		// is the deque empty?
		return N==0;
	}
	public int size(){
		return N;
		// return the number of items on the deque
	}
	
	public void addFirst(Item item){
		// insert the item at the front
        if (item == null) throw new NullPointerException("can't add null item");
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		first.previous = null;
		if (!isEmpty()){
			oldfirst.previous = first;
		}else{
			last = first;
		}
		N++;
	}
	public void addLast(Item item){
		// insert the item at the end
		if (item == null) throw new NullPointerException("can't add null item");
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		last.previous = oldlast;
		if (!isEmpty()){
			oldlast.next = last;
		}else{
			first = last;
		}
		N++;
	}
	public Item removeFirst(){
		// delete and return the item at the front
        if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
        Node<Item> oldfirst = first;
        first = first.next;
        N--;
        if (!isEmpty()){first.previous = null;}
        else{
        	last = null;
        }
		return oldfirst.item;
	}
	public Item removeLast(){
		// delete and return the item at the end
        if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
        Node<Item> oldlast = last;
        last = last.previous;
        N--;
        if (!isEmpty()){last.next = null;}
        else{
        	first = null;
        }
		return oldlast.item;
	}
	public Iterator<Item> iterator(){
		// return an iterator over items in order from front to end
		return new ListIterator<Item>(first);  
	}
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		public ListIterator(Node<Item> first){
			current = first;
		}
		public boolean hasNext(){
			return current != null;
		}
		public void remove()      { throw new UnsupportedOperationException();  }
		public Item next(){
			if (!hasNext())throw new NoSuchElementException();
			Item temp = current.item;
			current = current.next;
			return temp;
		}
	}
	public static void main(String[] args) {
		// unit testing
		Deque<Integer> test = new Deque<Integer>();
		test.addFirst(1);
		test.addFirst(2);
		test.addLast(3);
		for (Integer i : test){
			System.out.print(i);
		}
		test.removeFirst();
		System.out.println();
		for (Integer i : test){
			System.out.print(i);
		}
		test.removeLast();
		System.out.println();
		for (Integer i : test){
			System.out.print(i);
		}
		System.out.print(test.removeLast());
		System.out.println();
		for (Integer i : test){
			System.out.print(i);
		}
		test.addLast(3);
		System.out.println();
		for (Integer i : test){
			System.out.print(i);
		}
		System.out.println("done");
	}
}

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N=0;
	private int first=0;
	private int last=0;
	private Item[] q;
	
	public RandomizedQueue(){
		// construct an empty randomized queue\
		q = (Item[]) new Object[2];
	}
	public boolean isEmpty(){
		// is the queue empty?
		return N==0;
	}
	public int size(){
		// return the number of items on the queue
		return N;
	}
	private void resize(int max){
		Item[] temp = (Item[]) new Object[max];
		for ( int i =0; i<N; i++){
			temp[i] = q[(first+i)%q.length];
		}
		q= temp;
		first = 0;
		last = N;
	}
	public void enqueue(Item item){
		// add the item
		if (item == null) throw new NullPointerException("can't add null item");
		if (N ==q.length)resize(2*q.length);
		q[last++] = item;
		if (last == q.length )last = 0;
		N++;
	}
	public Item dequeue(){
		// delete and return a random item
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		int index = (first+StdRandom.uniform(N))%q.length;
		Item temp = q[index];
		q[index] = q[first];
		q[first] = null;
		N--;
		first++;
		if(first == q.length)first =0;
		if (N>0 && N==q.length/4)resize(q.length/2);
		return temp;
	}
	public Item sample(){
		// return (but do not delete) a random item
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		return q[(first+StdRandom.uniform(N))%q.length];		
	}
	public Iterator<Item> iterator(){
		// return an independent iterator over items in random order
		return new ArrayIterator(first,last,N, q);
	}
    private class ArrayIterator implements Iterator<Item> {
        private int head = 0;
        private int tail = 0;
        private int x =0;
        private boolean[] store;
        public ArrayIterator(int first, int last,int N, Item[] q){
        	head = first;
        	tail = last;
        	x = N;
        	store = new boolean[q.length];
        	for (int i = 0; i<q.length; i++){
        		store[i] = false;
        	}
        }
        public boolean hasNext()  { return x>0;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
    		int index = (StdRandom.uniform(q.length))%store.length;
    		Item temp = null;
    		while ((store[index])||index>q.length||(q[index]==null)){
    			index = (StdRandom.uniform(q.length))%store.length;
    		}

        		temp = q[index];
        		store[index] = true;
//        		head++;
//        		if(head == q.length)head =0;
        	x--;
    		return temp;
        }
    }
	public static void main(String[] args) {
		// unit testing
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		   q.enqueue("1");
		   q.enqueue("2");
		   q.enqueue("3");
		   q.enqueue("4");
		   for (String i : q){
			   System.out.print(i);
		   }
		   System.out.print("done");
		   q.dequeue();
		   for (String j : q){
			   System.out.print(j);
		   }
	}
}
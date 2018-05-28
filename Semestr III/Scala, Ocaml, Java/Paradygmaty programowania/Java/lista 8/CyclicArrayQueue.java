
import java.util.*;

public class CyclicArrayQueue<E> implements MyQueue<E> {
    private List<E> queue;
    private int f;
    private int r;

    public CyclicArrayQueue(int size) {
    	f = 0;
    	r = 0;
        queue = new ArrayList<>(size + 1);
        
        for (int i = 0; i < size + 1; i++) 
            queue.add(null);
        
    }

    @Override
    public void enqueue(E x) throws FullException {
        if (isFull())
            throw new FullException("lista jest pełna");
        else {
            queue.set(r, x);
            System.out.println("r przed: " + r);
            r = (r + 1) % queue.size();

            System.out.println("r po: " + r);
        }
    }

    @Override
    public void dequeue() {
        if (!isEmpty()) {
        	
        	System.out.println("f przed: " + f);
        	
            f = (f + 1) % queue.size();
            System.out.println("f po: " + f);
        }
    }

    @Override
    public E first() throws EmptyException {
        if (isEmpty())
            throw new EmptyException("lista jest pusta");
        else
            return queue.get(f);
    }
    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public boolean isFull() {
        return (r + 1) % queue.size() == f;
    }
    
    public void wyswietl() {
    	for (int i = 0; i < queue.size(); i++) {
    		System.out.println(queue.get(i));
    	}
    }
}

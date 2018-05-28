

public class Main {
	public static void main(String[] args) throws FullException, EmptyException {
	    MyQueue<String> queue = new CyclicArrayQueue<>(3);
	    
	    queue.enqueue("jeden");
	    queue.enqueue("dwa");
	    queue.enqueue("trzy");
	    
	    System.out.println(queue.first());
	    System.out.println();
	    queue.wyswietl();
	    
	    
	    System.out.println("dequeue");
	    
	    queue.dequeue();
	    System.out.println(queue.first());
	    System.out.println();
	    
	    System.out.println("po dequeue");
	    queue.wyswietl();
	    System.out.println();
	    System.out.println();
	    
	    
	    queue.enqueue("cztery");
	    System.out.println(queue.first());
	    System.out.println();
	    
	    queue.wyswietl();
	    System.out.println();
	    System.out.println();
	    
	    queue.dequeue();
	    System.out.println();
	    queue.enqueue("piec");
	    System.out.println(queue.first());
	    System.out.println();
	    
	    queue.wyswietl();
	    /*
	    
	    queue.dequeue();
	    queue.enqueue("pięć");
	    System.out.println(queue.first());
	    System.out.println();
	    
	    queue.wyswietl();
	    */
	}


}

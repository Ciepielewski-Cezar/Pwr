import java.lang.Exception;

public interface Queue {

public void enqueue(Object value); //wstaw do kolejki

public Object dequeue(); //pobierz z kolejki

public void clear(); //usuń wszystkie elementy

public int size(); //długość kolejki

public boolean isEmpty(); // true jeśli kolejka jest pusta

}
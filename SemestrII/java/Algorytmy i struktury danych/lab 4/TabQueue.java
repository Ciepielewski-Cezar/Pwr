
public class TabQueue implements Queue{
  Object[] tab;
  int size;
  int gdzie = 0;
  public TabQueue (int sizeP){
      size = sizeP;
      tab = new Object[size];
    }
  
  public void enqueue(Object value) throws FullQueueException{ //wstaw do kolejki
      FullQueueException(gdzie);
      tab[gdzie] = value;
      gdzie++;
      
  }
      

  public Object dequeue() throws EmptyQueueException{ //pobierz z kolejki
      if(isEmpty()){
          throw new EmptyQueueException();
      }
      Object oldValue = tab[0];
      tab[0] = null;
      for(int i = 0; i<gdzie-1; i++){
          tab[i] = tab[i+1];
      }
      
      gdzie--;
      return oldValue;
  }
      

  public void clear(){ //usuń wszystkie elementy
      Object[] pomocnicza = new Object[size];
      tab = pomocnicza;
  }

  public int size(){ //długość kolejki
      return size;
  }

  public boolean isEmpty(){ // true jeśli kolejka jest pusta
      return gdzie == 0;
  }
    
  private void FullQueueException(int indeks) throws IndexOutOfBoundsException{
        if (indeks >= size)
            throw new IndexOutOfBoundsException();
  }
    
  private void EmptyQueueException(int indeks) throws IndexOutOfBoundsException{
      if (indeks < 0)
      throw new IndexOutOfBoundsException();
  }
  
  void wyswietl(){
      for(int i = 0; i<gdzie; i++){
          System.out.println(tab[i]);
      }
      System.out.println();
  }
}


public class StosTab implements Stos{
    Object[] tab;
    int size;
    int ilosc = 0;
    public StosTab(int size){
        this.size = size;
        tab = new Object[size];
    }
    
    public void push(Object value){
        tab[ilosc] = value;
        ilosc++;
        int granica = size-(size/4);
        if(ilosc >= granica){
            size *= 2;
            //System.out.println("Powiększona");
            
            Object[] pomocnicza = tab;
            tab = new Object[size];
            przepisz(pomocnicza, tab, pomocnicza.length);
        }
    }
        
    public Object pop() throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Object oldValue = tab[ilosc-1];
        tab[ilosc] = null;
        --ilosc;
        
        int granica = size/4;
        if(ilosc <= granica){
            size /= 2;
            //System.out.println("Pomniejszona");
            
            Object[] pomocnicza = tab;
            tab = new Object[size];
            przepisz(pomocnicza, tab, tab.length);
        }
        return oldValue;
    }
    
    public Object peek() throws EmptyStackException{
      if(isEmpty()){
          throw new EmptyStackException();
      }
      return tab[ilosc-1];
    }
    
    public void clear(){
        Object[] pomocnicza = new Object[size];
        tab = pomocnicza;
        ilosc = 0;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        if (ilosc == 0){
            return true;
        }
        return false;
    }
    
    void wyswietl(){
      for(int i = 0; i<ilosc; i++){
          System.out.println(tab[i]);
      }
      System.out.println();
    }
    
    void przepisz(Object[] old, Object[] current, int rozmiar){
        for(int i = 0; i < rozmiar; i++){
                current[i] = old[i];
        }
    }
}

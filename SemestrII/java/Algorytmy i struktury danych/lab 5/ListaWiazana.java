import java.util.*;
import java.lang.Exception;
public class ListaWiazana{
    private Element headAndTail = new Element(null);
    private int size;
    
    
    //Część pierwsza
    public void ListWiazana(){
        clear();
    }
    
    public void clear(){
       headAndTail.setNext(headAndTail);
       size = 0;
    }
            
    public void insert(int indeks, Object value) throws IndexOutOfBoundsException{
        if(indeks < 0 || indeks > size) throw new IndexOutOfBoundsException();

        Element current = new Element(value);
        Element previous = whereIs(indeks -1);
        
        current.setNext(previous.getNext());
        previous.setNext(current);
        size++;
    }
    
    public Object delete(int indeks) throws IndexOutOfBoundsException {
        checkOutOfBounds(indeks);
        Element element = whereIs(indeks);
        Element previous = whereIs(indeks - 1);
        previous.setNext(element.getNext());
        --size;
        return element.getValue();
    }
    
    public Object get(int indeks) throws IndexOutOfBoundsException{
        checkOutOfBounds(indeks);
        return whereIs(indeks).getValue();
    }
        
    public Element whereIs(int index) {
        Element el = headAndTail;
        
        for( int i = 0; i <= index; i++ ) {
            el = el.getNext();
            if(el == headAndTail) return headAndTail;
        }
        return el;
    }
    
    public void wyswietl(){
        Element element = headAndTail.getNext();
        for(int i = 0; i<size; i++){
            System.out.println(element.value);
            element = element.getNext();
        }
    }
    
    private void checkOutOfBounds(int indeks) throws IndexOutOfBoundsException{
        if (indeks < 0 || indeks > size)
            throw new IndexOutOfBoundsException();
    }
    
    public int size(){
        return size;
    }
    
    
    //druga część
    public void add(Object value){
        insert(size(), value);
    }
    
    public boolean delete(Object value){
        int i = indexOf(value);
        if (i == -1){ return false;}
        else{delete(i); return true;}
    }
    
    public Object set(int indeks, Object value) throws IndexOutOfBoundsException{
        checkOutOfBounds(indeks);
        Element element = whereIs(indeks);
        Object oldValue = element.getValue();
        element.setValue(value);
        return oldValue;
    }
    
    public int indexOf(Object value){
        for(int i = 0; i<size; i++){
            if( get(i).equals(value)) return i;
        }
        return -1;
    }
    
    public boolean contains(Object value){
        return indexOf(value) != -1;
    }
    
    public boolean isEmpty(){
         return size == 0;
    }
    
    private static final class Element{
        private Object value;
        private Element next;

        public Element(Object valueP){
            value = valueP;
            next = null;
        }
        
        public Element(Object valueP, Element nextP){
            value = valueP;
            next = nextP;
        }

        public void setValue(Object valueP){
            value = valueP;
        }

        public Object getValue(){
            return value;
        }

        public Element getNext(){
            return next;
        }
        
        public void setNext(Element nextP){
            next = nextP;
        }
        //wstaw przed nextP
        public void attachBefore(Element nextP){
            setNext(nextP);
        }
    }
}
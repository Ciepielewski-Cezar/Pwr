
public class StosLista implements Stos{
    private Element head = new Element(null);
    private int size;
    
    public void push(Object value){
        if (head.getNext() == null){
            Element element = new Element(value);
            head.setNext(element);
        }
        else{
            Element element = new Element(value);
            Element previous = whereIs(size - 1);
            previous.setNext(element);
        }
        ++size;
    }
    
    public Object pop() throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }
        Element element = whereIs(size-1);
        Element previous = whereIs(size - 2);
        previous.setNext(null);
        --size;
        return element.getValue();
    }
    
    public Object peek() throws EmptyStackException{
        Object result = pop();
        push(result);
        return result;
    }
    
    public void clear(){
       head.setNext(null);
       size = 0;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    void wyswietl(){
      Element element = head.getNext();
        for(int i = 0; i<size; i++){
            System.out.println(element.value);
            element = element.getNext();
        }
    }
    
    public Element whereIs(int index) {
        Element el = head;
        
        for( int i = 0; i <= index; i++ ) {
            el = el.getNext();
            if(el == null) return null;
        }
        return el;
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


public class IteratorFiltrujacy implements Iterator{
    final Iterator iter;
    final Predicate pred;
    
    public IteratorFiltrujacy(Iterator i, Predicate predykat){
        iter = i;
        pred = predykat;
    }
    public void filtrujDoPrzodu(){
        while(!iter.isDone() && !pred.accept(iter.current())){
            iter.next();
        }
    }
    public void filtrujDoTylu(){
        while( !iter.isDone() && !pred.accept(iter.current())){
            iter.previous();
        }
    }
    
    public void first(){
        iter.first();
        filtrujDoPrzodu();
    }
    
    public void last(){
        iter.last();
        filtrujDoTylu();
    }
    
    public void next(){
        iter.next(); 
        filtrujDoPrzodu();
    }
    
    public void previous(){
        iter.previous(); 
        filtrujDoTylu();
    }
    
    public boolean isDone()
    { return iter.isDone(); }
    public Object current()
    { return iter.current();}
}

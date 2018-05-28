
 
public class ArrayIterator implements Iterator {
    final Object[] TAB;
    final int PIERWSZY;
    final int OSTATNI;
    int biezacy = -1;
    
    public ArrayIterator(Object[] TAB){
        this.TAB = TAB;
        PIERWSZY = 0;
        OSTATNI = TAB.length-1;
    }
    
    public void first(){
        biezacy = PIERWSZY;
    }
    
    public void last(){
        biezacy = OSTATNI;
    }
    public void next(){
        ++biezacy;
    }
    
    public void previous(){
        --biezacy;
    }
    
    public boolean isDone(){
        return biezacy<PIERWSZY || biezacy>OSTATNI;
    }
    
    public Object current(){
        return TAB[biezacy];
    }
}

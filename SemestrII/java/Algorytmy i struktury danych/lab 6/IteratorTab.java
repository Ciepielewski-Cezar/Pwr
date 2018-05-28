import java.util.*;

public class IteratorTab implements Iterator{
    final Object[] tab;
    final int pierwszy;
    final int ostatni;
    int biezacy = -1;
    
    public IteratorTab(Object[] tab){
        this.tab = tab;
        pierwszy = 0;
        ostatni = tab.length-1;
    }
    public void first(){
        biezacy = pierwszy;
    }
    public void last(){
        biezacy = ostatni;
    }
    public void next(){
        ++biezacy;
    }
    public void previous(){
        --biezacy;
    }
    public boolean isDone(){
        return biezacy<pierwszy || biezacy>ostatni;
    }
    public Object current(){
        return tab[biezacy];
    }
        
}
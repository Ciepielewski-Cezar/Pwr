
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundComparator implements Comparator { 
    //tablica komparatorów ; od najważniejszego      
    private final Object[] komparatory = new Object[5];
    private int size = 0;
 
    public void addComparator(Comparator comparator){
        komparatory[size] = comparator;
        size++;
    } 
 
    public int compare(Object left, Object right) throws ClassCastException {
        int result = 0;            
        for (int i = 0; i<komparatory.length &&
        (result = ((Comparator) komparatory[i]).compare(left, right))==0; i++);        
        return result;     
    } 
} 


public class MarkComparator implements Comparator{
    public static final MarkComparator INSTANCE = new MarkComparator();
    
    private MarkComparator() {} 
    
    public int compare(Object left, Object right) throws ClassCastException{
        return ((Samochod)left).porMarka((Samochod)right);
    }
}
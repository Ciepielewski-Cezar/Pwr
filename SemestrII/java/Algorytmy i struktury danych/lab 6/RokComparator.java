
public class RokComparator implements Comparator{
    public static final RokComparator INSTANCE = new RokComparator();
    
    private RokComparator() {} 
    
    public int compare(Object left, Object right) throws ClassCastException{
        return ((Samochod)left).porRok((Samochod)right);
    }
}
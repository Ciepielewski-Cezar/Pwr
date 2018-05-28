
public final class NaturalComparator implements Comparator{
    public static final NaturalComparator INSTANCE = new NaturalComparator();
    
    private NaturalComparator() { }
    
    public int compare(Object left, Object right) throws ClassCastException{
        return ((Comparable) left).compareTo(right);
    }
    
}